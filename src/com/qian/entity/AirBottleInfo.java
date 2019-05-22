package com.qian.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qian.mobile.entity.MobileAirBottleInfo;
import com.qian.util.AirBottleCodeInitUtils;

/**
 * @author Lu Kongwen
 * @version Create time：2018-1-22 上午9:37:06
 * @Description
 */
@Entity
@Table(name = "air_bottle_imformation")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class AirBottleInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, unique = true)
	private String air_bottle_code;

	@Column(length = 50, unique = true)
	private String air_bottle_seal_code;

	@Column(length = 50)
	private String factory_number;

	@Column()
	private Integer use_cycle; // 使用年限

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_type_id")
	private AirBottleType airBottleType;

	@Column()
	private Date produce_time; // 生产时间

	@Column()
	private Date check_time; // 检测时间

	@Column()
	private Date next_check_time;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "production_unit_id")
	private ProductionUnit productionUnit; // 生产单位

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "detection_unit_id")
	private DetectionUnit detectionUnit; // 检测单位

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_belong_id")
	private AirBottleBelong airBottleBelong; // 气瓶归属

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "final_tracking_record_id")
	@JsonIgnore
	private AirBottleTrackingRecord finalAirBottleTrackingRecord;

	@Column(length = 255)
	private String img1;

	@Column(length = 255)
	private String img2;

	@Column()
	private Date create_time; // 创建时间

	@Column()
	private String create_people;

	@Column(nullable = false, columnDefinition = "INT default 0")
	@Index(name = "isscrapIndex")
	private int isscrap;

	@Column(name = "cloud_id", columnDefinition = ("varchar(50)  comment '云平台ID'"))
	private String cloudId;

	@Column(nullable = false, columnDefinition = "INT default 0")
	private int upState;
	
	@Column(nullable = false, columnDefinition = "INT default 0")
	private int publicServiceUpState;

	@Column()
	private Date scrap_time;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "scrap_operator_id")
	private User scrap_operator;

	@Column()
	private String remark;

	@Column()
	private String volume; // 容积(L)

	@Column()
	private Double bottle_weight; // 气瓶重量(kg)

	public AirBottleInfo() {

	}

	public AirBottleInfo(MobileAirBottleInfo mobileAirBottleInfo, User user, Date nowTime) {

		this.air_bottle_code = AirBottleCodeInitUtils.initCode(mobileAirBottleInfo.getAir_bottle_code());
		this.air_bottle_seal_code = mobileAirBottleInfo.getAir_bottle_seal_code();
		this.factory_number = mobileAirBottleInfo.getFactory_number();
		this.use_cycle = mobileAirBottleInfo.getUse_cycle();
		this.airBottleType = new AirBottleType(mobileAirBottleInfo.getAir_bottle_type_id());
		this.productionUnit = new ProductionUnit(mobileAirBottleInfo.getProduction_unit_id());
		this.volume = mobileAirBottleInfo.getVolume();
		this.bottle_weight = mobileAirBottleInfo.getBottle_weight();
		if (mobileAirBottleInfo.getDetection_unit_id() != null && 0 != mobileAirBottleInfo.getDetection_unit_id()) {
			this.detectionUnit = new DetectionUnit(mobileAirBottleInfo.getDetection_unit_id());
		}
		if (mobileAirBottleInfo.getAir_bottle_belong_id() != null && 0 != mobileAirBottleInfo.getAir_bottle_belong_id()) {
			this.airBottleBelong = new AirBottleBelong(mobileAirBottleInfo.getAir_bottle_belong_id());
		}

		this.produce_time = new Date(mobileAirBottleInfo.getProduce_time_tmp());

		if (0 != mobileAirBottleInfo.getCheck_time_tmp()) {
			this.check_time = new Date(mobileAirBottleInfo.getCheck_time_tmp());
		}

		if (mobileAirBottleInfo.getPhotos() != null && mobileAirBottleInfo.getPhotos().size() >= 2) {
			this.img1 = mobileAirBottleInfo.getPhotos().get(0);
			this.img2 = mobileAirBottleInfo.getPhotos().get(1);
		}

		this.next_check_time = new Date(mobileAirBottleInfo.getNext_check_time_tmp());
		this.create_time = nowTime;
		this.create_people = user.getFull_name();
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAir_bottle_code() {
		return air_bottle_code;
	}

	public void setAir_bottle_code(String air_bottle_code) {
		this.air_bottle_code = air_bottle_code;
	}

	public String getAir_bottle_seal_code() {
		return air_bottle_seal_code;
	}

	public void setAir_bottle_seal_code(String air_bottle_seal_code) {
		this.air_bottle_seal_code = air_bottle_seal_code;
	}

	public String getFactory_number() {
		return factory_number;
	}

	public void setFactory_number(String factory_number) {
		this.factory_number = factory_number;
	}

	public Integer getUse_cycle() {
		return use_cycle;
	}

	public void setUse_cycle(Integer use_cycle) {
		this.use_cycle = use_cycle;
	}

	public AirBottleType getAirBottleType() {
		return airBottleType;
	}

	public void setAirBottleType(AirBottleType airBottleType) {
		this.airBottleType = airBottleType;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getCheck_time() {
		return check_time;
	}

	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}

	public Date getNext_check_time() {
		return next_check_time;
	}

	public void setNext_check_time(Date next_check_time) {
		this.next_check_time = next_check_time;
	}

	public ProductionUnit getProductionUnit() {
		return productionUnit;
	}

	public void setProductionUnit(ProductionUnit productionUnit) {
		this.productionUnit = productionUnit;
	}

	public DetectionUnit getDetectionUnit() {
		return detectionUnit;
	}

	public void setDetectionUnit(DetectionUnit detectionUnit) {
		this.detectionUnit = detectionUnit;
	}

	public AirBottleTrackingRecord getFinalAirBottleTrackingRecord() {
		return finalAirBottleTrackingRecord;
	}

	public void setFinalAirBottleTrackingRecord(AirBottleTrackingRecord finalAirBottleTrackingRecord) {
		this.finalAirBottleTrackingRecord = finalAirBottleTrackingRecord;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public Date getProduce_time() {
		return produce_time;
	}

	public void setProduce_time(Date produce_time) {
		this.produce_time = produce_time;
	}

	public String getCreate_people() {
		return create_people;
	}

	public void setCreate_people(String create_people) {
		this.create_people = create_people;
	}

	public AirBottleBelong getAirBottleBelong() {
		return airBottleBelong;
	}

	public void setAirBottleBelong(AirBottleBelong airBottleBelong) {
		this.airBottleBelong = airBottleBelong;
	}

	public int getIsscrap() {
		return isscrap;
	}

	public void setIsscrap(int isscrap) {
		this.isscrap = isscrap;
	}

	public Date getScrap_time() {
		return scrap_time;
	}

	public void setScrap_time(Date scrap_time) {
		this.scrap_time = scrap_time;
	}

	public User getScrap_operator() {
		return scrap_operator;
	}

	public void setScrap_operator(User scrap_operator) {
		this.scrap_operator = scrap_operator;
	}

	public String getCloudId() {
		return cloudId;
	}

	public void setCloudId(String cloudId) {
		this.cloudId = cloudId;
	}

	public int getUpState() {
		return upState;
	}

	public void setUpState(int upState) {
		this.upState = upState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public int getPublicServiceUpState() {
		return publicServiceUpState;
	}

	public void setPublicServiceUpState(int publicServiceUpState) {
		this.publicServiceUpState = publicServiceUpState;
	}

}
