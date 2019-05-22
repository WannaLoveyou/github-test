package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phone_record_state")
public class PhoneRecordState {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column()
	private String description;

	public PhoneRecordState() {

	}

	public PhoneRecordState(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
