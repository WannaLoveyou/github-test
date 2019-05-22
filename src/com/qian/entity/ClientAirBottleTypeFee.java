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

@Entity
@Table(name = "client_air_bottle_type_fee")
public class ClientAirBottleTypeFee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "client_id")
	private ClientInfo clientInfo;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_type_id")
	private AirBottleType airBottleType;

	@Column()
	private Double delivery_fee;// 运气费

	@Column()
	private Double check_fee;// 检测费

	@Column()
	private Double account_price;// 单价优惠

	private Date create_time;

	@Column(length = 50)
	private String create_people;

	@Column(length = 50)
	private String modify_people;

	@Column()
	private Date modify_time;

	public Double getAccount_price() {
		return account_price;
	}

	public void setAccount_price(Double account_price) {
		this.account_price = account_price;
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

	public AirBottleType getAirBottleType() {
		return airBottleType;
	}

	public void setAirBottleType(AirBottleType airBottleType) {
		this.airBottleType = airBottleType;
	}

	public Double getDelivery_fee() {
		return delivery_fee;
	}

	public void setDelivery_fee(Double delivery_fee) {
		this.delivery_fee = delivery_fee;
	}

	public Double getCheck_fee() {
		return check_fee;
	}

	public void setCheck_fee(Double check_fee) {
		this.check_fee = check_fee;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getCreate_people() {
		return create_people;
	}

	public void setCreate_people(String create_people) {
		this.create_people = create_people;
	}

	public String getModify_people() {
		return modify_people;
	}

	public void setModify_people(String modify_people) {
		this.modify_people = modify_people;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

}
