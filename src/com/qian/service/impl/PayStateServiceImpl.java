package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.PayStateDaoImpl;
import com.qian.entity.PayState;
import com.qian.service.PayStateService;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-15 上午11:07:59
 * @Description
 */
@Service
@Transactional
public class PayStateServiceImpl implements PayStateService{

	@Autowired
	private PayStateDaoImpl payStateDaoImpl;
	
	public List<PayState> getAllList() {

		return payStateDaoImpl.getAllList();
	}

	
}
