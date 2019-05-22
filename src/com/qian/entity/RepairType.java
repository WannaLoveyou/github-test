package com.qian.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="repair_type")

//维修类型表
public class RepairType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(length=50,nullable=false)
	private String repair_type_name;
	
	@Column(length=50,nullable=false)
	private String repair_content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRepair_type_name() {
		return repair_type_name;
	}

	public void setRepair_type_name(String repair_type_name) {
		this.repair_type_name = repair_type_name;
	}

	public String getRepair_content() {
		return repair_content;
	}

	public void setRepair_content(String repair_content) {
		this.repair_content = repair_content;
	}
	

}
