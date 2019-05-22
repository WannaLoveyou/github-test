package com.qian.service;

import java.util.List;

import com.qian.entity.RepairType;

public interface RepairTypeService {
	
	public List<RepairType> getPageList(int page,int rows);
	
	public Long getTotalNum();
	
	public List<RepairType> getAllList();
	
	public void add(RepairType repairType);
	
	public void delByids(String ids[]);
	
	public void edit(RepairType repairType);
}
