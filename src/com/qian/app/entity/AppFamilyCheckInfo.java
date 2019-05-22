package com.qian.app.entity;

import com.qian.entity.FamilyCheckInfo;
import com.qian.util.TimeUtils;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-13 下午4:24:26
 * @Description
 */
public class AppFamilyCheckInfo {

	private Integer family_check_id; // 入户检查单ID

	private String family_check_code; // 入户检查单编码

	private Integer client_id; // 客戶ID

	private String client_name; // 客户姓名

	private String client_code; // 客户编码

	private String family_check_address; // 订单地址

	private String family_check_tel_number; // 订单号码

	private String create_time; // 订单创建时间

	private String appointment_check_time; // 订单预约时间

	private String remark; // 备注

	public AppFamilyCheckInfo() {

	}

	public AppFamilyCheckInfo(FamilyCheckInfo familyCheckInfo) {

		this.family_check_id = familyCheckInfo.getId();
		this.family_check_code = familyCheckInfo.getFamily_check_code();
		this.client_id = familyCheckInfo.getClientInfo().getId();
		this.client_name = familyCheckInfo.getClientInfo().getClient_name();
		this.client_code = familyCheckInfo.getClientInfo().getClient_code();
		this.family_check_address = familyCheckInfo.getFamily_check_address();
		this.family_check_tel_number = familyCheckInfo.getFamily_check_tel_number();
		this.create_time = TimeUtils.getyyyyMMddHHmmStringByDate(familyCheckInfo.getCreate_time());
		this.appointment_check_time = TimeUtils.getyyyyMMddHHmmStringByDate(familyCheckInfo.getAppointment_check_time());
		this.remark = familyCheckInfo.getRemark();
	}

	public String getFamily_check_code() {
		return family_check_code;
	}

	public void setFamily_check_code(String family_check_code) {
		this.family_check_code = family_check_code;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
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

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getAppointment_check_time() {
		return appointment_check_time;
	}

	public void setAppointment_check_time(String appointment_check_time) {
		this.appointment_check_time = appointment_check_time;
	}

	public Integer getFamily_check_id() {
		return family_check_id;
	}

	public void setFamily_check_id(Integer family_check_id) {
		this.family_check_id = family_check_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getClient_code() {
		return client_code;
	}

	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}

	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}

}
