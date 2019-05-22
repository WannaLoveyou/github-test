package com.qian.service;

import java.util.List;

import com.qian.entity.WeChatActivityInfo;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-30 下午4:04:53
 * @Description
 */
public interface WeChatActivityService {

	public CommonCode addActivityInfo(String telNumber, String name);

	public Long getTotalNum(List<String> l, Field filed);

	public List<WeChatActivityInfo> getWeChatActivityInfoPageList(List<String> l, Field filed, int page, int rows);

}
