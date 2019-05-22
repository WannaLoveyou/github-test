package com.qian.vo;

import java.util.Date;

/**
 * @author Lu Kongwen
 * @version Create time：2016-8-25 上午9:26:53
 * @Description
 */
public class SaleReportInfoVo {

	private int secondCategoryId;

	private int airBottleTypeId;

	private int deliveryTypeId;

	private int is_speed_up;

	private int orderNumber;

	private double price;

	private double total_amount;

	private Date order_time;

	public SaleReportInfoVo() {

	}

	public SaleReportInfoVo(int secondCategoryId, int airBottleTypeId, int deliveryTypeId, int orderNumber, double price, double total_amount, Date order_time,
			int is_speed_up) {
		this.secondCategoryId = secondCategoryId;
		this.airBottleTypeId = airBottleTypeId;
		this.deliveryTypeId = deliveryTypeId;
		this.orderNumber = orderNumber;
		this.price = price;
		this.total_amount = total_amount;
		this.order_time = order_time;
		this.is_speed_up = is_speed_up;
	}

	public int getSecondCategoryId() {
		return secondCategoryId;
	}

	public void setSecondCategoryId(int secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
	}

	public int getAirBottleTypeId() {
		return airBottleTypeId;
	}

	public void setAirBottleTypeId(int airBottleTypeId) {
		this.airBottleTypeId = airBottleTypeId;
	}

	public int getDeliveryTypeId() {
		return deliveryTypeId;
	}

	public void setDeliveryTypeId(int deliveryTypeId) {
		this.deliveryTypeId = deliveryTypeId;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getIs_speed_up() {
		return is_speed_up;
	}

	public void setIs_speed_up(int is_speed_up) {
		this.is_speed_up = is_speed_up;
	}

}
