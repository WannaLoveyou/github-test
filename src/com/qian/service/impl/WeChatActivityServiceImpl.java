package com.qian.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.WeChatActivityInfoDaoImpl;
import com.qian.entity.WeChatActivityInfo;
import com.qian.service.WeChatActivityService;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-30 下午4:05:08
 * @Description
 */
@Service
@Transactional
public class WeChatActivityServiceImpl implements WeChatActivityService {

	@Autowired
	private WeChatActivityInfoDaoImpl weChatActivityInfoDaoImpl;

	public CommonCode addActivityInfo(String telNumber, String name) {

		WeChatActivityInfo weChatActivityInfo = new WeChatActivityInfo();

		weChatActivityInfo.setTel_number(telNumber);
		weChatActivityInfo.setName(name);
		weChatActivityInfo.setCreate_time(new Date());

		weChatActivityInfoDaoImpl.insert(weChatActivityInfo);

		return CommonCode.OK;
	}

	public Long getTotalNum(List<String> l, Field filed) {

		return weChatActivityInfoDaoImpl.getTotalNum(l, filed);
	}

	public List<WeChatActivityInfo> getWeChatActivityInfoPageList(List<String> l, Field filed, int page, int rows) {

		return weChatActivityInfoDaoImpl.getWeChatActivityInfoPageList(l, filed, page, rows);
	}

}
