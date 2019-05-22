<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>${companyName}</title>
<link href="${contextPath}/styles/index/default.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/styles/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/styles/easyui/themes/icon.css" />
<script type="text/javascript"
	src="${contextPath}/styles/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/styles/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/styles/index/outlook2.js"></script>
<script type="text/javascript"
	src="${contextPath}/styles/easyui/extension/datagrid-detailview.js"></script>




<script type="text/javascript">
	var username = '${user.username}';

	$(function() {
		checkState();
	});

	function checkState() {
		$.ajax({
			type : 'get',
			url : 'http://127.0.0.1:4454/System?Action=GetState&encode=json',
			success : function(data) {
			
			    var obj = jQuery.parseJSON(data);
			     
				if (obj.agentid == undefined || obj.agentid == null) {
					loginTelSystem();
				} else {
					if (obj.agentid != username) {
						loginAfterLogout();
					}
				}
			}
		});
	}

	function loginTelSystem() {
		$.ajax({
			type : 'get',
			url : 'http://127.0.0.1:4454/ACD?Action=login&AgentID=' + username
					+ '&encode=json',
			success : function(data) {
				$.messager.show({
					title : '提示信息',
					msg : username + '成功登陆电话系统'
				});
			}
		});
	}

	function loginAfterLogout() {
		$.ajax({
			type : 'get',
			url : 'http://127.0.0.1:4454/ACD?Action=logoff&encode=json',
			success : function(data) {
				loginTelSystem();
			}
		});
	}
	
	function logoutSystem(){
		$.ajax({
			type : 'get',
			url : 'http://127.0.0.1:4454/ACD?Action=logoff&encode=json',
			success : function(data) {
			    window.location.href= basePath + '/logout';
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {            
                window.location.href= basePath + '/logout';
            }  
		});
	}
	
	function logout(){
	    logoutSystem();
	}
	
</script>

</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div
			style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
			<img src="${contextPath}/styles/images/noscript.gif"
				alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 30px;
        background: url(${contextPath}/styles/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
		<span style="float:right; padding-right:20px;" class="head"> <a
			style="text-decoration: none;">当前用户&nbsp;:&nbsp;${user.full_name
				}&nbsp;&nbsp;&nbsp;&nbsp; </a><a id="loginOut" href="javascript:void(0)" onclick="logout()">安全退出</a></span> <span
			style="padding-left:10px; font-size: 16px; "><img
			src="${contextPath}/styles/images/blocks.gif" width="20" height="20"
			align="absmiddle" /> </span>
	</div>
	<div region="south" split="true"
		style="height: 30px; background: #D2E0F2; ">
		<div class="footer">东莞速成信息科技有限公司 www.suchkj.com</div>
	</div>
	<div region="west" split="true" title="导航菜单" style="width:210px;"
		id="west">
		<div class="easyui-accordion" fit="true" border="false">
			<!--  导航内容 -->
			<c:forEach var="child" items="${childList }">
				<lzy:menuAccordion urlPrefix="${child.url }" child="${child }" />
			</c:forEach>
		</div>

	</div>
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y:hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用" style="padding:20px;overflow: hidden;" id="home">

				<h1>欢迎登录管理系统</h1>

			</div>
		</div>
	</div>



	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>


</body>
</html>