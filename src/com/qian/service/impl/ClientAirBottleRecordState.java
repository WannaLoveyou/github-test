package com.qian.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-20 上午11:12:56
 * @Description 用户气瓶记录状态表
 */
@Entity
@Table(name = "client_air_bottle_record_state")
public class ClientAirBottleRecordState {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String state_description;

	@OneToMany(mappedBy = "state")
	@JsonIgnore
	private List<ClientAirBottleRecord> clientAirBottleReocrd = new ArrayList<ClientAirBottleRecord>();

	public ClientAirBottleRecordState() {

	}

	public ClientAirBottleRecordState(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState_description() {
		return state_description;
	}

	public void setState_description(String state_description) {
		this.state_description = state_description;
	}

	public List<ClientAirBottleRecord> getClientAirBottleReocrd() {
		return clientAirBottleReocrd;
	}

	public void setClientAirBottleReocrd(List<ClientAirBottleRecord> clientAirBottleReocrd) {
		this.clientAirBottleReocrd = clientAirBottleReocrd;
	}

}
