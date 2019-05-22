package com.qian.app.entity;

import com.qian.entity.User;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-27 上午9:23:16
 * @Description
 */
public class AppUserInfo {

	private int userId; // 用户ID

	private String userName; // 账号名

	private String fullName; // 用户名

	public AppUserInfo(User user) {

		this.userId = user.getId();
		this.userName = user.getUsername();
		this.fullName = user.getFull_name();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
