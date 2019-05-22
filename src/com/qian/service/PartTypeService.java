package com.qian.service;

import java.util.List;

import com.qian.entity.PartType;

public interface PartTypeService {
	
	public List<PartType> getPageList(int page, int rows);

	public List<PartType> getAllList();
	
	public long getTotalNum();

	public int add(PartType businessType);

	public int edit(PartType businessType);
	
	public void delByIds(String ids[]);
}

