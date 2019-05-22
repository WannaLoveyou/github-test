package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-5-21 下午2:56:02
 * @Description
 */
public class WarehouseInventoryInfoSummary {

	/**
	 * 仓库id
	 */
	private Integer warehouse_id;
	
	/**
	 * 仓库名
	 */
	private String warehouse_name;
	
	private Integer air_bottle_state_id; // 气瓶状态ID

	private String air_bottle_state_description; // 气瓶状态类型名

	private Integer air_bottle_type_id; // 物料ID

	private String air_bottle_type_name; // 物料类型名

	private Integer inventory_num; // 库存数量

	public Integer getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(Integer warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	public String getWarehouse_name() {
		return warehouse_name;
	}

	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}

	public Integer getAir_bottle_state_id() {
		return air_bottle_state_id;
	}

	public void setAir_bottle_state_id(Integer air_bottle_state_id) {
		this.air_bottle_state_id = air_bottle_state_id;
	}

	public String getAir_bottle_state_description() {
		return air_bottle_state_description;
	}

	public void setAir_bottle_state_description(String air_bottle_state_description) {
		this.air_bottle_state_description = air_bottle_state_description;
	}

	public Integer getAir_bottle_type_id() {
		return air_bottle_type_id;
	}

	public void setAir_bottle_type_id(Integer air_bottle_type_id) {
		this.air_bottle_type_id = air_bottle_type_id;
	}

	public String getAir_bottle_type_name() {
		return air_bottle_type_name;
	}

	public void setAir_bottle_type_name(String air_bottle_type_name) {
		this.air_bottle_type_name = air_bottle_type_name;
	}

	public Integer getInventory_num() {
		return inventory_num;
	}

	public void setInventory_num(Integer inventory_num) {
		this.inventory_num = inventory_num;
	}

}
