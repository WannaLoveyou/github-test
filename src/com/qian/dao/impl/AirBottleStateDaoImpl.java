package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.AirBottleState;

/**
 * @author Lu Kongwen
 * @version Create time：2016-5-16 上午11:42:56
 * @Description
 */
@Component
public class AirBottleStateDaoImpl extends HBaseDao<AirBottleState>{

	public List<AirBottleState> getAllList() {
		return selectFromHQL(" from AirBottleState");
	}
}
