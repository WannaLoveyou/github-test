package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.FamilyCheckInfoDaoImpl;
import com.qian.dao.impl.InspectionSystemCodeDaoImpl;
import com.qian.entity.AirBottleVariety;
import com.qian.entity.InspectionSystemCode;
import com.qian.service.InspectionSystemCodeService;

/**
 * @author Lu Kongwen
 * @version Create time：2018-6-7 下午3:58:13
 * @Description
 */
@Service
@Transactional
public class InspectionSystemCodeServiceImpl  implements InspectionSystemCodeService {

	@Autowired
	private InspectionSystemCodeDaoImpl inspectionSystemCodeDaoImpl;
	
	@Override
	public List<InspectionSystemCode> getAllList() {
		
		return inspectionSystemCodeDaoImpl.getAllList();
	}

}
