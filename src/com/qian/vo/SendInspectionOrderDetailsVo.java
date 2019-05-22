package com.qian.vo;

import com.qian.entity.InspectionOrderItemInfo;
import com.qian.util.TimeUtils;

/**
 * @author Lu Kongwen
 * @version Create time：2018-5-24 下午6:25:24
 * @Description
 */
public class SendInspectionOrderDetailsVo {

	private String gasNumberId; // 钢瓶唯一标识

	private String createcode; // 出厂编号

	private String gasNumber; // 钢瓶编码

	private String producer; // 生产厂商

	private Integer cylindertype; // 气瓶类型

	private Integer fillingmedium; // 充装介质

	private Double weight;// 净重

	private String pressure; // 公称压力(Mpa)

	private String testpressure; // 实验压力(Mpa)

	private Double wallthickness; // 设计壁厚(mm)

	private String volume; // 设计容积(L)

	private String twobarcodecipher; // 二维码密文

	private String twobarcode; // 二维码明文

	private String remark; // 备注

	private String createdate; // 生产日期

	private String result; // 检测结果

	public SendInspectionOrderDetailsVo() {

	}

	public SendInspectionOrderDetailsVo(InspectionOrderItemInfo inspectionOrderItemInfo) {

		this.gasNumberId = inspectionOrderItemInfo.getGasNumberId();
		this.gasNumber = inspectionOrderItemInfo.getAirBottleInfo().getAir_bottle_seal_code();
		this.producer = inspectionOrderItemInfo.getAirBottleInfo().getProductionUnit().getProduction_unit_name();
		this.cylindertype = inspectionOrderItemInfo.getAirBottleInfo().getAirBottleType().getInspectionSystemCode().getInspection_system_code();
		this.fillingmedium = 1; // 液化石油气
		this.twobarcodecipher = inspectionOrderItemInfo.getQrCode();
		this.twobarcode = inspectionOrderItemInfo.getAirBottleInfo().getAir_bottle_code();
		this.createdate = TimeUtils.getyyyyMMddStringByDate(inspectionOrderItemInfo.getAirBottleInfo().getProduce_time());

		this.weight = inspectionOrderItemInfo.getAirBottleInfo().getAirBottleType().getBottle_weight();
		this.pressure = inspectionOrderItemInfo.getAirBottleInfo().getAirBottleType().getNominal_working_pressure();
		this.testpressure = inspectionOrderItemInfo.getAirBottleInfo().getAirBottleType().getWater_test_pressure();
		this.wallthickness = inspectionOrderItemInfo.getAirBottleInfo().getAirBottleType().getWall_thickness();
		this.volume = inspectionOrderItemInfo.getAirBottleInfo().getAirBottleType().getVolume();
	}

	public String getCreatecode() {
		return createcode;
	}

	public void setCreatecode(String createcode) {
		this.createcode = createcode;
	}

	public String getGasNumber() {
		return gasNumber;
	}

	public void setGasNumber(String gasNumber) {
		this.gasNumber = gasNumber;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Integer getCylindertype() {
		return cylindertype;
	}

	public void setCylindertype(Integer cylindertype) {
		this.cylindertype = cylindertype;
	}

	public Integer getFillingmedium() {
		return fillingmedium;
	}

	public void setFillingmedium(Integer fillingmedium) {
		this.fillingmedium = fillingmedium;
	}

	public String getTwobarcodecipher() {
		return twobarcodecipher;
	}

	public void setTwobarcodecipher(String twobarcodecipher) {
		this.twobarcodecipher = twobarcodecipher;
	}

	public String getTwobarcode() {
		return twobarcode;
	}

	public void setTwobarcode(String twobarcode) {
		this.twobarcode = twobarcode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGasNumberId() {
		return gasNumberId;
	}

	public void setGasNumberId(String gasNumberId) {
		this.gasNumberId = gasNumberId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public String getTestpressure() {
		return testpressure;
	}

	public void setTestpressure(String testpressure) {
		this.testpressure = testpressure;
	}

	public Double getWallthickness() {
		return wallthickness;
	}

	public void setWallthickness(Double wallthickness) {
		this.wallthickness = wallthickness;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

}
