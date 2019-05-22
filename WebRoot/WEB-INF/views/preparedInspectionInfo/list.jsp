<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>待送检列表</title>

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
</head>
<body>

	<table id="tb" class="easyui-datagrid" toolbar="#toolbar" >
	</table>

	<div id="toolbar">
		<form id="toolbarForm">
			<table>
				<tr>
					<td>开始日期：</td>
					<td><input id="create_time_begin" name="create_time_begin"
						class="easyui-datebox" editable="false" /></td>
					<td>结束日期:</td>
					<td><input id="create_time_end" name="create_time_end"
						class="easyui-datebox" editable="false" />
					</td>
					<td>气瓶类型：</td>
					<td><input id="airBottleTypeTool" name="airBottleType.id"
						editable="false" class="easyui-combobox" /></td>
				</tr>
				<tr>
					<td colspan="8">
						<a id="toolbarSearch" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">查询</a>
						<a id="createInspectionOrder" href="#" class="easyui-linkbutton" 
						data-options="iconCls:'icon-add'">生成送检单</a>
						<a id="del" href="#" class="easyui-linkbutton" 
						data-options="iconCls:'icon-remove'">删除</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<script type="text/javascript"
		src="${contextPath}/js/preparedInspectionInfo/list.js"></script>
</body>
</html>