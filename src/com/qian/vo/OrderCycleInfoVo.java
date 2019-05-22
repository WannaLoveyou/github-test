package com.qian.vo;

import java.util.Date;

/**
 * @author Lu Kongwen
 * @version Create time：2017-1-7 上午10:28:06
 * @Description
 */
public class OrderCycleInfoVo {

	private Integer clientId;

	private Integer clientTypeId;

	private Integer airBottleTypeId;

	private Integer orderNum;

	private Integer orderBottleNum;

	private Date beginTime;

	private Date endTime;

	public OrderCycleInfoVo() {

	}

	public OrderCycleInfoVo(Integer clientId, Integer clientTypeId, Integer airBottleTypeId, Long orderNum, Long orderBottleNum, Date beginTime, Date endTime) {

		this.clientId = clientId;
		this.clientTypeId = clientTypeId;
		this.airBottleTypeId = airBottleTypeId;
		this.orderNum = orderNum.intValue();
		this.orderBottleNum = orderBottleNum.intValue();
		this.beginTime = beginTime;
		this.endTime = endTime;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getClientTypeId() {
		return clientTypeId;
	}

	public void setClientTypeId(Integer clientTypeId) {
		this.clientTypeId = clientTypeId;
	}

	public Integer getOrderBottleNum() {
		return orderBottleNum;
	}

	public void setOrderBottleNum(Integer orderBottleNum) {
		this.orderBottleNum = orderBottleNum;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getAirBottleTypeId() {
		return airBottleTypeId;
	}

	public void setAirBottleTypeId(Integer airBottleTypeId) {
		this.airBottleTypeId = airBottleTypeId;
	}

}
