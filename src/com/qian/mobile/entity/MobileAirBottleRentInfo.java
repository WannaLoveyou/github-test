package com.qian.mobile.entity;

import java.util.Date;

import com.qian.entity.AirBottleRentInfo;

/**
 * @author Lu Kongwen
 * @version Create time：2019-5-14 下午4:03:24
 * @Description
 */
public class MobileAirBottleRentInfo {

	private int airBottleRentInfoId;// 订单ID

	private int clientId; // 客户ID

	private String clientCode; // 客户编号

	private String clientName;// 客户名称

	private String clientTelNumber; // 客户手机号

	private String clientAddress; // 客户地址

	private Integer rentSecondCategoryId; // 租瓶门店ID

	private String rentSecondCategoryName; // 租瓶门店名

	private Date rentTime; // 租瓶时间

	private Double rentDeposit; // 租瓶押金

	private Double rentMoneyForMonth; // 每月租金

	private Double rentMoneyForYear;// 每年租金

	private int airBottleTypeId; // 气瓶类型ID

	private String airBottleTypeName; // 气瓶类型名

	private int rentNum; // 租瓶数量

	private Date expireTime; // 到期时间

	private Date backTime; // 归还时间

	private Double totalRentMoney;// 总租金

	private Double backDeposit;// 退还押金

	private Integer rentOperatorId; // 租瓶操作人ID

	private String rentOperatorName; // 租瓶操作人名

	private Integer backOperatorId; // 退瓶操作人ID

	private String backOperatorName; // 退瓶操作人名

	private int airBottleRentInfoStateId;// 租瓶状态ID

	private String airBottleRentInfoStateName; // 租瓶状态名

	private Integer backSecondCategoryId; // 退瓶门店ID

	private String backSecondCategoryName; // 退瓶门店名

	public MobileAirBottleRentInfo(AirBottleRentInfo airBottleRentInfo) {
		this.airBottleRentInfoId = airBottleRentInfo.getId();
		this.clientId = airBottleRentInfo.getClientInfo().getId();
		this.clientCode = airBottleRentInfo.getClientInfo().getClient_code();
		this.clientName = airBottleRentInfo.getClientInfo().getClient_name();
		this.clientTelNumber = airBottleRentInfo.getClientInfo().getMobile_tel_number_1();
		this.clientAddress = airBottleRentInfo.getClientInfo().getClient_address();

		if (airBottleRentInfo.getSecondCategory() != null) {
			this.rentSecondCategoryId = airBottleRentInfo.getSecondCategory().getId();
			this.rentSecondCategoryName = airBottleRentInfo.getSecondCategory().getSecond_category_name();
		}

		this.rentTime = airBottleRentInfo.getRent_time();
		this.rentDeposit = airBottleRentInfo.getRent_deposit();
		this.rentMoneyForMonth = airBottleRentInfo.getRent_money_for_month();
		this.rentMoneyForYear = airBottleRentInfo.getRent_money_for_year();

		this.airBottleTypeId = airBottleRentInfo.getAirBottleType().getId();
		this.airBottleTypeName = airBottleRentInfo.getAirBottleType().getAir_bottle_specifications();
		this.rentNum = airBottleRentInfo.getRent_num();

		this.expireTime = airBottleRentInfo.getExpire_time();
		this.backTime = airBottleRentInfo.getBack_time();

		this.totalRentMoney = airBottleRentInfo.getTotal_rent_money();
		this.backDeposit = airBottleRentInfo.getBack_deposit();

		this.rentOperatorId = airBottleRentInfo.getRent_operator().getId();
		this.rentOperatorName = airBottleRentInfo.getRent_operator().getFull_name();

		if (airBottleRentInfo.getBack_operator() != null) {
			this.backOperatorId = airBottleRentInfo.getBack_operator().getId();
			this.backOperatorName = airBottleRentInfo.getBack_operator().getFull_name();
		}

		this.airBottleRentInfoStateId = airBottleRentInfo.getAirBottleRentInfoState().getId();
		this.airBottleRentInfoStateName = airBottleRentInfo.getAirBottleRentInfoState().getState_description();

		if (airBottleRentInfo.getBackSecondCategory() != null) {
			this.backSecondCategoryId = airBottleRentInfo.getBackSecondCategory().getId();
			this.backSecondCategoryName = airBottleRentInfo.getBackSecondCategory().getSecond_category_name();
		}
	}

