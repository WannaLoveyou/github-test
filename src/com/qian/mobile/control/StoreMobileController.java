package com.qian.mobile.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.qian.code.DeliveryTypeCode;
import com.qian.code.OrderInfoStateCode;
import com.qian.entity.ClientInventoryInfo;
import com.qian.entity.DeliveryManInventoryInfo;
import com.qian.entity.OrderAirBottleInfo;
import com.qian.entity.OrderInfo;
import com.qian.entity.StoreInventoryInfo;
import com.qian.entity.User;
import com.qian.entity.WarehouseInventoryInfo;
import com.qian.mobile.entity.MobileAirBottleCheckEntity;
import com.qian.mobile.entity.MobileAirBottleScanReturnEntity;
import com.qian.mobile.entity.MobileAirBottleSumbitEntity;
import com.qian.mobile.entity.MobileOrderInfo;
import com.qian.service.ClientInventoryInfoService;
import com.qian.service.DeliveryManInventoryInfoService;
import com.qian.service.OrderAirBottleInfoService;
import com.qian.service.OrderService;
import com.qian.service.StoreInventoryInfoService;
import com.qian.service.UserService;
import com.qian.service.WarehouseInventoryInfoService;
import com.qian.util.DateUtil;
import com.qian.util.StringUtils;
import com.qian.util.TimeUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

@Controller
@RequestMapping("/mobile/store")
public class StoreMobileController {

	@Autowired
	private UserService userService;

	@Autowired
	private StoreInventoryInfoService storeInventoryInfoService;

	@Autowired
	private DeliveryManInventoryInfoService deliveryManInventoryInfoService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ClientInventoryInfoService clientInventoryInfoService;

	@Autowired
	private OrderAirBottleInfoService orderAirBottleInfoService;

	@Autowired
	private WarehouseInventoryInfoService warehouseInventoryInfoService;

