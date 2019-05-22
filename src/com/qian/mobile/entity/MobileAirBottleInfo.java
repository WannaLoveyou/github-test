package com.qian.mobile.entity;

import java.util.Date;
import java.util.List;

import com.qian.entity.AirBottleInfo;

/**
 * @author Lu Kongwen
 * @version Create time：2017-2-20 下午4:34:18
 * @Description
 */

public class MobileAirBottleInfo {

	private int air_bottle_id;

	private String air_bottle_code; // 气瓶编码

	private String air_bottle_seal_code; // 气瓶钢码

	private String factory_number;// 出厂编号

	private String volume; // 容积

	private Double bottle_weight; // 气瓶重量

	private Integer use_cycle;

	private Integer air_bottle_type_id; // 物料类型名

	private String air_bottle_type_name; // 物料类型名

	private Integer production_unit_id; // 生产厂家ID

	private String production_unit_name; // 生产厂家

	private Integer detection_unit_id; // 检修单位ID

	private String detection_unit_name; // 检修单位名

	private Integer air_bottle_belong_id; // 气瓶归属单位ID

	private String air_bottle_belong_name; // 气瓶归属单位单位名

	private Date produce_time; // 生产日期

	private Long produce_time_tmp; // 生产日期Long

	private Date check_time; // 检测日期

	private Long check_time_tmp; // 检测日期Long

	private Date next_check_time; // 下次检测日期

	private Long next_check_time_tmp; // 下次检测日期Long

	private Date safe_time; // 安全日期

	private Date expiration_time; // 过期日期

	private List<MobileAirBottleTrackingRecord> mobileAirBottleTrackingRecords;

	private List<String> photos;

	private String bottle_model;

	public MobileAirBottleInfo() {

	}

	public MobileAirBottleInfo(AirBottleInfo airBottleInfo) {
		this(airBottleInfo, null);
	}

	public MobileAirBottleInfo(AirBottleInfo airBottleInfo, List<MobileAirBottleTrackingRecord> mobileAirBottleTrackingRecords) {

		this.air_bottle_id = airBottleInfo.getId();
		this.air_bottle_code = airBottleInfo.getAir_bottle_code();
		this.air_bottle_seal_code = airBottleInfo.getAir_bottle_seal_code();
		this.factory_number = airBottleInfo.getFactory_number();
		this.volume = airBottleInfo.getVolume();
		this.bottle_weight = airBottleInfo.getBottle_weight();
		this.use_cycle = airBottleInfo.getUse_cycle();

		if (airBottleInfo.getAirBottleType() != null) {
			this.air_bottle_type_id = airBottleInfo.getAirBottleType().getId();
			this.air_bottle_type_name = airBottleInfo.getAirBottleType().getAir_bottle_specifications();
			this.bottle_model = airBottleInfo.getAirBottleType().getBottle_model();
		}

		if (airBottleInfo.getAirBottleBelong() != null) {
			this.air_bottle_belong_id = airBottleInfo.getAirBottleBelong().getId();
			this.air_bottle_belong_name = airBottleInfo.getAirBottleBelong().getBlong_name();
		}

		this.production_unit_id = airBottleInfo.getProductionUnit().getId();
		this.production_unit_name = airBottleInfo.getProductionUnit().getProduction_unit_name();

		if (airBottleInfo.getDetectionUnit() != null) {
			this.detection_unit_id = airBottleInfo.getDetectionUnit().getId();
			this.detection_unit_name = airBottleInfo.getDetectionUnit().getDetection_unit_name();
		}

		this.produce_time = airBottleInfo.getProduce_time();
		this.check_time = airBottleInfo.getCheck_time();
		this.next_check_time = airBottleInfo.getNext_check_time();

		if (this.produce_time != null) {
			this.produce_time_tmp = this.produce_time.getTime();
		}

		if (this.check_time != null) {
			this.check_time_tmp = this.check_time.getTime();
		}

		if (this.next_check_time != null) {
			this.next_check_time_tmp = this.next_check_time.getTime();
		}

		this.mobileAirBottleTrackingRecords = mobileAirBottleTrackingRecords;
	}

	public int getAir_bottle_id() {
		return air_bottle_id;
	}

	public void setAir_bottle_id(int air_bottle_id) {
		this.air_bottle_id = air_bottle_id;
	}

	public String getAir_bottle_code() {
		return air_bottle_code;
	}

