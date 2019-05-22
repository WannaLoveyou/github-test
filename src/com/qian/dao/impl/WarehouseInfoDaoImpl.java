package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.code.DisabledStateCode;
import com.qian.dao.HBaseDao;
import com.qian.entity.WarehouseInfo;
import com.qian.util.FieldUtils;
import com.qian.util.SqlUtils;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-2-18 下午5:25:29
 * @Description
 */
@Component
public class WarehouseInfoDaoImpl extends HBaseDao<WarehouseInfo> {

	public Long getTotalNum(List<String> l, Field field) {

		return getTotalFromHQL(SqlUtils.initSearchConditionSql(l, getEntityName()), field);
	}

	public List<WarehouseInfo> getPageList(List<String> l, Field field, int page, int rows) {

		l.add("disabled_state = " + DisabledStateCode.NOT_DISABLE);

		return selectFromHQL(SqlUtils.initSearchConditionSql(l, getEntityName()), field, (page - 1) * rows, rows);
	}

	public void delByIds(String[] idList) {

		update(SqlUtils.initDelEntitySql(getEntityName()), FieldUtils.getDelFields(idList));

	}

	public WarehouseInfo findWarehouseInfoByCode(String warehouseCode) {

		Field field = new Field();
		field.addStr(warehouseCode);

		List<WarehouseInfo> list = selectFromHQL(" from WarehouseInfo where warehouse_code = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public List<WarehouseInfo> getAllList() {

		Field field = new Field();

		List<WarehouseInfo> list = selectFromHQL(" from WarehouseInfo", field);

		return list;
	}

}
