package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2016-8-4 上午11:33:34
 * @Description
 */
@Entity
@Table(name = "card_money_recharge_money_type")
public class CardMoneyRechargeMoneyType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column()
	private String card_money_recharge_money_type_name;

	@Column()
	private double recharge_money;

	@Column()
	private double gift_money;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCard_money_recharge_money_type_name() {
		return card_money_recharge_money_type_name;
	}

	public void setCard_money_recharge_money_type_name(String card_money_recharge_money_type_name) {
		this.card_money_recharge_money_type_name = card_money_recharge_money_type_name;
	}

	public double getRecharge_money() {
		return recharge_money;
	}

	public void setRecharge_money(double recharge_money) {
		this.recharge_money = recharge_money;
	}

	public double getGift_money() {
		return gift_money;
	}

	public void setGift_money(double gift_money) {
		this.gift_money = gift_money;
	}

}
