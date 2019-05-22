package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.OrderAirBottleInfo;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-28 上午9:46:17
 * @Description
 */
@Component
public class OrderAirBottleInfoDaoImpl extends HBaseDao<OrderAirBottleInfo> {

	public Long findOrderNumByOrder(int orderId) {

		Field field = new Field();
		field.addInt(orderId);

		return getTotalFromHQL(" from OrderAirBottleInfo where orderInfo.id = ?", field);

	}

	public List<OrderAirBottleInfo> findByOrderId(int orderId) {

		Field field = new Field();
		field.addInt(orderId);

		return selectFromHQL(" from OrderAirBottleInfo where orderInfo.id = ?", field);

	}

	public OrderAirBottleInfo findByClinetIdAndAirBottleCode(int clinetId, String bottleCode) {

		Field field = new Field();
		field.addInt(clinetId);
		field.addStr(bottleCode);

		List<OrderAirBottleInfo> list = selectFromHQL(
				" from OrderAirBottleInfo where orderInfo.clientInfo.id=? and airBottleInfo.air_bottle_code = ? order by id desc", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
	
	public OrderAirBottleInfo findByClinetIdAndBottleId(int clinetId, int bottleId) {

		Field field = new Field();
		field.addInt(clinetId);
		field.addInt(bottleId);

		List<OrderAirBottleInfo> list = selectFromHQL(
				" from OrderAirBottleInfo where orderInfo.clientInfo.id=? and airBottleInfo.id = ? order by id desc", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public OrderAirBottleInfo findByOrderIdAndBottleId(Integer orderId, int bottleId) {

		Field field = new Field();
		field.addInt(orderId);
		field.addInt(bottleId);

		List<OrderAirBottleInfo> list = selectFromHQL(" from OrderAirBottleInfo where orderInfo.id = ? and airBottleInfo.id = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
}
