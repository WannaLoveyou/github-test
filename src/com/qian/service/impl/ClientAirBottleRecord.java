package com.qian.service.impl;

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

import com.qian.code.ClientAirBottleReocrdStateCode;
import com.qian.entity.AirBottleInfo;
import com.qian.entity.ClientInfo;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-17 下午3:42:27
 * @Description 用户气瓶记录表
 */
@Entity
@Table(name = "client_air_bottle_record")
public class ClientAirBottleRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "client_id")
	private ClientInfo clientInfo;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_id")
	private AirBottleInfo airBottleInfo;

	@Column(nullable = false)
	private Date begin_time;

	@Column()
	private Date end_time;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "state_id")
	private ClientAirBottleRecordState state;

	public ClientAirBottleRecord() {

	}

	public ClientAirBottleRecord(ClientInfo clientInfo, AirBottleInfo airBottleInfo, Date nowTime) {

		this.clientInfo = clientInfo;
		this.airBottleInfo = airBottleInfo;
		this.begin_time = nowTime;
		this.state = new ClientAirBottleRecordState(ClientAirBottleReocrdStateCode.NO_RETURN);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public AirBottleInfo getAirBottleInfo() {
		return airBottleInfo;
	}

	public void setAirBottleInfo(AirBottleInfo airBottleInfo) {
		this.airBottleInfo = airBottleInfo;
	}

	public Date getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public ClientAirBottleRecordState getState() {
		return state;
	}

	public void setState(ClientAirBottleRecordState state) {
		this.state = state;
	}

}
