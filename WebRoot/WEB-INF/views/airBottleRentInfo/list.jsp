<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title></title>

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
	src="${contextPath}/styles/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${contextPath}/styles/lodop/LodopFuncs.js"></script>
<object id="LODOP_OB"
	classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object>
</head>

<jsp:include page="/WEB-INF/views/printStore/${projectName}-rentBottle.jsp" />

<body>
	
	<table id="tb" class="easyui-datagrid" toolbar="#toolbar">
	</table>

	<div id="toolbar">
		<form id="toolbarForm">
			<table>
				<tr>
					<td>租瓶时间：</td>
					<td><input id="begin_rent_time" class="easyui-datebox"
						editable="false" name="begin_rent_time" /></td>

					<td>至：</td>
					<td><input id="end_rent_time" class="easyui-datebox"
						editable="false" name="end_rent_time" /></td>
						
					<td>&nbsp;&nbsp;&nbsp;到期时间：</td>
					<td><input id="begin_expire_time" class="easyui-datebox"
						editable="false" name="begin_expire_time" /></td>

					<td>至：</td>
					<td><input id="end_rent_time" class="easyui-datebox"
						editable="false" name="end_rent_time" /></td>
				</tr>
				<tr>
					<td>租瓶退瓶时间：</td>
					<td><input id="begin_back_time" class="easyui-datebox"
						editable="false" name="begin_back_time" /></td>

					<td>至：</td>
					<td><input id="end_back_time" class="easyui-datebox"
						editable="false" name="end_back_time" /></td>


				</tr>
				<tr>
					<td colspan="8">客户编码 :&nbsp;&nbsp;<input id="client_code_tool"
						name="client_code" style="width: 130px" /> &nbsp;&nbsp;<a id="search"
						href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">查询</a><a id="back" href="#"
						class="easyui-linkbutton" data-options="iconCls:'icon-remove'">归还</a>
						<a id="printOrderBtn" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-print'">打印</a>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<script type="text/javascript"
		src="${contextPath}/js/airBottleRentInfo/list.js?v=2019-05-10"></script>
	<script type="text/javascript"
	src="${contextPath}/js/printStore/${projectName}-rentBottle.js"></script>
</body>
</html>