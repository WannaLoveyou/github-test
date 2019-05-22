
$(document).ready(
function(){
	
	$('#secondCategoryToolId').combobox({
		icons : [ {
			iconCls : 'icon-combo-clear',
			handler : function(e) {
				$(e.data.target).combobox('clear');
			}
		} ],
		url : basePath + '/secondCategory/getMyList',
		method : 'get',
		valueField : 'id',
		textField : 'second_category_name',

	});
	
	$('#airBottleTypeToolId').combobox({
		icons:[{
			iconCls:'icon-combo-clear',
			handler:function(e){
				$(e.data.target).combobox('clear');
			}
		}],
		value : "",
		editable : false,
		method:'get',
		url : basePath+'/airbottleType/getAllList',
		valueField : 'id',
		textField : 'air_bottle_specifications',
	});
});

function conveterParamsToJson(paramsAndValues) {  
    var jsonObj = {};  
  
    var param = paramsAndValues.split("&");  
    for ( var i = 0; param != null && i < param.length; i++) {  
        var para = param[i].split("=");  
        jsonObj[para[0]] = para[1];  
    }  
  
    return jsonObj;  
}  
  
function queryParamByFormId(form) {  
    var formValues = $("#" + form).serialize();  
      
    // 关于jquery的serialize方法转换空格为+号的解决方法
    formValues = formValues.replace(/\+/g," ");   // g表示对整个字符串中符合条件的都进行替换
    var temp = JSON.stringify(conveterParamsToJson(formValues));  
    var queryParam = JSON.parse(temp);  
    return queryParam;  
} 

$("#search").click(function(){
	var queryParams=queryParamByFormId("toolbarForm"); 
	
$('#tb').datagrid({ 
		
		url: basePath+'/clientAirBottleTypeFee/getPageList',
		method:'get',
		queryParams:queryParams,
		pagination:true,
		pageSize:5,
		pageList:[5,10,15,20],
		rownumbers:true,
		striped:true,
		fitColumns:true,
		toolbar: '#toolbar',
		width:'auto',
		height:'auto',
		title:"运气费管理",
		
		columns:[[    
			{field:'ck',checkbox:true}, 
			{field:'clientInfo.secondCategory',title:'所属门店',align:'center',width:100,formatter:getSecondCategory},
			{field:'clientInfo.client_code',title:'客户编码',align:'center',width:100,formatter:getClientCode},
			{field:'clientInfo.client_name',title:'客户',align:'center',width:100,formatter:getClient},
			{field:'airBottleType.air_bottle_specifications',title:'气瓶类型',align:'center',width:100,formatter:getAirBottleType},
			{field:'delivery_fee',title:'运气费',align:'center',width:100},
			{field:'check_fee',title:'检测费',align:'center',width:100},
			{field:'account_price',title:'气瓶单价优惠',align:'center',width:100},
		]],
		
		loadFilter: function(data){
			
			if (data.data!=null){
				return data.data;
			}
			return null;
		}
	});
});

function getSecondCategory(value,rec){
	if(rec.clientInfo!=null && rec.clientInfo.secondCategory!=null){
		return rec.clientInfo.secondCategory.second_category_name ;		
	}
}


function getAirBottleType(value,rec){
	if(rec.airBottleType!=null){
		return rec.airBottleType.air_bottle_specifications ;		
	}
}

function getClientCode(value,rec){
	if(rec.clientInfo!=null){
		return rec.clientInfo.client_code;
	}
}

function getClient(value,rec){
	if(rec.clientInfo!=null){
		return rec.clientInfo.client_name;
	}
}

function getClientName(){
	
	$.ajax({
		type : 'post',
		async : false,    
		cache : false,
		data : {client_code:$("#client_code").val()},
		url : basePath+'/clientInfo/getClientNameByclientCode',
		success : function(data) {
			if(data.data==null){
				$("#tdRemark").text("X");
				$("#client_name").val("");
			}else{
				$("#tdRemark").text("√");
				$("#clientInfoId").val(data.data.id);
				$("#client_name").val(data.data.client_name);
			}
		}
	});
}
var flag;

$("#add").click(function(){

	$("#tdRemark").text("");
	$("#mydialog").dialog({
	title:"新增运气费单价类型"
	});
	$("#myform").get(0).reset();
	$("#myform").form("load",{
		id:0,
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
		$("#myform").form("load",{
			delivery_fee:0,
			check_fee:0,
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
			url : basePath+'/clientAirBottleTypeFee/del',
			success : function(data) {
				$("#tb").datagrid("reload");
			}
		});
	}
});


$("#edit").click(function(){
	
	$("#mydialog").dialog({
	title:"修改运气费单价类型"
	});
	$("#tdRemark").text("");
	$("#myform").get(0).reset();
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
			"clientInfo.id":selectRows[0].clientInfo.id,
			"client_code":selectRows[0].clientInfo.client_code,
			client_name:selectRows[0].clientInfo.client_name,
			'airBottleType.id':selectRows[0].airBottleType.id,
			delivery_fee:selectRows[0].delivery_fee,
			check_fee:selectRows[0].check_fee,
			account_price:selectRows[0].account_price,
		});
		$("#mydialog").dialog("open");
		flag='edit';
	}
	
});


$("#btn2").click(function(){
	$("#mydialog").dialog("close");
});


$("#btn1").click(function(){
	if (!$("#myform").form('validate')) {
		return false;
	}
	
	
	if($("#delivery_fee").val()==""||$("#check_fee").val()==""){
		
		$("#delivery_fee").val();
		$("#check_fee").val();
	}
	$.ajax({
		type : 'post',
		data : $('#myform').serialize(),
		url : basePath+'/clientAirBottleTypeFee/'+flag,
		success : function(data) {
			
			if(data.code==200){
				$("#mydialog").dialog("close");
				$("#tb").datagrid("reload");
				$.messager.show({
					title : '提示信息',
					msg : '操作成功'
				});
			}else{
				$.messager.show({
					title : '提示信息',
					msg : data.msg
				});
			}
			
			
		}
	});
	
});