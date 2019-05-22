package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-18 上午10:41:41
 * @Description
 */
@Entity
@Table(name = "location_state")
public class LocationState {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String state_description;

	public LocationState() {

	}

	public LocationState(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState_description() {
		return state_description;
	}

	public void setState_description(String state_description) {
		this.state_description = state_description;
	}

}
