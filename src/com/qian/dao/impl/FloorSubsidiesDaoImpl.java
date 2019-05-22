package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.FloorSubsidies;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-9 上午10:25:51
 * @Description
 */
@Component
public class FloorSubsidiesDaoImpl extends HBaseDao<FloorSubsidies> {

	public List<FloorSubsidies> getPageList(int page, int rows) {
		return selectFromHQL(" from FloorSubsidies", (page - 1) * rows, rows);
	}

	public long getTotalNum() {
		return getTotalFromHQL(" from FloorSubsidies");
	}

	public void delById(int id) {
		Field field = new Field();
		field.addInt(id);
		delete(" delete from FloorSubsidies where id=?", field);
	}

	public void delByIds(String[] ids) {

		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete FloorSubsidies as f where f.id = ?", afield);

	}

	public List<FloorSubsidies> getAllList() {

		return selectFromHQL(" from FloorSubsidies");
	}

	public FloorSubsidies findByMoney(double floor_subsidies_money) {

		Field field = new Field();
		field.addDouble(floor_subsidies_money);
		List<FloorSubsidies> result = selectFromHQL("from FloorSubsidies where floor_subsidies_money = ?", field);

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}
}
