package com.qian.entity;

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
@Table(name="part_price")
public class PartPrice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "part_type_id")
	private PartType partType;//零件类型
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_type_id")
	private AirBottleType airBottleType;//气瓶类型
	
	@Column(nullable = false)
	private double price;//零件单价

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public PartType getPartType() {
		return partType;
	}

	public void setPartType(PartType partType) {
		this.partType = partType;
	}

	public AirBottleType getAirBottleType() {
		return airBottleType;
	}

	public void setAirBottleType(AirBottleType airBottleType) {
		this.airBottleType = airBottleType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	
}
