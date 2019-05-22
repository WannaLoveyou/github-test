package com.qian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.WechatFirstOrderDiscountInfoDaoImpl;
import com.qian.entity.WechatFirstOrderDiscountInfo;
import com.qian.service.WechatFirstOrderDiscountInfoService;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-20 下午5:56:07
 * @Description
 */
@Service
@Transactional
public class WechatFirstOrderDiscountInfoServiceImpl  implements WechatFirstOrderDiscountInfoService {

	
	@Autowired
	private WechatFirstOrderDiscountInfoDaoImpl wechatFirstOrderDiscountInfoDaoImpl;

	@Override
	public WechatFirstOrderDiscountInfo findByOpenId(String openid) {
		
		return wechatFirstOrderDiscountInfoDaoImpl.findByOpenId(openid);
	}
}
