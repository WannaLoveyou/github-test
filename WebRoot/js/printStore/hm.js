function initOrderDialog(orderInfoRow){
	
	document.getElementById('print_client_name').innerHTML = getClientname(null,orderInfoRow);
	document.getElementById('print_client_tel_number').innerHTML = orderInfoRow.order_tel_number;
	document.getElementById('print_client_code').innerHTML = getClientCode(null,orderInfoRow);
	document.getElementById('print_print_times').innerHTML = orderInfoRow.id;
	document.getElementById('print_now_time').innerHTML = formatDate(new Date());
	document.getElementById('print_order_address').innerHTML = orderInfoRow.order_address;
	document.getElementById('print_now_buy_time').innerHTML = formatDate(new Date(orderInfoRow.order_time));
	document.getElementById('print_order_operator').innerHTML = getOperator(null,orderInfoRow);
	document.getElementById('print_second_category_name').innerHTML = getSecondCategoryName(null,orderInfoRow);
	document.getElementById('print_delivery_man').innerHTML = getDeliveryman(null,orderInfoRow);

	
	document.getElementById('print_delivery_type').innerHTML = getDeliveryType(null,orderInfoRow);
	document.getElementById('print_air_bottle_specifications').innerHTML = getAirBottleType(null,orderInfoRow);
	document.getElementById('print_order_number').innerHTML = orderInfoRow.order_number;
	document.getElementById('print_price').innerHTML = orderInfoRow.price.toFixed(2);
	document.getElementById('print_air_bottle_total_amount').innerHTML = (orderInfoRow.price * orderInfoRow.order_number).toFixed(2);
	document.getElementById('print_delivery_fee').innerHTML = (orderInfoRow.delivery_fee * orderInfoRow.order_number).toFixed(2);
	document.getElementById('print_air_bottle_total_amount_need_pay').innerHTML = orderInfoRow.total_amount.toFixed(2);
	document.getElementById('print_pay_state_description').innerHTML = getPayType(null,orderInfoRow);

	document.getElementById('print_remark').innerHTML = orderInfoRow.remark;

	
	
	$.ajax({
		type : 'post',
		url : basePath+'/order/getPirntOrderInfo?',
		data : {orderId:orderInfoRow.id},
		success : function(data) {
			
			if(data.data.print_last_buy_time != null){
			    document.getElementById('print_last_buy_time').innerHTML = formatDate(new Date(data.data.print_last_buy_time));
			}else{
			    document.getElementById('print_last_buy_time').innerHTML = '';
			}
			document.getElementById('print_floor_subsidies_name').innerHTML = data.data.print_floor_subsidies_name;
			document.getElementById('print_print_operator').innerHTML = data.data.print_print_operator;
			
			
		    initPrintOrderInfo(orderInfoRow,data.data.partFeeInfos);
		     
		    // 自定义打印
			if(customPrintFlag){
				
				if ($("input[name='isPrintPrice']:checked").val() == 'N'){
					document.getElementById('print_price').innerHTML = '';
					document.getElementById('print_air_bottle_total_amount').innerHTML = '';
				}
				
				if($("input[name='isPrintTotalPrice']:checked").val() == 'N'){
					document.getElementById('print_air_bottle_total_amount_need_pay').innerHTML = '';
				}
			}
		    
			table(orderInfoRow.id);
		}
	});
}

function initPrintOrderInfo(orderInfoRow,partFeeInfos){
	
	var totalPartMoney = 0;
	
	var trLen = 2;
	var tdLen = 6;
	
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
				}else if(td_index == 5){
					$(this).html('&nbsp;'); // 搬运费
				}
	   });
	}
}