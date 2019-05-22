package com.qian.mobile.control;

import java.util.ArrayList;
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
import com.qian.entity.User;
import com.qian.mobile.entity.MobileAirBottleCheckEntity;
import com.qian.mobile.entity.MobileAirBottleScanReturnEntity;
import com.qian.mobile.entity.MobileAirBottleSumbitEntity;
import com.qian.mobile.entity.MobileOrderInfo;
import com.qian.mobile.entity.MobileTotalInventoryInfo;
import com.qian.service.ClientInventoryInfoService;
import com.qian.service.DeliveryManInventoryInfoService;
import com.qian.service.OrderAirBottleInfoService;
import com.qian.service.OrderService;
import com.qian.service.UserService;
import com.qian.util.SearchConditionUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2018-1-26 下午7:41:39
 * @Description
 */
@Controller
@RequestMapping("/mobile/deliveryMan")
public class DeliveryManMoblieController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private DeliveryManInventoryInfoService deliveryManInventoryInfoService;

	@Autowired
	private ClientInventoryInfoService clientInventoryInfoService;

	@Autowired
	private OrderAirBottleInfoService orderAirBottleInfoService;

	@RequestMapping(value = "getMyNoDeliveryList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileOrderInfo>> getMyNoDeliveryList(HttpServletRequest request, Map<String, Object> map, int userId) {

		User user = userService.findById(userId);

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		l.add("deliveryType.id = ?");
		filed.addInt(DeliveryTypeCode.ORDER_DELIVERY); // 订气配送

		l.add("state.id = ?");
		filed.addInt(OrderInfoStateCode.ALREADY_DISPATCHING_BUT_NO_DELIVERY); // 已派工未派送

		l.add("delivery_man.id = ?");
		filed.addInt(user.getId());

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
		filed.addInt(DeliveryTypeCode.ORDER_DELIVERY); // 订气配送

		l.add("state.id = ?");
		filed.addInt(OrderInfoStateCode.ALREADY_DELIVERY_BUT_NO_BACK); // 已派送未回单

		l.add("delivery_man.id = ?");
		filed.addInt(user.getId());

		List<MobileOrderInfo> list = orderService.getOrderInfoPageList(l, filed);

		return BaseDto.getSuccessResult(list);
	}

	@RequestMapping(value = "heavyBottleOutToClient", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> heavyBottleOutToClient(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return deliveryManInventoryInfoService.heavyBottleOutToClient(entity, user);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "heavyBottleOutToClientCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> heavyBottleOutToClientCheck(HttpServletRequest request, Map<String, Object> map, MobileAirBottleCheckEntity entity) {

		DeliveryManInventoryInfo deliveryManInventoryInfo = deliveryManInventoryInfoService.findByUserIdAndBottleCode(entity, AirBottleStateCode.HEAVY_BOTTLE,
				AirBottleInventoryStateCode.ALREADY_RECEIVE);

		if (deliveryManInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(deliveryManInventoryInfo.getAirBottleInfo())));
	}

	@RequestMapping(value = "emptyBottleStorageFromClient", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> emptyBottleStorageFromClient(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return deliveryManInventoryInfoService.emptyBottleStorageFromClient(entity, user);
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

	@RequestMapping(value = "heavyBottleBackFromClient", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> heavyBottleBackFromClient(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return deliveryManInventoryInfoService.heavyBottleBackFromClient(entity, user);
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
			if (orderInfo == null) {
				return BaseDto.getFailResult(CommonCode.ORDER_NOT_EXIST);
			}
			orderId = orderInfo.getId();
		}

		entity.setClientId(orderInfo.getClientInfo().getId());

		ClientInventoryInfo clientInventoryInfo = clientInventoryInfoService.findByClientIdAndBottleCode(entity);

		if (clientInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(clientInventoryInfo.getAirBottleInfo())));
	}
	
	@RequestMapping(value = "addMobileDeliveryOrder", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileOrderInfo> addMobileDeliveryOrder(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		return deliveryManInventoryInfoService.addMobileDeliveryOrder(entity,user);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addMobileDeliveryOrderCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> addMobileDeliveryOrderCheck(HttpServletRequest request, Map<String, Object> map,MobileAirBottleCheckEntity entity) {

		DeliveryManInventoryInfo deliveryManInventoryInfo = deliveryManInventoryInfoService.findByUserIdAndBottleCode(entity, AirBottleStateCode.HEAVY_BOTTLE,
				AirBottleInventoryStateCode.ALREADY_RECEIVE);

		if (deliveryManInventoryInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(deliveryManInventoryInfo.getAirBottleInfo())));
	}
	
	@RequestMapping(value = "getDeliveryManTotalInventoryInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileTotalInventoryInfo>> getDeliveryManTotalInventoryInfo(HttpServletRequest request, Map<String, Object> map, int userId) {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		l.add("delivery_man.id = ?");
		filed.addInt(userId);

		List<MobileTotalInventoryInfo> list = deliveryManInventoryInfoService.getDeliveryManTotalInventoryInfo(l, filed);

		return BaseDto.getSuccessResult(list);
	}
	
	@RequestMapping(value = "getMessageNum", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Map<String, Long>> getMessageNum(HttpServletRequest request, Map<String, Object> map, int userId) {

		User user = userService.findById(userId);
		
		// 未派送单
		List<String> myNoDeliveryList = new ArrayList<String>();
		Field myNoDeliveryFiled = new Field();

		SearchConditionUtils.getMyNoDeliveryListByDeliveryMan(myNoDeliveryList, myNoDeliveryFiled, user);

		long myNoDeliveryNum = orderService.getTotalNum(myNoDeliveryList, myNoDeliveryFiled);

		// 未派送回单
		List<String> myDeliveryReceiptList = new ArrayList<String>();
		Field myDeliveryReceiptFiled = new Field();

		SearchConditionUtils.getMyDeliveryReceiptList(myDeliveryReceiptList, myDeliveryReceiptFiled, user);

		long myDeliveryReceiptNum = orderService.getTotalNum(myDeliveryReceiptList, myDeliveryReceiptFiled);

		Map<String, Long> result = new HashMap<String, Long>();

		result.put("deliveryNum", myNoDeliveryNum);
		result.put("receiptNum", myDeliveryReceiptNum);

		return BaseDto.getSuccessResult(result);
	}
	
}
