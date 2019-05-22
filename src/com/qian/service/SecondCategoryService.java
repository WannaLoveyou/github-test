package com.qian.service;

import java.util.List;

import com.qian.entity.SecondCategory;
import com.qian.entity.User;

public interface SecondCategoryService {

	public List<SecondCategory> getMyList(User user);
	
	public List<SecondCategory> getAllList();
	
	public Long getTotalNum();
	
	public List<SecondCategory> getPageList(int page, int rows);
	
	public List<SecondCategory> getListByFirstId(int firstId);

	public int add(SecondCategory secondCategory);

	public int edit(SecondCategory secondCategory);

	public void delByIds(String[] idString);

	public SecondCategory findById(int secondCategoryId);

}
