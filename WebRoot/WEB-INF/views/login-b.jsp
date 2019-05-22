<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录 -  ${companyName}</title>


<link href="${contextPath}/styles/login/style.css" rel="stylesheet" type="text/css">
<head>

</head>
<body class="login">
<form id="myform" name="myform" action="" method="post">
<div class="login_m">
	<div class="login_logo">
		<img class="img" src="${contextPath}/styles/login/logo1.png" width="300" height="100">
		<div class="sys">钢瓶管理系统</div>
	</div>
	<div class="login_boder" id="moz-content">
		<div class="login_padding">
		<div style="display: flex;justify-content: space-between;max-width: 295px">
			<h2 style="display: inline;">用户名</h2><h2 class="errorMsg" style="color: red;display: inline;">${msg }</h2>
			</div>
			<label>
				<input type="text" name="username" id="username" class="txt_input txt_input2"  >
			</label>
			<h2>密码</h2>
			<label>
				<input type="password" name="password" id="password" class="txt_input"  >
			</label>
			<p class="forgot"></p>
			<div class="rem_sub">
				<div class="rem_sub_l">
					<input type="checkbox" name="checkbox" id="save_me">
					<label for="checkbox">记住</label>
				</div>
				<label>
					<input type="submit" class="sub_button" name="button" id="button" value="登录" style="opacity: 0.7;">
				</label>
			</div>
		</div>
	</div><!--login_boder end-->
</div><!--login_m end-->
<div class="foot">
	<div class="foot_left">技术指导:东莞市质量技术监督局</div>
	<div class="foot_right">技术研发:东莞市速成信息科技有限公司</div>
</div>
</form>

<script type = "text/javascript">

    var loginUrl = basePath;
    

    loginUrl+="/login/doLogin";
        
    document.myform.action= loginUrl;
    
</script>
</body>
</html>