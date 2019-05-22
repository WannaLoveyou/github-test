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
@Table(name = "sotre_custom_air_bottle_price_imformation")
public class StoreCustomAirBottlePriceInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "second_category_id")
	private SecondCategory secondCategory;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_type_id")
	private AirBottleType airBottleType;

	@Column()
	private Double custom_price;// 自定义价格

	@Column()
	private Double custom_delivery_fee;// 自定义送气费

	private Date create_time;

	@Column(length = 50)
	private String create_people;

	@Column(length = 50)
	private String modify_people;

	@Column()
	private Date modify_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SecondCategory getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(SecondCategory secondCategory) {
		this.secondCategory = secondCategory;
	}

	public AirBottleType getAirBottleType() {
		return airBottleType;
	}

	public void setAirBottleType(AirBottleType airBottleType) {
		this.airBottleType = airBottleType;
	}

	public Double getCustom_price() {
		return custom_price;
	}

	public void setCustom_price(Double custom_price) {
		this.custom_price = custom_price;
	}

	public Double getCustom_delivery_fee() {
		return custom_delivery_fee;
	}

	public void setCustom_delivery_fee(Double custom_delivery_fee) {
		this.custom_delivery_fee = custom_delivery_fee;
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
