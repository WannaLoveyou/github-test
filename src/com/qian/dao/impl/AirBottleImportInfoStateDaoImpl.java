package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.AirBottleImportInfoState;

/**
 * @author Lu Kongwen
 * @version Create time：2017-12-22 下午2:55:32
 * @Description
 */
@Component
public class AirBottleImportInfoStateDaoImpl extends HBaseDao<AirBottleImportInfoState> {

	public List<AirBottleImportInfoState> getAllList() {
		return selectFromHQL(" from AirBottleImportInfoState");
	}

}
