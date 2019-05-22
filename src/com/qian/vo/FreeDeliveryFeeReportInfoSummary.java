package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-9-29 下午4:15:42
 * @Description
 */
public class FreeDeliveryFeeReportInfoSummary {

	private String second_category_name;

	private String air_bottle_specifications;

	private int orderBottleNumber;

	private Double delivery_fee;

	private double total_amount;

	public FreeDeliveryFeeReportInfoSummary() {
		this.orderBottleNumber = 0;
	}

	public String getSecond_category_name() {
		return second_category_name;
	}

	public void setSecond_category_name(String second_category_name) {
		this.second_category_name = second_category_name;
	}

	public String getAir_bottle_specifications() {
		return air_bottle_specifications;
	}

	public void setAir_bottle_specifications(String air_bottle_specifications) {
		this.air_bottle_specifications = air_bottle_specifications;
	}

	public int getOrderBottleNumber() {
		return orderBottleNumber;
	}

	public void setOrderBottleNumber(int orderBottleNumber) {
		this.orderBottleNumber = orderBottleNumber;
	}

	public Double getDelivery_fee() {
		return delivery_fee;
	}

	public void setDelivery_fee(Double delivery_fee) {
		this.delivery_fee = delivery_fee;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

}
