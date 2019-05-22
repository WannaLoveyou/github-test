package com.qian.vo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-21 上午10:54:57
 * @Description
 */
public class SaleReportInfoSummaryByAirBottleType {

	private String order_date;

	private String second_category_name;

	private Integer num_for_5KG;

	private Integer num_for_15KG;

	private Integer num_for_50KG;

	private Integer num_for_2KG;

	private Integer num_for_total;

	private Double x_coordinate;

	private Double y_coordinate;

	public SaleReportInfoSummaryByAirBottleType() {

		this.num_for_2KG = 0;
		this.num_for_5KG = 0;
		this.num_for_15KG = 0;
		this.num_for_50KG = 0;
		this.num_for_total = 0;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getSecond_category_name() {
		return second_category_name;
	}

	public void setSecond_category_name(String second_category_name) {
		this.second_category_name = second_category_name;
	}

	public Integer getNum_for_5KG() {
		return num_for_5KG;
	}

	public void setNum_for_5KG(Integer num_for_5KG) {
		this.num_for_5KG = num_for_5KG;
	}

	public Integer getNum_for_15KG() {
		return num_for_15KG;
	}

	public void setNum_for_15KG(Integer num_for_15KG) {
		this.num_for_15KG = num_for_15KG;
	}

	public Integer getNum_for_50KG() {
		return num_for_50KG;
	}

	public void setNum_for_50KG(Integer num_for_50KG) {
		this.num_for_50KG = num_for_50KG;
	}

	public Integer getNum_for_2KG() {
		return num_for_2KG;
	}

	public void setNum_for_2KG(Integer num_for_2KG) {
		this.num_for_2KG = num_for_2KG;
	}

	public Integer getNum_for_total() {
		return num_for_total;
	}

	public void setNum_for_total(Integer num_for_total) {
		this.num_for_total = num_for_total;
	}

	public Double getX_coordinate() {
		return x_coordinate;
	}

	public void setX_coordinate(Double x_coordinate) {
		this.x_coordinate = x_coordinate;
	}

	public Double getY_coordinate() {
		return y_coordinate;
	}

	public void setY_coordinate(Double y_coordinate) {
		this.y_coordinate = y_coordinate;
	}

}
