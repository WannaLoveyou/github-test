package com.qian.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Lu Kongwen
 * @version Create time：2015-12-18 上午10:45:48
 * @Description 气瓶追踪记录表
 */

@Entity
@Table(name = "air_bottle_tracking_record")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class AirBottleTrackingRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_id")
	private AirBottleInfo airBottleInfo;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "second_category_id")
	private SecondCategory secondCategory;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "delivery_man_id")
	private User deliveryMan;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "operator_id")
	private User operator;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "warehouse_id")
	private WarehouseInfo warehouseInfo;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "client_id")
	private ClientInfo clientInfo;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "state_id")
	private AirBottleTrackingRecordState state;

	@Column(nullable = false)
	private Date create_time;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	@JsonIgnore
	private AirBottleTrackingRecord parent;

	@Column(nullable = false, columnDefinition = "INT default 0")
	private int is_final; // 是否终止轨迹

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

	public SecondCategory getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(SecondCategory secondCategory) {
		this.secondCategory = secondCategory;
	}

	public User getDeliveryMan() {
		return deliveryMan;
	}

	public void setDeliveryMan(User deliveryMan) {
		this.deliveryMan = deliveryMan;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public WarehouseInfo getWarehouseInfo() {
		return warehouseInfo;
	}

	public void setWarehouseInfo(WarehouseInfo warehouseInfo) {
		this.warehouseInfo = warehouseInfo;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public AirBottleTrackingRecordState getState() {
		return state;
	}

	public void setState(AirBottleTrackingRecordState state) {
		this.state = state;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public AirBottleTrackingRecord getParent() {
		return parent;
	}

	public void setParent(AirBottleTrackingRecord parent) {
		this.parent = parent;
	}

	/**
	 * @return the is_final
	 */
	public int getIs_final() {
		return is_final;
	}

	/**
	 * @param is_final
	 *            the is_final to set
	 */
	public void setIs_final(int is_final) {
		this.is_final = is_final;
	}


}
