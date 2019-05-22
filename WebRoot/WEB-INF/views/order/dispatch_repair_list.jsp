<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>维修派工</title>

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

	<div data-options="region:'south',title:'',split:true"
		style="height:50px;padding-top: 10px;"></div>

	<div
		data-options="region:'east',iconCls:'icon-reload',title:'East',split:true,border:false"
		style="width:100px;">
		<table id=deliveryManTable class="easyui-datagrid"
			style="height: auto"
			data-options="fitColumns:true,striped:false,nowrap:false,singleSelect:true">

		</table>
		<center>
			<a id="dispatch" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'">派工</a>


		</center>
	</div>



	<div data-options="region:'west',title:'',split:true,border:false"
		style="width:130px;">
		<table id=secondCategoryTable class="easyui-datagrid"
			style="height: 233px"
			data-options="fitColumns:true,striped:false,nowrap:false,singleSelect:true,
		url:'${contextPath }/secondCategory/getMyList',method:'get'">
			<thead>
				<tr>
					<th
						data-options="field:'second_category_name',width:100,align:'center'">部门</th>
				</tr>
			</thead>
		</table>

		<table id=thirdCategoryTable class="easyui-datagrid"
			style="height: 233px"
			data-options="fitColumns:true,striped:false,nowrap:false,singleSelect:true">
			<thead>
				<tr>
				</tr>
			</thead>
		</table>
	</div>


	<div data-options="region:'center',title:'维修派工'"
		style="padding:5px;background:#eee;">
		<table id="repairInfoTable" class="easyui-datagrid" toolbar="#toolbar"
			title="" style="height:450px;width:100%;"
			data-options="striped:true,singleSelect:true,
		pagination:true,pageSize:20,pageList:[10,20,50]">
			<thead>
				<tr>
					
				</tr>
			</thead>
		</table>

	</div>


	<div id="toolbar">
		<form id="toolbarForm">
			<table>
				<tr>
					<td>开始日期：</td>
					<td><input id="begin_time" class="easyui-datebox"
						editable="false" name="begin_time" /></td>

					<td>结束日期：</td>
					<td><input id="end_time" class="easyui-datebox"
						editable="false" name="end_time" /></td>
				</tr>
				<tr>
					<td>维修状态：</td>
					<td><input id="repairInfoState" name="repairInfoState.id"
						editable="false" class="easyui-combobox" /></td>

					<td>客户编码：</td>
					<td><input id="client_code" name="client_code" /></td>

					<td><a id="search" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">查询</a></td>
					<td><a id="cancelOrder" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-cancel'">撤销订单</a>
					</td>
					<td><a id="finishOrder" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'">完成订单</a>
					</td>
					<shiro:hasPermission name="DispatchRepair:dispatchDeliveryMan">
						<td><a id="dispatchDeliveryMan" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'">派给送气工</a></td>
					</shiro:hasPermission>
					<shiro:hasPermission name="DispatchRepair:dispatchSafetyTechnologyDepartment">
						<td><a id="dispatchSafetyTechnologyDepartment" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'">派给安技部</a></td>
					</shiro:hasPermission>
				</tr>
			</table>
		</form>
	</div>

	<script type="text/javascript"
		src="${contextPath}/js/order/dispatch_repair_list.js"></script>
</body>
</html>
