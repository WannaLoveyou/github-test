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
					<td>
					<a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a> 
					
				    <a id="del" href="#" class="easyui-linkbutton"	data-options="iconCls:'icon-remove'">删除</a>
					
					<a id="edit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
					</td>
				</tr>
			</table>
   </div>

	<table id="tb" class="easyui-datagrid" toolbar="#toolbar" title="客户类型管理" style="height:auto;width:auto;" 
		data-options="fitColumns:true,striped:true,nowrap:false,rownumbers:true,

		url:'${contextPath }/clientType/getPageList',method:'get',pagination:true,pageSize:5,pageList:[5,10,15,20]">
		<thead>
			<tr>
			    <th field="ck" checkbox="true"></th>

				<th data-options="field:'client_type_name',width:100">客户类型名称</th>
			</tr>
		</thead>
	</table>
	

    <div id="mydialog" class="easyui-dialog" title="新增用户" modal="true" closed="true" style="width:400px;height:auto"> 
    <form id="myform" action="">
    <input type="hidden" name="id" value="0"/>
    <table>
    <tr>

    <td>客户类型名称：</td>
    <td><input name="client_type_name" class="easyui-validatebox" required="true"/></td>
    </tr>
    <tr align="center" >
    <td colspan="2">
    <a id="btn1" class="easyui-linkbutton">确认</a>
    <a id="btn2" class="easyui-linkbutton">关闭</a>
    </td>
    </tr>
    </table>
    </form>  
    </div>  
<script>
var flag;

$("#add").click(function(){
	$("#mydialog").dialog({
	title:"新增用户"
	});
	$("#myform").get(0).reset();
	$("#mydialog").dialog("open");
	flag='add';
});

$("#edit").click(function(){
	$("#mydialog").dialog({
	title:"修改用户"
	});
	$("#myform").get(0).reset();
	var selectRows = $("#tb").datagrid("getSelections");
	if(selectRows.length!=1){
		$.messager.show({
			title:'提示信息',
			msg:'只能选择一行记录'
		});
	}else{
		$("#myform").get(0).reset();
		$("#myform").form("load",{
			id:selectRows[0].id,
			client_type_name:selectRows[0].client_type_name
		});
		$("#mydialog").dialog("open");
		flag='edit';
	}
	
});


$("#btn2").click(function(){
	$("#mydialog").dialog("close");
});

$("#btn1").click(function(){
	if(!$("#myform").form("validate")){
		return false;
	}
	$.ajax({
		type : 'post',
		data : $('#myform').serialize(),
		url : '${contextPath}/clientType/'+flag,
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
			url : '${contextPath}/clientType/del',
			success : function(data) {
				$("#tb").datagrid("reload");
			}
		});
	}
});

</script>
    
</body>
</html>