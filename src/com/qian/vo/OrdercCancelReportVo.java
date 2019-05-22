package com.qian.vo;

import java.util.Date;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-12 下午4:57:18
 * @Description
 */
public class OrdercCancelReportVo {

	private int secondCategoryId;

	private Date order_time;

	public OrdercCancelReportVo() {

	}

	public OrdercCancelReportVo(int secondCategoryId, Date order_time) {
		this.secondCategoryId = secondCategoryId;
		this.order_time = order_time;
	}

	public int getSecondCategoryId() {
		return secondCategoryId;
	}

	public void setSecondCategoryId(int secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

}
