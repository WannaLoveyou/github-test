package com.qian.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.PartType;
import com.qian.vo.Field;

@Component
public class PartTypeDaoImpl extends HBaseDao<PartType> {
	public List<PartType> getPageList(int page, int rows) {
		return selectFromHQL(" from PartType", (page - 1) * rows, rows);
	}

	public long getTotalNum() {
		return getTotalFromHQL(" from PartType");
	}
	

	public void delById(int id) {
		Field field = new Field();
		field.addInt(id);
		delete(" delete from PartType where id=?", field);
	}

	public void delByIds(String[] ids) {

		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete PartType as b where b.id = ?", afield);

	}

	public List<PartType> getAllList() {
		return selectFromHQL(" from PartType");
	}
	
}
