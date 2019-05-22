package com.qian.service;

import java.util.List;

import com.qian.entity.StoreInventoryInfo;
import com.qian.entity.User;
import com.qian.mobile.entity.MobileAirBottleCheckEntity;
import com.qian.mobile.entity.MobileAirBottleSumbitEntity;
import com.qian.mobile.entity.MobileOrderInfo;
import com.qian.vo.AirBottleInfoReportDetailVo;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;
import com.qian.vo.StoreInventoryInfoVo;

public interface StoreInventoryInfoService {

	public BaseDto<Object> heavyBottleStorageFromDeliveryMan(MobileAirBottleSumbitEntity entity, User user);

	public StoreInventoryInfo findByStoreIdAndBottleCode(MobileAirBottleCheckEntity entity, int heavyBottle, int alreadyReceive);

	public BaseDto<Object> heavyBottleOutToDeliveryMan(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<Object> emptyBottleOutToDeliveryMan(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<Object> emptyBottleStorageFromDeliveryMan(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<MobileOrderInfo> addMobileStoreOrder(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<Object> heavyBottleOutToClient(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<Object> emptyBottleStorageFromClient(MobileAirBottleSumbitEntity entity, User user);
	
	public List<AirBottleInfoReportDetailVo> getDetailsInventoryInfo(List<String> l, Field filed);

	public List<StoreInventoryInfoVo> getStoreInventoryInfoVo(List<String> l, Field filed);

	public BaseDto<Object> heavyBottleBackFromClient(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<Object> heavyBottleBackFromDeliveryMan(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<Object> heavyBottlePickFromWarehouse(MobileAirBottleSumbitEntity entity, User user);

}
