package com.qian.vo;

import java.util.Date;

import com.qian.entity.AirBottleInfo;
import com.qian.util.DGPublicServiceUtil;
import com.qian.util.TimeUtils;

/**
 * 
 * @author sc
 *
 */
public class DGBottleInfoDataVo {
	
	/**
	 * 气瓶ID
	 */
	private Integer id;

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
	private String enterpriseName;
	/**
	 * 气瓶规格 id
	 */
	private String standard;
	/**
	 * 瓶体自重（kg）
	 */
	private String weight;
	/**
	 *  生产厂家
	 */
	private String manufacturer;
	/**
	 *  出厂日期
	 */
	private String productionDate;
	/**
	 *  报废日期
	 */
	private String invalidDate;
	/**
	 *  上次检测日期
	 */
	private String lastTestDate;
	/**
	 *  下次检测日期
	 */
	private String nextTestDate;
	/**
	 *  检测单位名称
	 */
	private String testUnit;
	/**
	 *  录入时间
	 */
	private String enterDate;
	/**
	 *  出厂编号
	 */
	private String manufacturerNo;

	// 对象属性初始化值为接口要求的空值字符串
	{
		this.id = null;
		this.enterpriseSteelNo = DGPublicServiceUtil.NULL_VALUE_STR;
		this.bottleLabelNo = DGPublicServiceUtil.NULL_VALUE_STR;
		this.enterpriseName = DGPublicServiceUtil.BOTTOLE_ENTERPRISE;
		this.standard = DGPublicServiceUtil.NULL_VALUE_STR;
		this.weight = DGPublicServiceUtil.NULL_VALUE_STR;
		this.manufacturer = DGPublicServiceUtil.NULL_VALUE_STR;
		this.productionDate = DGPublicServiceUtil.NULL_VALUE_STR;
		this.invalidDate = DGPublicServiceUtil.NULL_VALUE_STR;
		this.lastTestDate = DGPublicServiceUtil.NULL_VALUE_STR;
		this.nextTestDate = DGPublicServiceUtil.NULL_VALUE_STR;
		this.testUnit = DGPublicServiceUtil.NULL_VALUE_STR;
		this.enterDate = DGPublicServiceUtil.NULL_VALUE_STR;
		this.manufacturerNo = DGPublicServiceUtil.NULL_VALUE_STR;
	}

	public DGBottleInfoDataVo() {

	}
	
	public DGBottleInfoDataVo(AirBottleInfo r) {
		this.id = r.getId();
		if (r.getAir_bottle_seal_code() != null) {
			this.enterpriseSteelNo = r.getAir_bottle_seal_code();
		}
		if (r.getAir_bottle_code() != null) {
			this.bottleLabelNo = r.getAir_bottle_code();
		}
		if (r.getAirBottleType().getPublicServiceMaterialType() != null) {
			this.standard = r.getAirBottleType().getPublicServiceMaterialType().getId().toString();
		}
//		if (r.getBottle_weight() != null) {
//			this.weight = r.getBottle_weight().toString();
//		}else {
//			this.weight = r.getAirBottleType().getBottle_weight().toString();
//		}
		if (r.getAirBottleType().getBottle_weight() != null) {
			this.weight = r.getAirBottleType().getBottle_weight().toString();
		}
		if (r.getProductionUnit() != null) {
			this.manufacturer = r.getProductionUnit().getProduction_unit_name();
		}
		if (r.getProduce_time() != null) {
			this.productionDate = TimeUtils.getyyyyMMddHHmmssStringByDate(r.getProduce_time());
		}
		if (r.getScrap_time() != null) {
			this.invalidDate = TimeUtils.getyyyyMMddHHmmssStringByDate(r.getScrap_time());
		}
		if (r.getCheck_time() != null) {
			this.lastTestDate = TimeUtils.getyyyyMMddHHmmssStringByDate(r.getCheck_time());
		}
		if (r.getNext_check_time() != null) {
			this.nextTestDate = TimeUtils.getyyyyMMddHHmmssStringByDate(r.getNext_check_time());
		}
		if (r.getDetectionUnit() != null) {
			this.testUnit = r.getDetectionUnit().getDetection_unit_name();
		}
		if (r.getCreate_time() != null) {
			this.enterDate = TimeUtils.getyyyyMMddHHmmssStringByDate(r.getCreate_time());
		}
		if (r.getFactory_number() != null) {
			this.manufacturerNo = r.getFactory_number();
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
	public DGBottleInfoDataVo(Integer id,String enterpriseSteelNo, String bottleLabelNo, Integer standard, Double weight,
			String manufacturer,Date productionDate,Date invalidDate,Date lastTestDate,Date nextTestDate,
			String testUnit,Date enterDate,String manufacturerNo) {
		this.id = id;
		if (enterpriseSteelNo != null) {
			this.enterpriseSteelNo = enterpriseSteelNo;
		}
		if (bottleLabelNo != null) {
			this.bottleLabelNo = bottleLabelNo;
		}
		if (standard != null) {
			this.standard = standard.toString();
		}
		if (weight != null) {
			this.weight = weight.toString();
		}
		if (manufacturer != null) {
			this.manufacturer = manufacturer;
		}
		if (productionDate != null) {
			this.productionDate = TimeUtils.getyyyyMMddHHmmssStringByDate(productionDate);
		}
		if (invalidDate != null) {
			this.invalidDate = TimeUtils.getyyyyMMddHHmmssStringByDate(invalidDate);
		}
		if (lastTestDate != null) {
			this.lastTestDate = TimeUtils.getyyyyMMddHHmmssStringByDate(lastTestDate);
		}
		if (nextTestDate != null) {
			this.nextTestDate = TimeUtils.getyyyyMMddHHmmssStringByDate(nextTestDate);
		}
		if (testUnit != null) {
			this.testUnit = testUnit;
		}
		if (enterDate != null) {
			this.enterDate = TimeUtils.getyyyyMMddHHmmssStringByDate(enterDate);
		}
		if (manufacturerNo != null) {
			this.manufacturerNo = manufacturerNo;
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

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	public String getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(String invalidDate) {
		this.invalidDate = invalidDate;
	}

	public String getLastTestDate() {
		return lastTestDate;
	}

	public void setLastTestDate(String lastTestDate) {
		this.lastTestDate = lastTestDate;
	}

	public String getNextTestDate() {
		return nextTestDate;
	}

	public void setNextTestDate(String nextTestDate) {
		this.nextTestDate = nextTestDate;
	}

	public String getTestUnit() {
		return testUnit;
	}

	public void setTestUnit(String testUnit) {
		this.testUnit = testUnit;
	}

	public String getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}

	public String getManufacturerNo() {
		return manufacturerNo;
	}

	public void setManufacturerNo(String manufacturerNo) {
		this.manufacturerNo = manufacturerNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
