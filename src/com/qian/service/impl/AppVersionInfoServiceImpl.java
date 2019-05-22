package com.qian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.AppVersionInfoDaoImpl;
import com.qian.entity.AppVersionInfo;
import com.qian.service.AppVersionInfoService;

/**
 * @author Lu Kongwen
 * @version Create time：2017-4-17 上午10:34:05
 * @Description
 */
@Service
@Transactional
public class AppVersionInfoServiceImpl implements AppVersionInfoService {

	@Autowired
	private AppVersionInfoDaoImpl appVersionInfoDaoImpl;

	public AppVersionInfo getNowAppVersionInfo() {

		return appVersionInfoDaoImpl.getNowAppVersionInfo();
	}

	public AppVersionInfo getNowAppManualVersionInfo() {

		return appVersionInfoDaoImpl.getNowAppManualVersionInfo();
	}

}
