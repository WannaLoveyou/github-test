package com.qian.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2018-10-23 下午8:18:43
 * @Description
 */
@Entity
@Table(name = "fill_check_record")
public class FillCheckRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_id")
	private AirBottleInfo airBottleInfo;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "operator_id")
	private User operator;

	@Column()
	private Date create_time;

	// 0 before, 1 after 参考com.tgb.code.BeforeOrAfterFillCode
	@Column()
	private int before_or_after_fill;

	public FillCheckRecord() {

	}

	public FillCheckRecord(AirBottleInfo airBottleInfo, User operator, Date nowTime, Integer before_or_after_fill) {
		this.airBottleInfo = airBottleInfo;
		this.operator = operator;
		this.create_time = nowTime;
		this.before_or_after_fill = before_or_after_fill;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AirBottleInfo getAirBottleInfo() {
		return airBottleInfo;
	}

	public void setAirBottleInfo(AirBottleInfo airBottleInfo) {
		this.airBottleInfo = airBottleInfo;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public int getBefore_or_after_fill() {
		return before_or_after_fill;
	}

	public void setBefore_or_after_fill(int before_or_after_fill) {
		this.before_or_after_fill = before_or_after_fill;
	}

}
