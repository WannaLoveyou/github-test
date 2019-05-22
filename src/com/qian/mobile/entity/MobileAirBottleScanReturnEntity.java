package com.qian.mobile.entity;

import com.qian.entity.AirBottleInfo;

/**
 * @author Lu Kongwen
 * @version Create time：2018-1-26 下午4:53:52
 * @Description
 */
public class MobileAirBottleScanReturnEntity {

	private int airBottleId;

	private int airBottleTypeId;

	public MobileAirBottleScanReturnEntity(AirBottleInfo airBottleInfo){
		this.airBottleId = airBottleInfo.getId();
		this.airBottleTypeId = airBottleInfo.getAirBottleType().getId();
	}
	
	public int getAirBottleId() {
		return airBottleId;
	}

	public void setAirBottleId(int airBottleId) {
		this.airBottleId = airBottleId;
	}

	public int getAirBottleTypeId() {
		return airBottleTypeId;
	}

	public void setAirBottleTypeId(int airBottleTypeId) {
		this.airBottleTypeId = airBottleTypeId;
	}

}
