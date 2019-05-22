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

import com.qian.code.PhoneRecordStateCode;

/**
 * @author Lu Kongwen
 * @version Create time：2018-1-22 上午9:37:06
 * @Description
 */
@Entity
@Table(name = "phone_record")
public class PhoneRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "phone_number", columnDefinition = "varchar(30) comment '来电号码'")
	private String phoneNumber;

	@Column(name = "call_in_time", columnDefinition = "datetime default null comment '来电时间'")
	private Date callInTime;

	@ManyToOne
	@JoinColumn(name = "phone_record_state_id")
	private PhoneRecordState phoneRecordState;

	@Column(name = "call_back_time", columnDefinition = "datetime default null comment '回访时间'")
	private Date callBackTime;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "answer_operator_id")
	private User answerOperator;
	
	public PhoneRecord(){
		
	}

	public PhoneRecord(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		this.callInTime = new Date();
		this.phoneRecordState  = new PhoneRecordState(PhoneRecordStateCode.UNPROCESSED);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getCallInTime() {
		return callInTime;
	}

	public void setCallInTime(Date callInTime) {
		this.callInTime = callInTime;
	}

	public PhoneRecordState getPhoneRecordState() {
		return phoneRecordState;
	}

	public void setPhoneRecordState(PhoneRecordState phoneRecordState) {
		this.phoneRecordState = phoneRecordState;
	}

	public Date getCallBackTime() {
		return callBackTime;
	}

	public void setCallBackTime(Date callBackTime) {
		this.callBackTime = callBackTime;
	}

	public User getAnswerOperator() {
		return answerOperator;
	}

	public void setAnswerOperator(User answerOperator) {
		this.answerOperator = answerOperator;
	}

}
