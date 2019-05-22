package com.qian.mobile.entity;

import java.util.List;

import com.qian.entity.User;

/**
 * @author Lu Kongwen
 * @version Create time：2017-10-31 下午2:49:18
 * @Description
 */
public class MobileFamilyCheckOrder {

	private Integer orderId;

	private Integer clientId;

	private Integer userId;

	private List<String> photoUrls;

	private User user;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
