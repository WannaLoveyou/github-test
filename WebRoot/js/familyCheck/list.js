$(document).ready(
function(){
	
	var secondCategoryTableSelectFlag = -1;
	
	$('#secondCategoryTable').datagrid({ 
		 
		onClickRow:function(rowIndex, rowData){
			
			if(secondCategoryTableSelectFlag == rowIndex){
				$(this).datagrid('unselectRow', rowIndex);
				secondCategoryTableSelectFlag = -1;
			}else{
				secondCategoryTableSelectFlag = rowIndex;
			}			
		}
	});
	
});

$("#search").click(function() {
	setFamilyCheckTable();	
});

/**
 * 撤销
 */
$("#revoke").click(function() {
	revoke();	
});

$("#getContent").click(function() {
	
	var selectRows = $("#familyCheckTable").datagrid("getSelections");
	if(selectRows.length!=1){
		$.messager.show({
			title:'提示信息',
			msg:'请选择一条记录'
		});
	}else{
		window.open(basePath + "/familyCheck/contentList?familyCheckId=" + selectRows[0].id);  
	}
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

function setFamilyCheckTable(){	
	
	var queryParams=queryParamByFormId("toolbarForm"); 
	var secondCategoryTableRow = $('#secondCategoryTable').datagrid('getSelected');

	var familyCheckTableUrl = basePath+'/familyCheck/getPageList?';
	
	if(secondCategoryTableRow!=null){
		familyCheckTableUrl+='secondCategory.id='+secondCategoryTableRow.id+'&';
	}
	
	$('#familyCheckTable').datagrid({ 
		url: familyCheckTableUrl,
		queryParams:queryParams,
		singleSelect: false,
		method:'post',
		columns:[[    
		 
            {field:'family_check_address',title:'地址',align:'center',width:100},
		    {field:'clientInfo.client_name',title:'用户名称',align:'center',formatter:function(value,row,index){
		    	return row.clientInfo.client_name;
		    }},
		    {field:'clientInfo.client_code',title:'用户编码',align:'center',formatter:function(value,row,index){
		    	return row.clientInfo.client_code;
		    }},
		    {field:'family_check_tel_number',title:'订单电话号码',align:'center',
		    },
		    {field:'state_description',title:'订单状态',align:'center',formatter: function(value,row,index){
				return row.familyCheckInfoState.state_description;
			}},
			 {field:'remark',title:'备注',align:'center',},
		    
		    {field:'create_time',title:'创建时间',align:'center',formatter: function(value,row,index){
				return formatDate(new Date(row.create_time));
			}},    
			{field:'appointment_check_time',title:'预约时间',align:'center',formatter: function(value,row,index){
				if(row.appointment_check_time!=null){
				return formatDate(new Date(row.appointment_check_time));
				}
				return null;
			}},
			{field:'check_time',title:'检查时间',align:'center',formatter: function(value,row,index){
				if(row.check_time!=null){
				return formatDate(new Date(row.check_time));
				}
				return null;
			}},
			{field:'full_name',title:'下单操作员',align:'center',formatter:function(value,row,index){
				return row.operator.full_name;
		    }},
		    {field:'check_operator_full_name',title:'检查员',align:'center',formatter:function(value,row,index){
				if(row.check_operator!=null){
					return row.check_operator.full_name;
				}
				return null;
		    }},
			{field:'family_check_code',title:'订单编号',width:100,align:'center'},
		]],
        loadFilter: function(data){
			
			if (data.data!=null){
				return data.data;
			}
			return null;
		}

	});
}

/**
 * 撤销
 */
function revoke(){
	
	var selectRows = $("#familyCheckTable").datagrid("getSelections");
	if(selectRows.length<=0){
		$.messager.show({
          title:'提示信息',
          msg:'请至少选择一条'
		});
		return;
	}
	
	$.messager.confirm('提示','仅待受理状态的可撤销，其他状态的不会被撤销！',function(r){
	    if (r){
	    	var selectRows = $("#familyCheckTable").datagrid("getSelections");
	    	var ids="";
		      for(var i=0;i<selectRows.length-1;i++){
		          ids+=selectRows[i].id+",";
		      }
		      ids+=selectRows[selectRows.length-1].id;
		    	
		      $.ajax({
		          type : 'post',
		          data : {ids:ids},
		          url : basePath+'/familyCheck/revokeFamilyCheck',
		          success : function(data) {
		              $("#familyCheckTable").datagrid("reload");
		              if(data.code!=200){
		            	  $.messager.show({
				  	            title:'提示信息',
				  	            msg:data.msg
				  	  		  });
		              }else{
		            	  $.messager.show({
				  	            title:'提示信息',
				  	            msg:'成功撤销'+data.data.num+'条数据'
				  	  		  });
		              }
		          }
		     });
	    }else{
	    	$.messager.show({
	            title:'提示信息',
	            msg:'已成功取消当前操作'
	  		});
	    }
	});
}

function formatDate(now) { 
	var year=now.getFullYear(); 
	var M=now.getMonth()+1;  
    var MM=(M<10)?"0"+M:M;  
	var D= now.getDate();  
    var DD=(D<10)?"0"+D:D; 
    var h=now.getHours();  
    var hh=(h<10)?"0"+h:h;  
    var m=now.getMinutes();  
    var mm=(m<10)?"0"+m:m; 
    var s=now.getSeconds();  
    var ss=(s<10)?"0"+s:s;  
	return year+"-"+MM+"-"+DD+" "+hh+":"+mm+":"+ss; 
} 


$("#viewFamilyCheckPic").click(function(){

    var selectRow = $("#familyCheckTable").datagrid("getSelected");
    
	if(selectRow==null){
		$.messager.show({
			title:'提示信息',
			msg:'请选择一条记录'
		});
		return;
	}
	
	if(selectRow.photo_urls == null){
		$.messager.show({
			title:'提示信息',
			msg:'该记录没有图片'
		});
		return;
	}
	
	$("#familyCheckPic").attr("src",viewFamilyCheckOrderPhotoPath  + selectRow.photo_urls);

	$("#viewFamilyCheckPicDiv").dialog("open");
});
