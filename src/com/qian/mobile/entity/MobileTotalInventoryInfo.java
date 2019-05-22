package com.qian.mobile.entity;

public class MobileTotalInventoryInfo {

	private Integer store_id; // 门店ID

	private String store_name; // 门店名
	
	private Integer delivery_man_id; //配送工ID
	
	private String delivery_man_name;//配送工名

	private Integer air_bottle_state_id; // 气瓶状态ID

	private String air_bottle_state_description; // 气瓶状态类型名

	private Integer air_bottle_type_id; // 气瓶类型ID

	private String air_bottle_type_name; // 气瓶类型名

	private Integer inventory_num; // 库存数量

	public MobileTotalInventoryInfo(){
		
	}
	
	public MobileTotalInventoryInfo(Integer delivery_man_id,String delivery_man_name,Integer air_bottle_type_id,String air_bottle_type_name,Integer air_bottle_state_id,String air_bottle_state_description,Long inventory_num){
		this.delivery_man_id = delivery_man_id;
		this.delivery_man_name = delivery_man_name;
		this.air_bottle_type_id = air_bottle_type_id;
		this.air_bottle_type_name = air_bottle_type_name;
		this.air_bottle_state_id = air_bottle_state_id;
		this.air_bottle_state_description = air_bottle_state_description;
		this.inventory_num = inventory_num.intValue();
	}
	
	public Integer getStore_id() {
		return store_id;
	}

	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public Integer getDelivery_man_id() {
		return delivery_man_id;
	}

	public void setDelivery_man_id(Integer delivery_man_id) {
		this.delivery_man_id = delivery_man_id;
	}

	public String getDelivery_man_name() {
		return delivery_man_name;
	}

	public void setDelivery_man_name(String delivery_man_name) {
		this.delivery_man_name = delivery_man_name;
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
