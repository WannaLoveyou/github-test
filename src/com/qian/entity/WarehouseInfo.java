package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2015-12-18 上午10:54:57
 * @Description 仓库信息表
 */

@Entity
@Table(name = "warehouse_imformation")
public class WarehouseInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50)
	private String warehouse_code;

	@Column(length = 50)
	private String warehouse_name;

	@Column(length = 50)
	private String filling_station_id;

	@Column(nullable = false, columnDefinition = "INT default 0")
	private int disabled_state;

	public WarehouseInfo() {

	}

	public WarehouseInfo(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWarehouse_name() {
		return warehouse_name;
	}

	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}

	public String getWarehouse_code() {
		return warehouse_code;
	}

	public void setWarehouse_code(String warehouse_code) {
		this.warehouse_code = warehouse_code;
	}

	public int getDisabled_state() {
		return disabled_state;
	}

	public void setDisabled_state(int disabled_state) {
		this.disabled_state = disabled_state;
	}

	public String getFilling_station_id() {
		return filling_station_id;
	}

	public void setFilling_station_id(String filling_station_id) {
		this.filling_station_id = filling_station_id;
	}

}
