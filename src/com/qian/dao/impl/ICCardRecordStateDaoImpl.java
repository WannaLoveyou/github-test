package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.ICCardRecordState;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-28 下午5:50:55
 * @Description
 */
@Component
public class ICCardRecordStateDaoImpl extends HBaseDao<ICCardRecordState> {

	public List<ICCardRecordState> getAllList() {

		return selectFromHQL(" from ICCardRecordState");
	}
	
}
