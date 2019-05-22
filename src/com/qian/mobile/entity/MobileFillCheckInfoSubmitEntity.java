package com.qian.mobile.entity;

/**
 * @author Lu Kongwen
 * @version Create time：2018-10-23 下午8:50:16
 * @Description
 */
public class MobileFillCheckInfoSubmitEntity {

	private int userId;

	private int airBottleId;

	private String itemIds;

	private Integer before_or_after_fill;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAirBottleId() {
		return airBottleId;
	}

	public void setAirBottleId(int airBottleId) {
		this.airBottleId = airBottleId;
	}

	public String getItemIds() {
		return itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public Integer getBefore_or_after_fill() {
		return before_or_after_fill;
	}

	public void setBefore_or_after_fill(Integer before_or_after_fill) {
		this.before_or_after_fill = before_or_after_fill;
	}

}
