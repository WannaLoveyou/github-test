package com.qian.pc.control;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qian.code.DeliveryTypeCode;
import com.qian.code.ModuleConfigurationCode;
import com.qian.code.OrderInfoStateCode;
import com.qian.code.PayStateCode;
import com.qian.code.PayTypeCode;
import com.qian.entity.ClientInfo;
import com.qian.entity.FloorSubsidies;
import com.qian.entity.ModuleConfigurationInfo;
import com.qian.entity.OrderInfo;
import com.qian.entity.OrderInfoState;
import com.qian.entity.PartFeeInfo;
import com.qian.entity.PayState;
import com.qian.entity.User;
import com.qian.service.ClientInfoService;
import com.qian.service.FloorSubsidiesService;
import com.qian.service.ModuleConfigurationInfoService;
import com.qian.service.OrderService;
import com.qian.service.PartFeeInfoService;
import com.qian.util.ExportHeadsUtils;
import com.qian.util.ExportUtils;
import com.qian.util.StringUtils;
import com.qian.util.TimeUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.ExportOrderDetailInfoHeads;
import com.qian.vo.Field;
import com.qian.vo.SimpleOrderInfo;

/**
 * @author lizhaoyang
 * @version Create time：2015-11-13 上午9:05:58
 * @Description
 */

@Controller
@RequestMapping("/order")
public class OrderController {

	private static final String ORDER_LIST = "order/order_list";
	private static final String DISPATCH_LIST = "order/dispatch_list";
	private static final String PICKUP_IN_STORES_LIST = "order/pickup_in_stores_list";
	private static final String ORDER_MANAGEMENT_LIST = "order/order_management_list";

	@Autowired
	private ClientInfoService clientInfoService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private FloorSubsidiesService floorSubsidiesService;

	@Autowired
	private PartFeeInfoService partFeeInfoService;

	@Autowired
	private ModuleConfigurationInfoService moduleConfigurationInfoService;

	@RequiresUser
	@RequestMapping(value = "orderList", method = RequestMethod.GET)
	public String orderList(HttpServletRequest request, Map<String, Object> map) {
		return ORDER_LIST;
	}

	@RequiresUser
	@RequestMapping(value = "dispatchList", method = RequestMethod.GET)
	public String dispatch_list(HttpServletRequest request, Map<String, Object> map) {
		return DISPATCH_LIST;
	}

	@RequiresUser
	@RequestMapping(value = "orderManagementList", method = RequestMethod.GET)
	public String orderManagementList(HttpServletRequest request, Map<String, Object> map) {
		return ORDER_MANAGEMENT_LIST;
	}

	@RequestMapping(value = "pickupInStoresList", method = RequestMethod.GET)
	public String pickupInStoresList(HttpServletRequest request, Map<String, Object> map) {
		return PICKUP_IN_STORES_LIST;
	}

	@RequestMapping(value = "getOrderInfoByLatest", method = RequestMethod.POST)
	public @ResponseBody
	List<OrderInfo> getOrderInfoByLatest(HttpServletRequest request, Map<String, Object> map, int clientId) {
		List<OrderInfo> list = orderService.getOrderInfoByLatest(clientId);
		return list;
	}

	@RequestMapping(value = "getDispatchPageList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getDispatchPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = orderService.getTotalNum();
		List<OrderInfo> list = orderService.getDispatchPageList(page, rows);

		result.put("total", total);
		result.put("rows", list);
		return result;
	}

	@RequestMapping(value = "getNewOrderInfo", method = RequestMethod.POST)
	public @ResponseBody
	List<OrderInfo> getNewOrderInfo(HttpServletRequest request, Map<String, Object> map, int clientId) {
		List<OrderInfo> list = orderService.getNewOrderInfo(clientId);
		return list;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addOrder", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> addOrder(HttpServletRequest request, Map<String, Object> map, OrderInfo orderInfo, String partsStr, String order_appointment_time1,
			String order_appointment_time2, Date dealDate) throws JsonParseException, JsonMappingException, IOException {
		// 设置预约时间
		orderInfo.setOrder_appointment_time1(TimeUtils.formatAppointmentTime(order_appointment_time1));
		orderInfo.setOrder_appointment_time2(TimeUtils.formatAppointmentTime(order_appointment_time2));

		ObjectMapper mapper = new ObjectMapper();

		List<PartFeeInfo> list = new ArrayList<PartFeeInfo>();

		if (StringUtils.nonEmpty(partsStr)) {

			list = mapper.readValue(partsStr, new TypeReference<List<PartFeeInfo>>() {
			});
		}
		OrderInfoState os = new OrderInfoState();
		os.setId(OrderInfoStateCode.ALREADY_ORDER_BUT_NO_DISPATCHING);
		orderInfo.setState(os);

		orderInfo.setPayState(new PayState(PayStateCode.NO_PAY));

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		orderInfo.setOperator(user);

		if (dealDate == null) {
			dealDate = new Date();
		}

		if (!TimeUtils.compareOrderConform(orderInfo.getOrder_appointment_time1(), dealDate)) {
			return BaseDto.getFailResult(CommonCode.ORDER_TIME_NOT_CONFORM);
		}

		orderInfo.setReport_time(orderInfo.getOrder_appointment_time1());

		orderService.addOrderInfo(orderInfo, list, user);

		return BaseDto.getSuccessResult(null);
	}

