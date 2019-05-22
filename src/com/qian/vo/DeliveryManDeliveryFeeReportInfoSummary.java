package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-18 上午10:47:17
 * @Description
 */
public class DeliveryManDeliveryFeeReportInfoSummary {

	private String delivery_man_full_name;

	private String air_bottle_specifications;

	private Integer order_num;

	private Integer order_bottle_num;

	private Double delivery_fee;

	private Double floor_subsidies_money;

	public DeliveryManDeliveryFeeReportInfoSummary() {
		this.order_num = 0;
		this.order_bottle_num = 0;
		this.delivery_fee = 0.0;
		this.floor_subsidies_money = 0.0;
	}

	public String getDelivery_man_full_name() {
		return delivery_man_full_name;
	}

	public void setDelivery_man_full_name(String delivery_man_full_name) {
		this.delivery_man_full_name = delivery_man_full_name;
	}

	public String getAir_bottle_specifications() {
		return air_bottle_specifications;
	}

	public void setAir_bottle_specifications(String air_bottle_specifications) {
		this.air_bottle_specifications = air_bottle_specifications;
	}

	public Double getDelivery_fee() {
		return delivery_fee;
	}

	public void setDelivery_fee(Double delivery_fee) {
		this.delivery_fee = delivery_fee;
	}

	public Double getFloor_subsidies_money() {
		return floor_subsidies_money;
	}

	public void setFloor_subsidies_money(Double floor_subsidies_money) {
		this.floor_subsidies_money = floor_subsidies_money;
	}

	public Integer getOrder_num() {
		return order_num;
	}

	public void setOrder_num(Integer order_num) {
		this.order_num = order_num;
	}

	public Integer getOrder_bottle_num() {
		return order_bottle_num;
	}

	public void setOrder_bottle_num(Integer order_bottle_num) {
		this.order_bottle_num = order_bottle_num;
	}

}
