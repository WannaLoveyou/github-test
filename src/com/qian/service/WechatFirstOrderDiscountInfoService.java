package com.qian.service;

import com.qian.entity.WechatFirstOrderDiscountInfo;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-20 下午5:21:34
 * @Description
 */
public interface WechatFirstOrderDiscountInfoService {

	public WechatFirstOrderDiscountInfo findByOpenId(String openid);

	
}
