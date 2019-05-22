package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.OrderEmptyBottleInfo;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-28 上午9:46:17
 * @Description
 */
@Component
public class OrderEmptyBottleInfoDaoImpl extends HBaseDao<OrderEmptyBottleInfo> {

	
	public Long findOrderNumByOrder(int orderId) {

		Field field = new Field();
		field.addInt(orderId);

		return getTotalFromHQL(" from OrderEmptyBottleInfo where orderInfo.id = ?", field);

	}
	
	public List<OrderEmptyBottleInfo> findByOrderId(int orderId) {

		Field field = new Field();
		field.addInt(orderId);

		List<OrderEmptyBottleInfo> list = selectFromHQL(" from OrderEmptyBottleInfo where orderInfo.id = ?", field);

		return list;
	}

	public OrderEmptyBottleInfo findByOrderIdAndBottleId(Integer orderId, int bottleId) {

		Field field = new Field();
		field.addInt(orderId);
		field.addInt(bottleId);

		List<OrderEmptyBottleInfo> list = selectFromHQL(" from OrderEmptyBottleInfo where orderInfo.id = ? and airBottleInfo.id = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
}
