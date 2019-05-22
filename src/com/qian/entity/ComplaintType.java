package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "complaint_type")
// 投诉类型表
public class ComplaintType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String complaint_type_name;

	@Column(length = 50, nullable = false)
	private String complaint_content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComplaint_type_name() {
		return complaint_type_name;
	}

	public void setComplaint_type_name(String complaint_type_name) {
		this.complaint_type_name = complaint_type_name;
	}

	public String getComplaint_content() {
		return complaint_content;
	}

	public void setComplaint_content(String complaint_content) {
		this.complaint_content = complaint_content;
	}

}
