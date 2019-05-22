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
 * 回访信息表
 * @author DUANKK
 * @version 2018年11月27日09:10:41
 */
@Entity
@Table(name = "visit_info")
public class VisitInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/**
	 * 回访编号
	 */
	@Column(length = 50)
	private String visit_code;

	/**
	 * 回访类型
	 */
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "visit_type_id")
	private VisitType visitType;

	/**
	 * 回访内容
	 */
	@Column(length = 5000)
	private String visit_note;

	/**
	 * 回访电话
	 */
	@Column(length = 50)
	private String visit_tel_number;

	/**
	 * 回访地址
	 */
	@Column(length = 100)
	private String visit_address;

	/**
	 * 回访备注
	 */
	@Column(length = 100)
	private String visit_content;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "client_id")
	private ClientInfo clientInfo;// 客户

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "operator_id")
	private User operator;// 操作人员

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "accept_second_category_id")
	private SecondCategory secondCategory;
	
	@Column()
	private Date create_time; // 创建时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVisit_code() {
		return visit_code;
	}

	public void setVisit_code(String visit_code) {
		this.visit_code = visit_code;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}

	public String getVisit_note() {
		return visit_note;
	}

	public void setVisit_note(String visit_note) {
		this.visit_note = visit_note;
	}

	public String getVisit_tel_number() {
		return visit_tel_number;
	}

	public void setVisit_tel_number(String visit_tel_number) {
		this.visit_tel_number = visit_tel_number;
	}

	public String getVisit_address() {
		return visit_address;
	}

	public void setVisit_address(String visit_address) {
		this.visit_address = visit_address;
	}

	public String getVisit_content() {
		return visit_content;
	}

	public void setVisit_content(String visit_content) {
		this.visit_content = visit_content;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public SecondCategory getSecondCategory() {
		return secondCategory;
	}

	public void setSecondCategory(SecondCategory secondCategory) {
		this.secondCategory = secondCategory;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
