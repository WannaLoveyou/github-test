package com.qian.service;

import java.util.List;

import com.qian.entity.PartPrice;

public interface PartPriceService {
	
	public List<PartPrice> getPageList(int page,int rows);
	
	public Long getTotalNum();
	
	public List<PartPrice> getAllList();
	
	
	public int add(PartPrice partPrice);

	public int edit(PartPrice partPrice);
	
	public void delByIds(String ids[]);
	
	public List<PartPrice> getFee(int airBottleTypeId,int partTypeId);
	
	public PartPrice findByid(int id);

	public List<PartPrice> getListByBottleType(int bottleTypeId);
}
