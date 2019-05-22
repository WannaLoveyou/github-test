package com.qian.vo;

import java.util.List;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-26 上午9:08:37
 * @Description
 */
public class RongXinTongVo {

	private String orderId;

	private String orderDate;

	private String orderFee;

	private String fee;

	private String detailCount;

	private String agentId;

	private String tradeId;

	private List<RongXinTongDetailVo> detail;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderFee() {
		return orderFee;
	}

	public void setOrderFee(String orderFee) {
		this.orderFee = orderFee;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(String detailCount) {
		this.detailCount = detailCount;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public List<RongXinTongDetailVo> getDetail() {
		return detail;
	}

	public void setDetail(List<RongXinTongDetailVo> detail) {
		this.detail = detail;
	}

}
