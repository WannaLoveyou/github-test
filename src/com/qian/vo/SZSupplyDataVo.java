package com.qian.vo;

import java.util.Date;

import com.qian.entity.AirBottleTrackingRecord;
import com.qian.util.SZPublicServiceUtil;
import com.qian.util.TimeUtils;

/**
 * 
 * @author sc
 *
 */
public class SZSupplyDataVo {

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
	 * 站点名称
	 */
	private String supplyName;
	/**
	 * 供应站接收时间
	 */
	private String supplyDate;

	// 对象属性初始化值为接口要求的空值字符串
	{
		this.enterpriseSteelNo = SZPublicServiceUtil.NULL_VALUE_STR;
		this.bottleLabelNo = SZPublicServiceUtil.NULL_VALUE_STR;
		this.bottoleEnterprise = SZPublicServiceUtil.BOTTOLE_ENTERPRISE;
		this.standard = SZPublicServiceUtil.NULL_VALUE_STR;
		this.supplyName = SZPublicServiceUtil.NULL_VALUE_STR;
		this.supplyDate = SZPublicServiceUtil.NULL_VALUE_STR;
	}

	public SZSupplyDataVo() {

	}
	
	public SZSupplyDataVo(AirBottleTrackingRecord r) {
		if (r.getAirBottleInfo().getAir_bottle_seal_code() != null) {
			this.enterpriseSteelNo = r.getAirBottleInfo().getAir_bottle_seal_code();
		}
		if (r.getAirBottleInfo().getAir_bottle_code() != null) {
			this.bottleLabelNo = r.getAirBottleInfo().getAir_bottle_code();
		}
		if (r.getAirBottleInfo().getAirBottleType().getPublicServiceMaterialType() != null) {
			this.standard = r.getAirBottleInfo().getAirBottleType().getPublicServiceMaterialType().getId().toString();
		}
		if (r.getSecondCategory() != null) {
			this.supplyName = r.getSecondCategory().getSecond_category_name();
		}
		if (r.getCreate_time() != null) {
			this.supplyDate = TimeUtils.getyyyyMMddHHmmssStringByDate(r.getCreate_time());
		}
	}

	/**
	 * 
	 * @param enterpriseSteelNo
	 * @param bottleLabelNo
	 * @param standard
	 * @param supplyName
	 * @param supplyDate
	 */
	public SZSupplyDataVo(String enterpriseSteelNo, String bottleLabelNo, Integer standard, String supplyName,
			Date supplyDate) {
		if (enterpriseSteelNo != null) {
			this.enterpriseSteelNo = enterpriseSteelNo;
		}
		if (bottleLabelNo != null) {
			this.bottleLabelNo = bottleLabelNo;
		}
		if (standard != null) {
			this.standard = standard.toString();
		}
		if (supplyName != null) {
			this.supplyName = supplyName;
		}
		if (supplyDate != null) {
			this.supplyDate = TimeUtils.getyyyyMMddHHmmssStringByDate(supplyDate);
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

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getSupplyDate() {
		return supplyDate;
	}

	public void setSupplyDate(String supplyDate) {
		this.supplyDate = supplyDate;
	}

}
