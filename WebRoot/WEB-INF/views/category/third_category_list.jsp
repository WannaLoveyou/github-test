<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>地段管理</title>

<link href="${contextPath}/styles/index/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/styles/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/styles/easyui/themes/icon.css" />
<script type="text/javascript" src="${contextPath}/styles/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/styles/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${contextPath}/styles/easyui/locale/easyui-lang-zh_CN.js"></script>



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

	<table id="tb" class="easyui-datagrid"  toolbar="#toolbar" title="地段管理" style="height:auto;width:auto;" 
		data-options="fitColumns:true,striped:true,nowrap:false,rownumbers:true,
		url:'${contextPath }/thirdCategory/getPageList',method:'get',pagination:true,pageSize:20,pageList:[5,10,15,20]">
		<thead>
			<tr>
			    <th field="ck" checkbox="true"></th>                                                 
			    <th data-options="field:'secondCategory.firstCategory.first_category_name',width:100,formatter:getFirstCategoryName">所属区域</th>
			    <th data-options="field:'secondCategory.second_category_name',width:100,formatter:getSecondCategoryName">所属门店</th>
				<th data-options="field:'third_category_name',width:100">所属地段</th>
			</tr>
		</thead>
	</table>
	
	<div id="mydialog" class="easyui-dialog" modal="true" closed="true" style="width:280px;height:auto;top:50px">
	<center>
	<form id="myform" action="">
    <input type="hidden" id="id" name="id" value="0"/>
    <table>
    
    <tr>
    <td>所属区域：</td>
    <td>
    <input id="firstCategory" name="firstCategory" editable="false"  class="easyui-combobox" required="true">

    </td>
    </tr>
    
    <tr>
    <td>所属门店：</td>
    <td> <input id="secondCategory" name="secondCategory.id" editable="false" class="easyui-combobox" required="true"/> </td>

    </tr>
    
    
   
    <tr>
    <td>所属地段：</td>
    <td><input id="thirdCategory" name="third_category_name" class="easyui-validatebox" required="true"/></td>
    </tr>
    <tr align="center" >
    <td colspan="2">
    <a id="btn1" class="easyui-linkbutton">确认</a>
    <a id="btn2" class="easyui-linkbutton">关闭</a>
    </td>
    </tr>
    </table>
    </form>
 </center>
</div>
	
	
<script>



var flag;

$("#add").click(function(){
	$("#mydialog").dialog({
	title:"新增地段"
	});
	$("#myform").get(0).reset();
	$("#mydialog").dialog("open");
	flag='add';
	$('#firstCategory').combobox({    
	    url:'${contextPath }/firstCategory/getAllList', 
	    method:'get',
	    valueField:'id',    
	    textField:'first_category_name',
	    onSelect:function(rec){
	    	$('#secondCategory').combobox({    
	    	    url:"${contextPath }/secondCategory/getListByFirstId?firstId="+rec.id,
	    	    method:'get',
	    	    valueField:'id',    
	    	    textField:'second_category_name',
	    	});  
	    }
	});  


});



$("#del").click(function(){
	var selectRows = $("#tb").datagrid("getSelections");
	if(selectRows.length<1){
		$.messager.show({
			title:'提示信息',
			msg:'请选择要删除的记录'
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
			url : '${contextPath}/thirdCategory/del',
			success : function(data) {
				$("#tb").datagrid("reload");
			}
		});
	}
});


$("#edit").click(function(){
	$("#mydialog").dialog({
	title:"修改地段"
	});
	$("#myform").get(0).reset();
	var selectRows = $("#tb").datagrid("getSelections");
	if( selectRows.length!=1){
		$.messager.show({
			title:'提示信息',
			msg:'请选择一条记录'
		});
	}else{
		$('#secondCategory').combobox({    
		    url:'${contextPath }/secondCategory/getAllList', 
		    method:'get',
		    valueField:'id',    
		    textField:'second_category_name'
		});
			$('#firstCategory').combobox({    
			    url:'${contextPath }/firstCategory/getAllList', 
			    method:'get',
			    valueField:'id',    
			    textField:'first_category_name',
			    onSelect:function(rec){
			    	$('#secondCategory').combobox({    
			    	    url:"${contextPath }/secondCategory/getListByFirstId?firstId="+rec.id,
			    	    method:'get',
			    	    valueField:'id',    
			    	    textField:'second_category_name'
			    	});  
			    }
			});
		
		
		
		
		$("#myform").form("load",{
			firstCategory:selectRows[0].secondCategory.firstCategory.id,
			'secondCategory.id':selectRows[0].secondCategory.id,
			third_category_name:selectRows[0].third_category_name
		});
			
		$("#id").val(selectRows[0].id);
		$("#thirdCategory").val(selectRows[0].third_category_name);
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
		url : '${contextPath}/thirdCategory/'+flag,
		success : function(data) {
			$("#mydialog").dialog("close");
			$("#tb").datagrid("reload");
		}
	});
});

function getFirstCategoryName(value,rec)
{
	if(rec.secondCategory!=null && rec.secondCategory.firstCategory!=null){
		return rec.secondCategory.firstCategory.first_category_name;
	}
	return null;
}

function getSecondCategoryName(value,rec)
{
	if(rec.secondCategory!=null){
		return rec.secondCategory.second_category_name;
	}
	return null;
}
</script>
    
</body>
</html>