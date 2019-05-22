<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>漂亮的easyui后台框架演示-css后台模板-www.16sucai.com</title>
<link href="${contextPath}/styles/index/default.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/styles/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/styles/easyui/themes/icon.css" />
	<link rel="stylesheet" type="text/css"
	href="${contextPath}/styles/ztree/css/zTreeStyle.css" />
	<link href="${contextPath}/styles/artdialog/ui-dialog.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${contextPath}/styles/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/styles/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"
	src="${contextPath}/styles/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript"
	src="${contextPath}/styles/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script src="${contextPath}/styles/artdialog/dialog-plus.js" type="text/javascript"></script>




</head>
<body>
	<div id="toolbar">
			<table>
				<tr>
					<td>
					<a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a> 
					
				    <a id="del" href="#" class="easyui-linkbutton"	data-options="iconCls:'icon-remove'">删除</a>
					
					<a id="edit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
					</td>
				</tr>
			</table>
   </div>

	<table id="tb" class="easyui-datagrid" toolbar="#toolbar" title="员工管理" style="height:auto;width:auto;" 
		data-options="fitColumns:true,striped:true,nowrap:false,rownumbers:true,
		url:'${contextPath }/role/getList',method:'get',pagination:true,pageSize:20,pageList:[10,20,50,100]">
		<thead>
			<tr>
			    <th field="ck" checkbox="true"></th>
				<th data-options="field:'name',width:100">角色名称</th>
				<th data-options="field:'description',width:100">角色描述</th>
			</tr>
		</thead>
	</table>
	
    <div id="mydialog" class="easyui-dialog" title="新增角色" modal="true" closed="true" style="width:600px;height:auto;top:5%;"> 
    <form id="myform" action="">
    <input type="hidden" name="id" value="0"/>
    <table>
    <tr>
    <td>角色名称：</td>
    <td><input name="name" class="easyui-validatebox" required="true"/></td>
    </td>
    <td>角色描述：</td>
    <td><input name="description" class="easyui-validatebox" required="true"/></td>
    </tr>
    <tr align="center" >
    <td colspan="2">
    <a id="btn1" class="easyui-linkbutton">确认</a>
    <a id="btn2" class="easyui-linkbutton">关闭</a>
    </td>
    </tr>
    </table>
    <div>
	<ul id="employeeTreeHasCheckBox" class="ztree"></ul>
	</div>
    </form>  
    </div>  
<script>
var flag;
$("#add").click(function(){
	$("#mydialog").dialog({
	title:"新增角色"
	});
	$("#myform").get(0).reset();
	$("#mydialog").dialog("open");
	flag='add';
});

$("#edit").click(function(){
	$("#mydialog").dialog({
	title:"修改角色"
	});
	$("#myform").get(0).reset();
	var selectRows = $("#tb").datagrid("getSelections");
	if(selectRows.length!=1){
		$.messager.show({
			title:'提示信息',
			msg:'只能选择一行记录'
		});
	}else{
		var d = dialog({
			id:'editRole',
		    title: '编辑角色',
		    url:'${contextPath}/role/editPage?roleId='+selectRows[0].id,
		    width:550,
		    height:500,
		    modal: true, //蒙层
		});
		d.show();
	}
	
});


$("#btn2").click(function(){
	$("#mydialog").dialog("close");
});

$("#btn1").click(function(){
	if(!$("#myform").form('validate')){
		return false;
	}
	if(!$("#myform").form('validate')){
		return false;
	}
	$
	.ajax({
		type : 'post',
		data : $('#myform').serialize(),
		url : '${contextPath}/role/'+flag,
		success : function(data) {
			$("#mydialog").dialog("close");
			$("#tb").datagrid("reload");
		}
	});
});


$("#del").click(function(){
	var selectRows = $("#tb").datagrid("getSelections");
	if(selectRows.length<1){
		$.messager.show({
			title:'提示信息',
			msg:'请先选中一条记录'
		});
	}else{
		var ids="";
		for(var i=0;i<selectRows.length;i++){
			ids+=selectRows[i].id+",";
		}
		$
		.ajax({
			type : 'post',
			data : {ids:ids},
			url : '${contextPath}/role/del',
			success : function(data) {
				$("#tb").datagrid("reload");
			}
		});
	}
});

</script>
    
</body>
</html>