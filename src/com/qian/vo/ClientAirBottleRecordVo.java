package com.qian.vo;

import java.util.Date;

/**
 * @author Lu Kongwen
 * @version Create time：2017-1-12 下午3:55:04
 * @Description
 */
public class ClientAirBottleRecordVo {

	private int client_id;

	private String air_bottle_code;

	private String air_bottle_specifications;

	private Date begin_time;

	private Date end_time;

	private String state_description;

	public ClientAirBottleRecordVo() {

	}

	public ClientAirBottleRecordVo(int client_id, String air_bottle_code, String air_bottle_specifications, Date begin_time, Date end_time,
			String state_description) {

		this.client_id = client_id;
		this.air_bottle_code = air_bottle_code;
		this.air_bottle_specifications = air_bottle_specifications;
		this.begin_time = begin_time;
		this.end_time = end_time;
		this.state_description = state_description;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getAir_bottle_code() {
		return air_bottle_code;
	}

	public void setAir_bottle_code(String air_bottle_code) {
		this.air_bottle_code = air_bottle_code;
	}

	public Date getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getState_description() {
		return state_description;
	}

	public void setState_description(String state_description) {
		this.state_description = state_description;
	}

	public String getAir_bottle_specifications() {
		return air_bottle_specifications;
	}

	public void setAir_bottle_specifications(String air_bottle_specifications) {
		this.air_bottle_specifications = air_bottle_specifications;
	}

}
