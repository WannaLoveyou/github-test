package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2016-4-28 上午9:06:11
 * @Description
 */
@Entity
@Table(name = "air_bottle_state")
public class AirBottleState {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String state_description;

	public AirBottleState() {

	}

	public AirBottleState(int id) {
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

	@Override
	public String toString() {
		return "AirBottleState [id=" + id + ", state_description=" + state_description + "]";
	}

}
