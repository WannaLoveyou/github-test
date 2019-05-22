package com.qian.mobile.entity;

import com.qian.entity.AppVersionInfo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-7-5 下午3:11:00
 * @Description
 */
public class MobileAppVersionInfo {

	private String updateMessage;

	private String url;

	private int versionCode;

	public MobileAppVersionInfo(AppVersionInfo appVersionInfo) {

		this.updateMessage = appVersionInfo.getUpdate_message();
		this.url = appVersionInfo.getUrl();
		this.versionCode = appVersionInfo.getVersion_code();

	}

	public String getUpdateMessage() {
		return updateMessage;
	}

	public void setUpdateMessage(String updateMessage) {
		this.updateMessage = updateMessage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

}
