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

@Entity
@Table(name = "repair_info")
public class RepairInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50)
	private String repair_code;

	@Column(nullable = false)
	private Date accept_time;// 受理日期

	@Column()
	private Date repair_appointment_time1;// 预约时间1

	@Column()
	private Date repair_appointment_time2;// 预约时间2

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "repair_type_id")
	private RepairType repairType;

	@Column(length = 50)
	private String repair_note;

	@Column(length = 50)
	private String repair_tel_number;

	@Column(length = 100)
	private String repair_address;

	@Column(length = 100)
	private String repair_content;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "repair_man_id")
	private User repair_man;// 维修人员

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "operator_id")
	private User operator;// 操作人员

	@Column
	private Double repair_price;// 上门费

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "client_id")
	private ClientInfo clientInfo;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "repair_state_id")
	private RepairInfoState repairInfoState;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "accept_second_category_id")
	private SecondCategory secondCategory;

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public RepairInfoState getRepairInfoState() {
		return repairInfoState;
	}

	public void setRepairInfoState(RepairInfoState repairInfoState) {
		this.repairInfoState = repairInfoState;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRepair_code() {
		return repair_code;
	}

	public void setRepair_code(String repair_code) {
		this.repair_code = repair_code;
	}

	public Date getAccept_time() {
		return accept_time;
	}

	public void setAccept_time(Date accept_time) {
		this.accept_time = accept_time;
	}

	public Date getRepair_appointment_time1() {
		return repair_appointment_time1;
	}

	public void setRepair_appointment_time1(Date repair_appointment_time1) {
		this.repair_appointment_time1 = repair_appointment_time1;
	}

	public Date getRepair_appointment_time2() {
		return repair_appointment_time2;
	}

	public void setRepair_appointment_time2(Date repair_appointment_time2) {
		this.repair_appointment_time2 = repair_appointment_time2;
	}

	public RepairType getRepairType() {
		return repairType;
	}

	public void setRepairType(RepairType repairType) {
		this.repairType = repairType;
	}

	public String getRepair_note() {
		return repair_note;
	}

	public void setRepair_note(String repair_note) {
		this.repair_note = repair_note;
	}

	public String getRepair_address() {
		return repair_address;
	}

	public void setRepair_address(String repair_address) {
		this.repair_address = repair_address;
	}

	public User getRepair_man() {
		return repair_man;
	}

	public void setRepair_man(User repair_man) {
		this.repair_man = repair_man;
	}

	public Double getRepair_price() {
		return repair_price;
	}

	public void setRepair_price(Double repair_price) {
		this.repair_price = repair_price;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public String getRepair_tel_number() {
		return repair_tel_number;
	}

	public void setRepair_tel_number(String repair_tel_number) {
		this.repair_tel_number = repair_tel_number;
	}

	public String getRepair_content() {
		return repair_content;
	}

	public void setRepair_content(String repair_content) {
		this.repair_content = repair_content;
	}

	public SecondCategory getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(SecondCategory secondCategory) {
		this.secondCategory = secondCategory;
	}

}
