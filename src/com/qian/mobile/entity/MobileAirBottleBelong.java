package com.qian.mobile.entity;

import com.qian.entity.AirBottleBelong;

/**
 * @author Lu Kongwen
 * @version Create time：2018-2-1 下午5:10:36
 * @Description
 */
public class MobileAirBottleBelong {

	private int id;

	private String air_bottle_belong_name;

	public MobileAirBottleBelong(AirBottleBelong airBottleBelong) {

		this.id = airBottleBelong.getId();
		this.air_bottle_belong_name = airBottleBelong.getBlong_name();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAir_bottle_belong_name() {
		return air_bottle_belong_name;
	}

	public void setAir_bottle_belong_name(String air_bottle_belong_name) {
		this.air_bottle_belong_name = air_bottle_belong_name;
	}

}
