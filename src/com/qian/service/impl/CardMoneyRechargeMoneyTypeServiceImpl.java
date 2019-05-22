package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.CardMoneyRechargeMoneyTypeDaoImpl;
import com.qian.entity.CardMoneyRechargeMoneyType;
import com.qian.service.CardMoneyRechargeMoneyTypeService;

/**
 * @author Lu Kongwen
 * @version Create time：2016-8-4 上午11:48:32
 * @Description
 */
@Service
@Transactional
public class CardMoneyRechargeMoneyTypeServiceImpl implements CardMoneyRechargeMoneyTypeService {

	@Autowired
	private CardMoneyRechargeMoneyTypeDaoImpl cardMoneyRechargeMoneyTypeDaoImpl;
	
	public void add(CardMoneyRechargeMoneyType cardMoneyRechargeMoneyType) {
		
		cardMoneyRechargeMoneyTypeDaoImpl.insert(cardMoneyRechargeMoneyType);
	}

	public void edit(CardMoneyRechargeMoneyType cardMoneyRechargeMoneyType) {
		
		cardMoneyRechargeMoneyTypeDaoImpl.update(cardMoneyRechargeMoneyType);
	}

	public void delByIds(String[] idString) {
		
		cardMoneyRechargeMoneyTypeDaoImpl.delByIds(idString);
	}

	public long getTotalNum() {
	
		return cardMoneyRechargeMoneyTypeDaoImpl.getTotalNum();
	}

	public List<CardMoneyRechargeMoneyType> getPageList(int page, int rows) {
		
		return cardMoneyRechargeMoneyTypeDaoImpl.getPageList(page,rows);
	}

	public List<CardMoneyRechargeMoneyType> getAllList() {
		
		return cardMoneyRechargeMoneyTypeDaoImpl.getAllList();
	}

}
