package com.qian.mobile.entity;

/**
 * @author Lu Kongwen
 * @version Create time：2017-2-24 上午9:19:30
 * @Description
 */
public class MobileAirBottleCheckEntity {

	private Integer userId;

	private Integer warehouseId;

	private Integer deliveryManId;

	private Integer clientId;

	private Integer orderId;

	private String bottleCode;

	private String orderCode;

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getDeliveryManId() {
		return deliveryManId;
	}

	public void setDeliveryManId(Integer deliveryManId) {
		this.deliveryManId = deliveryManId;
	}

	public String getBottleCode() {
		return bottleCode;
	}

	public void setBottleCode(String bottleCode) {
		this.bottleCode = bottleCode;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}
