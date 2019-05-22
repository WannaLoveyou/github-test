package com.qian.service;

import java.util.List;

import com.qian.entity.ProductionUnit;

/**
 * @author Lu Kongwen
 * @version Create time：2016-6-16 下午2:53:39
 * @Description
 */
public interface ProductionUnitService {

	public List<ProductionUnit> getPageList(int page, int rows);

	public long getTotalNum();

	public int add(ProductionUnit productionUnit);

	public int edit(ProductionUnit productionUnit);
	
	public void delByIds(String ids[]);

	public List<ProductionUnit> getAllList();
}
