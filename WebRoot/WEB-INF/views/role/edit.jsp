<%@page import="com.qian.entity.Module"%>
<%@page import="com.qian.entity.Role"%>
<%@page import="com.qian.entity.RolePermission"%>
<%@page import="com.qian.entity.Permission"%>
<%@page import="java.util.List"%>
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
<script type="text/javascript"
	src="${contextPath}/styles/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/styles/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"
	src="${contextPath}/styles/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript"
	src="${contextPath}/styles/ztree/js/jquery.ztree.all-3.5.min.js"></script>

<%!public String getTree(Module root, Role role) {
	StringBuilder builder = new StringBuilder();
	long pid = root.getParent() == null ? 0 : root
			.getParent().getId();
	for(Permission permission : root.getPermissions()){
		if("view".equals(permission.getShortName())){
			builder.append("{id:" + root.getId()
					+ ", pId:" + pid + ", name:\""
					+ root.getName()+"\",permissionId:"+permission.getId());
		 boolean flag=false;
		 for(RolePermission rolePermission : role.getRolePermissions()) {
				if (rolePermission.getPermission().getId()==permission.getId()) {
					builder.append(", checked:true},");
					flag=true;
					break;
				}
			}
			if(!flag){
				builder.append("},");
			}
		}
}
	for(Permission permission : root.getPermissions()){
			if(!"view".equals(permission.getShortName())){
				builder.append("{id:\"a" + permission.getId()
						+ "\", pId:" + root.getId() + ", name:\""
						+ root.getName()+"_"+permission.getName()
						+ "\", permissionId:"+permission.getId());
				boolean flag=false;
			 for(RolePermission rolePermission : role.getRolePermissions()) {
					if (rolePermission.getPermission().getId()==permission.getId()) {
						builder.append(", checked:true},");
						flag=true;
						break;
					}
				}
				if(!flag){
					builder.append("},");
				}
			}
	}
	for(Module children : root.getChildren()) {
		builder.append(getTree(children, role));				
	} 

	return builder.toString();
	}
	//public int%>
<%
	Module module = (Module)request.getAttribute("module");
	Role role = (Role)request.getAttribute("role");
	int n=1;
	int parentId=1;
	String employeeTreeHasCheckBox = getTree(module, role);
	employeeTreeHasCheckBox = employeeTreeHasCheckBox.substring(0,
			employeeTreeHasCheckBox.length() - 1);
%>

<script type="text/javascript">
$(function(){
	$.messager.progress({ 
		title:'请稍后', 
		msg:'页面加载中...',
		interval:100
		});
});

function hello(){ 
	$.messager.progress('close');
 
	} 
	window.setTimeout(hello,1000); 


	var setting = {
		view : {
			//showIcon: false
			nameCol : "name",//显示树的名称，默认为"name"
			fontCss : {
			//color : "blue",

			},//字体样式，json形式
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				primissionId : "",

			}
		},
		check : {
			enable : true,
			autoCheckTrigger : false,
			chkboxType : {
				"Y" : "ps",
				"N" : "s"
			}
		},

		callback : {
			onClick : function(event, treeId, treeNode) {
				var $rel = $("#jbsxBox2moduleList");
				$rel.loadUrl(treeNode.url, {}, function() {
					$rel.find("[layoutH]").layoutH();
				});
				event.preventDefault();
			}

		}
	};

	var zNodes = [
<%=employeeTreeHasCheckBox%>
	];

	$(document).ready(function() {
		
		var t = $("#employeeTreeHasCheckBox");
		t = $.fn.zTree.init(t, setting, zNodes);
		t.expandAll(true);
	});
	
	
</script>


</head>
<body>
    <form id="myform" action="">
    <input type="hidden" name="id" value="${role.id }"/>
    <input id="permissionIds" type="hidden" name="permissionIds"/>
    <table>
    <tr>
    <td>角色名称：</td>
    <td><input name="name" class="easyui-validatebox" value="${role.name }" required="true"/></td>
    </td>
    <td>角色描述：</td>
    <td><input name="description" class="easyui-validatebox" value="${role.description }" required="true"/></td>
     <td colspan="2">
    <a id="btn1" class="easyui-linkbutton">确认</a>
    <a id="btn2" class="easyui-linkbutton">关闭</a>
    </td>
    </table>
    </form>
    <div style="height:400px;overflow:auto;">
	<ul id="employeeTreeHasCheckBox" class="ztree" ></ul>
	</div>
     <script>
     $("#btn1").click(function(){
    	 if(!$("#myform").form("validate")){
    		 return;
    	 }
    	 findCheckIds();
    	 $.ajax({
				type : 'post',
				data : $('#myform').serialize(),
				url : '${contextPath }/role/edit',
				success : function(data) {
					 parent.dialog({id:'editRole'}).close();
					 parent.location.href='${contextPath }/role/list';
				}
			});
     });
     
     function findCheckIds()
 	{
    	var primissionIds="";
 		var zTreeObj = $.fn.zTree.getZTreeObj("employeeTreeHasCheckBox"); //获得树对象
 		var nodes = zTreeObj.getNodes(); //获得节点
 		var childNodes = zTreeObj.transformToArray(nodes); //getNodes()方法得到的只是父级节点，想要得到全部的，得转换为数组进行遍历
 		for ( var j = 0; j < childNodes.length; j++) {
 			if (childNodes[j].checked) {
 				primissionIds+=childNodes[j].permissionId+",";
 			}
 		}
 		$("#permissionIds").val(primissionIds);
 	}
     
     $("#btn2").click(function(){
    	parent.dialog({id:'editRole'}).close();
     });
     
     </script>

    
</body>
</html>