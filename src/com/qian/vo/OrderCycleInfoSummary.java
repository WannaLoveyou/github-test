package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2017-1-7 上午10:01:41
 * @Description
 */
public class OrderCycleInfoSummary {

	private String clientTypeName;

	private String bottleTypeName;

	private Integer clientNum;

	private Integer orderNum;

	private Integer orderBottleNum;

	private Integer totalCycle;

	private Double avgOrderCycle;

	public OrderCycleInfoSummary() {
		this.clientNum = 0;
		this.orderNum = 0;
		this.orderBottleNum = 0;
		this.totalCycle = 0;
		this.avgOrderCycle = 0.0;
	}

	public String getClientTypeName() {
		return clientTypeName;
	}

	public void setClientTypeName(String clientTypeName) {
		this.clientTypeName = clientTypeName;
	}

	public Integer getClientNum() {
		return clientNum;
	}

	public void setClientNum(Integer clientNum) {
		this.clientNum = clientNum;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getOrderBottleNum() {
		return orderBottleNum;
	}

	public void setOrderBottleNum(Integer orderBottleNum) {
		this.orderBottleNum = orderBottleNum;
	}

	public Double getAvgOrderCycle() {
		return avgOrderCycle;
	}

	public void setAvgOrderCycle(Double avgOrderCycle) {
		this.avgOrderCycle = avgOrderCycle;
	}

	public Integer getTotalCycle() {
		return totalCycle;
	}

	public void setTotalCycle(Integer totalCycle) {
		this.totalCycle = totalCycle;
	}

	public String getBottleTypeName() {
		return bottleTypeName;
	}

	public void setBottleTypeName(String bottleTypeName) {
		this.bottleTypeName = bottleTypeName;
	}

}
