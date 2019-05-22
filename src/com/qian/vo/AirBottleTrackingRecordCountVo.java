package com.qian.vo;

import java.util.Date;

/**
 * 
 * @author sc
 *
 */
public class AirBottleTrackingRecordCountVo {

	private Integer airBottleInfoId;
	
	private Integer deliveryManId;
	
	private String deliveryManFullName;

	private Integer operatorManId;
	
	private String operatorFullName;

	private Integer warehouseInfoId;
	
	private String warehouseInfoName;

	private Integer airBottleTrackingRecordStateId;

	private Date create_time;

	private Integer airBottleTrackingRecordParentId;

	private int is_final; // 是否终止轨迹
	
	private Integer total;
	
	public AirBottleTrackingRecordCountVo(Integer airBottleInfoId,Integer deliveryManId,
			String deliveryManFullName,Integer operatorManId,String operatorFullName,Integer warehouseInfoId,
			String warehouseInfoName,Integer airBottleTrackingRecordStateId,Date create_time,
			Integer airBottleTrackingRecordParentId,int is_final,Long total){

		this.airBottleInfoId = airBottleInfoId;
		this.deliveryManId = deliveryManId;
		this.deliveryManFullName = deliveryManFullName;
		this.operatorManId = operatorManId;
		this.operatorFullName = operatorFullName;
		this.warehouseInfoId = warehouseInfoId;
		this.warehouseInfoName = warehouseInfoName;
		this.airBottleTrackingRecordStateId = airBottleTrackingRecordStateId;
		this.create_time = create_time;
		this.airBottleTrackingRecordParentId = airBottleTrackingRecordParentId;
		this.is_final = is_final;
		this.total = total.intValue();
	}

	public Integer getAirBottleInfoId() {
		return airBottleInfoId;
	}

	public void setAirBottleInfoId(Integer airBottleInfoId) {
		this.airBottleInfoId = airBottleInfoId;
	}

	public Integer getDeliveryManId() {
		return deliveryManId;
	}

	public void setDeliveryManId(Integer deliveryManId) {
		this.deliveryManId = deliveryManId;
	}

	public String getDeliveryManFullName() {
		return deliveryManFullName;
	}

	public void setDeliveryManFullName(String deliveryManFullName) {
		this.deliveryManFullName = deliveryManFullName;
	}

	public Integer getOperatorManId() {
		return operatorManId;
	}

	public void setOperatorManId(Integer operatorManId) {
		this.operatorManId = operatorManId;
	}

	public String getOperatorFullName() {
		return operatorFullName;
	}

	public void setOperatorFullName(String operatorFullName) {
		this.operatorFullName = operatorFullName;
	}

	public Integer getWarehouseInfoId() {
		return warehouseInfoId;
	}

	public void setWarehouseInfoId(Integer warehouseInfoId) {
		this.warehouseInfoId = warehouseInfoId;
	}

	public String getWarehouseInfoName() {
		return warehouseInfoName;
	}

	public void setWarehouseInfoName(String warehouseInfoName) {
		this.warehouseInfoName = warehouseInfoName;
	}

	public Integer getAirBottleTrackingRecordStateId() {
		return airBottleTrackingRecordStateId;
	}

	public void setAirBottleTrackingRecordStateId(
			Integer airBottleTrackingRecordStateId) {
		this.airBottleTrackingRecordStateId = airBottleTrackingRecordStateId;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getAirBottleTrackingRecordParentId() {
		return airBottleTrackingRecordParentId;
	}

	public void setAirBottleTrackingRecordParentId(
			Integer airBottleTrackingRecordParentId) {
		this.airBottleTrackingRecordParentId = airBottleTrackingRecordParentId;
	}

	public int getIs_final() {
		return is_final;
	}

	public void setIs_final(int is_final) {
		this.is_final = is_final;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
