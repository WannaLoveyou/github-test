package com.qian.vo;

public class ClientInventoryInfoVo {

	private int clientId;

	private String secondCategoryName;

	private String clientCode;

	private String clientName;

	private String clientTelumber1;

	private String clientTelumber2;

	private String clientTelumber3;

	private String clientTelumber4;

	private String clientAddress;

	private String clientTypeName;

	private int airBottleTypeId;

	private String airBottleTypeName;

	private Integer count;

	public ClientInventoryInfoVo(Integer clientId, String secondCategoryName, String clientCode, String clientName, String clientTypeName,
			String clientTelumber1, String clientTelumber2, String clientTelumber3, String clientTelumber4, String clientAddress, Integer airBottleTypeId,
			String airBottleTypeName, Long count) {
		this.clientId = clientId;
		this.secondCategoryName = secondCategoryName;
		this.clientCode = clientCode;
		this.clientName = clientName;
		this.clientTelumber1 = clientTelumber1;
		this.clientTelumber2 = clientTelumber2;
		this.clientTelumber3 = clientTelumber3;
		this.clientTelumber4 = clientTelumber4;
		this.clientAddress = clientAddress;
		this.clientTypeName = clientTypeName;
		this.airBottleTypeId = airBottleTypeId;
		this.airBottleTypeName = airBottleTypeName;
		this.count = count.intValue();
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientTypeName() {
		return clientTypeName;
	}

	public void setClientTypeName(String clientTypeName) {
		this.clientTypeName = clientTypeName;
	}

	public int getAirBottleTypeId() {
		return airBottleTypeId;
	}

	public void setAirBottleTypeId(int airBottleTypeId) {
		this.airBottleTypeId = airBottleTypeId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getAirBottleTypeName() {
		return airBottleTypeName;
	}

	public void setAirBottleTypeName(String airBottleTypeName) {
		this.airBottleTypeName = airBottleTypeName;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getSecondCategoryName() {
		return secondCategoryName;
	}

	public void setSecondCategoryName(String secondCategoryName) {
		this.secondCategoryName = secondCategoryName;
	}

	public String getClientTelumber1() {
		return clientTelumber1;
	}

	public void setClientTelumber1(String clientTelumber1) {
		this.clientTelumber1 = clientTelumber1;
	}

	public String getClientTelumber2() {
		return clientTelumber2;
	}

	public void setClientTelumber2(String clientTelumber2) {
		this.clientTelumber2 = clientTelumber2;
	}

	public String getClientTelumber3() {
		return clientTelumber3;
	}

	public void setClientTelumber3(String clientTelumber3) {
		this.clientTelumber3 = clientTelumber3;
	}

	public String getClientTelumber4() {
		return clientTelumber4;
	}

	public void setClientTelumber4(String clientTelumber4) {
		this.clientTelumber4 = clientTelumber4;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

}
