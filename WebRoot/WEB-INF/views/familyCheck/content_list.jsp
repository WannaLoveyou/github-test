<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@taglib prefix="lzy" uri="http://www.lizhaoyang.com/LZY"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta charset="UTF-8">
<title>富民燃气在线订气系统</title>
<link rel="stylesheet"
	href="${contextPath}/styles/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${contextPath}/styles/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${contextPath}/styles/checkorder/table.css">
<script type="text/javascript"
	src="${contextPath}/styles/jquery/jquery.min.js"></script>

</head>
<body>
	<form id="user_check_form" method="post">
		<table class="table table-hover">
			<input type="hidden" id="familyCheckId" name="familyCheckId"
				value=${familyCheckId } />
			<tr>
				<td colspan="10">
					<div>
						<div style="text-align: center">
							<span class="control-label  " style="font-size: 30px; ">
								虎门能源投资有限公司客户安全检查记录表</span>
						</div>
						<div style="text-align: right;padding-right: 10%">
							<span class="control-label col-xs-11 " style="font-size: 14px;">
								检查编号:</span> <input id="family_check_code" name="family_check_code"
								type="text" class="col-xs-1"
								style="color: #b92c28;text-align: left;" readonly>
						</div>

					</div></td>
			</tr>

			<tr>
				<td colspan="3">
					<p class="col-xs-5">客户名称:</p> <input type="text" class="col-xs-7"
					id="customer_name" name="customer_name"></td>

				<td colspan="4">
					<p class="col-xs-5">客户编号:</p> <input type="text" class="col-xs-7"
					id="customer_number" name="customer_number"></td>
				<td colspan="3">
					<p class="col-xs-5">电话:</p> <input type="text" class="col-xs-7"
					id="customer_phone" name="customer_phone"></td>
			</tr>

			<tr>
				<td colspan="3">
					<p class="col-xs-5">客户地址:</p> <input type="text" class="col-xs-7 "
					id="customer_address" name="customer_address"></td>
				<td colspan="4">
					<p class="col-xs-5">到达时间:</p> <input type="time" class="col-xs-7 "
					name="arrive_time"></td>
				<td colspan="4">
					<p class="col-xs-3">入户检查:</p> <input type="checkbox"
					class="col-xs-1" value="" id="spcheckbox3"
					name="whether_enter_home"></td>
			</tr>


			<tr>
				<td colspan="3">
					<p class="col-xs-5">检查日期:</p> <input type="date" class="col-xs-7 "
					value="" name="check_date"></td>

				<td colspan="4">
					<p class="col-xs-5">离开时间:</p> <input type="time" class="col-xs-7 "
					value="" name="leave_time"></td>


				<td colspan="4">
					<p class="col-xs-3">无人:</p> <input type="checkbox" class="col-xs-1"
					value="yes" id="spcheckbox1" name="whether_have_somebody">
					<p class="col-xs-3" style="text-align: left;">拒绝:</p> <input
					type="checkbox" class="col-xs-1" value="yes" id="spcheckbox2"
					name="whether_refuse"></td>
			</tr>

			<tr>
				<td rowspan="15" colspan="1" style="width: 8%;">
					<h3>
						<p class="col-xs-12" style="text-align: center;">
							</br> </br> </br> </br> </br> </br> </br> </br> 检</br> </br> </br>查</br> </br> </br>内</br> </br> </br>容</br> </br> </br>
						</p>
					</h3></td>
			</tr>

			<td colspan="9">
				<h2>
					<p id="spspan" class="col-xs-12"
						style="text-align: center;letter-spacing: 20px">检查项目</p>
				</h2></td>
			</tr>

			<tr>
				<td rowspan="3" style="width: 1%">
					<p class="col-xs-12" style="text-align: center;">
						</br> 钢</br> </br>瓶</br>
					</p></td>
				<td colspan="4">
					<p class="col-xs-4" style="text-align: left">外观:</p>
					<p class="col-xs-4">
						&nbsp;严重腐蚀<input type="checkbox" name="bollte_corrosion_degree"
							value="severity">
					</p>

					<p class="col-xs-4">
						轻度腐蚀<input type="checkbox" name="bollte_corrosion_degree"
							value="mild">
					</p></td>
				<td colspan="4">
					<p class="col-xs-3">漏气:</p>
					<p class="col-xs-2">
						有 <input type="checkbox" name="bottle_whether_air_leakage"
							value="yes">
					</p>

					<p class="col-xs-2">
						无 <input type="checkbox" name="bottle_whether_air_leakage"
							value="no">
					</p></td>
			</tr>

			<tr>
				<td colspan="4">
					<p class="col-xs-4">钢瓶编码:</p> <input type="text" class="col-xs-8"
					name="bollte_number">
				</td>
				<td colspan="4">
					<p class="col-xs-3">钢瓶期效:</p>
					<p class="col-xs-2">
						正常 <input type="checkbox" name="bolle_indate" value="normal">
					</p>

					<p class="col-xs-2">
						过期瓶 <input type="checkbox" name="bolle_indate" value="past_due">
					</p>

					<p class="col-xs-2">
						报废瓶 <input type="checkbox" name="bolle_indate" value="scrap">
					</p></td>

			</tr>




			<tr>
				<td colspan="4">
					<p class="col-xs-4">是否我司钢瓶:</p>
					<p class="col-xs-4">
						是<input type="checkbox" name="whether_company_bottle" value="yes">
					</p>
					<p class="col-xs-4">
						否<input type="checkbox" name="whether_company_bottle" value="no">
					</p></td>
				<td colspan="4">
					<p class="col-xs-5">下次检验期（报废期）:</p> <input type="date"
					class="col-xs-4" name="next_probative_term"></td>
			</tr>





			<tr>
				<td rowspan="2" style="">
					<p class="col-xs-12" style="text-align: center;">
						减</br> </br>压</br> </br>阀
					</p></td>
				<td colspan="4">
					<p class="col-xs-4">减压阀品牌:</p> <input type="text"
					name="reducing_valve_brand"></td>
				<td colspan="4">
					<p class="col-xs-3">型号:</p> <input type="text"
					name="reducing_valve_type"></td>
			</tr>


			<tr>
				<td colspan="4">
					<p class="col-xs-4">外观:</p>
					<p class="col-xs-4 ">
						严重腐蚀<input type="checkbox" name="reducing_valve_corrosion_degree"
							value="severity">
					</p>
					<p class="col-xs-4 ">
						轻度腐蚀 <input type="checkbox" name="reducing_valve_corrosion_degree"
							value="mild">
					</p></td>
				<td colspan="4">
					<p class="col-xs-3">漏气:</p>
					<p class="col-xs-2">
						有<input type="checkbox" name="reducing_valve_whether_air_leakage"
							value="yes">
					</p>
					<p class="col-xs-2">
						无<input type="checkbox" name="reducing_valve_whether_air_leakage"
							value="no">
					</p></td>
			</tr>













			<tr>
				<td rowspan="3" style="">
					<p class="col-xs-12" style="text-align: center;">
						</br> </br> 灶</br> </br>具
					</p></td>
				<td colspan="4">
					<p class="col-xs-4">灶具品牌:</p> <input type="text" class="col-xs-6"
					name="stove_brand">
					<p class="col-xs-4" id="sptime1">购置时间:</p> <input type="date"
					id="sptime2" class="col-xs-6" name="stove_buy_date"></td>
				<td colspan="4">
					<p class="col-xs-3">安装类型:</p>
					<p class="col-xs-2">
						台式:<input type="checkbox" name="stove_install_type" value="table">
					</p>
					<p class="col-xs-2">
						嵌入式:<input type="checkbox" name="stove_install_type"
							value="flushbonading">
					</p></td>
			</tr>


			<tr>

				<td colspan="4">
					<p class="col-xs-4">熄火保护装置:</p>
					<p class="col-xs-3 " id="spP">
						有<input type="checkbox" name="flame_out_protection_device"
							value="yes">
					</p>
					<p class="col-xs-4  ">
						无<input type="checkbox" name="flame_out_protection_device"
							value="no">
					</p></td>
				<td colspan="4">
					<p class="col-xs-3">漏气:</p>
					<p class="col-xs-2">
						有:<input type="checkbox" name="stove_whether_air_leakage"
							value="yes">
					</p>
					<p class="col-xs-2">
						无:<input type="checkbox" name="stove_whether_air_leakage"
							value="no">
					</p></td>

			</tr>

			<tr>

				<td colspan="8">
					<p class="col-xs-2">灶具连接管:</p>
					<p class="col-xs-1">
						过长:<input type="checkbox" name="stove_connecting_pipe"
							value="too_long">
					</p>
					<p class="col-xs-1">
						无管卡:<input type="checkbox" name="stove_connecting_pipe"
							value="not_have_pipe_strap">
					</p>
					<p class="col-xs-1">
						老化:<input type="checkbox" name="stove_connecting_pipe"
							value="Aging_through_walls">
					</p>
					<p class="col-xs-1">
						穿墙:<input type="checkbox" name="stove_connecting_pipe"
							value="through_walls">
					</p></td>

			</tr>








			<tr>
				<td rowspan="5" style="">
					<p class="col-xs-12" style="text-align: center;">
						</br> </br> 热</br> </br>水</br> </br>器
					</p></td>
				<td colspan="8">
					<p class="col-xs-2">热水器品牌:</p> <input type="text"
					name="calorifier_brand" class="col-xs-2">
					<p class="col-xs-3">
						位置:<input type="text" name="calorifier_address">
					</p>
					<p class="col-xs-2">购置时间:</p> <input type="date"
					name="calorifier_buy_date" class="col-xs-2">
				</td>

			</tr>

			<tr>

				<td colspan="8">
					<p class="col-xs-2">排气方式:</p>
					<p class="col-xs-1">
						烟道式:<input type="checkbox" name="calorifier_exhaust_type"
							value="flue_type">
					</p>
					<p class="col-xs-1">
						强排试:<input type="checkbox" name="calorifier_exhaust_type"
							value="JSQ">
					</p>
					<p class="col-xs-1">
						平衡式:<input type="checkbox" name="calorifier_exhaust_type"
							value="balanced_type">
					</p></td>

			</tr>
			<tr>

				<td colspan="8">
					<p class="col-xs-2">烟道状况:</p>
					<p class="col-xs-1">
						无烟道:<input type="checkbox" name="calorifier_flue_state"
							value="not_have_flue">
					</p>
					<p class="col-xs-1">
						脱开:<input type="checkbox" name="calorifier_flue_state"
							value="dlurt">
					</p>
					<p class="col-xs-1">
						破损:<input type="checkbox" name="calorifier_flue_state"
							value="Damaged">
					</p>
					<p class="col-xs-2">
						未完全伸至室外:<input type="checkbox" name="calorifier_flue_state"
							value="not_entirely_out_of_the_window">
					</p></td>

			</tr>

			<tr>

				<td colspan="8">
					<p class="col-xs-2">烟道材质:</p>
					<p class="col-xs-1">
						铝箔:<input type="checkbox" name="calorifier_flue_texture"
							value="aluminum_foil">
					</p>
					<p class="col-xs-2">
						不锈钢:<input type="checkbox" name="calorifier_flue_texture"
							value="stainless_steel">
					</p>
					<p class="col-xs-2">燃烧状况:</p>
					<p class="col-xs-1">
						正常:<input type="checkbox" name="calorifier_burn_statu"
							value="normal">
					</p>
					<p class="col-xs-1">
						不正常:<input type="checkbox" name="calorifier_burn_statu"
							value="abnormality">
					</p></td>

			</tr>

			<tr>

				<td colspan="10">
					<p class="col-xs-2">热水器连接管:</p>
					<p class="col-xs-1">
						过长:<input type="checkbox" name="calorifier_connecting_pipe"
							value="too_long">
					</p>
					<p class="col-xs-1">
						无管卡:<input type="checkbox" name="calorifier_connecting_pipe"
							value="not_have_pipe_strap">
					</p>
					<p class="col-xs-1">
						老化:<input type="checkbox" name="calorifier_connecting_pipe"
							value="Aging_through_walls">
					</p>
					<p class="col-xs-1">
						穿墙:<input type="checkbox" name="calorifier_connecting_pipe"
							value="through_walls">
					</p></td>

			</tr>

			<tr>
				<td colspan="2" id="spTD" style="border-right: 0px solid red"></td>
				<td colspan="9" style="border-left: 0px solid red">
					<p class="col-xs-2">安全宣传资料:</p>
					<p class="col-xs-1">
						已发放:<input type="checkbox" name="security_manual" value="issued">
					</p>
					<p class="col-xs-1">
						未发放:<input type="checkbox" name="security_manual" value="passed">
					</p></td>


			</tr>

			<tr>
				<td rowspan="2" style="width: 4%;">
					<h3 class="col-xs-12" style="text-align: center;">
						</br> </br> </br> </br> 整</br> </br>改</br>
					</h3></td>
				<td colspan="3">
					<p class="col-xs-12">违章、隐患情况</p></td>
				<td colspan="3">
					<p class="col-xs-12">整改建议</p></td>
				<td colspan="3">
					<p class="col-xs-12">客户反馈</p></td>

			</tr>

			<tr>
				<td colspan="3">
					<p class="col-xs-12">
						<textarea rows="6" cols="30" style="border: 0px"
							name="case_description"></textarea>
					</p></td>
				<td colspan="3">
					<p class="col-xs-12">
						<textarea rows="6" cols="30" style="border: 0px" name=""></textarea>
					<p class="col-xs-12">
						安检人员签名:<input type="text" name="personnel_signature">
					</p>
					</p>
				</td>
				<td colspan="3">
					<p class="col-xs-12">意见或建议:</p> <textarea rows="5" cols="35"
						style="border: 0px" name="opinion"></textarea>
					<p class="col-xs-4">
						满意:<input type="checkbox" value="satisfaction" name="satisfaction">
					</p>
					<p class="col-xs-4">
						基本满意:<input type="checkbox" name="satisfaction"
							value="vary_satisfaction">
					</p>
					<p class="col-xs-4">
						不满意:<input type="checkbox" name="satisfaction"
							value="no_satisfaction">
					</p>
					<div id="spDIV">
						<p class="col-xs-2">签名:</p>
						<p class="col-xs-9">
							<input type="text" name="client_signature">
						</p>
					</div>
				</td>
			</tr>
			<tr>

				<td rowspan="3" style="width: 4%;">
					<h3 class="col-xs-12" style="text-align: center;">
						</br> 话</br> </br>务</br> </br>员</br> </br>回</br> </br>访</br> </br>记</br> </br>录</br> </br>
					</h3>
				</td>
				<td colspan="6">
					<p class="col-xs-12">
						回访时间: <input type="date" name="callback_date">
					</p>
				</td>
				<td colspan="3">
					<p class="col-xs-12">温馨提示:</p>
				</td>



			</tr>
			<tr>
				<td colspan="6">
					<div style="float: left">
						<p class="col-xs-12">回记记录:</p>
						<textarea rows="5" cols="45" style="border: 0px "
							name="reply_record"></textarea>
					</div>
				</td>
				<td colspan="3">
					<p class="col-xs-11" style="text-indent:2em;" id="tests">尊敬的客户：感谢您选购虎门能源投资有限公司的产品！安检服务是我公司对客户的免费服务项目
						，不收任何费用.燃气安检人员上门进行检查时，必须统一穿着我公司的工作服，并佩戴工作证，如客户对工作人员的身份和服务有任何一问
						和意见，请记下工作人员的工号，并统一拨打我公司的统一客服热线（85191111）进行咨询，感谢你对我们安检工作的配合！</p>
			</tr>
			<tr>
				<td colspan="3">
					<p>
						话务员签字： <input type="text" name="operator_to_sign">
					</p>
				</td>

				<td colspan="6">
					<p>
						工号： <input type="text" name="operator_id">
					</p>
				</td>
			</tr>
			<tr>
			<td colspan="11" style="text-align: center;"> <button type="button" id="save">保存</button></td>
			</tr>
			
		</table>
	</form>

	<script type="text/javascript"
		src="${contextPath}/js/familyCheck/table.js"></script>

	<script type="text/javascript"
		src="${contextPath}/js/familyCheck/content_list.js"></script>
</body>
</html>
