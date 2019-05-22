package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.ComplaintInfoState;

@Component
public class ComplaintInfoStateDaoImpl extends HBaseDao<ComplaintInfoState> {
	
	public List<ComplaintInfoState> getAllList(){
		
		return selectFromHQL("from ComplaintInfoState");
	};
}
