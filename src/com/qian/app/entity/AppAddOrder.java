package com.qian.app.entity;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-22 下午4:00:38
 * @Description
 */
public class AppAddOrder {

	private String openid; // openid

	private int clientId; // 用户ID

	private int airBottleTypeId; // 气瓶类型ID

	private int floorSubsidiesId; // 楼层补贴方案ID

	private int orderNumber; // 订单数量

	private String orderTelNumber; // 订单电话号码

	private int payTypeId; // 付款方式ID

	private String orderAddress; // 订单地址

	private String remark; // 备注;

	private String orderAppointmentTime; // 预约时间

	private Double price; // 单价

	private Double deliveryFee; // 送气费

	private Integer userId; // 用户ID

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getAirBottleTypeId() {
		return airBottleTypeId;
	}

	public void setAirBottleTypeId(int airBottleTypeId) {
		this.airBottleTypeId = airBottleTypeId;
	}

	public int getFloorSubsidiesId() {
		return floorSubsidiesId;
	}

	public void setFloorSubsidiesId(int floorSubsidiesId) {
		this.floorSubsidiesId = floorSubsidiesId;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderTelNumber() {
		return orderTelNumber;
	}

	public void setOrderTelNumber(String orderTelNumber) {
		this.orderTelNumber = orderTelNumber;
	}

	public int getPayTypeId() {
		return payTypeId;
	}

	public void setPayTypeId(int payTypeId) {
		this.payTypeId = payTypeId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getOrderAppointmentTime() {
		return orderAppointmentTime;
	}

	public void setOrderAppointmentTime(String orderAppointmentTime) {
		this.orderAppointmentTime = orderAppointmentTime;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(Double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
