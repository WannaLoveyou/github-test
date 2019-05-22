package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-21 下午4:54:11
 * @Description
 */
public class PartSaleReportInfoSummary {

	private String order_date;

	private String second_category_name;

	private String air_bottle_specifications;

	private String part_type_name;

	private int number;

	private double price;

	private double total_amount;

	public PartSaleReportInfoSummary(){
		this.number = 0;
		this.total_amount = 0;
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

	public String getPart_type_name() {
		return part_type_name;
	}

	public void setPart_type_name(String part_type_name) {
		this.part_type_name = part_type_name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

}
