package com.qian.mobile.entity;

import com.qian.entity.AirBottleType;
import com.qian.entity.ClientInfo;
import com.qian.entity.User;

/**
 * @author Lu Kongwen
 * @version Create time：2019-5-13 上午10:21:12
 * @Description
 */
public class MobileAirBottleRentInfoEntity {

	private int userId;

	private int clientId;

	private int airBottleTypeId;

	private int rentNum;

	private Double rentDeposit; // 租瓶押金

	private Double rentMoneyForMonth; // 每月租金

	private Double rentMoneyForYear; // 每年租金

	private Long expireTimeStamp; // 过期时间

	private ClientInfo clientInfo;

	private User user;

	private AirBottleType airBottleType;

	public MobileAirBottleRentInfoEntity() {

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getAirBottleTypeId() {
		return airBottleTypeId;
	}

	public void setAirBottleTypeId(int airBottleTypeId) {
		this.airBottleTypeId = airBottleTypeId;
	}

	public int getRentNum() {
		return rentNum;
	}

	public void setRentNum(int rentNum) {
		this.rentNum = rentNum;
	}

	public Double getRentDeposit() {
		return rentDeposit;
	}

	public void setRentDeposit(Double rentDeposit) {
		this.rentDeposit = rentDeposit;
	}

	public Double getRentMoneyForMonth() {
		return rentMoneyForMonth;
	}

	public void setRentMoneyForMonth(Double rentMoneyForMonth) {
		this.rentMoneyForMonth = rentMoneyForMonth;
	}

	public Double getRentMoneyForYear() {
		return rentMoneyForYear;
	}

	public void setRentMoneyForYear(Double rentMoneyForYear) {
		this.rentMoneyForYear = rentMoneyForYear;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getExpireTimeStamp() {
		return expireTimeStamp;
	}

	public void setExpireTimeStamp(Long expireTimeStamp) {
		this.expireTimeStamp = expireTimeStamp;
	}

	public AirBottleType getAirBottleType() {
		return airBottleType;
	}

	public void setAirBottleType(AirBottleType airBottleType) {
		this.airBottleType = airBottleType;
	}

}
