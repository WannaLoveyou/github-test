package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.InspectionOrderItemInfo;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-6-2 下午7:22:49
 * @Description
 */
@Component
public class InspectionOrderItemInfoDaoImpl extends
		HBaseDao<InspectionOrderItemInfo> {

	public List<InspectionOrderItemInfo> getItemsByOrderId(int orderId) {
		return selectFromHQL(
				"from InspectionOrderItemInfo where inspectionOrderInfo.id = ?",
				new Field().addInt(orderId));
	}
}
