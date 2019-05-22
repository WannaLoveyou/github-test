package com.qian.service;
import java.util.List;

import com.qian.entity.DeliveryType;

public interface DeliveryTypeService {
	public List<DeliveryType> getPageList(int page, int rows);

	public long getTotalNum();

	public int add(DeliveryType deliveryType);

	public int edit(DeliveryType deliveryType);
	
	public void delByIds(String ids[]);

	public List<DeliveryType> getAllList();

	public List<DeliveryType> getMyDeliveryType(int deliveryTypeId);
}
