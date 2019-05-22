
$(document).ready(
function(){
	$('#tb').datagrid({ 
		
		url: basePath+'/visitType/getPageList',
		method:'get',
		pagination:true,
		pageSize:5,
		pageList:[5,10,15,20],
		rownumbers:true,
		striped:true,
		fitColumns:true,
		toolbar: '#toolbar',
		width:'auto',
		height:'auto',
		title:"回访类型管理",
		
		columns:[[    
			{field:'ck',checkbox:true}, 
			{field:'visit_type_name',title:'回访类型名',align:'center',width:100},
			{field:'visit_content',title:'回访描述',align:'center',width:100},
		]],
		
		loadFilter: function(data){
			
			if (data.data!=null){
				return data.data;
			}
			return null;
		}
	});
});
function getAirBottleType(value,rec){
	if(rec.airBottleType!=null){
		return rec.airBottleType.air_bottle_specifications ;		
	}
}
function getClient(value,rec){
	if(rec.clientInfo!=null){
		return rec.clientInfo.client_name;
	}
}
var flag;

$("#add").click(function(){
	
	$("#mydialog").dialog({
	title:"新增回访类型"
	});

	$("#myform").get(0).reset();
	$("#mydialog").dialog("open");
	flag='add';
});



$("#del").click(function(){
	var selectRows = $("#tb").datagrid("getSelections");
	if(selectRows.length<1){
		$.messager.show({
			title:'提示信息',
			msg:'请选择要删除的记录'
		});
	}else{
		var ids="";
		for(var i=0;i<selectRows.length;i++){
			ids+=selectRows[i].id+",";
		}
		$
		.ajax({
			type : 'post',
			data : {ids:ids},
			url : basePath+'/visitType/del',
			success : function(data) {
				$("#tb").datagrid("reload");
			}
		});
	}
});


$("#edit").click(function(){
	$("#mydialog").dialog({
	title:"修改回访类型"
	});
	$("#myform").get(0).reset();
	
	var selectRows = $("#tb").datagrid("getSelections");
	if(selectRows.length!=1){
		$.messager.show({
			title:'提示信息',
			msg:'请选择一条记录'
		});
	}else{
		$("#myform").get(0).reset();
		$("#myform").form("load",{
			id:selectRows[0].id,
			visit_type_name:selectRows[0].visit_type_name,
			visit_content:selectRows[0].visit_content,
		});
		$("#mydialog").dialog("open");
		flag='edit';
	}
	
});


$("#btn2").click(function(){
	$("#mydialog").dialog("close");
});

$("#btn1").click(function(){
	$.ajax({
		type : 'post',
		data : $('#myform').serialize(),
		url : basePath+'/visitType/'+flag,
		success : function(data) {
			$("#mydialog").dialog("close");
			$("#tb").datagrid("reload");
		}
	});
});