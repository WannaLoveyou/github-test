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
	src="${contextPath}/styles/exportexcel/LodopFuncs.js"></script>
<script type="text/javascript"
	src="${contextPath}/styles/easyui/extension/datagrid-detailview.js"></script>
</head>


<body id="homeLayout" class="easyui-layout">
	<div data-options="region:'center',title:''"
		style="padding:5px;background:#eee; ">
		<div id="reportTableId">
			<table id="reportTable" class="easyui-datagrid" toolbar="#toolbar"
				title="" style="height:500;width:auto;"
				data-options="fitColumns:true,nowrap:false,rownumbers:true,striped:true,pagination:true,pageSize:50,pageList:[20,50,100,200]"">
				<thead>
					<tr>

					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div id="toolbar">
		<form id="toolbarForm" method="post">
			<table>
				<tr>

					<td>所属门店：</td>
					<td><input id="secondCategoryToolId" name="secondCategory.id"
						editable="false" class="easyui-combobox" /></td>
					<td>气瓶类型：</td>
					<td><input id="airBottleTypeToolId" name="airBottleType.id"
						editable="false" class="easyui-combobox" /></td>

					<td>开始日期：</td>
					<td><input id="begin_time" name="begin_time"
						class="easyui-datebox" editable="false" /></td>

					<td>结束日期：</td>
					<td><input id="end_time" name="end_time"
						class="easyui-datebox" editable="false" /></td>
				</tr>
				<tr>
					<td>回瓶周期(天)：</td>
					<td><input id="no_back_time" name="no_back_time" />

						<td>订单周期(天)：</td>
						<td><input id="order_cycle" name="order_cycle" />
					</td>
					<td>配送工：</td>
					<td><input id="delivery_man" name="delivery_man" />
					<td>客户编码：</td>
					<td><input id="client_code" name="client_code" />
						<td><a id="search" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'">查询</a></td>

						<td><a id="exportExcel" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-save'">导出</a>
					</td>
				</tr>
				<tr>
					<td colspan="8">（回瓶周期表示气瓶最后一次轨迹离当前时间的间隔天数，订单周期表示用户最后一次订气离当前时间的间隔天数，若用户订单周期超过输入订单周期则当条记录为红色）</td>
				</tr>
			</table>
		</form>
	</div>


	<script type="text/javascript"
		src="${contextPath}/js/bottleProcess/air_bottle_back_long_time_detail_report_list.js"></script>
</body>


</html>