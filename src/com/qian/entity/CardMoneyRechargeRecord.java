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
 * @version Create time：2016-8-4 上午11:01:44
 * @Description
 */
@Entity
@Table(name = "card_money_recharge_record")
public class CardMoneyRechargeRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "client_id")
	private ClientInfo clientInfo;

	@Column()
	private Double recharge_money;

	@Column()
	private Double gift_money;

	@Column()
	private Date recharge_time;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "pay_type_id")
	private PayType payType;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "operator_id")
	private User operator;

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

	public Double getRecharge_money() {
		return recharge_money;
	}

	public void setRecharge_money(Double recharge_money) {
		this.recharge_money = recharge_money;
	}

	public Double getGift_money() {
		return gift_money;
	}

	public void setGift_money(Double gift_money) {
		this.gift_money = gift_money;
	}

	public Date getRecharge_time() {
		return recharge_time;
	}

	public void setRecharge_time(Date recharge_time) {
		this.recharge_time = recharge_time;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

}
