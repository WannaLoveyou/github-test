package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.code.WeiXinBuyCode;
import com.qian.dao.HBaseDao;
import com.qian.entity.AirBottleType;
import com.qian.vo.Field;

@Component
public class AirBottleTypeDaoImpl extends HBaseDao<AirBottleType> {

	public List<AirBottleType> getPageList(int page, int rows) {
		return selectFromHQL(" from AirBottleType", (page - 1) * rows, rows);
	}

	public long getTotalNum() {
		return getTotalFromHQL(" from AirBottleType");
	}

	public void delById(int id) {
		Field field = new Field();
		field.addInt(id);
		delete(" delete from air_bottle_type where id=?", field);
	}

	public void delByIds(String[] ids) {

		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete AirBottleType as a where a.id = ?", afield);

	}

	public List<AirBottleType> getAllList() {
		return selectFromHQL(" from AirBottleType");
	}

	public AirBottleType getByAirBottleTypeName(String airBottleTypeName) {

		Field field = new Field();
		field.addStr(airBottleTypeName);

		List<AirBottleType> result = selectFromHQL(" from AirBottleType where air_bottle_specifications = ?", field);

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<AirBottleType> getAllWeiXinList() {
		
		Field field = new Field();
		field.addInt(WeiXinBuyCode.ALLOW_BUY);
		return selectFromHQL(" from AirBottleType where weixin_buy = ?", field);
	}

}
