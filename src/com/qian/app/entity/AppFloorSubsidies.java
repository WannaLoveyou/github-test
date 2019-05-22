package com.qian.app.entity;

import com.qian.entity.FloorSubsidies;

public class AppFloorSubsidies {

	private int floorSubsidiesId; // 楼层补贴方案ID

	private String floorSubsidiesName; // 楼层补贴方案名

	private double floorSubsidiesMoney; // 楼层补贴金额

	public AppFloorSubsidies(FloorSubsidies floorSubsidies) {

		this.floorSubsidiesId = floorSubsidies.getId();
		this.floorSubsidiesName = floorSubsidies.getFloor_subsidies_name();
		this.floorSubsidiesMoney = floorSubsidies.getFloor_subsidies_money();
	}

	public int getFloorSubsidiesId() {
		return floorSubsidiesId;
	}

	public void setFloorSubsidiesId(int floorSubsidiesId) {
		this.floorSubsidiesId = floorSubsidiesId;
	}

	public String getFloorSubsidiesName() {
		return floorSubsidiesName;
	}

	public void setFloorSubsidiesName(String floorSubsidiesName) {
		this.floorSubsidiesName = floorSubsidiesName;
	}

	public double getFloorSubsidiesMoney() {
		return floorSubsidiesMoney;
	}

	public void setFloorSubsidiesMoney(double floorSubsidiesMoney) {
		this.floorSubsidiesMoney = floorSubsidiesMoney;
	}

}
