package com.qian.service;

import java.util.List;

import com.qian.entity.VisitType;


public interface VisitTypeService {
	
	public List<VisitType> getPageList(int page,int rows);
	
	public Long getTotalNum();
	
	public List<VisitType> getAllList();
	
	public void add(VisitType visitType);
	
	public void delByids(String ids[]);
	
	public void edit(VisitType visitType);
}
