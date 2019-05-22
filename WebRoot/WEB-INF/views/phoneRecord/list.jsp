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

</head>
<body>
	<table id="tb" class="easyui-datagrid" toolbar="#toolbar"
		title="来电记录" style="height:auto;width:auto;">
	</table>
	<div id="toolbar">
		<form id="toolbarForm">
			<table>
				<tr>
					<td>电话号码：</td>
					<td><input id="phoneNumberTool" name="phoneNumber" 
						class="easyui-numberbox" /></td>
					<td>来电时间：</td>
					<td><input id="begin_call_in_time" class="easyui-datebox"
						editable="false" name="begin_call_in_time" /></td>
					<td>至</td>
					<td><input id="end_call_in_time" class="easyui-datebox"
						editable="false" name="end_call_in_time" /></td>	
					<td>记录状态：</td>
					<td><input id="recordStateTool" name="phoneRecordState"
						editable="false" class="easyui-combobox" /></td>
				</tr>
				<tr>
					<td colspan="6"><a id="search" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">查看</a>
						<a id="del" href="#" class="easyui-linkbutton" 
						data-options="iconCls:'icon-remove'">删除</a>
						<a id="callBack" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-edit'">设置回访</a>
						<a id="startRecordModule" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-edit'">开启记录未接来电模式</a>
						<a id="shutdownRecordModule" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-edit'">关闭记录未接来电模式</a>
					</td>
					<td id="recordModuleDisplay" style="font-size: 20px;color:red;" colspan="4"></td>
				</tr>
			</table>
		</form>
	</div>

	<script type="text/javascript" 
		src="${contextPath}/js/phoneRecord/list.js"></script>
</body>
</html>