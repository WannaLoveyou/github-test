package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-27 下午4:02:37
 * @Description
 */
@Entity
@Table(name = "filling_medium")
public class FillingMedium {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50)
	private String filling_medium_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFilling_medium_name() {
		return filling_medium_name;
	}

	public void setFilling_medium_name(String filling_medium_name) {
		this.filling_medium_name = filling_medium_name;
	}

}
