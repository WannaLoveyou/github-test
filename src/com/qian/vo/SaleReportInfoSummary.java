package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-8-25 上午10:41:14
 * @Description
 */
public class SaleReportInfoSummary {

	private String order_date;

	private Integer second_category_id;

	private String second_category_name;

	private Double x_coordinate;

	private Double y_coordinate;

	private String air_bottle_specifications;

	private String delivery_type_name;

	private int warehouseOrderBottleNumber;

	private int orderNumber;

	private int orderBottleNumber;

	private double total_amount;

	public SaleReportInfoSummary() {
		this.orderNumber = 0;
		this.total_amount = 0;
		this.orderBottleNumber = 0;
		this.warehouseOrderBottleNumber = 0;
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

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public int getOrderBottleNumber() {
		return orderBottleNumber;
	}

	public void setOrderBottleNumber(int orderBottleNumber) {
		this.orderBottleNumber = orderBottleNumber;
	}

	public int getWarehouseOrderBottleNumber() {
		return warehouseOrderBottleNumber;
	}

	public void setWarehouseOrderBottleNumber(int warehouseOrderBottleNumber) {
		this.warehouseOrderBottleNumber = warehouseOrderBottleNumber;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public Double getX_coordinate() {
		return x_coordinate;
	}

	public void setX_coordinate(Double x_coordinate) {
		this.x_coordinate = x_coordinate;
	}

	public Double getY_coordinate() {
		return y_coordinate;
	}

	public void setY_coordinate(Double y_coordinate) {
		this.y_coordinate = y_coordinate;
	}

	public Integer getSecond_category_id() {
		return second_category_id;
	}

	public void setSecond_category_id(Integer second_category_id) {
		this.second_category_id = second_category_id;
	}

}
