package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2018-6-6 下午5:23:04
 * @Description
 */
@Entity
@Table(name = "inspection_system_code")
public class InspectionSystemCode {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50)
	private Integer inspection_system_code;

	@Column(length = 50)
	private String inspection_system_code_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getInspection_system_code() {
		return inspection_system_code;
	}

	public void setInspection_system_code(Integer inspection_system_code) {
		this.inspection_system_code = inspection_system_code;
	}

	public String getInspection_system_code_name() {
		return inspection_system_code_name;
	}

	public void setInspection_system_code_name(String inspection_system_code_name) {
		this.inspection_system_code_name = inspection_system_code_name;
	}

}