	public int getAirBottleRentInfoId() {
		return airBottleRentInfoId;
	}

	public void setAirBottleRentInfoId(int airBottleRentInfoId) {
		this.airBottleRentInfoId = airBottleRentInfoId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientTelNumber() {
		return clientTelNumber;
	}

	public void setClientTelNumber(String clientTelNumber) {
		this.clientTelNumber = clientTelNumber;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public Integer getRentSecondCategoryId() {
		return rentSecondCategoryId;
	}

	public void setRentSecondCategoryId(Integer rentSecondCategoryId) {
		this.rentSecondCategoryId = rentSecondCategoryId;
	}

	public String getRentSecondCategoryName() {
		return rentSecondCategoryName;
	}

	public void setRentSecondCategoryName(String rentSecondCategoryName) {
		this.rentSecondCategoryName = rentSecondCategoryName;
	}

	public Date getRentTime() {
		return rentTime;
	}

	public void setRentTime(Date rentTime) {
		this.rentTime = rentTime;
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

	public int getAirBottleTypeId() {
		return airBottleTypeId;
	}

	public void setAirBottleTypeId(int airBottleTypeId) {
		this.airBottleTypeId = airBottleTypeId;
	}

	public String getAirBottleTypeName() {
		return airBottleTypeName;
	}

	public void setAirBottleTypeName(String airBottleTypeName) {
		this.airBottleTypeName = airBottleTypeName;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getBackTime() {
		return backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

	public Double getTotalRentMoney() {
		return totalRentMoney;
	}

	public void setTotalRentMoney(Double totalRentMoney) {
		this.totalRentMoney = totalRentMoney;
	}

	public Double getBackDeposit() {
		return backDeposit;
	}

	public void setBackDeposit(Double backDeposit) {
		this.backDeposit = backDeposit;
	}

	public Integer getRentOperatorId() {
		return rentOperatorId;
	}

	public void setRentOperatorId(Integer rentOperatorId) {
		this.rentOperatorId = rentOperatorId;
	}

	public String getRentOperatorName() {
		return rentOperatorName;
	}

	public void setRentOperatorName(String rentOperatorName) {
		this.rentOperatorName = rentOperatorName;
	}

	public Integer getBackOperatorId() {
		return backOperatorId;
	}

	public void setBackOperatorId(Integer backOperatorId) {
		this.backOperatorId = backOperatorId;
	}

	public String getBackOperatorName() {
		return backOperatorName;
	}

	public void setBackOperatorName(String backOperatorName) {
		this.backOperatorName = backOperatorName;
	}

	public int getAirBottleRentInfoStateId() {
		return airBottleRentInfoStateId;
	}

	public void setAirBottleRentInfoStateId(int airBottleRentInfoStateId) {
		this.airBottleRentInfoStateId = airBottleRentInfoStateId;
	}

	public String getAirBottleRentInfoStateName() {
		return airBottleRentInfoStateName;
	}

	public void setAirBottleRentInfoStateName(String airBottleRentInfoStateName) {
		this.airBottleRentInfoStateName = airBottleRentInfoStateName;
	}

	public Integer getBackSecondCategoryId() {
		return backSecondCategoryId;
	}

	public void setBackSecondCategoryId(Integer backSecondCategoryId) {
		this.backSecondCategoryId = backSecondCategoryId;
	}

	public String getBackSecondCategoryName() {
		return backSecondCategoryName;
	}

	public void setBackSecondCategoryName(String backSecondCategoryName) {
		this.backSecondCategoryName = backSecondCategoryName;
	}

	public int getRentNum() {
		return rentNum;
	}

	public void setRentNum(int rentNum) {
		this.rentNum = rentNum;
	}

}
