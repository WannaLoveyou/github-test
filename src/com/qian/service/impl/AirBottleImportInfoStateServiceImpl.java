package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.AirBottleImportInfoStateDaoImpl;
import com.qian.entity.AirBottleImportInfoState;
import com.qian.service.AirBottleImportInfoStateService;

/**
 * @author Lu Kongwen
 * @version Create time：2017-12-22 下午2:56:40
 * @Description
 */
@Service
@Transactional
public class AirBottleImportInfoStateServiceImpl implements AirBottleImportInfoStateService {

	@Autowired
	private AirBottleImportInfoStateDaoImpl airBottleImportInfoStateDaoImpl;

	@Override
	public List<AirBottleImportInfoState> getAllList() {
		
		return airBottleImportInfoStateDaoImpl.getAllList();
	}
	
	
}
