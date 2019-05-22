function initOrderDialog(orderInfoRow){
	console.log(orderInfoRow);
	document.getElementById('print_computer_no').innerHTML = orderInfoRow.clientInfo.card_code;
	document.getElementById('print_client_name').innerHTML = getClientName(null,orderInfoRow);//客户名称
	document.getElementById('print_client_tel_number').innerHTML = orderInfoRow.clientInfo.fixed_tel_number_1;//电话
	document.getElementById('print_client_code').innerHTML = getClientCode(null,orderInfoRow);//客户编号
	document.getElementById('print_now_time').innerHTML = formatDate(new Date());//时间
	document.getElementById('print_order_address').innerHTML = orderInfoRow.clientInfo.client_address;//地址
	if(orderInfoRow.rent_operator.secondCategory!=null){
		document.getElementById('print_deliver_department').innerHTML = orderInfoRow.rent_operator.secondCategory.second_category_name;//配送部门
	}
	document.getElementById('print_delivery_man').innerHTML = orderInfoRow.rent_operator.is_wh_delivery_man;//运送人员 
	document.getElementById('print_remark').innerHTML = "月租"+orderInfoRow.rent_money_for_month+"元/月";//备注
	
	/*document.getElementById('print_business_name').innerHTML = orderInfoRow.floorSubsidies.floor_subsidies_name;*///商务（业务）名称
	document.getElementById('print_air_bottle_specifications').innerHTML = getAieBottleType(null,orderInfoRow);//规格型号
	document.getElementById('print_price').innerHTML = (orderInfoRow.rent_deposit).toFixed(2);
	document.getElementById('print_count').innerHTML = orderInfoRow.rent_num;//数量
	document.getElementById('print_total_price').innerHTML = (orderInfoRow.rent_num * orderInfoRow.rent_deposit).toFixed(2);//金额
	document.getElementById('print_total_info').innerHTML = (orderInfoRow.rent_num * orderInfoRow.rent_deposit).toFixed(2);//总计
	if(orderInfoRow.airBottleRentInfoState.state_description=="已归还"){//退瓶
		document.getElementById('print_business_deposit').innerHTML = "退瓶";
	}else{
		document.getElementById('print_business_deposit').innerHTML = "钢瓶押金";
	}
	
	$.ajax({
		type : 'post',
		url : basePath+'/order/getPirntOrderInfo?',
		data : {orderId:orderInfoRow.id},
		success : function(data) {
			console.log("商品名称="+data.data.print_floor_subsidies_name);
			//document.getElementById('print_business_deposit').innerHTML = orderInfoRow.rent_deposit;//钢瓶押金
			//document.getElementById('print_floor_subsidies_name').innerHTML = data.data.print_floor_subsidies_name;
			document.getElementById('print_print_operator').innerHTML = data.data.print_print_operator;

		    /*initPrintOrderInfo(orderInfoRow,data.data.partFeeInfos);
		     
		    // 自定义打印
			if(customPrintFlag){
				
				if ($("input[name='isPrintPrice']:checked").val() == 'N'){
					document.getElementById('print_price').innerHTML = '';
					document.getElementById('print_air_bottle_total_amount').innerHTML = '';
				}
				
				if($("input[name='isPrintTotalPrice']:checked").val() == 'N'){
					document.getElementById('print_air_bottle_total_amount_need_pay').innerHTML = '';
				}
			}*/
		    
			table(orderInfoRow.id);
		}
	});


}

function initPrintOrderInfo(orderInfoRow,partFeeInfos){
	
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