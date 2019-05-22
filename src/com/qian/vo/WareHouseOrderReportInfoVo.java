package com.qian.vo;

import java.util.Date;

/**
 * @author Lu Kongwen
 * @version Create time：2016-8-25 下午5:00:42
 * @Description
 */
public class WareHouseOrderReportInfoVo {

	private Integer secondCategoryId;

	private int airBottleTypeId;

	private int orderNum;

	private Date orderTime;

	public WareHouseOrderReportInfoVo() {

	}

	public WareHouseOrderReportInfoVo(Integer secondCategoryId, int airBottleTypeId, int orderNum, Date orderTime) {

		this.secondCategoryId = secondCategoryId;
		this.airBottleTypeId = airBottleTypeId;
		this.orderNum = orderNum;
		this.orderTime = orderTime;

	}

	public Integer getSecondCategoryId() {
		return secondCategoryId;
	}

	public void setSecondCategoryId(Integer secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
	}

	public int getAirBottleTypeId() {
		return airBottleTypeId;
	}

	public void setAirBottleTypeId(int airBottleTypeId) {
		this.airBottleTypeId = airBottleTypeId;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

}
