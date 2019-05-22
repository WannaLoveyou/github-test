package com.qian.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.PayTypeDaoImpl;
import com.qian.entity.PayType;
import com.qian.service.PayTypeService;

@Service
@Transactional
public class PayTypeServiceImpl implements PayTypeService{
	
	@Autowired
	private PayTypeDaoImpl payTypeDaoImpl;
	
	public List<PayType> getPageList(int page, int rows) {
		
		return payTypeDaoImpl.getPageList(page, rows);
	}

	public long getTotalNum() {

		return payTypeDaoImpl.getTotalNum();
	}

	public int add(PayType payType) {
		
		return payTypeDaoImpl.insert(payType);
	}

	public void delByIds(String[] ids) {
		
		payTypeDaoImpl.delByIds(ids);
	}

	public int edit(PayType payType) {
		return payTypeDaoImpl.update(payType);
	}

	public List<PayType> getAllList() {
		return payTypeDaoImpl.getAllList();
	}

}
