package com.qian.service;

import com.qian.entity.WeChatLoginInfo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-8 上午9:01:55
 * @Description
 */
public interface WeChatLoginInfoService {

	public WeChatLoginInfo findByOpenId(String openId);

}
