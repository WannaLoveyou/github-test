package com.qian.app.entity;

import com.qian.entity.PayType;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-22 下午4:38:37
 * @Description
 */
public class AppPayType {

	private int payTypeId; // 付款方式ID

	private String payTypeName; // 付款方式名

	public AppPayType(PayType payType) {

		this.payTypeId = payType.getId();

		this.payTypeName = payType.getPay_type_name();
	}

	public int getPayTypeId() {
		return payTypeId;
	}

	public void setPayTypeId(int payTypeId) {
		this.payTypeId = payTypeId;
	}

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

}
