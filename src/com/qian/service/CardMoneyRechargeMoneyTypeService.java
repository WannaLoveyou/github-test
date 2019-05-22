package com.qian.service;

import java.util.List;

import com.qian.entity.CardMoneyRechargeMoneyType;

/**
 * @author Lu Kongwen
 * @version Create time：2016-8-4 上午11:41:31
 * @Description
 */
public interface CardMoneyRechargeMoneyTypeService {

	public void add(CardMoneyRechargeMoneyType cardMoneyRechargeMoneyType);

	public void edit(CardMoneyRechargeMoneyType cardMoneyRechargeMoneyType);

	public void delByIds(String[] idString);

	public long getTotalNum();

	public List<CardMoneyRechargeMoneyType> getPageList(int page, int rows);

	public List<CardMoneyRechargeMoneyType> getAllList();

}
