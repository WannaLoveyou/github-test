package com.qian.vo;


/**
 * @author Lu Kongwen
 * @version Create time：2016-11-22 下午5:00:36
 * @Description
 */
public class PartSaleReportInfoVo {

	private String order_date;

	private int secondCategoryId;

	private int airBottleTypeId;

	private int partTypeId;

	private int number;

	private double price;

	private double total_amount;

	public PartSaleReportInfoVo(String order_time, Integer secondCategoryId, Integer airBottleTypeId, Integer partTypeId, Integer number, Double price,
			Double total_amount) {

		this.order_date = order_time;
		this.secondCategoryId = secondCategoryId;
		this.airBottleTypeId = airBottleTypeId;
		this.partTypeId = partTypeId;
		this.number = number;
		this.price = price;
		this.total_amount = total_amount;
	}

	public PartSaleReportInfoVo() {

	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
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

	public int getPartTypeId() {
		return partTypeId;
	}

	public void setPartTypeId(int partTypeId) {
		this.partTypeId = partTypeId;
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
