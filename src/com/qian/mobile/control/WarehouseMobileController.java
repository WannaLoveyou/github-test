package com.qian.mobile.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.code.AirBottleInventoryStateCode;
import com.qian.code.AirBottleStateCode;
import com.qian.code.BeforeOrAfterFillCode;
import com.qian.code.DeliveryTypeCode;
import com.qian.code.OrderInfoStateCode;
import com.qian.entity.AirBottleInfo;
import com.qian.entity.ClientInventoryInfo;
import com.qian.entity.DeliveryManInventoryInfo;
import com.qian.entity.FillCheckInfo;
import com.qian.entity.User;
import com.qian.entity.WarehouseInventoryInfo;
import com.qian.mobile.entity.MobileAirBottleCheckEntity;
import com.qian.mobile.entity.MobileAirBottleScanReturnEntity;
import com.qian.mobile.entity.MobileAirBottleSumbitEntity;
import com.qian.mobile.entity.MobileFillCheckInfo;
import com.qian.mobile.entity.MobileFillCheckInfoSubmitEntity;
import com.qian.mobile.entity.MobileOrderInfo;
import com.qian.service.AirBottleInfoService;
import com.qian.service.ClientInventoryInfoService;
import com.qian.service.DeliveryManInventoryInfoService;
import com.qian.service.FillCheckInfoService;
import com.qian.service.FillCheckRecordService;
import com.qian.service.OrderService;
import com.qian.service.UserService;
import com.qian.service.WarehouseInventoryInfoService;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2018-1-26 上午11:54:43
 * @Description
 */
@Controller
@RequestMapping("/mobile/warehouse")
public class WarehouseMobileController {

	@Autowired
	private AirBottleInfoService airBottleInfoService;

	@Autowired
	private UserService userService;

	@Autowired
	private WarehouseInventoryInfoService warehouseInventoryInfoService;

	@Autowired
	private DeliveryManInventoryInfoService deliveryManInventoryInfoService;

	@Autowired
	private ClientInventoryInfoService clientInventoryInfoService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private FillCheckInfoService fillCheckInfoService;

	@Autowired
	private FillCheckRecordService fillCheckRecordService;
	
