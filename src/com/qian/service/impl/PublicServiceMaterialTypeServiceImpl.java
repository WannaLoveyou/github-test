package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.PublicServiceMaterialTypeDaoImpl;
import com.qian.entity.PublicServiceMaterialType;
import com.qian.service.PublicServiceMaterialTypeService;

@Service
@Transactional
public class PublicServiceMaterialTypeServiceImpl implements PublicServiceMaterialTypeService {

	@Autowired
	private PublicServiceMaterialTypeDaoImpl publicServiceMaterialTypeDaoImpl;

	@Override
	public List<PublicServiceMaterialType> getAllList() {
		return publicServiceMaterialTypeDaoImpl.getAllList();
	}

}
