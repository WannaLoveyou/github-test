package com.qian.vo;

/**
 * 
 * @author sc
 *
 */
public class HeavyBottleOutToDeliveryManInWarehouseVo {

	private String name;
	
	private Integer total;
	
	public HeavyBottleOutToDeliveryManInWarehouseVo(String name,Long total){
		this.name = name;
		this.total = total.intValue();
	}
	
	public HeavyBottleOutToDeliveryManInWarehouseVo(AirBottleTrackingRecordCountVo airBottleTrackingRecordTotalVo){
		this.name = airBottleTrackingRecordTotalVo.getDeliveryManFullName();
		this.total = airBottleTrackingRecordTotalVo.getTotal();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
