package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2016-6-16 上午10:21:16
 * @Description 检测单位表
 */
@Entity
@Table(name = "detection_unit")
public class DetectionUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String detection_unit_name;

	@Column(length = 50, nullable = false)
	private String detection_unit_code;

	public DetectionUnit() {

	}

	public DetectionUnit(int id) {
		this.id = id;
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

	public String getDetection_unit_code() {
		return detection_unit_code;
	}

	public void setDetection_unit_code(String detection_unit_code) {
		this.detection_unit_code = detection_unit_code;
	}

}
