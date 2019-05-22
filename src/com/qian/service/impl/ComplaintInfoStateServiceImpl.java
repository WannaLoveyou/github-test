package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qian.dao.impl.ComplaintInfoStateDaoImpl;
import com.qian.entity.ComplaintInfoState;
import com.qian.service.ComplaintInfoStateService;
@Service
public class ComplaintInfoStateServiceImpl implements ComplaintInfoStateService {
	@Autowired
	private ComplaintInfoStateDaoImpl complaintInfoStateDaoImpl;
	
	public List<ComplaintInfoState> getAllList() {
		
		return complaintInfoStateDaoImpl.getAllList();
	}

}
