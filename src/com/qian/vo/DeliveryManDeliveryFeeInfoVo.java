package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-18 上午10:53:40
 * @Description
 */
public class DeliveryManDeliveryFeeInfoVo {

	private Integer airBottleTypeId;

	private Integer deliveryManId;

	private Integer order_number;

	private Double floor_subsidies_money;

	private Double delivery_fee;

	public DeliveryManDeliveryFeeInfoVo() {

	}

	public DeliveryManDeliveryFeeInfoVo(Integer airBottleTypeId, Integer order_number, Integer deliveryManId, Double floor_subsidies_money, Double delivery_fee) {

		this.airBottleTypeId = airBottleTypeId;
		this.order_number = order_number;
		this.deliveryManId = deliveryManId;
		this.floor_subsidies_money = floor_subsidies_money;
		this.delivery_fee = delivery_fee;
	}

	public Integer getAirBottleTypeId() {
		return airBottleTypeId;
	}

	public void setAirBottleTypeId(Integer airBottleTypeId) {
		this.airBottleTypeId = airBottleTypeId;
	}

	public Integer getDeliveryManId() {
		return deliveryManId;
	}

	public void setDeliveryManId(Integer deliveryManId) {
		this.deliveryManId = deliveryManId;
	}

	public Double getFloor_subsidies_money() {
		return floor_subsidies_money;
	}

	public void setFloor_subsidies_money(Double floor_subsidies_money) {
		this.floor_subsidies_money = floor_subsidies_money;
	}

	public Double getDelivery_fee() {
		return delivery_fee;
	}

	public void setDelivery_fee(Double delivery_fee) {
		this.delivery_fee = delivery_fee;
	}

	public Integer getOrder_number() {
		return order_number;
	}

	public void setOrder_number(Integer order_number) {
		this.order_number = order_number;
	}

}
