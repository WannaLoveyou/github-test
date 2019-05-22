package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.InvoiceRecord;
import com.qian.util.SqlUtils;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-10-13 下午4:23:35
 * @Description
 */
@Component
public class InvoiceRecordDaoImpl  extends HBaseDao<InvoiceRecord> {

	public InvoiceRecord findByOrderId(int orderId){
		
		Field field = new Field();
		field.addInt(orderId);

		List<InvoiceRecord> result = selectFromHQL(" from InvoiceRecord where order_id = ?", field);

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}
	
	public Long getTotalNum(List<String> l, Field filed) {
		return  getTotalFromHQL(SqlUtils.initSearchConditionSql(l, getEntityName()), filed);
	}

	public List<InvoiceRecord> getPageList(List<String> l, Field filed, int page, int rows) {
		return selectFromHQL(SqlUtils.initSearchConditionSql(l, getEntityName()), filed, (page - 1) * rows, rows);
	}

}
