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
 	<div data-options="region:'center',title:''"
		style="padding:5px;background:#eee; ">
		<div id="reportTableId">
			<table id="reportTable" class="easyui-datagrid" toolbar="#toolbar"
				title="" style="height:500;width:auto;"
				data-options="fitColumns:true,nowrap:false,rownumbers:true,striped:true">
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
					<td>开始日期：</td>
					<td><input id="create_time_begin_time" name="create_time_begin_time"
						class="easyui-datebox" editable="false" /></td>
					<td>结束日期：</td>
					<td><input id="create_time_end_time" name="create_time_end_time"
						class="easyui-datebox" editable="false" /></td>
					<td><a id="showReport" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'" onclick="showClientCoordinatesMap()">展示</a></td>	
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript"
		src="${contextPath}/js/distributionWorkerPositionShow/distribution_worker_position_show.js"></script>
</body>
</html>