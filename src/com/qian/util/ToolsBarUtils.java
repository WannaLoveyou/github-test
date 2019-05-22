package com.qian.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qian.code.OrderInfoStateCode;
import com.qian.code.BeforeOrAfterFillCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-28 上午11:20:22
 * @Description
 */
public class ToolsBarUtils {

	public static void getClientInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String firstCategoryId = request.getParameter("firstCategory.id");
		if (StringUtils.nonEmpty(firstCategoryId)) {
			l.add("secondCategory.firstCategory.id = ?");
			filed.addInt(Integer.parseInt(firstCategoryId));
		}

		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}

		String create_time_begin_time = request.getParameter("create_time_begin_time");
		if (StringUtils.nonEmpty(create_time_begin_time)) {
			l.add("create_time >= ?");
			filed.addDate(create_time_begin_time);
		}

		String create_time_end_time = request.getParameter("create_time_end_time");
		if (StringUtils.nonEmpty(create_time_end_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(create_time_end_time));
		}

		String client_name = request.getParameter("client_name");

		if (StringUtils.nonEmpty(client_name)) {
			try {

				client_name = URLDecoder.decode(client_name, "UTF-8");

				StringBuffer client_name_str = new StringBuffer();
				client_name_str.append("%").append(client_name).append("%");

				l.add("client_name like ?");
				filed.addStr(client_name_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		String client_code = request.getParameter("client_code");
		if (StringUtils.nonEmpty(client_code)) {
			l.add("client_code = ?");
			filed.addStr(client_code);
		}

		String old_client_code = request.getParameter("old_client_code");
		if (StringUtils.nonEmpty(old_client_code)) {
			l.add("old_client_code = ?");
			filed.addStr(old_client_code);
		}

		String card_code1 = request.getParameter("card_code");
		if (StringUtils.nonEmpty(card_code1)) {
			l.add("card_code = ?");
			filed.addStr(card_code1);
		}

		String tel_number = request.getParameter("tel_number");
		if (StringUtils.nonEmpty(tel_number)) {
			l.add("(mobile_tel_number_1 = ? or mobile_tel_number_2 = ? or fixed_tel_number_1 = ? or fixed_tel_number_2 = ?)");
			filed.addStr(tel_number);
			filed.addStr(tel_number);
			filed.addStr(tel_number);
			filed.addStr(tel_number);
		}

		String client_address = request.getParameter("client_address");

		if (StringUtils.nonEmpty(client_address)) {
			try {

				client_address = URLDecoder.decode(client_address, "UTF-8");

				StringBuffer client_address_str = new StringBuffer();
				client_address_str.append("%").append(client_address).append("%");

				l.add("client_address like ?");
				filed.addStr(client_address_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
		
		String remark = request.getParameter("remark");

		if (StringUtils.nonEmpty(remark)) {
			try {

				remark = URLDecoder.decode(remark, "UTF-8");

				StringBuffer remark_str = new StringBuffer();
				remark_str.append("%").append(remark).append("%");

				l.add("remark like ?");
				filed.addStr(remark_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
		
		String last_order_time_begin_time = request.getParameter("last_order_time_begin_time");
		if (StringUtils.nonEmpty(last_order_time_begin_time)) {
			l.add("last_order_time >= ?");
			filed.addDate(last_order_time_begin_time);
		}

		String last_order_time_end_time = request.getParameter("last_order_time_end_time");
		if (StringUtils.nonEmpty(last_order_time_end_time)) {
			l.add("last_order_time < ?");
			filed.addDate(TimeUtils.getNextDayString(last_order_time_end_time));
		}
		
		String disabled_state = request.getParameter("disabled_state");
		if (StringUtils.nonEmpty(disabled_state)) {
			l.add("disabled_state = ?");
			filed.addInt(Integer.valueOf(disabled_state));
		}
		
	}

	public static void getAirBottleSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String air_bottle_code = request.getParameter("air_bottle_code1");
		try {
			if (StringUtils.nonEmpty(air_bottle_code)) {
				air_bottle_code = URLDecoder.decode(air_bottle_code, "UTF-8");
				l.add("air_bottle_code = ?");
				filed.addStr(air_bottle_code);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String air_bottle_code_status = request.getParameter("air_bottle_code_status");
		if (StringUtils.nonEmpty(air_bottle_code_status)) {
			if("1".equals(air_bottle_code_status)){
				l.add("air_bottle_code is not null");
			}
			if("2".equals(air_bottle_code_status)){
				l.add("air_bottle_code is null");
			}
		}
		
		
		String air_bottle_seal_code = request.getParameter("air_bottle_seal_code1");
		if (StringUtils.nonEmpty(air_bottle_seal_code)) {
			
			StringBuffer air_bottle_seal_code_str = new StringBuffer();
			air_bottle_seal_code_str.append("%").append(air_bottle_seal_code).append("%");
			
			l.add("air_bottle_seal_code like ?");
			filed.addStr(air_bottle_seal_code_str.toString());

		}
		
		String factory_number = request.getParameter("factory_number1");
		if (StringUtils.nonEmpty(factory_number)) {

			StringBuffer factory_number_str = new StringBuffer();
			factory_number_str.append("%").append(factory_number).append("%");

			l.add("factory_number like ?");
			filed.addStr(factory_number_str.toString());

		}
		
		String begin_next_check_time = request.getParameter("begin_next_check_time");
		if (StringUtils.nonEmpty(begin_next_check_time)) {
			l.add("next_check_time >= ?");
			filed.addDate(begin_next_check_time);
		}

		String end_next_check_time = request.getParameter("end_next_check_time");
		if (StringUtils.nonEmpty(end_next_check_time)) {
			l.add("next_check_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_next_check_time));
		}
		
		String begin_create_time = request.getParameter("begin_create_time");
		if (StringUtils.nonEmpty(begin_create_time)) {
			l.add("create_time >= ?");
			filed.addDate(begin_create_time);
		}

		String end_create_time = request.getParameter("end_create_time");
		if (StringUtils.nonEmpty(end_create_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_create_time));
		}
		
		String airBottleBelongId = request.getParameter("airBottleBelong.id");
		if (StringUtils.nonEmpty(airBottleBelongId)) {
			l.add("airBottleBelong.id = ?");
			filed.addInt(Integer.parseInt(airBottleBelongId));
		}

		String airBottleTypeId = request.getParameter("airBottleType.id");
		if (StringUtils.nonEmpty(airBottleTypeId)) {
			l.add("airBottleType.id = ?");
			filed.addInt(Integer.parseInt(airBottleTypeId));
		}
	}

	public static void getRepairInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}

		String thirdCategoryId = request.getParameter("thirdCategory.id");
		if (StringUtils.nonEmpty(thirdCategoryId)) {
			l.add("clientInfo.thirdCategory.id = ?");
			filed.addInt(Integer.parseInt(thirdCategoryId));
		}

		String client_code = request.getParameter("client_code");
		if (StringUtils.nonEmpty(client_code)) {
			l.add("clientInfo.client_code = ?");
			filed.addStr(client_code);
		}

		String begin_time = request.getParameter("begin_time");
		if (StringUtils.nonEmpty(begin_time)) {
			l.add("accept_time >= ?");
			filed.addDate(begin_time);
		}

		String end_time = request.getParameter("end_time");
		if (StringUtils.nonEmpty(end_time)) {
			l.add("accept_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_time));
		}

		String repairInfoStateId = request.getParameter("repairInfoState.id");
		if (StringUtils.nonEmpty(repairInfoStateId)) {
			l.add("repairInfoState.id = ?");
			filed.addInt(Integer.parseInt(repairInfoStateId));
		}
	}

	public static void getcomplaintInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}

		String thirdCategoryId = request.getParameter("thirdCategory.id");
		if (StringUtils.nonEmpty(thirdCategoryId)) {
			l.add("clientInfo.thirdCategory.id = ?");
			filed.addInt(Integer.parseInt(thirdCategoryId));
		}

		String client_code = request.getParameter("client_code");
		if (StringUtils.nonEmpty(client_code)) {
			l.add("clientInfo.client_code = ?");
			filed.addStr(client_code);
		}

		String begin_time = request.getParameter("begin_time");
		if (StringUtils.nonEmpty(begin_time)) {
			l.add("complaint_appointment_time1 >= ?");
			filed.addDate(begin_time);
		}

		String end_time = request.getParameter("end_time");
		if (StringUtils.nonEmpty(end_time)) {
			l.add("complaint_appointment_time1 < ?");
			filed.addDate(TimeUtils.getNextDayString(end_time));
		}

		String complaintInfoStateId = request.getParameter("complaintInfoState.id");
		if (StringUtils.nonEmpty(complaintInfoStateId)) {
			l.add("complaintInfoState.id = ?");
			filed.addInt(Integer.parseInt(complaintInfoStateId));
		}
	}
	
	public static void getVisitInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {
		
		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}
		
		String thirdCategoryId = request.getParameter("thirdCategory.id");
		if (StringUtils.nonEmpty(thirdCategoryId)) {
			l.add("clientInfo.thirdCategory.id = ?");
			filed.addInt(Integer.parseInt(thirdCategoryId));
		}
		
		String client_code = request.getParameter("client_code");
		if (StringUtils.nonEmpty(client_code)) {
			l.add("clientInfo.client_code = ?");
			filed.addStr(client_code);
		}
		
		String begin_time = request.getParameter("begin_time");
		if (StringUtils.nonEmpty(begin_time)) {
			l.add("create_time >= ?");
			filed.addDate(begin_time);
		}

		String end_time = request.getParameter("end_time");
		if (StringUtils.nonEmpty(end_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_time));
		}
		
	}

	public static void getOrderInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}

		String thirdCategoryId = request.getParameter("thirdCategory.id");
		if (StringUtils.nonEmpty(thirdCategoryId)) {
			l.add("clientInfo.thirdCategory.id = ?");
			filed.addInt(Integer.parseInt(thirdCategoryId));
		}

		String client_code = request.getParameter("client_code");
		if (StringUtils.nonEmpty(client_code)) {
			l.add("clientInfo.client_code = ?");
			filed.addStr(client_code);
		}

		String begin_time = request.getParameter("begin_time");
		if (StringUtils.nonEmpty(begin_time)) {
			l.add("order_time >= ?");
			filed.addDate(begin_time);
		}

		String end_time = request.getParameter("end_time");
		if (StringUtils.nonEmpty(end_time)) {
			l.add("order_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_time));
		}

		String begin_report_time = request.getParameter("begin_report_time");
		if (StringUtils.nonEmpty(begin_report_time)) {
			l.add("report_time >= ?");
			filed.addDate(begin_report_time);
		}

		String end_report_time = request.getParameter("end_report_time");
		if (StringUtils.nonEmpty(end_report_time)) {
			l.add("report_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_report_time));
		}

		String begin_appointment_time = request.getParameter("begin_appointment_time");
		if (StringUtils.nonEmpty(begin_appointment_time)) {
			l.add("order_appointment_time1 >= ?");
			filed.addDate(begin_appointment_time);
		}

		String end_appointment_time = request.getParameter("end_appointment_time");
		if (StringUtils.nonEmpty(end_appointment_time)) {
			l.add("order_appointment_time1 < ?");
			filed.addDate(TimeUtils.getNextDayString(end_appointment_time));
		}

		String businessTypeId = request.getParameter("businessType.id");
		if (StringUtils.nonEmpty(businessTypeId)) {
			l.add("businessType.id = ?");
			filed.addInt(Integer.parseInt(businessTypeId));
		}

		String deliveryTypeId = request.getParameter("deliveryType.id");
		if (StringUtils.nonEmpty(deliveryTypeId)) {
			l.add("deliveryType.id = ?");
			filed.addInt(Integer.parseInt(deliveryTypeId));
		}

		String airBottleTypeId = request.getParameter("airBottleType.id");
		if (StringUtils.nonEmpty(airBottleTypeId)) {
			l.add("airBottleType.id = ?");
			filed.addInt(Integer.parseInt(airBottleTypeId));
		}

		String payStateId = request.getParameter("payState.id");
		if (StringUtils.nonEmpty(payStateId)) {
			l.add("payState.id = ?");
			filed.addInt(Integer.parseInt(payStateId));
		}

		String payTypeId = request.getParameter("payType.id");
		if (StringUtils.nonEmpty(payTypeId)) {
			l.add("payType.id = ?");
			filed.addInt(Integer.parseInt(payTypeId));
		}

		String orderInfoStateId = request.getParameter("orderInfoState.id");
		if (StringUtils.nonEmpty(orderInfoStateId)) {
			l.add("state.id = ?");
			filed.addInt(Integer.parseInt(orderInfoStateId));
		}

		String order_number = request.getParameter("order_number");
		if (StringUtils.nonEmpty(order_number)) {
			l.add("order_number = ?");
			filed.addInt(Integer.parseInt(order_number));
		}

		String order_code = request.getParameter("order_code");

		if (StringUtils.nonEmpty(order_code)) {
			try {

				order_code = URLDecoder.decode(order_code, "UTF-8");

				StringBuffer order_code_str = new StringBuffer();
				order_code_str.append("%").append(order_code).append("%");

				l.add("order_code like ?");
				filed.addStr(order_code_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		String delivery_fee = request.getParameter("delivery_fee");
		if (StringUtils.nonEmpty(delivery_fee)) {
			l.add("delivery_fee = ?");
			filed.addDouble(Double.parseDouble(delivery_fee));
		}

		String price = request.getParameter("price");
		if (StringUtils.nonEmpty(price)) {
			l.add("price = ?");
			filed.addDouble(Double.parseDouble(price));
		}

		String operator = request.getParameter("operator");

		if (StringUtils.nonEmpty(operator)) {
			try {

				operator = URLDecoder.decode(operator, "UTF-8");

				StringBuffer operator_str = new StringBuffer();
				operator_str.append("%").append(operator).append("%");

				l.add("operator.full_name like ?");
				filed.addStr(operator_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		String deliveryManId = request.getParameter("deliveryMan.id");
		if (StringUtils.nonEmpty(deliveryManId)) {
			l.add("delivery_man.id = ?");
			filed.addInt(Integer.parseInt(deliveryManId));
		}

		String delivery_man = request.getParameter("delivery_man");

		if (StringUtils.nonEmpty(delivery_man)) {
			try {

				delivery_man = URLDecoder.decode(delivery_man, "UTF-8");

				StringBuffer delivery_man_str = new StringBuffer();
				delivery_man_str.append("%").append(delivery_man).append("%");

				l.add("delivery_man.full_name like ?");
				filed.addStr(delivery_man_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		String tel_number = request.getParameter("tel_number");
		if (StringUtils.nonEmpty(tel_number)) {
			l.add("(clientInfo.mobile_tel_number_1 = ? or clientInfo.mobile_tel_number_2 = ? or clientInfo.fixed_tel_number_1 = ? or clientInfo.fixed_tel_number_2 = ? or order_tel_number = ?)");
			filed.addStr(tel_number);
			filed.addStr(tel_number);
			filed.addStr(tel_number);
			filed.addStr(tel_number);
			filed.addStr(tel_number);
		}

		String client_address = request.getParameter("client_address");

		if (StringUtils.nonEmpty(client_address)) {
			try {

				client_address = URLDecoder.decode(client_address, "UTF-8");

				StringBuffer client_address_str = new StringBuffer();
				client_address_str.append("%").append(client_address).append("%");

				l.add("clientInfo.client_address like ?");
				filed.addStr(client_address_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
		
		String invoiceNum = request.getParameter("invoice_num");
		if (StringUtils.nonEmpty(invoiceNum)) {
			l.add("invoice_num = ?");
			filed.addInt(Integer.parseInt(invoiceNum));
		}
	}

	public static void getWarehouseInventoryInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String empty_bottle_create_begin_time = request.getParameter("empty_bottle_create_begin_time");
		if (StringUtils.nonEmpty(empty_bottle_create_begin_time)) {
			l.add("empty_bottle_create_time >= ?");
			filed.addDate(empty_bottle_create_begin_time);
		}

		String empty_bottle_create_end_time = request.getParameter("empty_bottle_create_end_time");
		if (StringUtils.nonEmpty(empty_bottle_create_end_time)) {
			l.add("empty_bottle_create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(empty_bottle_create_end_time));
		}

		String air_bottle_seal_code = request.getParameter("air_bottle_seal_code");
		if (StringUtils.nonEmpty(air_bottle_seal_code)) {
			l.add("airBottleInfo.air_bottle_seal_code = ?");
			filed.addInt(Integer.parseInt(air_bottle_seal_code));
		}
	}

	public static void getWarehouseOrderInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}

		String warehouse_order_time_begin_time = request.getParameter("warehouse_order_time_begin_time");
		if (StringUtils.nonEmpty(warehouse_order_time_begin_time)) {
			l.add("warehouse_order_time >= ?");
			filed.addDate(warehouse_order_time_begin_time);
		}

		String warehouse_order_time_end_time = request.getParameter("warehouse_order_time_end_time");
		if (StringUtils.nonEmpty(warehouse_order_time_end_time)) {
			l.add("warehouse_order_time < ?");
			filed.addDate(TimeUtils.getNextDayString(warehouse_order_time_end_time));
		}

		String check_begin_time = request.getParameter("check_begin_time");
		if (StringUtils.nonEmpty(check_begin_time)) {
			l.add("check_time >= ?");
			filed.addDate(check_begin_time);
		}

		String check_end_time = request.getParameter("check_end_time");
		if (StringUtils.nonEmpty(check_end_time)) {
			l.add("check_time < ?");
			filed.addDate(TimeUtils.getNextDayString(check_end_time));
		}

		String warehouseOrderInfoStateId = request.getParameter("warehouseOrderInfoState.id");
		if (StringUtils.nonEmpty(warehouseOrderInfoStateId)) {
			l.add("state.id = ?");
			filed.addInt(Integer.parseInt(warehouseOrderInfoStateId));
		}
	}

	public static void getWarehouseOrderDetailsSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("warehouseOrderInfo.secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}

		String warehouse_order_time_begin_time = request.getParameter("begin_time");
		if (StringUtils.nonEmpty(warehouse_order_time_begin_time)) {
			l.add("warehouse_order_time >= ?");
			filed.addDate(warehouse_order_time_begin_time);
		}

		String warehouse_order_time_end_time = request.getParameter("end_time");
		if (StringUtils.nonEmpty(warehouse_order_time_end_time)) {
			l.add("warehouse_order_time < ?");
			filed.addDate(TimeUtils.getNextDayString(warehouse_order_time_end_time));
		}

		String airBottleTypeId = request.getParameter("airBottleType.id");
		if (StringUtils.nonEmpty(airBottleTypeId)) {
			l.add("airBottleType.id = ?");
			filed.addInt(Integer.parseInt(airBottleTypeId));
		}

	}

	public static void getDeliveryManAirBottleInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");

		try {
			if (start_time != null) {
				start_time = URLDecoder.decode(start_time, "UTF-8");
			}
			if (end_time != null) {
				end_time = URLDecoder.decode(end_time, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (StringUtils.nonEmpty(start_time)) {
			long startLoginTime = TimeUtils.getDateTime(start_time);
			l.add("last_operation_time >= ?");
			filed.addDateTime(startLoginTime);
		}

		if (StringUtils.nonEmpty(end_time)) {
			long endLoginTime = TimeUtils.getDateTime(end_time);
			l.add("last_operation_time <= ?");
			filed.addDateTime(endLoginTime);

		}
	}

	public static void getStoreInventoryInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}
	}

	public static void getWarehouseNonQRCodeInventoryInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {
		// TODO Auto-generated method stub

	}

	public static void getWarehouseNonQRCodeInventoryInfoRecordSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String begin_time = request.getParameter("begin_time");
		String end_time = request.getParameter("end_time");
		try {

			begin_time = URLDecoder.decode(begin_time, "UTF-8");
			end_time = URLDecoder.decode(end_time, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (StringUtils.nonEmpty(begin_time)) {
			long beginDateTime = TimeUtils.getDateTime(begin_time);
			l.add("create_time >= ?");
			filed.addDateTime(beginDateTime);
		}

		if (StringUtils.nonEmpty(end_time)) {
			long endDateTime = TimeUtils.getDateTime(end_time);
			l.add("create_time <= ?");
			filed.addDateTime(endDateTime);

		}
	}

	public static void getUserSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String full_name = request.getParameter("full_name1");

		if (StringUtils.nonEmpty(full_name)) {
			try {

				full_name = URLDecoder.decode(full_name, "UTF-8");

				StringBuffer full_name_str = new StringBuffer();
				full_name_str.append("%").append(full_name).append("%");

				l.add("u.full_name like ?");
				filed.addStr(full_name_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		String username = request.getParameter("username1");

		if (StringUtils.nonEmpty(username)) {
			try {

				username = URLDecoder.decode(username, "UTF-8");

				StringBuffer username_str = new StringBuffer();
				username_str.append("%").append(username).append("%");

				l.add("u.username like ?");
				filed.addStr(username_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		String secondCategoryId = request.getParameter("secondCategory1.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("u.secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}

		String card_code = request.getParameter("card_code1");
		if (StringUtils.nonEmpty(card_code)) {
			l.add("u.card_code = ?");
			filed.addStr(card_code);
		}

		String userRoleId = request.getParameter("userRole1.id");
		if (StringUtils.nonEmpty(userRoleId)) {
			l.add("ur.role.id = ? and ur.user.id = u.id");
			filed.addInt(Integer.parseInt(userRoleId));
		}

	}

	public static void getOrderCancelInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}

		String thirdCategoryId = request.getParameter("thirdCategory.id");
		if (StringUtils.nonEmpty(thirdCategoryId)) {
			l.add("clientInfo.thirdCategory.id = ?");
			filed.addInt(Integer.parseInt(thirdCategoryId));
		}

		String client_code = request.getParameter("client_code");
		if (StringUtils.nonEmpty(client_code)) {
			l.add("clientInfo.client_code = ?");
			filed.addStr(client_code);
		}

		String begin_time = request.getParameter("begin_time");
		if (StringUtils.nonEmpty(begin_time)) {
			l.add("order_time >= ?");
			filed.addDate(begin_time);
		}

		String end_time = request.getParameter("end_time");
		if (StringUtils.nonEmpty(end_time)) {
			l.add("order_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_time));
		}
		
		String begin_report_time = request.getParameter("begin_report_time");
		if (StringUtils.nonEmpty(begin_report_time)) {
			l.add("report_time >= ?");
			filed.addDate(begin_report_time);
		}

		String end_report_time = request.getParameter("end_report_time");
		if (StringUtils.nonEmpty(end_report_time)) {
			l.add("report_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_report_time));
		}
		
		String cancel_begin_time = request.getParameter("cancel_begin_time");
		if (StringUtils.nonEmpty(cancel_begin_time)) {
			l.add("cancel_time >= ?");
			filed.addDate(cancel_begin_time);
		}

		String cancel_end_time = request.getParameter("cancel_end_time");
		if (StringUtils.nonEmpty(cancel_end_time)) {
			l.add("cancel_time < ?");
			filed.addDate(TimeUtils.getNextDayString(cancel_end_time));
		}

		String begin_appointment_time = request.getParameter("begin_appointment_time");
		if (StringUtils.nonEmpty(begin_appointment_time)) {
			l.add("(order_appointment_time1 >= ? or order_appointment_time2 >= ?)");
			filed.addDate(begin_appointment_time);
			filed.addDate(begin_appointment_time);
		}

		String end_appointment_time = request.getParameter("end_appointment_time");
		if (StringUtils.nonEmpty(end_appointment_time)) {
			l.add("(order_appointment_time1 < ? or order_appointment_time2 < ?)");
			filed.addDate(TimeUtils.getNextDayString(end_appointment_time));
			filed.addDate(TimeUtils.getNextDayString(end_appointment_time));
		}

		String businessTypeId = request.getParameter("businessType.id");
		if (StringUtils.nonEmpty(businessTypeId)) {
			l.add("businessType.id = ?");
			filed.addInt(Integer.parseInt(businessTypeId));
		}

		String deliveryTypeId = request.getParameter("deliveryType.id");
		if (StringUtils.nonEmpty(deliveryTypeId)) {
			l.add("deliveryType.id = ?");
			filed.addInt(Integer.parseInt(deliveryTypeId));
		}

		String airBottleTypeId = request.getParameter("airBottleType.id");
		if (StringUtils.nonEmpty(airBottleTypeId)) {
			l.add("airBottleType.id = ?");
			filed.addInt(Integer.parseInt(airBottleTypeId));
		}

		String orderInfoStateId = request.getParameter("orderInfoState.id");
		if (StringUtils.nonEmpty(orderInfoStateId)) {
			l.add("state.id = ?");
			filed.addInt(Integer.parseInt(orderInfoStateId));
		}

		String order_code = request.getParameter("order_code");

		if (StringUtils.nonEmpty(order_code)) {
			try {

				order_code = URLDecoder.decode(order_code, "UTF-8");

				StringBuffer order_code_str = new StringBuffer();
				order_code_str.append("%").append(order_code).append("%");

				l.add("order_code like ?");
				filed.addStr(order_code_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		String operator = request.getParameter("operator");

		if (StringUtils.nonEmpty(operator)) {
			try {

				operator = URLDecoder.decode(operator, "UTF-8");

				StringBuffer operator_str = new StringBuffer();
				operator_str.append("%").append(operator).append("%");

				l.add("operator.full_name like ?");
				filed.addStr(operator_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		String delivery_man = request.getParameter("delivery_man");

		if (StringUtils.nonEmpty(delivery_man)) {
			try {

				delivery_man = URLDecoder.decode(operator, "UTF-8");

				StringBuffer delivery_man_str = new StringBuffer();
				delivery_man_str.append("%").append(operator).append("%");

				l.add("delivery_man.full_name like ?");
				filed.addStr(delivery_man_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		String tel_number = request.getParameter("tel_number");
		if (StringUtils.nonEmpty(tel_number)) {
			l.add("(clientInfo.mobile_tel_number_1 = ? or clientInfo.mobile_tel_number_2 = ? or clientInfo.fixed_tel_number_1 = ? or clientInfo.fixed_tel_number_2 = ?)");
			filed.addStr(tel_number);
			filed.addStr(tel_number);
			filed.addStr(tel_number);
			filed.addStr(tel_number);
		}

		String client_address = request.getParameter("client_address");

		if (StringUtils.nonEmpty(client_address)) {
			try {

				client_address = URLDecoder.decode(client_address, "UTF-8");

				StringBuffer client_address_str = new StringBuffer();
				client_address_str.append("%").append(client_address).append("%");

				l.add("clientInfo.client_address like ?");
				filed.addStr(client_address_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

	}

	public static void getAirBottleFinalTrackingRecordSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String begin_time = request.getParameter("begin_time");
		if (StringUtils.nonEmpty(begin_time)) {
			l.add("create_time >= ?");
			filed.addDate(begin_time);
		}

		String end_time = request.getParameter("end_time");
		if (StringUtils.nonEmpty(end_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_time));
		}

		String no_back_time = request.getParameter("no_back_time");
		if (StringUtils.nonEmpty(no_back_time)) {
			l.add("(UNIX_TIMESTAMP(current_timestamp()) - UNIX_TIMESTAMP(create_time)) > ?");
			filed.addInt(Integer.valueOf(no_back_time) * TimeUtils.oneDaySeconds);
		}

		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("operator.secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}

		String airBottleTypeId = request.getParameter("airBottleType.id");
		if (StringUtils.nonEmpty(airBottleTypeId)) {
			l.add("airBottleInfo.airBottleType.id = ?");
			filed.addInt(Integer.parseInt(airBottleTypeId));
		}

		String delivery_man = request.getParameter("delivery_man");
		if (StringUtils.nonEmpty(delivery_man)) {
			try {

				delivery_man = URLDecoder.decode(delivery_man, "UTF-8");

				StringBuffer delivery_man_str = new StringBuffer();
				delivery_man_str.append("%").append(delivery_man).append("%");

				l.add("deliveryMan.full_name like ?");
				filed.addStr(delivery_man_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		String client_code = request.getParameter("client_code");
		if (StringUtils.nonEmpty(client_code)) {
			l.add("clientInfo.client_code = ?");
			filed.addStr(client_code);
		}
	}

	public static void getAirBottleTrackingRecordSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String begin_time = request.getParameter("begin_time");
		if (StringUtils.nonEmpty(begin_time)) {
			l.add("create_time >= ?");
			filed.addDate(begin_time);
		}

		String end_time = request.getParameter("end_time");
		if (StringUtils.nonEmpty(end_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_time));
		}

		String begin_report_time = request.getParameter("begin_report_time");
		if (StringUtils.nonEmpty(begin_report_time)) {
			l.add("create_time >= ?");
			filed.addDate(begin_report_time);
		}

		String end_report_time = request.getParameter("end_report_time");
		if (StringUtils.nonEmpty(end_report_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_report_time));
		}

		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}

		String deliveryManSecondCategoryId = request.getParameter("deliveryManSecondCategory.id");
		if (StringUtils.nonEmpty(deliveryManSecondCategoryId)) {
			l.add("deliveryMan.secondCategory.id = ?");
			filed.addInt(Integer.parseInt(deliveryManSecondCategoryId));
		}

		String deliveryManId = request.getParameter("deliveryMan.id");
		if (StringUtils.nonEmpty(deliveryManId)) {
			l.add("deliveryMan.id = ?");
			filed.addInt(Integer.parseInt(deliveryManId));
		}

		String airBottleTypeId = request.getParameter("airBottleType.id");
		if (StringUtils.nonEmpty(airBottleTypeId)) {
			l.add("airBottleInfo.airBottleType.id = ?");
			filed.addInt(Integer.parseInt(airBottleTypeId));
		}
		
		String air_bottle_seal_code = request.getParameter("air_bottle_seal_code");
		if (StringUtils.nonEmpty(air_bottle_seal_code)) {
			l.add("airBottleInfo.air_bottle_seal_code = ? ");
			filed.addStr(air_bottle_seal_code);
		}
		
		String operator = request.getParameter("delivery_man");

		if (StringUtils.nonEmpty(operator)) {
			try {

				operator = URLDecoder.decode(operator, "UTF-8");

				StringBuffer operator_str = new StringBuffer();
				operator_str.append("%").append(operator).append("%");

				l.add("deliveryMan.full_name like ?");
				filed.addStr(operator_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}


	}

	public static void getClientAirBottleTypeFeeSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String client_name = request.getParameter("client_name1");

		if (StringUtils.nonEmpty(client_name)) {
			try {

				client_name = URLDecoder.decode(client_name, "UTF-8");

				StringBuffer client_name_str = new StringBuffer();
				client_name_str.append("%").append(client_name).append("%");

				l.add("clientInfo.client_name like ?");
				filed.addStr(client_name_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		String client_code = request.getParameter("client_code1");
		if (StringUtils.nonEmpty(client_code)) {
			l.add("clientInfo.client_code = ?");
			filed.addStr(client_code);
		}

		String airBottleTypeId = request.getParameter("airBottleType.id");
		if (StringUtils.nonEmpty(airBottleTypeId)) {
			l.add("airBottleType.id = ?");
			filed.addInt(Integer.parseInt(airBottleTypeId));
		}

		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("clientInfo.secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}

		String create_time_begin_time = request.getParameter("create_time_begin_time");
		if (StringUtils.nonEmpty(create_time_begin_time)) {
			l.add("create_time >= ?");
			filed.addDate(create_time_begin_time);
		}

		String create_time_end_time = request.getParameter("create_time_end_time");
		if (StringUtils.nonEmpty(create_time_end_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(create_time_end_time));
		}

	}

	public static void getExceptionReturnAirBottleCondition(HttpServletRequest request, List<String> l, Field filed) {

		String create_time_begin_time = request.getParameter("create_time_begin_time");
		if (StringUtils.nonEmpty(create_time_begin_time)) {
			l.add("create_time >= ?");
			filed.addDate(create_time_begin_time);
		}

		String create_time_end_time = request.getParameter("create_time_end_time");
		if (StringUtils.nonEmpty(create_time_end_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(create_time_end_time));
		}

		String begin_report_time = request.getParameter("begin_report_time");
		if (StringUtils.nonEmpty(begin_report_time)) {
			l.add("create_time >= ?");
			filed.addDate(begin_report_time);
		}

		String end_report_time = request.getParameter("end_report_time");
		if (StringUtils.nonEmpty(end_report_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_report_time));
		}

		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("operator.secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}

		String exceptionReturnAirBottleStateId = request.getParameter("exceptionReturnAirBottleState.id");
		if (StringUtils.nonEmpty(exceptionReturnAirBottleStateId)) {
			l.add("state.id = ?");
			filed.addInt(Integer.parseInt(exceptionReturnAirBottleStateId));
		}
	}

	public static void getICCardRecordSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String create_time_begin_time = request.getParameter("create_time_begin_time");
		if (StringUtils.nonEmpty(create_time_begin_time)) {
			l.add("create_time >= ?");
			filed.addDate(create_time_begin_time);
		}

		String create_time_end_time = request.getParameter("create_time_end_time");
		if (StringUtils.nonEmpty(create_time_end_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(create_time_end_time));
		}

		String clientSecondCategoryToolId = request.getParameter("clientSecondCategory.id");
		if (StringUtils.nonEmpty(clientSecondCategoryToolId)) {
			l.add("clientInfo.secondCategory.id = ?");
			filed.addInt(Integer.parseInt(clientSecondCategoryToolId));
		}

		String operatorSecondCategoryToolId = request.getParameter("operatorSecondCategory.id");
		if (StringUtils.nonEmpty(operatorSecondCategoryToolId)) {
			l.add("operator.secondCategory.id = ?");
			filed.addInt(Integer.parseInt(operatorSecondCategoryToolId));
		}

		String iCCardRecordStateToolId = request.getParameter("iCCardRecordState.id");
		if (StringUtils.nonEmpty(iCCardRecordStateToolId)) {
			l.add("state.id = ?");
			filed.addInt(Integer.parseInt(iCCardRecordStateToolId));
		}

		String client_code = request.getParameter("client_code");
		if (StringUtils.nonEmpty(client_code)) {
			l.add("clientInfo.client_code = ?");
			filed.addStr(client_code);
		}
	}

	public static void getPartFeeInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("orderInfo.secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}

		String begin_time = request.getParameter("begin_time");
		if (StringUtils.nonEmpty(begin_time)) {
			l.add("orderInfo.order_time >= ?");
			filed.addDate(begin_time);
		}

		String end_time = request.getParameter("end_time");
		if (StringUtils.nonEmpty(end_time)) {
			l.add("orderInfo.order_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_time));
		}

		String begin_report_time = request.getParameter("begin_report_time");
		if (StringUtils.nonEmpty(begin_report_time)) {
			l.add("orderInfo.report_time >= ?");
			filed.addDate(begin_report_time);
		}

		String end_report_time = request.getParameter("end_report_time");
		if (StringUtils.nonEmpty(end_report_time)) {
			l.add("orderInfo.report_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_report_time));
		}

		String airBottleTypeId = request.getParameter("airBottleType.id");
		if (StringUtils.nonEmpty(airBottleTypeId)) {
			l.add("orderInfo.airBottleType.id = ?");
			filed.addInt(Integer.parseInt(airBottleTypeId));
		}
	}

	public static void getFamilyCheckInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {

		String begin_time = request.getParameter("begin_time");
		if (StringUtils.nonEmpty(begin_time)) {
			l.add("appointment_check_time >= ?");
			filed.addDate(begin_time);
		}

		String end_time = request.getParameter("end_time");
		if (StringUtils.nonEmpty(end_time)) {
			l.add("appointment_check_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_time));
		}

		String client_code = request.getParameter("client_code");
		if (StringUtils.nonEmpty(client_code)) {
			l.add("clientInfo.client_code = ?");
			filed.addStr(client_code);
		}

		String family_check_time_begin_time = request.getParameter("family_check_time_begin_time");
		if (StringUtils.nonEmpty(family_check_time_begin_time)) {
			l.add("check_time >= ?");
			filed.addDate(family_check_time_begin_time);
		}

		String family_check_time_end_time = request.getParameter("family_check_time_end_time");
		if (StringUtils.nonEmpty(family_check_time_end_time)) {
			l.add("check_time < ?");
			filed.addDate(TimeUtils.getNextDayString(family_check_time_end_time));
		}
		
		String checkOperatorSecondCategoryId = request.getParameter("checkOperatorSecondCategory.id");
		if (StringUtils.nonEmpty(checkOperatorSecondCategoryId)) {
			l.add("check_operator.secondCategory.id = ?");
			filed.addInt(Integer.parseInt(checkOperatorSecondCategoryId));
		}
	}

	public static void getWeChatActivityInfoCondition(HttpServletRequest request, List<String> l, Field filed) {

		String begin_time = request.getParameter("create_time_begin_time");
		if (StringUtils.nonEmpty(begin_time)) {
			l.add("create_time >= ?");
			filed.addDate(begin_time);
		}

		String end_time = request.getParameter("create_time_end_time");
		if (StringUtils.nonEmpty(end_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_time));
		}
	}

	public static void getStoreCustomAirBottlePriceInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {
		
		String begin_time = request.getParameter("create_time_begin_time");
		if (StringUtils.nonEmpty(begin_time)) {
			l.add("create_time >= ?");
			filed.addDate(begin_time);
		}

		String end_time = request.getParameter("create_time_end_time");
		if (StringUtils.nonEmpty(end_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_time));
		}
		
		String airBottleTypeId = request.getParameter("airBottleType.id");
		if (StringUtils.nonEmpty(airBottleTypeId)) {
			l.add("airBottleType.id = ?");
			filed.addInt(Integer.parseInt(airBottleTypeId));
		}
		
		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}
	}

	public static void getClientAirBottleRecordSearchCondition(HttpServletRequest request, List<String> l, Field filed) {
		
		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("clientInfo.secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}
		
		String clientTypeId = request.getParameter("clientType.id");
		if (StringUtils.nonEmpty(clientTypeId)) {
			l.add("clientInfo.clientType.id = ?");
			filed.addInt(Integer.parseInt(clientTypeId));
		}
		
		String airBottleTypeId = request.getParameter("airBottleType.id");
		if (StringUtils.nonEmpty(airBottleTypeId)) {
			l.add("airBottleInfo.airBottleType.id = ?");
			filed.addInt(Integer.parseInt(airBottleTypeId));
		}
		
		String client_code = request.getParameter("client_code");
		if (StringUtils.nonEmpty(client_code)) {
			l.add("clientInfo.client_code = ?");
			filed.addStr(client_code);
		}
		
	}

	public static void getWarehouseBussinessOrderInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {
		
		String client_code = request.getParameter("client_code");
		if (StringUtils.nonEmpty(client_code)) {
			l.add("clientInfo.client_code = ?");
			filed.addStr(client_code);
		}

		String begin_time = request.getParameter("begin_time");
		if (StringUtils.nonEmpty(begin_time)) {
			l.add("warehouse_order_time >= ?");
			filed.addDate(begin_time);
		}

		String end_time = request.getParameter("end_time");
		if (StringUtils.nonEmpty(end_time)) {
			l.add("warehouse_order_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_time));
		}
		
		String warehouseBusinessOrderInfoStateId = request.getParameter("warehouseBusinessOrderInfoState.id");
		if (StringUtils.nonEmpty(warehouseBusinessOrderInfoStateId)) {
			l.add("state.id = ?");
			filed.addInt(Integer.parseInt(warehouseBusinessOrderInfoStateId));
		}
		
		String operator = request.getParameter("operator");

		if (StringUtils.nonEmpty(operator)) {
			try {

				operator = URLDecoder.decode(operator, "UTF-8");

				StringBuffer operator_str = new StringBuffer();
				operator_str.append("%").append(operator).append("%");

				l.add("warehouse_order_operator.full_name like ?");
				filed.addStr(operator_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
		
		
		String delivery_man = request.getParameter("delivery_man");

		if (StringUtils.nonEmpty(delivery_man)) {
			try {

				delivery_man = URLDecoder.decode(delivery_man, "UTF-8");

				StringBuffer delivery_man_str = new StringBuffer();
				delivery_man_str.append("%").append(delivery_man).append("%");

				l.add("delivery_man.full_name like ?");
				filed.addStr(delivery_man_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		String tel_number = request.getParameter("tel_number");
		if (StringUtils.nonEmpty(tel_number)) {
			l.add("(clientInfo.mobile_tel_number_1 = ? or clientInfo.mobile_tel_number_2 = ? or clientInfo.fixed_tel_number_1 = ? or clientInfo.fixed_tel_number_2 = ? or order_tel_number = ?)");
			filed.addStr(tel_number);
			filed.addStr(tel_number);
			filed.addStr(tel_number);
			filed.addStr(tel_number);
			filed.addStr(tel_number);
		}
		
		
		String warehouse_order_code = request.getParameter("warehouse_order_code");

		if (StringUtils.nonEmpty(warehouse_order_code)) {
			try {

				warehouse_order_code = URLDecoder.decode(warehouse_order_code, "UTF-8");

				StringBuffer warehouse_order_code_str = new StringBuffer();
				warehouse_order_code_str.append("%").append(warehouse_order_code).append("%");

				l.add("warehouse_order_code like ?");
				filed.addStr(warehouse_order_code_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}


	}

	public static void getModuleConfigurationInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {
		// TODO Auto-generated method stub
		
	}

	public static void getInvoiceRecordSearchCondition(HttpServletRequest request, List<String> l, Field filed) {
		
		String begin_report_time = request.getParameter("begin_report_time");
		if (StringUtils.nonEmpty(begin_report_time)) {
			l.add("create_time >= ?");
			filed.addDate(begin_report_time);
		}

		String end_report_time = request.getParameter("end_report_time");
		if (StringUtils.nonEmpty(end_report_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_report_time));
		}
		
		String invoiceRecordStateId = request.getParameter("invoiceRecordState.id");
		if (StringUtils.nonEmpty(invoiceRecordStateId)) {
			l.add("invoiceRecordState.id = ?");
			filed.addInt(Integer.parseInt(invoiceRecordStateId));
		}

		String operator_name = request.getParameter("operator_name");

		if (StringUtils.nonEmpty(operator_name)) {
			try {

				operator_name = URLDecoder.decode(operator_name, "UTF-8");

				StringBuffer operator_name_str = new StringBuffer();
				operator_name_str.append("%").append(operator_name).append("%");

				l.add("operator.full_name like ?");
				filed.addStr(operator_name_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
		
	
	String order_code = request.getParameter("order_code");

	if (StringUtils.nonEmpty(order_code)) {
		try {

			order_code = URLDecoder.decode(order_code, "UTF-8");

			StringBuffer order_code_str = new StringBuffer();
			order_code_str.append("%").append(order_code).append("%");

			l.add("order_code like ?");
			filed.addStr(order_code_str.toString());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}
}

	public static void getStoreBorrowMoneyOrderInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {
		// TODO Auto-generated method stub
		
	}

	public static void getStoreMoneyChangeRecordSearchCondition(HttpServletRequest request, List<String> l, Field filed) {
		
		String begin_report_time = request.getParameter("begin_report_time");
		if (StringUtils.nonEmpty(begin_report_time)) {
			l.add("create_time >= ?");
			filed.addDate(begin_report_time);
		}

		String end_report_time = request.getParameter("end_report_time");
		if (StringUtils.nonEmpty(end_report_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_report_time));
		}
		
		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}
	}

	public static void getStoreMoneyRefundInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {
		// TODO Auto-generated method stub
		
	}

	public static void getStoreBankInfoSearchCondition(HttpServletRequest request, List<String> l, Field field) {
		
		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("secondCategory.id = ?");
			field.addInt(Integer.parseInt(secondCategoryId));
		}
		

		String bank_account = request.getParameter("bank_account");

		if (StringUtils.nonEmpty(bank_account)) {
			try {

				bank_account = URLDecoder.decode(bank_account, "UTF-8");

				StringBuffer bank_account_str = new StringBuffer();
				bank_account_str.append("%").append(bank_account).append("%");

				l.add("bank_account like ?");
				field.addStr(bank_account_str.toString());

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	}
	}

	public static void getWarehouseInfoSearchCondition(HttpServletRequest request, List<String> l, Field field) {
		// TODO Auto-generated method stub
		
	}

	public static void getAirBottleBelongSearchCondition(HttpServletRequest request, List<String> l, Field field) {
		// TODO Auto-generated method stub
		
	}

	public static void getAirBottleRentInfoSearchCondition(HttpServletRequest request, List<String> l, Field field) {
		
		String begin_rent_time = request.getParameter("begin_rent_time");
		if (StringUtils.nonEmpty(begin_rent_time)) {
			l.add("rent_time >= ?");
			field.addDate(begin_rent_time);
		}

		String end_rent_time = request.getParameter("end_rent_time");
		if (StringUtils.nonEmpty(end_rent_time)) {
			l.add("rent_time < ?");
			field.addDate(TimeUtils.getNextDayString(end_rent_time));
		}
		
		String begin_expire_time = request.getParameter("begin_expire_time");
		if (StringUtils.nonEmpty(begin_expire_time)) {
			l.add("expire_time >= ?");
			field.addDate(begin_expire_time);
		}

		String end_expire_time = request.getParameter("end_expire_time");
		if (StringUtils.nonEmpty(end_expire_time)) {
			l.add("expire_time < ?");
			field.addDate(TimeUtils.getNextDayString(end_expire_time));
		}
		
		String begin_back_time = request.getParameter("begin_back_time");
		if (StringUtils.nonEmpty(begin_back_time)) {
			l.add("back_time >= ?");
			field.addDate(begin_back_time);
		}

		String end_back_time = request.getParameter("end_back_time");
		if (StringUtils.nonEmpty(end_back_time)) {
			l.add("back_time < ?");
			field.addDate(TimeUtils.getNextDayString(end_back_time));
		}
		
		String client_code = request.getParameter("client_code");
		if (StringUtils.nonEmpty(client_code)) {
			l.add("clientInfo.client_code = ?");
			field.addStr(client_code);
		}
		
	
	}

	public static void getPhoneRecordSearchCondition(HttpServletRequest request, List<String> l, Field field) {
		
		String begin_call_in_time = request.getParameter("begin_call_in_time");
		if (StringUtils.nonEmpty(begin_call_in_time)) {
			l.add("callInTime >= ?");
			field.addDate(begin_call_in_time);
		}

		String end_call_in_time = request.getParameter("end_call_in_time");
		if (StringUtils.nonEmpty(end_call_in_time)) {
			l.add("callInTime < ?");
			field.addDate(TimeUtils.getNextDayString(end_call_in_time));
		}
		
		String phoneNumber = request.getParameter("phoneNumber");
		if (StringUtils.nonEmpty(phoneNumber)) {
			l.add("phoneNumber = ?");
			field.addStr(phoneNumber);
		}

		String phoneRecordState = request.getParameter("phoneRecordState");
		if (StringUtils.nonEmpty(phoneRecordState)) {
			l.add("phoneRecordState.id = ?");
			field.addInt(Integer.parseInt(phoneRecordState));
		}
	}
	
public static void getPreparedInspectionInfoCondition(HttpServletRequest request, List<String> l, Field field) {
		
		String create_time_begin = request.getParameter("create_time_begin");
		if (StringUtils.nonEmpty(create_time_begin)) {
			l.add("createTime >= ?");
			field.addDate(create_time_begin);
		}	
		String create_time_end = request.getParameter("create_time_end");
		if (StringUtils.nonEmpty(create_time_end)) {
			l.add("createTime <= ?");
			field.addDate(TimeUtils.getNextDayString(create_time_end));
		}
		String materialTypeId = request.getParameter("airBottleType.id");
		if (StringUtils.nonEmpty(materialTypeId)) {
			l.add("airBottleInfo.airBottleType.id = ?");
			field.addInt(Integer.parseInt(materialTypeId));
		}
	}
	
	public static void getInspectionOrderCondition(HttpServletRequest request, List<String> l, Field field) {
		
		String create_time_begin = request.getParameter("create_time_begin");
		if (StringUtils.nonEmpty(create_time_begin)) {
			l.add("createTime >= ?");
			field.addDate(create_time_begin);
		}	
		String create_time_end = request.getParameter("create_time_end");
		if (StringUtils.nonEmpty(create_time_end)) {
			l.add("createTime <= ?");
			field.addDate(TimeUtils.getNextDayString(create_time_end));
		}
		String warehouseId = request.getParameter("warehouse.id");
		if (StringUtils.nonEmpty(warehouseId)) {
			l.add("warehouseInfo.id = ?");
			field.addInt(Integer.parseInt(warehouseId));
		}
	}

public static void getAirBottleImportInfoSearchCondition(HttpServletRequest request, List<String> l, Field filed) {
		
		String create_time_begin_time = request.getParameter("create_time_begin_time");
		if (StringUtils.nonEmpty(create_time_begin_time)) {
			l.add("create_time >= ?");
			filed.addDate(create_time_begin_time);
		}

		String create_time_end_time = request.getParameter("create_time_end_time");
		if (StringUtils.nonEmpty(create_time_end_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(create_time_end_time));
		}

		String airBottleImportInfoStateId = request.getParameter("airBottleImportInfoState.id");
		if (StringUtils.nonEmpty(airBottleImportInfoStateId)) {
			l.add("state.id = ?");
			filed.addInt(Integer.parseInt(airBottleImportInfoStateId));
		}
		
		String air_bottle_seal_code = request.getParameter("air_bottle_seal_code");
		if (StringUtils.nonEmpty(air_bottle_seal_code)) {

			StringBuffer air_bottle_seal_code_str = new StringBuffer();
			air_bottle_seal_code_str.append("%").append(air_bottle_seal_code).append("%");
			l.add("air_bottle_seal_code like ?");
			filed.addStr(air_bottle_seal_code_str.toString());
		}

	}
	
	public static void getOrderPositionList(HttpServletRequest request, List<String> l, Field field) {
		String orderImformationId = request.getParameter("order_imformation.id");
		if (StringUtils.nonEmpty(orderImformationId)) {
			l.add("order_imformation.id = ?");
			field.addInt(Integer.parseInt(orderImformationId));
			l.add("order_imformation.state_id = ?");
			field.addInt(OrderInfoStateCode.ALREADY_DISPATCHING_BUT_NO_DELIVERY);
		}
		String delivery_man_id = request.getParameter("delivery_man_id");
		if (StringUtils.nonEmpty(delivery_man_id)) {
			l.add("user.id = ?");
			field.addInt(Integer.parseInt(delivery_man_id));
		}
		
	}

public static void getFillCheckRecordSearchCondition(HttpServletRequest request, List<String> l, Field filed) {
		
		String begin_report_time = request.getParameter("begin_report_time");
		if (StringUtils.nonEmpty(begin_report_time)) {
			l.add("create_time >= ?");
			filed.addDate(begin_report_time);
		}

		String end_report_time = request.getParameter("end_report_time");
		if (StringUtils.nonEmpty(end_report_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_report_time));
		}
		
		String air_bottle_seal_code = request.getParameter("air_bottle_seal_code");
		if (StringUtils.nonEmpty(air_bottle_seal_code)) {
			l.add("airBottleInfo.air_bottle_seal_code = ? ");
			filed.addStr(air_bottle_seal_code);
		}
		
		String operatorId = request.getParameter("operator.id");
		if (StringUtils.nonEmpty(operatorId)) {
			l.add("operator.id = ?");
			filed.addInt(Integer.parseInt(operatorId));
			
			l.add("before_or_after_fill = ?");
			filed.addInt(BeforeOrAfterFillCode.AFTER_FILLING);
		}
		
	}

	public static void getClientInventorySearchCondition(HttpServletRequest request, List<String> l, Field filed) {
	
		String secondCategoryId = request.getParameter("secondCategory.id");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("clientInfo.secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
		}
	
		String create_time_begin_time = request.getParameter("create_time_begin_time");
		if (StringUtils.nonEmpty(create_time_begin_time)) {
			l.add("create_time >= ?");
			filed.addDate(create_time_begin_time);
		}
	
		String create_time_end_time = request.getParameter("create_time_end_time");
		if (StringUtils.nonEmpty(create_time_end_time)) {
			l.add("create_time < ?");
			filed.addDate(TimeUtils.getNextDayString(create_time_end_time));
		}
	
		String client_name = request.getParameter("client_name");
	
		if (StringUtils.nonEmpty(client_name)) {
			try {
	
				client_name = URLDecoder.decode(client_name, "UTF-8");
	
				StringBuffer client_name_str = new StringBuffer();
				client_name_str.append("%").append(client_name).append("%");
	
				l.add("clientInfo.client_name like ?");
				filed.addStr(client_name_str.toString());
	
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	
		}
	
		String client_code = request.getParameter("client_code");
		if (StringUtils.nonEmpty(client_code)) {
			l.add("clientInfo.client_code = ?");
			filed.addStr(client_code);
		}
	
		String old_client_code = request.getParameter("old_client_code");
		if (StringUtils.nonEmpty(old_client_code)) {
			l.add("clientInfo.old_client_code = ?");
			filed.addStr(old_client_code);
		}
	
		String card_code1 = request.getParameter("card_code");
		if (StringUtils.nonEmpty(card_code1)) {
			l.add("clientInfo.card_code = ?");
			filed.addStr(card_code1);
		}
		
		String tel_number = request.getParameter("tel_number");
		if (StringUtils.nonEmpty(tel_number)) {
			l.add("(clientInfo.mobile_tel_number_1 = ? or clientInfo.mobile_tel_number_2 = ? or clientInfo.fixed_tel_number_1 = ? or clientInfo.fixed_tel_number_2 = ?)");
			filed.addStr(tel_number);
			filed.addStr(tel_number);
			filed.addStr(tel_number);
			filed.addStr(tel_number);
		}
	
		String client_address = request.getParameter("client_address");
	
		if (StringUtils.nonEmpty(client_address)) {
			try {
	
				client_address = URLDecoder.decode(client_address, "UTF-8");
	
				StringBuffer client_address_str = new StringBuffer();
				client_address_str.append("%").append(client_address).append("%");
	
				l.add("clientInfo.client_address like ?");
				filed.addStr(client_address_str.toString());
	
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	
		}
		
		String airBottleTypeId = request.getParameter("airBottleType.id");
		if (StringUtils.nonEmpty(airBottleTypeId)) {
			l.add("airBottleInfo.airBottleType.id = ?");
			filed.addInt(Integer.parseInt(airBottleTypeId));
		}
		
		String airBottleBelongId = request.getParameter("airBottleBelong.id");
		if (StringUtils.nonEmpty(airBottleBelongId)) {
			l.add("airBottleInfo.airBottleBelong.id = ?");
			filed.addInt(Integer.parseInt(airBottleBelongId));
		}
		
	}

}
