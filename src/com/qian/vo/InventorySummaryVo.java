package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2017-10-16 上午10:00:59
 * @Description
 */
public class InventorySummaryVo {

	private Integer deliveryManId;

	private Integer airBottleTypeId;

	private Integer airBottleStateId;

	private Integer inventoryNum;

	public InventorySummaryVo(){
		
	}
	
	public InventorySummaryVo(Integer deliveryManId,Integer airBottleTypeId,Long inventoryNum){
		this.deliveryManId = deliveryManId;
		this.airBottleTypeId = airBottleTypeId;
		this.inventoryNum = inventoryNum.intValue();
	}
	
	/**
	 * @return the deliveryManId
	 */
	public Integer getDeliveryManId() {
		return deliveryManId;
	}

	/**
	 * @param deliveryManId
	 *            the deliveryManId to set
	 */
	public void setDeliveryManId(Integer deliveryManId) {
		this.deliveryManId = deliveryManId;
	}

	/**
	 * @return the airBottleTypeId
	 */
	public Integer getAirBottleTypeId() {
		return airBottleTypeId;
	}

	/**
	 * @param airBottleTypeId
	 *            the airBottleTypeId to set
	 */
	public void setAirBottleTypeId(Integer airBottleTypeId) {
		this.airBottleTypeId = airBottleTypeId;
	}

	/**
	 * @return the airBottleStateId
	 */
	public Integer getAirBottleStateId() {
		return airBottleStateId;
	}

	/**
	 * @param airBottleStateId
	 *            the airBottleStateId to set
	 */
	public void setAirBottleStateId(Integer airBottleStateId) {
		this.airBottleStateId = airBottleStateId;
	}

	/**
	 * @return the inventoryNum
	 */
	public Integer getInventoryNum() {
		return inventoryNum;
	}

	/**
	 * @param inventoryNum
	 *            the inventoryNum to set
	 */
	public void setInventoryNum(Integer inventoryNum) {
		this.inventoryNum = inventoryNum;
	}

}
