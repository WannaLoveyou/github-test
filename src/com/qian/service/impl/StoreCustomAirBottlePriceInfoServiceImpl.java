package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.StoreCustomAirBottlePriceInfoDaoImpl;
import com.qian.entity.StoreCustomAirBottlePriceInfo;
import com.qian.service.StoreCustomAirBottlePriceInfoService;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

@Service
@Transactional
public class StoreCustomAirBottlePriceInfoServiceImpl implements StoreCustomAirBottlePriceInfoService {

	@Autowired
	private StoreCustomAirBottlePriceInfoDaoImpl storeCustomAirBottlePriceInfoDaoImpl;

	public Long getTotalNum(List<String> l, Field filed) {

		return storeCustomAirBottlePriceInfoDaoImpl.getTotalNum(l, filed);
	}

	public List<StoreCustomAirBottlePriceInfo> getPageList(List<String> l, Field filed, int page, int rows) {

		return storeCustomAirBottlePriceInfoDaoImpl.getPageList(l, filed, page, rows);
	}

	public CommonCode add(StoreCustomAirBottlePriceInfo storeCustomAirBottlePriceInfo) {

		Integer storeCustomAirBottlePriceInfoId = storeCustomAirBottlePriceInfoDaoImpl.findIdBySecondCategoryIdAndAirBottleTypeId(storeCustomAirBottlePriceInfo.getSecondCategory().getId(),storeCustomAirBottlePriceInfo.getAirBottleType().getId());
		
		if(storeCustomAirBottlePriceInfoId != null){
			return CommonCode.THE_RECORD_HAS_EXIST;

		}
		
		storeCustomAirBottlePriceInfoDaoImpl.insert(storeCustomAirBottlePriceInfo);

		return CommonCode.OK;
	}

	public CommonCode edit(StoreCustomAirBottlePriceInfo storeCustomAirBottlePriceInfo) {
		
		Integer storeCustomAirBottlePriceInfoId = storeCustomAirBottlePriceInfoDaoImpl.findIdBySecondCategoryIdAndAirBottleTypeId(storeCustomAirBottlePriceInfo.getSecondCategory().getId(),storeCustomAirBottlePriceInfo.getAirBottleType().getId());
		
		if(storeCustomAirBottlePriceInfoId != null && storeCustomAirBottlePriceInfoId!=storeCustomAirBottlePriceInfo.getId()){
			return CommonCode.THE_RECORD_HAS_EXIST;

		}
		storeCustomAirBottlePriceInfoDaoImpl.update(storeCustomAirBottlePriceInfo);

		return CommonCode.OK;
	}

	public void delByIds(String[] ids) {
		storeCustomAirBottlePriceInfoDaoImpl.delByIds(ids);
	}

	public List<StoreCustomAirBottlePriceInfo> getByListSecondCategoryId(int secondCategoryId) {
		
		return storeCustomAirBottlePriceInfoDaoImpl.getByListSecondCategoryId(secondCategoryId);
	}

}
