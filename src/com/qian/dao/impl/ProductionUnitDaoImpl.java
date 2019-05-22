package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.ProductionUnit;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-6-16 下午2:55:54
 * @Description
 */
@Component
public class ProductionUnitDaoImpl extends HBaseDao<ProductionUnit> {

	public List<ProductionUnit> getPageList(int page, int rows) {
		
		return selectFromHQL(" from ProductionUnit", (page - 1) * rows, rows);

	}

	public long getTotalNum() {
		
		return getTotalFromHQL(" from ProductionUnit");
	}

	public void delByIds(String[] ids) {
		
		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete ProductionUnit as b where b.id = ?", afield);
	}

	public List<ProductionUnit> getAllList() {
		
		return selectFromHQL(" from ProductionUnit");
	}

	
	
	
}
