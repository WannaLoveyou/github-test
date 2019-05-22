<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>客户管理</title>
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

	<applet codebase="${contextPath}/plugin/m1Card"
		code="M1CardApplet2.class" name="myApplet" archive="../jna.jar"
		width="0" height="0"> </applet>

	<div id="toolbar">
		<form id="toolbarForm">
			<table>

				<tr>
					<td>所属区域</td>
					<td><input id="firstCategoryTool" name="firstCategory.id"
						editable="false" class="easyui-combobox" />
					</td>
					<td>所属门店</td>
					<td><input id="secondCategoryTool" name="secondCategory.id"
						editable="false" class="easyui-combobox" />
					</td>
					<td>用户状态</td>
					<td>
						<input id="disabled_state_tool" name="disabled_state" editable="false" class="easyui-combobox" />
					</td>

				</tr>
				<tr>
					<td>开户时间：</td>
					<td><input id="create_time_begin_time_tool"
						class="easyui-datebox" editable="false"
						name="create_time_begin_time" />
					</td>

					<td>至</td>
					<td><input id="create_time_end_time_tool"
						class="easyui-datebox" editable="false"
						name="create_time_end_time" />
					</td>
					
					<td>最后订气时间：</td>
					<td><input id="last_order_time_begin_time_tool"
						class="easyui-datebox" editable="false"
						name="last_order_time_begin_time" />
					</td>

					<td>至</td>
					<td><input id="last_order_time_end_time_tool"
						class="easyui-datebox" editable="false"
						name="last_order_time_end_time" />
					</td>
				</tr>
				<tr>
					<td>IC卡编码</td>
					<td><input id="card_code_tool" name="card_code"
						style="width: 100px" /><a id="searchM1CardCodeInToolbar" href="#"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'"></a>
						<a id="searchM1CardCodeInToolbarNew" href="#"
						class="easyui-linkbutton" data-options="iconCls:'icon-search'"></a>
					</td>
					<td>客户编码</td>
					<td><input id="client_code_tool" name="client_code"
						style="width: 130px" />
					</td>
					<td>旧客户编码</td>
					<td><input id="old_client_code_tool" name="old_client_code"
						style="width: 130px" />
					</td>
				</tr>
				<tr>
					<td>客户名</td>
					<td><input id="client_name_tool" name="client_name"
						style="width: 130px" />
					</td>
					<td>电话</td>
					<td><input id="tel_number_tool" name="tel_number"
						style="width: 130px" />
					</td>
					<td>备注</td>
					<td><input id="remark_tool" name="remark"
						style="width: 130px" />
					</td>
					<td>地址</td>
					<td><input id="client_address" name="client_address"
						style="width: 200px" />
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
						<a id="clear" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">清空</a>
						<a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
						<a id="del" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
						<a id="edit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
						<a id="editPassword" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">查看并修改密码</a>
						<a id="rentAirBottle" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">租瓶</a>
						<a id="cancel" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">注销</a>
					</td>
				</tr>

			</table>
		</form>
	</div>
	<table id="tb" class="easyui-datagrid" toolbar="#toolbar"
		title="客户资料管理" style="height:auto;width:auto;"
		style="height:auto;width:auto;"
		data-options="fitColumns:true,striped:true,nowrap:false,rownumbers:true,pagination:true,pageSize:5,pageList:[5,10,15,20]">
	</table>

	<div id="mydialog" class="easyui-dialog" title="新增客户资料" modal="true"
		closed="true" style="width:800px;height:auto;top:50px"
		class="easyui-layout">
		<form id="myform" action="">

			<div id="p" class="easyui-panel">
				<input type="hidden" name="clientId" value="0" />
				<table>
					<tr>
						<td>IC卡编码：</td>
						<td><input name="card_code" /><a
							id="searchM1CardCodeInDialog" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'">读卡</a><a
							id="searchM1CardCodeInDialogNew" href="#"
							class="easyui-linkbutton" data-options="iconCls:'icon-search'"></a>
						</td>

					</tr>
					<tr>
						<td>客户编码：</td>
						<td><input name="client_code" / readonly>
						</td>
						<td>旧系统客户编码：</td>
						<td><input name="old_client_code"/ >
						</td>
						<td>客户名称：</td>
						<td><input name="client_name" required="true" /></td>

					</tr>

					<tr>
						<td>密码：</td>
						<td><input type="hidden" name="password" readonly /></td>
						<td>人员性别：</td>
						<td><input id="client_sex" class="easyui-combobox"
							name="client_sex" style="width:140px;" editable="false">
						</input>
						</td>
						<td>客户类型：</td>
						<td><input id="clientType" name="clientType.id"
							required="true" /></td>

					</tr>

					<tr>
						<td>移动电话1：</td>
						<td><input name="mobile_tel_number_1" /></td>
						<td>移动电话2：</td>
						<td><input name="mobile_tel_number_2" /></td>
						<td></td>
						<td></td>


					</tr>

					<tr>
						<td>固定电话1：</td>
						<td><input name="fixed_tel_number_1" /></td>
						<td>固定电话2：</td>
						<td><input name="fixed_tel_number_2" /></td>
						<td></td>
						<td></td>
					</tr>


					<tr>
						<td>备注：</td>
						<td colspan="6"><textarea style="height:50px;width: 600px;"
								name="remark"></textarea></td>

					</tr>
					<tr>
						<td>临时提示：</td>
						<td colspan="6"><textarea style="height:30px;width: 600px;"
								name="temp_tips"></textarea></td>

					</tr>
					<tr>
						<td>创建人：</td>
						<td><input id="create_people" name="create_people"
							for="create_people" value="" readonly /></td>

						<td>修改人：</td>
						<td><input id="modify_people" name="modify_people"
							for="modify_people" value="" readonly /></td>


					</tr>
					<tr>
						<td>创建日期：</td>
						<td><label id="create_time"></label>
						</td>
						<input id="create_time_temp" name="create_time_temp"
							for="create_time_temp" type="hidden" readonly />
						<td>修改日期：</td>
						<td><label id="modify_time"></label>
						</td>
						<input id="modify_time_temp" name="modify_time_temp"
							for="modify_time_temp" type="hidden" />

					</tr>
				</table>
			</div>

			<div id="p" class="easyui-panel" style="margin-top: 20px;">
				<table>
					<tr>
						<td>所属区域：</td>
						<td><input id="firstCategory" name="firstCategory.id"
							class="easyui-combobox" /></td>

						<td>楼层补贴：</td>
						<td><input id="floorSubsidies" name="floorSubsidies.id"
							required="true" /></td>
						<td>详细地址：</td>
						<td rowspan="3"><textarea style="height:100px;width: 220px;"
								name="client_address"></textarea>
						</td>
					</tr>

					<tr>
						<td>所属门店：</td>
						<td><input id="secondCategory" name="secondCategory.id"
							class="easyui-combobox" editable="false" />
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>

					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>


					<tr align="center">
						<td colspan="6"><a id="btn1" class="easyui-linkbutton"
							data-options="iconCls:'icon-ok'">确认</a> <a id="btn2"
							class="easyui-linkbutton" data-options="iconCls:'icon-no'">关闭</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	
	<div id="rentAirBottleDialog" class="easyui-dialog" modal="true" closed="true" title=" "
		style="width: 500px; height: auto; top: 50px">
		<center>
			<form id="rentAirBottleForm" action="">
				<input type="hidden" name="clientInfo.id" value="0" />
				<table>
					<tr>
						<td>气瓶类型：</td>
						<td><input id="rentAirBottleType" name="airBottleType.id" class="easyui-combobox" required="true" /></td>
					</tr>
					<tr>
						<td>租瓶数量：</td>
						<td><input id="rent_num" name="rent_num" class="easyui-validatebox"  required="true" />
						</td>
					</tr>
					<tr>
						<td>租瓶押金：</td>
						<td><input id="rent_deposit" name="rent_deposit" class="easyui-validatebox" required="true" />
						</td>
					</tr>
					<tr>
						<td>每月租金：</td>
						<td><input id="rent_money_for_month" name="rent_money_for_month" class="easyui-numberbobox"/>
						</td>
					</tr>
					<tr>
						<td>每年租金：</td>
						<td><input id="rent_money_for_year" name="rent_money_for_year" class="easyui-numberbobox"/>
						</td>
					</tr>
					<tr>
						<td>到期时间：</td>
						<td><input id="expire_time" name="expire_time" class="easyui-datebox" />
						</td>
					</tr>
					<tr align="center">
						<td colspan="2"><a id="rentAirBottleSumbitBtn" class="easyui-linkbutton">确认</a></td>
					</tr>
				</table>
			</form>
		</center>
	</div>
	
	<script type="text/javascript"
		src="${contextPath}/js/clientInfo/list.js?v=2019-05-10"></script>
	<script type="text/javascript" src="${contextPath}/js/M1card/common.js"></script>

</body>
</html>