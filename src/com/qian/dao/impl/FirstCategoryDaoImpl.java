package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.FirstCategory;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-4-20 下午3:34:40
 * @Description
 */
@Component
public class FirstCategoryDaoImpl extends HBaseDao<FirstCategory> {

	public List<FirstCategory> getPageList(int page, int rows) {
		return selectFromHQL(" from FirstCategory", (page - 1) * rows, rows);
	}

	public long getTotalNum() {
		return getTotalFromHQL(" from FirstCategory");
	}

	public void delById(int id) {
		Field field = new Field();
		field.addInt(id);
		delete(" delete from first_category where id=?", field);
	}

	public void delByIds(String[] ids) {

		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete FirstCategory as f where f.id = ?", afield);

	}

	public List<FirstCategory> getAllList() {
		return selectFromHQL(" from FirstCategory");
	}

}
