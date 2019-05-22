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
 * @version Create time：2016-12-30 上午10:28:02
 * @Description
 */
@Entity
@Table(name = "multiple_send_record")
public class MultipleSendRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_id")
	private AirBottleInfo airBottleInfo;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "state_id")
	private AirBottleTrackingRecordState state;

	@Column()
	private Integer count;

	@Column(length = 50)
	private String create_time;

	@Column()
	private Date handle_time;

	public MultipleSendRecord() {

	}

	public MultipleSendRecord(int air_bottle_id, int state_id, int count, String create_time, Date nowTime) {

		AirBottleInfo airBottleInfo = new AirBottleInfo();
		airBottleInfo.setId(air_bottle_id);
		this.airBottleInfo = airBottleInfo;
		
		AirBottleTrackingRecordState state = new AirBottleTrackingRecordState();
		state.setId(state_id);
		this.state = state;

		this.count = count;

		this.create_time = create_time;

		this.handle_time = nowTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AirBottleTrackingRecordState getState() {
		return state;
	}

	public void setState(AirBottleTrackingRecordState state) {
		this.state = state;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public Date getHandle_time() {
		return handle_time;
	}

	public void setHandle_time(Date handle_time) {
		this.handle_time = handle_time;
	}

	public AirBottleInfo getAirBottleInfo() {
		return airBottleInfo;
	}

	public void setAirBottleInfo(AirBottleInfo airBottleInfo) {
		this.airBottleInfo = airBottleInfo;
	}

}
