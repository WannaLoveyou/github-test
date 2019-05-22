package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-5 下午4:05:58
 * @Description 客户类型表
 */

@Entity
@Table(name = "client_type")
public class ClientType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String client_type_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClient_type_name() {
		return client_type_name;
	}

	public void setClient_type_name(String client_type_name) {
		this.client_type_name = client_type_name;
	}


}
