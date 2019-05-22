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
		<form id="toolbarForm" method="post">
			<table>
				<tr>
					<td colspan="8">所属门店&nbsp;&nbsp;<input id="secondCategory1"
						name="secondCategory1.id" class="easyui-combobox" editable="false" />
						&nbsp;&nbsp;&nbsp;&nbsp;所属角色&nbsp;&nbsp;<input id="userRole1" name="userRole1.id"
						class="easyui-combobox" editable="false" /></td>

				</tr>
				 <tr>
					<td  colspan="8">员工卡号：&nbsp;&nbsp;<input id="card_code1" name="card_code1"
						style="width: 100px" required="true" /><a
						id="searchM1CardCodeInToolbarNew" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'"></a></td>
				</tr> 
				<tr>
					<td colspan="8">账号名&nbsp;&nbsp;<input id="username1" name="username1"
						style="width: 130px" />&nbsp;&nbsp;&nbsp;&nbsp; 员工姓名&nbsp;&nbsp;<input id="full_name1"
						name="full_name1" style="width: 130px" />
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
						<a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
						<a id="del" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
						<a id="edit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a> 
						<a id="limitLogin" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">限制登录</a> 
						<a id="noLimitLogin" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">不限制登录</a> 
						<a id="clearLimitLogin" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">清除登录数据</a> 
						<a id="setWHDeliveryMan" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">设定司机</a> 
						<a id="cancelWHDeliveryMan" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">撤销司机</a> 
						<a id="setStoreDeliveryMan" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">设定送气工</a> 
						<a id="cancelStoreDeliveryMan" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">撤销送气工</a>
						<a id="setSafetyTechnologyDepartment" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">设定安技部</a> 
						<a id="cancelSafetyTechnologyDepartment" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">撤销安技部</a>
						<a id="exportExcel" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">导出</a>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<table id="tb" class="easyui-datagrid" toolbar="#toolbar" title="员工管理"
		style="height:auto;width:auto;"
		data-options="singleSelect:false,fitColumns:true,striped:true,nowrap:false,rownumbers:true,pagination:true,pageSize:20,pageList:[10,20,50,100]">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th data-options="field:'id',width:50">工号</th>
				<th data-options="field:'full_name',width:50">姓名</th>
				<th data-options="field:'contactNumber',width:80">联系方式</th>
				<th data-options="field:'roleNames',width:120">所属角色</th>
				<th data-options="field:'secondCategoryName',width:60">所属门店</th>
				<th data-options="field:'warehouseInfoName',width:60">所属仓库</th>
				<th data-options="field:'username',width:60">账号名</th>
				<th data-options="field:'password',width:60">密码</th>
				<th data-options="field:'user_code',width:80">员工编码</th>
				<th data-options="field:'card_code',width:80">IC卡号</th>
				<th
					data-options="field:'limit_login',width:60,formatter:getLimitLogin">是否限制登录</th>
				<th
					data-options="field:'is_wh_delivery_man',width:60,formatter:getYesOrNo">是否司机</th>
				<th
					data-options="field:'is_store_delivery_man',width:60,formatter:getYesOrNo">是否送气工</th>
				<th
					data-options="field:'is_safety_technology_department',width:60,formatter:getYesOrNo">是否安技部</th>
			</tr>
		</thead>
	</table>

	<div id="mydialog" class="easyui-dialog" title="新增员工" modal="true"
		closed="true" style="width:auto;height:auto;top:5%;">
		<form id="myform" action="">
			<input type="hidden" id="id" name="id" value="0" /> <input
				type="hidden" name="roleIds" id="roleIds" /> <input type="hidden"
				name="secondCategory.id" id="secondCategoryNew" /> <input
				type="hidden" name="warehouseInfo.id" id="warehouseInfoNew" />
			<table>
				<tr>
					<td>员工账号：</td>
					<td><input name="username" class="easyui-validatebox"
						required="true" data-options="validType:'checkUserId'" />
					</td>
					<td>员工密码：</td>
					<td><input name="password" required="true" />
					</td>
				</tr>
				<tr>
					<td>员工卡号：</td>
					<td><input id="card_code" name="card_code" /><a
						id="searchM1CardCodeNew" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'"></a></td>
					<td>员工姓名：</td>
					<td><input name="full_name" required="true" />
					</td>
				</tr>
				<tr>
					<td>员工编码：</td>
					<td><input name="user_code" required="true" /></td>
					<td>联系方式：</td>
					<td><input name="contactNumber" required="true" /></td>
				</tr>
			</table>
			<table id="tb1" style="width: 200px"></table>
			<table id="tb2"
				style="width: 220px;height:142px;float: right; margin-top: -145px">
			</table>
			<table id="tb3" style="width: 220px;height:142px;">
			</table>
			<a id="btn1" class="easyui-linkbutton">确认</a> <a id="btn2"
				class="easyui-linkbutton">关闭</a>

		</form>

	</div>
	<script type="text/javascript" src="${contextPath}/js/user/list.js"></script>
	<script type="text/javascript" src="${contextPath}/js/M1card/common.js"></script>


</body>
</html>