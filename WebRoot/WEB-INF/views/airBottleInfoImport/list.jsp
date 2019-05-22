<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>气瓶信息导入</title>

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
	src="${contextPath}/styles/uplod/ajaxfileupload.js"></script>


</head>
<body>
	<div id="toolbar">
		<form id="toolbarForm">
			<table>
				<tr>
					<td>创建时间：</td>
					<td><input id="create_time_begin_time" class="easyui-datebox"
						editable="false" name="create_time_begin_time" />
					</td>
					<td>&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;</td>
					<td><input id="create_time_end_time" class="easyui-datebox"
						editable="false" name="create_time_end_time" />
					</td>
					<td>&nbsp;&nbsp;导入状态</td>
					<td><input id="airBottleImportInfoStateTool" name="airBottleImportInfoState.id"
						class="easyui-combobox" /></td>
				</tr>
				<tr>
					<td>气瓶钢印码</td>
					<td><input id="air_bottle_seal_code1"
						name="air_bottle_seal_code" style="width: 150px" />
					</td>
					<td  colspan="8"><a id="search" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">查询</a><a id="add" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'">新增</a> <a id="del" href="#"
						class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
						<a id="edit" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-edit'">编辑</a><a id="import" href="#"
						class="easyui-linkbutton" data-options="iconCls:'icon-add'">上传</a>
						<a id="transform" href="#"
						class="easyui-linkbutton" data-options="iconCls:'icon-add'">导入正式库</a>
						<a id="batchDel" href="#"
						class="easyui-linkbutton" data-options="iconCls:'icon-remove'">批量删除</a>
						<a id="downloadTemplate"  href="#"
						class="easyui-linkbutton" data-options="iconCls:'icon-save'">下载导入模板</a>
					</td>

				</tr>

			</table>
		</form>
	</div>

	<table id="tb" class="easyui-datagrid" toolbar="#toolbar"
		title="气瓶信息管理" style="height:auto;width:auto;">
		<thead>
		</thead>
	</table>

	<div id="mydialog" class="easyui-dialog" modal="true" closed="true"
		style="width:420px;height:auto;top:50px">
		<center>
			<form id="myform" action="">
				<input type="hidden" name="entityId" value="0" />
				<table>
					<tr>
						<td>所属单位编码：</td>
						<td><input name="air_bottle_blong_code" style="width:200px;" />
						</td>
					</tr>
					<tr>
						<td>气瓶钢印码：</td>
						<td><input name="air_bottle_seal_code" style="width:200px;" />
						</td>
					</tr>
					<tr>
						<td>气瓶类型编码：</td>
						<td><input name="air_bottle_type_code" style="width:200px;" />
						</td>
					</tr>
					<tr>
						<td>出厂编号：</td>
						<td><input name="factory_number" style="width:200px;" /></td>
					</tr>
					<tr>
						<td>生产单位编码：</td>
						<td><input name="production_unit_code"
							style="width:200px;" /></td>
					</tr>
					<tr>
						<td>检测单位编码：</td>
						<td><input name="detection_unit_code"
							style="width:200px;" /></td>
					</tr>
					<tr>
						<td>检修时间：</td>
						<td><input name="check_time" style="width:200px;" /></td>
					</tr>
					<tr>
						<td>下次检修时间：</td>
						<td><input name="next_check_time" style="width:200px;" /></td>
					</tr>
					<tr>
						<td>生产时间：</td>
						<td><input name="produce_time" style="width:200px;" /></td>
					</tr>
					<tr>
						<td>设计使用年限：</td>
						<td><input name="use_cycle" style="width:200px;" /></td>
					</tr>
					<tr>
						<td>容积：</td>
						<td><input name="volume" style="width:200px;" /></td>
					</tr>
					<tr>
						<td>气瓶重量：</td>
						<td><input name="bottle_weight" style="width:200px;" /></td>
					</tr>
					<tr>
						<td>备注：</td>
						<td><input id="remark" name="remark" style="width:200px;" />
						</td>
					</tr>

					<tr align="center">
						<td colspan="2"><a id="btn1" class="easyui-linkbutton">确认</a>
							<a id="btn2" class="easyui-linkbutton">关闭</a></td>
					</tr>
				</table>
			</form>
		</center>
	</div>


	<div id="uploadDialog" class="easyui-dialog" modal="true" closed="true"
		style="width:420px;height:auto;top:50px">
		<center>
			<form id="uploadform" action="">
				<table>
					<tr>
						<td>选择文件：</td>
						<td><input name="uploadfile1" type="file" id="uploadfile1" />
						</td>
					</tr>

					<tr align="center">
						<td colspan="2"><a id="uploadSubmitBtn"
							class="easyui-linkbutton">上传</a></td>
					</tr>
				</table>
			</form>
		</center>
	</div>

	<script>
		
	</script>
	<script type="text/javascript"
		src="${contextPath}/js/airBottleInfoImport/list.js"></script>
</body>
</html>