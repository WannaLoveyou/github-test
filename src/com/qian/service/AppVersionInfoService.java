package com.qian.service;

import com.qian.entity.AppVersionInfo;

/**
 * @author Lu Kongwen
 * @version Create time：2017-4-17 上午10:33:53
 * @Description
 */
public interface AppVersionInfoService {

	public AppVersionInfo getNowAppVersionInfo();

	public AppVersionInfo getNowAppManualVersionInfo();

}