	@RequestMapping(value = "cancelOrder", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> cancelOrder(HttpServletRequest request, Map<String, Object> map, Integer orderId, String cancelMsg) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		return orderService.cancelOrderInfo(orderId, cancelMsg, user);

	}

	@RequestMapping(value = "forceCacnel", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> forceCacnel(HttpServletRequest request, Map<String, Object> map, Integer orderId) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		return orderService.forceCacnel(orderId, user);

	}

	@RequestMapping(value = "cancelPickupInWH", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> cancelPickupInWH(HttpServletRequest request, Map<String, Object> map, Integer orderId) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		return orderService.cancelPickupInWH(orderId, user);
	}

	// 订单管理 （派工未派送）
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "cancelDeliveryOrder", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> cancelDeliveryOrder(HttpServletRequest request, Map<String, Object> map, Integer orderId) {

		boolean b = orderService.cancelDeliveryOrder(orderId);

		if (!b) {
			return BaseDto.getFailResult(CommonCode.ORDER_CAN_NOT_CANCEL);
		}

		return BaseDto.getSuccessResult(null);
	}

	@RequestMapping(value = "searchDispatchPageList", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> searchDispatchPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) throws ParseException {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		l.add("state.id = ?");
		filed.addInt(OrderInfoStateCode.ALREADY_ORDER_BUT_NO_DISPATCHING); // 下单未派送

		l.add("deliveryType.id = ?");
		filed.addInt(DeliveryTypeCode.ORDER_DELIVERY); // 订气派送

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if (user.getSecondCategory() != null) {
			l.add("secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId()); // 取对应门店
		}

		ToolsBarUtils.getOrderInfoSearchCondition(request, l, filed);

		Map<String, Object> result = new HashMap<String, Object>();
		Long total = orderService.getTotalNum(l, filed);
		List<OrderInfo> list = orderService.getOrderInfoPageList(l, filed, page, rows);

		result.put("total", total);
		result.put("rows", list);
		return result;
	}

	@RequestMapping(value = "searchOrderInfoList", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> searchOrderInfoList(HttpServletRequest request, Map<String, Object> map, int page, int rows, String sort, String order)
			throws ParseException {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if (user.getSecondCategory() != null) {
			l.add("secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId()); // 取对应门店
		}

		ToolsBarUtils.getOrderInfoSearchCondition(request, l, filed);

		Map<String, Object> result = new HashMap<String, Object>();
		Long total = orderService.getTotalNum(l, filed);
		List<SimpleOrderInfo> list = orderService.getSimpleOrderInfoPageList(l, filed, page, rows, 0, sort, order);

		Map<String, Integer> orderInfoStateTotalMap = orderService.getOrderInfoStateTotalNum(l, filed);
		Integer orderBottleNum = orderService.getOrderBottleNum(l, filed);

		result.put("total", total);
		result.put("rows", list);
		result.put("orderInfoStateTotalMap", orderInfoStateTotalMap);
		result.put("orderBottleNum", orderBottleNum);

		return result;
	}

	@RequestMapping(value = "exportOrderInfo", method = RequestMethod.POST)
	public void exportOrderInfo(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {

		List<ExportOrderDetailInfoHeads> heads = ExportHeadsUtils.getExportOrderDetailInfoHeads(request);

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if (user.getSecondCategory() != null) {
			l.add("secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId()); // 取对应门店
		}

		ToolsBarUtils.getOrderInfoSearchCondition(request, l, filed);

		List<SimpleOrderInfo> simpleOrderInfoList = orderService.getSimpleOrderInfoPageList(l, filed, 1, 1000000, 1, null, null);
		ExportUtils.exportExcel(response, heads, simpleOrderInfoList, "订单明细信息报表.xlsx", "template-OrderDetailInfo.xlsx");

	}

	@RequestMapping(value = "searchPickupInStoresList", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> searchPickupInStoresList(HttpServletRequest request, Map<String, Object> map, int page, int rows) throws ParseException {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		l.add("state.id < ?");
		filed.addInt(OrderInfoStateCode.ALREADY_FINISH); // 非历史订单

		l.add("deliveryType.id = ?");
		filed.addInt(DeliveryTypeCode.PICKUP_IN_STORES); // 门店自提

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if (user.getSecondCategory() != null) {
			l.add("secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId()); // 取对应门店
		}

		ToolsBarUtils.getOrderInfoSearchCondition(request, l, filed);

		Map<String, Object> result = new HashMap<String, Object>();
		Long total = orderService.getTotalNum(l, filed);
		List<OrderInfo> list = orderService.getOrderInfoPageList(l, filed, page, rows);

		result.put("total", total);
		result.put("rows", list);
		return result;
	}

	@RequestMapping(value = "dispatch", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<?> dispatch(HttpServletRequest request, Map<String, Object> map, Integer orderId, Integer deliveryManId) {

		OrderInfo orderInfo = orderService.findOrderInfoById(orderId);

		if (orderInfo.getPayType() != null && orderInfo.getPayState() != null) {
			if (orderInfo.getPayType().getId() == PayTypeCode.WECHAT_PAY && orderInfo.getPayState().getId() == PayStateCode.NO_PAY) {
				return BaseDto.getFailResult(CommonCode.ORDER_NOT_DISPATCH);
			}
		}

		if (orderInfo.getState().getId() != OrderInfoStateCode.ALREADY_ORDER_BUT_NO_DISPATCHING) {
			return BaseDto.getFailResult(CommonCode.ORDER_HAS_DISPATCH);
		}

		Date nowTime = new Date();

		User deliveryMan = new User();
		deliveryMan.setId(deliveryManId);

		OrderInfoState os = new OrderInfoState();
		os.setId(OrderInfoStateCode.ALREADY_DISPATCHING_BUT_NO_DELIVERY);

		orderInfo.setDelivery_man(deliveryMan);
		orderInfo.setDelivery_time(nowTime);
		orderInfo.setState(os);

		orderService.updateOrderInfo(orderInfo);

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addRemark", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> addRemark(HttpServletRequest request, Map<String, Object> map, Integer orderId, String remark) {

		boolean b = orderService.addRemark(orderId, remark);

		if (!b) {
			return BaseDto.getFailResult(CommonCode.ORDER_NOT_EXIST);
		}

		return BaseDto.getSuccessResult(null);
	}

	@RequestMapping(value = "getPirntOrderInfo", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Map<String, Object>> getPirntOrderInfo(HttpServletRequest request, Map<String, Object> map, Integer orderId) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		OrderInfo orderInfo = orderService.findOrderInfoById(orderId);

		FloorSubsidies floorSubsidies = floorSubsidiesService.findByMoney(orderInfo.getFloor_subsidies_money());

		OrderInfo lastOrderInfo = orderService.getLastOrderInfoByClientIdAndOrderId(orderInfo.getClientInfo().getId(), orderInfo.getId());
		Date lastOrderDate = null;
		if (lastOrderInfo != null) {
			lastOrderDate = lastOrderInfo.getOrder_time();
		}

		List<PartFeeInfo> partFeeInfos = partFeeInfoService.findByOrderId(orderId);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("print_print_operator", user.getFull_name());
		result.put("print_floor_subsidies_name", floorSubsidies.getFloor_subsidies_name());
		result.put("print_last_buy_time", lastOrderDate);
		result.put("print_pay_type", orderInfo.getPayType());
		result.put("print_pay_state", orderInfo.getPayState());
		result.put("partFeeInfos", partFeeInfos);

		return BaseDto.getSuccessResult(result);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addPrintTime", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Map<String, Object>> addPrintTime(HttpServletRequest request, Map<String, Object> map, Integer orderId) {

		CommonCode commonCode = orderService.addPrintTime(orderId);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getPirntTimes", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<CommonCode> getPirntTimes(HttpServletRequest request, Map<String, Object> map, Integer orderId) {

		// 判断打印次数限制
		ModuleConfigurationInfo printTimeMoudle = moduleConfigurationInfoService.findById(ModuleConfigurationCode.PRINT_TIMES_LIMIT);
		if (printTimeMoudle.getIs_open() == 1) {
			int printTimes = orderService.getPirntTimes(orderId);

			if (printTimes >= printTimeMoudle.getModule_parameter()) {
				return BaseDto.getFailResult(CommonCode.ORDER_CAN_OVER_LIMITS);
			}
		}

		OrderInfo orderInfo = orderService.findOrderInfoById(orderId);
		if (orderInfo.getPayType() != null && orderInfo.getPayState() != null) {
			if (orderInfo.getPayType().getId() == PayTypeCode.WECHAT_PAY && orderInfo.getPayState().getId() == PayStateCode.NO_PAY) {
				return BaseDto.getFailResult(CommonCode.ORDERCAN_NOT_PRINT_BY_WECHAT_NOT_PAY);
			}
		}
		
		ModuleConfigurationInfo dispatchingPrintModule = moduleConfigurationInfoService.findById(ModuleConfigurationCode.DISPATCHING_PRINT_LIMIT);
		if (dispatchingPrintModule.getIs_open() == 1) {
			if(orderInfo.getDeliveryType().getId() == DeliveryTypeCode.ORDER_DELIVERY && orderInfo.getState().getId() == OrderInfoStateCode.ALREADY_ORDER_BUT_NO_DISPATCHING){
				return BaseDto.getFailResult(CommonCode.ORDER_NO_DISPATCHING_CAN_NOT_PRINT);

			}
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "printCancel", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Map<String, Object>> printCancel(HttpServletRequest request, Map<String, Object> map, Integer orderId, String printNo) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		CommonCode commonCode = orderService.printCancel(orderId, printNo, user);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addNonQrcodeHeavyBottle", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Map<String, Object>> addNonQrcodeHeavyBottle(HttpServletRequest request, Map<String, Object> map, Integer orderId, String bottlecodes) {

		CommonCode commonCode = orderService.addNonQrcodeHeavyBottle(orderId, bottlecodes);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addNonQrcodeEmptyBottle", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Map<String, Object>> addNonQrcodeEmptyBottle(HttpServletRequest request, Map<String, Object> map, Integer orderId, String bottlecodes) {

		CommonCode commonCode = orderService.addNonQrcodeEmptyBottle(orderId, bottlecodes);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "speedUpOrder", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Map<String, Object>> speedUpOrder(HttpServletRequest request, Map<String, Object> map, Integer orderId) {

		CommonCode commonCode = orderService.speedUpOrder(orderId);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "cancelSpeedUpOrder", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Map<String, Object>> cancelSpeedUpOrder(HttpServletRequest request, Map<String, Object> map, Integer orderId) {

		CommonCode commonCode = orderService.cancelSpeedUpOrder(orderId);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "finishPartsOrder", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Map<String, Object>> finishPartsOrder(HttpServletRequest request, Map<String, Object> map, Integer orderId) {

		CommonCode commonCode = orderService.finishPartsOrder(orderId);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "editOrderStore", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Map<String, Object>> editOrderStore(HttpServletRequest request, Map<String, Object> map, Integer orderId, Integer sotreId) {

		CommonCode commonCode = orderService.editOrderStore(orderId, sotreId);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "returnQRcode", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Map<String, Object>> returnQRcode(HttpServletRequest request, Map<String, Object> map, Integer orderId) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		CommonCode commonCode = orderService.returnQRcode(orderId, user);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "invoice", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Map<String, Object>> invoice(HttpServletRequest request, Map<String, Object> map, Integer orderId) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		CommonCode commonCode = orderService.invoice(orderId, user);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "cancelInvoice", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Map<String, Object>> cancelInvoice(HttpServletRequest request, Map<String, Object> map, Integer orderId, String remark) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		CommonCode commonCode = orderService.cancelInvoice(orderId, remark, user);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@RequestMapping(value = "calFamilyCheckTime", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Integer> calFamilyCheckTime(HttpServletRequest request, Map<String, Object> map, Integer clientId) {

		int day = 0;

		ClientInfo clientInfo = clientInfoService.findById(clientId);

		if (clientInfo.getLast_family_check_time() != null) {
			day = TimeUtils.compareTimeByDay(clientInfo.getLast_family_check_time(), new Date());
		}

		return BaseDto.getSuccessResult(day);
	}

	@RequestMapping(value = "checkDispatch", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<?> checkDispatch(HttpServletRequest request, Map<String, Object> map, Integer orderId) {

		CommonCode commonCode = orderService.checkDispatch(orderId);

		return BaseDto.getUnKnowResult(null, commonCode);
	}


}
