<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, minimum-scale=1, maximum-scale=1">
<title></title>


<script type="text/javascript"
	src="${contextPath}/styles/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/styles/qrcode/jquery.qrcode.min.js"></script>


<style type="text/css">
body {
	padding: 0px;
	margin: 0px;
	font: 14px/22px '微软雅黑', Arial, "times new roman", Helvetica, sans-serif;
	width: 100%;
	background-color: white;
}

.qr_code_div {
	border: 1px solid #D6D6D6;
	width: 200px;
	height: 200px;
	margin: auto;
	margin-top: 5px;
}

.qr_button {
	width: 203px;
	padding: 10px 0px;
}
/**第一套表格*/
.user_table_1 {
	border-top: 1px solid #D6D6D6;
	border-right: 1px solid #D6D6D6;
	width: 98%;
	margin: auto;
	margin-top: 10px;
	margin-bottom: 5px;
}

.user_td_1 {
	border-bottom: 1px solid #D6D6D6;
	border-left: 1px solid #D6D6D6;
	text-align: left;
	padding: 5px;
}
/**第二套表格*/
.user_table_2 {
	border-top: 1px solid #D6D6D6;
	border-right: 1px solid #D6D6D6;
	width: 98%;
	margin: auto;
	margin-top: 10px;
	margin-bottom: 5px;
}

.user_td_2 {
	border-bottom: 1px solid #D6D6D6;
	border-left: 1px solid #D6D6D6;
	text-align: left;
	padding: 5px;
	width: 50%;
}
/**第三套表格*/
.user_table_3 {
	border-top: 1px solid #D6D6D6;
	border-right: 1px solid #D6D6D6;
	border-left: 1px solid #D6D6D6;
	width: 98%;
	margin: auto;
	margin-top: 10px;
	margin-bottom: 5px;
}

.user_td_3 {
	border-bottom: 1px solid #D6D6D6;
	text-align: left;
	padding: 5px;
	width: 50%;
}
</style>

</head>

<body>
	<table
		style="width: 100%;background-color: green;color: white;text-align: center;">
		<tr>
			<td
				style="width: 40%;padding: 8px 0px;font-size: 15px;font-weight: bold;">用户卡个人信息</td>
		</tr>
	</table>

	<table style="width: 100%;text-align: center;">
		<tr>
			<td style="text-align: center;">
				<div id="qr_code" class="qr_code_div"></div> <%-- 二维码 --%>
			</td>
		</tr>
		<tr>
			<td style="text-align: center;"><input id="refeshQRCode"
				type="button" value="刷新二维码" class="qr_button" /></td>
		</tr>
	</table>

	<input type="hidden" id="clientId" name="clientId"
		value="${clientInfo.id}" />

	<table class="user_table_1" cellspacing="0" cellpadding="0">
		<tr>
			<td class="user_td_1" style="width: 20%;">客户编码</td>
			<td class="user_td_1" style="width: 30%;">${clientInfo.client_code}</td>
		</tr>
		<tr>
			<td class="user_td_1" style="width: 20%;">用户名称</td>
			<td class="user_td_1" style="width: 30%;">${clientInfo.client_name}</td>
		</tr>
	</table>

	<table class="user_table_1" cellspacing="0" cellpadding="0">
		<tr>
			<td class="user_td_1" style="width: 20%;">所属门店</td>
			<td class="user_td_1" style="width: 30%;">${clientInfo.secondCategory.second_category_name}</td>
		</tr>
		<tr>
			<td class="user_td_1" style="width: 20%;"></td>
			<td class="user_td_1" style="width: 30%;"></td>
		</tr>

		<tr>
			<td class="user_td_1" style="width: 20%;">楼层</td>
			<td class="user_td_1" style="width: 30%;">${clientInfo.floorSubsidies.floor_subsidies_name}</td>
		</tr>
	</table>


	<table class="user_table_1" cellspacing="0" cellpadding="0">
		<tr>
			<td class="user_td_1" colspan="2">送气地址</td>
		</tr>
		<tr>
			<td class="user_td_1" colspan="2">${clientInfo.client_address}</td>
		</tr>
	</table>

	<script type="text/javascript"
		src="${contextPath}/js/wechat/my_card_code_Info_list.js"></script>
</body>
</html>
