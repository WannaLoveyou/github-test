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

<body id="homeLayout" class="easyui-layout">
	
	<table id="tb" class="easyui-datagrid" toolbar="#toolbar" title="充装检查项目">
	</table>

	<div id="toolbar">
		<form id="toolbarForm">
			<table>
				<tr>
					<td><a id="toolbarSearch" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">查询</a>
						<a id="add" href="#" class="easyui-linkbutton" 
						data-options="iconCls:'icon-add'">新增</a>
						<a id="edit" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-edit'">编辑</a> 
						<a id="del" href="#" class="easyui-linkbutton" 
						data-options="iconCls:'icon-remove'">删除</a>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="mydialog" class="easyui-dialog" modal="true" closed="true" title=" "
		style="width: 450px; height: auto; top: 50px">
		<center>
			<form id="myform" action="">
				<input type="hidden" name="entityId" value="0" />
				<table style="width: 100%; height: auto; top: 50px">
					<tr>
						<td>检查项目代码：</td>
						<td><input id="fill_check_code" name="fill_check_code" 
							class="easyui-textbox" required="true" style="width: 90%; "/>
						</td>
					</tr>
					<tr>
						<td>检查项目描述：</td>
						<td><input id="fill_check_description" name="fill_check_description" 
							class="easyui-textbox" data-options="multiline:true" style="width: 90%; height:100px"/>
						</td>
					</tr>
					<tr>
						<td>检查类型：</td>
						<td><input id="before_or_after_fill" name="before_or_after_fill"
							class="easyui-combobox" required="true" editable="false" style="width: 90%; "/></td>
					</tr>
					<tr align="center">
						<td colspan="2">
							<a id="sumbitBtn" class="easyui-linkbutton">确认</a>
							<a id="closeBtn" class="easyui-linkbutton">关闭</a>
						</td>
					</tr>
				</table>
			</form>
		</center>
	</div>
	
	<script type="text/javascript"
		src="${contextPath}/js/fillCheckInfo/list.js"></script>
</body>
</html>