package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.OrderInfoState;
@Component
public class OrderInfoStateDaoImpl extends HBaseDao<OrderInfoState>{
	public List<OrderInfoState> getAllList(){
		return selectFromHQL("from OrderInfoState");
	};
	

}
