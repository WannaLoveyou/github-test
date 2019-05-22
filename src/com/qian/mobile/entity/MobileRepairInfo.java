package com.qian.mobile.entity;

import java.util.Date;

import com.qian.entity.RepairInfo;
import com.qian.entity.RepairType;
import com.qian.entity.User;

public class MobileRepairInfo {

	private int id;

	private String repair_address; //地址

	private String second_category_name; //对应门店

	private String client_name; //客户名

	private String repair_tel_number; //联系电话

	private String repair_state_description;

	private String operator_name; 

	private Date accept_time;  

	private Date repair_appointment_time1; 

	private Date repair_appointment_time2;

	private String repair_note; //维修备注

	private String repair_content; //维修内容

	private String repair_type_name; //维修项目

	private Double repair_price; //上门费

	private String repair_man_name; //维修人员

	private String repair_code;

	public MobileRepairInfo() {
	}

	public MobileRepairInfo(RepairInfo repairInfo) {
		this.id = repairInfo.getId();
		this.repair_address = repairInfo.getRepair_address();
		this.second_category_name = repairInfo.getSecondCategory().getSecond_category_name();
		this.client_name = repairInfo.getClientInfo().getClient_name();
		this.repair_tel_number = repairInfo.getRepair_tel_number();
		this.repair_state_description = repairInfo.getRepairInfoState().getrepair_state_description();
		this.operator_name = repairInfo.getOperator().getFull_name();
		this.accept_time = repairInfo.getAccept_time();
		this.repair_appointment_time1 = repairInfo.getRepair_appointment_time1();
		this.repair_appointment_time2 = repairInfo.getRepair_appointment_time2();
		this.repair_note = repairInfo.getRepair_note();
		RepairType repairType = repairInfo.getRepairType();
		this.repair_content = repairType.getRepair_content();
		this.repair_type_name = repairType.getRepair_type_name();
		this.repair_price = repairInfo.getRepair_price();
		User repair_man = repairInfo.getRepair_man();
		if (repair_man != null) {
			this.repair_man_name = repair_man.getFull_name();
		}
		this.repair_code = repairInfo.getRepair_code();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRepair_address() {
		return repair_address;
	}

	public void setRepair_address(String repair_address) {
		this.repair_address = repair_address;
	}

	public String getSecond_category_name() {
		return second_category_name;
	}

	public void setSecond_category_name(String second_category_name) {
		this.second_category_name = second_category_name;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getRepair_tel_number() {
		return repair_tel_number;
	}

	public void setRepair_tel_number(String repair_tel_number) {
		this.repair_tel_number = repair_tel_number;
	}

	public String getRepair_state_description() {
		return repair_state_description;
	}

	public void setRepair_state_description(String repair_state_description) {
		this.repair_state_description = repair_state_description;
	}

	public String getOperator_name() {
		return operator_name;
	}

	public void setOperator_name(String operator_name) {
		this.operator_name = operator_name;
	}

	public String getRepair_note() {
		return repair_note;
	}

	public void setRepair_note(String repair_note) {
		this.repair_note = repair_note;
	}

	public String getRepair_content() {
		return repair_content;
	}

	public void setRepair_content(String repair_content) {
		this.repair_content = repair_content;
	}

	public String getRepair_type_name() {
		return repair_type_name;
	}

	public void setRepair_type_name(String repair_type_name) {
		this.repair_type_name = repair_type_name;
	}

	public String getRepair_man_name() {
		return repair_man_name;
	}

	public void setRepair_man_name(String repair_man_name) {
		this.repair_man_name = repair_man_name;
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

	public Double getRepair_price() {
		return repair_price;
	}

	public void setRepair_price(Double repair_price) {
		this.repair_price = repair_price;
	}

}
