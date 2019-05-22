package com.qian.mobile.entity;

import com.qian.entity.ClientInfo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-9-20 下午4:21:43
 * @Description
 */
public class MobileClientInfo {

	private int clientId;

	private String client_code;

	private String card_code;

	private String client_name;

	private String mobile_tel_number_1;

	private String mobile_tel_number_2;

	private String fixed_tel_number_1;

	private String fixed_tel_number_2;

	private String client_address;

	private String second_category_name;

	private Integer todayOrderNum;

	public MobileClientInfo(ClientInfo clientInfo) {

		this.clientId = clientInfo.getId();
		this.card_code = clientInfo.getCard_code();
		this.client_code = clientInfo.getClient_code();
		this.client_name = clientInfo.getClient_name();
		this.mobile_tel_number_1 = clientInfo.getMobile_tel_number_1();
		this.mobile_tel_number_2 = clientInfo.getMobile_tel_number_2();
		this.fixed_tel_number_1 = clientInfo.getFixed_tel_number_1();
		this.fixed_tel_number_2 = clientInfo.getFixed_tel_number_2();
		this.client_address = clientInfo.getClient_address();
		this.second_category_name = clientInfo.getSecondCategory().getSecond_category_name();
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClient_code() {
		return client_code;
	}

	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getMobile_tel_number_1() {
		return mobile_tel_number_1;
	}

	public void setMobile_tel_number_1(String mobile_tel_number_1) {
		this.mobile_tel_number_1 = mobile_tel_number_1;
	}

	public String getMobile_tel_number_2() {
		return mobile_tel_number_2;
	}

	public void setMobile_tel_number_2(String mobile_tel_number_2) {
		this.mobile_tel_number_2 = mobile_tel_number_2;
	}

	public String getFixed_tel_number_1() {
		return fixed_tel_number_1;
	}

	public void setFixed_tel_number_1(String fixed_tel_number_1) {
		this.fixed_tel_number_1 = fixed_tel_number_1;
	}

	public String getFixed_tel_number_2() {
		return fixed_tel_number_2;
	}

	public void setFixed_tel_number_2(String fixed_tel_number_2) {
		this.fixed_tel_number_2 = fixed_tel_number_2;
	}

	public String getClient_address() {
		return client_address;
	}

	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}

	public String getSecond_category_name() {
		return second_category_name;
	}

	public void setSecond_category_name(String second_category_name) {
		this.second_category_name = second_category_name;
	}

	public String getCard_code() {
		return card_code;
	}

	public void setCard_code(String card_code) {
		this.card_code = card_code;
	}

	public Integer getTodayOrderNum() {
		return todayOrderNum;
	}

	public void setTodayOrderNum(Integer todayOrderNum) {
		this.todayOrderNum = todayOrderNum;
	}

}
