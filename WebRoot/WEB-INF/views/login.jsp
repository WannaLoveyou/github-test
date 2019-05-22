<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录 -  ${companyName}</title>
<link href="${contextPath}/styles/login/login.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/styles/login/iconfont/iconfont.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<form id="myform" name="myform" action="" method="post">
		<div class="sc-disflex" style="position: absolute;top: 50%;left: 50%;transform: translate(-50%,-50%);">
			<div id="images" style="margin-right: 6.9rem;">
				<img src="${contextPath}/styles/login/bg.png" class="img" />
			</div>
			<div class="sc-radius sc-bg-fff sc-pl26 sc-pr26">
				<div class="sc-pt15 sc-pb15 sc-borbottom-bfbfbf sc-fs30 sc-fs-333">钢瓶管理系统</div>
				<div>
					<h2 class="errorMsg" style="color: red;display: inline;font-size: 0.9rem;">${ msg }</h2>
				</div>
				<div style="padding-top: 0.57rem;">
					<div class="sc-disflex input_control sc-align">
						<i class="iconfont icon-wode_1"></i>
						<input type="text" class="text" placeholder="用户名" name="username" />
					</div>
					<div class="sc-disflex input_control sc-align">
						<i class="iconfont icon-mima1"></i>
						<input type="password" class="text" placeholder="密码" name="password" />
					</div>
					
					<button type="submit" class="login">登录</button>
					<div class="exegesis">技术支持：速成信息科技</div>
				</div>
			</div>
				
		</div>
	</form>	
	<script type="text/javascript">
		var loginUrl = basePath;
	
		history.forward();
		loginUrl += "/login/doLogin";
	
		document.myform.action = loginUrl;
	</script>
</body>
</html>