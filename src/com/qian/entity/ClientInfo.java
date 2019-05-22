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
 * @version Create time：2015-11-5 下午4:00:59
 * @Description 客户信息表
 */

@Entity
@Table(name = "client_imformation")
public class ClientInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50)
	private String client_code;

	@Column(length = 50)
	private String old_client_code;

	@Column(length = 50)
	private String card_code;

	@Column(length = 50)
	private String client_name;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "client_type_id")
	private ClientType clientType;

	@Column(length = 50)
	private String password;

	@Column(length = 10)
	private String client_sex;

	@Column(length = 225)
	private String mobile_tel_number_1;

	@Column(length = 225)
	private String mobile_tel_number_2;

	@Column(length = 225)
	private String fixed_tel_number_1;

	@Column(length = 225)
	private String fixed_tel_number_2;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "second_category_id")
	private SecondCategory secondCategory;

	@Column(length = 1000)
	private String client_address;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "floor_subsidies_id")
	private FloorSubsidies floorSubsidies;

	@Column(length = 1000)
	private String remark;

	@Column(length = 1000)
	private String temp_tips;

	@Column()
	private Date create_time;

	@Column(length = 50)
	private String create_people;

	@Column(length = 50)
	private String modify_people;

	@Column()
	private Date modify_time;

	@Column()
	private String weixin_qr_code;

	@Column(columnDefinition = "DOUBLE default 0")
	private Double card_money;

	private Integer bottleNum;

	@Column()
	private Date last_family_check_time; // 最后入户安检时间

	@Column()
	private Double longitude;

	@Column()
	private Double latitude;

	@Column()
	private Date last_order_time; // 最后订气时间
	
	@Column(nullable = false, columnDefinition = "INT default 0")
	private int disabled_state;// 是否伪删除

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClient_code() {
		return client_code;
	}

	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}

	public String getOld_client_code() {
		return old_client_code;
	}

	public void setOld_client_code(String old_client_code) {
		this.old_client_code = old_client_code;
	}

	public String getCard_code() {
		return card_code;
	}

	public void setCard_code(String card_code) {
		this.card_code = card_code;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClient_sex() {
		return client_sex;
	}

	public void setClient_sex(String client_sex) {
		this.client_sex = client_sex;
	}

	public String getMobile_tel_number_1() {
		return mobile_tel_number_1;
	}

	public void setMobile_tel_number_1(String mobile_tel_number_1) {
		this.mobile_tel_number_1 = mobile_tel_number_1;
	}

	public String getMobile_tel_number_2() {
		return mobile_tel_number_2;
	}

	public void setMobile_tel_number_2(String mobile_tel_number_2) {
		this.mobile_tel_number_2 = mobile_tel_number_2;
	}

	public String getFixed_tel_number_1() {
		return fixed_tel_number_1;
	}

	public void setFixed_tel_number_1(String fixed_tel_number_1) {
		this.fixed_tel_number_1 = fixed_tel_number_1;
	}

	public String getFixed_tel_number_2() {
		return fixed_tel_number_2;
	}

	public void setFixed_tel_number_2(String fixed_tel_number_2) {
		this.fixed_tel_number_2 = fixed_tel_number_2;
	}

	public SecondCategory getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(SecondCategory secondCategory) {
		this.secondCategory = secondCategory;
	}

	public String getClient_address() {
		return client_address;
	}

	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}

	public FloorSubsidies getFloorSubsidies() {
		return floorSubsidies;
	}

	public void setFloorSubsidies(FloorSubsidies floorSubsidies) {
		this.floorSubsidies = floorSubsidies;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTemp_tips() {
		return temp_tips;
	}

	public void setTemp_tips(String temp_tips) {
		this.temp_tips = temp_tips;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getCreate_people() {
		return create_people;
	}

	public void setCreate_people(String create_people) {
		this.create_people = create_people;
	}

	public String getModify_people() {
		return modify_people;
	}

	public void setModify_people(String modify_people) {
		this.modify_people = modify_people;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public String getWeixin_qr_code() {
		return weixin_qr_code;
	}

	public void setWeixin_qr_code(String weixin_qr_code) {
		this.weixin_qr_code = weixin_qr_code;
	}

	public Double getCard_money() {
		return card_money;
	}

	public void setCard_money(Double card_money) {
		this.card_money = card_money;
	}

	public Integer getBottleNum() {
		return bottleNum;
	}

	public void setBottleNum(Integer bottleNum) {
		this.bottleNum = bottleNum;
	}

	public Date getLast_family_check_time() {
		return last_family_check_time;
	}

	public void setLast_family_check_time(Date last_family_check_time) {
		this.last_family_check_time = last_family_check_time;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Date getLast_order_time() {
		return last_order_time;
	}

	public void setLast_order_time(Date last_order_time) {
		this.last_order_time = last_order_time;
	}

	public int getDisabled_state() {
		return disabled_state;
	}

	public void setDisabled_state(int disabled_state) {
		this.disabled_state = disabled_state;
	}

	@Override
	public String toString() {
		return "ClientInfo [id=" + id + ", client_code=" + client_code
				+ ", old_client_code=" + old_client_code + ", card_code="
				+ card_code + ", client_name=" + client_name + ", clientType="
				+ clientType + ", password=" + password + ", client_sex="
				+ client_sex + ", mobile_tel_number_1=" + mobile_tel_number_1
				+ ", mobile_tel_number_2=" + mobile_tel_number_2
				+ ", fixed_tel_number_1=" + fixed_tel_number_1
				+ ", fixed_tel_number_2=" + fixed_tel_number_2
				+ ", secondCategory=" + secondCategory + ", client_address="
				+ client_address + ", floorSubsidies=" + floorSubsidies
				+ ", remark=" + remark + ", temp_tips=" + temp_tips
				+ ", create_time=" + create_time + ", create_people="
				+ create_people + ", modify_people=" + modify_people
				+ ", modify_time=" + modify_time + ", weixin_qr_code="
				+ weixin_qr_code + ", card_money=" + card_money
				+ ", bottleNum=" + bottleNum + ", last_family_check_time="
				+ last_family_check_time + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", last_order_time="
				+ last_order_time + ", disabled_state=" + disabled_state + "]";
	}

}
