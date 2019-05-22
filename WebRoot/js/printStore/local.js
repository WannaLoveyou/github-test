function initOrderDialog(orderInfoRow){
	console.log(orderInfoRow);

	$(".print_order_code").html(orderInfoRow.order_code);//票据编号
	$(".print_client_code").html(orderInfoRow.client_code);//客户编号
	if(orderInfoRow.delivery_type_id===2){//门店自提
		
		$(".print_client_name").html("零售");//客户名称
	}else{//订气配送
		$(".print_client_name").html(orderInfoRow.delivery_man_full_name);//客户名称
	}
	$(".print_children_client_name").html(orderInfoRow.client_name);//子客户名称
	$(".print_air_bottle_specifications").html(orderInfoRow.air_bottle_specifications);//规格
	$(".print_order_number").html(orderInfoRow.order_number);//订单数量
	$(".print_price").html(orderInfoRow.price);//单价
	$(".print_totalPrice").html(orderInfoRow.price*orderInfoRow.order_number);//总价
	$(".print_cash_receipts").html(orderInfoRow.price*orderInfoRow.order_number);//实收款
	$(".print_total_number").html(orderInfoRow.order_number);//合计数量
	$(".print_weight").html(orderInfoRow.air_bottle_specifications);//合计净重

	/*document.getElementById('print_computer_no').innerHTML = orderInfoRow.order_code;
	document.getElementById('print_client_name').innerHTML = getClientname(null,orderInfoRow);
	document.getElementById('print_client_tel_number').innerHTML = orderInfoRow.order_tel_number;
	document.getElementById('print_client_code').innerHTML = getClientCode(null,orderInfoRow);
	document.getElementById('print_now_time').innerHTML = formatDate(new Date());
	document.getElementById('print_order_address').innerHTML = orderInfoRow.order_address;
	document.getElementById('print_deliver_department').innerHTML = getSecondCategoryName(null,orderInfoRow);
	document.getElementById('print_delivery_man').innerHTML = getDeliveryman(null,orderInfoRow);
	document.getElementById('print_remark').innerHTML = orderInfoRow.remark;
	
	document.getElementById('print_business_name').innerHTML = getDeliveryType(null,orderInfoRow);
	document.getElementById('print_air_bottle_specifications').innerHTML = getAirBottleType(null,orderInfoRow);
	document.getElementById('print_price').innerHTML = (orderInfoRow.price + orderInfoRow.delivery_fee).toFixed(2);
	document.getElementById('print_count').innerHTML = orderInfoRow.order_number;
	document.getElementById('print_total_price').innerHTML = ((orderInfoRow.price + orderInfoRow.delivery_fee) * orderInfoRow.order_number).toFixed(2);
	document.getElementById('print_total_info').innerHTML = orderInfoRow.total_amount.toFixed(2);*/

	$.ajax({
		type : 'post',
		url : basePath+'/order/getPirntOrderInfo?',
		data : {orderId:orderInfoRow.id},
		success : function(data) {
			/*document.getElementById('print_floor_subsidies_name').innerHTML = data.data.print_floor_subsidies_name;
			document.getElementById('print_print_operator').innerHTML = data.data.print_print_operator;*/
			$(".print_last_buy_time").html(getLastBuyTime(null,data.data));//开票日期
			$(".print_print_operator").html(data.data.print_print_operator);//开票员
			
			/*document.getElementsByClassName('print_last_buy_time').innerHTML = getLastBuyTime(null,data.data);//开票日期
			document.getElementsByClassName('print_print_operator').innerHTML = data.data.print_print_operator;//开票员
*/		    initPrintOrderInfo(orderInfoRow,data.data.partFeeInfos);
		     
		    // 自定义打印
			if(customPrintFlag){
				
				/*if ($("input[name='isPrintPrice']:checked").val() == 'N'){
					document.getElementById('print_price').innerHTML = '';
					document.getElementById('print_air_bottle_total_amount').innerHTML = '';
				}
				
				if($("input[name='isPrintTotalPrice']:checked").val() == 'N'){
					document.getElementById('print_air_bottle_total_amount_need_pay').innerHTML = '';
				}*/
			}
		    
			table(orderInfoRow.id);
		}
	});
}

function getLastBuyTime(value, rec) {
	var myDate = new Date(rec.print_last_buy_time);
	var years = myDate.getFullYear();
	var month = myDate.getMonth();
	var dates = myDate.getDate();
	var hours = myDate.getHours();
	var minutes = myDate.getMinutes();
	return years+"年"+month+"月"+dates+"日";
	/*if (rec.print_last_buy_time != null) {
		return new Date(rec.print_last_buy_time).toLocaleString() ;
	}
	return null;*/
}

function initPrintOrderInfo(orderInfoRow,partFeeInfos){

	console.log(partFeeInfos);
	
	var totalPartMoney = 0;
	
	var trLen = 2;
	var tdLen = 5;
	
	var json = partFeeInfos;
		
	for(var i = 0; i < trLen ; i++){
			
	   $(("#printOrderInfoRowsTr" + (i+1))).find("td").each(function(td_index){
		    if(td_index < tdLen){
		    	$(this).html('&nbsp;');
		    }
	   });
   }
	
   
	if(json == null){
		return;
	}
	
	
	for(var i = 0; i < json.length&&i < trLen ; i++){
		
		$(("#printOrderInfoRowsTr" + (i+1))).find("td").each(function(td_index){
			if(td_index == 0){
				$(this).html(json[i].partType.part_type_name);// 项目
			}else if(td_index == 1){
				$(this).html(getAirBottleType(null,orderInfoRow));// 型号
			}else if(td_index == 2){
				$(this).html(json[i].number); // 订单数量
			}else if(td_index == 3){
				$(this).html(json[i].price.toFixed(2));// 单价
			}else if(td_index == 4){
				$(this).html((json[i].price * json[i].number).toFixed(2)); // 金额
			}
//			else if(td_index == 5){
//				$(this).html('&nbsp;'); // 搬运费
//			}
	   });
	}
}