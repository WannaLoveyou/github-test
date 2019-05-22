package com.qian.mobile.entity;

import com.qian.entity.FillCheckInfo;

/**
 * @author Lu Kongwen
 * @version Create time：2018-10-23 下午4:08:03
 * @Description
 */
public class MobileFillCheckInfo {

	private int itemId;

	private String itemCode;

	private String itemDescription;

	public MobileFillCheckInfo(FillCheckInfo fillCheckInfo){
		this.itemId = fillCheckInfo.getId();
		this.itemCode = fillCheckInfo.getFill_check_code();
		this.itemDescription = fillCheckInfo.getFill_check_description();
	}
	
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

}
