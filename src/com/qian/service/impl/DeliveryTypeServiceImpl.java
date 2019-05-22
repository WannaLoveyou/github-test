package com.qian.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.DeliveryTypeDaoImpl;
import com.qian.entity.DeliveryType;
import com.qian.service.DeliveryTypeService;

@Service
@Transactional
public class DeliveryTypeServiceImpl implements DeliveryTypeService{
	@Autowired
	private DeliveryTypeDaoImpl deliveryTypeDaoImpl;
	
	public List<DeliveryType> getPageList(int page, int rows) {
		
		return deliveryTypeDaoImpl.getPageList(page, rows);
	}

	public long getTotalNum() {
		
		return deliveryTypeDaoImpl.getTotalNum();
	}

	public int add(DeliveryType deliveryType) {
		
		return deliveryTypeDaoImpl.insert(deliveryType);
	}

	public int edit(DeliveryType deliveryType) {
		
		return deliveryTypeDaoImpl.update(deliveryType);
	}

	public void delByIds(String[] ids) {
		
		deliveryTypeDaoImpl.delByIds(ids);
	}

	public List<DeliveryType> getAllList() {
		return deliveryTypeDaoImpl.getAllList();
	}

	public List<DeliveryType> getMyDeliveryType(int deliveryTypeId) {
		
		return deliveryTypeDaoImpl.getMyDeliveryType(deliveryTypeId);
	}

}
