const WECHAT_PAY_CODE = 4;
const NO_PAY_CODE = 100;
const HAS_PAY_CODE = 200;
function initOrderDialog(orderInfoRow){
	
	// 没有显示受理时间,受理时间处填写客户姓名;送气时间是打单的时候;右上角是下单时间
	document.getElementById('print_order_code').innerHTML = orderInfoRow.order_code;
	document.getElementById('print_order_time').innerHTML = formatDate(new Date(orderInfoRow.order_time));

	document.getElementById('print_client_code').innerHTML = getClientCode(null,orderInfoRow);
	
	document.getElementById('print_client_name').innerHTML = getClientname(null,orderInfoRow);
	document.getElementById('print_appointment_time').innerHTML = formatDate(new Date(orderInfoRow.order_appointment_time1));
	document.getElementById('print_now_time').innerHTML = formatDate(new Date());
	
	document.getElementById('print_order_address').innerHTML = orderInfoRow.order_address;
	document.getElementById('print_client_tel_number').innerHTML = orderInfoRow.order_tel_number;	
	// 单价已经包含送气费，不用单独列出来
	document.getElementById('print_business_name').innerHTML = getAirBottleType(null,orderInfoRow);
	document.getElementById('print_count').innerHTML = orderInfoRow.order_number;
	document.getElementById('print_price').innerHTML = (orderInfoRow.price + orderInfoRow.delivery_fee).toFixed(2);
	document.getElementById('print_total_price').innerHTML = ((orderInfoRow.price + orderInfoRow.delivery_fee) * orderInfoRow.order_number).toFixed(2);
	document.getElementById('print_remark').innerHTML = orderInfoRow.remark;

	document.getElementById('print_total_info').innerHTML = "￥"+orderInfoRow.total_amount.toFixed(2);
	document.getElementById('print_total_info_in_chinese').innerHTML = changeMoneyToChinese(orderInfoRow.total_amount.toFixed(2));

	document.getElementById('print_delivery_man').innerHTML = getDeliveryman(null,orderInfoRow);
	
	$.ajax({
		type : 'post',
		url : basePath+'/order/getPirntOrderInfo?',
		data : {orderId:orderInfoRow.id},
		success : function(data) {
			
			//document.getElementById('print_floor_subsidies_name').innerHTML = data.data.print_floor_subsidies_name;
			document.getElementById('print_print_operator').innerHTML = data.data.print_print_operator;
			var print_pay_type = "";
			if(data.data.print_pay_type.id == WECHAT_PAY_CODE){
				var print_pay_state_id = data.data.print_pay_state.id;
				if(print_pay_state_id == NO_PAY_CODE)
					print_pay_type = "微信未付";
				if(print_pay_state_id == HAS_PAY_CODE)
					print_pay_type = "微信已付";
			}else{
				print_pay_type = data.data.print_pay_type.pay_type_name;
			}	
			document.getElementById('print_pay_type').innerHTML = print_pay_type;
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
	
	var trLen = 3;
	var tdLen = 8;
	
	var json = partFeeInfos;
	for(var i = 0; i < trLen ; i++){			
	   $(("#printOrderInfoRowsTr" + (i+1))).find("td").each(function(td_index){
		    if(td_index < tdLen){
		    	$(this).html('&nbsp;');
		    }
	   });
    }
	var partFeeInfosLength = json==null?0:json.length;
	for(var i = 0; i < partFeeInfosLength && i < trLen ; i++){		
		$(("#printOrderInfoRowsTr" + (i+1))).find("td").each(function(td_index){
			if(td_index == 0){
				$(this).html(json[i].partType.part_type_name);// 项目
			}else if(td_index == 1){
				$(this).html(json[i].number); // 订单数量
			}else if(td_index == 2){
				$(this).html(getAirBottleType(null,orderInfoRow));// 型号
			}else if(td_index == 3){
				$(this).html(json[i].price.toFixed(2));// 单价
			}else if(td_index == 4){
				$(this).html((json[i].price * json[i].number).toFixed(2)); // 金额
			}
		});
	}
	// 显示楼层费
	var floor_subsidies_money = orderInfoRow.floor_subsidies_money;
	if (floor_subsidies_money != 0 && partFeeInfosLength < trLen) {
		$(("#printOrderInfoRowsTr" + (partFeeInfosLength + 1))).find("td").each(function(td_index){
			if(td_index == 0){
				$(this).html("送三楼及以上");// 项目
			}else if(td_index == 1){
				$(this).html(orderInfoRow.order_number); // 订单数量
			}else if(td_index == 2){
				$(this).html("瓶");// 型号
			}else if(td_index == 3){
				$(this).html(floor_subsidies_money);// 单价
			}else if(td_index == 4){
				$(this).html((floor_subsidies_money * orderInfoRow.order_number).toFixed(2)); // 金额
			}
		});
	}
}