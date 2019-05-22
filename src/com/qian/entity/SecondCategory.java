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

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-5 下午4:32:59
 * @Description 第二区域表
 */

@Entity
@Table(name = "second_category")
public class SecondCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String second_category_name;

	@Column()
	private Double x_coordinate;

	@Column()
	private Double y_coordinate;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "first_category_id")
	private FirstCategory firstCategory;
	
	@Column(name = "contact_name", columnDefinition = ("varchar(20) comment '联系名称/负责人'"))
	private String contactName;
	
	@Column(name = "contact_number", columnDefinition = ("varchar(20) comment '联系电话'"))
	private String contactNumber;
	
	@Column(name = "address", columnDefinition = ("varchar(50) comment '地址'"))
	private String address;

	public SecondCategory(){
		
	}
	
	public SecondCategory(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSecond_category_name() {
		return second_category_name;
	}

	public void setSecond_category_name(String second_category_name) {
		this.second_category_name = second_category_name;
	}

	public Double getX_coordinate() {
		return x_coordinate;
	}

	public void setX_coordinate(Double x_coordinate) {
		this.x_coordinate = x_coordinate;
	}

	public Double getY_coordinate() {
		return y_coordinate;
	}

	public void setY_coordinate(Double y_coordinate) {
		this.y_coordinate = y_coordinate;
	}

	public FirstCategory getFirstCategory() {
		return firstCategory;
	}

	public void setFirstCategory(FirstCategory firstCategory) {
		this.firstCategory = firstCategory;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}