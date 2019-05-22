package com.qian.entity;

import java.util.Date;

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
 * @version Create time：2017-11-21 下午3:23:51
 * @Description
 */
@Entity
@Table(name = "air_bottle_import_imformation")
public class AirBottleImportInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 255)
	private String air_bottle_blong_code; // 所属单位编码

	@Column(length = 50)
	private String air_bottle_seal_code; // 气瓶钢码

	@Column(length = 50)
	private String air_bottle_type_code; // 物料类型编码

	@Column(length = 50)
	private String factory_number; // 出厂编码

	@Column(length = 255)
	private String production_unit_code; // 生产单位编码

	@Column(length = 255)
	private String detection_unit_code; // 检测单位编码

	@Column()
	private String use_cycle; // 使用年限

	@Column()
	private String remark; // 备注

	@Column()
	private String produce_time; // 生产时间

	@Column()
	private String check_time; // 检测时间

	@Column()
	private String next_check_time; // 下次检测时间

	@Column()
	private String volume; // 容积(L)

	@Column()
	private String bottle_weight; // 气瓶重量(kg)

	@Column()
	private Date create_time; // 导入时间

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "operator_id")
	private User operator;

	@Column()
	private String import_remark; // 导入备注

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "state_id")
	private AirBottleImportInfoState state;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAir_bottle_blong_code() {
		return air_bottle_blong_code;
	}

	public void setAir_bottle_blong_code(String air_bottle_blong_code) {
		this.air_bottle_blong_code = air_bottle_blong_code;
	}

	public String getAir_bottle_seal_code() {
		return air_bottle_seal_code;
	}

	public void setAir_bottle_seal_code(String air_bottle_seal_code) {
		this.air_bottle_seal_code = air_bottle_seal_code;
	}

	public String getAir_bottle_type_code() {
		return air_bottle_type_code;
	}

	public void setAir_bottle_type_code(String air_bottle_type_code) {
		this.air_bottle_type_code = air_bottle_type_code;
	}

	public String getFactory_number() {
		return factory_number;
	}

	public void setFactory_number(String factory_number) {
		this.factory_number = factory_number;
	}

	public String getProduction_unit_code() {
		return production_unit_code;
	}

	public void setProduction_unit_code(String production_unit_code) {
		this.production_unit_code = production_unit_code;
	}

	public String getDetection_unit_code() {
		return detection_unit_code;
	}

	public void setDetection_unit_code(String detection_unit_code) {
		this.detection_unit_code = detection_unit_code;
	}

	public String getUse_cycle() {
		return use_cycle;
	}

	public void setUse_cycle(String use_cycle) {
		this.use_cycle = use_cycle;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProduce_time() {
		return produce_time;
	}

	public void setProduce_time(String produce_time) {
		this.produce_time = produce_time;
	}

	public String getCheck_time() {
		return check_time;
	}

	public void setCheck_time(String check_time) {
		this.check_time = check_time;
	}

	public String getNext_check_time() {
		return next_check_time;
	}

	public void setNext_check_time(String next_check_time) {
		this.next_check_time = next_check_time;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public String getImport_remark() {
		return import_remark;
	}

	public void setImport_remark(String import_remark) {
		this.import_remark = import_remark;
	}

	public AirBottleImportInfoState getState() {
		return state;
	}

	public void setState(AirBottleImportInfoState state) {
		this.state = state;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getBottle_weight() {
		return bottle_weight;
	}

	public void setBottle_weight(String bottle_weight) {
		this.bottle_weight = bottle_weight;
	}

}
