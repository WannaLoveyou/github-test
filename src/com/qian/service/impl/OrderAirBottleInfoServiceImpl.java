package com.qian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.OrderAirBottleInfoDaoImpl;
import com.qian.entity.OrderAirBottleInfo;
import com.qian.service.OrderAirBottleInfoService;

/**
 * @author Lu Kongwen
 * @version Create time：2017-4-24 上午10:00:08
 * @Description
 */
@Service
@Transactional
public class OrderAirBottleInfoServiceImpl  implements OrderAirBottleInfoService {

	@Autowired
	private OrderAirBottleInfoDaoImpl orderAirBottleInfoDaoImpl;

	public OrderAirBottleInfo findByOrderIdAndBottleId(Integer orderId, int bottleId) {
		
		return orderAirBottleInfoDaoImpl.findByOrderIdAndBottleId(orderId,bottleId);
	}
	
	
}
