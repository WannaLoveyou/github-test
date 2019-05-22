package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2017-7-6 下午4:42:25
 * @Description
 */
public class AirBottleInfoReportDetailVo {

	private Integer delivery_man_id;

	private String delivery_man_name;

	private String delivery_man_store_name;

	private Integer air_bottle_state_id;

	private String air_bottle_state_description;

	private Integer air_bottle_type_id;

	private String air_bottle_type_name;

	private String air_bottle_code;

	private String air_bottle_seal_code;

	private String create_time;

	private String operator;

	public AirBottleInfoReportDetailVo() {

	}

	public AirBottleInfoReportDetailVo(String air_bottle_code, String air_bottle_seal_code, String create_time, String operator) {

		this.air_bottle_code = air_bottle_code;
		this.air_bottle_seal_code = air_bottle_seal_code;
		this.create_time = create_time;
		this.operator = operator;
	}

	public AirBottleInfoReportDetailVo(Integer delivery_man_id, Integer air_bottle_state_id, Integer air_bottle_type_id, 
			String air_bottle_code, String air_bottle_seal_code, String create_time, String operator) {

		this.delivery_man_id = delivery_man_id;
		this.air_bottle_state_id = air_bottle_state_id;
		this.air_bottle_type_id = air_bottle_type_id;
		this.air_bottle_code = air_bottle_code;
		this.air_bottle_seal_code = air_bottle_seal_code;
		this.create_time = create_time;
		this.operator = operator;
	}

	public Integer getDelivery_man_id() {
		return delivery_man_id;
	}

	public void setDelivery_man_id(Integer delivery_man_id) {
		this.delivery_man_id = delivery_man_id;
	}

	public Integer getAir_bottle_state_id() {
		return air_bottle_state_id;
	}

	public void setAir_bottle_state_id(Integer air_bottle_state_id) {
		this.air_bottle_state_id = air_bottle_state_id;
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

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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

	public String getDelivery_man_store_name() {
		return delivery_man_store_name;
	}

	public void setDelivery_man_store_name(String delivery_man_store_name) {
		this.delivery_man_store_name = delivery_man_store_name;
	}

}
