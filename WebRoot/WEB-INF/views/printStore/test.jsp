<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title></title>
</head>
<body>
	<div id="print_div" class="print_div">
		<table cellpadding="0" cellspacing="0" style="width: 100%;"
			align="left">
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" style="width: 100%;">
						<tr>
							<td style="width: 2.1cm; height: 0.45cm;color: white;" class="sc-table-name">流水号:</td>
							<td id="print_order_code" style="width: 3.6cm;" align="left"></td>
							<td style="width: 2.1cm;color: white;" class="sc-table-name">送气电话:</td>
							<td style="width: 3.7cm;color: white;" align="left">88317171</td>
							<td style="width: 1cm;color: white;" class="sc-table-name">日期</td>
							<td id="print_order_time" style="width: 4.2cm;" align="left"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" style="width: 100%;">
						<tr align="center">
							<td style="width: 2.1cm; height: 0.7cm;color: white;" class="sc-table-name">业务单位</td>
							<td style="width: 4cm;"></td>
							<td style="width: 2.2cm;color: white;" class="sc-table-name">客户类型</td>
							<td id="print_client_type" style="width: 2.05cm;"></td>
							<td style="width: 1.8cm;color: white;" class="sc-table-name">客户编号</td>
							<td id="print_client_code" style="width: 3.7cm;"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" style="width: 100%;">
						<tr align="center">
							<td style="width: 2.1cm; height: 0.7cm;color: white;" class="sc-table-name">受理时间</td>
							<td id="print_client_name" style="width: 2.9cm;"></td>
							<td style="width: 1.0cm;color: white;" class="sc-table-name">预约时间</td>
							<td id="print_appointment_time" style="width: 4.25cm;"></td>
							<td style="width: 1.8cm;color: white;" class="sc-table-name">送气时间</td>
							<td id="print_now_time" style="width: 3.7cm;"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr >
				<td>
					<table cellpadding="0" cellspacing="0" style="width: 100%;">
						<tr align="center" valign="top">
							<td style="width: 2.1cm; height: 1.3cm;color: white;" class="sc-table-name">客户地址</td>
							<td id="print_order_address" style="width: 6.1cm;"></td>
							<td style="width: 2.2cm;color: white;" class="sc-table-name">客户电话</td>
							<td id="print_client_tel_number" style="width: 5.4cm;"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" style="width: 100%;font-size: 12px;">
						<tr style="color: white;">
							<th style="width: 2.1cm; height: 0.1cm;"></th>
							<th style="width: 1.8cm;"></th>
							<th style="width: 1.8cm;"></th>
							<th style="width: 2cm;"></th>
							<th style="width: 2cm;"></th>
							<th style="width: 1.95cm;"></th>
							<th style="width: 2cm;"></th>
							<th style="width: 2.5cm;"></th>
						</tr>
						<tr align="center">
							<td id="print_business_name" style="width: 2.1cm; height: 0.66cm;"></td>
							<td id="print_count"></td>
							<td>瓶</td>
							<td id="print_price"></td>
							<td id="print_total_price"></td>
							<td id="print_delivery_fee"></td>
							<td id="print_pay_type"></td>
							<td id="print_remark"></td>
						</tr>
						<tr id="printOrderInfoRowsTr1" align="center" >
							<td style="width: 2.1cm; height: 0.66cm;"></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr id="printOrderInfoRowsTr2" align="center">
							<td style="width: 2.1cm; height: 0.66cm;"></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr id="printOrderInfoRowsTr3" align="center">
							<td style="width: 2.1cm; height: 0.66cm;"></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td style="width: 2.1cm; height: 0.74cm;color: white;">金额合计:</td>
							<td id="print_total_info"></td>
							<td style="color: white;">大写:</td>
							<td id="print_total_info_in_chinese" colspan="5"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" style="width: 100%;">
						<tr align="center">
							<td style="width: 2.1cm; height: 0.58cm;color: white;">重瓶信息</td>
							<td style="width: 4.1cm;"></td>
							<td style="width: 2cm;color: white;">空瓶信息</td>
							<td style="width: 3.4cm;"></td>
							<td style="width: 1.8cm;color: white;">送气工号</td>
							<td id="print_delivery_man" style="width: 2.15cm;"></td>
						</tr>
						<tr align="center">
							<td style="height: 0.58cm;color: white;">制单人:</td>
							<td id="print_print_operator"></td>
							<td style="color: white;">复核:</td>
							<td></td>
							<td style="color: white;">客服签收:</td>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript">
	/*
	SET_PRINT_PAGESIZE(intOrient,intPageWidth,intPageHeight,strPageName)设定纸张大小
	ADD_PRINT_TABLE(intTop,intLeft,intWidth,intHeight,strHtml)增加表格项
	*/
	
	var PRINT_INIT_NAME = "道滘株洲业务受理单";
	var intPageWidth = "19.1cm";
	var intPageHeight = "10.2cm";
	var intTop = "1.05cm";
	var intLeft = "0.1cm";
	var intWidth = "15.7cm";
	var intHeight = "8.2cm";


</script>
</html>