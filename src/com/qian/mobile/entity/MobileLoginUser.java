package com.qian.mobile.entity;

import java.util.List;

import com.qian.entity.User;

/**
 * @author Lu Kongwen
 * @version Create time：2016-5-5 下午3:54:34
 * @Description
 */
public class MobileLoginUser {

	private int user_id;

	private String username;

	private String full_name;

	private List<MobileModule> mobileModules;

	public MobileLoginUser(User user, List<MobileModule> mobileModules) {

		this.user_id = user.getId();
		this.username = user.getUsername();
		this.full_name = user.getFull_name();
		this.mobileModules = mobileModules;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public List<MobileModule> getMobileModules() {
		return mobileModules;
	}

	public void setMobileModules(List<MobileModule> mobileModules) {
		this.mobileModules = mobileModules;
	}

}
