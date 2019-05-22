package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "complaint_info_state")
// 维修状态
public class ComplaintInfoState {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String complaint_state_description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComplaint_state_description() {
		return complaint_state_description;
	}

	public void setComplaint_state_description(String complaint_state_description) {
		this.complaint_state_description = complaint_state_description;
	}

}
