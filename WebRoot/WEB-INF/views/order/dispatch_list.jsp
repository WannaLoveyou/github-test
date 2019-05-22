<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>充气派工</title>

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


	<div data-options="region:'center',title:'订单派工'"
		style="padding:5px;background:#eee;">
		<table id="orderInfoTable" class="easyui-datagrid" toolbar="#toolbar"
			title="" style="height:450px;width:100%;"
			data-options="striped:true,singleSelect:true,
		pagination:true,pageSize:20,pageList:[5,10,20,50]">
			<thead>
			</thead>
		</table>

	</div>


	<div id="toolbar">
		<form id="toolbarForm">
			<table>
				<tr>
					<td>订单预约日期：</td>
					<td><input id="begin_appointment_time" class="easyui-datebox"
						editable="false" name="begin_appointment_time" /></td>

					<td>订单预约日期：</td>
					<td><input id="end_appointment_time" class="easyui-datebox"
						editable="false" name="end_appointment_time" /></td>
					<td>订单下单日期：</td>
					<td><input id="begin_time" class="easyui-datebox"
						editable="false" name="begin_time" /></td>

					<td>订单下单日期：</td>
					<td><input id="end_time" class="easyui-datebox"
						editable="false" name="end_time" /></td>
				</tr>
				<tr>
					<td>气瓶类型：</td>
					<td><input id="airBottleType" name="airBottleType.id"
						editable="false" class="easyui-combobox" /></td>
					<td>配送方式：</td>
					<td><input id="deliveryType" name="deliveryType.id"
						editable="false" class="easyui-combobox" /></td>
					<td>订单状态：</td>
					<td><input id="orderInfoState" name="orderInfoState.id"
						editable="false" class="easyui-combobox" /></td>
					<td>付款状态：</td>
					<td><input id="payState" name="payState.id"
						editable="false" class="easyui-combobox" /></td>
				</tr>
				<tr>
					<td>订单数量：</td>
					<td><input id="order_number" name="order_number" />
					</td>
					<td>配送费：</td>
					<td><input id="delivery_fee" name="delivery_fee" />
					</td>

					<td>下单员姓名：</td>
					<td><input id="operator" name="operator" />
					</td>

					<td>配送工姓名：</td>
					<td><input id="delivery_man" name="delivery_man" />
					</td>
				</tr>
				<tr>
					<td>客户编码：</td>
					<td><input id="client_code" name="client_code"/></td>
					<td>客户手机号：</td>
					<td><input id="tel_number" name="tel_number"
						class="easyui-numberbox" /></td>


					<td colspan="3"><a id="search" href="#"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			
					</td>

				</tr>


			</table>
		</form>
	</div>


<script type="text/javascript"
	src="${contextPath}/js/order/dispatch_list.js"></script>
</body>
</html>
