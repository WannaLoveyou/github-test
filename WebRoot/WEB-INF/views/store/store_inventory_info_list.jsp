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
	src="${contextPath}/styles/easyui/extension/datagrid-detailview.js"></script>
</head>


<body id="homeLayout" class="easyui-layout">
	<div data-options="region:'center',title:''"
		style="padding:5px;background:#eee;">
		<table id="storeInventoryTable" class="easyui-datagrid"
			toolbar="#toolbar" title="" style="height:500;width:auto;"
			data-options="fitColumns:true,nowrap:false,rownumbers:true,striped:true">
			<thead>
				<tr>

				</tr>
			</thead>
		</table>

	</div>



	<div id="toolbar">
		<form id="toolbarForm" method="post">
			<table>
				<tr>

					<td>所属门店：</td>
					<td><input id="secondCategoryTool" name="secondCategory.id"
						editable="false" class="easyui-combobox" /></td>

					<td><a id="search" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">查询</a></td>


				</tr>
			</table>
		</form>
	</div>


	<div id="BottleDialog" class="easyui-dialog" title="" modal="true"
		closed="true" style="width:500px;height:auto;top:100px">
		<center>
			<form id="BottleForm" action="">
				<table id="tb1">

				</table>
				<tr align="center">
					<td colspan="2"><a id="BottleSumbit" class="easyui-linkbutton">确认</a>
						<a id="closeBottleDialog" class="easyui-linkbutton">关闭</a></td>
				</tr>
			</form>
		</center>
	</div>

	<script type="text/javascript"
		src="${contextPath}/js/store/store_inventory_info_list.js"></script>
</body>


</html>