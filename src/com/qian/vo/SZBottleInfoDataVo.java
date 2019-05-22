package com.qian.vo;

import java.util.Date;

import com.qian.entity.AirBottleInfo;
import com.qian.util.DateUtil;
import com.qian.util.SZPublicServiceUtil;
import com.qian.util.StringUtils;
import com.qian.util.TimeUtils;

/**
 * 
 * @author sc
 *
 */
public class SZBottleInfoDataVo {
	
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
	/**
	 * 图片url
	 */
	private String coverPicture;
	/**
	 * 1新增 ,2修改,3删除
	 */
	private String optCode;
	
	// 对象属性初始化值为接口要求的空值字符串
	{
		this.id = null;
		this.enterpriseSteelNo = SZPublicServiceUtil.NULL_VALUE_STR;
		this.bottleLabelNo = SZPublicServiceUtil.NULL_VALUE_STR;
		this.enterpriseName = SZPublicServiceUtil.BOTTOLE_ENTERPRISE;
		this.standard = SZPublicServiceUtil.NULL_VALUE_STR;
		this.weight = SZPublicServiceUtil.NULL_VALUE_STR;
		this.manufacturer = SZPublicServiceUtil.NULL_VALUE_STR;
		this.productionDate = SZPublicServiceUtil.NULL_VALUE_STR;
		this.invalidDate = SZPublicServiceUtil.NULL_VALUE_STR;
		this.lastTestDate = SZPublicServiceUtil.NULL_VALUE_STR;
		this.nextTestDate = SZPublicServiceUtil.NULL_VALUE_STR;
		this.testUnit = SZPublicServiceUtil.NULL_VALUE_STR;
		this.enterDate = SZPublicServiceUtil.NULL_VALUE_STR;
		this.manufacturerNo = SZPublicServiceUtil.NULL_VALUE_STR;
//		this.coverPicture = SZPublicServiceUtil.NULL_VALUE_STR;
		this.optCode = SZPublicServiceUtil.OPT_ADD;
	}

	public SZBottleInfoDataVo() {

	}
	
	public SZBottleInfoDataVo(AirBottleInfo r) {
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
		}else{
			this.invalidDate = TimeUtils.getyyyyMMddHHmmssStringByDate(DateUtil.addYear2Date(r.getUse_cycle(), r.getProduce_time()));
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
		if (StringUtils.nonEmpty(r.getImg1())&&StringUtils.nonEmpty(r.getImg2())) {
			this.coverPicture = SZPublicServiceUtil.IMG_PATH+"airBottleInfo/"+r.getImg1()+";"+
					SZPublicServiceUtil.IMG_PATH+"airBottleInfo/"+r.getImg2();
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
	public SZBottleInfoDataVo(Integer id,String enterpriseSteelNo, String bottleLabelNo, Integer standard, Double weight,
			String manufacturer,Date productionDate,Date invalidDate,Date lastTestDate,Date nextTestDate,
			String testUnit,Date enterDate,String manufacturerNo,String img1,String img2) {
		this.id = id;
		if (StringUtils.nonEmpty(enterpriseSteelNo)) {
			this.enterpriseSteelNo = enterpriseSteelNo;
		}
		if (StringUtils.nonEmpty(bottleLabelNo)) {
			this.bottleLabelNo = bottleLabelNo;
		}
		if (standard != null) {
			this.standard = standard.toString();
		}
		if (weight != null) {
			this.weight = weight.toString();
		}
		if (StringUtils.nonEmpty(manufacturer)) {
			this.manufacturer = manufacturer;
		}
		if (productionDate != null) {
			this.productionDate = TimeUtils.getyyyyMMddHHmmssStringByDate(productionDate);
		}
		if (invalidDate != null) {
			this.invalidDate = TimeUtils.getyyyyMMddHHmmssStringByDate(invalidDate);
		}else{
			this.invalidDate = TimeUtils.getyyyyMMddHHmmssStringByDate(DateUtil.addYear2Date(12, productionDate));
		}
		if (lastTestDate != null) {
			this.lastTestDate = TimeUtils.getyyyyMMddHHmmssStringByDate(lastTestDate);
		}
		if (nextTestDate != null) {
			this.nextTestDate = TimeUtils.getyyyyMMddHHmmssStringByDate(nextTestDate);
		}
		if (StringUtils.nonEmpty(testUnit)) {
			this.testUnit = testUnit;
		}
		if (enterDate != null) {
			this.enterDate = TimeUtils.getyyyyMMddHHmmssStringByDate(enterDate);
		}
		if (StringUtils.nonEmpty(manufacturerNo)) {
			this.manufacturerNo = manufacturerNo;
		}
		if (StringUtils.nonEmpty(img1)&&StringUtils.nonEmpty(img2)) {
			this.coverPicture = SZPublicServiceUtil.IMG_PATH+"airBottleInfo/"+img1+";"+
					SZPublicServiceUtil.IMG_PATH+"airBottleInfo/"+img2;
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

	public String getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(String coverPicture) {
		this.coverPicture = coverPicture;
	}

	public String getOptCode() {
		return optCode;
	}

	public void setOptCode(String optCode) {
		this.optCode = optCode;
	}

}
