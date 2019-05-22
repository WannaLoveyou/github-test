package com.qian.service;

import java.util.List;

import com.qian.entity.StoreCustomAirBottlePriceInfo;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;


public interface StoreCustomAirBottlePriceInfoService {

	public Long getTotalNum(List<String> l, Field filed);

	public List<StoreCustomAirBottlePriceInfo> getPageList(List<String> l, Field filed, int page, int rows);

	public CommonCode add(StoreCustomAirBottlePriceInfo storeCustomAirBottlePriceInfo);

	public CommonCode edit(StoreCustomAirBottlePriceInfo storeCustomAirBottlePriceInfo);

	public void delByIds(String[] idString);

	public List<StoreCustomAirBottlePriceInfo> getByListSecondCategoryId(int secondCategoryId);


}
