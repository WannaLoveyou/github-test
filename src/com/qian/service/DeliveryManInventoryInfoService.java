package com.qian.service;

import java.util.List;

import com.qian.entity.DeliveryManInventoryInfo;
import com.qian.entity.User;
import com.qian.mobile.entity.MobileAirBottleCheckEntity;
import com.qian.mobile.entity.MobileAirBottleSumbitEntity;
import com.qian.mobile.entity.MobileOrderInfo;
import com.qian.mobile.entity.MobileTotalInventoryInfo;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;


/**
 * @author Lu Kongwen
 * @version Create time：2017-2-24 下午4:13:15
 * @Description
 */
public interface DeliveryManInventoryInfoService {

	public DeliveryManInventoryInfo findByDeliveryManIdAndBottleCode(MobileAirBottleCheckEntity entity, int emptyBottle, int alreadyReceive);

	public BaseDto<Object> heavyBottleOutToClient(MobileAirBottleSumbitEntity entity, User user);

	public DeliveryManInventoryInfo findByUserIdAndBottleCode(MobileAirBottleCheckEntity entity, int heavyBottle, int alreadyReceive);

	public BaseDto<Object> emptyBottleStorageFromClient(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<Object> heavyBottleBackFromClient(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<MobileOrderInfo> addMobileDeliveryOrder(MobileAirBottleSumbitEntity entity, User user);

	public List<MobileTotalInventoryInfo> getDeliveryManTotalInventoryInfo(List<String> l, Field filed);

	
}
