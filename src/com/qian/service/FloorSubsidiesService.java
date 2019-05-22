package com.qian.service;

import java.util.List;

import com.qian.entity.FloorSubsidies;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-9 上午10:53:30
 * @Description
 */
public interface FloorSubsidiesService {

	public List<FloorSubsidies> getPageList(int page, int rows);

	public long getTotalNum();

	public int edit(FloorSubsidies floorSubsidies);
	
	public int add(FloorSubsidies floorSubsidies);

	public void delByIds(String ids[]);

	public List<FloorSubsidies> getAllList();

	public FloorSubsidies findByMoney(double floor_subsidies_money);
}
