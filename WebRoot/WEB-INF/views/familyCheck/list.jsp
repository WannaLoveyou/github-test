<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>入户检查管理</title>

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
<script type="text/javascript">
	var viewFamilyCheckOrderPhotoPath = '${viewFamilyCheckOrderPhotoPath}';
</script>
</head>


<body id="homeLayout" class="easyui-layout">

	<div data-options="region:'south',title:'',split:true"
		style="height:50px;padding-top: 10px;"></div>

	<div data-options="region:'west',title:'',split:true,border:false"
		style="width:130px;">
		<table id=secondCategoryTable class="easyui-datagrid"
			style="height: 433px"
			data-options="fitColumns:true,striped:false,nowrap:false,singleSelect:true,
		url:'${contextPath }/secondCategory/getMyList',method:'get'">
			<thead>
				<tr>
					<th
						data-options="field:'second_category_name',width:100,align:'center'">部门</th>
				</tr>
			</thead>
		</table>
	</div>


	<div data-options="region:'center',title:'入户检查管理'"
		style="padding:5px;background:#eee;">
		<table id="familyCheckTable" class="easyui-datagrid"
			toolbar="#toolbar" title="" style="height:450px;width:100%;"
			data-options="striped:true,singleSelect:true,
		pagination:true,pageSize:20,pageList:[20,50,100]">
			<thead>
				<tr>
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

					<td>客户编码：</td>
					<td><input id="client_code" name="client_code"
						style="width: 120px" />
					</td>

					<td></td>

					<td>
						<a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
						<a id="revoke" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">撤销</a>
						<a id="viewFamilyCheckPic" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">查看图片</a></td>

				</tr>
			</table>
		</form>
	</div>
	
		<div id="viewFamilyCheckPicDiv" title="查看图片" class="easyui-dialog"
		modal="true" closed="true" style="width:600px;height:500px;top:1px">
		<img id="familyCheckPic" width="400px" height="400px" src="" />
	</div>

	<script type="text/javascript"
		src="${contextPath}/js/familyCheck/list.js"></script>

</body>
</html>
