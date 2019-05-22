package com.qian.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.PayType;
import com.qian.vo.Field;

@Component
public class PayTypeDaoImpl extends HBaseDao<PayType> {
	public List<PayType> getPageList(int page, int rows) {
		return selectFromHQL(" from PayType", (page - 1) * rows, rows);
	}

	
	public long getTotalNum() {
		return getTotalFromHQL(" from PayType");
	}
	
	
	public void delById(int id) {
		Field field = new Field();
		field.addInt(id);
		delete(" delete from PayType where id=?", field);
	}

	public void delByIds(String[] ids) {

		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete PayType as p where p.id = ?", afield);

	}


	public List<PayType> getAllList() {
		return selectFromHQL(" from PayType");
	}
	
	
	
}