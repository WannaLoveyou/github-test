package com.qian.service;

import com.qian.entity.OrderAirBottleInfo;

/**
 * @author Lu Kongwen
 * @version Create time：2017-4-24 上午9:59:57
 * @Description
 */
public interface OrderAirBottleInfoService {

	public OrderAirBottleInfo findByOrderIdAndBottleId(Integer orderId, int bottleId);

}
