package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.qian.dao.impl.PartPriceDaoImpl;
import com.qian.entity.PartPrice;
import com.qian.service.PartPriceService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PartPriceFeeServiceImpl implements PartPriceService {
	@Autowired
	private PartPriceDaoImpl partPriceDaoImpl;

	public List<PartPrice> getPageList(int page, int rows) {
		return partPriceDaoImpl.getPageList(page, rows);
	}

	public Long getTotalNum() {
		return partPriceDaoImpl.getTotalNum();
	}

	public List<PartPrice> getAllList() {
		return partPriceDaoImpl.getAllList();
	}

	public int add(PartPrice partPrice) {
		return partPriceDaoImpl.insert(partPrice);
	}

	public int edit(PartPrice partPrice) {
		return partPriceDaoImpl.update(partPrice);
	}

	public void delByIds(String[] ids) {
		partPriceDaoImpl.delByIds(ids);
	}

	public List<PartPrice> getFee(int airBottleTypeId, int partTypeId) {
		return null;
	}

	public PartPrice findByid(int id) {
		return partPriceDaoImpl.findById(id);
	}

	public List<PartPrice> getListByBottleType(int bottleTypeId) {
		return partPriceDaoImpl.getListByBottleType(bottleTypeId);
	}

	
	
}
