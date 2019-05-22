package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-4 下午3:37:21
 * @Description
 */
@Entity
@Table(name = "sms_record_type")
public class SMSRecordType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50)
	private String sms_record_type_name;

	public SMSRecordType() {

	}

	public SMSRecordType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSms_record_type_name() {
		return sms_record_type_name;
	}

	public void setSms_record_type_name(String sms_record_type_name) {
		this.sms_record_type_name = sms_record_type_name;
	}

}
