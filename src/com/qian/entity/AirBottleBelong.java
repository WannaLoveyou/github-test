package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2018-2-1 下午5:00:49
 * @Description
 */
@Entity
@Table(name = "air_bottle_blong")
public class AirBottleBelong {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String air_bottle_blong_code;

	@Column(length = 50, nullable = false)
	private String blong_name;

	@Column(columnDefinition = "INT default 1")
	private int upload_cloud_platform; // 0不开启 1开启

	public AirBottleBelong(int id) {
		this.id = id;
	}

	public AirBottleBelong() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBlong_name() {
		return blong_name;
	}

	public void setBlong_name(String blong_name) {
		this.blong_name = blong_name;
	}

	public String getAir_bottle_blong_code() {
		return air_bottle_blong_code;
	}

	public void setAir_bottle_blong_code(String air_bottle_blong_code) {
		this.air_bottle_blong_code = air_bottle_blong_code;
	}

	public int getUpload_cloud_platform() {
		return upload_cloud_platform;
	}

	public void setUpload_cloud_platform(int upload_cloud_platform) {
		this.upload_cloud_platform = upload_cloud_platform;
	}

}
