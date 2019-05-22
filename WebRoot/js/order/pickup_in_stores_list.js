$(document).ready(
function(){
	
		 $('#orderInfoState').combobox({    
			    value : "",
				editable : false,
				method:'get',
				url : basePath+'/orderInfoState/getAllList',
				valueField : 'id',
				textField : 'state_description',
		    	
		});  
		 
	$('#businessType').combobox({
		value : "",
		editable : false,
		method:'get',
		url : basePath+'/businessType/getAllList',
		valueField : 'id',
		textField : 'business_type_name',
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
	
	
	
});


$("#search").click(function() {
	setorderInfoTable();	
});

$("#cancelOrder").click(function() {
	$('#cancelMsg').val("");
	$("#cancelMsgDialog").dialog("open");
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
		url : basePath+'/order/cancelOrder?orderId='+orderInfoRow.id,
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

	var orderInfoTableUrl = basePath+'/order/searchPickupInStoresList?';
	
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
		    {field:'clientInfo.client_name',title:'用户名称',align:'center',formatter:getClientname },
		    {field:'order_address',title:'订单地址',align:'center'},
		    {field:'state',title:'状态',align:'center',formatter:getState},
		    {field:'order_number',title:'订单数量',align:'center'},
		    {field:'remark',title:'备注',align:'center'},
		    {field:'order_time',title:'订单时间',align:'center',formatter:getOrderTime},
		    {field:'deliveryType.delivery_type_name',title:'配送方式',align:'center',formatter:getDeliveryType},
		    {field:'businessType.business_type_name',title:'业务类型',align:'center',formatter:getBusinessType},
		    {field:'order_tel_number',title:'订单电话号码',align:'center'},
		    {field:'total_amount',title:'总金额',align:'center'},
		    {field:'airBottleType.air_bottle_specifications',title:'气瓶类型',align:'center',formatter:getAirBottleType},
		    {field:'orderAirBottleInfo',title:'重瓶编号',align:'center',formatter:getOrderAirBottleInfo},
		    {field:'orderEmptyBottleInfo',title:'空瓶编号',align:'center',formatter:getOrderEmptyBottleInfo},
		    {field:'feeType',title:'零件',align:'center'},
			{field:'fee_total_amount',title:'零件总金额',align:'center'},
		    {field:'delivery_man.full_name',title:'配送员',align:'center',formatter:getDeliveryman},
		    {field:'operator.full_name',title:'操作员',align:'center',formatter:getOperator},
		    {field:'order_code',title:'订单编号',align:'center'},
		]],
	});
}

$("#fill").click(function() {
	$("#fillDialog").dialog({
		title:"填充"
		});
		$("#fillForm").get(0).reset();
		var selectRow = $("#orderInfoTable").datagrid("getSelected");
		if(selectRow == null){
			$.messager.show({
				title:'提示信息',
				msg:'请选择一行记录'
			});
		}else{
			clearFillTable();
			$("#fillForm").get(0).reset();
			$("#fillForm").form("load",{
				id:selectRow.id,
				order_number:selectRow.order_number
			});
			
			for(var i = 0;i < selectRow.order_number; i++) {
				
				var trHtml = "<tr><td>气瓶编号"+(i+1)+"：</td><td><input class='inputClass' id='input"+(i+1)+"' name='input"+(i+1)+"' onblur='onBlurFillCheck("+(i+1)+")'/></td><td class='tdRemark' id='remark1' style='color:red;'></td></tr>";
				
				addTr("fillTable",i,trHtml);
			}
			
			$("#fillDialog").dialog("open");
			
		};
		
});

function addTr(tab, row, trHtml){
    //获取table最后一行 $("#tab tr:last")
    //获取table第一行 $("#tab tr").eq(0)
    //获取table倒数第二行 $("#tab tr").eq(-2)
    var $tr=$("#"+tab+" tr").eq(row);
    if($tr.size()==0){
       alert("指定的table id或行数不存在！");
       return;
    }
    $tr.after(trHtml);
}

function delTr(tab, row){
	var $tr=$("#"+tab+" tr").eq(row);
    if($tr.size()==0){
       alert("指定的table id或行数不存在！");
       return;
    }
    $tr.remove();
}

function onBlurFillCheck(inputVal){// 鼠标失去焦点判断钢瓶编码是否正确
	
	var bottleCode = $("#input"+inputVal).val();
	
	if(bottleCode=="" || bottleCode==null){
		$("#remark"+inputVal).text("请输入编码！");
		return ;
	}
			
	var selectRow = $("#orderInfoTable").datagrid("getSelected");
	var bottleTypeId = selectRow.airBottleType.id;
	
	$.ajax({
		type : 'post',
		url : basePath+'/store/checkHeavyBottleFromStoreByBottleCodeAndBottleType',
		data : {bottleCode:bottleCode,bottleTypeId:bottleTypeId},
		success : function(data) {
			if(data.code == 200){
				$("#remark"+inputVal).text("");
				$("#remark"+inputVal).text("√");
			}else{
				$("#remark"+inputVal).text(data.msg);
			}
		}
	});
} 

$("#sumbitFill").click(function() {
	
    var bottleCodes = "";
	
	var flag = false;
	
	if(!$("#fillForm").form("validate")){
		return false;
	}
	
	var selectRow = $("#orderInfoTable").datagrid("getSelected");
	
	$.each($(".inputClass"),function(i,value){// 验证每个气瓶编码是否正确
		bottleCodes+=$(value).val()+",";
		onBlurFillCheck($(value).attr("id").substring(5,$(this).attr("id").length));
	});
	
	$.each($(".tdRemark"),function(i,value){
		if($(value).text()!="√"){
			flag = true;
			return false;
		}
	});
	
	if(flag){
		return;
	}
	
	
	$.ajax({
		type : 'post',
		data : {bottleCodes:bottleCodes,orderId:selectRow.id},
		url :  basePath+'/store/fillHeavyBottleByPickUpInSotres',
		success : function(data) {
			$("#fillDialog").dialog("close");
			$("#orderInfoTable").datagrid("reload");
		}
	});
});

$("#closeFillDialog").click(function(){
	$("#fillDialog").dialog("close");
});


function clearFillTable(){
	
	var len = $("#fillTable tr").length;
	
	 //循环删除行,从最后一行往前删除  
    for (i = len - 2; i > 0; i--) {  
   	delTr("fillTable",i);  
   }  
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
	return "";
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

function getOrderEmptyBottleInfo(value, rec) 
{
	if (rec != null) {
		if(rec.orderEmptyBottleInfo.length!=0){
			var str="";
			$.each(rec.orderEmptyBottleInfo,function(i,v){
				str+=v.airBottleInfo.air_bottle_code+",";
			});
		return str.substring(0, str.length-1);
		}
	}
	
	return "";
}

function getOrderAirBottleInfo(value, rec) 
{
	if (rec != null) {
		if(rec.orderAirBottleInfo.length!=0){
			var str="";
			$.each(rec.orderAirBottleInfo,function(i,v){
				str+=v.airBottleInfo.air_bottle_code+",";
			});
		return str.substring(0, str.length-1);
		}
	}
	
	return "";
}