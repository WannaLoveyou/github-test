package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2017-10-13 上午9:15:17
 * @Description
 */
@Entity
@Table(name = "invoice_record_state")
public class InvoiceRecordState {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 50, nullable = false)
	private String state_description;

	public InvoiceRecordState() {

	}

	public InvoiceRecordState(int id) {
		this.id = id;
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
	 * @return the state_description
	 */
	public String getState_description() {
		return state_description;
	}

	/**
	 * @param state_description
	 *            the state_description to set
	 */
	public void setState_description(String state_description) {
		this.state_description = state_description;
	}

}
