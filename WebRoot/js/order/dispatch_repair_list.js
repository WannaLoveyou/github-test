$(document).ready(
function(){
	
	$('#businessType').combobox({
		value : "",
		editable : false,
		method:'get',
		url : basePath+'/businessType/getAllList',
		valueField : 'id',
		textField : 'business_type_name',
	});
	$('#repairInfoState').combobox({
		value : "",
		editable : false,
		method:'get',
		url : basePath+'/repairInfoState/getAllList',
		valueField : 'id',
		textField : 'repair_state_description',
	});
	
	
	var secondCategoryTableSelectFlag = -1;
	var thirdCategoryTableSelectFlag = -1;
	$('#secondCategoryTable').datagrid({ 
		 
		onClickRow:function(rowIndex, rowData){
			
			if(secondCategoryTableSelectFlag == rowIndex){
				$(this).datagrid('unselectRow', rowIndex);
				secondCategoryTableSelectFlag = -1;
			}else{
				secondCategoryTableSelectFlag = rowIndex;
				$('#thirdCategoryTable').datagrid({ 
					url:basePath+'/thirdCategory/getListBySecondId?secondtId='+rowData.id,
					method:'get',
					columns:[[    
					          {field:'third_category_name',title:'地段',width:100,align:'center'},
					]],
					onClickRow:function(rowIndex, rowData){
						if(thirdCategoryTableSelectFlag == rowIndex){
							$(this).datagrid('unselectRow', rowIndex);
							thirdCategoryTableSelectFlag = -1;
						}else{
							thirdCategoryTableSelectFlag = rowIndex;
						}
					}
				});
			}			
		}
	});
	
	
	var repairInfoTableSelectFlag = -1;
	
	$('#homeLayout').layout('collapse','east');
	$('#repairInfoTable').datagrid({ 
		
		 onClickRow:function(rowIndex, rowData){
				$('#homeLayout').layout('collapse','east');
				if(repairInfoTableSelectFlag == rowIndex){
					$(this).datagrid('unselectRow', rowIndex);
					repairInfoTableSelectFlag = -1;
				}else{
					repairInfoTableSelectFlag = rowIndex;
					
				}
		},
			
	 
	});
	
	
	
});


$("#dispatch").click(function() {
	
	var orderInfoRow = $('#repairInfoTable').datagrid('getSelected');
	var deliveryManRow = $('#deliveryManTable').datagrid('getSelected');
	 $.ajax({
			type : 'post',
			url : basePath+'/repairInfo/dispatch?repairId='+orderInfoRow.id+'&repairManId='+deliveryManRow.id,
			success : function(data) {
				$('#homeLayout').layout('collapse','east');
				$('#repairInfoTable').datagrid('reload'); 
			}
		});
});


$("#search").click(function() {
	setrepairInfoTable();	
});

$("#cancelOrder").click(function() {
	
	var orderInfoRow = $('#repairInfoTable').datagrid('getSelected');
		
	if (orderInfoRow ==null) {
		$.messager.show({
			title : '提示信息',
			msg : '请先选中一条记录'
		});
		return;
	}
	
	$.ajax({
		type : 'post',
		url : basePath+'/repairInfo/cancelOrder?orderId='+orderInfoRow.id,
		success : function(data) {
			if(data.code == 200){
				 $.messager.show({
						title : '提示信息',
						msg : '操作成功'
					});
			    $('#repairInfoTable').datagrid('reload'); 
			}else{
				$.messager.show({
					title : '提示信息',
					msg : data.msg
				});
			}
		}
	});	
});

