<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>投诉处理</title>

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


	<div data-options="region:'center',title:'投诉处理'"
		style="padding:5px;background:#eee;">
		<table id="complaintInfoTable" class="easyui-datagrid" toolbar="#toolbar"
			title="" style="height:450px;width:100%;"
			data-options="striped:true,singleSelect:true,
		pagination:true,pageSize:5,pageList:[5,10,15,20]">
			<thead>
				<tr>
					<th data-options="field:'order_code',width:100">投诉编号</th>
					<th
						data-options="field:'clientInfo.client_name',width:100">用户名称</th>
					<th
						data-options="field:'airBottleType.air_bottle_specifications',width:100">客户电话号码1</th>
					<th
						data-options="field:'businessType.business_type_name',width:100">客户电话号码2</th>
					<th
						data-options="field:'deliveryType.delivery_type_name',width:100">操作员</th>

					<th data-options="field:'order_number',width:100">受理时间</th>
					<th data-options="field:'order_address',width:100">预约时间1</th>
					<th data-options="field:'order_tel_number',width:100">预约时间2</th>
					<th data-options="field:'total_amount',width:100">受理备注</th>
					<th data-options="field:'remark',width:100">投诉类型</th>
					<th
						data-options="field:'order_time',width:100">投诉内容</th>

					<th
						data-options="field:'delivery_man.full_name',width:100">地址</th>
					<th
						data-options="field:'operator.full_name',width:100">投诉状态</th>
					<th
						data-options="field:'operator.full_name',width:100">处理备注</th>
				</tr>
			</thead>
		</table>

	</div>


	<div id="toolbar">
		<form id="toolbarForm">
			<table id="tb">
				<tr>
					<td>开始日期：</td>
					<td><input id="begin_time" class="easyui-datebox"
						editable="false" name="begin_time" />
					</td>

					<td>结束日期：</td>
					<td><input id="end_time" class="easyui-datebox"
						editable="false" name="end_time" />
					</td>

					<td>投诉状态：</td>
					<td><input id="complaintInfoState" name="complaintInfoState.id"
						editable="false" class="easyui-combobox" style="width: 120px" />
					</td>

					<td>客户编码：</td>
					<td><input id="client_code" name="client_code" style="width: 120px"/>
					</td>

					<td></td>

					<td><a id="search" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">查询</a>
						<a id="deal" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'">处理</a>
					</td>

				</tr>
			</table>
		</form>
	</div>
	<div id="dealcomplaintdiv" class="easyui-dialog" title="处理信息" modal="true"
		closed="true" style="width:500px;height:auto">
		<center>
		<form id="dealcomplaintform">
		<input id="complaintId" name="complaintId" type="hidden"/>
			<table>
				<tr><td>投诉处理备注：</td>
				<td><input id="dealcomplaintNote" name="dealcomplaintNote" /></td>
				</tr>
				<tr align="center">
					<td colspan="2"><a id="dealBtn" class="easyui-linkbutton">确认</a> <a
						id="dealclose" class="easyui-linkbutton">关闭</a></td>
				</tr>
			</table>
		</form>
		</center>
	</div>>


<script type="text/javascript"
	src="${contextPath}/js/order/dispatch_complaint_list.js"></script>
</body>
</html>
