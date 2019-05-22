package com.qian.mobile.entity;

import com.qian.entity.DetectionUnit;

/**
 * @author Lu Kongwen
 * @version Create time：2016-6-16 下午4:01:57
 * @Description
 */
public class MobileDetectionUnit {

	private int id;

	private String detection_unit_name;

	public MobileDetectionUnit(DetectionUnit detectionUnit) {

		this.id = detectionUnit.getId();
		this.detection_unit_name = detectionUnit.getDetection_unit_name();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetection_unit_name() {
		return detection_unit_name;
	}

	public void setDetection_unit_name(String detection_unit_name) {
		this.detection_unit_name = detection_unit_name;
	}

}
