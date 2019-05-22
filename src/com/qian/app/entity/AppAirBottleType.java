package com.qian.app.entity;

import com.qian.entity.AirBottleType;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-20 下午5:16:47
 * @Description
 */
public class AppAirBottleType {

	private int airBottleTypeId; // 气瓶类型ID

	private String airBottleSpecifications; // 气瓶规格

	private double deliveryFee; // 送气费

	private String measurementUnit; // 计量单位

	private double price; // 气瓶价格

	private double originalPrice; // 原价

	private String appImg; // app图片路径

	private String airBottleProductName; // 气瓶产品名称

	private String airBottleLengthWidth; // 气瓶直径

	private String hasGasWeight; // 含气重量

	public AppAirBottleType(AirBottleType airBottleType) {

		this.airBottleTypeId = airBottleType.getId();
		this.airBottleSpecifications = airBottleType.getAir_bottle_specifications();
		this.deliveryFee = airBottleType.getDelivery_fee();
		this.measurementUnit = airBottleType.getMeasurement_unit();
		this.price = airBottleType.getWeixin_discounty_fee(); // 微信使用微信价格
		this.appImg = airBottleType.getApp_img();
		this.airBottleProductName = airBottleType.getAir_bottle_product_name();
		this.airBottleLengthWidth = airBottleType.getAir_bottle_length_width();
		this.hasGasWeight = airBottleType.getHas_gas_weight();
		this.originalPrice = airBottleType.getPrice(); // 原价
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getAppImg() {
		return appImg;
	}

	public void setAppImg(String appImg) {
		this.appImg = appImg;
	}

	public String getAirBottleProductName() {
		return airBottleProductName;
	}

	public void setAirBottleProductName(String airBottleProductName) {
		this.airBottleProductName = airBottleProductName;
	}

	public String getAirBottleLengthWidth() {
		return airBottleLengthWidth;
	}

	public void setAirBottleLengthWidth(String airBottleLengthWidth) {
		this.airBottleLengthWidth = airBottleLengthWidth;
	}

	public String getHasGasWeight() {
		return hasGasWeight;
	}

	public void setHasGasWeight(String hasGasWeight) {
		this.hasGasWeight = hasGasWeight;
	}

	public int getAirBottleTypeId() {
		return airBottleTypeId;
	}

	public void setAirBottleTypeId(int airBottleTypeId) {
		this.airBottleTypeId = airBottleTypeId;
	}

	public String getAirBottleSpecifications() {
		return airBottleSpecifications;
	}

	public void setAirBottleSpecifications(String airBottleSpecifications) {
		this.airBottleSpecifications = airBottleSpecifications;
	}

	public double getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
