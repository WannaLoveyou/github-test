package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2017-7-17 上午11:11:57
 * @Description
 */
public class DeliveryManInventoryInfoVo {

	private Integer deliveryManId;

	private Integer materialTypeId;

	private Integer airBottleStateId;

	private Integer inventoryNum;

	private String delivery_man_name; // 送气工名

	private String air_bottle_state_description; // 气瓶状态类型名

	private String material_type_name; // 物料类型名

	private String store_name; // 门店名称

	public DeliveryManInventoryInfoVo() {

	}

	public DeliveryManInventoryInfoVo(Integer deliveryManId, Integer materialTypeId, Integer airBottleStateId, Long inventoryNum) {
		this.deliveryManId = deliveryManId;
		this.materialTypeId = materialTypeId;
		this.airBottleStateId = airBottleStateId;
		this.inventoryNum = inventoryNum.intValue();
	}

	public Integer getDeliveryManId() {
		return deliveryManId;
	}

	public void setDeliveryManId(Integer deliveryManId) {
		this.deliveryManId = deliveryManId;
	}

	public String getDelivery_man_name() {
		return delivery_man_name;
	}

	public void setDelivery_man_name(String delivery_man_name) {
		this.delivery_man_name = delivery_man_name;
	}

	public String getAir_bottle_state_description() {
		return air_bottle_state_description;
	}

	public void setAir_bottle_state_description(String air_bottle_state_description) {
		this.air_bottle_state_description = air_bottle_state_description;
	}

	public String getMaterial_type_name() {
		return material_type_name;
	}

	public void setMaterial_type_name(String material_type_name) {
		this.material_type_name = material_type_name;
	}

	public Integer getMaterialTypeId() {
		return materialTypeId;
	}

	public void setMaterialTypeId(Integer materialTypeId) {
		this.materialTypeId = materialTypeId;
	}

	public Integer getAirBottleStateId() {
		return airBottleStateId;
	}

	public void setAirBottleStateId(Integer airBottleStateId) {
		this.airBottleStateId = airBottleStateId;
	}

	public Integer getInventoryNum() {
		return inventoryNum;
	}

	public void setInventoryNum(Integer inventoryNum) {
		this.inventoryNum = inventoryNum;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

}
