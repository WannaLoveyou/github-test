package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2017-4-21 上午11:31:30
 * @Description
 */
@Entity
@Table(name = "module_configuration_imformation")
public class ModuleConfigurationInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50)
	private String module_name;

	@Column(columnDefinition = "INT default 1")
	private int is_open; // 0不开启 1开启

	@Column(columnDefinition = "DOUBLE default 0")
	private double module_parameter;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public int getIs_open() {
		return is_open;
	}

	public void setIs_open(int is_open) {
		this.is_open = is_open;
	}

	public double getModule_parameter() {
		return module_parameter;
	}

	public void setModule_parameter(double module_parameter) {
		this.module_parameter = module_parameter;
	}

}
