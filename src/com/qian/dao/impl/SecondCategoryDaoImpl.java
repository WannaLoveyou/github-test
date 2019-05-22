package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.SecondCategory;
import com.qian.vo.Field;

@Component
public class SecondCategoryDaoImpl extends HBaseDao<SecondCategory> {

	public List<SecondCategory> getAllList() {
		return selectFromHQL(" from SecondCategory");
	}

	public Long getTotalNum() {

		return getTotalFromHQL(" from SecondCategory");
	}

	public List<SecondCategory> getPageList(int page, int rows) {

		return selectFromHQL(" from SecondCategory", (page - 1) * rows, rows);
	}

	public void delById(int id) {
		Field field = new Field();
		field.addInt(id);
		delete(" delete from second_category where id=?", field);
	}

	public void delByIds(String[] ids) {

		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete SecondCategory as s where s.id = ?", afield);

	}

	public List<SecondCategory> getListByFirstId(int id) {
		Field filed = new Field();
		filed.addInt(id);

		String hql = "from SecondCategory where first_category_id=?";

		return selectFromHQL(hql, filed);

	}

}
