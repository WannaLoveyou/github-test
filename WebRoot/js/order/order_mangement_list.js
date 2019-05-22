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
	$('#payType').combobox({
		icons:[{
			iconCls:'icon-combo-clear',
			handler:function(e){
				$(e.data.target).combobox('clear');
			}
		}],
		value : "",
		editable : false,
		method:'get',
		url : basePath+'/payType/getAllList',
		valueField : 'id',
		textField : 'pay_type_name',
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
	
	$('#orderInfoTable').datagrid({ 
		
		 onClickRow:function(rowIndex, rowData){
			 			 
				if(orderInfoTableSelectFlag == rowIndex){
					$(this).datagrid('unselectRow', rowIndex);
					orderInfoTableSelectFlag = -1;
				}else{
					orderInfoTableSelectFlag = rowIndex;					
				}
		},
			
	 
	});
	
	$('#homeLayout').layout('collapse','east');
	
});


$("#invoice").click(function() {
	
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
			url : basePath+'/order/invoice',
			data :{orderId:orderInfoRow.id},
			success : function(data) {
				if(data.code == 200){
					 $.messager.show({
							title : '提示信息',
							msg : '操作成功'
						});
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

$("#cancelInvoice").click(function() {
	
	 var orderInfoRow = $('#orderInfoTable').datagrid('getSelected');
	 
	 if (orderInfoRow ==null) {
			$.messager.show({
				title : '提示信息',
				msg : '请先选中一条记录'
			});
			return;
	}
	 
	 var remark = prompt("请输入撤销的备注","");
		
	 if(remark == null){
		 return;
	 }
	 
	 $.ajax({
			type : 'post',
			url : basePath+'/order/cancelInvoice',
			data :{orderId:orderInfoRow.id,remark:remark},
			success : function(data) {
				if(data.code == 200){
					 $.messager.show({
							title : '提示信息',
							msg : '操作成功'
						});
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

$("#addRemark").click(function() {
	
	  var orderInfoRow = $('#orderInfoTable').datagrid('getSelected');
		
		if (orderInfoRow ==null) {
			$.messager.show({
				title : '提示信息',
				msg : '请先选中一条记录'
			});
			return;
		}
		
	var remark = prompt("请输入要添加的备注",orderInfoRow.remark);
	
	 if(remark == null){
		 return;
	 }
	
  
	$.ajax({
		type : 'post',
		url : basePath+'/order/addRemark',
		data :{orderId:orderInfoRow.id,remark:remark},
		success : function(data) {
			if(data.code == 200){
				 $.messager.show({
						title : '提示信息',
						msg : '操作成功'
					});
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

$("#addNonQrcodeHeavyBottle").click(function() {
	
	  var orderInfoRow = $('#orderInfoTable').datagrid('getSelected');
		
		if (orderInfoRow ==null) {
			$.messager.show({
				title : '提示信息',
				msg : '请先选中一条记录'
			});
			return;
		}
		
		
		if(orderInfoRow.delivery_type_id == 1 ){
			if(orderInfoRow.delivery_man_id == null){
				$.messager.show({
					title : '提示信息',
					msg : '请先派工'
				});
				return;
			}
		}
		
		
	var bottlecodes = prompt("请输入要录入重瓶的钢瓶码,多个钢瓶码以逗号分隔,没有钢瓶号录入横杠(-)","");
	
	 if(bottlecodes == null){
		 return;
	 }
	

	$.ajax({
		type : 'post',
		url : basePath+'/order/addNonQrcodeHeavyBottle',
		data :{orderId:orderInfoRow.id,bottlecodes:bottlecodes},
		success : function(data) {
			if(data.code == 200){
				 $.messager.show({
						title : '提示信息',
						msg : '操作成功'
					});
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


$("#addNonQrcodeEmptyBottle").click(function() {
	
	  var orderInfoRow = $('#orderInfoTable').datagrid('getSelected');
		
		if (orderInfoRow ==null) {
			$.messager.show({
				title : '提示信息',
				msg : '请先选中一条记录'
			});
			return;
		}
		
		if(orderInfoRow.non_qrcode_heavy_bottle == null){
			$.messager.show({
				title : '提示信息',
				msg : '请先录入重瓶钢瓶号'
			});
			return;
		}
		
	var bottlecodes = prompt("请输入要录入空瓶的钢瓶码,多个钢瓶码以逗号分隔,没有钢瓶号录入横杠(-)","");
	
	 if(bottlecodes == null){
		 return;
	 }
	

	$.ajax({
		type : 'post',
		url : basePath+'/order/addNonQrcodeEmptyBottle',
		data :{orderId:orderInfoRow.id,bottlecodes:bottlecodes},
		success : function(data) {
			if(data.code == 200){
				 $.messager.show({
						title : '提示信息',
						msg : '操作成功'
					});
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



$("#cancelPickupInWH").click(function() {
	
    var orderInfoRow = $('#orderInfoTable').datagrid('getSelected');
	
	if (orderInfoRow ==null) {
		$.messager.show({
			title : '提示信息',
			msg : '请先选中一条记录'
		});
		return;
	}
	 
	 if(!confirm("请注意,换瓶单撤销后订单对应的二维码重瓶将返回订单的受理仓库，二维码空瓶将返回对应的客户，充装单撤销后二维码库存将保持在客户所属库存不变，订单将变成未派工状态,是否继续操作?"))
	 {
		return;
	 }

	$.ajax({
		type : 'post',
		url : basePath+'/order/cancelPickupInWH?orderId='+orderInfoRow.id,
		success : function(data) {
			if(data.code == 200){
				 $.messager.show({
						title : '提示信息',
						msg : '操作成功'
					});
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

$("#cancelDispatch").click(function() {
	
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
		url : basePath+'/order/cancelDeliveryOrder?orderId='+orderInfoRow.id,
		success : function(data) {
			if(data.code == 200){
				 $.messager.show({
						title : '提示信息',
						msg : '操作成功'
					});
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


$("#cancelOrder").click(function() {
	
    var orderInfoRow = $('#orderInfoTable').datagrid('getSelected');
	
	if (orderInfoRow ==null) {
		$.messager.show({
			title : '提示信息',
			msg : '请先选中一条记录'
		});
		return;
	}
	
	$('#cancelMsg').val("");
	$("#cancelMsgDialog").dialog("open");
});



$("#forceCacnel").click(function() {
	
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
		url : basePath+'/order/forceCacnel?orderId='+orderInfoRow.id,
		success : function(data) {
			if(data.code == 200){
				 $.messager.show({
						title : '提示信息',
						msg : '操作成功'
					});
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

$("#sumbitCancelMsgDialog").click(function() {
	
	var orderInfoRow = $('#orderInfoTable').datagrid('getSelected');
	
	if (orderInfoRow ==null) {
		$.messager.show({
			title : '提示信息',
			msg : '请先选中一条记录'
		});
		return;
	}
	
   var cancelMsg = $('#cancelMsg').val();
	   
   if(cancelMsg == ""){
	   $.messager.show({
			title : '提示信息',
			msg : '请输入撤销备注'
		});
	   return;
   }

	$.ajax({
		type : 'post',
		url : basePath+'/order/cancelOrder?',
		data : {orderId:orderInfoRow.id,cancelMsg:cancelMsg},
		success : function(data) {
			if(data.code == 200){
				 $.messager.show({
						title : '提示信息',
						msg : '操作成功'
					});
				$("#cancelMsgDialog").dialog("close");
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

$("#closeCancelMsgDialog").click(function() {
	$("#cancelMsgDialog").dialog("close");
});


$("#editOrderStore").click(function() {
	
    var orderInfoRow = $('#orderInfoTable').datagrid('getSelected');
	
	if (orderInfoRow ==null) {
		$.messager.show({
			title : '提示信息',
			msg : '请先选中一条记录'
		});
		return;
	}
	
	$('#editOrderStoreSecondCategoryToolId').combobox({
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
	
	$("#editOrderStoreDialog").dialog("open");
});



$("#sumbitEditOrderStoreDialog").click(function() {
	
	var orderInfoRow = $('#orderInfoTable').datagrid('getSelected');
	
	if (orderInfoRow ==null) {
		$.messager.show({
			title : '提示信息',
			msg : '请先选中一条记录'
		});
		return;
	}
	
	
	if(!$('#editOrderStoreForm').form('validate')){
		$.messager.show({
			title : '提示信息',
			msg : '请选择新的门店'
		});
		return;
	}
	
	var sotreId = $('#editOrderStoreSecondCategoryToolId').combobox('getValue');
	
	$.ajax({
		type : 'post',
		url : basePath+'/order/editOrderStore?',
		data : {orderId:orderInfoRow.id,sotreId:sotreId},
		success : function(data) {
			if(data.code == 200){
				
				$("#editOrderStoreDialog").dialog("close");
			    $('#orderInfoTable').datagrid('reload'); 
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


$("#closeEditOrderStoreDialog").click(function() {
	$("#editOrderStoreDialog").dialog("close");
});




$("#returnQRcode").click(function() {
	
    var orderInfoRow = $('#orderInfoTable').datagrid('getSelected');
	
	if (orderInfoRow ==null) {
		$.messager.show({
			title : '提示信息',
			msg : '请先选中一条记录'
		});
		return;
	}
	
	if(!confirm("撤销后订单对应的二维码重瓶将返回订单的受理门店，二维码空瓶将返回对应的客户，订单将变成未派工状态,是否继续操作?"))
    {
		return;
    }

	$.ajax({
		type : 'post',
		url : basePath+'/order/returnQRcode?orderId='+orderInfoRow.id,
		success : function(data) {
			if(data.code == 200){
				 $.messager.show({
						title : '提示信息',
						msg : '操作成功'
					});
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

$("#exportExcel").click(function() {
	outToFile();
});

function outToFile() {

	
	var secondCategoryTableRow = $('#secondCategoryTable').datagrid('getSelected');
	var thirdCategoryTableRow = $('#thirdCategoryTable').datagrid('getSelected');
	
	var url = basePath+ '/order/exportOrderInfo?';
		
	if(secondCategoryTableRow!=null){
		url+='secondCategory.id='+secondCategoryTableRow.id+'&';
	}
		
	if(thirdCategoryTableRow!=null){
		url+='thirdCategory.id='+thirdCategoryTableRow.id;
	}
	
	document.getElementById("toolbarForm").action = url;

	document.getElementById("toolbarForm").submit();
};

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

function setorderInfoTable(){	
	
	var queryParams=queryParamByFormId("toolbarForm"); 
	var secondCategoryTableRow = $('#secondCategoryTable').datagrid('getSelected');
	var thirdCategoryTableRow = $('#thirdCategoryTable').datagrid('getSelected');

	var orderInfoTableUrl = basePath+'/order/searchOrderInfoList?';
	
	if(secondCategoryTableRow!=null){
		orderInfoTableUrl+='secondCategory.id='+secondCategoryTableRow.id+'&';
	}
	
	if(thirdCategoryTableRow!=null){
		orderInfoTableUrl+='thirdCategory.id='+thirdCategoryTableRow.id;
	}
	
	var orderInfoStateTotalMap = {};
	
	$('#orderInfoTable').datagrid({ 
		url: orderInfoTableUrl,
		queryParams:queryParams,
		method:'post',
		remoteSort:true,
		columns:[[    
			{field:'price',title:'单价',align:'center',sortable:true},
			{field:'delivery_fee',title:'送气费',align:'center',sortable:true},
			{field:'floor_subsidies_money',title:'楼层费',align:'center',sortable:true},
		    {field:'order_address',title:'订单地址',align:'center',sortable:true},
		    {field:'clientInfo.card_code',title:'用户卡号',align:'center',formatter:getCardCode,sortable:true },
		    {field:'clientInfo.client_name',title:'用户名称',align:'center',formatter:getClientname,sortable:true },
		    {field:'clientInfo.client_code',title:'用户编码',align:'center',formatter:getClientCode,sortable:true },
		    {field:'clientInfo.tel_number',title:'用户电话号码',align:'center',formatter:getClientTelNumber},
		    {field:'operator.full_name',title:'操作员',align:'center',formatter:getOperator,sortable:true},
		    {field:'state',title:'订单状态',align:'center',formatter:getState,sortable:true},
		    {field:'is_speed_up',title:'催气次数',align:'center',sortable:true},
		    {field:'payState',title:'付款状态',align:'center',formatter:getPayState,sortable:true},
		    {field:'payType',title:'付款方式',align:'center',formatter:getPayType,sortable:true},
		    {field:'airBottleType.air_bottle_specifications',title:'气瓶类型',align:'center',formatter:getAirBottleType,sortable:true},
		    {field:'order_number',title:'订单数量',align:'center',sortable:true},
		    {field:'remark',title:'备注',align:'center',sortable:true},
		    {field:'order_tel_number',title:'订单电话号码',align:'center',sortable:true},
		    {field:'order_time',title:'订单时间',align:'center',formatter:getOrderTime,sortable:true},
		    {field:'report_time',title:'统计时间',align:'center',formatter:getReportTime,sortable:true},
		    {field:'order_appointment_time1',title:'预约时间1',align:'center',formatter:getAppointmentTime1,sortable:true},
		    {field:'order_appointment_time2',title:'预约时间2',align:'center',formatter:getAppointmentTime2,sortable:true},
		    {field:'deliveryType.delivery_type_name',title:'配送方式',align:'center',formatter:getDeliveryType,sortable:true},
		    {field:'businessType.business_type_name',title:'业务类型',align:'center',formatter:getBusinessType,sortable:true},
		    {field:'total_amount',title:'总金额',align:'center',sortable:true},
		    {field:'feeType',title:'零件',align:'center',sortable:true},
			{field:'fee_total_amount',title:'零件总金额',align:'center',sortable:true},
		    {field:'orderAirBottleInfo',title:'重瓶编号',align:'center',formatter:getOrderAirBottleInfo},
		    {field:'orderEmptyBottleInfo',title:'空瓶编号',align:'center',formatter:getOrderEmptyBottleInfo},
		    {field:'non_qrcode_heavy_bottle',title:'录入重瓶钢瓶号',align:'center'},
		    {field:'non_qrcode_empty_bottle',title:'录入空瓶钢瓶号',align:'center'},
		    {field:'delivery_man.full_name',title:'配送员',align:'center',formatter:getDeliveryman,sortable:true},
		    {field:'delivery_time',title:'派工时间',align:'center',formatter:getDeliveryTime,sortable:true},
		    {field:'secondCategory.second_category_name',title:'受理门店',align:'center',formatter:getSecondCategoryName,sortable:true},
		    {field:'invoice_operator.full_name',title:'开票人',align:'center',formatter:getInvoiceOperator,sortable:true},
		    {field:'invoice_time',title:'开票时间',align:'center',formatter:getInvoiceTime,sortable:true},
		    {field:'cancel_invoice_operator.full_name',title:'撤销开票人',align:'center',formatter:getCancelInvoiceOperator,sortable:true},
		    {field:'cancel_invoice_time',title:'撤销开票时间',align:'center',formatter:getCancelInvoiceTime,sortable:true},
		    {field:'cancel_invoice_remark',title:'撤销开票备注',align:'center'},
		    {field:'invoice_num',title:'开票次数',align:'center',sortable:true},
		    {field:'order_code',title:'订单编号',align:'center',sortable:true},
		    {field:'id',title:'系统编号',align:'center',sortable:true},
		]],
		fitColumns:true,
		rowStyler:function(index,row){

			 if(row.fee_total_amount > 0){
				  return 'color:#1E90FF;';
			  }
			 
			  if (row.is_speed_up > 0){   
			     return 'color:#CD0000;';   
			  } 
			 
		},
		onLoadSuccess:function(data){
			
			var strOrder = "";
			
			for (var key in data.orderInfoStateTotalMap) { 
				strOrder+=key+ data.orderInfoStateTotalMap[key] + "条       "; 
		    }
			
			var strBottle = "共" + data.orderBottleNum + "瓶  ";
			
			 var p = $('#orderInfoTable').datagrid('getPager'); 
			    $(p).pagination({ 
			        displayMsg: '(红色表示催气)  当前显示 {from} - {to} 条记录   共 {total} 条记录  ' + strBottle+ strOrder, 
			 });  
		}
	});
	
	
}

$("#finishPartsOrder").click(function() {
	
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
			url : basePath+'/order/finishPartsOrder?',
			data : {orderId:orderInfoRow.id},
			success : function(data) {
				if(data.code == 200){
					 $('#orderInfoTable').datagrid('reload'); 
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


var customPrintFlag = false;

$("#printOrderBtn").click(function() {
	
	customPrintFlag = false;
	initPrint();
	//testPrint();
});


$("#customPrintBtn").click(function() {
	
	$("#customPrintDialog").dialog("open");
});

$("#sumbitCustomPrintDialog").click(function() {
	
	customPrintFlag = true;
	initPrint();
	$("#customPrintDialog").dialog("close");
});

$("#closeCustomPrintDialog").click(function() {
	
	$("#customPrintDialog").dialog("close");
});


$("#dispatchBtn").click(function() {
	
	var orderInfoRow = $('#orderInfoTable').datagrid('getSelected');
	if(orderInfoRow == null){
		$.messager.show({
			title : '提示信息',
			msg : '请选择一条记录'
		});
		return;
	}
	
	$.ajax({
		type : 'post',
		url : basePath+'/order/checkDispatch?orderId='+orderInfoRow.id,
		success : function(data) {
			if(data.code == 200){
				$('#deliveryManTable').datagrid({ 
					url:basePath+'/user/getDeliveryManListBySecondCategoryId?secondCategoryId='+orderInfoRow.secondCategory_id,
					method:'get',
					columns:[[    
					    {field:'full_name',title:'送气工',width:100,align:'center'},
					]]    

				});
				$('#homeLayout').layout('expand','east');
			}else{
				$.messager.show({
					title : '提示信息',
					msg : data.msg
				});
			}
			
		}
	});
	
});



$("#dispatchBtn").click(function() {
	
	var url = basePath+'/user/getDeliveryManListBySecondCategoryId?';
	
	initDispatch(url);
	
});


$("#dispatchNoLimitBtn").click(function() {
	
	var url = basePath+'/user/getAllDeliveryManList?';
	
	initDispatch(url);
	
});

function initDispatch(url){
	
	var orderInfoRow = $('#orderInfoTable').datagrid('getSelected');
	if(orderInfoRow == null){
		$.messager.show({
			title : '提示信息',
			msg : '请选择一条记录'
		});
		return;
	}
	
	$.ajax({
		type : 'post',
		url : basePath+'/order/checkDispatch?orderId='+orderInfoRow.id,
		success : function(data) {
			if(data.code == 200){
				$('#deliveryManTable').datagrid({ 
					url:url + 'secondCategoryId='+orderInfoRow.secondCategory_id,
					method:'get',
					columns:[[    
					    {field:'full_name',title:'送气工',width:100,align:'center'},
					]]    

				});
				$('#homeLayout').layout('expand','east');
			}else{
				$.messager.show({
					title : '提示信息',
					msg : data.msg
				});
			}
			
		}
	});
}


$("#dispatchCancelBtn").click(function() {
	
	$('#homeLayout').layout('collapse','east');
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

function initPrint(){
	
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
		url : basePath+'/order/getPirntTimes?',
		data : {orderId:orderInfoRow.id},
		success : function(data) {
			
			if(data.code != 200){
				$.messager.show({
					title : '提示信息',
					msg : data.msg
				});
			}else{
				initOrderDialog(orderInfoRow);
			}
		
		}
	});
}

$("#printCanceBtn").click(function() {
	
    var orderInfoRow = $('#orderInfoTable').datagrid('getSelected');
	
	if (orderInfoRow ==null) {
		$.messager.show({
			title : '提示信息',
			msg : '请先选中一条记录'
		});
		return;
	}

	
	var printNo = prompt("请输入单据编号","");
	
	 if(printNo == null){
		 return;
	 }
	 
	 if(printNo == ""){
		 $.messager.show({
				title : '提示信息',
				msg : '单据编号不能为空'
		 });
		 return;
	 }
	
	
	$.ajax({
		type : 'post',
		url : basePath+'/order/printCancel?',
		data : {orderId:orderInfoRow.id,printNo:printNo},
		success : function(data) {
			if(data.code == 200){
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

var LODOP;
function table(orderId){
	
	 LODOP=getLodop();
		 
	 if ((LODOP==null)||(typeof(LODOP.VERSION)=="undefined")) {
    	 if(window.confirm('打印需要控件，请点击确认下载，下载安装后需要重新登录系统')){
    		 window.location.href='http://106.75.137.43:8078/files/lodop/CLodop_Setup_for_Win32NT_2.068.exe';
          }else{
         }
    	 return false;
     }
	
	LODOP.PRINT_INIT(PRINT_INIT_NAME);// 首先一个初始化语句
	// SET_PRINT_PAGESIZE(intOrient,intPageWidth,intPageHeight,strPageName)设定纸张大小
	LODOP.SET_PRINT_PAGESIZE(1,intPageWidth,intPageHeight,"");
	// ADD_PRINT_TABLE(intTop,intLeft,intWidth,intHeight,strHtml)增加表格项
	LODOP.ADD_PRINT_TABLE(intTop,intLeft,intWidth,intHeight,document.getElementById("print_div").innerHTML);// 然后多个ADD语句及SET语句
	LODOP.PREVIEW(); // 打印预览
	// LODOP.PRINT();
	
	$.ajax({
		type : 'post',
		url : basePath+'/order/addPrintTime?',
		data : {orderId:orderId},
		success : function(data) {
		}
	});
	
}

function getSecondCategoryName(value,rec)
{
	return rec.second_category_name;
}

function getClientCode(value,rec)
{
	return rec.client_code;
}

function getClientname(value,rec)
{
	return rec.client_name;
}

function getCardCode(value,rec)
{
	return rec.card_code;
}


function getAirBottleType(value,rec)
{
	return rec.air_bottle_specifications;
}

function getBusinessType(value,rec)
{
	return rec.business_type_name;
}


function getDeliveryType(value,rec)
{
	return rec.delivery_type_name;
}

function getDeliveryman(value,rec)
{
	return rec.delivery_man_full_name;
}
function getFeeType(value,rec)
{
	if(rec.feeType!=null){
		return rec.feeType;
	}
	return "";
}

function getFeeTotalPrice(value,rec)
{
	if(rec.fee_total_amount!=null){
		return rec.fee_total_amount;
	}
	return "";
}

function getOperator(value,rec)
{
	return rec.operator_full_name;
}

function getInvoiceOperator(value,rec)
{
	return rec.invoice_operator_full_name;
}

function getInvoiceTime(value,rec)
{
	if (rec.invoice_time != null) {
		return formatDate(new Date(rec.invoice_time)) ;
	}
	return null;
}

function getCancelInvoiceOperator(value,rec)
{
	return rec.cancel_invoice_operator_full_name;
}

function getCancelInvoiceTime(value,rec)
{
	if (rec.cancel_invoice_time != null) {
		return formatDate(new Date(rec.cancel_invoice_time)) ;
	}
	return null;
}

function getDeliveryTime(value,rec)
{
	if (rec.delivery_time != null) {
		return formatDate(new Date(rec.delivery_time)) ;
	}
	return null;
}

function getState(value,rec)
{
	return rec.state_description;
}

function getPayState(value,rec)
{
	return rec.pay_state_description;
}

function getPayType(value,rec)
{
	return rec.pay_type_name;
}

function getReportTime(value, rec) 
{
	if (rec.report_time != null) {
		return new Date(rec.report_time).toLocaleDateString() ;
	}
	return null;
}


function getOrderTime(value, rec) 
{
	if (rec.order_time != null) {
		return new Date(rec.order_time).toLocaleString() ;
	}
	return null;
}

function getClientTelNumber(value, rec) 
{

	var mobileArray= new Array();
	
	mobileArray.push(rec.mobile_tel_number_1);
	mobileArray.push(rec.mobile_tel_number_2);
	mobileArray.push(rec.fixed_tel_number_1);
	mobileArray.push(rec.fixed_tel_number_2);
		
	for(var i=0;i<mobileArray.length;i++){
			
		if(mobileArray[i]!=""&&mobileArray[i]!="NULL"){
			return  mobileArray[i];
		}
	}
		
	return null;
}


function getAppointmentTime1(value, rec) 
{
	if (rec.order_appointment_time1 != null) {
		return new Date(rec.order_appointment_time1).toLocaleString() ;
	}
	return null;
}

function getAppointmentTime2(value, rec) 
{
	if (rec.order_appointment_time2 != null) {
		return new Date(rec.order_appointment_time2).toLocaleString() ;
	}
	return null;
}

function getOrderEmptyBottleInfo(value, rec) 
{
	return rec.orderEmptyBottleInfos;
}

function getOrderAirBottleInfo(value, rec) 
{
	return rec.orderAirBottleInfos;
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