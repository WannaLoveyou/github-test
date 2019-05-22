package com.qian.app.entity;

import com.qian.entity.ClientInfo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-9-20 下午4:21:43
 * @Description
 */
public class AppClinentInfo {

	private int clientId; // 客户ID

	private String clientCode; // 客户编码

	private String clientName; // 客户姓名

	private String cardCode; // 客户卡号

	private String logiMobileTelNumber;// 登录手机号码

	private String mobileTelNumber1; // 移动电话1

	private String mobileTelNumber2; // 移动电话2

	private String fixedTelNumber1; // 固定电话1

	private String fixedTelNumber2; // 固定电话2

	private String clientAddress; // 客户地址

	private String secondCategoryName; // 门店名称

	private String thirdCategoryName; // 地段名称

	private Integer floorSubsidiesId; // 楼层补贴ID

	private String floorSubsidiesName; // 楼层补贴方案名

	private Double cardMoney; // 预存款余额

	public AppClinentInfo() {

	}

	public AppClinentInfo(ClientInfo clientInfo) {

		this.clientId = clientInfo.getId();
		this.clientCode = clientInfo.getClient_code();
		this.clientName = clientInfo.getClient_name();
		this.mobileTelNumber1 = clientInfo.getMobile_tel_number_1();
		this.mobileTelNumber2 = clientInfo.getMobile_tel_number_2();
		this.fixedTelNumber1 = clientInfo.getFixed_tel_number_1();
		this.fixedTelNumber2 = clientInfo.getFixed_tel_number_2();
		this.clientAddress = clientInfo.getClient_address();
		this.secondCategoryName = clientInfo.getSecondCategory().getSecond_category_name();
		this.cardMoney = clientInfo.getCard_money();
		this.cardCode = clientInfo.getCard_code();

		if (clientInfo.getFloorSubsidies() != null) {
			this.floorSubsidiesId = clientInfo.getFloorSubsidies().getId();
			this.floorSubsidiesName = clientInfo.getFloorSubsidies().getFloor_subsidies_name();
		}
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getMobileTelNumber1() {
		return mobileTelNumber1;
	}

	public void setMobileTelNumber1(String mobileTelNumber1) {
		this.mobileTelNumber1 = mobileTelNumber1;
	}

	public String getMobileTelNumber2() {
		return mobileTelNumber2;
	}

	public void setMobileTelNumber2(String mobileTelNumber2) {
		this.mobileTelNumber2 = mobileTelNumber2;
	}

	public String getFixedTelNumber1() {
		return fixedTelNumber1;
	}

	public void setFixedTelNumber1(String fixedTelNumber1) {
		this.fixedTelNumber1 = fixedTelNumber1;
	}

	public String getFixedTelNumber2() {
		return fixedTelNumber2;
	}

	public void setFixedTelNumber2(String fixedTelNumber2) {
		this.fixedTelNumber2 = fixedTelNumber2;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getSecondCategoryName() {
		return secondCategoryName;
	}

	public void setSecondCategoryName(String secondCategoryName) {
		this.secondCategoryName = secondCategoryName;
	}

	public String getThirdCategoryName() {
		return thirdCategoryName;
	}

	public void setThirdCategoryName(String thirdCategoryName) {
		this.thirdCategoryName = thirdCategoryName;
	}

	public Integer getFloorSubsidiesId() {
		return floorSubsidiesId;
	}

	public void setFloorSubsidiesId(Integer floorSubsidiesId) {
		this.floorSubsidiesId = floorSubsidiesId;
	}

	public String getFloorSubsidiesName() {
		return floorSubsidiesName;
	}

	public void setFloorSubsidiesName(String floorSubsidiesName) {
		this.floorSubsidiesName = floorSubsidiesName;
	}

	public Double getCardMoney() {
		return cardMoney;
	}

	public void setCardMoney(Double cardMoney) {
		this.cardMoney = cardMoney;
	}

	public String getLogiMobileTelNumber() {
		return logiMobileTelNumber;
	}

	public void setLogiMobileTelNumber(String logiMobileTelNumber) {
		this.logiMobileTelNumber = logiMobileTelNumber;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

}
