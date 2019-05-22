package com.qian.mobile.entity;

import java.util.Date;

import com.qian.entity.OrderInfo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-1-20 上午10:06:23
 * @Description
 */
public class MobileOrderInfo {

	private int id;

	private String order_code;

	private int client_id;

	private String client_code;

	private String client_name;

	private String air_bottle_specifications;

	private String business_type_name;

	private String delivery_type_name;

	private String pay_type_name;

	private int order_number;

	private String order_address;

	private String order_tel_number;

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

	private Double fee_total_amount;// 零件收费总金额

	private Date order_appointment_time1; // 预约时间1

	private Date order_appointment_time2; // 预约时间2

	private String pay_state_description; // 付费状态

	private Integer second_category_id; // 门店ID

	private String second_category_name; // 门店名称

	private String delivery_man_name;// 配送工

	private String operator_name; // 下单人

	public MobileOrderInfo(OrderInfo orderInfo) {

		this.id = orderInfo.getId();
		this.order_code = orderInfo.getOrder_code();
		this.client_id = orderInfo.getClientInfo().getId();
		this.client_code = orderInfo.getClientInfo().getClient_code();
		this.client_name = orderInfo.getClientInfo().getClient_name();
		this.air_bottle_specifications = orderInfo.getAirBottleType().getAir_bottle_specifications();
		this.delivery_type_name = orderInfo.getDeliveryType().getDelivery_type_name();
		this.pay_type_name = orderInfo.getPayType().getPay_type_name();
		this.order_number = orderInfo.getOrder_number();
		this.order_address = orderInfo.getOrder_address();
		this.order_tel_number = orderInfo.getOrder_tel_number();
		this.remark = orderInfo.getRemark();
		this.state_description = orderInfo.getState().getState_description();
		this.floor_subsidies_money = orderInfo.getFloor_subsidies_money();
		this.delivery_fee = orderInfo.getDelivery_fee();
		this.check_fee = orderInfo.getCheck_fee();
		this.price = orderInfo.getPrice();
		this.discount_amount = orderInfo.getDiscount_amount();
		this.total_amount = orderInfo.getTotal_amount();
		this.order_time = orderInfo.getOrder_time();
		this.feeType = orderInfo.getFeeType();
		this.fee_total_amount = orderInfo.getFee_total_amount();
		this.order_appointment_time1 = orderInfo.getOrder_appointment_time1();
		this.order_appointment_time2 = orderInfo.getOrder_appointment_time2();

		if (orderInfo.getPayState() != null) {
			this.pay_state_description = orderInfo.getPayState().getState_description();
		}

		this.second_category_id = orderInfo.getSecondCategory().getId();
		if (orderInfo.getSecondCategory() != null) {
			this.second_category_name = orderInfo.getSecondCategory().getSecond_category_name();
		}
		if (orderInfo.getDelivery_man() != null) {
			this.delivery_man_name = orderInfo.getDelivery_man().getFull_name();
		}

		if (orderInfo.getOperator() != null) {
			this.operator_name = orderInfo.getOperator().getFull_name();
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

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getClient_code() {
		return client_code;
	}

	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getAir_bottle_specifications() {
		return air_bottle_specifications;
	}

	public void setAir_bottle_specifications(String air_bottle_specifications) {
		this.air_bottle_specifications = air_bottle_specifications;
	}

	public String getBusiness_type_name() {
		return business_type_name;
	}

	public void setBusiness_type_name(String business_type_name) {
		this.business_type_name = business_type_name;
	}

	public String getDelivery_type_name() {
		return delivery_type_name;
	}

	public void setDelivery_type_name(String delivery_type_name) {
		this.delivery_type_name = delivery_type_name;
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

	public Double getFee_total_amount() {
		return fee_total_amount;
	}

	public void setFee_total_amount(Double fee_total_amount) {
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

	public String getPay_state_description() {
		return pay_state_description;
	}

	public void setPay_state_description(String pay_state_description) {
		this.pay_state_description = pay_state_description;
	}

	public Integer getSecond_category_id() {
		return second_category_id;
	}

	public void setSecond_category_id(Integer second_category_id) {
		this.second_category_id = second_category_id;
	}

	public String getSecond_category_name() {
		return second_category_name;
	}

	public void setSecond_category_name(String second_category_name) {
		this.second_category_name = second_category_name;
	}

	public String getDelivery_man_name() {
		return delivery_man_name;
	}

	public void setDelivery_man_name(String delivery_man_name) {
		this.delivery_man_name = delivery_man_name;
	}

	public String getOperator_name() {
		return operator_name;
	}

	public void setOperator_name(String operator_name) {
		this.operator_name = operator_name;
	}

}
