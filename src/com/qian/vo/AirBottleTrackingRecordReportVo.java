package com.qian.vo;

import java.util.Date;

import com.qian.entity.AirBottleTrackingRecord;
import com.qian.entity.ClientInfo;
import com.qian.entity.SecondCategory;
import com.qian.entity.User;
import com.qian.entity.WarehouseInfo;
import com.qian.util.StringUtils;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-14 上午9:39:18
 * @Description
 */
public class AirBottleTrackingRecordReportVo {

	private Integer secondCategoryId;

	private Integer deliveryManId;

	private Integer warehouseId;

	private Integer clientId;

	private String air_bottle_code;

	private String air_bottle_seal_code;

	private String air_bottle_specifications;

	private String second_category_name;

	private String delivery_man_full_name;

	private Integer operatorId;

	private String operator_full_name;

	private String operator_second_category_name;

	private String warehouse_name;

	private Date clinet_last_order_time;

	private String client_code;

	private String client_name;

	private String client_mobile_tel_number;

	private String client_fixed_tel_number;

	private String client_address;

	private String state_description;

	private Date create_time;

	public AirBottleTrackingRecordReportVo() {

	}

	public void initAirBottleTrackingRecordReportVo(User operator, SecondCategory secondCategory, User deliveryMan, WarehouseInfo warehouse,
			ClientInfo clientInfo, Date lastOrderDate) {

		this.operator_full_name = operator.getFull_name();

		if (operator.getSecondCategory() != null) {
			this.operator_second_category_name = operator.getSecondCategory().getSecond_category_name();
		}

		if (secondCategory != null) {
			this.second_category_name = secondCategory.getSecond_category_name();
		}

		if (deliveryMan != null) {
			this.delivery_man_full_name = deliveryMan.getFull_name();
		}

		if (warehouse != null) {
			this.warehouse_name = warehouse.getWarehouse_name();
		}

		if (clientInfo != null) {
			this.client_code = clientInfo.getClient_code();
			this.client_name = clientInfo.getClient_name();

			this.client_address = clientInfo.getClient_address();

			if (StringUtils.nonEmpty(clientInfo.getMobile_tel_number_1())) {
				this.client_mobile_tel_number = clientInfo.getMobile_tel_number_1();
			} else {
				this.client_mobile_tel_number = clientInfo.getMobile_tel_number_2();
			}

			if (StringUtils.nonEmpty(clientInfo.getFixed_tel_number_1())) {
				this.client_fixed_tel_number = clientInfo.getFixed_tel_number_1();
			} else {
				this.client_fixed_tel_number = clientInfo.getFixed_tel_number_2();
			}

			this.clinet_last_order_time = lastOrderDate;
		}
	}

	public AirBottleTrackingRecordReportVo(String air_bottle_code, String air_bottle_seal_code, String air_bottle_specifications, Integer secondCategoryId,
			Integer deliveryManId, Integer operatorId, Integer warehouseId, Integer clientId, String state_description, Date create_time) {

		this.air_bottle_code = air_bottle_code;

		this.air_bottle_seal_code = air_bottle_seal_code;

		this.air_bottle_specifications = air_bottle_specifications;

		this.secondCategoryId = secondCategoryId;

		this.deliveryManId = deliveryManId;

		this.operatorId = operatorId;

		this.warehouseId = warehouseId;

		this.clientId = clientId;

		this.state_description = state_description;

		this.create_time = create_time;
	}

	public AirBottleTrackingRecordReportVo(AirBottleTrackingRecord airBottleTrackingRecord) {

		this.air_bottle_code = airBottleTrackingRecord.getAirBottleInfo().getAir_bottle_code();

		this.air_bottle_seal_code = airBottleTrackingRecord.getAirBottleInfo().getAir_bottle_seal_code();

		this.air_bottle_specifications = airBottleTrackingRecord.getAirBottleInfo().getAirBottleType().getAir_bottle_specifications();

		if (airBottleTrackingRecord.getSecondCategory() != null) {
			this.second_category_name = airBottleTrackingRecord.getSecondCategory().getSecond_category_name();
		}

		if (airBottleTrackingRecord.getDeliveryMan() != null) {
			this.delivery_man_full_name = airBottleTrackingRecord.getDeliveryMan().getFull_name();
		}

		if (airBottleTrackingRecord.getOperator() != null) {
			this.operator_full_name = airBottleTrackingRecord.getOperator().getFull_name();
		}

		if (airBottleTrackingRecord.getWarehouseInfo() != null) {
			this.warehouse_name = airBottleTrackingRecord.getWarehouseInfo().getWarehouse_name();
		}

		if (airBottleTrackingRecord.getClientInfo() != null) {
			this.client_code = airBottleTrackingRecord.getClientInfo().getClient_code();
			this.client_name = airBottleTrackingRecord.getClientInfo().getClient_name();

		}

		this.state_description = airBottleTrackingRecord.getState().getState_description();

		this.create_time = airBottleTrackingRecord.getCreate_time();
	}

