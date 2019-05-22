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

import com.qian.code.LocationStateCode;

/**
 * @author Lu Kongwen
 * @version Create time：2017-2-23 下午3:13:56
 * @Description
 */
@Entity
@Table(name = "client_inventory_imformation")
public class ClientInventoryInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "client_id")
	private ClientInfo clientInfo;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_id", unique = true)
	private AirBottleInfo airBottleInfo;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_state_id")
	private AirBottleState airBottleState;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_inventory_state_id")
	private AirBottleInventoryState airBottleInventoryState;

	@Column(nullable = false)
	private Date create_time;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "operator_id")
	private User operator;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "location_state_id")
	private LocationState locationState;

	@Column()
	private Double longitude;

	@Column()
	private Double latitude;

	public ClientInventoryInfo() {

	}

	public ClientInventoryInfo(ClientInfo clientInfo, AirBottleInfo airBottleInfo, AirBottleState airBottleState,
			AirBottleInventoryState airBottleInventoryState, User operator, Date nowTime) {

		this.clientInfo = clientInfo;
		this.airBottleInfo = airBottleInfo;
		this.airBottleState = airBottleState;
		this.airBottleInventoryState = airBottleInventoryState;
		this.operator = operator;
		this.create_time = nowTime;

		if (clientInfo.getLongitude() != null && clientInfo.getLatitude() != null) {
			this.longitude = clientInfo.getLongitude();
			this.latitude = clientInfo.getLatitude();
			this.locationState = new LocationState(LocationStateCode.SIMULATION);
		}

	}

	public ClientInventoryInfo(ClientInfo clientInfo, AirBottleInfo airBottleInfo, AirBottleState airBottleState,
			AirBottleInventoryState airBottleInventoryState, User operator, Date nowTime, Double longitude, Double latitude) {

		this.clientInfo = clientInfo;
		this.airBottleInfo = airBottleInfo;
		this.airBottleState = airBottleState;
		this.airBottleInventoryState = airBottleInventoryState;
		this.operator = operator;
		this.create_time = nowTime;

		if (longitude != null && latitude != null) {
			this.longitude = longitude;
			this.latitude = latitude;
			this.locationState = new LocationState(LocationStateCode.REAL_TIME);
		}

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

	public AirBottleState getAirBottleState() {
		return airBottleState;
	}

	public void setAirBottleState(AirBottleState airBottleState) {
		this.airBottleState = airBottleState;
	}

	public AirBottleInventoryState getAirBottleInventoryState() {
		return airBottleInventoryState;
	}

	public void setAirBottleInventoryState(AirBottleInventoryState airBottleInventoryState) {
		this.airBottleInventoryState = airBottleInventoryState;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public LocationState getLocationState() {
		return locationState;
	}

	public void setLocationState(LocationState locationState) {
		this.locationState = locationState;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

}
