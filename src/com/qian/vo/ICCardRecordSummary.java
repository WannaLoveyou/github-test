package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-21 上午10:55:55
 * @Description
 */
public class ICCardRecordSummary {

	private String client_second_category_name;

	private String operator_second_category_name;

	private String state_description;

	private Integer num;

	private Double money;

	private Double total_amount;

	public ICCardRecordSummary() {

	}

	public ICCardRecordSummary(String state_description) {
		this.num = 0;

		if (state_description.equals("首次开卡")) {
			this.money = 0.0; // 首次开卡免费
		} else {
			this.money = 5.0; // 补卡费5块
		}

		this.total_amount = 0.0;
	}

	public String getClient_second_category_name() {
		return client_second_category_name;
	}

	public void setClient_second_category_name(String client_second_category_name) {
		this.client_second_category_name = client_second_category_name;
	}

	public String getOperator_second_category_name() {
		return operator_second_category_name;
	}

	public void setOperator_second_category_name(String operator_second_category_name) {
		this.operator_second_category_name = operator_second_category_name;
	}

	public String getState_description() {
		return state_description;
	}

	public void setState_description(String state_description) {
		this.state_description = state_description;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
	}

}
