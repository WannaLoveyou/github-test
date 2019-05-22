package com.qian.app.entity;

/**
 * @author Lu Kongwen
 * @version Create time：2018-3-15 下午4:34:10
 * @Description
 */
public class AppAddClientInfo {

	private Integer userId; // 用户ID

	private String clientName; // 客户姓名

	private String mobileTelNumber; // 移动电话

	private String clientAddress; // 客户地址

	private Integer secondCategoryId; // 门店ID

	private Integer floorSubsidiesId; // 楼层补贴ID

	private String remark;

	private Double latitude;

	private Double longitude;

	private String openid;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getMobileTelNumber() {
		return mobileTelNumber;
	}

	public void setMobileTelNumber(String mobileTelNumber) {
		this.mobileTelNumber = mobileTelNumber;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public Integer getSecondCategoryId() {
		return secondCategoryId;
	}

	public void setSecondCategoryId(Integer secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
	}

	public Integer getFloorSubsidiesId() {
		return floorSubsidiesId;
	}

	public void setFloorSubsidiesId(Integer floorSubsidiesId) {
		this.floorSubsidiesId = floorSubsidiesId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
