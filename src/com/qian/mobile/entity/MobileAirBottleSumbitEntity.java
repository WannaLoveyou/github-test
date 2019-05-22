package com.qian.mobile.entity;

import java.util.List;

/**
 * @author Lu Kongwen
 * @version Create time：2017-2-24 上午9:23:42
 * @Description
 */
public class MobileAirBottleSumbitEntity {

	private Integer userId;

	private Integer warehouseId;

	private String sendBottleCodes;

	private String returnBottleCodes;

	private String bottleCode;

	private String bottleCodes;

	private String bottleIds;

	private Integer deliveryManId;

	private Integer clientId;

	private String deliveryManCode;

	private String clientCode;

	private String cardCode;

	private String orderCode;

	private Long operatingTime;

	private Integer materialTypeId;

	private Integer inventoryNum;

	private List<String> photos;

	private Integer auditorId;

	private Integer orderId;

	private Integer airBottleTypeId;

	private Double longitude;

	private Double latitude;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getDeliveryManId() {
		return deliveryManId;
	}

	public void setDeliveryManId(Integer deliveryManId) {
		this.deliveryManId = deliveryManId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getBottleCodes() {
		return bottleCodes;
	}

	public void setBottleCodes(String bottleCodes) {
		this.bottleCodes = bottleCodes;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Long getOperatingTime() {
		return operatingTime;
	}

	public void setOperatingTime(Long operatingTime) {
		this.operatingTime = operatingTime;
	}

	public String getDeliveryManCode() {
		return deliveryManCode;
	}

	public void setDeliveryManCode(String deliveryManCode) {
		this.deliveryManCode = deliveryManCode;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getInventoryNum() {
		return inventoryNum;
	}

	public void setInventoryNum(Integer inventoryNum) {
		this.inventoryNum = inventoryNum;
	}

	public Integer getMaterialTypeId() {
		return materialTypeId;
	}

	public void setMaterialTypeId(Integer materialTypeId) {
		this.materialTypeId = materialTypeId;
	}

	public String getSendBottleCodes() {
		return sendBottleCodes;
	}

	public void setSendBottleCodes(String sendBottleCodes) {
		this.sendBottleCodes = sendBottleCodes;
	}

	public String getReturnBottleCodes() {
		return returnBottleCodes;
	}

	public void setReturnBottleCodes(String returnBottleCodes) {
		this.returnBottleCodes = returnBottleCodes;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getBottleCode() {
		return bottleCode;
	}

	public void setBottleCode(String bottleCode) {
		this.bottleCode = bottleCode;
	}

	public Integer getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}

	public String getBottleIds() {
		return bottleIds;
	}

	public void setBottleIds(String bottleIds) {
		this.bottleIds = bottleIds;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getAirBottleTypeId() {
		return airBottleTypeId;
	}

	public void setAirBottleTypeId(Integer airBottleTypeId) {
		this.airBottleTypeId = airBottleTypeId;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

}
