package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-16 下午4:21:57
 * @Description 订气方式表
 */
@Entity
@Table(name = "order_type")
public class OrderType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String order_type_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrder_type_name() {
		return order_type_name;
	}

	public void setOrder_type_name(String order_type_name) {
		this.order_type_name = order_type_name;
	}

}
