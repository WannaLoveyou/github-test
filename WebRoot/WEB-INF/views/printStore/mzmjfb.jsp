<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title></title>
</head>
<body>

	<div id="print_div" class="print_div">
		<table cellpadding="0" cellspacing="0" align="left">
			<tr>
				<td align="center"
					style="width: 1.71cm; font-size: 14px; height: 0.7cm; color: white;">电脑序号</td>
				<td id="print_computer_no" align="left"
					style="width: 2cm; font-size: 14px; padding-left: 5px;"></td>
				<td align="center" style="width: 2.21cm; font-size: 14px;"></td>
				<td align="left" style="width: 0.6cm; font-size: 14px;"></td>
				<td align="left" style="width: 1cm; font-size: 14px;"></td>
				<td align="center" style="width: 2.22cm; font-size: 14px;"></td>
				<td align="center"
					style="width: 1.6cm; font-size: 14px; color: white;">时间</td>
				<td id="print_now_time" align="left"
					style="width: 3.5cm; font-size: 14px; padding-left: 15px;"></td>
			</tr>
			<tr>
				<td align="center"
					style="font-size: 14px; height: 0.7cm; color: white;">客户名称</td>
				<td id="print_client_name" align="left" colspan="2"
					style="font-size: 14px; padding-left: 5px;"></td>
				<td align="center" colspan="2"
					style="font-size: 14px; color: white;">客户编号</td>
				<td id="print_client_code" align="left"
					style="font-size: 14px; padding-left: 5px;"></td>
				<td align="center" style="font-size: 14px; color: white;">结款方式</td>
				<td id="print_pay_type" align="left"
					style="font-size: 14px; padding-left: 5px;">现结</td>
			</tr>
			<tr>
				<td align="center"
					style="font-size: 14px; height: 0.7cm; color: white;">客户电话</td>
				<td id="print_client_tel_number" align="left" colspan="5"
					style="font-size: 14px; padding-left: 5px;"></td>
				<td align="center" style="font-size: 14px; color: white;">安检提示</td>
				<td id="print_security_info" align="left"
					style="font-size: 14px; padding-left: 5px;">已安检</td>
			</tr>
			<tr>
				<td align="center"
					style="font-size: 14px; height: 0.7cm; color: white;">客户地址</td>
				<td id="print_order_address" align="left" colspan="5"
					style="font-size: 14px; padding-left: 5px;"></td>
				<td align="center" style="font-size: 14px; color: white;">备注</td>
				<td id="print_remark" align="left"
					style="font-size: 14px; padding-left: 5px;"></td>
			</tr>
			<tr>
				<td align="center" colspan="2"
					style="font-size: 14px; height: 0.7cm; color: white;">商务（业务）名称</td>
				<td align="center" style="font-size: 14px; color: white;">规格型号</td>
				<td align="center" style="font-size: 14px; color: white;">数量</td>
				<td align="center" style="font-size: 14px; color: white;">单价</td>
				<td align="center" style="font-size: 14px; color: white;">金额</td>
				<td align="center" style="font-size: 14px; color: white;">配送部门</td>
				<td id="print_deliver_department" align="left"
					style="font-size: 14px; padding-left: 5px;"></td>
			</tr>
			<tr>
				<td id="print_business_name" align="center" colspan="2"
					style="font-size: 14px; height: 0.7cm;"></td>
				<td id="print_air_bottle_specifications" align="center"
					style="font-size: 14px;"></td>
				<td id="print_count" align="center" style="font-size: 14px;"></td>
				<td id="print_price" align="center" style="font-size: 14px;"></td>
				<td id="print_total_price" align="center"
					style="font-size: 14px;"></td>
				<td align="center" style="font-size: 14px; color: white;">制单人员</td>
				<td id="print_print_operator" align="left"
					style="font-size: 14px; padding-left: 5px;"></td>
			</tr>
			<tr id="printOrderInfoRowsTr1">
				<td align="center" colspan="2"
					style="font-size: 14px; height: 0.7cm;"></td>
				<td align="center"
					style="font-size: 14px;"></td>
				<td align="center" style="font-size: 14px;"></td>
				<td align="center" style="font-size: 14px;"></td>
				<td align="center"
					style="font-size: 14px;"></td>
				<td align="center" style="font-size: 14px; color: white;">运送人员</td>
				<td id="print_delivery_man" align="left"
					style="font-size: 14px; padding-left: 5px;"></td>
			</tr>
			<tr id="printOrderInfoRowsTr2">
				<td align="center" colspan="2"
					style="font-size: 14px; height: 0.7cm;"></td>
				<td align="center"
					style="font-size: 14px;"></td>
				<td align="center" style="font-size: 14px;"></td>
				<td align="center" style="font-size: 14px;"></td>
				<td align="center"
					style="font-size: 14px;"></td>
				<td align="center" rowspan="2"
					style="font-size: 14px; color: white;">客户确认</td>
				<td align="left" style="font-size: 14px; color: white;">（单价已含送气费）</td>
			</tr>
			<tr>
				<td align="center"
					style="font-size: 14px; height: 0.7cm; color: white;">合计：</td>
				<td id="print_total_info" align="right" colspan="5"
					style="font-size: 14px; padding-right: 25px;"></td>
				<td align="left" style="font-size: 14px; padding-left: 5px;"></td>
			</tr>
			<tr>
				<td align="center"
					style="font-size: 14px; height: 0.7cm; color: white;">本次重瓶</td>
				<td id="print_this_time_heavy_bottle" align="left" colspan="7"
					style="font-size: 14px;"></td>
			</tr>
			<tr>
				<td align="center"
					style="font-size: 14px; height: 0.7cm; color: white;">应回空瓶</td>
				<td id="print_return_empty_bottle" align="left" colspan="7"
					style="font-size: 14px;"></td>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript">
	/*
		SET_PRINT_PAGESIZE(intOrient,intPageWidth,intPageHeight,strPageName)设定纸张大小
		ADD_PRINT_TABLE(intTop,intLeft,intWidth,intHeight,strHtml)增加表格项
	 */

	var PRINT_INIT_NAME = "";
	var intPageWidth = "19cm";
	var intPageHeight = "9.5cm";
	var intTop = "1.05cm";
	//var intLeft = "1.5cm"; // final
	var intLeft = "2.5cm";
	var intWidth = "19cm";
	var intHeight = "9.5cm";
</script>
</html>