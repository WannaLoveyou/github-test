package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qian.dao.impl.RepairInfoStateDaoImpl;
import com.qian.entity.RepairInfoState;
import com.qian.service.RepairInfoStateService;

@Service
public class RepairInfoInfoServiceImpl implements RepairInfoStateService {
	
	@Autowired
	private RepairInfoStateDaoImpl repairInfoStateDaoImpl;

	
	public List<RepairInfoState> getAllList() {
		return repairInfoStateDaoImpl.getAllList();
	}

}
