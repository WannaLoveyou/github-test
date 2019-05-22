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
 * @version Create time：2016-11-7 上午11:37:39
 * @Description
 */
@Entity
@Table(name = "wechat_login_imformation")
public class WeChatLoginInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "client_id")
	private ClientInfo clientInfo;

	@Column(length = 50)
	private String wechat_openid;

	@Column(length = 50)
	private String password;

	@Column(length = 50)
	private String login_mobile_tel_number;

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

	public String getWechat_openid() {
		return wechat_openid;
	}

	public void setWechat_openid(String wechat_openid) {
		this.wechat_openid = wechat_openid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin_mobile_tel_number() {
		return login_mobile_tel_number;
	}

	public void setLogin_mobile_tel_number(String login_mobile_tel_number) {
		this.login_mobile_tel_number = login_mobile_tel_number;
	}

}
