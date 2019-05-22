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
import javax.persistence.Transient;

/**
 * @author Lu Kongwen
 * @version Create time：2017-10-13 上午9:11:43
 * @Description
 */
@Entity
@Table(name = "invoice_record")
public class InvoiceRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "operator_id")
	private User operator;

	@Column()
	private Date create_time;

	@Column(length = 255)
	private String remark;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "invoice_record_state_id")
	private InvoiceRecordState invoiceRecordState;

	@Column()
	private int order_id;

	@Transient
	private String order_state_description;

	@Column()
	private String order_code;

	public InvoiceRecord() {

	}

	public InvoiceRecord(User operator, Date create_time, String remark, InvoiceRecordState invoiceRecordState, int orderId, String orderCode) {
		this.operator = operator;
		this.create_time = create_time;
		this.remark = remark;
		this.invoiceRecordState = invoiceRecordState;
		this.order_id = orderId;
		this.order_code = orderCode;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the operator
	 */
	public User getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(User operator) {
		this.operator = operator;
	}

	/**
	 * @return the create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}

	/**
	 * @param create_time
	 *            the create_time to set
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the invoiceRecordState
	 */
	public InvoiceRecordState getInvoiceRecordState() {
		return invoiceRecordState;
	}

	/**
	 * @param invoiceRecordState
	 *            the invoiceRecordState to set
	 */
	public void setInvoiceRecordState(InvoiceRecordState invoiceRecordState) {
		this.invoiceRecordState = invoiceRecordState;
	}

	/**
	 * @return the order_id
	 */
	public int getOrder_id() {
		return order_id;
	}

	/**
	 * @param order_id
	 *            the order_id to set
	 */
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	/**
	 * @return the order_code
	 */
	public String getOrder_code() {
		return order_code;
	}

	/**
	 * @param order_code
	 *            the order_code to set
	 */
	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

	public String getOrder_state_description() {
		return order_state_description;
	}

	public void setOrder_state_description(String order_state_description) {
		this.order_state_description = order_state_description;
	}

}
