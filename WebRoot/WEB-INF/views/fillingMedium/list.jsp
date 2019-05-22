<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>充装介质</title>
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
<body>
	<div id="toolbar">
		<table>
			<tr>
				<td><a id="add" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-add'">新增</a>
					<a id="del" href="#" class="easyui-linkbutton" 
					data-options="iconCls:'icon-remove'">删除</a>
					<a id="edit" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-edit'">编辑</a></td>
			</tr>
		</table>
	</div>

	<table id="tb" class="easyui-datagrid" toolbar="#toolbar"
		title="充装种类" style="height:auto;width:auto;"
		data-options="fitColumns:true,striped:true,nowrap:true,rownumbers:true,
		url:'${contextPath }/fillingMedium/getPageList',method:'get',pagination:true,pageSize:5,pageList:[5,10,15,20]">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th data-options="field:'filling_medium_name',align:'center',width:100">充装介质名称</th>
			</tr>
		</thead>
	</table>

	<div id="mydialog" class="easyui-dialog" title="" modal="true"
		closed="true" style="width:400px;height:auto;top:30px">
		<center>
			<form id="myform" action="">
				<input type="hidden" name="entityId" value="0" />
				<table>
					<tr>
						<td>充装介质名称：</td>
						<td><input name="filling_medium_name" required="true" />
						</td>
					</tr>
					<tr align="center">
						<td colspan="2"><a id="btn1" class="easyui-linkbutton">确认</a>
							<a id="btn2" class="easyui-linkbutton">关闭</a>
						</td>
					</tr>
				</table>
			</form>
		</center>
	</div>
	
	<script>
		var flag;

		$("#add").click(function() {
			$("#mydialog").dialog({
				title : "新增充装种类"
			});
			$('#myform').form('clear');
			$("#mydialog").dialog("open");
			flag = 'add';
		});

		$("#edit").click(function() {
			var selectRows = $("#tb").datagrid("getSelections");
			if (selectRows.length != 1) {
				$.messager.show({
					title : '提示信息',
					msg : '请选择一行记录'
				});
				return;
			}
			$("#mydialog").dialog({
				title : "修改充装种类"
			});
			$('#myform').form('clear');
			$("#myform").form("load",{
				entityId : selectRows[0].id,
				filling_medium_name : selectRows[0].filling_medium_name,
			});
			$("#mydialog").dialog("open");
			flag = 'edit';
		});
		
		$("#btn2").click(function() {
			$("#mydialog").dialog("close");
		});

		$("#btn1").click(function() {
			$.ajax({
				type : 'post',
				data : $('#myform').serialize(),
				url : '${contextPath}/fillingMedium/' + flag,
				success : function(data) {
					$("#mydialog").dialog("close");
					$("#tb").datagrid("reload");
				}
			});
		});

		$("#del").click(function() {
			var selectRows = $("#tb").datagrid("getSelections");
			if (selectRows.length < 1) {
				$.messager.show({
					title : '提示信息',
					msg : '请先选中一条记录'
				});
			} else {
				var ids = "";
				for ( var i = 0; i < selectRows.length; i++) {
					ids += selectRows[i].id + ",";
				}
				$.ajax({
					type : 'post',
					data : {
						ids : ids
					},
					url : '${contextPath}/fillingMedium/del',
					success : function(data) {
						$("#tb").datagrid("reload");
					}
				});
			}
		});
	</script>

</body>
</html>