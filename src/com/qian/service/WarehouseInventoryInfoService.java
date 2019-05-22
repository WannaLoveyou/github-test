package com.qian.service;

import java.util.List;
import java.util.Map;

import com.qian.entity.User;
import com.qian.entity.WarehouseInventoryInfo;
import com.qian.mobile.entity.MobileAirBottleCheckEntity;
import com.qian.mobile.entity.MobileAirBottleSumbitEntity;
import com.qian.mobile.entity.MobileOrderInfo;
import com.qian.vo.AirBottleInfoReportDetailVo;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;
import com.qian.vo.WarehouseInventoryInfoVo;

/**
 * @author Lu Kongwen
 * @version Create time：2018-1-26 上午11:57:39
 * @Description
 */
public interface WarehouseInventoryInfoService {

	public BaseDto<Object> forceEmptyBottleStorage(MobileAirBottleSumbitEntity entity, User user);

	public WarehouseInventoryInfo findByWarehouseIdAndBottleCode(MobileAirBottleCheckEntity entity, int emptyBottle, int alreadyReceive);

	public BaseDto<Object> heavyBottleOutToDeliveryMan(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<Object> emptyBottleStorageFromDeliveryMan(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<Object> heavyBottleBackFromDeliveryMan(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<Object> heavyBottleStorageByFilling(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<Object> fillingPickUpInWarehouseForClient(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<Object> forceFillingPickUpInWarehouseForClient(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<MobileOrderInfo> addMobileWarehouseOrder(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<Object> emptyBottleStorageFromClient(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<Object> heavyBottleOutToClient(MobileAirBottleSumbitEntity entity, User user);

	public List<AirBottleInfoReportDetailVo> getDetailsInventoryInfo(List<String> l, Field filed);
	
	public List<WarehouseInventoryInfoVo> getWarehouseInventoryInfoVo(List<String> l, Field filed);
	
	public BaseDto<Map<String, Object>> forceScrapBottleStorage(String bottleIds, User user);
	
	public BaseDto<Map<String, Object>> cancelForceScrapBottleStorage(String bottleIds, User user);

}
