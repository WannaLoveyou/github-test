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


	<div data-options="region:'center',title:'门店自提'"
		style="padding:5px;background:#eee;">
		<table id="orderInfoTable" class="easyui-datagrid" toolbar="#toolbar"
			title="" style="height:450px;width:100%;"
			data-options="striped:true,singleSelect:true,
		pagination:true,pageSize:5,pageList:[5,10,15,20]">
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
						editable="false" name="begin_time" />
					</td>

					<td>结束日期：</td>
					<td><input id="end_time" class="easyui-datebox"
						editable="false" name="end_time" />
					</td>
				</tr>
				<tr>
					<td>业务类型：</td>
					<td><input id="businessType" name="businessType.id"
						editable="false" class="easyui-combobox" />
					</td>

					<td>客户编码：</td>
					<td><input id="client_code" name="client_code"
						class="easyui-numberbox" />
					</td>

					<td><a id="search" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">查询</a>
					</td>

					<td><a id="fill" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'">填充气瓶</a></td>

					<td><a id="cancelOrder" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-cancel'">撤销订单</a>
					</td>
				</tr>

			</table>
		</form>
	</div>

	<div id="fillDialog" class="easyui-dialog" modal="true" closed="true"
		style="width:280px;height:auto;top:50px">
		<center>
			<form id="fillForm" action="">
				<input type="hidden" name="id" value="0" />
				<table id=fillTable>
					<tr>
						<td>用户编码：</td>
						<td><input name="card_code" />
						</td>
					</tr>
					<tr>

						<td>订单数量：</td>
						<td><input name="order_number" />
						</td>
					</tr>

					<tr align="center">
						<td colspan="2"><a id="sumbitFill" class="easyui-linkbutton">确认</a>
							<a id="closeFillDialog" class="easyui-linkbutton">关闭</a>
						</td>
					</tr>
				</table>
			</form>
		</center>
	</div>

	<div id="cancelMsgDialog" class="easyui-dialog" title="订单撤销"
		style="width:300px;height:150px;padding:10px" closed="true"
		modal="true">
		<table>
			<tr>
				<td>撤销备注：</td>
				<td colspan=3><textarea id="cancelMsg" name="cancelMsg"
						style="width:100%;"></textarea>
				</td>
			</tr>
			<tr align="center">
				<td colspan="6"><a id="sumbitCancelMsgDialog"
					class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确认</a> <a
					id="closeCancelMsgDialog" class="easyui-linkbutton"
					data-options="iconCls:'icon-no'">关闭</a>
				</td>
			</tr>
		</table>
	</div>

</body>



<script type="text/javascript"
	src="${contextPath}/js/order/pickup_in_stores_list.js"></script>
</body>
</html>
