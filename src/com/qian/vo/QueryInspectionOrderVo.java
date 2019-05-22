package com.qian.vo;

import com.qian.util.InspectionUtils;

/**
 * @author Lu Kongwen
 * @version Create time：2018-5-29 下午4:39:47
 * @Description
 */
public class QueryInspectionOrderVo {

	private String key;

	private String checkNumber;

	private String gasNumberId;

	public QueryInspectionOrderVo(String checkNumber) {
		this.key = InspectionUtils.KEY;
		this.checkNumber = checkNumber;
	}

	public QueryInspectionOrderVo(String checkNumber, String gasNumberId) {
		this.key = InspectionUtils.KEY;
		this.checkNumber = checkNumber;
		this.gasNumberId = gasNumberId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public String getGasNumberId() {
		return gasNumberId;
	}

	public void setGasNumberId(String gasNumberId) {
		this.gasNumberId = gasNumberId;
	}

}
