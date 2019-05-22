package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qian.dao.impl.OrderInfoStateDaoImpl;
import com.qian.entity.OrderInfoState;
import com.qian.service.OrderInfoStateService;

@Service
public class OrderInfoStateServiceImpl implements OrderInfoStateService{
	
	@Autowired
	private OrderInfoStateDaoImpl orderInfoStateDaoImpl;
	
	public List<OrderInfoState> getAllList() {
		
		return orderInfoStateDaoImpl.getAllList();
	}

}
