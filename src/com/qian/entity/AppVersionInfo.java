package com.qian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lu Kongwen
 * @version Create time：2017-4-17 上午10:31:55
 * @Description
 */
@Entity
@Table(name = "app_version_imformation")
public class AppVersionInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 225)
	private String update_message;

	@Column(length = 50)
	private String url;

	@Column()
	private int version_code;

	@Column()
	private int update_state; // 0强制更新，1手动更新

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUpdate_message() {
		return update_message;
	}

	public void setUpdate_message(String update_message) {
		this.update_message = update_message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getVersion_code() {
		return version_code;
	}

	public void setVersion_code(int version_code) {
		this.version_code = version_code;
	}

	public int getUpdate_state() {
		return update_state;
	}

	public void setUpdate_state(int update_state) {
		this.update_state = update_state;
	}

}
