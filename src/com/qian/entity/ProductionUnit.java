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
 * @Description 生产单位表
 */
@Entity
@Table(name = "production_unit")
public class ProductionUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String production_unit_name;

	@Column(length = 50, nullable = false)
	private String production_unit_code;

	public ProductionUnit() {

	}

	public ProductionUnit(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduction_unit_name() {
		return production_unit_name;
	}

	public void setProduction_unit_name(String production_unit_name) {
		this.production_unit_name = production_unit_name;
	}

	public String getProduction_unit_code() {
		return production_unit_code;
	}

	public void setProduction_unit_code(String production_unit_code) {
		this.production_unit_code = production_unit_code;
	}

}
