package com.qian.vo;

import java.util.Date;

import com.qian.entity.AirBottleTrackingRecord;
import com.qian.util.DGPublicServiceUtil;
import com.qian.util.TimeUtils;

/**
 * 配送记录vo
 * @author sc
 *
 */
public class DGDeliveryDataVo {

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
	 * 送气工姓名
	 */
	private String deliveryName;
	/**
	 * 配送气瓶时间
	 */
	private String deliveryDate;
	/**
	 * 送气工拿气瓶站点名称
	 */
	private String stationName;

	// 对象属性初始化值为接口要求的空值字符串
	{
		this.enterpriseSteelNo = DGPublicServiceUtil.NULL_VALUE_STR;
		this.bottleLabelNo = DGPublicServiceUtil.NULL_VALUE_STR;
		this.bottoleEnterprise = DGPublicServiceUtil.BOTTOLE_ENTERPRISE;
		this.standard = DGPublicServiceUtil.NULL_VALUE_STR;
		this.deliveryName = DGPublicServiceUtil.NULL_VALUE_STR;
		this.deliveryDate = DGPublicServiceUtil.NULL_VALUE_STR;
		this.stationName = DGPublicServiceUtil.NULL_VALUE_STR;
	}

	public DGDeliveryDataVo() {

	}
	
	public DGDeliveryDataVo(AirBottleTrackingRecord r ) {
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
			this.deliveryName = r.getOperator().getFull_name();
		}
		if (r.getCreate_time() != null) {
			this.deliveryDate = TimeUtils.getyyyyMMddHHmmssStringByDate(r.getCreate_time());
		}
		if (r.getSecondCategory() != null) {
			this.stationName = r.getSecondCategory().getSecond_category_name();
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
	public DGDeliveryDataVo(String enterpriseSteelNo, String bottleLabelNo, Integer standard, String deliveryName,
			Date deliveryDate,String stationName) {
		if (enterpriseSteelNo != null) {
			this.enterpriseSteelNo = enterpriseSteelNo;
		}
		if (bottleLabelNo != null) {
			this.bottleLabelNo = bottleLabelNo;
		}
		if (standard != null) {
			this.standard = standard.toString();
		}
		if (deliveryName != null) {
			this.deliveryName = deliveryName;
		}
		if (deliveryDate != null) {
			this.deliveryDate = TimeUtils.getyyyyMMddHHmmssStringByDate(deliveryDate);
		}
		if (stationName != null) {
			this.stationName = stationName;
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

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

}
