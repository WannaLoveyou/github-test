package com.qian.app.entity;

import java.util.ArrayList;
import java.util.List;

import com.qian.entity.AirBottleInfo;
import com.qian.entity.AirBottleTrackingRecord;
import com.qian.util.PathUtils;
import com.qian.util.TimeUtils;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-9 下午3:39:59
 * @Description
 */
public class AppAirBottleInfo {

	private String companyName; // 公司名称

	private String airBottleCode; // 气瓶编码

	private String airBottleSealCode; // 气瓶钢码

	private String airBottleSpecifications; // 气瓶规格

	private String produceTime;// 生产日期

	private String createTime;// 初始化日期

	private String checkTime; // 检修日期

	private String nextCheckTime; // 下次检修日期

	private String scrapTime; // 报废日期

	private String detectionUnitName; // 检验单位

	private String productionUnitName; // 生产单位

	private String hasGasWeight;// 含气量

	private String airBottleBelong; // 气瓶归属

	private String airBottleVarietyName; // 气瓶种类

	private String fillingMediumName; // 充装介质

	private List<AppAirBottleTrackingRecord> appAirBottleTrackingRecords; // 气瓶轨迹

	public AppAirBottleInfo(AirBottleInfo airBottleInfo, List<AirBottleTrackingRecord> airBottleTrackingRecords) {

		this.airBottleCode = airBottleInfo.getAir_bottle_code();
		this.airBottleSealCode = airBottleInfo.getAir_bottle_seal_code();
		this.airBottleSpecifications = airBottleInfo.getAirBottleType().getAir_bottle_specifications();
		this.hasGasWeight = airBottleInfo.getAirBottleType().getHas_gas_weight();
		this.produceTime = TimeUtils.getyyyyMMddStringByDate(airBottleInfo.getProduce_time());
		this.createTime = TimeUtils.getyyyyMMddStringByDate(airBottleInfo.getCreate_time());
		if (airBottleInfo.getCheck_time() != null) {
			this.checkTime = TimeUtils.getyyyyMMddStringByDate(airBottleInfo.getCheck_time());
		}
		this.nextCheckTime = TimeUtils.getyyyyMMddStringByDate(airBottleInfo.getNext_check_time());
		this.scrapTime = TimeUtils.getyyyyMMddStringByDate(TimeUtils.addFewYear(airBottleInfo.getProduce_time(), airBottleInfo.getUse_cycle()));

		if (airBottleInfo.getDetectionUnit() != null) {
			this.detectionUnitName = airBottleInfo.getDetectionUnit().getDetection_unit_name();
		}

		if (airBottleInfo.getProductionUnit() != null) {
			this.productionUnitName = airBottleInfo.getProductionUnit().getProduction_unit_name();
		}

		List<AppAirBottleTrackingRecord> appAirBottleTrackingRecords = new ArrayList<AppAirBottleTrackingRecord>();

		for (AirBottleTrackingRecord airBottleTrackingRecord : airBottleTrackingRecords) {
			AppAirBottleTrackingRecord appAirBottleTrackingRecord = new AppAirBottleTrackingRecord(airBottleTrackingRecord);
			appAirBottleTrackingRecords.add(appAirBottleTrackingRecord);
		}

		this.appAirBottleTrackingRecords = appAirBottleTrackingRecords;

		this.companyName = PathUtils.getProperty("company.name");

		if (airBottleInfo.getAirBottleBelong() != null) {
			this.airBottleBelong = airBottleInfo.getAirBottleBelong().getBlong_name();
		}

		if (airBottleInfo.getAirBottleType().getAirBottleVariety() != null) {
			this.airBottleVarietyName = airBottleInfo.getAirBottleType().getAirBottleVariety().getAir_bottle_variety_name();
		}

		if (airBottleInfo.getAirBottleType().getFillingMedium() != null) {
			this.fillingMediumName = airBottleInfo.getAirBottleType().getFillingMedium().getFilling_medium_name();
		}

	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAirBottleCode() {
		return airBottleCode;
	}

	public void setAirBottleCode(String airBottleCode) {
		this.airBottleCode = airBottleCode;
	}

	public String getAirBottleSealCode() {
		return airBottleSealCode;
	}

	public void setAirBottleSealCode(String airBottleSealCode) {
		this.airBottleSealCode = airBottleSealCode;
	}

	public String getAirBottleSpecifications() {
		return airBottleSpecifications;
	}

	public void setAirBottleSpecifications(String airBottleSpecifications) {
		this.airBottleSpecifications = airBottleSpecifications;
	}

	public String getProduceTime() {
		return produceTime;
	}

	public void setProduceTime(String produceTime) {
		this.produceTime = produceTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getNextCheckTime() {
		return nextCheckTime;
	}

	public void setNextCheckTime(String nextCheckTime) {
		this.nextCheckTime = nextCheckTime;
	}

	public String getScrapTime() {
		return scrapTime;
	}

	public void setScrapTime(String scrapTime) {
		this.scrapTime = scrapTime;
	}

	public String getDetectionUnitName() {
		return detectionUnitName;
	}

	public void setDetectionUnitName(String detectionUnitName) {
		this.detectionUnitName = detectionUnitName;
	}

	public String getProductionUnitName() {
		return productionUnitName;
	}

	public void setProductionUnitName(String productionUnitName) {
		this.productionUnitName = productionUnitName;
	}

	public String getHasGasWeight() {
		return hasGasWeight;
	}

	public void setHasGasWeight(String hasGasWeight) {
		this.hasGasWeight = hasGasWeight;
	}

	public String getAirBottleBelong() {
		return airBottleBelong;
	}

	public void setAirBottleBelong(String airBottleBelong) {
		this.airBottleBelong = airBottleBelong;
	}

	public String getAirBottleVarietyName() {
		return airBottleVarietyName;
	}

	public void setAirBottleVarietyName(String airBottleVarietyName) {
		this.airBottleVarietyName = airBottleVarietyName;
	}

	public String getFillingMediumName() {
		return fillingMediumName;
	}

	public void setFillingMediumName(String fillingMediumName) {
		this.fillingMediumName = fillingMediumName;
	}

	public List<AppAirBottleTrackingRecord> getAppAirBottleTrackingRecords() {
		return appAirBottleTrackingRecords;
	}

	public void setAppAirBottleTrackingRecords(List<AppAirBottleTrackingRecord> appAirBottleTrackingRecords) {
		this.appAirBottleTrackingRecords = appAirBottleTrackingRecords;
	}

}
