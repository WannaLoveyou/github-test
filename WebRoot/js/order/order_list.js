var airBottleTypeRec;
var clientId;
var callsRecordPermission = false;

var i = 1;

$(function () {  
	 
	/* document.onkeydown=function(event){
         var e = event || window.event || arguments.callee.caller.arguments[0];
          if(e && e.keyCode==13){ // enter 键
        	  searchClientInfo();
         }
     }; */
	
	$('#clientType').combobox({
		value : "请选择",
		editable : false,
		method:'get',
		url : basePath+'/clientType/getAllList',
		valueField : 'id',
		textField : 'client_type_name'
	});
	
	$('#deliveryType').combobox({// 配送方式
	    url:basePath+'/deliveryType/getAllList', 
	    method:'get',
	    valueField:'id',    
	    textField:'delivery_type_name',
	    onSelect:function(rec){
	    	initPrice();
	    	calTotalAmount();
	    },
	    onLoadSuccess: function () { // 加载完成后,设置选中第一项
            var val = $(this).combobox("getData");
            $(this).combobox("select", val[0].id);
	    }
	});
	
	$("#tt-tabs").tabs({
		border:false,
		onSelect:function(title){
			
			if(clientId == null){
				return;
			}
			
			
			if(title=="业务受理"){
				setOrderInfo(clientId);
			}	
			if(title=="维修受理"){
				setRepairInfo(clientId);
			}
			if(title=="投诉受理"){
				setComplaintInfo(clientId);
			}
			if(title=="客户回访"){
				setVisitInfo(clientId);
			}
			if(title=="撤单记录"){
				setOrderCancelInfo(clientId);
			}
			if(title=="客户气瓶信息"){
				setClientBottleRecordInfo(clientId);
			}

	}
	});
	
	
        $('#Pagination').pagination({  
            pageSize: 1,  
            pageNumber: 1,  
            onSelectPage: function (pageNumber, pageSize) {  
                $(this).pagination('loading');  
                $(this).pagination('loaded');  
                filp(pageNumber);   
            }  
        });
   
        
        
    // 检测来电记录权限
    checkCallsRecordPermission();
    
    // 就绪语音系统
    readyTelSystem();
    
});


function checkCallsRecordPermission(){
	$.ajax({
		type : 'post',
		url : basePath+'/phoneRecord/checkCallsRecordPermission',
		success : function(data) {
			if(data.data == true){
				$('#homeLayout').layout('collapse','east');
				callsRecordPermission = true;
				refeshPhoneRecord();
			}
		}
    });
}

function readyTelSystem() {
	
	$.ajax({
		type : 'get',
		url : 'http://127.0.0.1:4454/ACD?Action=ready&encode=json',
		success : function(data) {
			window.setInterval("getEventTelSystem()",2000); 
		}
	});
}

function getEventTelSystem() {
		
	$.ajax({
		type : 'get',
		url : 'http://127.0.0.1:4454/System?Action=GetEvent&encode=json',
		success : function(data) {
			var obj = jQuery.parseJSON(data);
			if(obj.event == 1){
				addReceivedCallsRecord(obj.content.author);
				$.messager.confirm('来电提醒', '电话' +obj.content.author+ '确认接听吗', function(r){
			    	if (r){
			    		 	clearTable();
						    $("#mobile").val(obj.content.author);
						    searchClientInfo();
			    	}
			    });

			}
		}
	});
}

function addReceivedCallsRecord(phoneNumber){
		
	if(!callsRecordPermission){
		return;
	}
	
	$.ajax({
		type : 'post',
		data : {phoneNumber:phoneNumber},
		url : basePath+'/phoneRecord/addReceivedCallsRecord',
		success : function(data) {
			
		}
    });
}

$("#refeshPhoneRecord").click(function(){
	refeshPhoneRecord();
});

function refeshPhoneRecord(){
	$('#phoneRecordTable').datagrid({ 
		url:basePath+'/phoneRecord/getPageList?begin_call_in_time='+NowDateTime()+'&end_call_in_time='+NowDateTime()+'&phoneRecordState=3&page=1&rows=1000',
		method:'get',
		columns:[[    
		    {field:'phoneNumber',title:'号码',align:'center'},
		    {field:'callInTime',title:'来电时间',align:'center',formatter:getCallInTime},
		    {field:'fullName',title:'接听人',align:'center',formatter:getUserFullName},
		    /*{field:'userCode',title:'接听人编号',align:'center',formatter:getUserCode},*/
		]],
		loadFilter: function(data){

            if (data.data!=null){
                return data.data;
            }
            return null;
        }

	});
}

function getCallInTime(value,rec) {
	
	if (rec.callInTime != null) {
		return formatDate(new Date(rec.callInTime));
	}
	return null;
}
function getUserFullName(value,rec) {
	if(rec.answerOperator==null) return;
	return rec.answerOperator.full_name;
}
function getUserCode(value,rec) {
	if(rec.answerOperator==null) return;
	return rec.answerOperator.user_code;
}

$("#showByphoneRecord").click(function(){
	
	 var selectedRow = $('#phoneRecordTable').datagrid('getSelected');
	 
	 clearTable();
	 $("#mobile").val(selectedRow.phoneNumber);
	 searchClientInfo();

	$('#homeLayout').layout('collapse','east');

});

$(function(){
	$.extend($.fn.datagrid.methods, {
		editCell: function(jq,param){
			return jq.each(function(){
				var opts = $(this).datagrid('options');
				var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
				for(var i=0; i<fields.length; i++){
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor1 = col.editor;
					if (fields[i] != param.field){
						col.editor = null;
					}
				}
				$(this).datagrid('beginEdit', param.index);
				for(var i=0; i<fields.length; i++){
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor = col.editor1;
				}
			});
		}
	});
	
});

