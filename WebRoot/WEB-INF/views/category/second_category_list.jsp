<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>门店管理</title>

<link href="${contextPath}/styles/index/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/styles/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/styles/easyui/themes/icon.css" />
<script type="text/javascript" src="${contextPath}/styles/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/styles/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${contextPath}/styles/easyui/locale/easyui-lang-zh_CN.js"></script>



</head>
<body>
	<div id="toolbar">
		<form id="toolbarForm" method="post">
			<table>
				<tr>
					<td>
						<a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a> 
					    <a id="del" href="#" class="easyui-linkbutton"	data-options="iconCls:'icon-remove'">删除</a>
						<a id="edit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
						<a id="exportExcel" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">导出</a>
					</td>
				</tr>
			</table>
		</form>
   </div>
	<table id="tb" class="easyui-datagrid" toolbar="#toolbar" title="门店管理" style="height:auto;width:auto;" 
		data-options="fitColumns:true,striped:true,nowrap:false,rownumbers:true,
		url:'${contextPath }/secondCategory/getPageList',method:'get',pagination:true,pageSize:20,pageList:[5,10,15,20]">
		<thead>
			<tr>
			    <th field="ck" checkbox="true"></th>
			    <th data-options="field:'firstCategory.first_category_name',width:100,formatter:getFirstCategoryName">所属区域</th>
				<th data-options="field:'second_category_name',width:100">所属门店</th>
				<th data-options="field:'contactName',width:100">负责人</th>
				<th data-options="field:'contactNumber',width:100">联系方式</th>
				<th data-options="field:'address',width:200">地址</th>
			</tr>
		</thead>
	</table>
	
   <div id="mydialog" class="easyui-dialog" modal="true" closed="true" style="width:450px;height:auto;top:50px">
    <center>
	    <form id="myform" action="">
	    	<input type="hidden" name="id" value="0"/>
		    <table>
			    <tr>
				    <td>所属区域：</td>
				    <td><input id="firstCategory" name="firstCategory.id" class="easyui-combobox"></td>
				    <td>门店：</td>
				    <td><input name="second_category_name" required="true"/></td>
			    </tr>
			    
			    <tr>
					<td>负责人：</td>
					<td><input name="contactName" required="true" /></td>
					<td>联系方式：</td>
					<td><input name="contactNumber" required="true" /></td>
				</tr>
			    
			    <tr>
					<td>地址：</td>
					<td colspan="3"><input name="address" required="true" style="width:354px" /></td>
				</tr>
			    
			    <tr align="center" >
				    <td colspan="4">
					    <a id="btn1" class="easyui-linkbutton">确认</a>
					    <a id="btn2" class="easyui-linkbutton">关闭</a>
				    </td>
			    </tr>
		    </table>
	    </form>  
    </center>
    </div>  
<script>

$("#exportExcel").click(function() {
	outToFile();
});

function outToFile() {
	
	document.getElementById("toolbarForm").action = basePath + '/secondCategory/exportAllList';  
	
	document.getElementById("toolbarForm").submit();    
};

var flag;

$("#add").click(function(){
	$("#mydialog").dialog({
	title:"新增门店"
	});
	$("#myform").get(0).reset();
	$("#mydialog").dialog("open");
	flag='add';
	
	$('#firstCategory').combobox({    
		value:"请选择",
		editable:false,
		method:'get',
		url:'${contextPath }/firstCategory/getAllList', 
	    valueField:'id',
	    textField:'first_category_name'   
	}); 
});

$("#edit").click(function(){
	$("#mydialog").dialog({
	title:"修改门店"
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
		$('#firstCategory').combobox({    
			value:"请选择",
			editable:false,
			method:'get',
			url:'${contextPath }/firstCategory/getAllList', 
		    valueField:'id',
		    textField:'first_category_name'   
		}); 
		$("#myform").form("load",{
			id:selectRows[0].id,
			'firstCategory.id':selectRows[0].firstCategory.id,
			second_category_name:selectRows[0].second_category_name,
			contactName:selectRows[0].contactName,
			contactNumber:selectRows[0].contactNumber,
			address:selectRows[0].address
		});
		$("#mydialog").dialog("open");
		flag='edit';
	}
	
});


$("#btn2").click(function(){
	$("#mydialog").dialog("close");
});

$("#btn1").click(function(){
	$.ajax({
		type : 'post',
		data : $('#myform').serialize(),
		url : '${contextPath}/secondCategory/'+flag,
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
			msg:'请选择要删除的行数'
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
			url : '${contextPath}/secondCategory/del',
			success : function(data) {
				$("#tb").datagrid("reload");
			}
		});
	}
});


function getFirstCategoryName(value,rec)
{
	if(rec.firstCategory!=null){
		return rec.firstCategory.first_category_name;
	}
	return null;
}
</script>
    
</body>
</html>