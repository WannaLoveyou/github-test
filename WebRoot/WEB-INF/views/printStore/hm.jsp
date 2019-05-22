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
	<div id="printOrderDialog" class="easyui-dialog" title="打印订单"
		style="width: 600px; height: 350px; padding: 10px" closed="true"
		modal="true">
		<div id="print_div" class="print_div">
			<table cellpadding="0" cellspacing="0" align="left">
				<tr>
					<td align="center"
						style="font-size: 14px; height: 0.51cm; color: white;">客户姓名</td>
					<td id="print_client_name" align="left" colspan="2"
						style="font-size: 14px; padding-left: 5px;"></td>
					<td align="center" style="font-size: 14px; color: white;">客户电话</td>
					<td id="print_client_tel_number" align="left" colspan="4"
						style="font-size: 14px; padding-left: 5px;"></td>
				</tr>
				<tr>
					<td align="center"
						style="font-size: 14px; height: 0.51cm; color: white;">客户编号</td>
					<td id="print_client_code" align="left" colspan="2"
						style="font-size: 14px; padding-left: 5px;"></td>
					<td align="center" style="font-size: 14px; color: white;">打印次数</td>
					<td id="print_print_times" align="center" style="font-size: 14px;">
					</td>
					<td align="center" style="font-size: 14px; color: white;">打印日期</td>
					<td id="print_now_time" align="left" colspan="2"
						style="font-size: 14px; padding-left: 5px;"></td>
				</tr>
				<tr>
					<td align="center"
						style="font-size: 14px; height: 0.51cm; color: white;">客户地址</td>
					<td id="print_order_address" align="left" colspan="5"
						style="font-size: 14px; padding-left: 5px;"></td>
					<td align="center" style="font-size: 14px; color: white;">楼层</td>
					<td id="print_floor_subsidies_name" align="left"
						style="font-size: 14px; padding-left: 5px;"></td>
				</tr>
				<tr>
					<td align="center" colspan="2"
						style="font-size: 14px; height: 0.51cm; color: white;">本次购气日期</td>
					<td id="print_now_buy_time" align="left" colspan="3"
						style="font-size: 14px; padding-left: 5px;"></td>
					<td align="center" style="font-size: 14px; color: white;">上次订气</td>
					<td id="print_last_buy_time" align="left" colspan="2"
						style="font-size: 14px; padding-left: 5px;"></td>
				</tr>
				<tr>
					<td align="center"
						style="width: 72.2px; font-size: 14px; height: 0.51cm; color: white;">项目</td>
					<td align="center"
						style="width: 72.2px; font-size: 14px; color: white;">规格</td>
					<td align="center"
						style="width: 72.2px; font-size: 14px; color: white;">数量</td>
					<td align="center"
						style="width: 72.2px; font-size: 14px; color: white;">单价</td>
					<td align="center"
						style="width: 72.2px; font-size: 14px; color: white;">金额</td>
					<td align="center"
						style="width: 72.2px; font-size: 14px; color: white;">搬运费</td>
					<td align="center"
						style="width: 72.2px; font-size: 14px; color: white;">本次应付</td>
					<td align="center"
						style="width: 72.2px; font-size: 14px; color: white;">支付方式</td>
				</tr>
				<tr>
					<td id="print_delivery_type" align="center"
						style="height: 0.48cm; font-size: 14px;"></td>
					<td id="print_air_bottle_specifications" align="center"
						style="font-size: 14px;"></td>
					<td id="print_order_number" align="center" style="font-size: 14px;"></td>
					<td id="print_price" align="center" style="font-size: 14px;"></td>
					<td id="print_air_bottle_total_amount" align="center"
						style="font-size: 14px;"></td>
					<td id="print_delivery_fee" align="center" style="font-size: 14px;"></td>
					<td id="print_air_bottle_total_amount_need_pay" align="center"
						style="font-size: 14px;" rowspan="3"></td>
					<td id="print_pay_state_description" align="center"
						style="font-size: 14px;" rowspan="3"></td>
				</tr>
				<tr id="printOrderInfoRowsTr1">
					<td align="center" style="height: 0.48cm; font-size: 14px;"></td>
					<td align="center" style="font-size: 14px;"></td>
					<td align="center" style="font-size: 14px;"></td>
					<td align="center" style="font-size: 14px;"></td>
					<td align="center" style="font-size: 14px;"></td>
					<td align="center" style="font-size: 14px;"></td>
				</tr>
				<tr id="printOrderInfoRowsTr2">
					<td align="center" style="height: 0.48cm; font-size: 14px;"></td>
					<td align="center" style="font-size: 14px;"></td>
					<td align="center" style="font-size: 14px;"></td>
					<td align="center" style="font-size: 14px;"></td>
					<td align="center" style="font-size: 14px;"></td>
					<td align="center" style="font-size: 14px;"></td>
				</tr>
				<tr>
					<td align="right"
						style="text-align: right; height: 0.48cm; font-size: 14px;">备注：</td>
					<td id="print_remark" align="left" colspan="4"
						style="font-size: 14px;">请浏览安全用气内容</td>
					<td align="right" style="text-align: right; font-size: 14px;">营业员：</td>
					<td id="print_order_operator" align="left" colspan="2"
						style="font-size: 14px;"></td>
				</tr>
				<tr>
					<td align="right"
						style="text-align: right; height: 0.48cm; font-size: 14px;">站点：</td>
					<td id="print_second_category_name" align="left"
						style="font-size: 14px;"></td>
					<td align="right" style="text-align: right; font-size: 14px;">打单：</td>
					<td id="print_print_operator" align="left" colspan="2"
						style="font-size: 14px;"></td>
					<td align="right" style="text-align: right; font-size: 14px;">送气工：</td>
					<td id="print_delivery_man" align="left" colspan="2"
						style="font-size: 14px;"></td>
				</tr>
				<tr>
					<td align="center"
						style="height: 1cm; font-size: 14px; color: white;">送出瓶号</td>
					<td align="left" colspan="3"
						style="font-size: 14px; padding-left: 5px;">&nbsp;</td>
					<td align="center" style="font-size: 14px; color: white;">回收瓶号</td>
					<td align="left" colspan="3"
						style="font-size: 14px; padding-left: 5px;">&nbsp;</td>
				</tr>
			</table>
		</div>
		<div style="clear: both;" align="center">
			<input style="padding: 5px 25px; margin-top: 25px;" type="button"
				value="打印" onclick="javascript:table();" />
		</div>
	</div>
</body>
<script type="text/javascript">
	/*
	SET_PRINT_PAGESIZE(intOrient,intPageWidth,intPageHeight,strPageName)设定纸张大小
	ADD_PRINT_TABLE(intTop,intLeft,intWidth,intHeight,strHtml)增加表格项
	 */

	/*
	var PRINT_INIT_NAME = "道滘注洲业务受理单";
	var intPageWidth = "19.1cm";
	var intPageHeight = "10.2cm";
	var intTop = "1.15cm";
	var intLeft = "2.3cm";
	var intWidth = "15.7cm";
	var intHeight = "8.2cm";
	*/
</script>
</html>