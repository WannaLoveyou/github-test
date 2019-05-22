package com.qian.entity;

import javax.persistence.CascadeType;
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
 * @version Create time：2015-11-13 下午3:42:10
 * @Description 气瓶类型表
 */

@Entity
@Table(name = "air_bottle_type")
public class AirBottleType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String air_bottle_specifications;

	@Column(length = 50, nullable = false)
	private String air_bottle_type_code;

	@Column(length = 50, nullable = false)
	private String measurement_unit; // 计量单位

	@Column(nullable = false)
	private double price;

	@Column(nullable = false)
	private double delivery_fee; // 送气费

	@Column(nullable = false)
	private double inspection_fee; // 检测费

	@Column(length = 50)
	private String has_gas_weight; // 含气重量

	@Column(length = 255)
	private String app_img;

	@Column()
	private Double supply_delivery_fee; // 免运费应补气费

	@Column()
	private Double weixin_discounty_fee; // 微信优惠价格

	@Column(nullable = false, columnDefinition = "INT default 1")
	private int weixin_buy;

	@Column(length = 50)
	private String air_bottle_product_name; // 气瓶产品名称

	@Column(length = 50)
	private String air_bottle_length_width; // 气瓶直径

	@Column()
	private Double default_weight; // 默认重量

	@Column()
	private String volume; // 容积(L)

	@Column()
	private Double bottle_weight; // 气瓶重量(kg)

	@Column()
	private Double total_weight; // 含气总重(kg)

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_variety_id")
	private AirBottleVariety airBottleVariety; // 气瓶品种

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "filling_medium_id")
	private FillingMedium fillingMedium; // 充装种类

	@Column()
	private String bottle_model; // 气瓶型号

	@Column()
	private String nominal_working_pressure; // 公称工作压力(MPa)

	@Column()
	private String water_test_pressure; // 水试验压力(MPa)

	@Column()
	private Double wall_thickness; // 设计壁厚(mm)

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "inspection_system_code_id")
	private InspectionSystemCode inspectionSystemCode; // 检测站气瓶类型编号

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "public_service_material_type_id")
	private PublicServiceMaterialType publicServiceMaterialType; // 东莞公共服务平台气瓶类型

	@Column(columnDefinition = "INT default 4")
	private Integer default_detection_cycle; // 默认检测周期

	@Column(columnDefinition = "INT default 8")
	private Integer default_use_cycle; // 默认设计使用年限

	public AirBottleType() {

	}

	public AirBottleType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAir_bottle_specifications() {
		return air_bottle_specifications;
	}

	public void setAir_bottle_specifications(String air_bottle_specifications) {
		this.air_bottle_specifications = air_bottle_specifications;
	}

	public String getMeasurement_unit() {
		return measurement_unit;
	}

	public void setMeasurement_unit(String measurement_unit) {
		this.measurement_unit = measurement_unit;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDelivery_fee() {
		return delivery_fee;
	}

	public void setDelivery_fee(double delivery_fee) {
		this.delivery_fee = delivery_fee;
	}

	public double getInspection_fee() {
		return inspection_fee;
	}

	public void setInspection_fee(double inspection_fee) {
		this.inspection_fee = inspection_fee;
	}

	public String getAir_bottle_product_name() {
		return air_bottle_product_name;
	}

	public void setAir_bottle_product_name(String air_bottle_product_name) {
		this.air_bottle_product_name = air_bottle_product_name;
	}

	public String getAir_bottle_length_width() {
		return air_bottle_length_width;
	}

	public void setAir_bottle_length_width(String air_bottle_length_width) {
		this.air_bottle_length_width = air_bottle_length_width;
	}

	public String getHas_gas_weight() {
		return has_gas_weight;
	}

	public void setHas_gas_weight(String has_gas_weight) {
		this.has_gas_weight = has_gas_weight;
	}

	public String getApp_img() {
		return app_img;
	}

	public void setApp_img(String app_img) {
		this.app_img = app_img;
	}

	public Double getSupply_delivery_fee() {
		return supply_delivery_fee;
	}

	public void setSupply_delivery_fee(Double supply_delivery_fee) {
		this.supply_delivery_fee = supply_delivery_fee;
	}

	public Double getWeixin_discounty_fee() {
		return weixin_discounty_fee;
	}

	public void setWeixin_discounty_fee(Double weixin_discounty_fee) {
		this.weixin_discounty_fee = weixin_discounty_fee;
	}

	public int getWeixin_buy() {
		return weixin_buy;
	}

	public void setWeixin_buy(int weixin_buy) {
		this.weixin_buy = weixin_buy;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public Double getBottle_weight() {
		return bottle_weight;
	}

	public void setBottle_weight(Double bottle_weight) {
		this.bottle_weight = bottle_weight;
	}

	public Double getTotal_weight() {
		return total_weight;
	}

	public void setTotal_weight(Double total_weight) {
		this.total_weight = total_weight;
	}

	public AirBottleVariety getAirBottleVariety() {
		return airBottleVariety;
	}

	public void setAirBottleVariety(AirBottleVariety airBottleVariety) {
		this.airBottleVariety = airBottleVariety;
	}

	public String getBottle_model() {
		return bottle_model;
	}

	public void setBottle_model(String bottle_model) {
		this.bottle_model = bottle_model;
	}

	public String getNominal_working_pressure() {
		return nominal_working_pressure;
	}

	public void setNominal_working_pressure(String nominal_working_pressure) {
		this.nominal_working_pressure = nominal_working_pressure;
	}

	public String getWater_test_pressure() {
		return water_test_pressure;
	}

	public void setWater_test_pressure(String water_test_pressure) {
		this.water_test_pressure = water_test_pressure;
	}

	public Double getWall_thickness() {
		return wall_thickness;
	}

	public void setWall_thickness(Double wall_thickness) {
		this.wall_thickness = wall_thickness;
	}

	public InspectionSystemCode getInspectionSystemCode() {
		return inspectionSystemCode;
	}

	public void setInspectionSystemCode(InspectionSystemCode inspectionSystemCode) {
		this.inspectionSystemCode = inspectionSystemCode;
	}

	public Double getDefault_weight() {
		return default_weight;
	}

	public void setDefault_weight(Double default_weight) {
		this.default_weight = default_weight;
	}

	public String getAir_bottle_type_code() {
		return air_bottle_type_code;
	}

	public void setAir_bottle_type_code(String air_bottle_type_code) {
		this.air_bottle_type_code = air_bottle_type_code;
	}

	public FillingMedium getFillingMedium() {
		return fillingMedium;
	}

	public void setFillingMedium(FillingMedium fillingMedium) {
		this.fillingMedium = fillingMedium;
	}

	public PublicServiceMaterialType getPublicServiceMaterialType() {
		return publicServiceMaterialType;
	}

	public void setPublicServiceMaterialType(PublicServiceMaterialType publicServiceMaterialType) {
		this.publicServiceMaterialType = publicServiceMaterialType;
	}

	public Integer getDefault_detection_cycle() {
		return default_detection_cycle;
	}

	public void setDefault_detection_cycle(Integer default_detection_cycle) {
		this.default_detection_cycle = default_detection_cycle;
	}

	public Integer getDefault_use_cycle() {
		return default_use_cycle;
	}

	public void setDefault_use_cycle(Integer default_use_cycle) {
		this.default_use_cycle = default_use_cycle;
	}

}