	public void setAir_bottle_code(String air_bottle_code) {
		this.air_bottle_code = air_bottle_code;
	}

	public String getAir_bottle_seal_code() {
		return air_bottle_seal_code;
	}

	public void setAir_bottle_seal_code(String air_bottle_seal_code) {
		this.air_bottle_seal_code = air_bottle_seal_code;
	}

	public String getFactory_number() {
		return factory_number;
	}

	public void setFactory_number(String factory_number) {
		this.factory_number = factory_number;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public Double getBottle_weight() {
		return bottle_weight;
	}

	public void setBottle_weight(Double bottle_weight) {
		this.bottle_weight = bottle_weight;
	}

	public Integer getAir_bottle_type_id() {
		return air_bottle_type_id;
	}

	public void setAir_bottle_type_id(Integer air_bottle_type_id) {
		this.air_bottle_type_id = air_bottle_type_id;
	}

	public String getAir_bottle_type_name() {
		return air_bottle_type_name;
	}

	public void setAir_bottle_type_name(String air_bottle_type_name) {
		this.air_bottle_type_name = air_bottle_type_name;
	}

	public Integer getProduction_unit_id() {
		return production_unit_id;
	}

	public void setProduction_unit_id(Integer production_unit_id) {
		this.production_unit_id = production_unit_id;
	}

	public String getProduction_unit_name() {
		return production_unit_name;
	}

	public void setProduction_unit_name(String production_unit_name) {
		this.production_unit_name = production_unit_name;
	}

	public Integer getDetection_unit_id() {
		return detection_unit_id;
	}

	public void setDetection_unit_id(Integer detection_unit_id) {
		this.detection_unit_id = detection_unit_id;
	}

	public String getDetection_unit_name() {
		return detection_unit_name;
	}

	public void setDetection_unit_name(String detection_unit_name) {
		this.detection_unit_name = detection_unit_name;
	}

	public Date getProduce_time() {
		return produce_time;
	}

	public void setProduce_time(Date produce_time) {
		this.produce_time = produce_time;
	}

	public Long getProduce_time_tmp() {
		return produce_time_tmp;
	}

	public void setProduce_time_tmp(Long produce_time_tmp) {
		this.produce_time_tmp = produce_time_tmp;
	}

	public Date getCheck_time() {
		return check_time;
	}

	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}

	public Long getCheck_time_tmp() {
		return check_time_tmp;
	}

	public void setCheck_time_tmp(Long check_time_tmp) {
		this.check_time_tmp = check_time_tmp;
	}

	public Date getNext_check_time() {
		return next_check_time;
	}

	public void setNext_check_time(Date next_check_time) {
		this.next_check_time = next_check_time;
	}

	public Long getNext_check_time_tmp() {
		return next_check_time_tmp;
	}

	public void setNext_check_time_tmp(Long next_check_time_tmp) {
		this.next_check_time_tmp = next_check_time_tmp;
	}

	public Date getSafe_time() {
		return safe_time;
	}

	public void setSafe_time(Date safe_time) {
		this.safe_time = safe_time;
	}

	public Date getExpiration_time() {
		return expiration_time;
	}

	public void setExpiration_time(Date expiration_time) {
		this.expiration_time = expiration_time;
	}

	public List<MobileAirBottleTrackingRecord> getMobileAirBottleTrackingRecords() {
		return mobileAirBottleTrackingRecords;
	}

	public void setMobileAirBottleTrackingRecords(List<MobileAirBottleTrackingRecord> mobileAirBottleTrackingRecords) {
		this.mobileAirBottleTrackingRecords = mobileAirBottleTrackingRecords;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public Integer getUse_cycle() {
		return use_cycle;
	}

	public void setUse_cycle(Integer use_cycle) {
		this.use_cycle = use_cycle;
	}

	public Integer getAir_bottle_belong_id() {
		return air_bottle_belong_id;
	}

	public void setAir_bottle_belong_id(Integer air_bottle_belong_id) {
		this.air_bottle_belong_id = air_bottle_belong_id;
	}

	public String getAir_bottle_belong_name() {
		return air_bottle_belong_name;
	}

	public void setAir_bottle_belong_name(String air_bottle_belong_name) {
		this.air_bottle_belong_name = air_bottle_belong_name;
	}

	public String getBottle_model() {
		return bottle_model;
	}

	public void setBottle_model(String bottle_model) {
		this.bottle_model = bottle_model;
	}

}
