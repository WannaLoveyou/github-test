package com.qian.vo;


/**
 * @author Lu Kongwen
 * @version Create time：2016-9-24 上午11:16:53
 * @Description
 */
public class SaleDetailReportInfoVo {

	private String saleTime;

	private int secondCategoryId;

	private int airBottleTypeId;

	private int deliveryTypeId;

	private int orderNumber;

	private double total_amount;

	public SaleDetailReportInfoVo() {

	}

	public SaleDetailReportInfoVo(String saleTime, int secondCategoryId, int airBottleTypeId, int deliveryTypeId, int orderNumber, double total_amount) {
		this.saleTime = saleTime;
		this.secondCategoryId = secondCategoryId;
		this.airBottleTypeId = airBottleTypeId;
		this.deliveryTypeId = deliveryTypeId;
		this.orderNumber = orderNumber;
		this.total_amount = total_amount;
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

	public String getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}

}
