package com.qian.vo;

import java.util.Date;

import com.qian.entity.AirBottleTrackingRecord;
import com.qian.util.DGPublicServiceUtil;
import com.qian.util.TimeUtils;

/**
 * 交付记录vo
 * @author sc
 *
 */
public class DGHandOverDataVo {

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
	 * 交付者名称
	 */
	private String handOverName;
	/**
	 * 交付时间
	 */
	private String handOverDate;
	/**
	 * 交付的用户名称
	 */
	private String userName;
	/**
	 * 用户卡编号
	 */
	private String userCardNo;

	// 对象属性初始化值为接口要求的空值字符串
	{
		this.enterpriseSteelNo = DGPublicServiceUtil.NULL_VALUE_STR;
		this.bottleLabelNo = DGPublicServiceUtil.NULL_VALUE_STR;
		this.bottoleEnterprise = DGPublicServiceUtil.BOTTOLE_ENTERPRISE;
		this.standard = DGPublicServiceUtil.NULL_VALUE_STR;
		this.handOverName = DGPublicServiceUtil.NULL_VALUE_STR;
		this.handOverDate = DGPublicServiceUtil.NULL_VALUE_STR;
		this.userName = DGPublicServiceUtil.NULL_VALUE_STR;
		this.userCardNo = DGPublicServiceUtil.NULL_VALUE_STR;
	}

	public DGHandOverDataVo() {

	}
	
	public DGHandOverDataVo(AirBottleTrackingRecord r ) {
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
			this.handOverName = r.getOperator().getFull_name();
		}
		if (r.getCreate_time() != null) {
			this.handOverDate = TimeUtils.getyyyyMMddHHmmssStringByDate(r.getCreate_time());
		}
		if (r.getClientInfo() != null) {
			this.userName = r.getClientInfo().getClient_name();
			this.userCardNo = r.getClientInfo().getCard_code();
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
	public DGHandOverDataVo(String enterpriseSteelNo, String bottleLabelNo, Integer standard, String handOverName,
			Date handOverDate,String userName,String userCardNo) {
		if (enterpriseSteelNo != null) {
			this.enterpriseSteelNo = enterpriseSteelNo;
		}
		if (bottleLabelNo != null) {
			this.bottleLabelNo = bottleLabelNo;
		}
		if (standard != null) {
			this.standard = standard.toString();
		}
		if (handOverName != null) {
			this.handOverName = handOverName;
		}
		if (handOverDate != null) {
			this.handOverDate = TimeUtils.getyyyyMMddHHmmssStringByDate(handOverDate);
		}
		if (userName != null) {
			this.userName = userName;
		}
		if (userCardNo != null) {
			this.userCardNo = userCardNo;
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

	public String getHandOverName() {
		return handOverName;
	}

	public void setHandOverName(String handOverName) {
		this.handOverName = handOverName;
	}

	public String getHandOverDate() {
		return handOverDate;
	}

	public void setHandOverDate(String handOverDate) {
		this.handOverDate = handOverDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCardNo() {
		return userCardNo;
	}

	public void setUserCardNo(String userCardNo) {
		this.userCardNo = userCardNo;
	}

}
