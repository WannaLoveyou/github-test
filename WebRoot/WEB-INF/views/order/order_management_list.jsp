<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@ taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
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
<script type="text/javascript"
	src="${contextPath}/styles/lodop/LodopFuncs.js"></script>

<object id="LODOP_OB"
	classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object>
<style>
	tr td{padding: 0px !important;text-align: left !important;}
</style>
</head>

<jsp:include page="/WEB-INF/views/printStore/${projectName}.jsp" />

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
				data-options="iconCls:'icon-ok'">确认</a> 
				<a id="dispatchCancelBtn" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'">取消</a> 

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


	<div data-options="region:'center',title:'订单管理'"
		style="padding:5px;background:#eee;">
		<table id="orderInfoTable" class="easyui-datagrid" toolbar="#toolbar"
			title="" style="height:450px;width:100%;"
			data-options="striped:true,singleSelect:true,
		pagination:true,pageSize:50,pageList:[10,20,50,100]">
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
					<td>订单预约日期：</td>
					<td><input id="begin_appointment_time" class="easyui-datebox"
						editable="false" name="begin_appointment_time" />
					</td>

					<td>订单预约日期：</td>
					<td><input id="end_appointment_time" class="easyui-datebox"
						editable="false" name="end_appointment_time" />
					</td>
					<td>订单下单日期：</td>
					<td><input id="begin_time" class="easyui-datebox"
						editable="false" name="begin_time" />
					</td>

					<td>订单下单日期：</td>
					<td><input id="end_time" class="easyui-datebox"
						editable="false" name="end_time" />
					</td>
				</tr>
				<tr>
					<td colspan="8">气瓶类型： <input id="airBottleType"
						name="airBottleType.id" editable="false" class="easyui-combobox"
						style="width:100px;" />&nbsp;&nbsp; 配送方式： <input
						id="deliveryType" name="deliveryType.id" editable="false"
						class="easyui-combobox" style="width:120px;" /> &nbsp;&nbsp;订单状态：
						<input id="orderInfoState" name="orderInfoState.id"
						editable="false" class="easyui-combobox" style="width:130px;" />
						&nbsp;&nbsp;付款方式： <input id="payType" name="payType.id"
						editable="false" class="easyui-combobox" style="width:120px;" />&nbsp;&nbsp;付款状态：
						<input id="payState" name="payState.id" editable="false"
						class="easyui-combobox" style="width:120px;" />
					</td>
				</tr>
				<tr>
					<td colspan="8">订单数量：<input id="order_number"
						name="order_number" style="width:50px;" />&nbsp;&nbsp;单价：<input
						id="price" name="price" style="width:50px;" /> &nbsp;&nbsp;下单员姓名：
						<input id="operator" name="operator" style="width:80px;" />&nbsp;&nbsp;配送工姓名：
						<input id="delivery_man" name="delivery_man" style="width:80px;" />&nbsp;&nbsp;客户手机号：<input
						id="tel_number" name="tel_number" style="width:120px;" />&nbsp;&nbsp;订单编号：<input
						id="order_code" name="order_code" style="width:80px;" /></td>
				</tr>
				<tr>
					<td colspan="8">客户编码：<input id="client_code"
						name="client_code" style="width:90px;" /> <shiro:hasPermission
							name="OrderManger:invoice">
						开票次数：<input id="invoice_times" name="invoice_num"
								style="width:50px;" />
						</shiro:hasPermission> <a id="search" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">查询</a> <a id="addRemark"
						href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'">补充备注</a> <a id="cancelOrder"
						href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-cancel'">撤销订单</a> <a id="dispatchBtn"
						href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'">派工</a> <shiro:hasPermission name="OrderManger:dispatchDeliveryManByNoLimit"><a id="dispatchNoLimitBtn"
						href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'">派工(不限门店)</a></shiro:hasPermission><a id="cancelDispatch"
						href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-cancel'">撤销派工</a> <a
						id="printOrderBtn" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-print'">打印</a><a id="printCanceBtn"
						href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-remove'">打印作废</a> <shiro:hasPermission
							name="BusinessModule:nonQrCodeOperate">
							<a id="addNonQrcodeHeavyBottle" href="#"
								class="easyui-linkbutton" data-options="iconCls:'icon-add'">重瓶录入</a>
							<a id="addNonQrcodeEmptyBottle" href="#"
								class="easyui-linkbutton" data-options="iconCls:'icon-add'">空瓶录入</a>
						</shiro:hasPermission> <a id="finishPartsOrder" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'">零件单回单</a>
				</tr>
				<tr>
					<td colspan="8"><shiro:hasPermission
							name="OrderManger:forceCacnel">
							<a id="forceCacnel" href="#" class="easyui-linkbutton"
								data-options="iconCls:'icon-cancel'">强制撤单</a>
						</shiro:hasPermission> <shiro:hasPermission name="OrderManger:exportOrder">
							<a id="exportExcel" href="#" class="easyui-linkbutton"
								data-options="iconCls:'icon-add'">导出</a>
						</shiro:hasPermission> <shiro:hasPermission name="OrderManger:editOrderStore">
							<a id="editOrderStore" href="#" class="easyui-linkbutton"
								data-options="iconCls:'icon-edit'">更改订单门店</a>
						</shiro:hasPermission> <shiro:hasPermission name="OrderManger:returnQRcode">
							<a id="returnQRcode" href="#" class="easyui-linkbutton"
								data-options="iconCls:'icon-cancel'">撤销订单二维码瓶</a>
						</shiro:hasPermission> <shiro:hasPermission name="OrderManger:invoice">
							<a id="invoice" href="#" class="easyui-linkbutton"
								data-options="iconCls:'icon-add'">开发票</a>
						</shiro:hasPermission> <shiro:hasPermission name="OrderManger:cancelInvoice">
							<a id="cancelInvoice" href="#" class="easyui-linkbutton"
								data-options="iconCls:'icon-cancel'">撤销发票</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="OrderManger:cancelPickupInWH">
							<a id="cancelPickupInWH" href="#" class="easyui-linkbutton"
								data-options="iconCls:'icon-cancel'">撤销仓库自提订单二维码瓶</a>
						</shiro:hasPermission>
						<!-- <a id="testPrint" href="#" class="easyui-linkbutton"
							onclick="testPrint()"	data-options="iconCls:'icon-print'">TestPrint</a> -->
					</td>
				</tr>
				<tr>
				<td colspan="20">    <font color="#FF0000">（备注：蓝色字体-含零件单 ,红色字体-催气单）</font> </td>
				</tr>
			</table>
		</form>
	</div>


	<div id="editOrderStoreDialog" class="easyui-dialog" title="更改订单门店"
		style="width:300px;height:120px;padding:10px" closed="true"
		modal="true">
		<form id="editOrderStoreForm" method="post">
			<table>
				<tr>
					<td>所属门店：</td>
					<td><input id="editOrderStoreSecondCategoryToolId"
						name="secondCategory.id" editable="false" class="easyui-combobox"
						required="true" /></td>
				</tr>
				<tr align="center">
					<td colspan="6"><a id="sumbitEditOrderStoreDialog"
						class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a>
						<a id="closeEditOrderStoreDialog" class="easyui-linkbutton"
						data-options="iconCls:'icon-no'">关闭</a></td>
				</tr>
			</table>
		</form>
	</div>


	<div id="cancelMsgDialog" class="easyui-dialog" title="订单撤销"
		style="width:300px;height:150px;padding:10px" closed="true"
		modal="true">
		<table>
			<tr>
				<td>撤销备注：</td>
				<td colspan=3><textarea id="cancelMsg" name="cancelMsg"
						style="width:100%;"></textarea></td>
			</tr>
			<tr align="center">
				<td colspan="6"><a id="sumbitCancelMsgDialog"
					class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a> <a
					id="closeCancelMsgDialog" class="easyui-linkbutton"
					data-options="iconCls:'icon-no'">关闭</a></td>
			</tr>
		</table>
	</div>
<script type="text/javascript"
	src="${contextPath}/js/order/order_mangement_list.js?v-2019-01-30"></script>
<script type="text/javascript"
	src="${contextPath}/js/printStore/${projectName}.js?v=2019-01-22"></script>
<script type="text/javascript"
	src="${contextPath}/js/utils/money_transform_chinese.js"></script>
</body>

</html>
