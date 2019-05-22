package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-9 下午2:42:43
 * @Description
 */
@Entity
@Table(name = "family_check_content_imformation")
public class FamilyCheckContentInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50)
	private String arrive_time; // 到达时间

	@Column(length = 50)
	private String whether_enter_home; // /入户检查 Value:值为yes是入户检查 没选择就是没值

	@Column(length = 50)
	private String check_date; // 检查日期

	@Column(length = 50)
	private String leave_time; // 离开时间

	@Column(length = 50)
	private String whether_have_somebody; // /无人 Value:值为yes是无人 没选择就是没值

	@Column(length = 50)
	private String whether_refuse; // 拒绝 Value:值为yes是拒绝 没选择就是没值

	@Column(length = 50)
	private String bollte_corrosion_degree; // 钢瓶外观腐蚀程度 severity严重，mild：轻度

	@Column(length = 50)
	private String bottle_whether_air_leakage; // 钢瓶是否漏气 yes 是no否

	@Column(length = 50)
	private String bollte_number; // 钢瓶编码

	@Column(length = 50)
	private String bolle_indate; // 钢瓶期效 normal正常,past_due过期，scrap报废

	@Column(length = 50)
	private String whether_company_bottle; // 是否我司钢瓶 yes是,no否

	@Column(length = 50)
	private String next_probative_term; // 下次检验期报废期

	@Column(length = 50)
	private String reducing_valve_brand; // 减压阀品牌

	@Column(length = 50)
	private String reducing_valve_type; // 减压阀型号

	@Column(length = 50)
	private String reducing_valve_corrosion_degree; // 减压阀外观腐蚀程度 severity严重，mild：轻度

	@Column(length = 50)
	private String reducing_valve_whether_air_leakage; // 减压阀是否漏气 yes 是no否

	@Column(length = 50)
	private String stove_brand; // 灶具品牌

	@Column(length = 50)
	private String stove_buy_date; // 灶具购置日期

	@Column(length = 50)
	private String stove_install_type; // 安装类型 table 台式 flushbonading嵌入式

	@Column(length = 50)
	private String flame_out_protection_device; // 熄火保护装置 yes是no否

	@Column(length = 50)
	private String stove_whether_air_leakage; // 是否漏气 yes有no无

	@Column(length = 100)
	private String stove_connecting_pipe; // 灶具连接管 too_long过长 not_have_pipe_strap无管卡 Aging_through_walls老化穿墙 through_walls穿墙

	@Column(length = 50)
	private String calorifier_brand; // 热水器品牌

	@Column(length = 50)
	private String calorifier_buy_date; // 热水器购置日期

	@Column(length = 50)
	private String calorifier_address; // 热水器位置

	@Column(length = 100)
	private String calorifier_exhaust_type; // 热水器排气方式 flue_type烟道式 JSQ强排式 balanced_type 平衡式

	@Column(length = 100)
	private String calorifier_flue_state; // 烟道状况 not_have_flue无烟道 dlurt脱口 Damaged破损 not_entirely_out_of_the_window 未完全伸至室外

	@Column(length = 50)
	private String calorifier_flue_texture; // 烟道材质 aluminum_foil:铝箔 stainless_steel:不锈钢

	@Column(length = 50)
	private String calorifier_burn_statu; // 燃烧状况 normal正常 abnormality 不正常

	@Column(length = 100)
	private String calorifier_connecting_pipe; // 热水器连接管 too_long过长 not_have_pipe_strap无管卡 Aging_through_walls老化穿墙 through_walls穿墙

	@Column(length = 50)
	private String security_manual; // 安全手册 issued已发放 passed未发放

	@Column(length = 225)
	private String case_description; // 违章隐患情况描述

	@Column(length = 100)
	private String personnel_signature; // /安检人员签字

	@Column(length = 225)
	private String opinion; // 意见或者建议

	@Column(length = 100)
	private String satisfaction; // 客户满意度 satisfaction满意 basic_satisfaction基本满意 Dissatisfaction不满意

	@Column(length = 100)
	private String client_signature; // 客户签名

	@Column(length = 100)
	private String callback_date; // 话务员回访时间

	@Column(length = 225)
	private String reply_record; // 回记记录

	@Column(length = 50)
	private String operator_id; // 话务员工号

	@Column(length = 100)
	private String operator_to_sign; // 话务员签字

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBollte_corrosion_degree() {
		return bollte_corrosion_degree;
	}

	public void setBollte_corrosion_degree(String bollte_corrosion_degree) {
		this.bollte_corrosion_degree = bollte_corrosion_degree;
	}

	public String getBottle_whether_air_leakage() {
		return bottle_whether_air_leakage;
	}

	public void setBottle_whether_air_leakage(String bottle_whether_air_leakage) {
		this.bottle_whether_air_leakage = bottle_whether_air_leakage;
	}

	public String getBollte_number() {
		return bollte_number;
	}

	public void setBollte_number(String bollte_number) {
		this.bollte_number = bollte_number;
	}

	public String getBolle_indate() {
		return bolle_indate;
	}

	public void setBolle_indate(String bolle_indate) {
		this.bolle_indate = bolle_indate;
	}

	public String getWhether_company_bottle() {
		return whether_company_bottle;
	}

	public void setWhether_company_bottle(String whether_company_bottle) {
		this.whether_company_bottle = whether_company_bottle;
	}

	public String getNext_probative_term() {
		return next_probative_term;
	}

	public void setNext_probative_term(String next_probative_term) {
		this.next_probative_term = next_probative_term;
	}

	public String getReducing_valve_brand() {
		return reducing_valve_brand;
	}

	public void setReducing_valve_brand(String reducing_valve_brand) {
		this.reducing_valve_brand = reducing_valve_brand;
	}

	public String getReducing_valve_type() {
		return reducing_valve_type;
	}

	public void setReducing_valve_type(String reducing_valve_type) {
		this.reducing_valve_type = reducing_valve_type;
	}

	public String getReducing_valve_corrosion_degree() {
		return reducing_valve_corrosion_degree;
	}

	public void setReducing_valve_corrosion_degree(String reducing_valve_corrosion_degree) {
		this.reducing_valve_corrosion_degree = reducing_valve_corrosion_degree;
	}

	public String getReducing_valve_whether_air_leakage() {
		return reducing_valve_whether_air_leakage;
	}

	public void setReducing_valve_whether_air_leakage(String reducing_valve_whether_air_leakage) {
		this.reducing_valve_whether_air_leakage = reducing_valve_whether_air_leakage;
	}

	public String getStove_brand() {
		return stove_brand;
	}

	public void setStove_brand(String stove_brand) {
		this.stove_brand = stove_brand;
	}

	public String getStove_buy_date() {
		return stove_buy_date;
	}

	public void setStove_buy_date(String stove_buy_date) {
		this.stove_buy_date = stove_buy_date;
	}

	public String getStove_install_type() {
		return stove_install_type;
	}

	public void setStove_install_type(String stove_install_type) {
		this.stove_install_type = stove_install_type;
	}

	public String getFlame_out_protection_device() {
		return flame_out_protection_device;
	}

	public void setFlame_out_protection_device(String flame_out_protection_device) {
		this.flame_out_protection_device = flame_out_protection_device;
	}

	public String getStove_whether_air_leakage() {
		return stove_whether_air_leakage;
	}

	public void setStove_whether_air_leakage(String stove_whether_air_leakage) {
		this.stove_whether_air_leakage = stove_whether_air_leakage;
	}

	public String getStove_connecting_pipe() {
		return stove_connecting_pipe;
	}

	public void setStove_connecting_pipe(String stove_connecting_pipe) {
		this.stove_connecting_pipe = stove_connecting_pipe;
	}

	public String getCalorifier_brand() {
		return calorifier_brand;
	}

	public void setCalorifier_brand(String calorifier_brand) {
		this.calorifier_brand = calorifier_brand;
	}

	public String getCalorifier_buy_date() {
		return calorifier_buy_date;
	}

	public void setCalorifier_buy_date(String calorifier_buy_date) {
		this.calorifier_buy_date = calorifier_buy_date;
	}

	public String getCalorifier_address() {
		return calorifier_address;
	}

	public void setCalorifier_address(String calorifier_address) {
		this.calorifier_address = calorifier_address;
	}

	public String getCalorifier_exhaust_type() {
		return calorifier_exhaust_type;
	}

	public void setCalorifier_exhaust_type(String calorifier_exhaust_type) {
		this.calorifier_exhaust_type = calorifier_exhaust_type;
	}

	public String getCalorifier_flue_state() {
		return calorifier_flue_state;
	}

	public void setCalorifier_flue_state(String calorifier_flue_state) {
		this.calorifier_flue_state = calorifier_flue_state;
	}

	public String getCalorifier_flue_texture() {
		return calorifier_flue_texture;
	}

	public void setCalorifier_flue_texture(String calorifier_flue_texture) {
		this.calorifier_flue_texture = calorifier_flue_texture;
	}

	public String getCalorifier_burn_statu() {
		return calorifier_burn_statu;
	}

	public void setCalorifier_burn_statu(String calorifier_burn_statu) {
		this.calorifier_burn_statu = calorifier_burn_statu;
	}

	public String getCalorifier_connecting_pipe() {
		return calorifier_connecting_pipe;
	}

	public void setCalorifier_connecting_pipe(String calorifier_connecting_pipe) {
		this.calorifier_connecting_pipe = calorifier_connecting_pipe;
	}

	public String getSecurity_manual() {
		return security_manual;
	}

	public void setSecurity_manual(String security_manual) {
		this.security_manual = security_manual;
	}

	public String getCase_description() {
		return case_description;
	}

	public void setCase_description(String case_description) {
		this.case_description = case_description;
	}

	public String getPersonnel_signature() {
		return personnel_signature;
	}

	public void setPersonnel_signature(String personnel_signature) {
		this.personnel_signature = personnel_signature;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}

	public String getClient_signature() {
		return client_signature;
	}

	public void setClient_signature(String client_signature) {
		this.client_signature = client_signature;
	}

	public String getCallback_date() {
		return callback_date;
	}

	public void setCallback_date(String callback_date) {
		this.callback_date = callback_date;
	}

	public String getReply_record() {
		return reply_record;
	}

	public void setReply_record(String reply_record) {
		this.reply_record = reply_record;
	}

	public String getOperator_to_sign() {
		return operator_to_sign;
	}

	public void setOperator_to_sign(String operator_to_sign) {
		this.operator_to_sign = operator_to_sign;
	}

	public String getArrive_time() {
		return arrive_time;
	}

	public void setArrive_time(String arrive_time) {
		this.arrive_time = arrive_time;
	}

	public String getWhether_enter_home() {
		return whether_enter_home;
	}

	public void setWhether_enter_home(String whether_enter_home) {
		this.whether_enter_home = whether_enter_home;
	}

	public String getCheck_date() {
		return check_date;
	}

	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}

	public String getLeave_time() {
		return leave_time;
	}

	public void setLeave_time(String leave_time) {
		this.leave_time = leave_time;
	}

	public String getWhether_have_somebody() {
		return whether_have_somebody;
	}

	public void setWhether_have_somebody(String whether_have_somebody) {
		this.whether_have_somebody = whether_have_somebody;
	}

	public String getWhether_refuse() {
		return whether_refuse;
	}

	public void setWhether_refuse(String whether_refuse) {
		this.whether_refuse = whether_refuse;
	}

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

}
