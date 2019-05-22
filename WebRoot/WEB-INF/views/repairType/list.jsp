<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>修理类型管理</title>

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

	<table id="tb" class="easyui-datagrid">
	</table>

	<div id="toolbar">
		<table>
			<tr>
				<td><a id="add" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-add'">新增</a> <a id="edit" href="#"
					class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>

					<a id="del" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-remove'">删除</a></td>
			</tr>
		</table>
	</div>

	<div id="mydialog" class="easyui-dialog" modal="true" closed="true"
		style="width:500px;height:auto;top:50px">
		<center>
			<form id="myform" action="">
				<input type="hidden" name="id" value="0" />
				<table>
					<tr>
						<td>维修类型名：</td>
						<td><input id="repair_type_name" name="repair_type_name"
							required="true" />
						</td>
					</tr>
					<tr>
						<td>维修内容描述：</td>
						<td colspan="6"><textarea style="height:100px;width: 300px;"
								id="repair_content" name="repair_content" required="true"></textarea>
						</td>
					</tr>

					<tr align="center">
						<td colspan="2"><a id="btn1" class="easyui-linkbutton">确认</a>
							<a id="btn2" class="easyui-linkbutton">关闭</a></td>
					</tr>
				</table>
			</form>
		</center>
	</div>


	<script type="text/javascript"
		src="${contextPath}/js/repairType/list.js"></script>
</body>
</html>