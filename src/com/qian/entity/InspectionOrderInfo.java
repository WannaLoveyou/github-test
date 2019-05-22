package com.qian.entity;

import java.util.Date;

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
 * @version Create time：2017-11-21 下午3:23:51
 * @Description
 */
@Entity
@Table(name = "inspection_order_info")
public class InspectionOrderInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "create_time", columnDefinition = "DATETIME default null comment '送检单生成时间'")
	private Date createTime;

	@ManyToOne
	@JoinColumn(name = "operation_user_id")
	private User operationUser;

	@ManyToOne
	@JoinColumn(name = "warehouse_info_id")
	private WarehouseInfo warehouseInfo;

	@ManyToOne
	@JoinColumn(name = "inspection_state_id")
	private InspectionState inspectionState;

	@Column(name = "inspection_order_number", columnDefinition = ("varchar(225)  comment '检测单编号'"))
	private String inspectionOrderNumber;

	@Column(name = "inspection_system_order_number", columnDefinition = ("varchar(225)  comment '检测站系统检测单编号'"))
	private String inspectionSystemOrderNumber;

	@Column(name = "inspection_system_state", columnDefinition = ("varchar(225)  comment '检测站系统状态'"))
	private String inspectionSystemState;

	@Column(name = "inspection_system_refresh_time", columnDefinition = "DATETIME default null comment '检测站系统最后刷新时间'")
	private Date inspectionSystemRefreshTime;
	
	@Column(name = "inspection_order_init_data_time", columnDefinition = "DATETIME default null comment '检测单数据同步时间'")
	private Date inspectionOrderInitDataTime;
	
	public InspectionOrderInfo() {

	}

	public InspectionOrderInfo(Date createTime, User operationUser, WarehouseInfo warehouseInfo, InspectionState inspectionState) {
		this.createTime = createTime;
		this.operationUser = operationUser;
		this.warehouseInfo = warehouseInfo;
		this.inspectionState = inspectionState;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public InspectionState getInspectionState() {
		return inspectionState;
	}

	public void setInspectionState(InspectionState inspectionState) {
		this.inspectionState = inspectionState;
	}

	public String getInspectionOrderNumber() {
		return inspectionOrderNumber;
	}

	public void setInspectionOrderNumber(String inspectionOrderNumber) {
		this.inspectionOrderNumber = inspectionOrderNumber;
	}

	
	public String getInspectionSystemState() {
		return inspectionSystemState;
	}

	public void setInspectionSystemState(String inspectionSystemState) {
		this.inspectionSystemState = inspectionSystemState;
	}

	public Date getInspectionSystemRefreshTime() {
		return inspectionSystemRefreshTime;
	}

	public void setInspectionSystemRefreshTime(Date inspectionSystemRefreshTime) {
		this.inspectionSystemRefreshTime = inspectionSystemRefreshTime;
	}

	public String getInspectionSystemOrderNumber() {
		return inspectionSystemOrderNumber;
	}

	public void setInspectionSystemOrderNumber(String inspectionSystemOrderNumber) {
		this.inspectionSystemOrderNumber = inspectionSystemOrderNumber;
	}

	public Date getInspectionOrderInitDataTime() {
		return inspectionOrderInitDataTime;
	}

	public void setInspectionOrderInitDataTime(Date inspectionOrderInitDataTime) {
		this.inspectionOrderInitDataTime = inspectionOrderInitDataTime;
	}

}
