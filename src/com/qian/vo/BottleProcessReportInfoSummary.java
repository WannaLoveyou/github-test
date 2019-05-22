package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2017-1-9 下午5:36:21
 * @Description
 */
public class BottleProcessReportInfoSummary {

	private String order_date;

	private String air_bottle_specifications;

	private Integer send_bottle_num;

	private Integer back_bottle_num;

	private Integer exception_return_waiting_for_check_bottle_num;

	private Integer exception_return_pass_bottle_num;

	public BottleProcessReportInfoSummary() {
		this.send_bottle_num = 0;
		this.back_bottle_num = 0;
		this.exception_return_waiting_for_check_bottle_num = 0;
		this.exception_return_pass_bottle_num = 0;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getAir_bottle_specifications() {
		return air_bottle_specifications;
	}

	public void setAir_bottle_specifications(String air_bottle_specifications) {
		this.air_bottle_specifications = air_bottle_specifications;
	}

	public Integer getSend_bottle_num() {
		return send_bottle_num;
	}

	public void setSend_bottle_num(Integer send_bottle_num) {
		this.send_bottle_num = send_bottle_num;
	}

	public Integer getException_return_waiting_for_check_bottle_num() {
		return exception_return_waiting_for_check_bottle_num;
	}

	public void setException_return_waiting_for_check_bottle_num(Integer exception_return_waiting_for_check_bottle_num) {
		this.exception_return_waiting_for_check_bottle_num = exception_return_waiting_for_check_bottle_num;
	}

	public Integer getException_return_pass_bottle_num() {
		return exception_return_pass_bottle_num;
	}

	public void setException_return_pass_bottle_num(Integer exception_return_pass_bottle_num) {
		this.exception_return_pass_bottle_num = exception_return_pass_bottle_num;
	}

	public Integer getBack_bottle_num() {
		return back_bottle_num;
	}

	public void setBack_bottle_num(Integer back_bottle_num) {
		this.back_bottle_num = back_bottle_num;
	}

}