	public AirBottleTrackingRecordReportVo(String air_bottle_code, String air_bottle_seal_code, String air_bottle_specifications, String second_category_name,
			String delivery_man_full_name, String operator_full_name, String warehouse_name, String client_code, String client_name, String state_description,
			Date create_time) {

		this.air_bottle_code = air_bottle_code;

		this.air_bottle_seal_code = air_bottle_seal_code;

		this.air_bottle_specifications = air_bottle_specifications;

		this.second_category_name = second_category_name;

		this.delivery_man_full_name = delivery_man_full_name;

		this.operator_full_name = operator_full_name;

		this.warehouse_name = warehouse_name;

		this.client_code = client_code;

		this.client_name = client_name;

		this.state_description = state_description;

		this.create_time = create_time;
	}

	public String getAir_bottle_code() {
		return air_bottle_code;
	}

	public void setAir_bottle_code(String air_bottle_code) {
		this.air_bottle_code = air_bottle_code;
	}

	public String getAir_bottle_seal_code() {
		return air_bottle_seal_code;
	}

	public void setAir_bottle_seal_code(String air_bottle_seal_code) {
		this.air_bottle_seal_code = air_bottle_seal_code;
	}

	public String getAir_bottle_specifications() {
		return air_bottle_specifications;
	}

	public void setAir_bottle_specifications(String air_bottle_specifications) {
		this.air_bottle_specifications = air_bottle_specifications;
	}

	public String getSecond_category_name() {
		return second_category_name;
	}

	public void setSecond_category_name(String second_category_name) {
		this.second_category_name = second_category_name;
	}

	public String getDelivery_man_full_name() {
		return delivery_man_full_name;
	}

	public void setDelivery_man_full_name(String delivery_man_full_name) {
		this.delivery_man_full_name = delivery_man_full_name;
	}

	public String getOperator_full_name() {
		return operator_full_name;
	}

	public void setOperator_full_name(String operator_full_name) {
		this.operator_full_name = operator_full_name;
	}

	public String getWarehouse_name() {
		return warehouse_name;
	}

	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}

	public String getClient_code() {
		return client_code;
	}

	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getState_description() {
		return state_description;
	}

	public void setState_description(String state_description) {
		this.state_description = state_description;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getSecondCategoryId() {
		return secondCategoryId;
	}

	public void setSecondCategoryId(Integer secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
	}

	public Integer getDeliveryManId() {
		return deliveryManId;
	}

	public void setDeliveryManId(Integer deliveryManId) {
		this.deliveryManId = deliveryManId;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Date getClinet_last_order_time() {
		return clinet_last_order_time;
	}

	public void setClinet_last_order_time(Date clinet_last_order_time) {
		this.clinet_last_order_time = clinet_last_order_time;
	}

	public String getOperator_second_category_name() {
		return operator_second_category_name;
	}

	public void setOperator_second_category_name(String operator_second_category_name) {
		this.operator_second_category_name = operator_second_category_name;
	}

	public String getClient_mobile_tel_number() {
		return client_mobile_tel_number;
	}

	public void setClient_mobile_tel_number(String client_mobile_tel_number) {
		this.client_mobile_tel_number = client_mobile_tel_number;
	}

	public String getClient_fixed_tel_number() {
		return client_fixed_tel_number;
	}

	public void setClient_fixed_tel_number(String client_fixed_tel_number) {
		this.client_fixed_tel_number = client_fixed_tel_number;
	}

	public String getClient_address() {
		return client_address;
	}

	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}

}
