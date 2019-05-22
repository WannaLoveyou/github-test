package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qian.dao.impl.ComplaintTypeDaoImpl;
import com.qian.entity.ComplaintType;
import com.qian.service.ComplaintTypeService;

@Service
public class ComplaintTypeServiceImpl implements ComplaintTypeService{
	@Autowired
	private ComplaintTypeDaoImpl complaintTypeDaoImpl;

	public List<ComplaintType> getPageList(int page, int rows) {
		return complaintTypeDaoImpl.getPageList(page, rows);
	}

	public Long getTotalNum() {
		return complaintTypeDaoImpl.getTotalNum();
	}

	public List<ComplaintType> getAllList() {
		return complaintTypeDaoImpl.getAllList();
	}

	public void add(ComplaintType complaintType) {
		complaintTypeDaoImpl.insert(complaintType);
	}

	public void delByids(String[] ids) {
		complaintTypeDaoImpl.delByIds(ids);
	}

	public void edit(ComplaintType complaintType) {
		complaintTypeDaoImpl.update(complaintType);
	}
}
