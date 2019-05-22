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

import com.qian.code.AirBottleRentInfoStateCode;
import com.qian.mobile.entity.MobileAirBottleRentInfoEntity;

/**
 * @author Lu Kongwen
 * @version Create time：2018-3-27 下午4:15:49
 * @Description
 */
@Entity
@Table(name = "air_bottle_rent_imformation")
public class AirBottleRentInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "client_id")
	private ClientInfo clientInfo;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_type_id")
	private AirBottleType airBottleType; // 气瓶类型

	@Column()
	private Integer rent_num; // 租瓶数量

	@Column()
	private Date rent_time; // 租瓶时间

	@Column()
	private Double rent_deposit; // 租瓶押金

	@Column()
	private Double rent_money_for_month; // 每月租金

	@Column()
	private Double rent_money_for_year; // 每年租金

	@Column()
	private Date expire_time; // 到期时间

	@Column()
	private Date back_time; // 退还时间

	@Column()
	private Double total_rent_money; // 总租金

	@Column()
	private Double back_deposit; // 退还押金

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "rent_operator_id")
	private User rent_operator; // 租瓶操作人

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "back_operator_id")
	private User back_operator; // 退瓶操作人

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_rent_state_id")
	private AirBottleRentInfoState airBottleRentInfoState; // 租瓶状态

	// 租瓶门店
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "second_category_id")
	private SecondCategory secondCategory;

	// 退瓶门店
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "back_second_category_id")
	private SecondCategory backSecondCategory;

	public AirBottleRentInfo(){
		
	}
	
	public AirBottleRentInfo(MobileAirBottleRentInfoEntity entity) {

		this.clientInfo = entity.getClientInfo();
		this.airBottleType = entity.getAirBottleType();
		this.rent_num = entity.getRentNum();
		this.rent_operator = entity.getUser();
		this.secondCategory = entity.getUser().getSecondCategory();
		this.rent_time = new Date();
		this.airBottleRentInfoState = new AirBottleRentInfoState(AirBottleRentInfoStateCode.NO_BACK);
		this.rent_deposit = entity.getRentDeposit();
		this.rent_money_for_month = entity.getRentMoneyForMonth();
		this.rent_money_for_year = entity.getRentMoneyForYear();
		if(entity.getExpireTimeStamp() != null){
			this.expire_time = new Date(entity.getExpireTimeStamp());
		}
		
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

	public Integer getRent_num() {
		return rent_num;
	}

	public void setRent_num(Integer rent_num) {
		this.rent_num = rent_num;
	}

	public Date getRent_time() {
		return rent_time;
	}

	public void setRent_time(Date rent_time) {
		this.rent_time = rent_time;
	}

	public Double getRent_deposit() {
		return rent_deposit;
	}

	public void setRent_deposit(Double rent_deposit) {
		this.rent_deposit = rent_deposit;
	}

	public Double getRent_money_for_month() {
		return rent_money_for_month;
	}

	public void setRent_money_for_month(Double rent_money_for_month) {
		this.rent_money_for_month = rent_money_for_month;
	}

	public Double getRent_money_for_year() {
		return rent_money_for_year;
	}

	public void setRent_money_for_year(Double rent_money_for_year) {
		this.rent_money_for_year = rent_money_for_year;
	}

	public Date getExpire_time() {
		return expire_time;
	}

	public void setExpire_time(Date expire_time) {
		this.expire_time = expire_time;
	}

	public Date getBack_time() {
		return back_time;
	}

	public void setBack_time(Date back_time) {
		this.back_time = back_time;
	}

	public Double getTotal_rent_money() {
		return total_rent_money;
	}

	public void setTotal_rent_money(Double total_rent_money) {
		this.total_rent_money = total_rent_money;
	}

	public Double getBack_deposit() {
		return back_deposit;
	}

	public void setBack_deposit(Double back_deposit) {
		this.back_deposit = back_deposit;
	}

	public User getRent_operator() {
		return rent_operator;
	}

	public void setRent_operator(User rent_operator) {
		this.rent_operator = rent_operator;
	}

	public User getBack_operator() {
		return back_operator;
	}

	public void setBack_operator(User back_operator) {
		this.back_operator = back_operator;
	}

	public AirBottleRentInfoState getAirBottleRentInfoState() {
		return airBottleRentInfoState;
	}

	public void setAirBottleRentInfoState(AirBottleRentInfoState airBottleRentInfoState) {
		this.airBottleRentInfoState = airBottleRentInfoState;
	}

	public SecondCategory getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(SecondCategory secondCategory) {
		this.secondCategory = secondCategory;
	}

	public SecondCategory getBackSecondCategory() {
		return backSecondCategory;
	}

	public void setBackSecondCategory(SecondCategory backSecondCategory) {
		this.backSecondCategory = backSecondCategory;
	}

}
