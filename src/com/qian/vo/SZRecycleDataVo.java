package com.qian.vo;

import java.util.Date;

import com.qian.entity.AirBottleTrackingRecord;
import com.qian.util.SZPublicServiceUtil;
import com.qian.util.TimeUtils;

/**
 * 回收气瓶记录vo
 * @author sc
 *
 */
public class SZRecycleDataVo {

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
	 * 回收人名称
	 */
	private String recycleName;
	/**
	 * 回收时间
	 */
	private String recycleDate;
	/**
	 * 气瓶回收前持有人名称
	 */
	private String ReclaimedName;

	// 对象属性初始化值为接口要求的空值字符串
	{
		this.enterpriseSteelNo = SZPublicServiceUtil.NULL_VALUE_STR;
		this.bottleLabelNo = SZPublicServiceUtil.NULL_VALUE_STR;
		this.bottoleEnterprise = SZPublicServiceUtil.BOTTOLE_ENTERPRISE;
		this.standard = SZPublicServiceUtil.NULL_VALUE_STR;
		this.recycleName = SZPublicServiceUtil.NULL_VALUE_STR;
		this.recycleDate = SZPublicServiceUtil.NULL_VALUE_STR;
		this.ReclaimedName = SZPublicServiceUtil.NULL_VALUE_STR;
	}

	public SZRecycleDataVo() {

	}
	
	public SZRecycleDataVo(AirBottleTrackingRecord r ) {
		if (r.getAirBottleInfo().getAir_bottle_seal_code() != null) {
			this.enterpriseSteelNo = r.getAirBottleInfo().getAir_bottle_seal_code();
		}
		if (r.getAirBottleInfo().getAir_bottle_code() != null) {
			this.bottleLabelNo = r.getAirBottleInfo().getAir_bottle_code();
		}
		if (r.getAirBottleInfo().getAirBottleType().getPublicServiceMaterialType() != null) {
			this.standard = r.getAirBottleInfo().getAirBottleType().getPublicServiceMaterialType().getId().toString();
		}
		if (r.getOperator() != null) {
			this.recycleName = r.getOperator().getFull_name();
		}
		if (r.getCreate_time() != null) {
			this.recycleDate = TimeUtils.getyyyyMMddHHmmssStringByDate(r.getCreate_time());
		}
		if (r.getClientInfo() != null) {
			this.ReclaimedName = r.getClientInfo().getClient_name();
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
	public SZRecycleDataVo(String enterpriseSteelNo, String bottleLabelNo, Integer standard, String recycleName,
			Date recycleDate,String ReclaimedName) {
		if (enterpriseSteelNo != null) {
			this.enterpriseSteelNo = enterpriseSteelNo;
		}
		if (bottleLabelNo != null) {
			this.bottleLabelNo = bottleLabelNo;
		}
		if (standard != null) {
			this.standard = standard.toString();
		}
		if (recycleName != null) {
			this.recycleName = recycleName;
		}
		if (recycleDate != null) {
			this.recycleDate = TimeUtils.getyyyyMMddHHmmssStringByDate(recycleDate);
		}
		if (ReclaimedName != null) {
			this.ReclaimedName = ReclaimedName;
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

	public String getRecycleName() {
		return recycleName;
	}

	public void setRecycleName(String recycleName) {
		this.recycleName = recycleName;
	}

	public String getRecycleDate() {
		return recycleDate;
	}

	public void setRecycleDate(String recycleDate) {
		this.recycleDate = recycleDate;
	}

	public String getReclaimedName() {
		return ReclaimedName;
	}

	public void setReclaimedName(String reclaimedName) {
		ReclaimedName = reclaimedName;
	}

}
