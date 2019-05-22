package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-8-24 下午4:57:03
 * @Description
 */
public class WarehouseInventoryInfoVo {

	private int warehouseId;
	
	private int airBottleTypeId;

	private int airBottleStateId;
	
	private Integer inventoryNum;

	public WarehouseInventoryInfoVo(){
		
	}
	
	public WarehouseInventoryInfoVo(int warehouseId,int airBottleTypeId,int airBottleStateId,
			Long inventoryNum){
		this.warehouseId = warehouseId;
		this.airBottleTypeId = airBottleTypeId;
		this.airBottleStateId = airBottleStateId;
		this.inventoryNum = inventoryNum.intValue();
	}
	
	public Integer getInventoryNum() {
		return inventoryNum;
	}

	public void setInventoryNum(Integer inventoryNum) {
		this.inventoryNum = inventoryNum;
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

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

}
