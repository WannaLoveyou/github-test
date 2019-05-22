package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.FirstCategoryDaoImpl;
import com.qian.entity.FirstCategory;
import com.qian.service.FirstCategoryService;

/**
 * @author Lu Kongwen
 * @version Create time：2017-4-20 下午3:33:54
 * @Description
 */
@Service
@Transactional
public class FirstCategoryServiceImpl  implements FirstCategoryService{

	@Autowired
	private FirstCategoryDaoImpl firstDaoImpl;

	public List<FirstCategory> getPageList(int page, int rows) {
		// TODO Auto-generated method stub
		return firstDaoImpl.getPageList(page, rows);
	}

	public long getTotalNum() {
		// TODO Auto-generated method stub
		return firstDaoImpl.getTotalNum();
	}

	public int edit(FirstCategory firstCategory) {
		// TODO Auto-generated method stub
		return firstDaoImpl.update(firstCategory);
	}

	public int add(FirstCategory firstCategory) {
		// TODO Auto-generated method stub
		return firstDaoImpl.insert(firstCategory);
	}

	public void delByIds(String[] ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			firstDaoImpl.delById(Integer.parseInt(id));
		}
	}

	public List<FirstCategory> getAllList() {
		return firstDaoImpl.getAllList();
	}
	

}
