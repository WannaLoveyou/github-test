package com.qian.vo;

import java.util.Date;

import com.qian.entity.User;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-28 下午5:08:01
 * @Description
 */
public class ICCardRecordReportVo {

	private String client_code;

	private String client_name;

	private String client_second_category_name;

	private String card_code;

	private Integer operatorId;

	private String operator_full_name;

	private String operator_second_category_name;

	private Date create_time;

	private String state_description;

	public ICCardRecordReportVo() {

	}

	public ICCardRecordReportVo(String client_code, String client_name, String client_second_category_name, String card_code, Integer operatorId,
			Date create_time, String state_description) {

		this.client_code = client_code;
		this.client_name = client_name;
		this.client_second_category_name = client_second_category_name;
		this.card_code = card_code;
		this.operatorId = operatorId;
		this.create_time = create_time;
		this.state_description = state_description;
	}

	public void initICCardRecordReportVo(User operator) {

		this.operator_full_name = operator.getFull_name();
		if (operator.getSecondCategory() != null) {
			this.operator_second_category_name = operator.getSecondCategory().getSecond_category_name();
		}
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getClient_code() {
		return client_code;
	}

	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}

	public String getClient_second_category_name() {
		return client_second_category_name;
	}

	public void setClient_second_category_name(String client_second_category_name) {
		this.client_second_category_name = client_second_category_name;
	}

	public String getCard_code() {
		return card_code;
	}

	public void setCard_code(String card_code) {
		this.card_code = card_code;
	}

	public String getOperator_full_name() {
		return operator_full_name;
	}

	public void setOperator_full_name(String operator_full_name) {
		this.operator_full_name = operator_full_name;
	}

	public String getOperator_second_category_name() {
		return operator_second_category_name;
	}

	public void setOperator_second_category_name(String operator_second_category_name) {
		this.operator_second_category_name = operator_second_category_name;
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

}
