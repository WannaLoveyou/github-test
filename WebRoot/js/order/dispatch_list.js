$(document).ready(
function(){
	 document.onkeydown=function(event){
         var e = event || window.event || arguments.callee.caller.arguments[0];
          if(e && e.keyCode==13){ // enter 键
        	  setorderInfoTable();
         }
     }; 
	
	$("#begin_appointment_time").datebox("setValue",getTodayTime()); 
	$("#end_appointment_time").datebox("setValue",getTodayTime()); 
	
	$('#airBottleType').combobox({
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
	
	$('#deliveryType').combobox({
		icons:[{
			iconCls:'icon-combo-clear',
			handler:function(e){
				$(e.data.target).combobox('clear');
			}
		}],
		value : "",
		editable : false,
		method:'get',
		url : basePath+'/deliveryType/getAllList',
		valueField : 'id',
		textField : 'delivery_type_name',
	});
	
	$('#businessType').combobox({
		icons:[{
			iconCls:'icon-combo-clear',
			handler:function(e){
				$(e.data.target).combobox('clear');
			}
		}],
		value : "",
		editable : false,
		method:'get',
		url : basePath+'/businessType/getAllList',
		valueField : 'id',
		textField : 'business_type_name',
	});
	$('#orderInfoState').combobox({
		icons:[{
			iconCls:'icon-combo-clear',
			handler:function(e){
				$(e.data.target).combobox('clear');
			}
		}],
		value : "",
		editable : false,
		method:'get',
		url : basePath+'/orderInfoState/getAllList',
		valueField : 'id',
		textField : 'state_description',
	});
	$('#payState').combobox({
		icons:[{
			iconCls:'icon-combo-clear',
			handler:function(e){
				$(e.data.target).combobox('clear');
			}
		}],
		value : "",
		editable : false,
		method:'get',
		url : basePath+'/payState/getAllList',
		valueField : 'id',
		textField : 'state_description',
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
	
	
	var orderInfoTableSelectFlag = -1;
	
	$('#homeLayout').layout('collapse','east');
	$('#orderInfoTable').datagrid({ 
		
		 onClickRow:function(rowIndex, rowData){
			 
				if(orderInfoTableSelectFlag == rowIndex){
					$(this).datagrid('unselectRow', rowIndex);
					$('#homeLayout').layout('collapse','east');
					orderInfoTableSelectFlag = -1;
				}else{
					orderInfoTableSelectFlag = rowIndex;
					$('#homeLayout').layout('expand','east');
					$('#deliveryManTable').datagrid({ 
						url:basePath+'/user/getDeliveryManListBySecondCategoryId?secondCategoryId='+rowData.secondCategory.id,
						method:'get',
						columns:[[    
						    {field:'full_name',title:'送气工',width:100,align:'center'},
						]]    

					});
				}
		},
			
	 
	});
	
	
	
});


$("#dispatch").click(function() {
	
	var orderInfoRow = $('#orderInfoTable').datagrid('getSelected');
	var deliveryManRow = $('#deliveryManTable').datagrid('getSelected');
	 $.ajax({
			type : 'post',
			url : basePath+'/order/dispatch?orderId='+orderInfoRow.id+'&deliveryManId='+deliveryManRow.id,
			success : function(data) {
				
				if(data.code == 200){
					$('#homeLayout').layout('collapse','east');
					$('#orderInfoTable').datagrid('reload'); 
				}else{
					$.messager.show({
						title : '提示信息',
						msg : data.msg
					});
				}
				
				
			}
		});
});


$("#search").click(function() {
	setorderInfoTable();	
});

$("#cancel").click(function() {
	
	var orderInfoRow = $('#orderInfoTable').datagrid('getSelected');
	
	if (orderInfoRow ==null) {
		$.messager.show({
			title : '提示信息',
			msg : '请先选中一条记录'
		});
		return;
	}

	$.ajax({
		type : 'post',
		url : basePath+'/order/cancelOrder?orderId='+orderInfoRow.id,
		success : function(data) {
			$('#homeLayout').layout('collapse','east');
			$('#orderInfoTable').datagrid('reload'); 
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
  
    //关于jquery的serialize方法转换空格为+号的解决方法  
    formValues = formValues.replace(/\+/g," ");   // g表示对整个字符串中符合条件的都进行替换  
    var temp = JSON.stringify(conveterParamsToJson(formValues));  
    var queryParam = JSON.parse(temp);  
    return queryParam;  
}  

function setorderInfoTable(){	
	
	var queryParams=queryParamByFormId("toolbarForm"); 
	var secondCategoryTableRow = $('#secondCategoryTable').datagrid('getSelected');
	var thirdCategoryTableRow = $('#thirdCategoryTable').datagrid('getSelected');

	var orderInfoTableUrl = basePath+'/order/searchDispatchPageList?';
	
	if(secondCategoryTableRow!=null){
		orderInfoTableUrl+='secondCategory.id='+secondCategoryTableRow.id+'&';
	}
	
	if(thirdCategoryTableRow!=null){
		orderInfoTableUrl+='thirdCategory.id='+thirdCategoryTableRow.id;
	}
	
	$('#orderInfoTable').datagrid({ 
		url: orderInfoTableUrl,
		queryParams:queryParams,
		method:'post',
		columns:[[    
				    {field:'order_address',title:'订单地址',align:'center'},
				    {field:'clientInfo.client_name',title:'用户名称',align:'center',formatter:getClientname },
				    {field:'clientInfo.card_code',title:'用户卡号',align:'center',formatter:getCardCode },
				    {field:'operator.full_name',title:'操作员',align:'center',formatter:getOperator},
				    {field:'state',title:'订单状态',align:'center',formatter:getState},
				    {field:'is_speed_up',align:'center',title:'催气次数'},
				    {field:'payState',title:'付款状态',align:'center',formatter:getPayState,sortable:true},
				    {field:'payType',title:'付款方式',align:'center',formatter:getPayType,sortable:true},
				    {field:'order_number',title:'订单数量',align:'center'},
				    {field:'remark',title:'备注',align:'center'},
				    {field:'order_time',title:'订单时间',align:'center',formatter:getOrderTime},
				    {field:'deliveryType.delivery_type_name',title:'配送方式',align:'center',formatter:getDeliveryType},
				    {field:'businessType.business_type_name',title:'业务类型',align:'center',formatter:getBusinessType},
				    {field:'order_tel_number',title:'订单电话号码',align:'center'},
				    {field:'total_amount',title:'总金额',align:'center'},
				    {field:'feeType',title:'零件',align:'center'},
					{field:'fee_total_amount',title:'零件总金额',align:'center'},
				    {field:'airBottleType.air_bottle_specifications',title:'气瓶类型',align:'center',formatter:getAirBottleType},
				    {field:'delivery_man.full_name',title:'配送员',align:'center',formatter:getDeliveryman},
				    {field:'order_code',title:'订单编号',align:'center'},
				]],
				rowStyler:function(index,row){   
					  if (row.is_speed_up > 0){   
					     return 'background-color:#FF4040;';   
					  }   
				},

	});
}
function getCardCode(value,rec)
{
	if(rec.clientInfo!=null){
		return rec.clientInfo.card_code;
	}
	return null;
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

function getPayType(value,rec)
{
	if(rec.payType!=null){
		return rec.payType.pay_type_name;
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

function getPayState(value,rec)
{
	if(rec.payState!=null){
		return rec.payState.state_description;
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

function getTodayTime(){
	
	   var curr_time = new Date();
	   
	    var year=curr_time.getFullYear(); 
		var M=curr_time.getMonth()+1;  
	    var MM=(M<10)?"0"+M:M;  
		var D= curr_time.getDate();  
	    var DD=(D<10)?"0"+D:D; 
	    
	    return year+"-"+MM+"-"+DD; 
	   
}