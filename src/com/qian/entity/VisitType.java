package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "visit_type")
// 回访类型表
public class VisitType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String visit_type_name;

	@Column(length = 5000, nullable = false)
	private String visit_content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVisit_type_name() {
		return visit_type_name;
	}

	public void setVisit_type_name(String visit_type_name) {
		this.visit_type_name = visit_type_name;
	}

	public String getVisit_content() {
		return visit_content;
	}

	public void setVisit_content(String visit_content) {
		this.visit_content = visit_content;
	}
	
}
