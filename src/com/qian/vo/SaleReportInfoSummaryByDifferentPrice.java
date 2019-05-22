package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-19 下午2:29:56
 * @Description
 */
public class SaleReportInfoSummaryByDifferentPrice {

	private String order_date;

	private String second_category_name;

	private String air_bottle_specifications;

	private double price;

	private int orderNumber;

	private int orderBottleNumber;

	private double total_amount;

	private String pay_type;

	public SaleReportInfoSummaryByDifferentPrice() {

		this.orderNumber = 0;
		this.orderBottleNumber = 0;
		this.total_amount = 0;
	}

	public SaleReportInfoSummaryByDifferentPrice(String second_category_name,
			String air_bottle_specifications, double price,
			Long orderBottleNumber, double total_amount, String pay_type) {
		this.second_category_name = second_category_name;
		this.air_bottle_specifications = air_bottle_specifications;
		this.price = price;
		this.orderNumber = 0;
		this.orderBottleNumber = orderBottleNumber.intValue();
		this.total_amount = total_amount;
		this.pay_type = pay_type;
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

	public String getAir_bottle_specifications() {
		return air_bottle_specifications;
	}

	public void setAir_bottle_specifications(String air_bottle_specifications) {
		this.air_bottle_specifications = air_bottle_specifications;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

}
