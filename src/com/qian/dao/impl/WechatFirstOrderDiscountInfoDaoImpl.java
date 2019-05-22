package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.WechatFirstOrderDiscountInfo;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-20 下午5:15:33
 * @Description
 */
@Component
public class WechatFirstOrderDiscountInfoDaoImpl extends HBaseDao<WechatFirstOrderDiscountInfo> {


	public WechatFirstOrderDiscountInfo findByOpenId(String openid) {

		Field field = new Field();
		field.addStr(openid);
		
		List<WechatFirstOrderDiscountInfo> list = selectFromHQL(" from WechatFirstOrderDiscountInfo where openid = ?", field);
		
		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
}
