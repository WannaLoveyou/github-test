package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.PhoneRecord;
import com.qian.util.SqlUtils;
import com.qian.vo.Field;

@Component
public class PhoneRecordDaoImpl extends HBaseDao<PhoneRecord> {

	// 按条件分页查找 排序
	public List<PhoneRecord> getPageListOrderByCallIn(List<String> l, Field field, int page, int rows) {
		return selectFromHQL(SqlUtils.initSearchConditionSql(l, getEntityName())+" order by callInTime desc", field, (page - 1) * rows, rows);
	}
}
