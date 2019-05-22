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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Lu Kongwen
 * @version Create time：2017-2-23 下午2:30:26
 * @Description
 */
@Entity
@Table(name = "delivery_man_inventory_imformation")
public class DeliveryManInventoryInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "delivery_man_id")
	@JsonIgnore
	private User delivery_man;

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

	public DeliveryManInventoryInfo() {

	}

	public DeliveryManInventoryInfo(User deliveryMan, AirBottleInfo airBottleInfo, AirBottleState airBottleState,
			AirBottleInventoryState airBottleInventoryState, User operator, Date nowTime) {

		this.delivery_man = deliveryMan;
		this.airBottleInfo = airBottleInfo;
		this.airBottleState = airBottleState;
		this.airBottleInventoryState = airBottleInventoryState;
		this.operator = operator;
		this.create_time = nowTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getDelivery_man() {
		return delivery_man;
	}

	public void setDelivery_man(User delivery_man) {
		this.delivery_man = delivery_man;
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

}
