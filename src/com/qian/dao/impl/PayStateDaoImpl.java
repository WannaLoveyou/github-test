package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.PayState;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-15 上午11:09:02
 * @Description
 */
@Component
public class PayStateDaoImpl extends HBaseDao<PayState> {

	public List<PayState> getAllList() {
		return selectFromHQL(" from PayState");
	}
}
