package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.DetectionUnitDaoImpl;
import com.qian.entity.DetectionUnit;
import com.qian.service.DetectionUnitService;

/**
 * @author Lu Kongwen
 * @version Create time：2016-6-16 下午3:08:33
 * @Description
 */
@Service
@Transactional
public class DetectionUnitServiceImpl implements DetectionUnitService {

	@Autowired
	private DetectionUnitDaoImpl detectionUnitDaoImpl;

	public List<DetectionUnit> getPageList(int page, int rows) {

		return detectionUnitDaoImpl.getPageList(page, rows);
	}

	public long getTotalNum() {

		return detectionUnitDaoImpl.getTotalNum();
	}

	public int add(DetectionUnit detectionUnit) {

		return detectionUnitDaoImpl.insert(detectionUnit);
	}

	public int edit(DetectionUnit detectionUnit) {

		return detectionUnitDaoImpl.update(detectionUnit);
	}

	public void delByIds(String[] ids) {

		detectionUnitDaoImpl.delByIds(ids);
	}

	public List<DetectionUnit> getAllList() {

		return detectionUnitDaoImpl.getAllList();
	}
}
