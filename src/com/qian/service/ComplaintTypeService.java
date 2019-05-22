package com.qian.service;

import java.util.List;

import com.qian.entity.ComplaintType;


public interface ComplaintTypeService {
	
	public List<ComplaintType> getPageList(int page,int rows);
	
	public Long getTotalNum();
	
	public List<ComplaintType> getAllList();
	
	public void add(ComplaintType complaintType);
	
	public void delByids(String ids[]);
	
	public void edit(ComplaintType complaintType);
}
