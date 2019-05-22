package com.qian.vo;

import java.util.Date;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-16 下午6:27:32
 * @Description
 */
public class FillCheckRecordVo {

	private Date fill_date;

	private String air_bottle_seal_code;

	private String volume;

	private Date produce_time;

	private Date check_time;

	private Integer operator_id;

	private String operator_full_name;

	private Double bottle_weight; // 气瓶总量

	private Double total_weight; // 含气总重

	public FillCheckRecordVo(Date fill_date,String air_bottle_seal_code,String volume,Date produce_time,Date check_time,Integer operator_id,String operator_full_name,Double bottle_weight,Double total_weight){
		this.fill_date = fill_date;
		this.air_bottle_seal_code = air_bottle_seal_code;
		this.volume = volume;
		this.produce_time = produce_time;
		this.check_time = check_time;
		this.operator_id = operator_id;
		this.operator_full_name = operator_full_name;
		this.bottle_weight = bottle_weight;
		this.total_weight = total_weight;
	}
	
	public Date getFill_date() {
		return fill_date;
	}

	public void setFill_date(Date fill_date) {
		this.fill_date = fill_date;
	}

	public String getAir_bottle_seal_code() {
		return air_bottle_seal_code;
	}

	public void setAir_bottle_seal_code(String air_bottle_seal_code) {
		this.air_bottle_seal_code = air_bottle_seal_code;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public Date getProduce_time() {
		return produce_time;
	}

	public void setProduce_time(Date produce_time) {
		this.produce_time = produce_time;
	}

	public Date getCheck_time() {
		return check_time;
	}

	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}

	public Integer getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(Integer operator_id) {
		this.operator_id = operator_id;
	}

	public String getOperator_full_name() {
		return operator_full_name;
	}

	public void setOperator_full_name(String operator_full_name) {
		this.operator_full_name = operator_full_name;
	}

	public Double getBottle_weight() {
		return bottle_weight;
	}

	public void setBottle_weight(Double bottle_weight) {
		this.bottle_weight = bottle_weight;
	}

	public Double getTotal_weight() {
		return total_weight;
	}

	public void setTotal_weight(Double total_weight) {
		this.total_weight = total_weight;
	}

}
