package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.code.AppUpdateVersionCode;
import com.qian.dao.HBaseDao;
import com.qian.entity.AppVersionInfo;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-4-17 上午11:02:54
 * @Description
 */
@Component
public class AppVersionInfoDaoImpl extends HBaseDao<AppVersionInfo> {

	public AppVersionInfo getNowAppVersionInfo() {

		Field field = new Field();
		field.addInt(AppUpdateVersionCode.AUTO); // 自动更新版本

		List<AppVersionInfo> list = selectFromHQL(" from AppVersionInfo where update_state = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public AppVersionInfo getNowAppManualVersionInfo() {

		Field field = new Field();
		field.addInt(AppUpdateVersionCode.MANUAL); // 手动更新版本

		List<AppVersionInfo> list = selectFromHQL(" from AppVersionInfo where update_state = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

}
