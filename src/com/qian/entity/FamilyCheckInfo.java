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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-9 上午9:35:46
 * @Description
 */
@Entity
@Table(name = "family_check_imformation")
public class FamilyCheckInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50)
	private String family_check_code;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "client_id")
	private ClientInfo clientInfo;

	@Column(length = 255)
	private String family_check_address;

	@Column(length = 255)
	private String family_check_tel_number;

	@Column()
	private Date create_time;

	@Column()
	private Date appointment_check_time;

	@Column()
	private Date check_time;

	@Column()
	private Date arrive_time;

	@Column()
	private Date leave_time;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "family_check_state_id")
	private FamilyCheckInfoState familyCheckInfoState;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "family_check_content_id")
	private @JsonIgnore
	FamilyCheckContentInfo familyCheckContentInfo;

	@Column(length = 255)
	private String remark;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "operator_id")
	private User operator;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "check_operator_id")
	private User check_operator;

	@Column(length = 225)
	private String photo_urls; // 照片地址

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public String getFamily_check_address() {
		return family_check_address;
	}

	public void setFamily_check_address(String family_check_address) {
		this.family_check_address = family_check_address;
	}

	public String getFamily_check_tel_number() {
		return family_check_tel_number;
	}

	public void setFamily_check_tel_number(String family_check_tel_number) {
		this.family_check_tel_number = family_check_tel_number;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getAppointment_check_time() {
		return appointment_check_time;
	}

	public void setAppointment_check_time(Date appointment_check_time) {
		this.appointment_check_time = appointment_check_time;
	}

	public Date getCheck_time() {
		return check_time;
	}

	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}

	public Date getArrive_time() {
		return arrive_time;
	}

	public void setArrive_time(Date arrive_time) {
		this.arrive_time = arrive_time;
	}

	public Date getLeave_time() {
		return leave_time;
	}

	public void setLeave_time(Date leave_time) {
		this.leave_time = leave_time;
	}

	public FamilyCheckInfoState getFamilyCheckInfoState() {
		return familyCheckInfoState;
	}

	public void setFamilyCheckInfoState(FamilyCheckInfoState familyCheckInfoState) {
		this.familyCheckInfoState = familyCheckInfoState;
	}

	public String getFamily_check_code() {
		return family_check_code;
	}

	public void setFamily_check_code(String family_check_code) {
		this.family_check_code = family_check_code;
	}

	public FamilyCheckContentInfo getFamilyCheckContentInfo() {
		return familyCheckContentInfo;
	}

	public void setFamilyCheckContentInfo(FamilyCheckContentInfo familyCheckContentInfo) {
		this.familyCheckContentInfo = familyCheckContentInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public String getPhoto_urls() {
		return photo_urls;
	}

	public void setPhoto_urls(String photo_urls) {
		this.photo_urls = photo_urls;
	}

	public User getCheck_operator() {
		return check_operator;
	}

	public void setCheck_operator(User check_operator) {
		this.check_operator = check_operator;
	}

}
