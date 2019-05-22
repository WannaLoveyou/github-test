<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title></title>
<style type="text/css">
	.sc-disflex{display: flex;}
	.sc-flex-column{flex-direction: column;}
	.sc-flex-row{flex-direction: row;}
	.sc-justify{justify-content: space-between;}
	.sc-flex{flex: 1;}
	.sc-align{align-items: center;}
	tr td{padding: 0px !important;text-align: left !important;}
</style>
</head>
<body>
	<div id="printDisplayDialog" class="easyui-dialog" title=" "
		style="width:300px;height:150px;padding:10px" closed="true"
		modal="true">
	<div id="print_div" class="print_div">
		<table cellpadding="0" cellspacing="0" style="width: 21.8cm;">
			<tr class="sc-disflex">
				<td class="sc-flex" style="position: relative;">
					<div style="position: absolute;bottom: -25%;right: 5px;width: 10px;">第一联存根联</div>
					<table>
						<tr style="height: 13.2px;">
							<td colspan="2" align="center" style="font-size: 16px;padding: 0px !important;">代充装液化气</td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;票据编号：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;" id="print_order_code" class="print_order_code"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;客户编号：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;" id="print_client_code" class="print_client_code"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;客户名称：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;" id="print_client_name" class="print_client_name"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;&nbsp;&nbsp;子客户：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;" class="print_children_client_name"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;开票日期：</td>
							<td style="font-size: 12px;color: #333333;padding: 0px !important;height: 13.2px;" id="print_last_buy_time" class="print_last_buy_time"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.32px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;开票员：</td>
							<td style="font-size: 13.32px;color: #333333;padding: 0px !important;" id="print_print_operator" class="print_print_operator"></td>
						</tr>
						<tr>
							<td colspan="2">
								<table border="1" cellspacing="0" width="100%">
									<tr>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 1px 3px;">规格</td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 1px 3px;">数量</td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 1px 3px;">单价</td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 1px 3px;">金额</td>
									</tr>
									<tr>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 2px 3px;" align="center" id="print_air_bottle_specifications" class="print_air_bottle_specifications"></td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 2px 3px;" align="center" id="print_order_number" class="print_order_number"></td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 2px 3px;" align="center" id="print_price" class="print_price"></td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 2px 3px;" align="center" id="print_totalPrice" class="print_totalPrice"></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px 0px 0px 0.3cm;width: 74px;">实收金额：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px;" id="print_cash_receipts" class="print_cash_receipts"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px 0px 0px 0.3cm;width: 74px;">合计数量：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px;" id="print_total_number" class="print_total_number"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px 0px 0px 0.3cm;width: 74px;">合计净重：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px;" id="print_weight" class="print_weight"></td>
						</tr>
					</table>
				</td>
				<td class="sc-flex" style="position: relative;">
					<div style="position: absolute;bottom: -25%;right: 5px;width: 10px;">第二联出门联</div>
					<table>
						<tr style="height: 13.2px;">
							<td colspan="2" align="center" style="font-size: 16px;padding: 0px !important;">代充装液化气</td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;票据编号：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;" id="print_order_code" class="print_order_code"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;客户编号：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;" id="print_client_code" class="print_client_code"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;客户名称：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;" id="print_client_name" class="print_client_name"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;&nbsp;&nbsp;子客户：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;" class="print_children_client_name"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;开票日期：</td>
							<td style="font-size: 12px;color: #333333;padding: 0px !important;height: 13.2px;" id="print_last_buy_time" class="print_last_buy_time"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.32px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;开票员：</td>
							<td style="font-size: 13.32px;color: #333333;padding: 0px !important;" id="print_print_operator" class="print_print_operator"></td>
						</tr>
						<tr>
							<td colspan="2">
								<table border="1" cellspacing="0" width="100%">
									<tr>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 1px 3px;">规格</td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 1px 3px;">数量</td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 1px 3px;">单价</td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 1px 3px;">金额</td>
									</tr>
									<tr>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 2px 3px;" align="center" id="print_air_bottle_specifications" class="print_air_bottle_specifications"></td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 2px 3px;" align="center" id="print_order_number" class="print_order_number"></td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 2px 3px;" align="center" id="print_price" class="print_price"></td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 2px 3px;" align="center" id="print_totalPrice" class="print_totalPrice"></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px 0px 0px 0.3cm;width: 74px;">实收金额：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px;" id="print_cash_receipts" class="print_cash_receipts"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px 0px 0px 0.3cm;width: 74px;">合计数量：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px;" id="print_total_number" class="print_total_number"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px 0px 0px 0.3cm;width: 74px;">合计净重：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px;" id="print_weight" class="print_weight"></td>
						</tr>
					</table>
				</td>
				<td class="sc-flex" style="position: relative;">
					<div style="position: absolute;bottom: -25%;right: 5px;width: 10px;">第三联客户联</div>
					<table>
						<tr style="height: 13.2px;">
							<td colspan="2" align="center" style="font-size: 16px;padding: 0px !important;">代充装液化气</td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;票据编号：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;" id="print_order_code" class="print_order_code"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;客户编号：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;" id="print_client_code" class="print_client_code"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;客户名称：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;" id="print_client_name" class="print_client_name"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;&nbsp;&nbsp;子客户：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;" class="print_children_client_name"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;开票日期：</td>
							<td style="font-size: 12px;color: #333333;padding: 0px !important;height: 13.2px;" id="print_last_buy_time" class="print_last_buy_time"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.32px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;开票员：</td>
							<td style="font-size: 13.32px;color: #333333;padding: 0px !important;" id="print_print_operator" class="print_print_operator"></td>
						</tr>
						<tr>
							<td colspan="2">
								<table border="1" cellspacing="0" width="100%">
									<tr>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 1px 3px;">规格</td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 1px 3px;">数量</td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 1px 3px;">单价</td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 1px 3px;">金额</td>
									</tr>
									<tr>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 2px 3px;" align="center" id="print_air_bottle_specifications" class="print_air_bottle_specifications"></td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 2px 3px;" align="center" id="print_order_number" class="print_order_number"></td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 2px 3px;" align="center" id="print_price" class="print_price"></td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 2px 3px;" align="center" id="print_totalPrice" class="print_totalPrice"></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px 0px 0px 0.3cm;width: 74px;">实收金额：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px;" id="print_cash_receipts" class="print_cash_receipts"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px 0px 0px 0.3cm;width: 74px;">合计数量：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px;" id="print_total_number" class="print_total_number"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px 0px 0px 0.3cm;width: 74px;">合计净重：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px;" id="print_weight" class="print_weight"></td>
						</tr>
					</table>
				</td>
				<td class="sc-flex" style="position: relative;">
					<div style="position: absolute;bottom: -25%;right: 5px;width: 10px;">第四联充装联</div>
					<table>
						<tr style="height: 13.2px;">
							<td colspan="2" align="center" style="font-size: 16px;padding: 0px !important;">代充装液化气</td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;票据编号：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;" id="print_order_code" class="print_order_code"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;客户编号：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;" id="print_client_code" class="print_client_code"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;客户名称：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;" id="print_client_name" class="print_client_name"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;&nbsp;&nbsp;子客户：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;" class="print_children_client_name"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;开票日期：</td>
							<td style="font-size: 12px;color: #333333;padding: 0px !important;height: 13.2px;" id="print_last_buy_time" class="print_last_buy_time"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.32px;color: #333333;padding: 0px !important;width: 74px;">&nbsp;开票员：</td>
							<td style="font-size: 13.32px;color: #333333;padding: 0px !important;" id="print_print_operator" class="print_print_operator"></td>
						</tr>
						<tr>
							<td colspan="2">
								<table border="1" cellspacing="0" width="100%">
									<tr>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 1px 3px;">规格</td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 1px 3px;">数量</td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 1px 3px;">单价</td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 1px 3px;">金额</td>
									</tr>
									<tr>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 2px 3px;" align="center" id="print_air_bottle_specifications" class="print_air_bottle_specifications"></td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 2px 3px;" align="center" id="print_order_number" class="print_order_number"></td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 2px 3px;" align="center" id="print_price" class="print_price"></td>
										<td style="font-size: 13.5px;color: #333333;height: 14px;padding: 2px 3px;" align="center" id="print_totalPrice" class="print_totalPrice"></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px 0px 0px 0.3cm;width: 74px;">实收金额：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px;" id="print_cash_receipts" class="print_cash_receipts"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px 0px 0px 0.3cm;width: 74px;">合计数量：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px;" id="print_total_number" class="print_total_number"></td>
						</tr>
						<tr style="height: 13.2px;">
							<td style="font-size: 13.5px;color: #333333;padding: 0px 0px 0px 0.3cm;width: 74px;">合计净重：</td>
							<td style="font-size: 13.5px;color: #333333;padding: 0px;" id="print_weight" class="print_weight"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	</div>
</body>
<script type="text/javascript">
	/*
		SET_PRINT_PAGESIZE(intOrient,intPageWidth,intPageHeight,strPageName)设定纸张大小
		ADD_PRINT_TABLE(intTop,intLeft,intWidth,intHeight,strHtml)增加表格项
	 */

	var PRINT_INIT_NAME = "恒源配送收据";
	var intPageWidth = "24cm";
	var intPageHeight = "14cm";
	var intTop = "0.05cm";
	//var intLeft = "1.5cm"; // final
	var intLeft = "0cm";
	var intWidth = "22.8cm";
	var intHeight = "14cm";
</script>
</html>