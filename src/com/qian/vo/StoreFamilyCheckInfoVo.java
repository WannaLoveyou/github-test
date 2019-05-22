package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-12 下午5:45:37
 * @Description
 */
public class StoreFamilyCheckInfoVo {

	private Integer second_category_id;

	private String second_category_name;

	private Integer check_client_num;

	private Integer total_client_num;

	private Double check_per;

	public StoreFamilyCheckInfoVo(){
		this.check_client_num = 0;
		this.total_client_num = 0;
		this.check_per = 0.0;
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

	public Integer getCheck_client_num() {
		return check_client_num;
	}

	public void setCheck_client_num(Integer check_client_num) {
		this.check_client_num = check_client_num;
	}

	public Integer getTotal_client_num() {
		return total_client_num;
	}

	public void setTotal_client_num(Integer total_client_num) {
		this.total_client_num = total_client_num;
	}

	public Double getCheck_per() {
		return check_per;
	}

	public void setCheck_per(Double check_per) {
		this.check_per = check_per;
	}

}
