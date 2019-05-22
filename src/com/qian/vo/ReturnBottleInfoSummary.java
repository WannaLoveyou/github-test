package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-30 下午3:24:23
 * @Description
 */
public class ReturnBottleInfoSummary {

	private String order_date;

	private String second_category_name;

	private String delivery_man_full_name;

	private String air_bottle_specifications;

	private Integer returnBottleNum;

	private Integer getBottleNum;

	private Integer forceReturnBottleNum;

	private Double per;

	public ReturnBottleInfoSummary() {
		this.returnBottleNum = 0;
		this.getBottleNum = 0;
		this.forceReturnBottleNum = 0;
		this.per = 0.0;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getSecond_category_name() {
		return second_category_name;
	}

	public void setSecond_category_name(String second_category_name) {
		this.second_category_name = second_category_name;
	}

	public String getDelivery_man_full_name() {
		return delivery_man_full_name;
	}

	public void setDelivery_man_full_name(String delivery_man_full_name) {
		this.delivery_man_full_name = delivery_man_full_name;
	}

	public String getAir_bottle_specifications() {
		return air_bottle_specifications;
	}

	public void setAir_bottle_specifications(String air_bottle_specifications) {
		this.air_bottle_specifications = air_bottle_specifications;
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

	public Integer getForceReturnBottleNum() {
		return forceReturnBottleNum;
	}

	public void setForceReturnBottleNum(Integer forceReturnBottleNum) {
		this.forceReturnBottleNum = forceReturnBottleNum;
	}

	public Double getPer() {
		return per;
	}

	public void setPer(Double per) {
		this.per = per;
	}

}
