package com.qian.vo;

import com.qian.entity.DistributionWorkerPositionShow;
import com.qian.util.TimeUtils;

public class WorkerPositionVo {
	// 纬度
	private double latitude;
	// 经度
	private double longitude;

	private String createTime;
	
	private String workName;
	
	private String cardCode;

	public WorkerPositionVo() {

	}

	public WorkerPositionVo(DistributionWorkerPositionShow info) {
		this.latitude = info.getLatitude();
		this.longitude = info.getLongitude();
		this.createTime = TimeUtils.getyyyyMMddHHmmssStringByDate(info.getCreate_time());
		this.cardCode = info.getDelivery_man().getCard_code();
		this.workName = info.getDelivery_man().getFull_name();
	}
	
	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}


	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
