package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.PartTypeDaoImpl;
import com.qian.entity.PartType;
import com.qian.service.PartTypeService;
@Service
@Transactional
public class PartTypeServiceImpl  implements PartTypeService{
	@Autowired
	private PartTypeDaoImpl partTypeDaoImpl;

	public List<PartType> getPageList(int page, int rows) {
		return partTypeDaoImpl.getPageList(page, rows);
	}

	public long getTotalNum() {
		return partTypeDaoImpl.getTotalNum();
	}

	public int add(PartType partType) {
		return partTypeDaoImpl.insert(partType);
	}

	public int edit(PartType partType) {
		return partTypeDaoImpl.update(partType);
	}

	public void delByIds(String[] ids) {
		partTypeDaoImpl.delByIds(ids);
	}

	public List<PartType> getAllList() {
		return partTypeDaoImpl.getAllList();
	}
}
