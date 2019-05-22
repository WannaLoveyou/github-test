package com.qian.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "operations", "roles", "menus" })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 32)
	private int id;

	@Column(length = 32)
	private String username;

	@Column(length = 32)
	private String password;

	@Column(length = 32)
	private String card_code;

	@Column(length = 32)
	private String user_code;

	@Column(length = 50)
	private String full_name;

	@Column(name = "limit_login", columnDefinition = ("int(11)  default 0 comment '0限制登录，1不限制登录'"))
	private int limit_login;

	@Column(name = "is_wh_delivery_man", columnDefinition = ("int(11)  default 0 comment '0否，1是'"))
	private int is_wh_delivery_man;

	@Column(name = "is_store_delivery_man", columnDefinition = ("int(11)  default 0 comment '0否，1是'"))
	private int is_store_delivery_man;

	@Column(name = "is_wh_auditor", columnDefinition = ("int(11)  default 0 comment '0否，1是'"))
	private int is_wh_auditor;
	
	@Column(name = "is_safety_technology_department", columnDefinition = ("int(11)  default 0 comment '0否，1是'"))
	private int is_safety_technology_department;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "second_category_id")
	private SecondCategory secondCategory;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "warehouse_id")
	private WarehouseInfo warehouseInfo;

	@OneToMany(mappedBy = "user")
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@JsonIgnore
	private List<UserRole> userRoles = new ArrayList<UserRole>();
	
	@Column(name = "contact_number", columnDefinition = ("varchar(20) comment '联系电话'"))
	private String contactNumber;

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCard_code() {
		return card_code;
	}

	public void setCard_code(String card_code) {
		this.card_code = card_code;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public int getLimit_login() {
		return limit_login;
	}

	public void setLimit_login(int limit_login) {
		this.limit_login = limit_login;
	}

	public SecondCategory getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(SecondCategory secondCategory) {
		this.secondCategory = secondCategory;
	}

	public WarehouseInfo getWarehouseInfo() {
		return warehouseInfo;
	}

	public void setWarehouseInfo(WarehouseInfo warehouseInfo) {
		this.warehouseInfo = warehouseInfo;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public int getIs_wh_delivery_man() {
		return is_wh_delivery_man;
	}

	public void setIs_wh_delivery_man(int is_wh_delivery_man) {
		this.is_wh_delivery_man = is_wh_delivery_man;
	}

	public int getIs_store_delivery_man() {
		return is_store_delivery_man;
	}

	public void setIs_store_delivery_man(int is_store_delivery_man) {
		this.is_store_delivery_man = is_store_delivery_man;
	}

	public int getIs_wh_auditor() {
		return is_wh_auditor;
	}

	public void setIs_wh_auditor(int is_wh_auditor) {
		this.is_wh_auditor = is_wh_auditor;
	}

	public int getIs_safety_technology_department() {
		return is_safety_technology_department;
	}

	public void setIs_safety_technology_department(int is_safety_technology_department) {
		this.is_safety_technology_department = is_safety_technology_department;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}