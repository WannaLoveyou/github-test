package com.qian.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.SecondCategoryDaoImpl;
import com.qian.entity.SecondCategory;
import com.qian.entity.User;
import com.qian.service.SecondCategoryService;

@Service
@Transactional
public class SecondCategoryServiceImpl implements SecondCategoryService {

	@Autowired
	private SecondCategoryDaoImpl secondCategoryDaoImpl;

	
	public List<SecondCategory> getMyList(User user) {
		List<SecondCategory> list = new ArrayList<SecondCategory>();

		SecondCategory sc = user.getSecondCategory();

		if (sc != null) {
			list.add(sc);
			return list;
		}

		return getAllList();
	}

	public List<SecondCategory> getAllList() {

		return secondCategoryDaoImpl.getAllList();
	}

	public Long getTotalNum() {
		// TODO Auto-generated method stub
		return secondCategoryDaoImpl.getTotalNum();
	}

	public List<SecondCategory> getPageList(int page, int rows) {
		// TODO Auto-generated method stub
		return secondCategoryDaoImpl.getPageList(page, rows);
	}

	public int edit(SecondCategory secondCategory) {
		// TODO Auto-generated method stub
		return secondCategoryDaoImpl.update(secondCategory);
	}

	public int add(SecondCategory secondCategory) {
		// TODO Auto-generated method stub
		return secondCategoryDaoImpl.insert(secondCategory);
	}

	public void delByIds(String[] ids) {
		// TODO Auto-generated method stub
		for (String id : ids) {
			secondCategoryDaoImpl.delById(Integer.parseInt(id));
		}
	}

	public List<SecondCategory> getListByFirstId(int id) {
		// TODO Auto-generated method stub
		return secondCategoryDaoImpl.getListByFirstId(id);
	}

	public SecondCategory findById(int secondCategoryId) {

		return secondCategoryDaoImpl.findById(secondCategoryId);
	}

}
