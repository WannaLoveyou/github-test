package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2017-7-22 上午8:43:52
 * @Description
 */
public class BussinessOrderInfoEntity {

	public Integer warehouse_order_id;

	public Integer client_id;

	public String warehouse_order_remark;

	public String order_tel_number;

	public String order_address;

	public Integer getWarehouse_order_id() {
		return warehouse_order_id;
	}

	public void setWarehouse_order_id(Integer warehouse_order_id) {
		this.warehouse_order_id = warehouse_order_id;
	}

	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}

	public String getWarehouse_order_remark() {
		return warehouse_order_remark;
	}

	public void setWarehouse_order_remark(String warehouse_order_remark) {
		this.warehouse_order_remark = warehouse_order_remark;
	}

	public String getOrder_tel_number() {
		return order_tel_number;
	}

	public void setOrder_tel_number(String order_tel_number) {
		this.order_tel_number = order_tel_number;
	}

	public String getOrder_address() {
		return order_address;
	}

	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}

}
