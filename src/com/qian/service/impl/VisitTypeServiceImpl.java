package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qian.dao.impl.VisitTypeDaoImpl;
import com.qian.entity.VisitType;
import com.qian.service.VisitTypeService;

@Service
public class VisitTypeServiceImpl implements VisitTypeService{
	@Autowired
	private VisitTypeDaoImpl visitTypeDaoImpl;

	public List<VisitType> getPageList(int page, int rows) {
		return visitTypeDaoImpl.getPageList(page, rows);
	}

	public Long getTotalNum() {
		return visitTypeDaoImpl.getTotalNum();
	}

	public List<VisitType> getAllList() {
		return visitTypeDaoImpl.getAllList();
	}

	public void add(VisitType visitType) {
		visitTypeDaoImpl.insert(visitType);
	}

	public void delByids(String[] ids) {
		visitTypeDaoImpl.delByIds(ids);
	}

	public void edit(VisitType visitType) {
		visitTypeDaoImpl.update(visitType);
	}
}
