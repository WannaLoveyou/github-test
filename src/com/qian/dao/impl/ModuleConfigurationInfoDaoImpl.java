package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.ModuleConfigurationInfo;
import com.qian.util.SqlUtils;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-4-21 下午2:24:41
 * @Description
 */
@Component
public class ModuleConfigurationInfoDaoImpl  extends HBaseDao<ModuleConfigurationInfo> {

	public Long getTotalNum(List<String> l, Field filed) {
		return  getTotalFromHQL(SqlUtils.initSearchConditionSql(l, getEntityName()), filed);
	}

	public List<ModuleConfigurationInfo> getPageList(List<String> l, Field filed, int page, int rows) {
		return selectFromHQL(SqlUtils.initSearchConditionSql(l, getEntityName()), filed, (page - 1) * rows, rows);
	}

}
