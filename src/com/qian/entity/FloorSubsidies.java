package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-5 下午4:32:59
 * @Description 楼层补贴表
 */

@Entity
@Table(name = "floor_subsidies")
public class FloorSubsidies {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String floor_subsidies_name;

	@Column(nullable = false)
	private double floor_subsidies_money;

	public FloorSubsidies() {

	}

	public FloorSubsidies(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFloor_subsidies_name() {
		return floor_subsidies_name;
	}

	public void setFloor_subsidies_name(String floor_subsidies_name) {
		this.floor_subsidies_name = floor_subsidies_name;
	}

	public double getFloor_subsidies_money() {
		return floor_subsidies_money;
	}

	public void setFloor_subsidies_money(double floor_subsidies_money) {
		this.floor_subsidies_money = floor_subsidies_money;
	}

}
