package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.ProductionUnitDaoImpl;
import com.qian.entity.ProductionUnit;
import com.qian.service.ProductionUnitService;

/**
 * @author Lu Kongwen
 * @version Create time：2016-6-16 下午2:55:01
 * @Description
 */
@Service
@Transactional
public class ProductionUnitServiceImpl implements ProductionUnitService {

	@Autowired
	private ProductionUnitDaoImpl productionUnitDaoImpl;

	public List<ProductionUnit> getPageList(int page, int rows) {

		return productionUnitDaoImpl.getPageList(page, rows);
	}

	public long getTotalNum() {

		return productionUnitDaoImpl.getTotalNum();
	}

	public int add(ProductionUnit productionUnit) {

		return productionUnitDaoImpl.insert(productionUnit);
	}

	public int edit(ProductionUnit productionUnit) {

		return productionUnitDaoImpl.update(productionUnit);
	}

	public void delByIds(String[] ids) {

		productionUnitDaoImpl.delByIds(ids);
	}

	public List<ProductionUnit> getAllList() {

		return productionUnitDaoImpl.getAllList();
	}

}
