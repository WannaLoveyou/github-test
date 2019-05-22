package com.qian.mobile.entity;

import com.qian.entity.AirBottleType;

/**
 * @author Lu Kongwen
 * @version Create time：2016-6-4 下午2:48:04
 * @Description
 */
public class MobileAirBottleType {

	private int id;

	private String air_bottle_specifications; // 气瓶类型名称

	private Double default_bottle_weight; // 默认重量

	private String default_volume; // 默认容积

	private Integer default_use_cycle; // 默认设计使用年限

	private Integer default_detection_cycle; // 默认检测周期

	private Double price; // 单价

	private Double delivery_fee; // 送气费

	public MobileAirBottleType(AirBottleType airBottleType) {

		this.id = airBottleType.getId();
		this.air_bottle_specifications = airBottleType.getAir_bottle_specifications();
		this.default_use_cycle = airBottleType.getDefault_use_cycle();
		this.default_detection_cycle = airBottleType.getDefault_detection_cycle();
		this.default_bottle_weight = airBottleType.getBottle_weight();
		this.default_volume = airBottleType.getVolume();
		this.price = airBottleType.getPrice();
		this.delivery_fee = airBottleType.getDelivery_fee();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAir_bottle_specifications() {
		return air_bottle_specifications;
	}

	public void setAir_bottle_specifications(String air_bottle_specifications) {
		this.air_bottle_specifications = air_bottle_specifications;
	}

	public Double getDefault_bottle_weight() {
		return default_bottle_weight;
	}

	public void setDefault_bottle_weight(Double default_bottle_weight) {
		this.default_bottle_weight = default_bottle_weight;
	}

	public String getDefault_volume() {
		return default_volume;
	}

	public void setDefault_volume(String default_volume) {
		this.default_volume = default_volume;
	}

	public Integer getDefault_use_cycle() {
		return default_use_cycle;
	}

	public void setDefault_use_cycle(Integer default_use_cycle) {
		this.default_use_cycle = default_use_cycle;
	}

	public Integer getDefault_detection_cycle() {
		return default_detection_cycle;
	}

	public void setDefault_detection_cycle(Integer default_detection_cycle) {
		this.default_detection_cycle = default_detection_cycle;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDelivery_fee() {
		return delivery_fee;
	}

	public void setDelivery_fee(Double delivery_fee) {
		this.delivery_fee = delivery_fee;
	}

}
