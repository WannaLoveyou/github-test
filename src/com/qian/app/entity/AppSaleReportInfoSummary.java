package com.qian.app.entity;

import com.qian.vo.SaleReportInfoSummary;

/**
 * @author Lu Kongwen
 * @version Create time：2018-1-9 下午4:07:34
 * @Description
 */
public class AppSaleReportInfoSummary {

	private String saleDate;

	private Integer storeId;

	private String storeName;

	private String materielTypeName;

	private int orderNumber;

	private int orderBottleNumber;

	private double totalAmount;

	public AppSaleReportInfoSummary(SaleReportInfoSummary saleReportInfoSummary) {
		this.saleDate = saleReportInfoSummary.getOrder_date();
		this.storeId = saleReportInfoSummary.getSecond_category_id();
		this.storeName = saleReportInfoSummary.getSecond_category_name();
		this.materielTypeName = saleReportInfoSummary.getAir_bottle_specifications();
		this.orderNumber = saleReportInfoSummary.getOrderNumber();
		this.orderBottleNumber = saleReportInfoSummary.getOrderBottleNumber();
		this.totalAmount = saleReportInfoSummary.getTotal_amount();
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getMaterielTypeName() {
		return materielTypeName;
	}

	public void setMaterielTypeName(String materielTypeName) {
		this.materielTypeName = materielTypeName;
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

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

}
