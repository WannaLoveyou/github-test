<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>气瓶流程信息</title>

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
	<div data-options="region:'center',title:'气瓶流程信息'"
		style="padding:5px;background:#eee;">
		<div id="toolbar">
			<form id="toolbarForm">
				<table>
					<tr>
						<td>气瓶编码：</td>
						<td><input id="bottleCode" name="bottleCode"
							class="easyui-validatebox" required="true" />
						</td>
						<td><a id="btn" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'">查询</a>
						</td>
						<shiro:hasPermission name="BottleProcessSearch:DyManExHandle">
							<td><a id="dyManExHandleBtn" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-ok'">送气工异常瓶处理</a>
						</td>
						</shiro:hasPermission>
					</tr>
				</table>
			</form>
		</div>
		<table id="tb" class="easyui-datagrid" toolbar="#toolbar"
			title="气瓶流程信息" style="height:auto;width:auto;"
			data-options="fitColumns:true,striped:true,nowrap:false,rownumbers:true">
		</table>
	</div>
</body>



<script type="text/javascript"
	src="${contextPath}/js/bottleProcess/bottleProcessSearch.js"></script>
</body>
</html>
