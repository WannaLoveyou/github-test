package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.DeliveryType;
import com.qian.vo.Field;

@Component
public class DeliveryTypeDaoImpl extends HBaseDao<DeliveryType> {

	public List<DeliveryType> getPageList(int page, int rows) {
		return selectFromHQL(" from DeliveryType", (page - 1) * rows, rows);
	}

	public long getTotalNum() {
		return getTotalFromHQL(" from DeliveryType");
	}

	public void delById(int id) {
		Field field = new Field();
		field.addInt(id);
		delete(" delete from DeliveryType where id=?", field);
	}

	public void delByIds(String[] ids) {

		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete DeliveryType as d where d.id = ?", afield);

	}

	public List<DeliveryType> getAllList() {
		return selectFromHQL(" from DeliveryType");
	}

	public List<DeliveryType> getMyDeliveryType(int deliveryTypeId) {

		Field field = new Field();
		field.addInt(deliveryTypeId);

		return selectFromHQL(" from DeliveryType where id = ?", field);
	}
}
