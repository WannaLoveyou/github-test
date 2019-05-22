package com.qian.app.entity;

import com.qian.entity.AirBottleTrackingRecord;
import com.qian.util.TimeUtils;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-10 上午11:33:54
 * @Description
 */
public class AppAirBottleTrackingRecord {

	private Integer operatorId;

	private String airBottleTrackingRecordTime; // 时间

	private String airBottleTrackingRecordStateDescription; // 状态描述

	private String storeName;

	private String clientCode;

	public AppAirBottleTrackingRecord(AirBottleTrackingRecord airBottleTrackingRecord) {

		this.operatorId = airBottleTrackingRecord.getOperator().getId();
		this.airBottleTrackingRecordTime = TimeUtils.getyyyyMMddHHmmStringByDate(airBottleTrackingRecord.getCreate_time());
		this.airBottleTrackingRecordStateDescription = airBottleTrackingRecord.getState().getState_description();
		if (airBottleTrackingRecord.getClientInfo() != null) {
			this.clientCode = airBottleTrackingRecord.getClientInfo().getClient_code();
		}
		if(airBottleTrackingRecord.getSecondCategory() != null){
			this.storeName = airBottleTrackingRecord.getSecondCategory().getSecond_category_name();
		}
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getAirBottleTrackingRecordTime() {
		return airBottleTrackingRecordTime;
	}

	public void setAirBottleTrackingRecordTime(String airBottleTrackingRecordTime) {
		this.airBottleTrackingRecordTime = airBottleTrackingRecordTime;
	}

	public String getAirBottleTrackingRecordStateDescription() {
		return airBottleTrackingRecordStateDescription;
	}

	public void setAirBottleTrackingRecordStateDescription(String airBottleTrackingRecordStateDescription) {
		this.airBottleTrackingRecordStateDescription = airBottleTrackingRecordStateDescription;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

}