var editIndex = undefined;
function endEditing(){
	if (editIndex == undefined){return true;}
	if ($('#dg').datagrid('validateRow', editIndex)){
		$('#dg').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

function accept(){
	if (endEditing()){
		$('#dg').datagrid('acceptChanges');
	}
}
function onClickCell(index, field){
	if (endEditing()){
		$('#dg').datagrid('selectRow', index)
				.datagrid('editCell', {index:index,field:field});
		editIndex = index;
	}
}

$('#prompt1').click(function(){
	
	var tempValue = $('#airBottleType').combobox('getValue');
		
	if(tempValue == null || tempValue == ""){
		$.messager.show({
			title:'提示信息',
			msg:'未选择气瓶类型'
		});
		return;
	}
		
	$('#w').window('open');
	
});

function initdgTable(){
		
	$('#dg').datagrid({   
		pagination:true,
		pageSize:20,
		pageList:[5,10,15,20],
		iconCls: 'icon-edit',
		rownumbers:true,
		fitColumns:true,
		striped:true,
		nowrap:false,
		method:'post',
		queryParams:{bottleTypeId:$('#airBottleType').combobox('getValue')},
		onClickCell: onClickCell,
		url: basePath+'/partPrice/getListByBottleType',
	    columns:[[ 
	        {field:'ck',checkbox:true},    
	     	{field:'partType',title:'零件类型',width:70,formatter:function(value,row,index){
	     		if(row.partType!=null){
	     			return row.partType.part_type_name;
	     		}
			}},
	        {field:'number',title:'数量/个',width:70,formatter:function(value,row,index){
				return value==undefined?0:value;
			},editor:'numberbox'
			},   
	        {field:'price',title:'单价/元',width:70},
	        {field:'totalprice',title:'金额/元',width:70,formatter:getTotalprice},
	        {field:'note',title:'备注',width:150,formatter: function(value,row,index){
				return value==undefined?"":value;
			},editor:'text'}
	    ]] 
	});
}

function getTotalprice(value,row){
	if(row.number==undefined){
		return 0;
	}
	if(row.price==undefined){
		return 0;
	}
	return row.number * row.price;
}

var floorSubsidiesIdTemp;
$("#saveClientInfo").click(function(){
	
	if(clientId == null){
		return;
	}
	
	var clientInfo={};
	
	var client_code = $("#client_code").val();
	var card_code = $("#card_code").val();
	var client_name = $("#client_name").val();
	var fixed_tel_number_1 = $("#fixed_tel_number_1").val();
	var fixed_tel_number_2 = $("#fixed_tel_number_2").val();
	var mobile_tel_number_1 = $("#mobile_tel_number_1").val();
	var mobile_tel_number_2 = $("#mobile_tel_number_2").val();
	var client_address = $("#client_address").val();
	var remark = $("#remark").val();
	var temp_tips = $("#temp_tips").val();
	
	var floorSubsidiesId = floorSubsidiesIdTemp;
	var clientTypeId = $('#clientType').combobox('getValue');
	var secondCategoryId = $('#secondCategory').combobox('getValue');

	
	clientInfo.client_code = client_code;
	clientInfo.card_code = card_code;
	clientInfo.client_name = client_name;
	clientInfo.fixed_tel_number_1 = fixed_tel_number_1;
	clientInfo.fixed_tel_number_2 = fixed_tel_number_2;
	clientInfo.mobile_tel_number_1 = mobile_tel_number_1;
	clientInfo.mobile_tel_number_2 = mobile_tel_number_2;
	clientInfo.client_address = client_address;
	clientInfo.remark = remark;
	clientInfo.temp_tips = temp_tips;
	
	var floorSubsidies = {};
	floorSubsidies.id = floorSubsidiesId;
	clientInfo.floorSubsidies = floorSubsidies;
	
	var clientType = {};
	clientType.id = clientTypeId;
	clientInfo.clientType = clientType;
	
	var secondCategory = {};
	secondCategory.id = secondCategoryId;
	clientInfo.secondCategory = secondCategory;
	
	$.ajax({
		type : 'post',
		data : {client_id:clientId,clientInfoStr:JSON.stringify(clientInfo)},
		url : basePath+'/clientInfo/editByOrderView',
		success : function(data) {
			
			if(data.code != 200){
				$.messager.show({
					title : '提示信息',
					msg : data.msg
				});
			}else{
				$.messager.show({
					title : '提示信息',
					msg : '操作成功'
				});
			}
			
		}
	 });

});

$("#resetPassword").click(function(){
	
	  var mobile = $("#mobile").val();
	  
	  if(clientId == null){
		  $.messager.show({
				title:'提示信息',
				msg:'请先查询对应信息'
		});
		return;
	  }
	  if(mobile == null || mobile== ""){
		  $.messager.show({
				title:'提示信息',
				msg:'请填写手机号码'
			});
		  return;
	  }
	  
	  $.ajax({
			type : 'post',
			data : {mobileTelNumber:mobile,clientId:clientId},
			url : basePath+'/clientInfo/resetPasswordByClientIdForSMS',
			success : function(data) {
				
				if(data.code == 200){
					
					 $.ajax({
							type : 'post',
							data : {},
							url : basePath+'/clientInfo/getSMSNum',
							success : function(data) {
								
								var tisMsg = '操作成功。';
								
								if(data.data < 10000){
									tisMsg += '请注意，当前短信条数不足，余下条数为' + data.data;
								}
								
								$.messager.show({
									title:'提示信息',
									msg: tisMsg
								});
								
							}
					});
					

				}else{
					$.messager.show({
						title:'提示信息',
						msg:data.msg
					});
				}
				
				
			}
		});
});

$("#getSMSNum").click(function(){
	  $.ajax({
			type : 'post',
			data : {},
			url : basePath+'/clientInfo/getSMSNum',
			success : function(data) {
				
				$.messager.show({
					title:'提示信息',
					msg:'剩余短信条数为：' + data.data
				});
			}
		});
});




$("#editOrderTime").click(function(){
	
	$('#dealDate').datebox('enable'); 
});

$("#editOrderMoney").click(function(){
	
	 $("#allPrice").removeAttr("readonly");
	
});

$("#searchM1CardCode").click(function(){
	
	$("#clientInfoForm").form("load",{
		'card_code':document.myApplet.tyBy7(),
	});
	
	searchClientInfo();
	
	return true;

});

$("#searchM1CardCodeNew").click(function(){
	
	$("#clientInfoForm").form("load",{
		'card_code':getCardContentByMiddleware(7,0),
	});
	
	searchClientInfo();
	
	return true;

});


$("#feebtn1").click(function(){
	endEditing();
	var selectedRows = $("#dg").datagrid("getSelections");
	var totalPrice=0;

	var partTypes="";
	
	var partsStr = new Array();
	
	for(var i=0;i<selectedRows.length;i++){
		
		var partFeeInfo={};
		var partType={};
		
		partType.id = selectedRows[i].partType.id;
		partFeeInfo.partType = partType;
		partFeeInfo.number = selectedRows[i].number;
		partFeeInfo.price = selectedRows[i].price;
		partFeeInfo.totalprice = partFeeInfo.number * partFeeInfo.price;
		partFeeInfo.note = selectedRows[i].note==undefined?"":selectedRows[i].note;
		
		totalPrice += partFeeInfo.totalprice;
		
		partTypes+=selectedRows[i].partType.part_type_name+"*"+selectedRows[i].number+"个;";
		
		partsStr.push(partFeeInfo);
		
	}
	
	$("#myForm").form("load",{
		partsStr:JSON.stringify(partsStr),
		fee_total_amount:totalPrice,
		feeType:partTypes,
	});
	 $('#w').window('close', true);
	 calTotalAmount();
	 
});

$("#feebtn2").click(function(){
	initdgTable();
	 calTotalAmount();
});


function getPartType(value,rec){
	if(rec.partType!=null){
		return rec.partType.part_type_name;
	}
}

$("#familyCheckBtn1").click(function(){// 入户检查
	if(!$("#familyCheckForm").form('validate')){
		return false;
	}
	
	 $.ajax({
			type : 'post',
			data : $('#familyCheckForm').serialize(),
			url : basePath+'/familyCheck/addFamilyCheckOrder',
			success : function(data) {
				$.messager.show({
					title:'提示信息',
					msg:'操作成功'
				});
				$('#tb2').datagrid('reload'); 
			}
		});
});

$("#repairBtn1").click(function(){// 维修受理
		if(!$("#repairmyForm").form('validate')){
			return false;
		}
		
		 $.ajax({
				type : 'post',
				data : $('#repairmyForm').serialize(),
				url : basePath+'/repairInfo/add',
				success : function(data) {
					$.messager.show({
						title:'提示信息',
						msg:'操作成功'
					});
					$('#tb2').datagrid('reload'); 
				}
			});
	});
	
	$("#complaintBtn1").click(function(){// 投诉受理
		if(!$("#complaintmyForm").form('validate')){
			return false;
		}
		
		 $.ajax({
				type : 'post',
				data : $('#complaintmyForm').serialize(),
				url : basePath+'/complaint/add',
				success : function(data) {
					$.messager.show({
						title:'提示信息',
						msg:'操作成功'
					});
					$('#tb2').datagrid('reload'); 
				}
			});
	});
	
	$("#visitBtn1").click(function(){// 投诉受理
		if(!$("#visitmyForm").form('validate')){
			return false;
		}
		
		$.ajax({
			type : 'post',
			data : $('#visitmyForm').serialize(),
			url : basePath+'/visit/add',
			success : function(data) {
				$.messager.show({
					title:'提示信息',
					msg:'操作成功'
				});
				$('#tb2').datagrid('reload'); 
			}
		});
	});
	
	
	
	$("#cardMoneyRechargeBtn1").click(function(){// 预存款
		if(!$("#cardMoneyRechargeForm").form('validate')){
			return false;
		}
		
		 $.ajax({
				type : 'post',
				data : $('#cardMoneyRechargeForm').serialize(),
				url : basePath+'/cardMoneyRechargeRecord/recharge',
				success : function(data) {
					
					clearCardMoneyRechargeForm(); 
					$("#card_money").val(data.data);
					
					$.messager.show({
						title:'提示信息',
						msg:'操作成功'
					});
				}
			});
	});
	
	
	$("#cardMoneyPaymentBtn1").click(function(){// 预存款
		if(!$("#cardMoneyPaymentForm").form('validate')){
			return false;
		}
		
		 $.ajax({
				type : 'post',
				data : $('#cardMoneyPaymentForm').serialize(),
				url : basePath+'/cardMoneyPaymentRecord/payment',
				success : function(data) {
					
					if(data.code!=200){
						$.messager.show({
							title:'提示信息',
							msg:data.msg
						});
						return;
					}
					
					clearCardMoneyPaymentForm(); 
					$("#card_money").val(data.data);
					
					$.messager.show({
						title:'提示信息',
						msg:'操作成功'
					});
					
					
				}
			});
	});
	
	$("#cardMoneyRechargeBtn2").click(function(){// 预存款充值
		clearCardMoneyRechargeForm();
	});
	
	
	function clearCardMoneyRechargeForm(){
		
		$("#cardMoneyRechargeMoney").val("");
		$("#cardMoneyGiftMoney").val("");
		$('#cardMoneyRechargeMoneyType').combobox('clear');
	}
	
	$("#cardMoneyPaymentBtn2").click(function(){// 预存款充值
		clearCardMoneyPaymentForm();
	});
    
    function clearCardMoneyPaymentForm(){
		
		$("#cardMoneyPaymentMoney").val("");
	}
	
var flag;

var mobile_temp;
var client_code_temp;
var card_code_temp;
var client_name_temp;
var client_address_temp;
var remark_temp;

		$("#search").click(function(){
						
			searchClientInfo();
			
		});

		
		function searchClientInfo(){


            mobile_temp = $("#mobile").val();
			client_code_temp = $("#client_code").val();
			card_code_temp = $("#card_code").val();
			client_name_temp = $("#client_name").val();
			client_address_temp = $("#client_address").val();
			remark_temp = $("#remark").val();


			$.ajax({
				type : 'post',
				url : basePath+'/clientInfo/getAllCount',
				data : {tel_number:mobile_temp,client_code:client_code_temp,card_code:card_code_temp,client_name:client_name_temp,client_address:client_address_temp,remark:remark_temp},
				success : function(data) {
					 if(data == 0){
						$.messager.show({
							title:'提示信息',
							msg:'查询结果为空'
						});
					 }
					 if(data > 1){
						 $.messager.show({
								title:'提示信息',
								msg:'存在多个资料请选择'
							});
					 }
					 $('#Pagination').pagination({ total: data});
				}
				});	
			filp(1);
		}
		
	
		function filp(pageNumber){// 根据号码查询客户信息
			
			$.ajax({
				type : 'post',
				url : basePath+'/clientInfo/getByMobile',
				data : {page:pageNumber,tel_number:mobile_temp,client_code:client_code_temp,card_code:card_code_temp,client_name:client_name_temp,client_address:client_address_temp,remark:remark_temp},
				success : function(data) {
					
					if(data==""){
						return;
					}
					
					if(data!=null){
						
						var mobileArray= new Array();
						
						if(mobile_temp == ""){
							
							mobileArray.push(data.mobile_tel_number_1);
							mobileArray.push(data.mobile_tel_number_2);
							mobileArray.push(data.fixed_tel_number_1);
							mobileArray.push(data.fixed_tel_number_2);
							
							for(var i=0;i<mobileArray.length;i++){
								
								if(mobileArray[i]!=""&&mobileArray[i]!="NULL"){
									$("#mobile").val(mobileArray[i]);
									break;
								}
							}
							
						}
							
						clientId = data.id;
						setClientInfo(data);
						setBusinessDeal(data);
						setOrderInfo(clientId);
						setRepairTable();
						setComplaintTable();
						setFamilyCheckTable();
					}
				}
			});
		}
		
		
	
		
	    $("#clear").click(function(){
		  clearTable();
		});
		
		
		function clearTable(){  // 清空table
			
			
			// 清空左边用户数据
			clientId = null;
			$("#mobile").val("");
			$("#card_code").val("");
			$("#client_code").val("");
			$("#client_name").val("");
			$("#fixed_tel_number_1").val("");
			$("#fixed_tel_number_2").val("");
			$("#mobile_tel_number_1").val("");
			$("#mobile_tel_number_2").val("");
			$("#create_time").val("");
			$("#client_address").val("");
			$("#remark").val("");
			$("#card_money").val("");
			$("#last_family_check_time").val("");
			$("#bottle_num").val("");
			
			
			$('#clientType').combobox('clear');
			$('#firstCategory').combobox('clear');
			$('#secondCategory').combobox('clear');
			
			
			// 清空右边业务数据
			
	        $("#order_appointment_time1").datetimebox("setValue","");  
	        $("#repair_appointment_time1").datetimebox("setValue","");  
	        $("#complaint_appointment_time1").datetimebox("setValue","");  
	        $("#family_check_appointment_check_time1").datetimebox("setValue",""); 
	        
			$("#dealDate").datebox("setValue","");  
			$("#orderNum").val("");
			$("#deliveryAddress").val("");
			$("#temp_tips").val("");
			$("#clientId").val("");
			$("#clientId2").val("");
			$("#clientId3").val("");
			$("#clientId4").val("");
			$("#familyCheckClientId").val("");
			$("#cardMoneyRechargeClientId").val("");
			$("#cardMoneyPaymentClientId").val("");
			$("#complaint_address").val("");
			$("#visit_address").val("");
			$("#repair_address").val("");
			$("#family_check_address").val("");
			$("#order_tel_number").val("");
			$("#repair_tel_number").val("");
			$("#complaint_tel_number").val("");
			$("#visit_tel_number").val("");
			$("#family_check_tel_number").val("");
			$("#accept_deal_time").val("");
			$("#complaint_time").val("");
			
			$("#fee_total_amount").val("");
			$("#account_price").val("");
			$("#check_fee").val("");
	    	$("#discount_amount").val("");
	    	$("#unit").val("");
	    	$("#delivery_fee").val("");
	    	$("#price").val("");
	    	$("#allPrice").val("");
	    	
	    	$("#partsStr").val("");
	    	$("#feeType").val("");
	    	
	        $('#airBottleType').combobox('clear');
	    	$('#floorSubsidies').combobox('clear');
	    	$('#payType').combobox('clear');
	    	$('#businessType').combobox('clear');
	    	
	    	$('#tb2').datagrid('loadData', { total: 0, rows: [] }); 
		}
		
		
		

		function setClientInfo(data){// 把客户信息展示到客户信息栏目表格上
						
			$("#client_code").val(data.client_code);
			$("#card_code").val(data.card_code);
			$("#client_name").val(data.client_name);
			$("#fixed_tel_number_1").val(data.fixed_tel_number_1);
			$("#fixed_tel_number_2").val(data.fixed_tel_number_2);
			$("#mobile_tel_number_1").val(data.mobile_tel_number_1);
			$("#mobile_tel_number_2").val(data.mobile_tel_number_2);
			$("#create_time").val(formatDate(new Date(data.create_time)));
			$("#client_address").val(data.client_address);
			$("#remark").val(data.remark);
			$("#card_money").val(data.card_money);
			$("#bottle_num").val(data.bottleNum);
			$("#last_family_check_time").val(formatDate(new Date(data.last_family_check_time)));
			
			
			$('#firstCategory').combobox({
			    value : "请选择",    
			    url:basePath+'/firstCategory/getAllList', 
			    method:'get',
			    editable : false,
			    valueField:'id',    
			    textField:'first_category_name',
			    onSelect:function(rec){
			    	$('#secondCategory').combobox({  
			    	    value : "请选择",  
			    	    url:basePath+'/secondCategory/getListByFirstId?firstId='+rec.id,
			    	    method:'get',
			    	    valueField:'id',    
			    	    textField:'second_category_name',
			    	    onSelect:function(rec){
			    	    }
			    	});
			    }
			}); 
			
			
			 $('#secondCategory').combobox({  
		    	 url:basePath+'/secondCategory/getListByFirstId?firstId='+data.secondCategory.firstCategory.id,
		    	 method:'get',
		    	 valueField:'id',    
		    	 textField:'second_category_name',
		    	    
		    });  
		    
		   
			$("#clientInfoForm").form("load", {
				'clientType.id' : data.clientType == null? null : data.clientType.id,
				'firstCategory.id' : data.secondCategory.firstCategory.id,
				'secondCategory.id' : data.secondCategory.id,
			});

		}
		
		function setRepairTable(){
			$("#repair_appointment_time1").datetimebox("setValue",NowAppointmentTime());
			$("#accept_deal_time").val(new Date().toLocaleDateString());
		}
		
		function setComplaintTable(){
			$("#complaint_appointment_time1").datetimebox("setValue",NowAppointmentTime());
			$("#complaint_time").val(new Date().toLocaleDateString());
		}
		
		function setFamilyCheckTable(){
			$("#family_check_appointment_check_time1").datetimebox("setValue",NowAppointmentTime());
			$("#family_check_time").val(new Date().toLocaleDateString());
		}
		
		function setBusinessDeal(data){// 展示业务受理表格的相应数据
			
			
			$("#order_appointment_time1").datetimebox("setValue",NowAppointmentTime());  
			
			$("#dealDate").datebox("setValue",NowDateTime());  

			$("#orderNum").val(1);
			$("#deliveryAddress").val(data.client_address);
			$("#temp_tips").val(data.temp_tips);
			$("#clientId").val(data.id);
			$("#clientId2").val(data.id);
			$("#clientId3").val(data.id);
			$("#clientId4").val(data.id);
			$("#familyCheckClientId").val(data.id);
			$("#cardMoneyRechargeClientId").val(data.id);
			$("#cardMoneyPaymentClientId").val(data.id);
			$("#complaint_address").val(data.client_address);
			$("#visit_address").val(data.client_address);
			$("#repair_address").val(data.client_address);
			$("#family_check_address").val(data.client_address);
			$("#order_tel_number").val($("#mobile").val());
			$("#repair_tel_number").val($("#mobile").val());
			$("#complaint_tel_number").val($("#mobile").val());
			$("#visit_tel_number").val($("#mobile").val());
			$("#family_check_tel_number").val($("#mobile").val());
			$("#complaint_time").val(new Date().toLocaleDateString());
			$("#fee_total_amount").val(0);
			
			
			
			$("#check_fee").val("");
	    	$("#discount_amount").val("");
	    	$("#unit").val("");
	    	$("#delivery_fee").val("");
	    	$("#price").val("");
	    	$("#allPrice").val("");
			
	    	
			$('#airBottleType').combobox({//气瓶类型
			    url:basePath+'/airbottleType/getAllListForSpecialPrice?secondCategoryId=' + data.secondCategory.id, 
			    method:'get',
			    valueField:'id',    
			    textField:'air_bottle_specifications',
			    onSelect:function(rec){
			    				  
			    	//缓存气瓶类型对象
			    	airBottleTypeRec = rec;
			    	
			    	initdgTable();
			    	$("#discount_amount").val(0);
			    	$("#unit").val(rec.measurement_unit);
			    	
			    	
			    	initPrice();
			    	calTotalAmount();
			    },
			    onLoadSuccess: function () { // 加载完成后,设置选中第一项
	                var val = $(this).combobox("getData");
	                $(this).combobox("select", val[0].id);
	                
			    }
			});
			
			 $('#floorSubsidies').combobox({
				editable : false,
				url : basePath+'/floorSubsidies/getAllList',
				method:'get',
				valueField : 'floor_subsidies_money',
				textField : 'floor_subsidies_name',
				formatter: function(row){
			    return row.floor_subsidies_name + ' ' +  row.floor_subsidies_money + '元';
		         },
		        onLoadSuccess:function(data1){
		        	
		        	if(data.floorSubsidies != null){
		        		
		        		if($('#deliveryType').combobox('getValue') == 1){
		        	        $('#floorSubsidies').combobox('select',data.floorSubsidies.floor_subsidies_money);
		        	    }else{
			        	    $('#floorSubsidies').combobox('select',0);
		        	    }
		        	}else{
		        		$('#floorSubsidies').combobox('clear');
		        	}
		        },
		        onSelect:function(rec){
		        	floorSubsidiesIdTemp = rec.id;
		        	if($('#airBottleType').combobox('getValue')!=""){
		        		calTotalAmount();
		        }}
			});
			 
			
			
			$('#payType').combobox({// 付款类型
			    url:basePath+'/payType/getAllList', 
			    method:'get',
			    valueField:'id',    
			    textField:'pay_type_name',
			    onLoadSuccess: function () { // 加载完成后,设置选中第一项
	                var val = $(this).combobox("getData");
	                $(this).combobox("select", val[0].id);
	                
			    }
			});
			
			

			$('#repairType').combobox({// 维修类型
			    url:basePath+'/repairType/getAllList', 
			    method:'get',
			    valueField:'id',    
			    textField:'repair_type_name',
			    onSelect:function(rec){
					$("#repair_content").val(rec.repair_content);
			}
			});
			
			$('#repair_man').combobox({// 所派维修工
				url:basePath+'/user/getDeliveryManList?clientId='+clientId,
			    method:'get',
			    valueField:'id',    
			    textField:'full_name',
			});
			
			$('#complaintType').combobox({// 投诉类型
			    url:basePath+'/complaintType/getAllList', 
			    method:'get',
			    valueField:'id',    
			    textField:'complaint_type_name',
			    onSelect:function(rec){
					$("#complaint_content").val(rec.complaint_content);
			}
			});
			
			$('#visitType').combobox({// 投诉类型
				url:basePath+'/visitType/getAllList', 
				method:'get',
				valueField:'id',    
				textField:'visit_type_name',
				onSelect:function(rec){
					$("#visit_content").val(rec.visit_content);
				}
			});
			
			$('#cardMoneyRechargeMoneyType').combobox({
			    url:basePath+'/cardMoneyRechargeMoneyType/getAllList', 
			    method:'get',
			    valueField:'id',    
			    textField:'card_money_recharge_money_type_name',
			    onSelect:function(rec){
					$("#cardMoneyRechargeMoney").val(rec.recharge_money);
					$("#cardMoneyGiftMoney").val(rec.gift_money);
			}
			});
			
		}
		
        function changeCheckFee(){
	    	
        	calTotalAmount();
		}
        
        function changeDiscountAmount(){
	    	
        	calTotalAmount();
		}
        function changetotalprice(){
        	calTotalAmount();
        }
        
        function changeOrderNumber(){
	   
			 calTotalAmount();
		}
        
        function changeDeliveryFee(){
     	   
			 calTotalAmount();
		}
        
        function changeAccountPrice(){
      	   
        	 changePrice();
			 calTotalAmount();
		}
        
        function changeFeeToTalAmount(){
       	   
        	calTotalAmount();
		}
        
        function changePrice(){
        	$("#price").val(airBottleTypeRec.price - $("#account_price").val());
        }
        
		function calTotalAmount(){
	    	
			var amount = 0;
			amount +=  parseInt($('#floorSubsidies').combobox('getValue'))*$("#orderNum").val();
			amount +=  parseInt($("#check_fee").val())*$("#orderNum").val();
			amount +=  parseInt($("#delivery_fee").val())*$("#orderNum").val();
			amount +=  parseInt($("#price").val())*$("#orderNum").val();
			amount +=  parseInt($("#fee_total_amount").val());
			amount -=  parseInt($("#discount_amount").val());
			$("#allPrice").val(amount);
		}
		
		function setClientBottleRecordInfo(clientId){// 展示客户气瓶记录信息
			$('#tb2').datagrid({
				title:'用户最近20条气瓶信息记录',
			    url:basePath+'/clientAirBottleRecord/getRecordByLeast?clientId='+clientId,
			    columns:[[    
			        {field:'begin_time',title:'创建时间',width:150,formatter: function(value,row,index){
						return formatDate(new Date(row.begin_time));
					}},    
			        {field:'state.state_description',title:'是否退瓶',width:100,formatter: function(value,row,index){
					 return row.state.state_description;
					}
			        },    
			        {field:'end_time',title:'退瓶时间',width:150,formatter: function(value,row,index){
			        	if(row.end_time==null) return null;
						return formatDate(new Date(row.end_time));
					}},
			        {field:'secondCategory',title:'气瓶种类',width:100,formatter: function(value,row,index){
						return row.airBottleInfo.airBottleType.air_bottle_specifications;
						}},
			        {field:'airBottleCode',title:'气瓶编号',width:100,formatter: function(value,row,index){
						return row.airBottleInfo.air_bottle_code;
					}},
			        {field:'price',title:'单价',width:100,formatter: function(value,row,index){
						return row.airBottleInfo.airBottleType.price;
					}},
			        {field:'create_time',title:'出厂日期',width:150,formatter: function(value,row,index){
						return formatDate(new Date(row.airBottleInfo.create_time));
					}}
			    ]]    
			});  

		}
		
		
		
		
		function setOrderInfo(clientId){// 展示用户最近20次购气记录
			$('#tb2').datagrid({    
				title:'用户最近20次购气记录',
			    url:basePath+'/order/getOrderInfoByLatest?clientId='+ clientId,
			    columns:[[    
					{field:'order_appointment_time1',align:'center',title:'预约时间1',formatter: function(value,row,index){
				        	if(row.order_appointment_time1!=null){
					    		return formatDate(new Date(row.order_appointment_time1));
					    	}
					    	return null;
					}},
					{field:'order_time',align:'center',title:'订单时间',formatter: function(value,row,index){
			        	if(row.order_time!=null){
				    		return formatDate(new Date(row.order_time));
				    	}
				    	return null;
					}},
			        {field:'order_address',align:'center',title:'送气地址'},
			        {field:'remark',title:'受理备注',align:'center'},
			        {field:'secondCategory',title:'送气门店',align:'center',formatter: function(value,row,index){
						return row.secondCategory.second_category_name;
						}},
			        {field:'airBottleType',align:'center',title:'气瓶种类',formatter: function(value,row,index){
						return row.airBottleType.air_bottle_specifications;
					}},
				    {field:'order_number',title:'合同数量',align:'center'},
			        {field:'price',title:'单价',align:'center',formatter: function(value,row,index){
						return row.price;
					}},
					{field:'total_amount',align:'center',title:'总金额'},
				    {field:'state',title:'订单状态',align:'center',formatter:function(value,row,index)
				    {
				    	if(row.state!=null){
				    		return row.state.state_description;
				    	}
				    	return null;
				    }},
				    {field:'is_speed_up',align:'center',title:'催气次数'},
				    {field:'operator',align:'center',title:'操作人',formatter:function(value,row,index){
				    	if(row.operator!=null){
				    		return row.operator.full_name;
				    	}
				    	return null;
				    }},
				    {field:'payType',align:'center',title:'付款方式',formatter:function(value,row,index){
				    	if(row.payType!=null){
				    		return row.payType.pay_type_name;
				    	}
				    	return null;
				    }},
				    {field:'payState',align:'center',title:'付款状态',formatter:function(value,row,index){
				    	if(row.payState!=null){
				    		return row.payState.state_description;
				    	}
				    	return null;
				    }},
					{field:'itemid1',title:'操作',align:'center',width:70,formatter: function(value,row,index){
						var str = '<a onClick="speedUpOrder('+row.id+')" class="speedUpOrderCls" ></a>';  
				        return str;  }},
				    {field:'itemid3',title:'操作',align:'center',width:70,formatter: function(value,row,index){
							var str = '<a onClick="cancelOrder('+row.id+')" class="cancelOrderCls" ></a>';  
					    return str;  }},
				    {field:'itemid2',title:'操作',align:'center',width:80,formatter: function(value,row,index){
							var str = '<a onClick="cancelSpeedUpOrder('+row.id+')" class="cancelSpeedUpOrderCls" ></a>';  
					    return str;  }},
					
			    ]],
			    rowStyler:function(index,row){   
			    	 if(row.fee_total_amount > 0){
						  return 'color:#1E90FF;';
					  }
					 
					  if (row.is_speed_up > 0){   
					     return 'color:#CD0000;';   
					  } 
				},
			    onLoadSuccess:function(data){    
			    	
		            $('.speedUpOrderCls').linkbutton({text:'催气',plain:true,iconCls:'icon-add'}); 
		            $('.cancelSpeedUpOrderCls').linkbutton({text:'撤销催气',plain:true,iconCls:'icon-remove'}); 
		            $('.cancelOrderCls').linkbutton({text:'撤单',plain:true,iconCls:'icon-cancel'}); 
		            $('#tb').datagrid('fixRowHeight');
		    },  
			});  

		}
		
		
		function speedUpOrder(orderId){
			 $.ajax({
					type : 'post',
					data : {orderId:orderId},
					url : basePath+'/order/speedUpOrder',
					success : function(data) {
						$.messager.show({
							title:'提示信息',
							msg:'操作成功'
						});
						$('#tb2').datagrid('reload'); 
					}
			});
		}
		
		function cancelSpeedUpOrder(orderId){
			$.ajax({
				type : 'post',
				data : {orderId:orderId},
				url : basePath+'/order/cancelSpeedUpOrder',
				success : function(data) {
					$.messager.show({
						title:'提示信息',
						msg:'操作成功'
					});
					$('#tb2').datagrid('reload'); 
				}
		    });
		}
		
		function cancelOrder(orderId){
			
			var cancelMsg = prompt("请输入要添加的备注","");
			
			 if(cancelMsg == null){
				 return;
			 }
			
			$.ajax({
				type : 'post',
				data : {orderId:orderId,cancelMsg:cancelMsg},
				url : basePath+'/order/cancelOrder?',
				success : function(data) {
					if(data.code == 200){
						 $.messager.show({
								title : '提示信息',
								msg : '操作成功'
							});
						 $('#tb2').datagrid('reload'); 
					}else{
						$.messager.show({
							title : '提示信息',
							msg : data.msg
						});
					}
				}
		    });
		}
		
		function setNewOrderInfo(clientId){// 展示最新订单信息
			$('#tb2').datagrid({    
			    url:basePath+'/order/getNewOrderInfo?clientId='+ clientId,
			    columns:[[    
			        {field:'order_time',title:'受理时间',width:150,formatter: function(value,row,index){
						return formatDate(new Date(row.order_time));
					}},    
			        {field:'air_bottle_code',title:'气瓶编号',width:100,formatter: function(value,row,index){
					if(row.orderAirBottleInfo.length!=0){
						var str="";
						$.each(row.orderAirBottleInfo,function(i,v){
							str+=v.airBottleInfo.air_bottle_code+",";
						});
						return str.substring(0, str.length-1);
					}else{
						return null;
					}
					}
},    
			        {field:'remark',title:'受理备注',width:100},
			        {field:'secondCategory',title:'送气部门',width:100,formatter: function(value,row,index){
						return row.clientInfo.secondCategory.second_category_name;
						}},
			        {field:'businessType',title:'业务类型',width:100,formatter: function(value,row,index){
						return row.businessType.business_type_name;
					}},
			        {field:'airBottleType',title:'气瓶种类',width:100,formatter: function(value,row,index){
						return row.airBottleType.air_bottle_specifications;
					}},
			        {field:'order_number',title:'合同数量',width:100},
			        {field:'price',title:'单价',width:100,formatter: function(value,row,index){
						return row.airBottleType.price;
					}},
					{field:'feeType',title:'零件',width:100},
					{field:'totalprice',title:'零件总金额',width:100},
			        {field:'order_address',title:'送气地址',width:100}
			    ]]    
			});  

		}
		
		function setRepairInfo(clientId){// 展示维修受理信息
			$('#tb2').datagrid({
				title:'用户维修订单记录',
			    url:basePath+'/repairInfo/getRepairInfo?clientId='+clientId,
			    columns:[[    
			        {field:'accept_time',title:'受理时间',align:'center',formatter: function(value,row,index){
						return formatDate(new Date(row.accept_time));
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
			        {field:'repair_content',title:'维修内容',align:'center'},
			        {field:'repair_price',title:'上门费',align:'center',},
					{field:'repair_man',title:'维修人员',align:'center',formatter: function(value,row,index){
						if(row.repair_man != null){
							return row.repair_man.full_name;
							}
							return null;
					}},
			        {field:'repair_address',title:'地址',align:'center'},
					{field:'repair_state_description',title:'维修状态',align:'center',formatter: function(value,row,index){
						return row.repairInfoState.repair_state_description;
					}}
			    ]]    
			});  

		}
		
		function setComplaintInfo(clientId){// 展示投诉受理信息
			$('#tb2').datagrid({  
				title:'用户投诉记录',
			    url:basePath+'/complaint/getComplaintInfo?clientId='+clientId,
			    columns:[[    
			        {field:'complaint_time',title:'受理时间',align:'center',formatter: function(value,row,index){
						return formatDate(new Date(row.complaint_time));
					}},    
			        
					{field:'complaint_appointment_time1',title:'预约时间1',align:'center',formatter: function(value,row,index){
						if(row.complaint_appointment_time1!=null){
						return formatDate(new Date(row.complaint_appointment_time1));
						}
						return null;
					}},
					{field:'complaint_appointment_time2',title:'预约时间2',align:'center',formatter: function(value,row,index){
						if(row.complaint_appointment_time2!=null){
						return formatDate(new Date(row.complaint_appointment_time2));
						}
						return null;
					}},
			        {field:'complaint_note',title:'受理备注',align:'center'},
			        {field:'complaintType',title:'投诉类型',align:'center',formatter: function(value,row,index){
						return row.complaintType.complaint_type_name;
					}},
			        {field:'complaint_content',title:'投诉内容',align:'center'},
			        {field:'complaint_address',title:'地址',align:'center'},
			        {field:'complaint_state_description',title:'维修状态',align:'center',formatter: function(value,row,index){
						return row.complaintInfoState.complaint_state_description;
					}}
			    ]]    
			});  

		}
		
		function setVisitInfo(clientId){// 展示回访信息
			$('#tb2').datagrid({  
				title:'用户回访记录',
				url:basePath+'/visit/getVisitInfo?clientId='+clientId,
				columns:[[    
				          {field:'visit_code',title:'回访编号',align:'center'},
				          {field:'visitType',title:'回访类型',align:'center',formatter: function(value,row,index){
				        	  return row.visitType.visit_type_name;
				          }},
				          {field:'visit_content',title:'回访内容',align:'center'},
				          {field:'visit_note',title:'回访备注',align:'center'},
				          {field:'visit_address',title:'地址',align:'center'},
				          {field:'create_time',title:'回访时间',align:'center',formatter: function(value,row,index){
								if(row.create_time!=null){
									return formatDate(new Date(row.create_time));
									}
									return null;
								}
				          },
				          ]]    
			});  
			
		}
		
		function setOrderCancelInfo(clientId){// 展示订单撤销记录信息
			$('#tb2').datagrid({   
			  title:'用户撤销订单记录',
			  url:basePath+'/orderCancelInfo/getOrderCancelInfo?clientId='+clientId,
			  columns:[[ 
						{field:'cancel_remark',title:'撤销备注',width:200,align:'center'},
					    {field:'cancel_operator.full_name',title:'撤销人',align:'center',formatter:getCancelOperator,sortable:true},
						{field:'order_code',title:'订单编号',width:100,align:'center'},
						{field:'clientInfo.client_name',title:'用户名称',align:'center',align:'center',formatter:function(value,row,index){
							return row.clientInfo.client_name;
						}},
						{field:'airBottleType.air_bottle_specifications',title:'气瓶类型',align:'center',align:'center',formatter:function(value,row,index){
							return row.airBottleType.air_bottle_specifications;
						}},
						{field:'businessType.business_type_name',title:'业务类型',align:'center',align:'center',formatter:function(value,row,index){
							return row.businessType.business_type_name;
						}},
						{field:'deliveryType.delivery_type_name',title:'配送方式',align:'center',align:'center',formatter:function(value,row,index){
							return row.deliveryType.delivery_type_name;
						}},
						{field:'order_number',title:'订单数量',width:100,align:'center'},
						{field:'order_address',title:'订单地址',width:100,align:'center'},
						{field:'order_tel_number',title:'订单电话号码',width:100,align:'center'},
						{field:'total_amount',title:'总金额',width:100,align:'center'},
						{field:'remark',title:'备注',width:100,align:'center'},
						{field:'order_time',title:'订单时间',width:200,align:'center',formatter:function(value,row,index){
							return formatDate(new Date(row.order_time));
						}},
						{field:'operator.full_name',title:'操作员',width:200,align:'center',formatter:function(value,row,index){
							return row.operator.full_name;
						}},
						{field:'state',title:'状态',width:200,align:'center',formatter:function(value,row,index){
							return row.state.state_description;
						}},
			    ]]    
			});  

		}
		
		function getCancelOperator(value,rec)
		{
			if(rec.cancel_operator!=null){
				return rec.cancel_operator.full_name;
			}
			return null;
		}

	
		$("#addOrder").click(function(){// 下订单操作
			
			if(!$("#myForm").form('validate')){
				$.messager.show({
					title:'提示信息',
					msg:'信息不完整'
				});
				return false;
			}
			
			var lastFamilyCheckdays = checkFamilyCheckTime(clientId);
			
			/* 一年未入户安检禁止下单  */
			if(lastFamilyCheckdays >= (365)){
				alert('请注意,该用户已有' + lastFamilyCheckdays + '天未入户安检，已被禁止下单');
				return false;
			}
			
			/* 半年未入户安检提示  */
			if(lastFamilyCheckdays >= (365 / 2)){
				alert('请注意,该用户已有' + lastFamilyCheckdays + '天未入户安检,超过1年未安检将被禁止下单');
			}
			
			
						
			 $.ajax({
					type : 'post',
					data : $('#myForm').serialize(),
					url : basePath+'/order/addOrder',
					success : function(data) {
						
						if(data.code!=200){
							$.messager.show({
								title:'提示信息',
								msg:data.msg
							});
						}else{
							$('#tb2').datagrid('reload'); 
							
							//预存款付款
							if($('#payType').combobox('getValue') == 2){
								getNowCardMoney();
							}
							
							$.messager.show({
								title:'提示信息',
								msg:'操作成功'
							});
						}
						
						
					}
				});
		});
		
		
		function checkFamilyCheckTime(clientId){
			
			var days = -1;
			
			$.ajax({
				async : false,
				cache : false,
				type : 'post',
				data : {clientId:clientId},
				url : basePath+'/order/calFamilyCheckTime',
				success : function(data) {
					days = data.data;
				}
		    });
			
			return days;
			
		}
		
		function getNowCardMoney(){
			$.ajax({
				type : 'post',
				data : {clientId:clientId},
				url : basePath+'/clientInfo/getNowCardMoney',
				success : function(data) {
					$("#card_money").val(data.data);
				}
		    });
		}
		
		function formatDate(now) { 
			
			if(now == null){
				return "";
			}
			
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
		
		function NowAppointmentTime(){
			
			   var curr_time = new Date();
			   
			    var year=curr_time.getFullYear(); 
				var M=curr_time.getMonth()+1;  
			    var MM=(M<10)?"0"+M:M;  
				var D= curr_time.getDate();  
			    var DD=(D<10)?"0"+D:D; 
			    var h=curr_time.getHours();  
			    var hh=(h<10)?"0"+h:h;  
			    var m=curr_time.getMinutes();  
			    var mm=(m<10)?"0"+m:m; 
			    
			    return year+"-"+MM+"-"+DD+" "+hh+":"+mm; 
			   
		}
		
		function NowDateTime(){
			   var curr_time = new Date();
			   
			    var year=curr_time.getFullYear(); 
				var M=curr_time.getMonth()+1;  
			    var MM=(M<10)?"0"+M:M;  
				var D= curr_time.getDate();  
			    var DD=(D<10)?"0"+D:D; 
			    var h=curr_time.getHours();  
			    var hh=(h<10)?"0"+h:h;  
			    var m=curr_time.getMinutes();  
			    var mm=(m<10)?"0"+m:m; 
			    
			    return year+"-"+MM+"-"+DD; 
		}
		
		
		function initPrice(){
			
			if(airBottleTypeRec == null){
				return;
			}
			
			$.ajax({
				type : 'post',
				async : false,    
				cache : false,
				data : {airBottleTypeId:airBottleTypeRec.id,clientId:$("#clientId").val()},
				url : basePath+'/clientAirBottleTypeFee/getDeliveryFeeByAirBottleType',
				success : function(data) {
				
						if($('#deliveryType').combobox('getValue') == 1){
							if(data.data == null){
								$("#delivery_fee").val(airBottleTypeRec.delivery_fee);
								$("#check_fee").val(airBottleTypeRec.inspection_fee);
								$("#account_price").val(0);
							}else{
								if(data.data.delivery_fee == null){
									$("#delivery_fee").val(airBottleTypeRec.delivery_fee);
								}else{
									$("#delivery_fee").val(data.data.delivery_fee);
								}
								
								if(data.data.check_fee == null){
									$("#check_fee").val(airBottleTypeRec.inspection_fee);
								}else{
								   $("#check_fee").val(data.data.check_fee);
								}
								
								if(data.data.account_price == null){
									$("#account_price").val(0);
								}else{
								   $("#account_price").val(data.data.account_price);
								}
							}
						}else{
							$("#delivery_fee").val(0);
							$("#check_fee").val(airBottleTypeRec.inspection_fee);
							$("#account_price").val(0);
						}
						
				    	$("#price").val(airBottleTypeRec.price - $("#account_price").val());

		}
			});
		}
