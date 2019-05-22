
$(document).ready(
function(){
	$('#tb').datagrid({ 
		
		url: basePath+'/partPrice/getPageList',
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
		title:"零件单价管理",
		
		columns:[[    
			{field:'ck',checkbox:true}, 
			{field:'partType.part_type_name',title:'零件类型',align:'center',width:100,formatter:getPartType},
			{field:'airBottleType.air_bottle_specifications',title:'气瓶类型',align:'center',width:100,formatter:getAirBottleType},
			{field:'price',title:'零件单价',align:'center',width:100},
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
function getPartType(value,rec){
	if(rec.partType!=null){
		return rec.partType.part_type_name;
	}
}
var flag;

$("#add").click(function(){
	
	$("#mydialog").dialog({
	title:"新增零件单价类型"
	});

	$("#myform").get(0).reset();
	
	$('#partType').combobox({    
    	value : "请选择",    
	    url:basePath+'/partType/getAllList', 
	    method:'get',
	    editable : false,
	    valueField:'id',    
	    textField:'part_type_name',
        loadFilter: function(data){
			if (data.data!=null){
				return data.data;
			}
			return null;
		},
	  });
	  $('#airBottleType').combobox({    
	    	value : "请选择",    
		    url:basePath+'/airbottleType/getNewAllList', 
		    method:'get',
		    editable : false,
		    valueField:'id',    
		    textField:'air_bottle_specifications',
	        loadFilter: function(data){
				if (data.data!=null){
					return data.data;
				}
				return null;
			},
		  });
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
			url : basePath+'/partPrice/del',
			success : function(data) {
				$("#tb").datagrid("reload");
			}
		});
	}
});


$("#edit").click(function(){
	$("#mydialog").dialog({
	title:"修改零件单价类型"
	});
	$("#myform").get(0).reset();
	
	$('#partType').combobox({    
    	value : "请选择",    
	    url:basePath+'/partType/getAllList', 
	    method:'get',
	    editable : false,
	    valueField:'id',    
	    textField:'part_type_name',
        loadFilter: function(data){
			if (data.data!=null){
				return data.data;
			}
			return null;
		},
	  });
	  $('#airBottleType').combobox({    
	    	value : "请选择",    
		    url:basePath+'/airbottleType/getNewAllList', 
		    method:'get',
		    editable : false,
		    valueField:'id',    
		    textField:'air_bottle_specifications',
	        loadFilter: function(data){
				if (data.data!=null){
					return data.data;
				}
				return null;
			},
		  });
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
			'partType.id':selectRows[0].partType.id,
			'airBottleType.id':selectRows[0].airBottleType.id,
			price:selectRows[0].price,
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
		url : basePath+'/partPrice/'+flag,
		success : function(data) {
			$("#mydialog").dialog("close");
			$("#tb").datagrid("reload");
		}
	});
});