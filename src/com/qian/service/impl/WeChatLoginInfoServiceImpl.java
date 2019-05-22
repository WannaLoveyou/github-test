package com.qian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.WeChatLoginInfoDaoImpl;
import com.qian.entity.WeChatLoginInfo;
import com.qian.service.WeChatLoginInfoService;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-8 上午9:02:14
 * @Description
 */
@Service
@Transactional
public class WeChatLoginInfoServiceImpl implements WeChatLoginInfoService {

	@Autowired
	private WeChatLoginInfoDaoImpl weChatLoginInfoDaoImpl;

	public WeChatLoginInfo findByOpenId(String openId) {

		return weChatLoginInfoDaoImpl.findByOpenId(openId);
	}

}