	@RequestMapping(value = "forceEmptyBottleStorage", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> forceEmptyBottleStorage(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return warehouseInventoryInfoService.forceEmptyBottleStorage(entity, user);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "forceEmptyBottleStorageCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> forceEmptyBottleStorageCheck(HttpServletRequest request, Map<String, Object> map, MobileAirBottleCheckEntity entity) {

		AirBottleInfo airBottleInfo = airBottleInfoService.findByAirBottleCode(entity);

		if (airBottleInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(airBottleInfo)));
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "heavyBottleOutToDeliveryManCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> heavyBottleOutToDeliveryManCheck(HttpServletRequest request, Map<String, Object> map,
			MobileAirBottleCheckEntity entity) {

		WarehouseInventoryInfo warehouseInventoryInfo = warehouseInventoryInfoService.findByWarehouseIdAndBottleCode(entity, AirBottleStateCode.HEAVY_BOTTLE,
				AirBottleInventoryStateCode.ALREADY_RECEIVE);

		if (warehouseInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		Date nowTime = new Date();

		if (warehouseInventoryInfo.getAirBottleInfo().getNext_check_time().getTime() <= nowTime.getTime()) {
			return BaseDto.getFailResult(CommonCode.AIR_BOTTLE_HAS_OVERDUE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(warehouseInventoryInfo.getAirBottleInfo())));

	}

	@RequestMapping(value = "heavyBottleOutToDeliveryMan", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> heavyBottleOutToDeliveryMan(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return warehouseInventoryInfoService.heavyBottleOutToDeliveryMan(entity, user);
	}

	@RequestMapping(value = "emptyBottleStorageFromDeliveryMan", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> emptyBottleStorageFromDeliveryMan(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return warehouseInventoryInfoService.emptyBottleStorageFromDeliveryMan(entity, user);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "emptyBottleStorageFromDeliveryManCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> emptyBottleStorageFromDeliveryManCheck(HttpServletRequest request, Map<String, Object> map,
			MobileAirBottleCheckEntity entity) {

		DeliveryManInventoryInfo deliveryManInventoryInfo = deliveryManInventoryInfoService.findByDeliveryManIdAndBottleCode(entity,
				AirBottleStateCode.EMPTY_BOTTLE, AirBottleInventoryStateCode.ALREADY_RECEIVE);

		if (deliveryManInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		Date nowTime = new Date();

		if (deliveryManInventoryInfo.getAirBottleInfo().getNext_check_time().getTime() <= nowTime.getTime()) {
			return BaseDto.getFailResult(CommonCode.AIR_BOTTLE_HAS_OVERDUE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(deliveryManInventoryInfo.getAirBottleInfo())));
	}

	@RequestMapping(value = "heavyBottleBackFromDeliveryMan", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> heavyBottleBackFromDeliveryMan(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return warehouseInventoryInfoService.heavyBottleBackFromDeliveryMan(entity, user);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "heavyBottleBackFromDeliveryManCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> heavyBottleBackFromDeliveryManCheck(HttpServletRequest request, Map<String, Object> map,
			MobileAirBottleCheckEntity entity) {

		DeliveryManInventoryInfo deliveryManInventoryInfo = deliveryManInventoryInfoService.findByDeliveryManIdAndBottleCode(entity,
				AirBottleStateCode.HEAVY_BOTTLE, AirBottleInventoryStateCode.ALREADY_RECEIVE);

		if (deliveryManInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(deliveryManInventoryInfo.getAirBottleInfo())));
	}

	@RequestMapping(value = "heavyBottleStorageByFilling", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> heavyBottleStorageByFilling(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return warehouseInventoryInfoService.heavyBottleStorageByFilling(entity, user);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "heavyBottleStorageByFillingCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> heavyBottleStorageByFillingCheck(HttpServletRequest request, Map<String, Object> map,
			MobileAirBottleCheckEntity entity) {

		WarehouseInventoryInfo warehouseInventoryInfo = warehouseInventoryInfoService.findByWarehouseIdAndBottleCode(entity, AirBottleStateCode.EMPTY_BOTTLE,
				AirBottleInventoryStateCode.ALREADY_RECEIVE);

		if (warehouseInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		Date nowTime = new Date();

		if (warehouseInventoryInfo.getAirBottleInfo().getNext_check_time().getTime() <= nowTime.getTime()) {
			return BaseDto.getFailResult(CommonCode.AIR_BOTTLE_HAS_OVERDUE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(warehouseInventoryInfo.getAirBottleInfo())));

	}

	@RequestMapping(value = "fillingPickUpInWarehouseForClient", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> fillingPickUpInWarehouseForClient(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return warehouseInventoryInfoService.fillingPickUpInWarehouseForClient(entity, user);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "fillingPickUpInWarehouseForClientCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> fillingPickUpInWarehouseForClientCheck(HttpServletRequest request, Map<String, Object> map,
			MobileAirBottleCheckEntity entity) {

		ClientInventoryInfo clientInventoryInfo = clientInventoryInfoService.findByClientIdAndBottleCode(entity);

		if (clientInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(clientInventoryInfo.getAirBottleInfo())));
	}

	@RequestMapping(value = "forceFillingPickUpInWarehouseForClient", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> forceFillingPickUpInWarehouseForClient(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return warehouseInventoryInfoService.forceFillingPickUpInWarehouseForClient(entity, user);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "forceFillingPickUpInWarehouseForClientCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> forceFillingPickUpInWarehouseForClientCheck(HttpServletRequest request, Map<String, Object> map,
			MobileAirBottleCheckEntity entity) {

		AirBottleInfo airBottleInfo = airBottleInfoService.findByAirBottleCode(entity);

		if (airBottleInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(airBottleInfo)));
	}

	@RequestMapping(value = "getMyNoDeliveryList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileOrderInfo>> getMyNoDeliveryList(HttpServletRequest request, Map<String, Object> map, int userId) {

		User user = userService.findById(userId);

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		l.add("deliveryType.id = ?");
		filed.addInt(DeliveryTypeCode.PICKUP_IN_STORES); // 门店自提

		l.add("state.id = ?");
		filed.addInt(OrderInfoStateCode.ALREADY_ORDER_BUT_NO_DISPATCHING); // 已下单未派送

		if (user.getSecondCategory() != null) {
			l.add("secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId());
		}

		List<MobileOrderInfo> list = orderService.getOrderInfoPageList(l, filed);

		return BaseDto.getSuccessResult(list);
	}

	@RequestMapping(value = "getMyDeliveryReceiptList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileOrderInfo>> getMyDeliveryReceiptList(HttpServletRequest request, Map<String, Object> map, int userId) {

		User user = userService.findById(userId);

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		l.add("deliveryType.id = ?");
		filed.addInt(DeliveryTypeCode.PICKUP_IN_STORES); // 门店自提

		l.add("state.id = ?");
		filed.addInt(OrderInfoStateCode.ALREADY_DELIVERY_BUT_NO_BACK); // 已派送未回单

		if (user.getSecondCategory() != null) {
			l.add("secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId());
		}

		List<MobileOrderInfo> list = orderService.getOrderInfoPageList(l, filed);

		return BaseDto.getSuccessResult(list);
	}

	@RequestMapping(value = "addMobileWarehouseOrder", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileOrderInfo> addMobileStoreOrder(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return warehouseInventoryInfoService.addMobileWarehouseOrder(entity, user);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addMobileWarehouseOrderCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> addMobileWarehouseOrderCheck(HttpServletRequest request, Map<String, Object> map, MobileAirBottleCheckEntity entity) {

		WarehouseInventoryInfo warehouseInventoryInfo = warehouseInventoryInfoService.findByWarehouseIdAndBottleCode(entity, AirBottleStateCode.HEAVY_BOTTLE,
				AirBottleInventoryStateCode.ALREADY_RECEIVE);

		if (warehouseInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(warehouseInventoryInfo.getAirBottleInfo())));
	}

	@RequestMapping(value = "emptyBottleStorageFromClient", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> emptyBottleStorageFromClient(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return warehouseInventoryInfoService.emptyBottleStorageFromClient(entity, user);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "emptyBottleStorageFromClientCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> emptyBottleStorageFromClientCheck(HttpServletRequest request, Map<String, Object> map,
			MobileAirBottleCheckEntity entity) {

		ClientInventoryInfo clientInventoryInfo = clientInventoryInfoService.findByClientIdAndBottleCode(entity);

		if (clientInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(clientInventoryInfo.getAirBottleInfo())));

	}
	
	@RequestMapping(value = "heavyBottleOutToClient", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> heavyBottleOutToClient(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return warehouseInventoryInfoService.heavyBottleOutToClient(entity, user);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "heavyBottleOutToClientCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> heavyBottleOutToClientCheck(HttpServletRequest request, Map<String, Object> map,
			MobileAirBottleCheckEntity entity) {

		WarehouseInventoryInfo warehouseInventoryInfo = warehouseInventoryInfoService.findByWarehouseIdAndBottleCode(entity, AirBottleStateCode.HEAVY_BOTTLE,
				AirBottleInventoryStateCode.ALREADY_RECEIVE);

		if (warehouseInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(warehouseInventoryInfo.getAirBottleInfo())));

	}
	

	@RequestMapping(value = "getFillCheckInfoBeforeFilling", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileFillCheckInfo>> getFillCheckInfoBeforeFilling(HttpServletRequest request, Map<String, Object> map) {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		l.add("before_or_after_fill = ?");
		filed.addInt(BeforeOrAfterFillCode.BEFORE_FILLING); // 充装前
		
		List<FillCheckInfo> list = fillCheckInfoService.getAllList(l,filed);

		List<MobileFillCheckInfo> result = new ArrayList<MobileFillCheckInfo>();

		for (FillCheckInfo fillCheckInfo : list) {

			MobileFillCheckInfo mobileFillCheckInfo = new MobileFillCheckInfo(fillCheckInfo);

			result.add(mobileFillCheckInfo);
		}

		return BaseDto.getSuccessResult(result);
	}
	
	
	@RequestMapping(value = "getFillCheckInfoAfterFilling", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileFillCheckInfo>> getFillCheckInfoAfterFilling(HttpServletRequest request, Map<String, Object> map) {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		l.add("before_or_after_fill = ?");
		filed.addInt(BeforeOrAfterFillCode.AFTER_FILLING); // 充装后
		
		List<FillCheckInfo> list = fillCheckInfoService.getAllList(l,filed);

		List<MobileFillCheckInfo> result = new ArrayList<MobileFillCheckInfo>();

		for (FillCheckInfo fillCheckInfo : list) {

			MobileFillCheckInfo mobileFillCheckInfo = new MobileFillCheckInfo(fillCheckInfo);

			result.add(mobileFillCheckInfo);
		}

		return BaseDto.getSuccessResult(result);
	}
	
	
	@RequestMapping(value = "sumbitFillCheckInfoBeforeFilling", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> sumbitFillCheckInfoBeforeFilling(HttpServletRequest request, Map<String, Object> map,MobileFillCheckInfoSubmitEntity entity) {

		return fillCheckRecordService.sumbitFillCheckInfoBeforeFilling(entity);
	}
	
	@RequestMapping(value = "sumbitFillCheckInfoAfterFilling", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> sumbitFillCheckInfoAfterFilling(HttpServletRequest request, Map<String, Object> map,MobileFillCheckInfoSubmitEntity entity) {

		return fillCheckRecordService.sumbitFillCheckInfoAfterFilling(entity);
	}
}
