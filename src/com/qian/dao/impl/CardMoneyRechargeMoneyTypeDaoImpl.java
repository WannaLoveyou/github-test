package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.CardMoneyRechargeMoneyType;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-8-4 上午11:39:37
 * @Description
 */
@Component
public class CardMoneyRechargeMoneyTypeDaoImpl  extends HBaseDao<CardMoneyRechargeMoneyType> {

	public void delByIds(String[] ids) {
		
		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete CardMoneyRechargeMoneyType as b where b.id = ?", afield);
	}

	public long getTotalNum() {
		
		return getTotalFromHQL(" from CardMoneyRechargeMoneyType");
	}

	public List<CardMoneyRechargeMoneyType> getPageList(int page, int rows) {

		return selectFromHQL(" from CardMoneyRechargeMoneyType", (page - 1) * rows, rows);
	}

	public List<CardMoneyRechargeMoneyType> getAllList() {
		
		return selectFromHQL(" from CardMoneyRechargeMoneyType");
	}

}
