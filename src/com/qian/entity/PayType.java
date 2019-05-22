package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-16 下午4:23:08
 * @Description
 */
@Entity
@Table(name = "pay_type")
public class PayType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String pay_type_name;

	public PayType() {

	}

	public PayType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPay_type_name() {
		return pay_type_name;
	}

	public void setPay_type_name(String pay_type_name) {
		this.pay_type_name = pay_type_name;
	}


}
