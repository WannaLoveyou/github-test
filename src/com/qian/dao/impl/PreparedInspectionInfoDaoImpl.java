package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.PreparedInspectionInfo;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-4-12 下午4:53:37
 * @Description
 */
@Component
public class PreparedInspectionInfoDaoImpl extends HBaseDao<PreparedInspectionInfo> {

	public PreparedInspectionInfo findByAirBottleId(int airBottleId) {

		Field field = new Field();
		field.addInt(airBottleId);
		List<PreparedInspectionInfo> list = selectFromHQL(" from PreparedInspectionInfo where airBottleInfo.id = ? ", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
}