<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1">
<title></title>
<style type="text/css">
html,body{ padding:0px; margin:0 auto; font:12px/22px '微软雅黑', Arial, "times new roman", Helvetica, sans-serif;background-color: #F1F1F1;height: 90%;}
img{margin-right: 10px;vertical-align: middle;padding: 3px;max-height: 30px;max-width: 30px;}
.content_div{width: 100%;font-size: 16px;padding: 8px 0px;}
.menu_table{padding-left: 5%;}
</style>
 
</head>

<body>
	
	<div style="width: 100%;background-color: white;margin-top: 25px;padding: 5px 0px;">
	<div class="content_div">
	
		<table style="width: 100%;">
			<tr>
				<td style="text-align: center;">
					<img src="${contextPath}/styles/icon/logo.png" style="max-height: 60px;max-width: 60px;"/>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;">
					虎门能源欢迎您
				</td>
			</tr>
		</table>
		</a>
	</div>
	</div>
	
	<c:forEach var="clientInfo" items="${list}"> 
	<div style="width: 100%;background-color: white;margin-top: 25px;">
	<div class="content_div"">
	<table class="menu_table" onclick="location.href='${contextPath}/weiXin/selectClinetInfo?clientId=${clientInfo.id}';">
		<tr>
			<td><img src="${contextPath}/styles/icon/user.png"/></td>
			<td>${clientInfo.client_name}</td>
		</tr>
	</table>
	</div>
	</div>
	<div style="border-bottom: 1px solid #F1F1F1;"></div>
	<div style="width: 100%;background-color: white;">
	<div class="content_div"">
	<table class="menu_table" onclick="location.href='${contextPath}/weiXin/selectClinetInfo?clientId=${clientInfo.id}';">
		<tr>
			<td><img src="${contextPath}/styles/icon/home.png"/></td>
			<td>${clientInfo.client_address}</td>
		</tr>
	</table>
	</div>
	</div>
    </c:forEach>
    
</body>

</html>
