package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.AirBottleImportInfo;
import com.qian.util.SqlUtils;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-12-15 下午4:42:43
 * @Description
 */
@Component
public class AirBottleImportInfoDaoImpl extends HBaseDao<AirBottleImportInfo> {

	public Long getTotalNum(List<String> l, Field filed) {
		return getTotalFromHQL(SqlUtils.initSearchConditionSql(l, getEntityName()), filed);

	}

	public List<AirBottleImportInfo> getPageList(List<String> l, Field filed, int page, int rows) {
		return selectFromHQL(SqlUtils.initSearchConditionSql(l, getEntityName()), filed, (page - 1) * rows, rows);
	}
	
	public List<AirBottleImportInfo> getAllList(List<String> l, Field filed) {
		return selectFromHQL(SqlUtils.initSearchConditionSql(l, getEntityName()), filed);
	}

	public void delByIds(String[] ids) {

		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete " + getEntityName() +" as a where a.id = ?", afield);
	}
}
