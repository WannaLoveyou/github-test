package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.StoreCustomAirBottlePriceInfo;
import com.qian.util.SqlUtils;
import com.qian.vo.Field;

@Component
public class StoreCustomAirBottlePriceInfoDaoImpl extends HBaseDao<StoreCustomAirBottlePriceInfo> {

	public List<StoreCustomAirBottlePriceInfo> getAllList() {

		List<StoreCustomAirBottlePriceInfo> result = selectFromHQL(" from StoreCustomAirBottlePriceInfo");

		return result;
	}

	public List<StoreCustomAirBottlePriceInfo> getByListSecondCategoryId(Integer secondCategoryId) {

		Field field = new Field();
		field.addInt(secondCategoryId);

		List<StoreCustomAirBottlePriceInfo> result = selectFromHQL(" from StoreCustomAirBottlePriceInfo where secondCategory.id = ?", field);

		return result;
	}

	public StoreCustomAirBottlePriceInfo findByairBottleTypeIdAndSecondCategoryId(int bottleTypeId, int secondCategoryId) {

		Field field = new Field();
		field.addInt(bottleTypeId);
		field.addInt(secondCategoryId);

		List<StoreCustomAirBottlePriceInfo> result = selectFromHQL(" from StoreCustomAirBottlePriceInfo where airBottleType.id = ? and secondCategory.id = ?",
				field);

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public Long getTotalNum(List<String> l, Field filed) {

		return getTotalFromHQL(SqlUtils.initSearchConditionSql(l, "StoreCustomAirBottlePriceInfo"), filed);

	}

	public List<StoreCustomAirBottlePriceInfo> getPageList(List<String> l, Field filed, int page, int rows) {

		return selectFromHQL(SqlUtils.initSearchConditionSql(l, "StoreCustomAirBottlePriceInfo"), filed, (page - 1) * rows, rows);

	}

	public Integer findIdBySecondCategoryIdAndAirBottleTypeId(int secondCategoryId, int airBottleTypeId) {

		Field field = new Field();
		field.addInt(secondCategoryId);
		field.addInt(airBottleTypeId);

		@SuppressWarnings("unchecked")
		List<Integer> result = selectFromHQLByClass("select id from StoreCustomAirBottlePriceInfo where secondCategory.id = ? and airBottleType.id = ?", field,
				Integer.class);

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public void delByIds(String[] ids) {
		
		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete StoreCustomAirBottlePriceInfo as a where a.id = ?", afield);
	}

}
