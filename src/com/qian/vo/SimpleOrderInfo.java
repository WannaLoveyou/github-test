package com.qian.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.qian.entity.DeliveryType;
import com.qian.entity.OrderAirBottleInfo;
import com.qian.entity.OrderEmptyBottleInfo;
import com.qian.entity.PayState;
import com.qian.entity.PayType;
import com.qian.entity.SecondCategory;
import com.qian.entity.User;
import com.qian.util.ObjectToTUtils;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-20 下午4:58:49
 * @Description
 */
public class SimpleOrderInfo {

	private int id;

	private String order_code;

	private Integer client_id;

	private String client_code;

	private String card_code;

	private String client_name;

	private String mobile_tel_number_1;

	private String mobile_tel_number_2;

	private String fixed_tel_number_1;

	private String fixed_tel_number_2;

	private String air_bottle_specifications;

	private Integer business_type_id;

	private String business_type_name;

	private Integer delivery_type_id;

	private String delivery_type_name;

	private Integer pay_type_id;

	private String pay_type_name;

	private int order_number;

	private String order_address;

	private String order_tel_number;

	private Integer delivery_man_id;

	private String delivery_man_full_name;

	private Date delivery_time;

	private Integer operator_id;

	private String operator_full_name;

	private String remark;

	private String state_description;

	private double floor_subsidies_money;

	private double delivery_fee;

	private double check_fee;

	private double price;

	private double discount_amount;

	private double total_amount;

	private Date order_time;

	private String feeType;// 收费项目

	private double fee_total_amount;// 零件收费总金额

	private Date order_appointment_time1;

	private Date order_appointment_time2;

	private int print_times;

	private Integer pay_state_id;

	private String pay_state_description;

	private String non_qrcode_heavy_bottle;

	private String non_qrcode_empty_bottle;

	private int is_speed_up; // 改为催气次数

	private Date report_time;

	private Integer secondCategory_id;

	private String second_category_name;

	private String orderAirBottleInfos;

	private String orderEmptyBottleInfos;

	private int invoice_num;

	private Integer invoice_operator_id;

	private String invoice_operator_full_name;

	private Date invoice_time;

	private Integer cancel_invoice_operator_id;

	private String cancel_invoice_operator_full_name;

	private Date cancel_invoice_time;

	private String cancel_invoice_remark;

	public SimpleOrderInfo() {

	}

	public SimpleOrderInfo(Map<String, Object> map) {

		this.id = ObjectToTUtils.toInteger(map.get("id"));
		this.order_code = ObjectToTUtils.toString(map.get("order_code"));
		this.client_id = ObjectToTUtils.toInteger(map.get("client_id"));
		this.client_code = ObjectToTUtils.toString(map.get("client_code"));
		this.card_code = ObjectToTUtils.toString(map.get("card_code"));
		this.client_name = ObjectToTUtils.toString(map.get("client_name"));
		this.mobile_tel_number_1 = ObjectToTUtils.toString(map.get("mobile_tel_number_1"));
		this.mobile_tel_number_2 = ObjectToTUtils.toString(map.get("mobile_tel_number_2"));
		this.fixed_tel_number_1 = ObjectToTUtils.toString(map.get("fixed_tel_number_1"));
		this.fixed_tel_number_2 = ObjectToTUtils.toString(map.get("fixed_tel_number_2"));
		this.air_bottle_specifications = ObjectToTUtils.toString(map.get("air_bottle_specifications"));
		this.business_type_id = ObjectToTUtils.toInteger(map.get("business_type_id"));
		this.delivery_type_id = ObjectToTUtils.toInteger(map.get("delivery_type_id"));
		this.pay_type_id = ObjectToTUtils.toInteger(map.get("pay_type_id"));
		this.order_number = ObjectToTUtils.toInteger(map.get("order_number"));
		this.order_address = ObjectToTUtils.toString(map.get("order_address"));
		this.order_tel_number = ObjectToTUtils.toString(map.get("order_tel_number"));
		this.delivery_man_id = ObjectToTUtils.toInteger(map.get("delivery_man_id"));
		this.delivery_time = ObjectToTUtils.toDate(map.get("delivery_time"));
		this.operator_id = ObjectToTUtils.toInteger(map.get("operator_id"));
		this.remark = ObjectToTUtils.toString(map.get("remark"));
		this.state_description = ObjectToTUtils.toString(map.get("state_description"));
		this.floor_subsidies_money = ObjectToTUtils.toDouble(map.get("floor_subsidies_money"));
		this.delivery_fee = ObjectToTUtils.toDouble(map.get("delivery_fee"));
		this.check_fee = ObjectToTUtils.toDouble(map.get("check_fee"));
		this.price = ObjectToTUtils.toDouble(map.get("price"));
		this.discount_amount = ObjectToTUtils.toDouble(map.get("discount_amount"));
		this.total_amount = ObjectToTUtils.toDouble(map.get("total_amount"));
		this.order_time = ObjectToTUtils.toDate(map.get("order_time"));
		this.feeType = ObjectToTUtils.toString(map.get("feeType"));
		this.fee_total_amount = ObjectToTUtils.toDouble(map.get("fee_total_amount"));
		this.order_appointment_time1 = ObjectToTUtils.toDate(map.get("order_appointment_time1"));
		this.order_appointment_time2 = ObjectToTUtils.toDate(map.get("order_appointment_time2"));
		this.print_times = ObjectToTUtils.toInteger(map.get("print_times"));
		this.pay_state_id = ObjectToTUtils.toInteger(map.get("pay_state_id"));
		this.non_qrcode_heavy_bottle = ObjectToTUtils.toString(map.get("non_qrcode_heavy_bottle"));
		this.non_qrcode_empty_bottle = ObjectToTUtils.toString(map.get("non_qrcode_empty_bottle"));
		this.is_speed_up = ObjectToTUtils.toInteger(map.get("is_speed_up"));
		this.report_time = ObjectToTUtils.toDate(map.get("report_time"));
		this.secondCategory_id = ObjectToTUtils.toInteger(map.get("secondCategory_id"));

		this.invoice_num = ObjectToTUtils.toInteger(map.get("invoice_num"));
		this.invoice_operator_id = ObjectToTUtils.toInteger(map.get("invoice_operator_id"));
		this.invoice_time = ObjectToTUtils.toDate(map.get("invoice_time"));

		this.cancel_invoice_operator_id = ObjectToTUtils.toInteger(map.get("cancel_invoice_operator_id"));
		this.cancel_invoice_time = ObjectToTUtils.toDate(map.get("cancel_invoice_time"));
		this.cancel_invoice_remark = ObjectToTUtils.toString(map.get("cancel_invoice_remark"));
	}

