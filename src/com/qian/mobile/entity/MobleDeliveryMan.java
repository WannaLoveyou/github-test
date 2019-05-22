package com.qian.mobile.entity;

import com.qian.entity.User;

/**
 * @author Lu Kongwen
 * @version Create time：2016-5-5 上午10:15:26
 * @Description
 */
public class MobleDeliveryMan {

	private int deliveryManId;

	private String deliveryManCardCode;

	private String deliveryManUserName;

	private String deliveryManFullName;

	public MobleDeliveryMan(User user) {
		this.deliveryManId = user.getId();
		this.deliveryManCardCode = user.getCard_code();
		this.deliveryManUserName = user.getUsername();
		this.deliveryManFullName = user.getFull_name();
	}

	public int getDeliveryManId() {
		return deliveryManId;
	}

	public void setDeliveryManId(int deliveryManId) {
		this.deliveryManId = deliveryManId;
	}

	public String getDeliveryManUserName() {
		return deliveryManUserName;
	}

	public void setDeliveryManUserName(String deliveryManUserName) {
		this.deliveryManUserName = deliveryManUserName;
	}

	public String getDeliveryManFullName() {
		return deliveryManFullName;
	}

	public void setDeliveryManFullName(String deliveryManFullName) {
		this.deliveryManFullName = deliveryManFullName;
	}

	public String getDeliveryManCardCode() {
		return deliveryManCardCode;
	}

	public void setDeliveryManCardCode(String deliveryManCardCode) {
		this.deliveryManCardCode = deliveryManCardCode;
	}

}
