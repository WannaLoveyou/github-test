<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, minimum-scale=1, maximum-scale=1">
<title>道滘珠洲石油气有限公司管理系统</title>
<style type="text/css">
body {
	padding: 0px;
	margin: 0px;
	font: 12px/22px '微软雅黑', Arial, "times new roman", Helvetica, sans-serif;
	background-color: #F1F1F1;
}

.text {
	height: 50px;
	width: 100%;
	border-radius: 4px;
	border: 1px;
	font-size: 16px;
	border: 1px solid #A9A9A9;
	text-align: center;
	color: #868686;
}

.button {
	height: 50px;
	width: 100%;
	border-radius: 4px;
	border: 1px;
	background-color: orange;
	margin-top: 10px;
	font-size: 18px;
	color: white;
	font-weight: bold;
}

td {
	text-align: center;
	padding: 0px 10px;
	width: 100%;
}
/**
div{text-align: left;font-size: 18px;width: 100%;color: #868686;}
*/
</style>
</head>

<body style="width: 100%;">
	<form id="login_form" action="${contextPath}/mobile/weiXin/clinetInfoIndex"
		method="post">
		<input type="hidden" name="platformType" value="mobile" />
		<table style="width: 100%;">
			<tr>
				<td style="font-size: 20px;height: 100px;color: #676767;">道滘珠洲石油气有限公司管理系统</td>
			</tr>
			<tr>
				<td>
					<div
						style="text-align: left;font-size: 16px;width: 100%;color: #868686;">手机号码</div>
					<input class="text" type="text" name="account" maxlength="11"
					value="" /></td>
			</tr>
			<tr>
				<td>
					<div
						style="text-align: left;font-size: 16px;width: 100%;color: #868686;margin-top: 10px;">密码</div>
					<input class="text" type="password" name="password" maxlength="30"
					value="" /></td>
			</tr>
			<tr>
				<td><input class="button" type="submit" value="普通登录" />
					<div
						style="float: left;color: red;font-size: 16px;margin-top: 10px;">${errorMsg}</div>
				</td>
			</tr>
			<%--
		<tr>
			<td>
			<span style="color: red;float: left;font-size: 16px;padding-top: 16px;">${wrong_info}</span>
			<div style="float: right;padding: 17px 0px 0px 0px;font-size: 16px;color: #868686;">&nbsp;微信授权登录</div>
			<div style="float: right;">
			<img src="/style/image/mobile/weixin_logo.png" style="max-height: 19px;max-width: 19px;margin-top: 18px;"/>
			</div>
			</td>
		</tr>
		--%>
			<tr>
				<td style="padding-top: 10px;">Copyright &copy; 2016. <a
					href="#" target="_blank" style="text-decoration: none;"></a>
					All rights reserved.</td>
			</tr>
		</table>
	</form>

	<script type="text/javascript"
		src="${contextPath}/js/wechat/weixin_login.js"></script>
</body>
</html>
