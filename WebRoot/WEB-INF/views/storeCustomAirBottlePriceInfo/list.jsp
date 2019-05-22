<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>门店自定义价格</title>

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

	<table id="tb" class="easyui-datagrid" toolbar="#toolbar" title="费用管理"
		style="height:auto;width:auto;">
		<thead>
		</thead>
	</table>

	<div id="toolbar">
		<form id="toolbarForm">
			<table>
				<tr>

					<td colspan="4">创建时间： <input id="create_time_begin_time"
						class="easyui-datebox" editable="false"
						name="create_time_begin_time" />-<input id="create_time_end_time"
						class="easyui-datebox" editable="false"
						name="create_time_end_time" />
					</td>

					<td>所属门店：</td>
					<td><input id="secondCategoryToolId" name="secondCategory.id"
						editable="false" class="easyui-combobox" />
					</td>



					<td>气瓶类型：</td>
					<td><input id="airBottleTypeToolId" name="airBottleType.id"
						editable="false" class="easyui-combobox" />
					</td>
					<td><a id="search" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">查询</a>
					</td>
					<td><a id="add" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'">新增</a> <a id="edit" href="#"
						class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>

						<a id="del" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-remove'">删除</a></td>
				</tr>

			</table>
		</form>
	</div>

	<div id="mydialog" class="easyui-dialog" modal="true" closed="true"
		style="width:290px;height:auto;top:50px">
		<center>
			<form id="myform" name="myform" action="">
				<input type="hidden" name="id" id="id" value="0" /> <input
					type="hidden" id="clientInfoId" name="clientInfo.id" value="0" />
				<table>

					<td>所属门店：</td>
					<td><input id="secondCategory" name="secondCategory.id"
						editable="false" class="easyui-combobox" />
					</td>
					<tr>
						<td>气瓶类型：</td>
						<td><input id="airBottleType" name="airBottleType.id"
							class="easyui-combobox" required="true" editable="false" />
						</td>
					</tr>
					<tr>
						<td>气费单价：</td>
						<td><input id="custom_price" name="custom_price" value="" />
						</td>
					</tr>
					<tr>
						<td>运气费：</td>
						<td><input id="custom_delivery_fee"
							name="custom_delivery_fee" value="" /></td>
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
		src="${contextPath}/js/storeCustomAirBottlePriceInfo/list.js"></script>
</body>
</html>