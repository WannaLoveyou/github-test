package com.qian.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-4 下午3:35:49
 * @Description
 */
@Entity
@Table(name = "sms_record")
public class SMSRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column()
	private String tel_number;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "sms_record_type_id")
	private SMSRecordType smsRecordType;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "operator_id")
	private User operator;

	@Column()
	private Date create_time;

	public SMSRecord(String tel_number, int smsRecordTypeId, User operator) {
		this.tel_number = tel_number;
		this.smsRecordType = new SMSRecordType(smsRecordTypeId);
		this.operator = operator;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTel_number() {
		return tel_number;
	}

	public void setTel_number(String tel_number) {
		this.tel_number = tel_number;
	}

	public SMSRecordType getSmsRecordType() {
		return smsRecordType;
	}

	public void setSmsRecordType(SMSRecordType smsRecordType) {
		this.smsRecordType = smsRecordType;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
