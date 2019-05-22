package com.qian.dao.impl;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.OrderInfo;
import com.qian.entity.User;
import com.qian.vo.Field;

@Component
public class DeliveryManDaoImpl extends HBaseDao<OrderInfo>{

	public List<OrderInfo> getDeliveryHistoryPageList(User user,int page, int rows) {	
	
		Field field = new Field();
		field.addInt(user.getId());
		return selectFromHQL(" from OrderInfo where delivery_man.id = ?",field,(page - 1) * rows, rows);
	}

	public long getDeliveryHistoryTotalNum(User user) {
		Field field = new Field();
		field.addInt(user.getId());
		return getTotalFromHQL(" from OrderInfo where delivery_man.id = ?",field);
	}
	
	
}
