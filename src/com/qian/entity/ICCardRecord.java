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
 * @version Create time：2016-10-27 上午10:16:06
 * @Description
 */
@Entity
@Table(name = "ic_card_record")
public class ICCardRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "client_id")
	private ClientInfo clientInfo;

	@Column(length = 50)
	private String card_code;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "operator_id")
	private User operator;

	@Column()
	private Date create_time;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "state_id")
	private ICCardRecordState state;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public ICCardRecordState getState() {
		return state;
	}

	public void setState(ICCardRecordState state) {
		this.state = state;
	}

	public String getCard_code() {
		return card_code;
	}

	public void setCard_code(String card_code) {
		this.card_code = card_code;
	}

}
