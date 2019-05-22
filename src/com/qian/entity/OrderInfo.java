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
 * @version Create time：2015-11-17 下午3:16:39
 * @Description 订单信息表
 */

@Entity
@Table(name = "order_imformation")
public class OrderInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50)
	private String order_code;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "client_id")
	private ClientInfo clientInfo;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "air_bottle_type_id")
	private AirBottleType airBottleType;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "order_type_id")
	private OrderType orderType;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "delivery_type_id")
	private DeliveryType deliveryType;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "pay_type_id")
	private PayType payType;

	@Column(length = 50, nullable = false)
	private int order_number;

	@Column(length = 255, nullable = false)
	private String order_address;

	@Column(length = 255)
	private String order_tel_number;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "delivery_man_id")
	private User delivery_man;

	@Column()
	private Date delivery_time;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "operator_id")
	private User operator;

	@Column(length = 255)
	private String remark;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "state_id")
	private OrderInfoState state;

	@Column(nullable = false)
	private double floor_subsidies_money;

	@Column(nullable = false)
	private double delivery_fee;

	@Column(nullable = false)
	private double check_fee;

	@Column(nullable = false)
	private double price;

	@Column(nullable = false)
	private double discount_amount;

	@Column(nullable = false)
	private double total_amount;

	@Column(nullable = false)
	private Date order_time;

	@Column
	private String feeType;// 收费项目

	@Column
	private double fee_total_amount;// 零件收费总金额

	@Column()
	private Date order_appointment_time1;

	@Column()
	private Date order_appointment_time2;

	@Column(nullable = false, columnDefinition = "INT default 0")
	private int has_photo;

	@Column(nullable = false, columnDefinition = "INT default 0")
	private int print_times;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "pay_state_id")
	private PayState payState;

	@Column(length = 255)
	private String weixin_prepay_id;

	@Column(length = 255)
	private String non_qrcode_heavy_bottle;

	@Column(length = 255)
	private String non_qrcode_empty_bottle;

	@Column(nullable = false, columnDefinition = "INT default 0")
	private int is_speed_up; // 改为催气次数

	@Column()
	private Date speed_up_time; // 催气时间

	@Column()
	private Integer speed_up_wait_for_minutes; // 催气等待时分钟数

	@Column()
	private Date report_time;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "accept_second_category_id")
	private SecondCategory secondCategory;

	@Column(nullable = false, columnDefinition = "INT default 0")
	private int invoice_num; // 开发票数量

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "invoice_operator_id")
	private User invoice_operator; // 开票人

	@Column()
	private Date invoice_time; // 开票时间

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "cancel_invoice_operator_id")
	private User cancel_invoice_operator; // 最后撤销开票人

	@Column()
	private Date cancel_invoice_time; // 最后撤销开票时间

	@Column(length = 255)
	private String cancel_invoice_remark; // 撤销开票备注

	public void addInvoice(User user, Date nowTime) {
		this.invoice_operator = user;
		this.invoice_num = invoice_num + 1;
		this.invoice_time = nowTime;
	}

	public void cancelInvoice(String remark, User user, Date nowTime) {

		this.invoice_num = invoice_num - 1;
		this.invoice_operator = null;
		this.invoice_time = null;

		this.cancel_invoice_operator = user;
		this.cancel_invoice_remark = remark;
		this.cancel_invoice_time = nowTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public AirBottleType getAirBottleType() {
		return airBottleType;
	}

	public void setAirBottleType(AirBottleType airBottleType) {
		this.airBottleType = airBottleType;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public DeliveryType getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(DeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public int getOrder_number() {
		return order_number;
	}

	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}

	public String getOrder_address() {
		return order_address;
	}

	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}

	public String getOrder_tel_number() {
		return order_tel_number;
	}

	public void setOrder_tel_number(String order_tel_number) {
		this.order_tel_number = order_tel_number;
	}

	public User getDelivery_man() {
		return delivery_man;
	}

	public void setDelivery_man(User delivery_man) {
		this.delivery_man = delivery_man;
	}

	public Date getDelivery_time() {
		return delivery_time;
	}

	public void setDelivery_time(Date delivery_time) {
		this.delivery_time = delivery_time;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public OrderInfoState getState() {
		return state;
	}

	public void setState(OrderInfoState state) {
		this.state = state;
	}

	public double getFloor_subsidies_money() {
		return floor_subsidies_money;
	}

	public void setFloor_subsidies_money(double floor_subsidies_money) {
		this.floor_subsidies_money = floor_subsidies_money;
	}

	public double getDelivery_fee() {
		return delivery_fee;
	}

	public void setDelivery_fee(double delivery_fee) {
		this.delivery_fee = delivery_fee;
	}

	public double getCheck_fee() {
		return check_fee;
	}

	public void setCheck_fee(double check_fee) {
		this.check_fee = check_fee;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(double discount_amount) {
		this.discount_amount = discount_amount;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public double getFee_total_amount() {
		return fee_total_amount;
	}

	public void setFee_total_amount(double fee_total_amount) {
		this.fee_total_amount = fee_total_amount;
	}

	public Date getOrder_appointment_time1() {
		return order_appointment_time1;
	}

	public void setOrder_appointment_time1(Date order_appointment_time1) {
		this.order_appointment_time1 = order_appointment_time1;
	}

	public Date getOrder_appointment_time2() {
		return order_appointment_time2;
	}

	public void setOrder_appointment_time2(Date order_appointment_time2) {
		this.order_appointment_time2 = order_appointment_time2;
	}

	public int getHas_photo() {
		return has_photo;
	}

	public void setHas_photo(int has_photo) {
		this.has_photo = has_photo;
	}

	public int getPrint_times() {
		return print_times;
	}

	public void setPrint_times(int print_times) {
		this.print_times = print_times;
	}

	public PayState getPayState() {
		return payState;
	}

	public void setPayState(PayState payState) {
		this.payState = payState;
	}

	public String getWeixin_prepay_id() {
		return weixin_prepay_id;
	}

	public void setWeixin_prepay_id(String weixin_prepay_id) {
		this.weixin_prepay_id = weixin_prepay_id;
	}

	public String getNon_qrcode_heavy_bottle() {
		return non_qrcode_heavy_bottle;
	}

	public void setNon_qrcode_heavy_bottle(String non_qrcode_heavy_bottle) {
		this.non_qrcode_heavy_bottle = non_qrcode_heavy_bottle;
	}

	public String getNon_qrcode_empty_bottle() {
		return non_qrcode_empty_bottle;
	}

	public void setNon_qrcode_empty_bottle(String non_qrcode_empty_bottle) {
		this.non_qrcode_empty_bottle = non_qrcode_empty_bottle;
	}

	public int getIs_speed_up() {
		return is_speed_up;
	}

	public void setIs_speed_up(int is_speed_up) {
		this.is_speed_up = is_speed_up;
	}

	public Date getSpeed_up_time() {
		return speed_up_time;
	}

	public void setSpeed_up_time(Date speed_up_time) {
		this.speed_up_time = speed_up_time;
	}

	public Integer getSpeed_up_wait_for_minutes() {
		return speed_up_wait_for_minutes;
	}

	public void setSpeed_up_wait_for_minutes(Integer speed_up_wait_for_minutes) {
		this.speed_up_wait_for_minutes = speed_up_wait_for_minutes;
	}

	public Date getReport_time() {
		return report_time;
	}

	public void setReport_time(Date report_time) {
		this.report_time = report_time;
	}

	public SecondCategory getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(SecondCategory secondCategory) {
		this.secondCategory = secondCategory;
	}

	public int getInvoice_num() {
		return invoice_num;
	}

	public void setInvoice_num(int invoice_num) {
		this.invoice_num = invoice_num;
	}

	public User getInvoice_operator() {
		return invoice_operator;
	}

	public void setInvoice_operator(User invoice_operator) {
		this.invoice_operator = invoice_operator;
	}

	public Date getInvoice_time() {
		return invoice_time;
	}

	public void setInvoice_time(Date invoice_time) {
		this.invoice_time = invoice_time;
	}

	public User getCancel_invoice_operator() {
		return cancel_invoice_operator;
	}

	public void setCancel_invoice_operator(User cancel_invoice_operator) {
		this.cancel_invoice_operator = cancel_invoice_operator;
	}

	public Date getCancel_invoice_time() {
		return cancel_invoice_time;
	}

	public void setCancel_invoice_time(Date cancel_invoice_time) {
		this.cancel_invoice_time = cancel_invoice_time;
	}

	public String getCancel_invoice_remark() {
		return cancel_invoice_remark;
	}

	public void setCancel_invoice_remark(String cancel_invoice_remark) {
		this.cancel_invoice_remark = cancel_invoice_remark;
	}

}
