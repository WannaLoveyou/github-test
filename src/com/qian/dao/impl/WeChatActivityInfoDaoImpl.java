package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.WeChatActivityInfo;
import com.qian.util.SqlUtils;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-30 下午4:02:33
 * @Description
 */
@Component
public class WeChatActivityInfoDaoImpl  extends HBaseDao<WeChatActivityInfo>{

	public Long getTotalNum(List<String> l, Field filed) {
		
		return getTotalFromHQL(SqlUtils.initSearchConditionSql(l, "WeChatActivityInfo"),filed);

	}

	public List<WeChatActivityInfo> getWeChatActivityInfoPageList(List<String> l, Field filed, int page, int rows) {

		return selectFromHQL(SqlUtils.initSearchConditionSql(l, "WeChatActivityInfo"),filed, (page - 1) * rows, rows);
	}

}
