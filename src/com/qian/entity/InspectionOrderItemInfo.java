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

import org.hibernate.annotations.Index;

/**
 * @author Lu Kongwen
 * @version Create time：2017-11-21 下午3:23:51
 * @Description
 */
@Entity
@Table(name = "inspection_order_item_info")
public class InspectionOrderItemInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "air_bottle_info_id")
	private AirBottleInfo airBottleInfo;

	@Column(name = "gas_number_id", columnDefinition = ("varchar(50)  comment '气瓶唯一标识'"))
	@Index(name = "gasNumberIdIndex")
	private String gasNumberId;

	@Column(name = "qr_code", columnDefinition = ("varchar(225)  comment '二维码'"))
	private String qrCode;

	@ManyToOne
	@JoinColumn(name = "inspection_order_info_id")
	private InspectionOrderInfo inspectionOrderInfo;

	@Column(name = "check_date", columnDefinition = ("DATETIME default null  comment '检测时间'"))
	private Date checkDate;

	@Column(name = "next_check_date", columnDefinition = ("DATETIME default null  comment '下次检测时间'"))
	private Date nextCheckDate;

	@Column(name = "last_use_date", columnDefinition = ("DATETIME default null  comment '截止使用日期'"))
	private Date lastUseDate;

	@Column(name = "end_use_date", columnDefinition = ("DATETIME default null  comment '终止使用日期'"))
	private Date endUseDate;

	@Column(name = "new_gas_number", columnDefinition = ("varchar(20)  comment '新钢码'"))
	private String newGasNumber;

	@Column(name = "isscrap", columnDefinition = ("varchar(20)  comment '是否报废'"))
	private String isscrap;

	@Column(name = "scrap_text", columnDefinition = ("varchar(50)  comment '报废原因'"))
	private String scrapText;

	@Column(name = "result", columnDefinition = ("varchar(50)  comment '检测结果'"))
	private String result;

	public InspectionOrderItemInfo() {

	}

	public InspectionOrderItemInfo(PreparedInspectionInfo preparedInspectionInfo, InspectionOrderInfo inspectionOrderInfo) {
		this.gasNumberId = String.valueOf(preparedInspectionInfo.getAirBottleInfo().getId());
		this.airBottleInfo = preparedInspectionInfo.getAirBottleInfo();
		this.inspectionOrderInfo = inspectionOrderInfo;
		this.qrCode = preparedInspectionInfo.getQrCode();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public InspectionOrderInfo getInspectionOrderInfo() {
		return inspectionOrderInfo;
	}

	public void setInspectionOrderInfo(InspectionOrderInfo inspectionOrderInfo) {
		this.inspectionOrderInfo = inspectionOrderInfo;
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

	public String getIsscrap() {
		return isscrap;
	}

	public void setIsscrap(String isscrap) {
		this.isscrap = isscrap;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getGasNumberId() {
		return gasNumberId;
	}

	public void setGasNumberId(String gasNumberId) {
		this.gasNumberId = gasNumberId;
	}

	public String getNewGasNumber() {
		return newGasNumber;
	}

	public void setNewGasNumber(String newGasNumber) {
		this.newGasNumber = newGasNumber;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Date getNextCheckDate() {
		return nextCheckDate;
	}

	public void setNextCheckDate(Date nextCheckDate) {
		this.nextCheckDate = nextCheckDate;
	}

	public String getScrapText() {
		return scrapText;
	}

	public void setScrapText(String scrapText) {
		this.scrapText = scrapText;
	}

	public Date getLastUseDate() {
		return lastUseDate;
	}

	public void setLastUseDate(Date lastUseDate) {
		this.lastUseDate = lastUseDate;
	}

	public Date getEndUseDate() {
		return endUseDate;
	}

	public void setEndUseDate(Date endUseDate) {
		this.endUseDate = endUseDate;
	}

}
