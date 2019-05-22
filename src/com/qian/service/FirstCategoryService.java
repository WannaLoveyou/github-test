package com.qian.service;

import java.util.List;

import com.qian.entity.FirstCategory;

/**
 * @author Lu Kongwen
 * @version Create time：2017-4-20 下午3:33:12
 * @Description
 */
public interface FirstCategoryService {

	public List<FirstCategory> getAllList();

	public List<FirstCategory> getPageList(int page, int rows);

	public long getTotalNum();

	public int edit(FirstCategory firstCategory);

	public int add(FirstCategory firstCategory);

	public void delByIds(String ids[]);
}
