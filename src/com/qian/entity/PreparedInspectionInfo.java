package com.qian.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2017-11-21 下午3:23:51
 * @Description
 */
@Entity
@Table(name = "prepared_inspection_info")
public class PreparedInspectionInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne
	@JoinColumn(name = "air_bottle_info_id", columnDefinition = "int unique")
	private AirBottleInfo airBottleInfo;

	@Column(name = "qr_code", columnDefinition = ("varchar(225)  comment '二维码'"))
	private String qrCode;
	
	@Column(name = "create_time", columnDefinition = "DATETIME default null comment '生成时间'")
	private Date createTime;

	@ManyToOne
	@JoinColumn(name = "operation_user_id")
	private User operationUser;

	@ManyToOne
	@JoinColumn(name = "warehouse_info_id")
	private WarehouseInfo warehouseInfo;

	public PreparedInspectionInfo() {

	}

	public PreparedInspectionInfo(AirBottleInfo airBottleInfo, WarehouseInfo warehouseInfo, User operationUser, Date createTime,String qrCode) {
		this.airBottleInfo = airBottleInfo;
		this.warehouseInfo = warehouseInfo;
		this.operationUser = operationUser;
		this.createTime = createTime;
		this.qrCode = qrCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AirBottleInfo getAirBottleInfo() {
		return airBottleInfo;
	}

	public void setAirBottleInfo(AirBottleInfo airBottleInfo) {
		this.airBottleInfo = airBottleInfo;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getOperationUser() {
		return operationUser;
	}

	public void setOperationUser(User operationUser) {
		this.operationUser = operationUser;
	}

	public WarehouseInfo getWarehouseInfo() {
		return warehouseInfo;
	}

	public void setWarehouseInfo(WarehouseInfo warehouseInfo) {
		this.warehouseInfo = warehouseInfo;
	}

}
