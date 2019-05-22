package com.qian.app.entity;

import com.qian.entity.OrderInfo;
import com.qian.util.TimeUtils;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-21 下午4:25:12
 * @Description
 */
public class AppOrderInfo {

	private int orderId; // 订单ID

	private String airBottleSpecifications; // 气瓶规格

	private int orderNumber; // 订单数量

	private double price; // 单价

	private double totalAmount; // 总金额

	private int stateId; // 订单状态ID

	private String stateDescription; // 订单状态

	private String orderTime; // 订单时间

	private String paystateDescription; // 支付状态

	public AppOrderInfo() {

	}

	public AppOrderInfo(OrderInfo orderInfo) {

		this.orderId = orderInfo.getId();
		this.airBottleSpecifications = orderInfo.getAirBottleType().getAir_bottle_specifications();
		this.orderNumber = orderInfo.getOrder_number();
		this.price = orderInfo.getPrice();
		this.stateId = orderInfo.getState().getId();
		this.stateDescription = orderInfo.getState().getState_description();
		this.orderTime = TimeUtils.getyyyyMMddHHmmStringByDate(orderInfo.getOrder_time());
		this.totalAmount = orderInfo.getTotal_amount();

		if (orderInfo.getPayState() != null) {
			this.paystateDescription = orderInfo.getPayState().getState_description();
		}
	}
	

	public String getAirBottleSpecifications() {
		return airBottleSpecifications;
	}

	public void setAirBottleSpecifications(String airBottleSpecifications) {
		this.airBottleSpecifications = airBottleSpecifications;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStateDescription() {
		return stateDescription;
	}

	public void setStateDescription(String stateDescription) {
		this.stateDescription = stateDescription;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getPaystateDescription() {
		return paystateDescription;
	}

	public void setPaystateDescription(String paystateDescription) {
		this.paystateDescription = paystateDescription;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

}
