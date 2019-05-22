package com.qian.app.entity;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-14 上午10:58:57
 * @Description
 */
public class AppAddFamilyCheckOrder {

	private Integer clientId; // 客户ID

	private String orderTelNumber; // 订单电话号码

	private String orderAddress; // 订单地址

	private String orderAppointmentTime; // 预约时间

	private String remark; // 备注;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getOrderTelNumber() {
		return orderTelNumber;
	}

	public void setOrderTelNumber(String orderTelNumber) {
		this.orderTelNumber = orderTelNumber;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