	public void init(DeliveryType deliveryType, PayType payType, PayState payState, User deliveryMan, User operator, User invoiceOperator,
			User cancelInvoiceOperator, SecondCategory secondCategory, List<OrderAirBottleInfo> orderAirBottleInfos,
			List<OrderEmptyBottleInfo> orderEmptyBottleInfos) {

		if (deliveryType != null) {
			this.delivery_type_name = deliveryType.getDelivery_type_name();
		}

		if (payType != null) {
			this.pay_type_name = payType.getPay_type_name();
		}

		if (payState != null) {
			this.pay_state_description = payState.getState_description();
		}

		if (deliveryMan != null) {
			this.delivery_man_full_name = deliveryMan.getFull_name();
		}

		if (operator != null) {
			this.operator_full_name = operator.getFull_name();
		}

		if (invoiceOperator != null) {
			this.invoice_operator_full_name = invoiceOperator.getFull_name();
		}

		if (cancelInvoiceOperator != null) {
			this.cancel_invoice_operator_full_name = cancelInvoiceOperator.getFull_name();
		}

		if (secondCategory != null) {
			this.second_category_name = secondCategory.getSecond_category_name();
		}

		this.orderAirBottleInfos = "";
		if (orderAirBottleInfos != null) {
			for (OrderAirBottleInfo orderAirBottleInfo : orderAirBottleInfos) {
				this.orderAirBottleInfos += orderAirBottleInfo.getAirBottleInfo().getAir_bottle_code() + ",";
			}
		}

		this.orderEmptyBottleInfos = "";
		if (orderEmptyBottleInfos != null) {
			for (OrderEmptyBottleInfo orderEmptyBottleInfo : orderEmptyBottleInfos) {
				this.orderEmptyBottleInfos += orderEmptyBottleInfo.getAirBottleInfo().getAir_bottle_code() + ",";
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

	public String getClient_code() {
		return client_code;
	}

	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}

	public String getCard_code() {
		return card_code;
	}

	public void setCard_code(String card_code) {
		this.card_code = card_code;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getMobile_tel_number_1() {
		return mobile_tel_number_1;
	}

	public void setMobile_tel_number_1(String mobile_tel_number_1) {
		this.mobile_tel_number_1 = mobile_tel_number_1;
	}

	public String getMobile_tel_number_2() {
		return mobile_tel_number_2;
	}

	public void setMobile_tel_number_2(String mobile_tel_number_2) {
		this.mobile_tel_number_2 = mobile_tel_number_2;
	}

	public String getFixed_tel_number_1() {
		return fixed_tel_number_1;
	}

	public void setFixed_tel_number_1(String fixed_tel_number_1) {
		this.fixed_tel_number_1 = fixed_tel_number_1;
	}

	public String getFixed_tel_number_2() {
		return fixed_tel_number_2;
	}

	public void setFixed_tel_number_2(String fixed_tel_number_2) {
		this.fixed_tel_number_2 = fixed_tel_number_2;
	}

	public String getAir_bottle_specifications() {
		return air_bottle_specifications;
	}

	public void setAir_bottle_specifications(String air_bottle_specifications) {
		this.air_bottle_specifications = air_bottle_specifications;
	}

	public Integer getBusiness_type_id() {
		return business_type_id;
	}

	public void setBusiness_type_id(Integer business_type_id) {
		this.business_type_id = business_type_id;
	}

	public String getBusiness_type_name() {
		return business_type_name;
	}

	public void setBusiness_type_name(String business_type_name) {
		this.business_type_name = business_type_name;
	}

	public Integer getDelivery_type_id() {
		return delivery_type_id;
	}

	public void setDelivery_type_id(Integer delivery_type_id) {
		this.delivery_type_id = delivery_type_id;
	}

	public String getDelivery_type_name() {
		return delivery_type_name;
	}

	public void setDelivery_type_name(String delivery_type_name) {
		this.delivery_type_name = delivery_type_name;
	}

	public Integer getPay_type_id() {
		return pay_type_id;
	}

	public void setPay_type_id(Integer pay_type_id) {
		this.pay_type_id = pay_type_id;
	}

	public String getPay_type_name() {
		return pay_type_name;
	}

	public void setPay_type_name(String pay_type_name) {
		this.pay_type_name = pay_type_name;
	}

	public int getOrder_number() {
		return order_number;
	}

	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}

	public String getOrder_address() {
		return order_address;
	}

	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}

	public String getOrder_tel_number() {
		return order_tel_number;
	}

	public void setOrder_tel_number(String order_tel_number) {
		this.order_tel_number = order_tel_number;
	}

	public Integer getDelivery_man_id() {
		return delivery_man_id;
	}

	public void setDelivery_man_id(Integer delivery_man_id) {
		this.delivery_man_id = delivery_man_id;
	}

	public String getDelivery_man_full_name() {
		return delivery_man_full_name;
	}

	public void setDelivery_man_full_name(String delivery_man_full_name) {
		this.delivery_man_full_name = delivery_man_full_name;
	}

	public Integer getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(Integer operator_id) {
		this.operator_id = operator_id;
	}

	public String getOperator_full_name() {
		return operator_full_name;
	}

	public void setOperator_full_name(String operator_full_name) {
		this.operator_full_name = operator_full_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState_description() {
		return state_description;
	}

	public void setState_description(String state_description) {
		this.state_description = state_description;
	}

	public double getFloor_subsidies_money() {
		return floor_subsidies_money;
	}

	public void setFloor_subsidies_money(double floor_subsidies_money) {
		this.floor_subsidies_money = floor_subsidies_money;
	}

	public double getDelivery_fee() {
		return delivery_fee;
	}

	public void setDelivery_fee(double delivery_fee) {
		this.delivery_fee = delivery_fee;
	}

	public double getCheck_fee() {
		return check_fee;
	}

	public void setCheck_fee(double check_fee) {
		this.check_fee = check_fee;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(double discount_amount) {
		this.discount_amount = discount_amount;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public double getFee_total_amount() {
		return fee_total_amount;
	}

	public void setFee_total_amount(double fee_total_amount) {
		this.fee_total_amount = fee_total_amount;
	}

	public Date getOrder_appointment_time1() {
		return order_appointment_time1;
	}

	public void setOrder_appointment_time1(Date order_appointment_time1) {
		this.order_appointment_time1 = order_appointment_time1;
	}

	public Date getOrder_appointment_time2() {
		return order_appointment_time2;
	}

	public void setOrder_appointment_time2(Date order_appointment_time2) {
		this.order_appointment_time2 = order_appointment_time2;
	}

	public int getPrint_times() {
		return print_times;
	}

	public void setPrint_times(int print_times) {
		this.print_times = print_times;
	}

	public Integer getPay_state_id() {
		return pay_state_id;
	}

	public void setPay_state_id(Integer pay_state_id) {
		this.pay_state_id = pay_state_id;
	}

	public String getPay_state_description() {
		return pay_state_description;
	}

	public void setPay_state_description(String pay_state_description) {
		this.pay_state_description = pay_state_description;
	}

	public String getNon_qrcode_heavy_bottle() {
		return non_qrcode_heavy_bottle;
	}

	public void setNon_qrcode_heavy_bottle(String non_qrcode_heavy_bottle) {
		this.non_qrcode_heavy_bottle = non_qrcode_heavy_bottle;
	}

	public String getNon_qrcode_empty_bottle() {
		return non_qrcode_empty_bottle;
	}

	public void setNon_qrcode_empty_bottle(String non_qrcode_empty_bottle) {
		this.non_qrcode_empty_bottle = non_qrcode_empty_bottle;
	}

	public int getIs_speed_up() {
		return is_speed_up;
	}

	public void setIs_speed_up(int is_speed_up) {
		this.is_speed_up = is_speed_up;
	}

	public Date getReport_time() {
		return report_time;
	}

	public void setReport_time(Date report_time) {
		this.report_time = report_time;
	}

	public Integer getSecondCategory_id() {
		return secondCategory_id;
	}

	public void setSecondCategory_id(Integer secondCategory_id) {
		this.secondCategory_id = secondCategory_id;
	}

	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}

	public String getSecond_category_name() {
		return second_category_name;
	}

	public void setSecond_category_name(String second_category_name) {
		this.second_category_name = second_category_name;
	}

	public String getOrderAirBottleInfos() {
		return orderAirBottleInfos;
	}

	public void setOrderAirBottleInfos(String orderAirBottleInfos) {
		this.orderAirBottleInfos = orderAirBottleInfos;
	}

	public String getOrderEmptyBottleInfos() {
		return orderEmptyBottleInfos;
	}

	public void setOrderEmptyBottleInfos(String orderEmptyBottleInfos) {
		this.orderEmptyBottleInfos = orderEmptyBottleInfos;
	}

	/**
	 * @return the invoice_num
	 */
	public int getInvoice_num() {
		return invoice_num;
	}

	/**
	 * @param invoice_num
	 *            the invoice_num to set
	 */
	public void setInvoice_num(int invoice_num) {
		this.invoice_num = invoice_num;
	}

	/**
	 * @return the invoice_operator_id
	 */
	public Integer getInvoice_operator_id() {
		return invoice_operator_id;
	}

	/**
	 * @param invoice_operator_id
	 *            the invoice_operator_id to set
	 */
	public void setInvoice_operator_id(Integer invoice_operator_id) {
		this.invoice_operator_id = invoice_operator_id;
	}

	/**
	 * @return the invoice_time
	 */
	public Date getInvoice_time() {
		return invoice_time;
	}

	/**
	 * @param invoice_time
	 *            the invoice_time to set
	 */
	public void setInvoice_time(Date invoice_time) {
		this.invoice_time = invoice_time;
	}

	/**
	 * @return the cancel_invoice_operator_id
	 */
	public Integer getCancel_invoice_operator_id() {
		return cancel_invoice_operator_id;
	}

	/**
	 * @param cancel_invoice_operator_id
	 *            the cancel_invoice_operator_id to set
	 */
	public void setCancel_invoice_operator_id(Integer cancel_invoice_operator_id) {
		this.cancel_invoice_operator_id = cancel_invoice_operator_id;
	}

	/**
	 * @return the cancel_invoice_time
	 */
	public Date getCancel_invoice_time() {
		return cancel_invoice_time;
	}

	/**
	 * @param cancel_invoice_time
	 *            the cancel_invoice_time to set
	 */
	public void setCancel_invoice_time(Date cancel_invoice_time) {
		this.cancel_invoice_time = cancel_invoice_time;
	}

	/**
	 * @return the cancel_invoice_remark
	 */
	public String getCancel_invoice_remark() {
		return cancel_invoice_remark;
	}

	/**
	 * @param cancel_invoice_remark
	 *            the cancel_invoice_remark to set
	 */
	public void setCancel_invoice_remark(String cancel_invoice_remark) {
		this.cancel_invoice_remark = cancel_invoice_remark;
	}

	/**
	 * @return the invoice_operator_full_name
	 */
	public String getInvoice_operator_full_name() {
		return invoice_operator_full_name;
	}

	/**
	 * @param invoice_operator_full_name
	 *            the invoice_operator_full_name to set
	 */
	public void setInvoice_operator_full_name(String invoice_operator_full_name) {
		this.invoice_operator_full_name = invoice_operator_full_name;
	}

	/**
	 * @return the cancel_invoice_operator_full_name
	 */
	public String getCancel_invoice_operator_full_name() {
		return cancel_invoice_operator_full_name;
	}

	/**
	 * @param cancel_invoice_operator_full_name
	 *            the cancel_invoice_operator_full_name to set
	 */
	public void setCancel_invoice_operator_full_name(String cancel_invoice_operator_full_name) {
		this.cancel_invoice_operator_full_name = cancel_invoice_operator_full_name;
	}

	public Date getDelivery_time() {
		return delivery_time;
	}

	public void setDelivery_time(Date delivery_time) {
		this.delivery_time = delivery_time;
	}

}
