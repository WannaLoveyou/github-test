package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qian.dao.impl.RepairTypeDaoImpl;
import com.qian.entity.RepairType;
import com.qian.service.RepairTypeService;

@Service
public class RepairTypeServiceImpl implements RepairTypeService {
	@Autowired
	private RepairTypeDaoImpl repairTypeDaoImpl;

	public List<RepairType> getPageList(int page, int rows) {
		return repairTypeDaoImpl.getPageList(page, rows);
	}

	public Long getTotalNum() {
		return repairTypeDaoImpl.getTotalNum();
	}

	public List<RepairType> getAllList() {
		return repairTypeDaoImpl.getAllList();
	}

	public void add(RepairType repairType) {
		repairTypeDaoImpl.insert(repairType);
	}

	public void delByids(String ids[]) {
		repairTypeDaoImpl.delByIds(ids);
	}

	public void edit(RepairType repairType) {
		repairTypeDaoImpl.update(repairType);
	}

}
