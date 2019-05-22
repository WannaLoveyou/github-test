package com.qian.mobile.entity;

import java.util.Date;

import com.qian.entity.AirBottleTrackingRecord;
import com.qian.entity.User;

/**
 * @author Lu Kongwen
 * @version Create time：2016-7-29 下午2:41:01
 * @Description
 */
public class MobileAirBottleTrackingRecord {

	private String second_category_name; // 门店

	private String delivery_man_name; // 配送工

	private String operator_name; // 操作员

	private String warehouse_name; // 仓库

	private MobileClientInfo mobileClinentInfo;

	private String client_name; // 客户

	private Date create_time; // 时间

	private String state_description; // 事情描述

	public MobileAirBottleTrackingRecord(AirBottleTrackingRecord airBottleTrackingRecord, User user) {

		if (airBottleTrackingRecord.getSecondCategory() != null) {
			this.second_category_name = airBottleTrackingRecord.getSecondCategory().getSecond_category_name();
		}

		if (airBottleTrackingRecord.getDeliveryMan() != null) {
			this.delivery_man_name = airBottleTrackingRecord.getDeliveryMan().getFull_name();
		}

		if (airBottleTrackingRecord.getOperator() != null) {
			this.operator_name = airBottleTrackingRecord.getOperator().getFull_name();
		}

		if (airBottleTrackingRecord.getWarehouseInfo() != null) {
			this.warehouse_name = airBottleTrackingRecord.getWarehouseInfo().getWarehouse_name();
		}

		if (airBottleTrackingRecord.getClientInfo() != null) {

			this.client_name = airBottleTrackingRecord.getClientInfo().getClient_name();

			if (user.getSecondCategory() == null
					|| user.getSecondCategory().getId() == airBottleTrackingRecord.getClientInfo().getSecondCategory().getId()) {
				this.mobileClinentInfo = new MobileClientInfo(airBottleTrackingRecord.getClientInfo());
			}

		}

		this.create_time = airBottleTrackingRecord.getCreate_time();
		this.state_description = airBottleTrackingRecord.getState().getState_description();
	}

	public String getSecond_category_name() {
		return second_category_name;
	}

	public void setSecond_category_name(String second_category_name) {
		this.second_category_name = second_category_name;
	}

	public String getDelivery_man_name() {
		return delivery_man_name;
	}

	public void setDelivery_man_name(String delivery_man_name) {
		this.delivery_man_name = delivery_man_name;
	}

	public String getOperator_name() {
		return operator_name;
	}

	public void setOperator_name(String operator_name) {
		this.operator_name = operator_name;
	}

	public String getWarehouse_name() {
		return warehouse_name;
	}

	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getState_description() {
		return state_description;
	}

	public void setState_description(String state_description) {
		this.state_description = state_description;
	}

	public MobileClientInfo getMobileClinentInfo() {
		return mobileClinentInfo;
	}

	public void setMobileClinentInfo(MobileClientInfo mobileClinentInfo) {
		this.mobileClinentInfo = mobileClinentInfo;
	}

}
