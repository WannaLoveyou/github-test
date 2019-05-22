package com.qian.vo;

import com.qian.entity.ClientInfo;
import com.qian.entity.ClientInventoryInfo;
import com.qian.util.TimeUtils;

public class CoordinateVo {

	// 纬度
	private double latitude;
	// 经度
	private double longitude;

	private String createTime;

	private String airBottleSealCode;

	private String clientAddress;

	private String clientName;

	private String clientCode;

	public CoordinateVo() {

	}

	public CoordinateVo(ClientInventoryInfo info) {
		this.latitude = info.getLatitude();
		this.longitude = info.getLongitude();
		this.createTime = TimeUtils.getyyyyMMddHHmmssStringByDate(info.getCreate_time());
		this.airBottleSealCode = info.getAirBottleInfo() == null ? null
				: info.getAirBottleInfo().getAir_bottle_seal_code();
		ClientInfo clientInfo = info.getClientInfo();
		if (clientInfo == null)
			return;
		this.clientAddress = info.getClientInfo().getClient_address();
		this.clientName = info.getClientInfo().getClient_name();
		this.clientCode = info.getClientInfo().getClient_code();
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

	public String getAirBottleSealCode() {
		return airBottleSealCode;
	}

	public void setAirBottleSealCode(String airBottleSealCode) {
		this.airBottleSealCode = airBottleSealCode;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
}
