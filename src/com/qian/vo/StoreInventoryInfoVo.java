package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2017-1-12 下午5:25:02
 * @Description
 */
public class StoreInventoryInfoVo {

	private int secondCategoryId;

	private int airBottleTypeId;

	private int airBottleStateId;
	
	private Integer inventoryNum;

	public StoreInventoryInfoVo() {

	}

	public StoreInventoryInfoVo(int secondCategoryId, int airBottleTypeId, int airBottleStateId,
			Long inventoryNum) {
		this.secondCategoryId = secondCategoryId;
		this.airBottleTypeId = airBottleTypeId;
		this.airBottleStateId = airBottleStateId;
		this.inventoryNum = inventoryNum.intValue();
	}

	public int getAirBottleTypeId() {
		return airBottleTypeId;
	}

	public void setAirBottleTypeId(int airBottleTypeId) {
		this.airBottleTypeId = airBottleTypeId;
	}

	public int getAirBottleStateId() {
		return airBottleStateId;
	}

	public void setAirBottleStateId(int airBottleStateId) {
		this.airBottleStateId = airBottleStateId;
	}

	public int getSecondCategoryId() {
		return secondCategoryId;
	}

	public void setSecondCategoryId(int secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
	}

	public Integer getInventoryNum() {
		return inventoryNum;
	}

	public void setInventoryNum(Integer inventoryNum) {
		this.inventoryNum = inventoryNum;
	}

}
