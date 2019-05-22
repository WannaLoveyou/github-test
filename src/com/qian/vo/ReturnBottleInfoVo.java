package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-30 下午4:06:17
 * @Description
 */
public class ReturnBottleInfoVo {

	private String date;

	private Integer secondCategoryId;

	private Integer airBottleTypeId;

	private Integer deliveryManId;

	private Integer returnBottleNum;

	private Integer getBottleNum;

	public ReturnBottleInfoVo() {

	}

	public ReturnBottleInfoVo(String date, Integer secondCategoryId, Integer airBottleTypeId, Integer deliveryManId, Long returnBottleNum, Long getBottleNum) {

		this.date = date;
		this.secondCategoryId = secondCategoryId;
		this.airBottleTypeId = airBottleTypeId;
		this.deliveryManId = deliveryManId;
		this.returnBottleNum = returnBottleNum.intValue();
		this.getBottleNum = getBottleNum.intValue();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getSecondCategoryId() {
		return secondCategoryId;
	}

	public void setSecondCategoryId(Integer secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
	}

	public Integer getAirBottleTypeId() {
		return airBottleTypeId;
	}

	public void setAirBottleTypeId(Integer airBottleTypeId) {
		this.airBottleTypeId = airBottleTypeId;
	}

	public Integer getDeliveryManId() {
		return deliveryManId;
	}

	public void setDeliveryManId(Integer deliveryManId) {
		this.deliveryManId = deliveryManId;
	}

	public Integer getReturnBottleNum() {
		return returnBottleNum;
	}

	public void setReturnBottleNum(Integer returnBottleNum) {
		this.returnBottleNum = returnBottleNum;
	}

	public Integer getGetBottleNum() {
		return getBottleNum;
	}

	public void setGetBottleNum(Integer getBottleNum) {
		this.getBottleNum = getBottleNum;
	}

}
