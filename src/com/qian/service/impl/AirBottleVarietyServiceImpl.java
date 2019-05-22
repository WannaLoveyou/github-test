package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.AirBottleVarietyDaoImpl;
import com.qian.entity.AirBottleVariety;
import com.qian.service.AirBottleVarietyService;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-27 下午6:33:02
 * @Description
 */
@Service
@Transactional
public class AirBottleVarietyServiceImpl  implements AirBottleVarietyService {

	@Autowired
	private AirBottleVarietyDaoImpl airBottleVarietyDaoImpl;

	@Override
	public List<AirBottleVariety> getAllList() {
		
		return airBottleVarietyDaoImpl.getAllList();
	}

}
