package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.DetectionUnit;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-6-16 下午3:06:02
 * @Description
 */
@Component
public class DetectionUnitDaoImpl extends HBaseDao<DetectionUnit> {

	public List<DetectionUnit> getPageList(int page, int rows) {
		
		return selectFromHQL(" from DetectionUnit", (page - 1) * rows, rows);

	}

	public long getTotalNum() {
		
		return getTotalFromHQL(" from DetectionUnit");
	}

	public void delByIds(String[] ids) {
		
		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete DetectionUnit as b where b.id = ?", afield);
	}

	public List<DetectionUnit> getAllList() {
		
		return selectFromHQL(" from DetectionUnit");
	}

}
