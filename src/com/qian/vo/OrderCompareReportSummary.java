package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-8 下午4:39:20
 * @Description
 */
public class OrderCompareReportSummary {

	private String order_date;

	private String second_category_name;

	private int pickUpInStoresOrderNumber;

	private int pickUpInStoresOrderBottleNumber;

	private int deliveryOrderNumber;

	private int deliveryOrderBottleNumber;

	private int cancelOrderNumber;

	private int speedUpOrderNum;

	private int speedUpTimes;

	public OrderCompareReportSummary() {
		this.pickUpInStoresOrderNumber = 0;
		this.pickUpInStoresOrderBottleNumber = 0;
		this.deliveryOrderNumber = 0;
		this.deliveryOrderBottleNumber = 0;
		this.cancelOrderNumber = 0;
		this.speedUpOrderNum = 0;
		this.speedUpTimes = 0;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getSecond_category_name() {
		return second_category_name;
	}

	public void setSecond_category_name(String second_category_name) {
		this.second_category_name = second_category_name;
	}

	public int getPickUpInStoresOrderNumber() {
		return pickUpInStoresOrderNumber;
	}

	public void setPickUpInStoresOrderNumber(int pickUpInStoresOrderNumber) {
		this.pickUpInStoresOrderNumber = pickUpInStoresOrderNumber;
	}

	public int getPickUpInStoresOrderBottleNumber() {
		return pickUpInStoresOrderBottleNumber;
	}

	public void setPickUpInStoresOrderBottleNumber(int pickUpInStoresOrderBottleNumber) {
		this.pickUpInStoresOrderBottleNumber = pickUpInStoresOrderBottleNumber;
	}

	public int getDeliveryOrderNumber() {
		return deliveryOrderNumber;
	}

	public void setDeliveryOrderNumber(int deliveryOrderNumber) {
		this.deliveryOrderNumber = deliveryOrderNumber;
	}

	public int getDeliveryOrderBottleNumber() {
		return deliveryOrderBottleNumber;
	}

	public void setDeliveryOrderBottleNumber(int deliveryOrderBottleNumber) {
		this.deliveryOrderBottleNumber = deliveryOrderBottleNumber;
	}

	public int getCancelOrderNumber() {
		return cancelOrderNumber;
	}

	public void setCancelOrderNumber(int cancelOrderNumber) {
		this.cancelOrderNumber = cancelOrderNumber;
	}

	public int getSpeedUpOrderNum() {
		return speedUpOrderNum;
	}

	public void setSpeedUpOrderNum(int speedUpOrderNum) {
		this.speedUpOrderNum = speedUpOrderNum;
	}

	public int getSpeedUpTimes() {
		return speedUpTimes;
	}

	public void setSpeedUpTimes(int speedUpTimes) {
		this.speedUpTimes = speedUpTimes;
	}

}
