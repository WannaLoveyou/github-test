<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>送检单管理</title>

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
	src="${contextPath}/styles/uplod/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="${contextPath}/styles/easyui/extension/datagrid-detailview.js"></script>
</head>
<body id="homeLayout" class="easyui-layout">

	<div
		data-options="region:'east',iconCls:'icon-reload',title:'检测详情',split:true,border:false"
		style="width:200px;">
		<table id=inspectionDetailsResultTable class="easyui-datagrid">
		</table>
	</div>

	<div data-options="region:'center'"
		style="padding:5px;background:#eee;">
	<table id="tb" class="easyui-datagrid" toolbar="#toolbar">
	</table>
	</div>

	<div id="toolbar">
		<form id="toolbarForm">
			<table>
				<tr>
					<td>开始日期：</td>
					<td><input id="create_time_begin" name="create_time_begin"
						class="easyui-datebox" editable="false" />
					</td>
					<td>结束日期:</td>
					<td><input id="create_time_end" name="create_time_end"
						class="easyui-datebox" editable="false" /></td>
					</td>
					<td><a id="toolbarSearch" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">查询</a><a id="sendInspection"
						href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'">提交检测站</a><a id="refreshInspectionOrder"
						href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-reload'">刷新检测单</a><a id="confirmInspectionOrder"
						href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'">确认检测单</a><a id="initInspectionOrderData"
						href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'">同步检测单</a></td>

				</tr>
			</table>
		</form>
	</div>

	<script type="text/javascript"
		src="${contextPath}/js/inspectionOrder/list.js"></script>
</body>
</html>