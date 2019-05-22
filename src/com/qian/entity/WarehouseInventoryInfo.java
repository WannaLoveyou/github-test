package com.qian.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2017-2-22 下午3:44:26
 * @Description
 */
@Entity
@Table(name = "warehouse_inventory_imformation")
public class WarehouseInventoryInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "warehouse_id")
	private WarehouseInfo warehouseInfo;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_id",unique=true)
	private AirBottleInfo airBottleInfo;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_state_id")
	private AirBottleState airBottleState;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_inventory_state_id")
	private AirBottleInventoryState airBottleInventoryState;

	@Column(nullable = false)
	private Date create_time;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "operator_id")
	private User operator;

	public WarehouseInventoryInfo(){
		
	}
	
	public WarehouseInventoryInfo(WarehouseInfo warehouseInfo,AirBottleInfo airBottleInfo,AirBottleState airBottleState,AirBottleInventoryState airBottleInventoryState, User operator,Date nowTime){
		
		this.warehouseInfo = warehouseInfo;
		this.airBottleInfo = airBottleInfo;
		this.airBottleState = airBottleState;
		this.airBottleInventoryState = airBottleInventoryState;
		this.operator = operator;
		this.create_time = nowTime;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public WarehouseInfo getWarehouseInfo() {
		return warehouseInfo;
	}

	public void setWarehouseInfo(WarehouseInfo warehouseInfo) {
		this.warehouseInfo = warehouseInfo;
	}

	public AirBottleInfo getAirBottleInfo() {
		return airBottleInfo;
	}

	public void setAirBottleInfo(AirBottleInfo airBottleInfo) {
		this.airBottleInfo = airBottleInfo;
	}

	public AirBottleState getAirBottleState() {
		return airBottleState;
	}

	public void setAirBottleState(AirBottleState airBottleState) {
		this.airBottleState = airBottleState;
	}

	public AirBottleInventoryState getAirBottleInventoryState() {
		return airBottleInventoryState;
	}

	public void setAirBottleInventoryState(AirBottleInventoryState airBottleInventoryState) {
		this.airBottleInventoryState = airBottleInventoryState;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

}
