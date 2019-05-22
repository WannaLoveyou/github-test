
$(document).ready(
function(){
	$('#tb').datagrid({ 
		
		url: basePath+'/partType/getPageList',
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
		title:"零件类型管理",
		
		columns:[[    
			{field:'ck',checkbox:true}, 
			{field:'part_type_name',title:'零件类型名',align:'center',width:100},
			{field:'note',title:'零件描述',align:'center',width:100},
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
	title:"新增零件类型"
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
			url : basePath+'/partType/del',
			success : function(data) {
				$("#tb").datagrid("reload");
			}
		});
	}
});


$("#edit").click(function(){
	$("#mydialog").dialog({
	title:"修改零件类型"
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
			part_type_name:selectRows[0].part_type_name,
			note:selectRows[0].note,
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
		url : basePath+'/partType/'+flag,
		success : function(data) {
			$("#mydialog").dialog("close");
			$("#tb").datagrid("reload");
		}
	});
});