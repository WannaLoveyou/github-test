package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-27 上午11:38:12
 * @Description
 */
@Entity
@Table(name = "air_bottle_variety")
public class AirBottleVariety {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50)
	private String air_bottle_variety_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAir_bottle_variety_name() {
		return air_bottle_variety_name;
	}

	public void setAir_bottle_variety_name(String air_bottle_variety_name) {
		this.air_bottle_variety_name = air_bottle_variety_name;
	}

}
