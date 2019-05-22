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

//投诉受理表
@Entity
@Table(name = "complaint_info")
public class ComplaintInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50)
	private String complaint_code;

	@Column(nullable = false)
	private Date complaint_time;// 受理日期

	@Column()
	private Date complaint_appointment_time1;// 预约时间1

	@Column()
	private Date complaint_appointment_time2;// 预约时间2

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "repair_type_id")
	private ComplaintType complaintType;// 投诉类型

	@Column(length = 50)
	private String complaint_note;

	@Column(length = 50)
	private String complaint_tel_number;

	@Column(length = 100)
	private String complaint_address;

	@Column(length = 100)
	private String complaint_content;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "client_id")
	private ClientInfo clientInfo;// 客户

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "complaintInfoState_id")
	private ComplaintInfoState complaintInfoState;// 状态

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "operator_id")
	private User operator;// 操作人员

	@Column(length = 50)
	private String dealcomplaintNote;// 已处理备注

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "accept_second_category_id")
	private SecondCategory secondCategory;

	public String getDealcomplaintNote() {
		return dealcomplaintNote;
	}

	public void setDealcomplaintNote(String dealcomplaintNote) {
		this.dealcomplaintNote = dealcomplaintNote;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public ComplaintInfoState getComplaintInfoState() {
		return complaintInfoState;
	}

	public void setComplaintInfoState(ComplaintInfoState complaintInfoState) {
		this.complaintInfoState = complaintInfoState;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComplaint_code() {
		return complaint_code;
	}

	public void setComplaint_code(String complaint_code) {
		this.complaint_code = complaint_code;
	}

	public Date getComplaint_time() {
		return complaint_time;
	}

	public void setComplaint_time(Date complaint_time) {
		this.complaint_time = complaint_time;
	}

	public Date getComplaint_appointment_time1() {
		return complaint_appointment_time1;
	}

	public void setComplaint_appointment_time1(Date complaint_appointment_time1) {
		this.complaint_appointment_time1 = complaint_appointment_time1;
	}

	public Date getComplaint_appointment_time2() {
		return complaint_appointment_time2;
	}

	public void setComplaint_appointment_time2(Date complaint_appointment_time2) {
		this.complaint_appointment_time2 = complaint_appointment_time2;
	}

	public ComplaintType getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(ComplaintType complaintType) {
		this.complaintType = complaintType;
	}

	public String getComplaint_note() {
		return complaint_note;
	}

	public void setComplaint_note(String complaint_note) {
		this.complaint_note = complaint_note;
	}

	public String getComplaint_address() {
		return complaint_address;
	}

	public void setComplaint_address(String complaint_address) {
		this.complaint_address = complaint_address;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public String getComplaint_tel_number() {
		return complaint_tel_number;
	}

	public void setComplaint_tel_number(String complaint_tel_number) {
		this.complaint_tel_number = complaint_tel_number;
	}

	public String getComplaint_content() {
		return complaint_content;
	}

	public void setComplaint_content(String complaint_content) {
		this.complaint_content = complaint_content;
	}

	public SecondCategory getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(SecondCategory secondCategory) {
		this.secondCategory = secondCategory;
	}

}
