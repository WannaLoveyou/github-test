package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2017-1-16 上午11:20:29
 * @Description
 */
public class SaleModeReportInfoSummary {

	private String mode_name;

	private String second_category_name;

	private String air_bottle_specifications;

	private String delivery_type_name;

	private int orderNumber;

	private int orderBottleNumber;

	private double total_amount;

	public SaleModeReportInfoSummary() {

	}

	public SaleModeReportInfoSummary(String mode_name, int orderNumber) {
		this.mode_name = mode_name;
		this.orderNumber = orderNumber;
	}

	public String getMode_name() {
		return mode_name;
	}

	public void setMode_name(String mode_name) {
		this.mode_name = mode_name;
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

	public String getDelivery_type_name() {
		return delivery_type_name;
	}

	public void setDelivery_type_name(String delivery_type_name) {
		this.delivery_type_name = delivery_type_name;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getOrderBottleNumber() {
		return orderBottleNumber;
	}

	public void setOrderBottleNumber(int orderBottleNumber) {
		this.orderBottleNumber = orderBottleNumber;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

}
