package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.WeChatLoginInfo;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-7 下午5:34:16
 * @Description
 */
@Component
public class WeChatLoginInfoDaoImpl extends HBaseDao<WeChatLoginInfo> {

	public WeChatLoginInfo findByOpenId(String openId) {

		Field field = new Field();
		field.addStr(openId);

		List<WeChatLoginInfo> result = selectFromHQL(" from WeChatLoginInfo where wechat_openid = ?", field);

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;

	}

	public WeChatLoginInfo findByClientId(Integer clientId) {
		
		Field field = new Field();
		field.addInt(clientId);

		List<WeChatLoginInfo> result = selectFromHQL(" from WeChatLoginInfo where clientInfo.id = ?", field);

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

}