$("#finishOrder").click(function() {
	
	var orderInfoRow = $('#repairInfoTable').datagrid('getSelected');
		
	if (orderInfoRow ==null) {
		$.messager.show({
			title : '提示信息',
			msg : '请先选中一条记录'
		});
		return;
	}
	
	$.ajax({
		type : 'post',
		url : basePath+'/repairInfo/finishOrder?orderId='+orderInfoRow.id,
		success : function(data) {
			if(data.code == 200){
				 $.messager.show({
						title : '提示信息',
						msg : '操作成功'
					});
			    $('#repairInfoTable').datagrid('reload'); 
			}else{
				$.messager.show({
					title : '提示信息',
					msg : data.msg
				});
			}
		}
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

$("#dispatchDeliveryMan").click(function() {
	var selectRow = $('#repairInfoTable').datagrid('getSelected');
	if (selectRow == null) {
		$.messager.show({
			title : '提示信息',
			msg : '请先选中一条记录'
		});
		return;
	}
	initDeliveryManTable(basePath+'/user/getDeliveryManListBySecondCategoryId?secondCategoryId='+selectRow.secondCategory.id
			, "送气工");
});

$("#dispatchSafetyTechnologyDepartment").click(function() {
	var selectRow = $('#repairInfoTable').datagrid('getSelected');
	if (selectRow == null) {
		$.messager.show({
			title : '提示信息',
			msg : '请先选中一条记录'
		});
		return;
	}
	initDeliveryManTable(basePath+'/user/getSafetyTechnologyDepartmentUsers', "安技部");
});

function initDeliveryManTable(url, title){
	$('#homeLayout').layout('expand','east');
	$('#deliveryManTable').datagrid({ 
		url:url,
		method:'get',
		columns:[[    
		    {field:'full_name',title:title,width:100,align:'center'},
		]]
	});	
}

function setrepairInfoTable(){	
	
	var queryParams=queryParamByFormId("toolbarForm"); 
	var secondCategoryTableRow = $('#secondCategoryTable').datagrid('getSelected');
	var thirdCategoryTableRow = $('#thirdCategoryTable').datagrid('getSelected');

	var repairInfoTableUrl = basePath+'/repairInfo/getRepairInfoAllList?';
	
	if(secondCategoryTableRow!=null){
		repairInfoTableUrl+='secondCategory.id='+secondCategoryTableRow.id+'&';
	}
	
	if(thirdCategoryTableRow!=null){
		repairInfoTableUrl+='thirdCategory.id='+thirdCategoryTableRow.id;
	}
	
	$('#repairInfoTable').datagrid({ 
		url: repairInfoTableUrl,
		queryParams:queryParams,
		method:'post',
		columns:[[    
		    
		    {field:'repair_address',title:'地址',align:'center'},
		    {field:'secondCategory.second_category_name',title:'对应门店',align:'center',formatter:function(value,row,index){
		    	if(row.secondCategory != null){
			    	return row.secondCategory.second_category_name;
		    	}
		    }},
		    {field:'clientInfo.client_name',title:'用户名称',align:'center',formatter:function(value,row,index){
		    	return row.clientInfo.client_name;
		    }},
		    
		    {field:'repair_tel_number',title:'订单电话号码',align:'center',
		    },
		    
		    {field:'repair_state_description',title:'维修状态',align:'center',formatter: function(value,row,index){
				return row.repairInfoState.repair_state_description;
			}},
		    {field:'operator.full_name',title:'操作员',align:'center',formatter:function(value,row,index){
		    	return row.operator.full_name;
		    }},
		    
		    {field:'accept_time',title:'受理时间',align:'center',formatter: function(value,row,index){
				if(row.accept_time!=null){
		    	return formatDate(new Date(row.accept_time));
				}
				return null;
			}},    
	        
			{field:'appointment_time1',title:'预约时间1',align:'center',formatter: function(value,row,index){
				if(row.repair_appointment_time1!=null){
				return formatDate(new Date(row.repair_appointment_time1));
				}
				return null;
			}},
			{field:'appointment_time2',title:'预约时间2',align:'center',formatter: function(value,row,index){
				if(row.repair_appointment_time2!=null){
				return formatDate(new Date(row.repair_appointment_time2));
				}
				return null;
			}},
	        {field:'repair_note',title:'受理备注',align:'center',},
	        {field:'repairType',title:'维修类型',align:'center',formatter: function(value,row,index){
				return row.repairType.repair_type_name;
			}},
	        {field:'repair_content',title:'维修内容',align:'center',},
	        {field:'repair_price',title:'上门费',align:'center',},
			{field:'repair_man',title:'维修人员',align:'center',formatter: function(value,row,index){
				if(row.repair_man != null){
				return row.repair_man.full_name;
				}
				return null;
			}},
	      
			
			{field:'repair_code',title:'维修编号',width:100,align:'center'},
		]]    

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

function getClientname(value,rec)
{
	if(rec.clientInfo!=null){
		return rec.clientInfo.client_name;
	}
	return null;
}


function getAirBottleType(value,rec)
{
	if(rec.airBottleType!=null){
		return rec.airBottleType.air_bottle_specifications;
	}
	return null;
}

function getBusinessType(value,rec)
{
	if(rec.businessType!=null){
		return rec.businessType.business_type_name;
	}
	return null;
}


function getDeliveryType(value,rec)
{
	if(rec.deliveryType!=null){
		return rec.deliveryType.delivery_type_name;
	}
	return null;
}

function getDeliveryman(value,rec)
{
	if(rec.delivery_man!=null){
		return rec.delivery_man.full_name;
	}
	return null;
}


function getOperator(value,rec)
{
	if(rec.operator!=null){
		return rec.operator.full_name;
	}
	return null;
}


function getState(value,rec)
{
	if(rec.state!=null){
		return rec.state.state_description;
	}
	return null;
}

function getOrderTime(value, rec) 
{
	if (rec != null) {
		return new Date(rec.order_time).toLocaleString() ;
	}
			return null;
}