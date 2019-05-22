package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-13 下午3:10:10
 * @Description
 */
public class CheckOperatorFamilyCheckInfoVo {

	private Integer second_category_id;

	private String second_category_name;

	private Integer check_operator_id;

	private String check_operator_full_name;

	private Integer check_order_num;

	private Integer check_client_num;

	public CheckOperatorFamilyCheckInfoVo() {
		this.check_order_num = 0;
		this.check_client_num = 0;
	}
	
	public CheckOperatorFamilyCheckInfoVo(Integer second_category_id,Integer check_operator_id,String check_operator_full_name,Long check_order_num,Long check_client_num){
		this.second_category_id = second_category_id;
		this.check_operator_id = check_operator_id;
		this.check_operator_full_name = check_operator_full_name;
		this.check_order_num = check_order_num.intValue();
		this.check_client_num = check_client_num.intValue();
	}

	public Integer getSecond_category_id() {
		return second_category_id;
	}

	public void setSecond_category_id(Integer second_category_id) {
		this.second_category_id = second_category_id;
	}

	public String getSecond_category_name() {
		return second_category_name;
	}

	public void setSecond_category_name(String second_category_name) {
		this.second_category_name = second_category_name;
	}

	public Integer getCheck_operator_id() {
		return check_operator_id;
	}

	public void setCheck_operator_id(Integer check_operator_id) {
		this.check_operator_id = check_operator_id;
	}

	public String getCheck_operator_full_name() {
		return check_operator_full_name;
	}

	public void setCheck_operator_full_name(String check_operator_full_name) {
		this.check_operator_full_name = check_operator_full_name;
	}

	public Integer getCheck_order_num() {
		return check_order_num;
	}

	public void setCheck_order_num(Integer check_order_num) {
		this.check_order_num = check_order_num;
	}

	public Integer getCheck_client_num() {
		return check_client_num;
	}

	public void setCheck_client_num(Integer check_client_num) {
		this.check_client_num = check_client_num;
	}

}
