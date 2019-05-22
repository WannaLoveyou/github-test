package com.qian.service;

import java.util.List;

import com.qian.entity.AirBottleType;

public interface AirBottleTypeService {

	public List<AirBottleType> getAllList();

	public List<AirBottleType> getAllWeiXinList();

	public List<AirBottleType> getPageList(int page, int rows);

	public long getTotalNum();

	public int add(AirBottleType airBottleType);

	public int edit(AirBottleType airBottleType, int airBottleTypeId);

	public int editSpecial(AirBottleType airBottleTypeTemp, int airBottleTypeId);

	public void delByIds(String ids[]);

	public AirBottleType getByAirBottleTypeName(String airBottleTypeName);

	public AirBottleType findById(int airBottleTypeId);

	public List<AirBottleType> getAllListForSpecialPrice(Integer secondCategoryId);

}
