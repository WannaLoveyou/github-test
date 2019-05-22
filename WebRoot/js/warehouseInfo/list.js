
$(document).ready(
function(){
	$('#tb').datagrid({ 
		
		url: basePath+'/warehouseInfo/getPageList',
		method:'post',
		pagination:true,
		pageSize:5,
		pageList:[5,10,15,20],
		rownumbers:true,
		striped:true,
		fitColumns:true,
		toolbar: '#toolbar',
		width:'auto',
		height:'auto',
		
		columns:[[    
			{field:'ck',checkbox:true}, 
			{field:'warehouse_code',title:'仓库编码',align:'center',width:100},
			{field:'warehouse_name',title:'仓库名',align:'center',width:100},
			{field:'filling_station_id',title:'充装站点编号',align:'center',width:100},
		]],
		
		loadFilter: function(data){
			
			if (data.data!=null){
				return data.data;
			}
			return null;
		}
	});
});

var flag;

$("#add").click(function(){

	$('#myform').form('clear');
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
			url : basePath+'/warehouseInfo/del',
			success : function(data) {
				$("#tb").datagrid("reload");
			}
		});
	}
});


$("#edit").click(function(){

	
	$('#myform').form('clear');

	
	var selectRows = $("#tb").datagrid("getSelections");
	if(selectRows.length!=1){
		$.messager.show({
			title:'提示信息',
			msg:'请选择一条记录'
		});
	}else{
		
		$("#myform").form("load",{
			entityId:selectRows[0].id,
			warehouse_code:selectRows[0].warehouse_code,
			warehouse_name:selectRows[0].warehouse_name,
			filling_station_id:selectRows[0].filling_station_id,
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
		url : basePath+'/warehouseInfo/'+flag,
		success : function(data) {
			$("#mydialog").dialog("close");
			$("#tb").datagrid("reload");
		}
	});
});