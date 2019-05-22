package com.qian.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-20 下午5:12:34
 * @Description
 */
@Entity
@Table(name = "wechat_first_order_discount_imformation")
public class WechatFirstOrderDiscountInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 225, unique = true)
	private String openid;

	@Column(length = 50)
	private String order_code;

	@Column()
	private Double discount_money;

	@Column()
	private Date create_time;

	public WechatFirstOrderDiscountInfo() {

	}

	public WechatFirstOrderDiscountInfo(String openid, String order_code, Double discount_money, Date create_time) {
		this.openid = openid;
		this.order_code = order_code;
		this.discount_money = discount_money;
		this.create_time = create_time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

	public Double getDiscount_money() {
		return discount_money;
	}

	public void setDiscount_money(Double discount_money) {
		this.discount_money = discount_money;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