	@RequestMapping(value = "getNoDispatchList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileOrderInfo>> getNoDispatchList(HttpServletRequest request, Map<String, Object> map, int userId, String startTime, String endTime) {

		User user = userService.findById(userId);

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		l.add("deliveryType.id = ?");
		filed.addInt(DeliveryTypeCode.ORDER_DELIVERY); // 订气派送

		l.add("state.id = ?");
		filed.addInt(OrderInfoStateCode.ALREADY_ORDER_BUT_NO_DISPATCHING); // 未派工

		if (StringUtils.nonEmpty(startTime)) {
			l.add("order_appointment_time1 >= ?");
			filed.addDate(startTime);
		}

		if (StringUtils.nonEmpty(endTime)) {
			l.add("order_appointment_time1 < ?");
			filed.addDate(TimeUtils.getNextDayString(endTime));
		}

		if (user.getSecondCategory() != null) {
			l.add("secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId());
		}

		List<MobileOrderInfo> list = orderService.getOrderInfoPageList(l, filed);

		return BaseDto.getSuccessResult(list);
	}
	
	@RequestMapping(value = "getWaitDispatchList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileOrderInfo>> getWaitDispatchList(HttpServletRequest request, Map<String, Object> map, int userId, String startTime, String endTime) {

		User user = userService.findById(userId);

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		l.add("deliveryType.id = ?");
		filed.addInt(DeliveryTypeCode.ORDER_DELIVERY); // 订气派送

		l.add("state.id = ?");
		filed.addInt(OrderInfoStateCode.ALREADY_DISPATCHING_BUT_NO_DELIVERY); // 已派工未派送

		if (StringUtils.nonEmpty(startTime)) {
			l.add("order_appointment_time1 >= ?");
			filed.addDate(startTime);
		}

		if (StringUtils.nonEmpty(endTime)) {
			l.add("order_appointment_time1 < ?");
			filed.addDate(TimeUtils.getNextDayString(endTime));
		}

		if (user.getSecondCategory() != null) {
			l.add("secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId());
		}

		List<MobileOrderInfo> list = orderService.getOrderInfoPageList(l, filed);

		return BaseDto.getSuccessResult(list);
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

	@RequestMapping(value = "heavyBottleStorageFromDeliveryMan", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> heavyBottleStorageFromDeliveryMan(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return storeInventoryInfoService.heavyBottleStorageFromDeliveryMan(entity, user);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "heavyBottleStorageFromDeliveryManCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> heavyBottleStorageFromDeliveryManCheck(HttpServletRequest request, Map<String, Object> map,
			MobileAirBottleCheckEntity entity) {

		DeliveryManInventoryInfo deliveryManInventoryInfo = deliveryManInventoryInfoService.findByDeliveryManIdAndBottleCode(entity,
				AirBottleStateCode.HEAVY_BOTTLE, AirBottleInventoryStateCode.ALREADY_RECEIVE);

		if (deliveryManInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(deliveryManInventoryInfo.getAirBottleInfo())));
	}

	@RequestMapping(value = "emptyBottleOutToDeliveryMan", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> emptyBottleOut(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return storeInventoryInfoService.emptyBottleOutToDeliveryMan(entity, user);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "emptyBottleOutToDeliveryManCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> emptyBottleOutCheck(HttpServletRequest request, Map<String, Object> map, MobileAirBottleCheckEntity entity) {

		StoreInventoryInfo storeInventoryInfo = storeInventoryInfoService.findByStoreIdAndBottleCode(entity, AirBottleStateCode.EMPTY_BOTTLE,
				AirBottleInventoryStateCode.ALREADY_RECEIVE);

		if (storeInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(storeInventoryInfo.getAirBottleInfo())));
	}

	@RequestMapping(value = "heavyBottleOutToDeliveryMan", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> heavyBottleOutToDeliveryMan(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		// 进行重瓶出库
		return storeInventoryInfoService.heavyBottleOutToDeliveryMan(entity, user);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "heavyBottleOutToDeliveryManCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> heavyBottleOutToDeliveryManCheck(HttpServletRequest request, Map<String, Object> map,
			MobileAirBottleCheckEntity entity) {

		StoreInventoryInfo storeInventoryInfo = storeInventoryInfoService.findByStoreIdAndBottleCode(entity, AirBottleStateCode.HEAVY_BOTTLE,
				AirBottleInventoryStateCode.ALREADY_RECEIVE);

		if (storeInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(storeInventoryInfo.getAirBottleInfo())));
	}

	@RequestMapping(value = "emptyBottleStorageFromDeliveryMan", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> emptyBottleStorageFromDeliveryMan(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return storeInventoryInfoService.emptyBottleStorageFromDeliveryMan(entity, user);
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

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(deliveryManInventoryInfo.getAirBottleInfo())));
	}

	@RequestMapping(value = "addMobileStoreOrder", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileOrderInfo> addMobileStoreOrder(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return storeInventoryInfoService.addMobileStoreOrder(entity, user);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addStoreOrderCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> addStoreOrderCheck(HttpServletRequest request, Map<String, Object> map, MobileAirBottleCheckEntity entity) {

		StoreInventoryInfo storeInventoryInfo = storeInventoryInfoService.findByStoreIdAndBottleCode(entity, AirBottleStateCode.HEAVY_BOTTLE,
				AirBottleInventoryStateCode.ALREADY_RECEIVE);

		if (storeInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(storeInventoryInfo.getAirBottleInfo())));
	}

	@RequestMapping(value = "emptyBottleStorageFromClient", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> emptyBottleStorageFromClient(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return storeInventoryInfoService.emptyBottleStorageFromClient(entity, user);
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

		OrderAirBottleInfo orderAirBottleInfo = orderAirBottleInfoService.findByOrderIdAndBottleId(entity.getOrderId(), clientInventoryInfo.getAirBottleInfo()
				.getId());

		if (orderAirBottleInfo != null) {
			return BaseDto.getFailResult(CommonCode.ORDER_CAN_HAS_SAME_HEAVY_EMPTY_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(clientInventoryInfo.getAirBottleInfo())));
	}

	@RequestMapping(value = "heavyBottleOutToClient", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> heavyBottleOutToClient(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		// 进行重瓶出库给用户
		return storeInventoryInfoService.heavyBottleOutToClient(entity, user);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "heavyBottleOutToClientCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> heavyBottleOutToClientCheck(HttpServletRequest request, Map<String, Object> map, MobileAirBottleCheckEntity entity) {

		StoreInventoryInfo storeInventoryInfo = storeInventoryInfoService.findByStoreIdAndBottleCode(entity, AirBottleStateCode.HEAVY_BOTTLE,
				AirBottleInventoryStateCode.ALREADY_RECEIVE);

		if (storeInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(storeInventoryInfo.getAirBottleInfo())));
	}

	@RequestMapping(value = "heavyBottleBackFromClient", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> heavyBottleBackFromClient(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return storeInventoryInfoService.heavyBottleBackFromClient(entity, user);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "heavyBottleBackFromClientCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> heavyBottleBackFromClientCheck(HttpServletRequest request, Map<String, Object> map,
			MobileAirBottleCheckEntity entity) {

		Integer orderId = entity.getOrderId();
		OrderInfo orderInfo = null;

		if (orderId != null) {
			orderInfo = orderService.findOrderInfoById(orderId);
		} else {
			orderInfo = orderService.findOrderInfoByOrderCode(entity.getOrderCode());
			orderId = orderInfo.getId();
		}

		entity.setClientId(orderInfo.getClientInfo().getId());

		ClientInventoryInfo clientInventoryInfo = clientInventoryInfoService.findByClientIdAndBottleCode(entity);

		if (clientInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(clientInventoryInfo.getAirBottleInfo())));
	}

	@RequestMapping(value = "heavyBottleBackFromDeliveryMan", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> heavyBottleBackFromDeliveryMan(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return storeInventoryInfoService.heavyBottleBackFromDeliveryMan(entity, user);
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

	@RequestMapping(value = "heavyBottlePickFromWarehouse", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> heavyBottlePickFromWarehouse(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return storeInventoryInfoService.heavyBottlePickFromWarehouse(entity, user);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "heavyBottlePickFromWarehouseCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> heavyBottlePickFromWarehouseCheck(HttpServletRequest request, Map<String, Object> map,
			MobileAirBottleCheckEntity entity) {

		WarehouseInventoryInfo warehouseInventoryInfo = warehouseInventoryInfoService.findByWarehouseIdAndBottleCode(entity, AirBottleStateCode.HEAVY_BOTTLE,
				AirBottleInventoryStateCode.ALREADY_RECEIVE);

		if (warehouseInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(warehouseInventoryInfo.getAirBottleInfo())));
	}
	
	@RequestMapping(value = "getMessageNum", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Map<String, Long>> getMessageNum(HttpServletRequest request, Map<String, Object> map, int userId) {

		User user = userService.findById(userId);
		
		// 未派送单
		List<String> myNoDeliveryList = new ArrayList<String>();
		Field myNoDeliveryFiled = new Field();
		
		myNoDeliveryList.add("deliveryType.id = ?");
		myNoDeliveryFiled.addInt(DeliveryTypeCode.ORDER_DELIVERY); // 订气派送
		
		myNoDeliveryList.add("state.id = ?");
		myNoDeliveryFiled.addInt(OrderInfoStateCode.ALREADY_DISPATCHING_BUT_NO_DELIVERY); // 已派工未派送

		if (user.getSecondCategory() != null) {
			myNoDeliveryList.add("secondCategory.id = ?");
			myNoDeliveryFiled.addInt(user.getSecondCategory().getId());
		}
		
		Date date = new Date(); 
		myNoDeliveryList.add("order_appointment_time1 >= ?");
		myNoDeliveryFiled.addDate(DateUtil.formatDate(date));

		myNoDeliveryList.add("order_appointment_time1 < ?");
		myNoDeliveryFiled.addDate(TimeUtils.getNextDayString(DateUtil.formatDate(date)));

		long myNoDeliveryNum = orderService.getTotalNum(myNoDeliveryList, myNoDeliveryFiled);

		Map<String, Long> result = new HashMap<String, Long>();

		result.put("deliveryNum", myNoDeliveryNum);

		return BaseDto.getSuccessResult(result);
	}
}
