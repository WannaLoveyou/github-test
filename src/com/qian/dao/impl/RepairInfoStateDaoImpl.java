package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.RepairInfoState;
@Component
public class RepairInfoStateDaoImpl extends HBaseDao<RepairInfoState> {
	
	public List<RepairInfoState> getAllList(){
		return selectFromHQL("from RepairInfoState");
	}
}
