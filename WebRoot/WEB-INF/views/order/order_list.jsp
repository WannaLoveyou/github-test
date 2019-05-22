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
<body  id="homeLayout" class="easyui-layout">

	<applet codebase="${contextPath}/plugin/m1Card"
		code="M1CardApplet2.class" name="myApplet" archive="../jna.jar"
		width="0" height="0"> </applet>

	<shiro:hasPermission name="Order:callsRecord">
 	<div
		data-options="region:'east',iconCls:'icon-reload',title:'East',split:true,border:false"
		style="width:250px;">
		<center>
			<a id="showByphoneRecord" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'">查询</a> 
			<a id="refeshPhoneRecord" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload'">刷新</a> 
		</center>
		<table id=phoneRecordTable class="easyui-datagrid"
			style="height: auto"
			data-options="fitColumns:true,striped:false,nowrap:false,singleSelect:true">

		</table>
	</div>
	</shiro:hasPermission>


	<div region="center" iconCls="icon-search" collapsible="true"
		style="padding:5px;width:100%;height:100%;">


		<div class="easyui-layout" fit="true">
			<div region="north" split="true" border="true"
				style="width:100% ;height:75%;">
				<div class="easyui-layout" fit="true">

					<div region=west split="true" border="true"
						style="width:50% ;height:50%;">


						<div class="easyui-panel" title="客户信息" iconCls="icon-search"
							collapsible="true" style="padding:5px;width:100%;height:100%;">

							<form id="clientInfoForm" action="">
								<table>
									<tr>

										<td>电话号码：</td>
										<td><input id="mobile" name="mobile" style="width:117px" />
											<a id="search" href="#" class="easyui-linkbutton"
											data-options="iconCls:'icon-search'">查询</a></td>

										<td colspan=2>
											<div id="Pagination" class="easyui-pagination"
												data-options="showPageList: false,
												showRefresh: false,
												displayMsg: ''"></div>
										</td>
									</tr>
									<tr>
										<td>IC卡编码:</td>
										<td><input id="card_code" name="card_code"
											style="width:90px" /><a id="searchM1CardCode" href="#"
											class="easyui-linkbutton"
											data-options="iconCls:'icon-search'">读卡</a> <a
											id="searchM1CardCodeNew" href="#" class="easyui-linkbutton"
											data-options="iconCls:'icon-search'"></a></td>
										<td><a id="clear" href="#" class="easyui-linkbutton"
											data-options="iconCls:'icon-remove'">清空</a><a
											id="saveClientInfo" href="#" class="easyui-linkbutton"
											data-options="iconCls:'icon-save'">保存</a>
										</td>
									</tr>
									<tr>
										<td>客户编号：</td>
										<td><input id="client_code" name="client_code" />
										</td>
										</td>
										<td>客户名称：</td>
										<td><input id="client_name" name="client_name" />
										</td>
										<tr>
											<td>客户类型：</td>
											<td><input id="clientType" name="clientType.id"
												class="easyui-combobox" />
											</td>
											<td>固定电话1：</td>
											<td><input id="fixed_tel_number_1"
												name="fixed_tel_number_1" />
											</td>
										</tr>
										<tr>
											<td>固定电话2：</td>
											<td><input id="fixed_tel_number_2"
												name="fixed_tel_number_2" />
											</td>
											<td>移动电话1：</td>
											<td><input id="mobile_tel_number_1"
												name="mobile_tel_number_1" />
											</td>
											</td>
										</tr>
										<tr>
											<td>移动电话2：</td>
											<td><input id="mobile_tel_number_2"
												name="mobile_tel_number_2" />
											</td>
											</td>
											<td>区域：</td>
											<td><input id="firstCategory" name="firstCategory.id"
												class="easyui-combobox" /></td>
										</tr>
										<tr>
											<td>送气门店：</td>
											<td><input id="secondCategory" name="secondCategory.id"
												class="easyui-combobox" /></td>
											<td>所属地段：</td>
											<td><input id="thirdCategory" name="thirdCategory.id"
												class="easyui-combobox" /></td>
										</tr>
										<tr>

											<td>创建时间：</td>
											<td><input id="create_time" name="create_time" />
											</td>
											<td colspan=2>客户密码：
											<!-- <a id="resetPassword" href="#"
												class="easyui-linkbutton"
												data-options="iconCls:'icon-search'">重设密码</a><a
												id="getSMSNum" href="#" class="easyui-linkbutton"
												data-options="iconCls:'icon-search'">查询剩余短信</a>
											 -->
											</td>
										</tr>
										<tr>
											<td>预存款余额：</td>
											<td><input id="card_money" name="card_money" readonly />
											</td>
											<td>客户存瓶：</td>
											<td><input id="bottle_num" name="bottle_num" readonly />
											</td>
										</tr>
										<tr>
											<td>地址：</td>
											<td colspan=3><input id="client_address"
												name="client_address" style="width:100%;" />
											</td>
										</tr>
										<tr>
											<td>开户备注：</td>
											<td colspan=3><textarea id="remark" rows="3" name="remark"
													style="width:100%;"></textarea>
											</td>
										</tr>
										<tr>
											<td>上次安检：</td>
											<td colspan=3><input id="last_family_check_time"
												name="last_family_check_time" style="width:100%;" />
											</td>
										</tr>
								</table>
						</div>
						</form>

					</div>


					<div region="east" split="true" border="true"
						style="width:50% ;height:50%;">


						<div class="easyui-layout" fit="true">
							<div id="tt-tabs" class="easyui-tabs"
								style="width:100%;height:100%;">


								<div title="业务受理" iconCls="icon-search" collapsible="true"
									style="padding:5px;width:100%;height:100%;">
									<form id="myForm" action="">

										<input id="partsStr" name="partsStr" type="hidden" /> <input
											id="parttypenumber" name="parttypenumber" type="hidden" /> <input
											id="parttypeid" name="parttypeid" type="hidden" /> <input
											id="parttypenote" name="parttypenote" type="hidden" /> <input
											id="clientId" type="hidden" name="clientInfo.id" /> <input
											id="order_tel_number" type="hidden" name="order_tel_number" />
										<table>
											<tr>
												<td>预约时间1：</td>
												<td><input id="order_appointment_time1"
													name="order_appointment_time1" class="easyui-datetimebox"
													showSeconds="false" required="true" />
												</td>
												<td>预约时间2：</td>
												<td><input id="order_appointment_time2"
													name="order_appointment_time2" class="easyui-datetimebox"
													showSeconds="false" disabled=true />
												</td>
											</tr>
											<tr>

												<td>气瓶种类：</td>
												<td><input id="airBottleType" name="airBottleType.id"
													class="easyui-combobox" required="true" />
												</td>
												<td>受理日期：</td>
												<td><input class="easyui-datebox" id="dealDate"
													name="dealDate" disabled=true /> <shiro:hasPermission
														name="Order:editOrderTime">
														<td><a id="editOrderTime" class="easyui-linkbutton"
															data-options="iconCls:'icon-edit'">修改</a></td>
													</shiro:hasPermission>
												</td>
											</tr>
											<tr>
												<td>计量单位：</td>
												<td><input id="unit" name="unit"
													class="easyui-validatebox" required="true" />
												</td>
												<td></td>
												<td>
												</td>
											</tr>
											<tr>
												<td>气瓶单价：</td>
												<td><input id="price" name="price"
													class="easyui-validatebox" required="true" readonly />
												</td>
												<td>订单数量：</td>
												<td><input id="orderNum" name="order_number"
													class="easyui-validatebox" onkeyup="changeOrderNumber();"
													required="true"/ >
												</td>
											</tr>
											<tr>
												<td>楼层费单价：</td>
												<td><input id="floorSubsidies"
													name="floor_subsidies_money" class="easyui-combobox"
													required="true" />
												</td>
												<td></td>
												<td><input id="check_fee" name="check_fee" type="hidden"
													onkeyup="changeCheckFee();" readonly />
												</td>
												<td><a id="addOrder" class="easyui-linkbutton"
													data-options="iconCls:'icon-add'">下订单</a></td>
											</tr>
											<tr>
												<td>送气费单价：</td>
												<td><input id="delivery_fee" name="delivery_fee"
													onkeyup="changeDeliveryFee();" readonly />
												</td>
												<td>单价已优惠：</td>
												<td><input id="account_price" name="account_price"
													onkeyup="changeAccountPrice();" readonly />
												</td>

											</tr>

											<tr>
												<td>配送方式：</td>
												<td><input id="deliveryType" name="deliveryType.id"
													class="easyui-combobox" required="true" />
												</td>
												<td>付款方式：</td>
												<td><input id="payType" name="payType.id"
													class="easyui-combobox" required="true" />
												</td>
											</tr>
											<tr>
												<td>临时提示：</td>
												<td colspan=3><textarea id="temp_tips" name="remark"
														style="width:100%;height: 40px"></textarea>
												</td>
											</tr>
											<tr>
												<td>收费项目：</td>
												<td colspan=3><input id="feeType" name="feeType"
													style="width:100%;" readonly></input>
												</td>
												<td><a id="prompt1" name="prompt1"
													class="easyui-linkbutton" style="width: 80px;height: 30px"
													data-options="iconCls:'icon-search'">收费管理</a></td>
											</tr>
											<tr>
												<td>收费金额：</td>
												<td colspan=3><input id="fee_total_amount"
													name="fee_total_amount" value=0
													onkeyup="changeFeeToTalAmount();" readonly></input>
												</td>
											</tr>
											<tr>
												<td>总优惠：</td>
												<td><input id="discount_amount" name="discount_amount"
													onkeyup="changeDiscountAmount();" readonly/>
												</td>
												<td>总金额：</td>
												<td><input id="allPrice" name="total_amount"
													class="easyui-validatebox" required="true" readonly />
												</td>
												<shiro:hasPermission name="Order:editOrderMoney">
													<td><a id="editOrderMoney" class="easyui-linkbutton"
														data-options="iconCls:'icon-edit'">修改</a></td>
												</shiro:hasPermission>
											</tr>
											<tr>
												<td>地址：</td>
												<td colspan=3><input id="deliveryAddress"
													name="order_address" style="width:100%;"
													class="easyui-validatebox" required="true" />
												</td>
											</tr>
										</table>
									</form>
								</div>

								<div title="维修受理" iconCls="icon-reload" style="padding:10px;">
									<form id="repairmyForm" action="">
										<input id="clientId2" type="hidden" name="clientInfo.id" /> <input
											id="repair_tel_number" type="hidden" name="repair_tel_number" />
										<table>
											<tr>
												<td>预约时间1：</td>
												<td><input id="repair_appointment_time1"
													name="repair_appointment_time11" class="easyui-datetimebox"
													showSeconds="false" required="true" />
												</td>
												<td>预约时间2：</td>
												<td><input id="repair_appointment_time2"
													showSeconds="false" name="repair_appointment_time22"
													class="easyui-datetimebox" />
												</td>
											</tr>
											<tr>
												<td>维修类型：</td>
												<td><input id="repairType" name="repairType.id"
													class="easyui-combobox" required="true" />
												</td>
												<td>受理日期：</td>
												<td><input id="accept_deal_time"
													name="accept_deal_time" editable="false" readonly />
												</td>
											</tr>
											<tr>
												<td>维修内容：</td>
												<td colspan=3><input id="repair_content"
													name="repair_content" required="true"
													style="width: 100%;height: 50px;" />
												</td>
												<td>
													<td><a id="repairBtn1" class="easyui-linkbutton"
														data-options="iconCls:'icon-add'">保存</a></td>
											</tr>
											<tr>
												<td>备注：</td>
												<td colspan=3><input id="repair_note"
													name="repair_note" style="width: 100%" />
												</td>
											</tr>
											<tr>
												<td>地址：</td>
												<td colspan=3><input id="repair_address"
													name="repair_address" style="width:100%;" />
												</td>
											</tr>
											<tr>
												<td>上门费：</td>
												<td><input id="repair_price" name="repair_price" />
												</td>
											</tr>


										</table>
									</form>
								</div>


								<div title="投诉受理" iconCls="icon-reload" style="padding:10px;">
									<form id="complaintmyForm" action="">
										<input id="clientId3" type="hidden" name="clientInfo.id" /> <input
											id="complaint_tel_number" type="hidden"
											name="complaint_tel_number" />
										<table>
											<tr>
												<td>预约时间1：</td>
												<td><input id="complaint_appointment_time1"
													name="complaint_appointment_time11" showSeconds="false"
													class="easyui-datetimebox" required="true" />
												</td>
												<td>预约时间2：</td>
												<td><input id="complaint_appointment_time2"
													name="complaint_appointment_time22" showSeconds="false"
													class="easyui-datetimebox" />
												</td>
											</tr>
											<tr>
												<td>投诉类型：</td>
												<td><input id="complaintType" name="complaintType.id"
													class="easyui-combobox" required="true" />
												</td>
												<td>受理日期：</td>
												<td><input id="complaint_time" editable="false" />
												</td>
											</tr>
											<tr>
												<td>投诉内容：</td>
												<td colspan=3><input id="complaint_content"
													name="complaint_content" required="true"
													style="width: 100%;height: 50px;" />
												</td>
												<td><a id="complaintBtn1" class="easyui-linkbutton"
													data-options="iconCls:'icon-add'">保存</a></td>
											</tr>
											<tr>
												<td>备注：</td>
												<td colspan=3><input id="complaint_note"
													name="complaint_note" style="width: 100%" />
												</td>
											</tr>
											<tr>
												<td>地址：</td>
												<td colspan=3><input id="complaint_address"
													name="complaint_address" style="width:100%;" />
												</td>
											</tr>
										</table>
									</form>
								</div>

								 
								<div title="客户回访" iconCls="icon-reload" style="padding:10px;">
									<form id="visitmyForm" action="">
										<input id="clientId4" type="hidden" name="clientInfo.id" />
										<input id="visit_tel_number" type="hidden" name="visit_tel_number" />
										<table style="width: 100%;">
											<tr>
												<td>回访类型：</td>
												<td colspan="3">
													<input id="visitType" name="visitType.id" 
													class="easyui-combobox" required="true" 
													style="width: 96%;"/>
												</td>
											</tr>
											<tr>
												<td>内容：</td>
												<td colspan=3><textarea id="visit_content"
													name="visit_content" required="true"
													style="width: 100%;height: 200px;" ></textarea>
												</td>
												<td><a id="visitBtn1" class="easyui-linkbutton"
													data-options="iconCls:'icon-add'">保存</a></td>
											</tr>
											<tr>
												<td>备注：</td>
												<td colspan=3><input id="visit_note"
													name="visit_note" style="width: 100%" />
												</td>
											</tr>
											<tr>
												<td>地址：</td>
												<td colspan=3><input id="visit_address"
													name="visit_address" style="width:100%;" />
												</td>
											</tr>
										</table>
									</form>
								</div>
								
								<!--<div title="催送气" iconCls="icon-reload" style="padding:10px;">
								Second Tab</div> -->
								
								<div title="撤单记录" iconCls="icon-reload" style="padding:10px;">
								</div>
								
								<div title="入户检查" iconCls="icon-reload" style="padding:10px;">
									<form id="familyCheckForm" action="">
										<input id="familyCheckClientId" type="hidden"
											name="clientInfo.id" /> <input id="family_check_tel_number"
											type="hidden" name="family_check_tel_number" />
										<table>
											<tr></tr>
											<tr>
												<td>预约时间：</td>
												<td><input id="family_check_appointment_check_time1"
													name="appointment_check_time" showSeconds="false"
													class="easyui-datetimebox" required="true" />
												</td>
												<td>受理日期：</td>
												<td><input id="family_check_time" editable="false" />
												</td>
											</tr>

											<tr>
												<td>备注：</td>
												<td colspan=3><input id="family_check_remark"
													name="remark" style="width:100%;height: 50px;" />
												</td>
												<td><a id="familyCheckBtn1" class="easyui-linkbutton"
													data-options="iconCls:'icon-add'">保存</a></td>
											</tr>
											<tr>
												<td>地址：</td>
												<td colspan=3><input id="family_check_address"
													name="family_check_address" style="width:100%;" />
												</td>
											</tr>
										</table>
									</form>
								</div>
								
								<!-- <div title="预存款充值" iconCls="icon-reload" style="padding:50px;">
									<form id="cardMoneyRechargeForm" action="">
										<input id="cardMoneyRechargeClientId" type="hidden"
											name="clientInfo.id" />
										<table>
											<tr>
												<td>充值类型：</td>
												<td><input id="cardMoneyRechargeMoneyType"
													name="cardMoneyRechargeMoneyType.id"
													class="easyui-combobox" required="true" />
												</td>
											</tr>
											<tr>
												<td>充值金额：</td>
												<td><input id="cardMoneyRechargeMoney"
													name="recharge_money" readonly />
												</td>

												<td>赠送金额：</td>
												<td><input id="cardMoneyGiftMoney" name="gift_money"
													readonly />
												</td>
											</tr>
											<tr>
												<td colspan=4 align="center"><input
													id="cardMoneyRechargeBtn1" name="cardMoneyRechargeBtn1"
													type="button" value="充值" /> <input
													id="cardMoneyRechargeBtn2" name="cardMoneyRechargeBtn2"
													type="button" value="撤销" />
												</td>
											</tr>
										</table>
									</form>
								</div>
								
								<div title="预存款扣款" iconCls="icon-reload" style="padding:50px;">
									<form id="cardMoneyPaymentForm" action="">
										<input id="cardMoneyPaymentClientId" type="hidden"
											name="clientInfo.id" />
										<table>

											<tr>
												<td>消费金额：</td>
												<td><input id="cardMoneyPaymentMoney"
													name="payment_money" />
												</td>

											</tr>
											<tr>
												<td colspan=4 align="center"><input
													id="cardMoneyPaymentBtn1" name="cardMoneyPaymentBtn1"
													type="button" value="扣款" /> <input
													id="cardMoneyPaymentBtn2" name="cardMoneyPaymentBtn2"
													type="button" value="撤销" />
												</td>
											</tr>
										</table>
									</form>
								</div> -->
								
								<div title="客户气瓶信息" iconCls="icon-reload" style="padding:10px;">
								</div>
								
							</div>

						</div>
					</div>


				</div>
			</div>




			<div region="south" split="true" border="true"
				style="width:100% ;height:28%;">
				<div class="easyui-panel" title="" iconCls="icon-search"
					collapsible="true" style="padding:5px;width:1600px;height:100%;">
					<table id="tb2" style="width:100%;" class="easyui-datagrid">
						<thead>
							<tr>
							</tr>
						</thead>
					</table>
				</div>
			</div>

			<div id="w" class="easyui-window" title="收费管理"
				data-options="modal:true,closed:true,iconCls:'icon-save'"
				style="width:500px;height:400px;padding:10px;">
				<form id="feeForm" action="">
					<!--  单元格编辑 -->
					<table id="dg" class="easyui-datagrid" title="收费项目管理"
						style="width:auto;height:auto">

					</table>
					<br /> <input type="button" id="feebtn1" value="确定" /> &nbsp; <input
						type="reset" id="feebtn2" value="重置" />

				</form>


			</div>
		</div>


	</div>


	<script type="text/javascript"
		src="${contextPath}/js/order/order_list.js"></script>
	<script type="text/javascript" src="${contextPath}/js/M1card/common.js"></script>

</body>
</html>