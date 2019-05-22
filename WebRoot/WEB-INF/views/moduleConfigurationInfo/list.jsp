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
		<table id="tb" class="easyui-datagrid" toolbar="#toolbar" title=""
			style="height:500;width:100%;"
			data-options="fitColumns:true,nowrap:false,rownumbers:true,striped:true,
		pagination:true,pageSize:20,pageList:[10,20,50,100]">
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

					<td><a id="search" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">查询</a>
					</td>
					<td><a id="open" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'">开启</a>
					</td>
					<td><a id="close" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-cancel'">关闭</a>
					</td>
				</tr>
			</table>
		</form>
	</div>


	<script type="text/javascript"
		src="${contextPath}/js/moduleConfigurationInfo/list.js"></script>
</body>


</html>