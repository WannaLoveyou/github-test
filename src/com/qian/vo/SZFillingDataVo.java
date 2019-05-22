package com.qian.vo;

import java.util.Date;

import com.qian.entity.AirBottleTrackingRecord;
import com.qian.util.SZPublicServiceUtil;
import com.qian.util.TimeUtils;

public class SZFillingDataVo {

	/**
	 * 气瓶钢码
	 */
	private String enterpriseSteelNo;
	/**
	 * 气瓶标签
	 */
	private String bottleLabelNo;
	/**
	 * 气瓶所属企业id
	 */
	private String bottoleEnterprise;
	/**
	 * 气瓶规格 id
	 */
	private String standard;
	/**
	 * 充装前重量
	 */
	private String fillingBeforeWeight;
	/**
	 * 充装后重量
	 */
	private String fillingAfterWeight;
	/**
	 * 充装站点id
	 */
	private String fillingStationId;
	/**
	 * 称号id
	 */
	private String scaleID;
	/**
	 * 充装时间
	 */
	private String fillingDate;
	/**
	 * 净重
	 */
	private String suttle;
	/**
	 * 充装工卡编号
	 */
	private String fillingCardNo;

	// 对象属性初始化值为接口要求的空值字符串
	{
		this.enterpriseSteelNo = SZPublicServiceUtil.NULL_VALUE_STR;
		this.bottleLabelNo = SZPublicServiceUtil.NULL_VALUE_STR;
		this.bottoleEnterprise = SZPublicServiceUtil.BOTTOLE_ENTERPRISE;
		this.standard = SZPublicServiceUtil.NULL_VALUE_STR;
		this.fillingBeforeWeight = SZPublicServiceUtil.NULL_VALUE_STR;
		this.fillingAfterWeight = SZPublicServiceUtil.NULL_VALUE_STR;
		this.fillingStationId = SZPublicServiceUtil.NULL_VALUE_STR;
		this.scaleID = SZPublicServiceUtil.NULL_VALUE_STR;
		this.fillingDate = SZPublicServiceUtil.NULL_VALUE_STR;
		this.suttle = SZPublicServiceUtil.NULL_VALUE_STR;
		this.fillingCardNo = SZPublicServiceUtil.NULL_VALUE_STR;
	}

	public SZFillingDataVo() {

	}
	
	public SZFillingDataVo(AirBottleTrackingRecord r) {
		if (r.getAirBottleInfo().getAir_bottle_seal_code() != null) {
			this.enterpriseSteelNo = r.getAirBottleInfo().getAir_bottle_seal_code();
		}
		if (r.getAirBottleInfo().getAir_bottle_code() != null) {
			this.bottleLabelNo = r.getAirBottleInfo().getAir_bottle_code();
		}
		if (r.getAirBottleInfo().getAirBottleType().getPublicServiceMaterialType() != null) {
			this.standard = r.getAirBottleInfo().getAirBottleType().getPublicServiceMaterialType().getId().toString();
		}
		if (r.getWarehouseInfo() != null) {
			this.fillingStationId = r.getWarehouseInfo().getFilling_station_id();
		}
		if (r.getCreate_time() != null) {
			this.fillingDate = TimeUtils.getyyyyMMddHHmmssStringByDate(r.getCreate_time());
		}
	}

	/**
	 * 查询气瓶轨迹表的结果
	 * 
	 * @param enterpriseSteelNo
	 * @param bottleLabelNo
	 * @param standard
	 * @param fillingStationId
	 * @param fillingDate
	 */
	public SZFillingDataVo(String enterpriseSteelNo, String bottleLabelNo, Integer standard, String fillingStationId,
			Date fillingDate) {
		if (enterpriseSteelNo != null) {
			this.enterpriseSteelNo = enterpriseSteelNo;
		}
		if (bottleLabelNo != null) {
			this.bottleLabelNo = bottleLabelNo;
		}
		if (standard != null) {
			this.standard = standard.toString();
		}
		if (fillingStationId != null) {
			this.fillingStationId = fillingStationId;
		}
		if (fillingDate != null) {
			this.fillingDate = TimeUtils.getyyyyMMddHHmmssStringByDate(fillingDate);
		}
	}

	public String getEnterpriseSteelNo() {
		return enterpriseSteelNo;
	}

	public void setEnterpriseSteelNo(String enterpriseSteelNo) {
		this.enterpriseSteelNo = enterpriseSteelNo;
	}

	public String getBottleLabelNo() {
		return bottleLabelNo;
	}

	public void setBottleLabelNo(String bottleLabelNo) {
		this.bottleLabelNo = bottleLabelNo;
	}

	public String getBottoleEnterprise() {
		return bottoleEnterprise;
	}

	public void setBottoleEnterprise(String bottoleEnterprise) {
		this.bottoleEnterprise = bottoleEnterprise;
	}

	public String getFillingBeforeWeight() {
		return fillingBeforeWeight;
	}

	public void setFillingBeforeWeight(String fillingBeforeWeight) {
		this.fillingBeforeWeight = fillingBeforeWeight;
	}

	public String getFillingAfterWeight() {
		return fillingAfterWeight;
	}

	public void setFillingAfterWeight(String fillingAfterWeight) {
		this.fillingAfterWeight = fillingAfterWeight;
	}

	public String getScaleID() {
		return scaleID;
	}

	public void setScaleID(String scaleID) {
		this.scaleID = scaleID;
	}

	public String getFillingDate() {
		return fillingDate;
	}

	public void setFillingDate(String fillingDate) {
		this.fillingDate = fillingDate;
	}

	public String getSuttle() {
		return suttle;
	}

	public void setSuttle(String suttle) {
		this.suttle = suttle;
	}

	public String getFillingCardNo() {
		return fillingCardNo;
	}

	public void setFillingCardNo(String fillingCardNo) {
		this.fillingCardNo = fillingCardNo;
	}

	public String getFillingStationId() {
		return fillingStationId;
	}

	public void setFillingStationId(String fillingStationId) {
		this.fillingStationId = fillingStationId;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

}
