package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.AirBottleStateDaoImpl;
import com.qian.entity.AirBottleState;
import com.qian.service.AirBottleStateService;

/**
 * @author Lu Kongwen
 * @version Create time：2016-5-16 上午11:45:01
 * @Description
 */
@Service
@Transactional
public class AirBottleStateServiceImpl implements AirBottleStateService {

	@Autowired
	private AirBottleStateDaoImpl airBottleStateDaoImpl;

	public List<AirBottleState> getAllList() {

		return airBottleStateDaoImpl.getAllList();
	}

}
