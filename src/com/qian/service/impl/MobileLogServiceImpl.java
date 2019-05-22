package com.qian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.MobileLogDaoImpl;
import com.qian.entity.MobileLog;
import com.qian.service.MobileLogService;

/**
 * @author Lu Kongwen
 * @version Create time：2016-9-23 上午9:18:10
 * @Description
 */
@Service
@Transactional
public class MobileLogServiceImpl implements MobileLogService {

	@Autowired
	private MobileLogDaoImpl mobileLogDaoImpl;

	public void add(MobileLog mobileLog) {

		mobileLogDaoImpl.insert(mobileLog);
	}

}
