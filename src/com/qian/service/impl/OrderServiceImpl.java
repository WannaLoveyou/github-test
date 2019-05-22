package com.qian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.app.entity.AppAddOrder;
import com.qian.code.AirBottleInventoryStateCode;
import com.qian.code.AirBottleStateCode;
import com.qian.code.AirBottleTrackingRecordCode;
import com.qian.code.ClientAirBottleReocrdStateCode;
import com.qian.code.DeliveryTypeCode;
import com.qian.code.InvoiceRecordStateCode;
import com.qian.code.ModuleConfigurationCode;
import com.qian.code.OperatorCode;
import com.qian.code.OrderInfoStateCode;
import com.qian.code.OrderPhtoStateCode;
import com.qian.code.PayStateCode;
import com.qian.code.PayTypeCode;
import com.qian.code.PermissionCode;
import com.qian.dao.impl.AirBottleInfoDaoImpl;
import com.qian.dao.impl.AirBottleTrackingRecordDaoImpl;
import com.qian.dao.impl.AirBottleTypeDaoImpl;
import com.qian.dao.impl.CardMoneyPaymentRecordDaoImpl;
import com.qian.dao.impl.CardMoneyRechargeRecordDaoImpl;
import com.qian.dao.impl.ClientAirBottleRecordDaoImpl;
import com.qian.dao.impl.ClientAirBottleTypeFeeDaoImpl;
import com.qian.dao.impl.ClientInfoDaoImpl;
import com.qian.dao.impl.DeliveryTypeDaoImpl;
import com.qian.dao.impl.FloorSubsidiesDaoImpl;
import com.qian.dao.impl.InvoiceRecordDaoImpl;
import com.qian.dao.impl.ModuleConfigurationInfoDaoImpl;
import com.qian.dao.impl.OrderAirBottleInfoDaoImpl;
import com.qian.dao.impl.OrderDaoImpl;
import com.qian.dao.impl.OrderEmptyBottleInfoDaoImpl;
import com.qian.dao.impl.OrderInfoStateDaoImpl;
import com.qian.dao.impl.OrderPhotoInfoDaoImpl;
import com.qian.dao.impl.PartFeeInfoDaoImpl;
import com.qian.dao.impl.PayStateDaoImpl;
import com.qian.dao.impl.PayTypeDaoImpl;
import com.qian.dao.impl.PrintCancelInfoDaoImpl;
import com.qian.dao.impl.SecondCategoryDaoImpl;
import com.qian.dao.impl.StoreCustomAirBottlePriceInfoDaoImpl;
import com.qian.dao.impl.StoreInventoryInfoDaoImpl;
import com.qian.dao.impl.UserDaoImpl;
import com.qian.dao.impl.WarehouseInfoDaoImpl;
import com.qian.dao.impl.WechatFirstOrderDiscountInfoDaoImpl;
import com.qian.entity.AirBottleInfo;
import com.qian.entity.AirBottleInventoryState;
import com.qian.entity.AirBottleState;
import com.qian.entity.AirBottleType;
import com.qian.entity.CardMoneyPaymentRecord;
import com.qian.entity.CardMoneyRechargeRecord;
import com.qian.entity.ClientAirBottleTypeFee;
import com.qian.entity.ClientInfo;
import com.qian.entity.DeliveryType;
import com.qian.entity.FloorSubsidies;
import com.qian.entity.InvoiceRecord;
import com.qian.entity.InvoiceRecordState;
import com.qian.entity.ModuleConfigurationInfo;
import com.qian.entity.OrderAirBottleInfo;
import com.qian.entity.OrderEmptyBottleInfo;
import com.qian.entity.OrderInfo;
import com.qian.entity.OrderInfoState;
import com.qian.entity.OrderPhotoInfo;
import com.qian.entity.PartFeeInfo;
import com.qian.entity.PayState;
import com.qian.entity.PayType;
import com.qian.entity.PrintCancelInfo;
import com.qian.entity.SecondCategory;
import com.qian.entity.StoreCustomAirBottlePriceInfo;
import com.qian.entity.StoreInventoryInfo;
import com.qian.entity.User;
import com.qian.entity.WechatFirstOrderDiscountInfo;
import com.qian.mobile.entity.MobileOrderInfo;
import com.qian.service.OrderService;
import com.qian.util.AirBottleCodeInitUtils;
import com.qian.util.AirBottleTrackingRecordUtils;
import com.qian.util.CodeUtils;
import com.qian.util.HttpUtils;
import com.qian.util.InitDataUtils;
import com.qian.util.JSONUtils;
import com.qian.util.PKIUtil;
import com.qian.util.SignUtils;
import com.qian.util.StringUtils;
import com.qian.util.TimeUtils;
import com.qian.util.UniqueIDGenerator;
import com.qian.util.WebServiceUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.DeliveryManDeliveryFeeInfoVo;
import com.qian.vo.Field;
import com.qian.vo.OrderCycleInfoVo;
import com.qian.vo.RongXinTongDetailVo;
import com.qian.vo.RongXinTongVo;
import com.qian.vo.SaleDetailReportInfoVo;
import com.qian.vo.SaleReportInfoSummaryByDifferentPrice;
import com.qian.vo.SaleReportInfoVo;
import com.qian.vo.SimpleOrderInfo;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private static Logger log = Logger.getLogger(OrderServiceImpl.class);

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private OrderDaoImpl orderDAOImpl;

	@Autowired
	private ClientInfoDaoImpl clientInfoDaoImpl;

	@Autowired
	private StoreInventoryInfoDaoImpl storeInventoryInfoDaoImpl;

	@Autowired
	private OrderAirBottleInfoDaoImpl orderAirBottleInfoDaoImpl;

	@Autowired
	private OrderEmptyBottleInfoDaoImpl orderEmptyBottleInfoDaoImpl;

	@Autowired
	private AirBottleTrackingRecordDaoImpl airBottleTrackingRecordDaoImpl;

	@Autowired
	private AirBottleInfoDaoImpl airBottleInfoDaoImpl;

	@Autowired
	private ClientAirBottleRecordDaoImpl clientAirBottleRecordDaoImpl;

	@Autowired
	private PartFeeInfoDaoImpl partFeeInfoDaoImpl;

	@Autowired
	private OrderPhotoInfoDaoImpl orderPhotoInfoDaoImpl;

	@Autowired
	private AirBottleTypeDaoImpl airBottleTypeDaoImpl;

	@Autowired
	private OrderInfoStateDaoImpl orderInfoStateDaoImpl;

	@Autowired
	private FloorSubsidiesDaoImpl floorSubsidiesDaoImpl;

	@Autowired
	private CardMoneyPaymentRecordDaoImpl cardMoneyPaymentRecordDaoImpl;

	@Autowired
	private CardMoneyRechargeRecordDaoImpl cardMoneyRechargeRecordDaoImpl;

	@Autowired
	private ClientAirBottleTypeFeeDaoImpl clientAirBottleTypeFeeDaoImpl;

	@Autowired
	private DeliveryTypeDaoImpl deliveryTypeDaoImpl;

	@Autowired
	private PayStateDaoImpl payStateDaoImpl;

	@Autowired
	private PayTypeDaoImpl payTypeDaoImpl;

	@Autowired
	private SecondCategoryDaoImpl secondCategoryDaoImpl;

	@Autowired
	private PrintCancelInfoDaoImpl printCancelInfoDaoImpl;

	@Autowired
	private StoreCustomAirBottlePriceInfoDaoImpl storeCustomAirBottlePriceInfoDaoImpl;

	@Autowired
	private ModuleConfigurationInfoDaoImpl moduleConfigurationInfoDaoImpl;

	@Autowired
	private InvoiceRecordDaoImpl invoiceRecordDaoImpl;

	@Autowired
	private WarehouseInfoDaoImpl warehouseDaoImpl;

	@Autowired
	private WechatFirstOrderDiscountInfoDaoImpl wechatFirstOrderDiscountInfoDaoImpl;

	public List<OrderInfo> getOrderInfoByLatest(int clientId) {
		return orderDAOImpl.getOrderInfoByLatest(clientId);
	}

	public List<OrderInfo> getNewOrderInfo(int clientId) {
		return orderDAOImpl.getNewOrderInfo(clientId);
	}

	public OrderInfo addOrderInfo(OrderInfo orderInfo, List<PartFeeInfo> partFeeInfos, User operator) {

		Date nowTime = new Date();

		ClientInfo clientInfo = clientInfoDaoImpl.findById(orderInfo.getClientInfo().getId());
		clientInfo.setLast_order_time(nowTime);
		clientInfoDaoImpl.update(clientInfo);

		orderInfo.setOrder_time(nowTime);
		orderInfo.setSecondCategory(clientInfo.getSecondCategory());

		orderDAOImpl.insert(orderInfo);

		orderInfo.setOrder_code(CodeUtils.setOrderCode(orderInfo));

		orderDAOImpl.update(orderInfo);

		for (PartFeeInfo partFeeInfo : partFeeInfos) {
			partFeeInfo.setOrderInfo(orderInfo);
		}

		partFeeInfoDaoImpl.insert(partFeeInfos);

		switch (orderInfo.getPayType().getId()) {

		case PayTypeCode.IC_CARD:
			payByICCard(clientInfo, orderInfo, operator);
			break;
		}

		return orderInfo;
	}

	public List<OrderInfo> getDispatchPageList(int page, int rows) {

		return orderDAOImpl.getDispatchPageList(page, rows);
	}

	public long getTotalNum() {

		return orderDAOImpl.getTotalNum();
	}

	public OrderInfo updateOrderInfo(OrderInfo orderInfo) {

		orderDAOImpl.update(orderInfo);

		return orderInfo;
	}

	// 取消订单并生成撤销记录
	@SuppressWarnings("unchecked")
	public BaseDto<Object> cancelOrderInfo(int orderId, String cancelMsg, User user) {

		OrderInfo orderInfo = orderDAOImpl.findById(orderId);

		if (orderInfo.getState().getId() == OrderInfoStateCode.ALREADY_FINISH
				|| orderInfo.getState().getId() == OrderInfoStateCode.ALREADY_DELIVERY_BUT_NO_BACK) {
			return BaseDto.getFailResult(CommonCode.ORDER_CAN_NOT_CANCEL);
		}

		// 已开发票订单不能撤单
		InvoiceRecord invoiceRecord = invoiceRecordDaoImpl.findByOrderId(orderId);
		if (invoiceRecord != null) {
			return BaseDto.getFailResult(CommonCode.ORDER_HAS_INVOICE);
		}

		int airBottleNum = orderAirBottleInfoDaoImpl.findOrderNumByOrder(orderId).intValue();

		int emptyBottleNum = orderEmptyBottleInfoDaoImpl.findOrderNumByOrder(orderId).intValue();

		if (airBottleNum > 0 || emptyBottleNum > 0) {
			return BaseDto.getFailResult(CommonCode.ORDER_CAN_NOT_CANCEL);
		}

		Date nowTime = new Date();

		if (orderInfo.getPayType().getId() == PayTypeCode.WECHAT_PAY) {

			if (orderInfo.getPayState().getId() == PayStateCode.HAS_PAY) {
				if (user.getId() != OperatorCode.NEW_WEIXIN) {
					Subject subject = SecurityUtils.getSubject();
					if (!subject.isPermitted(PermissionCode.CANCEL_WECHAT)) {
						return BaseDto.getFailResult(CommonCode.ORDER_CAN_NOT_CANCEL_BY_WECHAT_HAS_PAY);
					}
				}
			}

			if (orderInfo.getPayState().getId() == PayStateCode.NO_PAY) {
				if (!TimeUtils.compareTimeByMin(nowTime, orderInfo.getOrder_time(), 5)) {
					return BaseDto.getFailResult(CommonCode.ORDER_HAS_NOT_CANCEL_FOR_FIVE_MIN);
				}
			}
		}

		orderInfo.setState(new OrderInfoState(OrderInfoStateCode.ALREADY_CANCEL));

		orderDAOImpl.update(orderInfo);

		returnMoney(orderInfo, user);

		return BaseDto.getSuccessResult(null);
	}

	public OrderInfo findOrderInfoById(int orderId) {

		return orderDAOImpl.findById(orderId);
	}

	public List<OrderInfo> getOrderInfoPageList(List<String> list, Field filed, int page, int rows) {

		return orderDAOImpl.getOrderInfoPageList(list, filed, page, rows);
	}

	public long getTotalNum(List<String> list, Field filed) {

		return orderDAOImpl.getTotalNum(list, filed);
	}

	public List<MobileOrderInfo> getOrderInfoPageList(List<String> l, Field filed) {

		List<OrderInfo> list = orderDAOImpl.getOrderInfoPageList(l, filed);

		List<MobileOrderInfo> result = new ArrayList<MobileOrderInfo>();

		for (OrderInfo oInfo : list) {

			MobileOrderInfo moInfo = new MobileOrderInfo(oInfo);

			result.add(moInfo);
		}

		return result;
	}

	public boolean cancelDeliveryOrder(int orderId) {

		OrderInfo orderInfo = orderDAOImpl.findById(orderId);

		if (orderInfo.getState().getId() != OrderInfoStateCode.ALREADY_DISPATCHING_BUT_NO_DELIVERY) {
			return false;
		}

		OrderInfoState os = new OrderInfoState();
		os.setId(OrderInfoStateCode.ALREADY_ORDER_BUT_NO_DISPATCHING);

		orderInfo.setDelivery_man(null);
		orderInfo.setDelivery_time(null);
		orderInfo.setState(os);

		orderDAOImpl.update(orderInfo);

		return true;
	}

	public boolean fillEmptyBottlePhotoByPickUpInSotres(int orderId, List<String> urls, User user) {

		return doitFillEmptyBottlePhotoInOrder(orderId, urls, user);
	}

	public boolean fillEmptyBottlePhotoInOrder(int orderId, List<String> urls, User user) {

		return doitFillEmptyBottlePhotoInOrder(orderId, urls, user);
	}

	public boolean doitFillEmptyBottlePhotoInOrder(int orderId, List<String> urls, User user) {

		if (urls.size() == 0) {
			return false;
		}

		List<OrderPhotoInfo> orderPhotoInfoList = new ArrayList<OrderPhotoInfo>();

		OrderInfo orderInfo = orderDAOImpl.findById(orderId);

		for (String url : urls) {

			OrderPhotoInfo orderPhotoInfo = new OrderPhotoInfo();

			orderPhotoInfo.setOrderInfo(orderInfo);
			orderPhotoInfo.setUrl(url);
			orderPhotoInfo.setOperator(user);

			orderPhotoInfoList.add(orderPhotoInfo);

		}

		// 新增拍照信息
		orderPhotoInfoDaoImpl.insert(orderPhotoInfoList);

		// 现金支付更改支付状态
		orderInfo.setHas_photo(OrderPhtoStateCode.HAS_PHOTO);
		orderInfo.setState(new OrderInfoState(OrderInfoStateCode.ALREADY_FINISH));// 历史订单

		if (orderInfo.getPayType().getId() == PayTypeCode.CASH) {
			orderInfo.setPayState(new PayState(PayStateCode.HAS_PAY));
		}

		orderDAOImpl.update(orderInfo);

		return true;
	}

	public Boolean addWeixinOrder(ClientInfo clientInfo, OrderInfo orderInfoTemp) {

		OrderInfo order = new OrderInfo();

		order.setClientInfo(clientInfo);
		order.setAirBottleType(orderInfoTemp.getAirBottleType());
		order.setOrder_number(orderInfoTemp.getOrder_number());
		order.setOrder_address(orderInfoTemp.getOrder_address());
		order.setOrder_tel_number(orderInfoTemp.getOrder_tel_number());
		order.setRemark(orderInfoTemp.getRemark());
		order.setFloor_subsidies_money(orderInfoTemp.getFloor_subsidies_money());
		order.setDelivery_fee(orderInfoTemp.getDelivery_fee());
		order.setPrice(orderInfoTemp.getPrice());
		order.setTotal_amount(orderInfoTemp.getTotal_amount());
		order.setOrder_appointment_time1(orderInfoTemp.getOrder_appointment_time1());

		order.setCheck_fee(0);
		order.setDiscount_amount(0);
		order.setFee_total_amount(0);

		DeliveryType deliveryType = new DeliveryType();
		deliveryType.setId(DeliveryTypeCode.ORDER_DELIVERY);
		order.setDeliveryType(deliveryType);

		PayType payType = new PayType();
		payType.setId(PayTypeCode.CASH);
		order.setPayType(payType);

		order.setPayState(new PayState(PayStateCode.NO_PAY));

		OrderInfoState os = new OrderInfoState();
		os.setId(OrderInfoStateCode.ALREADY_ORDER_BUT_NO_DISPATCHING);
		order.setState(os);

		order.setOrder_time(new Date());

		User operator = userDaoImpl.findById(OperatorCode.WEIXIN);
		order.setOperator(operator);

		orderDAOImpl.insert(order);

		order.setOrder_code(CodeUtils.setOrderCode(order));

		orderDAOImpl.update(order);

		return true;
	}

	public List<SaleReportInfoVo> getAllOrderInfo(List<String> l, Field filed) {

		return orderDAOImpl.getAllOrderInfo(l, filed);
	}

	public CommonCode heavyBottleBackFromClient(String bottleCodes, User user) {

		Date nowTime = new Date();

		String[] bottleCodesStr = bottleCodes.split(",");

		if (user.getSecondCategory() == null) {
			return CommonCode.NOT_STORE_MAN;
		}

		for (String bottleCode : bottleCodesStr) {

			bottleCode = AirBottleCodeInitUtils.initCode(bottleCode);

			AirBottleInfo airBottleInfo = airBottleInfoDaoImpl.findByAirBottleCode(bottleCode);

			ClientAirBottleRecord clientAirBottleRecord = clientAirBottleRecordDaoImpl.getNoReturnClientAirBottleRecord(bottleCode);

			if (clientAirBottleRecord == null) {
				return CommonCode.USER_HAS_NOT_BOTTLE;
			}

			OrderAirBottleInfo orderAirBottleInfo = orderAirBottleInfoDaoImpl.findByClinetIdAndAirBottleCode(clientAirBottleRecord.getClientInfo().getId(),
					bottleCode);

			int orderId = orderAirBottleInfo.getOrderInfo().getId();

			List<OrderEmptyBottleInfo> orderEmptyBottleInfos = orderEmptyBottleInfoDaoImpl.findByOrderId(orderId);

			if (orderEmptyBottleInfos.size() > 0) {
				return CommonCode.ORDER_HAS_EMPTY_BOTTLE_NOT_FINISH;
			}

			if (orderAirBottleInfo != null) {
				orderAirBottleInfoDaoImpl.delete(orderAirBottleInfo);
			}

			int orderNum = orderAirBottleInfoDaoImpl.findOrderNumByOrder(orderId).intValue();

			OrderInfo orderInfo = orderDAOImpl.findById(orderId);

			if (orderNum < orderInfo.getOrder_number()) {

				orderInfo.setState(new OrderInfoState(OrderInfoStateCode.ALREADY_ORDER_BUT_NO_DISPATCHING));

				orderDAOImpl.update(orderInfo);

			}

			StoreInventoryInfo storeInventoryInfo = new StoreInventoryInfo();

			storeInventoryInfo.setSecondCategory(user.getSecondCategory());

			storeInventoryInfo.setAirBottleInfo(airBottleInfo);

			storeInventoryInfo.setAirBottleState(new AirBottleState(AirBottleStateCode.HEAVY_BOTTLE));

			storeInventoryInfo.setAirBottleInventoryState(new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE));

			storeInventoryInfo.setOperator(user);

			storeInventoryInfo.setCreate_time(nowTime);

			storeInventoryInfoDaoImpl.insert(storeInventoryInfo);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, airBottleInfo, user.getSecondCategory(), null, null,
					clientAirBottleRecord.getClientInfo(), AirBottleTrackingRecordCode.HEAVY_BOTTLE_BACK_FROM_CLIENT_BY_STORE, user);

			clientAirBottleRecordDaoImpl.delete(clientAirBottleRecord);

		}

		return CommonCode.OK;
	}

	public CommonCode mobileAddStoreOrder(String bottleCodes, User user, ClientInfo clientInfo, int bottleTypeId, OrderInfo orderInfoReturn) {

		Date nowTime = new Date();

		int OrderNum = bottleCodes.split(",").length;

		OrderInfo orderInfo = new OrderInfo();

		AirBottleType airBottleType = airBottleTypeDaoImpl.findById(bottleTypeId);
		orderInfo.setAirBottleType(airBottleType);

		double price = airBottleType.getPrice();

		// 查询优惠门店
		StoreCustomAirBottlePriceInfo storeCustomAirBottlePriceInfo = storeCustomAirBottlePriceInfoDaoImpl.findByairBottleTypeIdAndSecondCategoryId(
				bottleTypeId, clientInfo.getSecondCategory().getId());
		if (storeCustomAirBottlePriceInfo != null) {
			if (storeCustomAirBottlePriceInfo.getCustom_price() != null) {
				price = storeCustomAirBottlePriceInfo.getCustom_price();
			}
		}

		orderInfo.setClientInfo(clientInfo);

		orderInfo.setOrder_appointment_time1(nowTime);
		orderInfo.setOrder_tel_number((InitDataUtils.findOrderNumber(clientInfo)));
		orderInfo.setOrder_address(clientInfo.getClient_address());

		orderInfo.setFloor_subsidies_money(0.0);
		orderInfo.setDelivery_fee(0.0);

		orderInfo.setPrice(price);
		orderInfo.setOrder_number(OrderNum);
		orderInfo.setTotal_amount(orderInfo.getPrice() * OrderNum);

		orderInfo.setCheck_fee(0);
		orderInfo.setDiscount_amount(0);
		orderInfo.setFee_total_amount(0);

		DeliveryType deliveryType = new DeliveryType();
		deliveryType.setId(DeliveryTypeCode.PICKUP_IN_STORES);
		orderInfo.setDeliveryType(deliveryType);

		PayType payType = new PayType();
		payType.setId(PayTypeCode.CASH);
		orderInfo.setPayType(payType);

		OrderInfoState os = new OrderInfoState();
		os.setId(OrderInfoStateCode.ALREADY_ORDER_BUT_NO_DISPATCHING);
		orderInfo.setState(os);

		orderInfo.setOrder_time(nowTime);
		orderInfo.setReport_time(nowTime);

		orderInfo.setOperator(user);

		orderInfo.setRemark("");

		// 修改受理门店
		orderInfo.setSecondCategory(clientInfo.getSecondCategory());

		orderDAOImpl.insert(orderInfo);

		orderInfo.setOrder_code(CodeUtils.setOrderCode(orderInfo));

		orderDAOImpl.update(orderInfo);

		boolean b = fillHeavyBottleByPickUpInSotres(bottleCodes, orderInfo.getId(), user);

		if (!b) {
			orderDAOImpl.delete(orderInfo); // 网络延时导致重复单
			return CommonCode.NOT_EXIST_AIR_BOTTLE;
		}

		BeanUtils.copyProperties(orderInfo, orderInfoReturn);

		return CommonCode.OK;
	}

	public List<SaleDetailReportInfoVo> getAllOrderInfoByDate(List<String> orderInfoSearchConditionList, Field orderInfoSearchConditionFiled) {

		return orderDAOImpl.getAllOrderInfoByDate(orderInfoSearchConditionList, orderInfoSearchConditionFiled);
	}

	public Map<String, Integer> getOrderInfoStateTotalNum(List<String> l, Field filed) {

		Map<String, Integer> result = new HashMap<String, Integer>();

		List<OrderInfoState> orderInfoStates = orderInfoStateDaoImpl.getAllList();

		for (OrderInfoState orderInfoState : orderInfoStates) {

			List<String> tmpList = new ArrayList<String>();
			Field tmpField = new Field();

			tmpList.addAll(l);
			tmpField.setFields(filed.getFields());

			tmpList.add("state.id = ?");
			tmpField.addInt(orderInfoState.getId());

			Long num = orderDAOImpl.getTotalNum(tmpList, tmpField);

			result.put(orderInfoState.getState_description(), num.intValue());
		}

		return result;
	}

	public boolean addRemark(Integer orderId, String remark) {

		OrderInfo orderInfo = orderDAOImpl.findById(orderId);

		if (orderInfo == null) {
			return false;
		}

		orderInfo.setRemark(remark);

		orderDAOImpl.update(orderInfo);

		return true;
	}

	public List<DeliveryManDeliveryFeeInfoVo> getAllDeliveryManDeliveryFeeInfo(List<String> l, Field filed) {

		return orderDAOImpl.getAllDeliveryManDeliveryFeeInfo(l, filed);
	}

	public Integer getOrderBottleNum(List<String> l, Field filed) {

		return orderDAOImpl.getOrderBottleNum(l, filed);
	}

	public OrderInfo addAppOrderInfo(AppAddOrder appAddOrder) {

		Date nowTime = new Date();

		OrderInfo order = new OrderInfo();

		ClientInfo clientInfo = clientInfoDaoImpl.findById(appAddOrder.getClientId());
		order.setClientInfo(clientInfo);

		AirBottleType airBottleType = airBottleTypeDaoImpl.findById(appAddOrder.getAirBottleTypeId());
		order.setAirBottleType(airBottleType);

		order.setOrder_number(appAddOrder.getOrderNumber());

		order.setOrder_address(appAddOrder.getOrderAddress());

		order.setOrder_tel_number(appAddOrder.getOrderTelNumber());

		order.setRemark(appAddOrder.getRemark());

		FloorSubsidies floorSubsidies = floorSubsidiesDaoImpl.findById(appAddOrder.getFloorSubsidiesId());

		order.setFloor_subsidies_money(floorSubsidies.getFloor_subsidies_money());

		// 运费处理
		double deliveryFee = airBottleType.getDelivery_fee();

		ClientAirBottleTypeFee clientAirBottleTypeFee = clientAirBottleTypeFeeDaoImpl.getDeliveryandCheckFee(airBottleType.getId(), clientInfo.getId());

		if (clientAirBottleTypeFee != null) {
			if (clientAirBottleTypeFee.getDelivery_fee() != null) {
				deliveryFee = clientAirBottleTypeFee.getDelivery_fee();
			}
		}

		order.setDelivery_fee(deliveryFee);

		Date appointmentTime = TimeUtils.formatAppointmentTime(appAddOrder.getOrderAppointmentTime());
		order.setOrder_appointment_time1(appointmentTime);

		PayType payType = new PayType();
		payType.setId(appAddOrder.getPayTypeId());
		order.setPayType(payType);

		order.setCheck_fee(0);
		order.setDiscount_amount(0);
		order.setFee_total_amount(0);

		// if (order.getPayType().getId() == PayTypeCode.WECHAT_PAY) {
		order.setPrice(airBottleType.getWeixin_discounty_fee()); // 微信支付使用微信价格计算
		// } else {
		// order.setPrice(airBottleType.getPrice()); // 微信支付使用微信价格计算
		// }

		Double totalAmount = (order.getPrice() + order.getDelivery_fee() + order.getFloor_subsidies_money()) * order.getOrder_number();
		order.setTotal_amount(totalAmount);

		// 首单优惠
		boolean wechatFirstOrderDiscountFlag = false;
		ModuleConfigurationInfo moduleConfigurationInfo = moduleConfigurationInfoDaoImpl.findById(ModuleConfigurationCode.WECHAT_FIRST_ORDER_DISCOUNT);
		if (moduleConfigurationInfo != null && moduleConfigurationInfo.getIs_open() != 0) {
			if (StringUtils.nonEmpty(appAddOrder.getOpenid())) {
				WechatFirstOrderDiscountInfo wechatFirstOrderDiscountInfo = wechatFirstOrderDiscountInfoDaoImpl.findByOpenId(appAddOrder.getOpenid());
				if (wechatFirstOrderDiscountInfo == null) {
					wechatFirstOrderDiscountFlag = true;
					order.setDiscount_amount(moduleConfigurationInfo.getModule_parameter());
					order.setTotal_amount(order.getTotal_amount() - order.getDiscount_amount());
				}
			}
		}

		DeliveryType deliveryType = new DeliveryType();
		deliveryType.setId(DeliveryTypeCode.ORDER_DELIVERY);
		order.setDeliveryType(deliveryType);

		OrderInfoState os = new OrderInfoState();
		os.setId(OrderInfoStateCode.ALREADY_ORDER_BUT_NO_DISPATCHING);
		order.setState(os);

		order.setOrder_time(nowTime);
		order.setReport_time(appointmentTime);

		User operator = userDaoImpl.findById(OperatorCode.NEW_WEIXIN);
		order.setOperator(operator);

		order.setPayState(new PayState(PayStateCode.NO_PAY));

		// 修改受理门店
		order.setSecondCategory(clientInfo.getSecondCategory());

		orderDAOImpl.insert(order);

		order.setOrder_code(CodeUtils.setOrderCode(order));

		orderDAOImpl.update(order);

		if (wechatFirstOrderDiscountFlag) {
			WechatFirstOrderDiscountInfo wechatFirstOrderDiscountInfo = new WechatFirstOrderDiscountInfo(appAddOrder.getOpenid(), order.getOrder_code(),
					moduleConfigurationInfo.getModule_parameter(), nowTime);
			wechatFirstOrderDiscountInfoDaoImpl.insert(wechatFirstOrderDiscountInfo);
		}

		switch (order.getPayType().getId()) {

		case PayTypeCode.IC_CARD:
			payByICCard(clientInfo, order, operator);
			break;
		}

		return order;

	}

	private void payByICCard(ClientInfo clientInfo, OrderInfo order, User operator) {

		double money = clientInfo.getCard_money() - order.getTotal_amount();

		if (money < 0) {

			return;
		}

		// 更新订单信息
		order.setPayState(new PayState(PayStateCode.HAS_PAY));

		orderDAOImpl.update(order);

		// 更新用户预存款
		clientInfo.setCard_money(money);

		clientInfoDaoImpl.update(clientInfo);

		// 插入预存款记录
		CardMoneyPaymentRecord cardMoneyPaymentRecord = new CardMoneyPaymentRecord();

		cardMoneyPaymentRecord.setClientInfo(clientInfo);

		cardMoneyPaymentRecord.setPayment_money(order.getTotal_amount());

		cardMoneyPaymentRecord.setOperator(operator);

		cardMoneyPaymentRecord.setPayment_time(new Date());

		cardMoneyPaymentRecordDaoImpl.insert(cardMoneyPaymentRecord);

	}

	public CommonCode savePrepayId(int orderId, String prepayId) {

		OrderInfo order = orderDAOImpl.findById(orderId);

		if (order == null) {
			return CommonCode.ORDER_NOT_EXIST;
		}

		order.setWeixin_prepay_id(prepayId);

		orderDAOImpl.update(order);

		return CommonCode.OK;
	}

	public boolean weixinPaySuccess(Map<String, Object> resultMap) {

		if (!("SUCCESS".equals(resultMap.get("return_code").toString()))) {
			log.info("return_code" + resultMap.get("return_code").toString());
			return false;
		}

		if (!("SUCCESS".equals(resultMap.get("result_code").toString()))) {
			log.info("result_code" + resultMap.get("result_code").toString());
			return false;
		}

		String sign = resultMap.get("sign").toString();

		String mySign = SignUtils.weiXinSign(resultMap);
		if (!sign.equals(mySign)) {
			return false;
		}

		OrderInfo order = orderDAOImpl.findById(Integer.valueOf(resultMap.get("out_trade_no").toString()));

		if (order == null) {
			return false;
		}

		order.setPayState(new PayState(PayStateCode.HAS_PAY));
		orderDAOImpl.update(order);

		return true;
	}

	public CommonCode cancelAppOrder(int orderId, String remark) {

		Date nowTime = new Date();

		OrderInfo order = orderDAOImpl.findById(orderId);

		if (order == null) {
			return CommonCode.ORDER_NOT_EXIST;
		}

		if (order.getState().getId() != OrderInfoStateCode.ALREADY_ORDER_BUT_NO_DISPATCHING) {
			return CommonCode.ORDER_CAN_NOT_CANCEL;
		}

		if (order.getPayType().getId() == PayTypeCode.WECHAT_PAY) {

			// 未支付的微信订单5分钟内不能撤单
			if (order.getPayState().getId() == PayStateCode.NO_PAY) {
				if (!TimeUtils.compareTimeByMin(nowTime, order.getOrder_time(), 5)) {
					return CommonCode.ORDER_CAN_NOT_CANCEL;
				}
			}

			// 已支付的订单不能通过取消支付操作撤单
			if (order.getPayState().getId() == PayStateCode.HAS_PAY) {
				if (remark.equals("用户取消支付")) {
					return CommonCode.ORDER_CAN_NOT_CANCEL;
				}
			}

		}

		User operator = userDaoImpl.findById(OperatorCode.NEW_WEIXIN);

		cancelOrderInfo(orderId, remark, operator);

		return CommonCode.OK;
	}

	private boolean returnMoney(OrderInfo order, User operator) {

		if (order.getPayState() == null) {
			return true;
		}

		if (order.getPayState().getId() == PayStateCode.NO_PAY) {
			return true;
		}

		boolean b = false;

		switch (order.getPayType().getId()) {

		case PayTypeCode.CASH:
			b = true;
			break;

		case PayTypeCode.IC_CARD:
			b = returnMoneyByICCard(order.getClientInfo(), order, operator);
			break;

		case PayTypeCode.WECHAT_PAY:
			b = returnMoneyByWeChat(order);
			break;
		}

		return b;

	}

	private boolean returnMoneyByWeChat(OrderInfo order) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appid", SignUtils.WeiXinAppid);
		map.put("mch_id", SignUtils.WeiXinMchId);
		map.put("nonce_str", UniqueIDGenerator.getUUID());
		map.put("out_trade_no", order.getId());
		map.put("out_refund_no", order.getId());
		int returnMoney = (int) (order.getTotal_amount() * 100);
		map.put("total_fee", returnMoney);
		map.put("refund_fee", returnMoney);
		map.put("op_user_id", SignUtils.WeiXinAppid);
		map.put("sign", SignUtils.weiXinSign(map));

		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append("<" + entry.getKey() + "><![CDATA[" + entry.getValue().toString() + "]]></" + entry.getKey() + ">");
		}
		sb.append("</xml>");

		log.info("-------------------------------------------------WeiXinReturnMoney------------------------------------------------");
		log.info(sb);
		log.info("-------------------------------------------------WeiXinReturnMoney------------------------------------------------");

		String weiXinResult = null;
		try {
			weiXinResult = HttpUtils.refundByWeiXin(SignUtils.WeiXinReturnMoneyUrl, sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("-------------------------------------------------WeiXinReturnMoneyResult------------------------------------------------");
		log.info(weiXinResult);
		log.info("-------------------------------------------------WeiXinReturnMoneyResult------------------------------------------------");

		Map<String, Object> resultMap = JSONUtils.xmlToJSON(weiXinResult);

		if (!("SUCCESS".equals(resultMap.get("return_code").toString()))) {
			return false;
		}

		if (!("SUCCESS".equals(resultMap.get("result_code").toString()))) {
			return false;
		}

		return true;
	}

	private boolean returnMoneyByICCard(ClientInfo clientInfo, OrderInfo order, User operator) {

		double money = clientInfo.getCard_money() + order.getTotal_amount();

		clientInfo.setCard_money(money);

		clientInfoDaoImpl.update(clientInfo);

		CardMoneyRechargeRecord cardMoneyRechargeRecord = new CardMoneyRechargeRecord();

		cardMoneyRechargeRecord.setClientInfo(clientInfo);
		cardMoneyRechargeRecord.setRecharge_money(order.getTotal_amount());
		cardMoneyRechargeRecord.setGift_money(0.0);
		cardMoneyRechargeRecord.setRecharge_time(new Date());
		cardMoneyRechargeRecord.setPayType(new PayType(PayTypeCode.IC_CARD));
		cardMoneyRechargeRecord.setOperator(operator);

		cardMoneyRechargeRecordDaoImpl.insert(cardMoneyRechargeRecord);

		return true;

	}

	public OrderInfo getLastOrderInfoByClientIdAndOrderId(int clientId, int orderId) {

		return orderDAOImpl.getLastOrderInfoByClientIdAndOrderId(clientId, orderId);
	}

	public CommonCode addPrintTime(Integer orderId) {

		OrderInfo orderInfo = orderDAOImpl.findById(orderId);

		orderInfo.setPrint_times(orderInfo.getPrint_times() + 1);

		orderDAOImpl.update(orderInfo);

		return CommonCode.OK;
	}

	public CommonCode heavyBottleChangeFromClient(String oldBottleCode, String newBottleCode, User user) {

		if (user.getSecondCategory() == null) {
			return CommonCode.NOT_STORE_MAN;
		}

		oldBottleCode = AirBottleCodeInitUtils.initCode(oldBottleCode);
		newBottleCode = AirBottleCodeInitUtils.initCode(newBottleCode);

		ClientAirBottleRecord clientAirBottleRecord = clientAirBottleRecordDaoImpl.getNoReturnClientAirBottleRecord(oldBottleCode);

		if (clientAirBottleRecord == null) {
			return CommonCode.USER_HAS_NOT_BOTTLE;
		}

		StoreInventoryInfo storeInventoryInfo = storeInventoryInfoDaoImpl.checkHeavyBottleFromStoreByBottleCode(newBottleCode, user);

		if (storeInventoryInfo == null) {
			return CommonCode.NOT_EXIST_AIR_BOTTLE;
		}

		if (clientAirBottleRecord.getAirBottleInfo().getAirBottleType().getId() != storeInventoryInfo.getAirBottleInfo().getAirBottleType().getId()) {
			return CommonCode.ORDER_BOTTLE_TYPE_NOT_MATCH;
		}

		AirBottleInfo oldAirBottleInfo = clientAirBottleRecord.getAirBottleInfo();
		AirBottleInfo newAirBottleInfo = storeInventoryInfo.getAirBottleInfo();

		OrderAirBottleInfo orderAirBottleInfo = orderAirBottleInfoDaoImpl.findByClinetIdAndAirBottleCode(clientAirBottleRecord.getClientInfo().getId(),
				oldBottleCode);

		// 更新订单气瓶记录
		if (orderAirBottleInfo == null) {
			return CommonCode.ORDER_NOT_EXIST;
		}

		orderAirBottleInfo.setAirBottleInfo(newAirBottleInfo);
		orderAirBottleInfoDaoImpl.update(orderAirBottleInfo);

		// 更新用户气瓶记录

		clientAirBottleRecord.setAirBottleInfo(newAirBottleInfo);
		clientAirBottleRecordDaoImpl.update(clientAirBottleRecord);

		// 更改门店库存
		storeInventoryInfo.setAirBottleInfo(oldAirBottleInfo);
		storeInventoryInfoDaoImpl.update(storeInventoryInfo);

		// 气瓶追踪
		AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, oldAirBottleInfo, user.getSecondCategory(), null, null,
				clientAirBottleRecord.getClientInfo(), AirBottleTrackingRecordCode.HEAVY_BOTTLE_CHANGE_FROM_CLIENT_BY_STORE_TO_STORE, user);

		AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, newAirBottleInfo, user.getSecondCategory(), null, null,
				clientAirBottleRecord.getClientInfo(), AirBottleTrackingRecordCode.HEAVY_BOTTLE_CHANGE_FROM_CLIENT_BY_STORE_TO_USER, user);

		return CommonCode.OK;
	}

	public CommonCode addNonQrcodeHeavyBottle(Integer orderId, String bottlecodes) {

		OrderInfo order = orderDAOImpl.findById(orderId);

		if (order == null) {
			return CommonCode.ORDER_NOT_EXIST;
		}

		order.setNon_qrcode_heavy_bottle(bottlecodes);

		if (order.getState().getId() < OrderInfoStateCode.ALREADY_DELIVERY_BUT_NO_BACK) {
			order.setState(new OrderInfoState(OrderInfoStateCode.ALREADY_DELIVERY_BUT_NO_BACK));

		}

		orderDAOImpl.update(order);

		return CommonCode.OK;
	}

	public CommonCode addNonQrcodeEmptyBottle(Integer orderId, String bottlecodes) {

		OrderInfo order = orderDAOImpl.findById(orderId);

		if (order == null) {
			return CommonCode.ORDER_NOT_EXIST;
		}

		order.setNon_qrcode_empty_bottle(bottlecodes);

		if (order.getState().getId() == OrderInfoStateCode.ALREADY_DELIVERY_BUT_NO_BACK) {
			order.setState(new OrderInfoState(OrderInfoStateCode.ALREADY_FINISH));
		}

		order.setPayState(new PayState(PayStateCode.HAS_PAY));

		orderDAOImpl.update(order);

		return CommonCode.OK;
	}

	public CommonCode speedUpOrder(Integer orderId) {

		OrderInfo order = orderDAOImpl.findById(orderId);

		if (order == null) {
			return CommonCode.ORDER_NOT_EXIST;
		}

		Date nowTime = new Date();

		order.setIs_speed_up(order.getIs_speed_up() + 1);

		order.setSpeed_up_time(nowTime);

		order.setSpeed_up_wait_for_minutes((int) TimeUtils.getDistanceMinutes(nowTime.getTime(), order.getOrder_time().getTime()));

		orderDAOImpl.update(order);

		return CommonCode.OK;
	}

	public CommonCode cancelSpeedUpOrder(Integer orderId) {

		OrderInfo order = orderDAOImpl.findById(orderId);

		if (order == null) {
			return CommonCode.ORDER_NOT_EXIST;
		}

		order.setIs_speed_up(0);

		order.setSpeed_up_time(null);

		order.setSpeed_up_wait_for_minutes(null);

		orderDAOImpl.update(order);

		return CommonCode.OK;
	}

	public List<SimpleOrderInfo> getSimpleOrderInfoPageList(List<String> l, Field filed, int page, int rows, int export, String sort, String order) {

		List<SimpleOrderInfo> simpleOrderInfos = orderDAOImpl.getSimpleOrderInfoPageList(l, filed, page, rows, sort, order);

		DeliveryType deliveryType = null;
		PayType payType = null;
		PayState payState = null;
		User deliveryMan = null;
		User operator = null;
		User invoiceOperator = null;
		User cancelInvoiceOperator = null;
		SecondCategory secondCategory = null;

		// Map<Integer, BusinessType> businessTypeMap = new HashMap<Integer, BusinessType>();
		// Map<Integer, DeliveryType> deliveryTypeMap = new HashMap<Integer, DeliveryType>();
		// Map<Integer, PayType> payTypeMap = new HashMap<Integer, PayType>();
		// Map<Integer, PayState> payStateMap = new HashMap<Integer, PayState>();
		// Map<Integer, User> userMap = new HashMap<Integer, User>();
		// Map<Integer, SecondCategory> secondCategoryMap = new HashMap<Integer, SecondCategory>();

		Integer deliveryTypeId = null;
		Integer payTypeId = null;
		Integer payStateId = null;
		Integer deliveryManId = null;
		Integer operatorId = null;
		Integer secondCategoryId = null;
		Integer invoiceOperatorId = null;
		Integer cancelInvoiceOperatorId = null;

		List<OrderAirBottleInfo> orderAirBottleInfos = null;
		List<OrderEmptyBottleInfo> orderEmptyBottleInfos = null;

		for (SimpleOrderInfo simpleOrderInfo : simpleOrderInfos) {

			deliveryType = null;
			payType = null;
			payState = null;
			deliveryMan = null;
			operator = null;
			secondCategory = null;
			invoiceOperator = null;
			cancelInvoiceOperator = null;

			deliveryTypeId = simpleOrderInfo.getDelivery_type_id();
			if (deliveryTypeId != null) {
				//
				// deliveryType = deliveryTypeMap.get(deliveryTypeId);
				//
				// if (deliveryType == null) {

				deliveryType = deliveryTypeDaoImpl.findById(deliveryTypeId);
				// deliveryTypeMap.put(deliveryTypeId, deliveryType);
				// }
			}

			payTypeId = simpleOrderInfo.getPay_type_id();
			if (payTypeId != null) {
				//
				// payType = payTypeMap.get(payTypeId);
				//
				// if (payType == null) {

				payType = payTypeDaoImpl.findById(payTypeId);
				// payTypeMap.put(payTypeId, payType);
				// }
				//
			}

			payStateId = simpleOrderInfo.getPay_state_id();
			if (payStateId != null) {
				//
				// payState = payStateMap.get(payStateId);
				//
				// if (payState == null) {
				payState = payStateDaoImpl.findById(payStateId);
				// payStateMap.put(payStateId, payState);
				// }
			}

			deliveryManId = simpleOrderInfo.getDelivery_man_id();
			if (deliveryManId != null) {
				//
				// deliveryMan = userMap.get(deliveryManId);
				//
				// if (deliveryMan == null) {
				deliveryMan = userDaoImpl.findById(deliveryManId);
				// userMap.put(deliveryManId, deliveryMan);
				// }
				//
			}

			operatorId = simpleOrderInfo.getOperator_id();
			if (operatorId != null) {
				//
				// operator = userMap.get(operatorId);
				//
				// if (operator == null) {
				operator = userDaoImpl.findById(operatorId);
				// userMap.put(operatorId, operator);
				// }
			}

			invoiceOperatorId = simpleOrderInfo.getInvoice_operator_id();
			if (invoiceOperatorId != null) {
				invoiceOperator = userDaoImpl.findById(invoiceOperatorId);
			}

			cancelInvoiceOperatorId = simpleOrderInfo.getCancel_invoice_operator_id();
			if (cancelInvoiceOperatorId != null) {
				cancelInvoiceOperator = userDaoImpl.findById(cancelInvoiceOperatorId);
			}

			secondCategoryId = simpleOrderInfo.getSecondCategory_id();
			if (secondCategoryId != null) {
				//
				// secondCategory = secondCategoryMap.get(secondCategoryId);
				//
				// if (secondCategory == null) {
				secondCategory = secondCategoryDaoImpl.findById(secondCategoryId);
				// secondCategoryMap.put(secondCategoryId, secondCategory);
				// }
				//
			}

			if (export == 0) {
				orderAirBottleInfos = orderAirBottleInfoDaoImpl.findByOrderId(simpleOrderInfo.getId());
				orderEmptyBottleInfos = orderEmptyBottleInfoDaoImpl.findByOrderId(simpleOrderInfo.getId());
			}

			simpleOrderInfo.init(deliveryType, payType, payState, deliveryMan, operator, invoiceOperator, cancelInvoiceOperator, secondCategory,
					orderAirBottleInfos, orderEmptyBottleInfos);

		}

		return simpleOrderInfos;
	}

	public CommonCode rongXinTongPay() {

		RongXinTongVo rongXinTongVo = new RongXinTongVo();

		List<RongXinTongDetailVo> rongXinTongDetailVos = new ArrayList<RongXinTongDetailVo>();

		RongXinTongDetailVo rongXinTongDetailVo = new RongXinTongDetailVo();
		rongXinTongDetailVo.setOrderId("1");
		rongXinTongDetailVo.setProductName("液化气");
		rongXinTongDetailVo.setProductBrand("富民燃气");
		rongXinTongDetailVo.setProductSerial("15KG");
		rongXinTongDetailVo.setProductUnit("瓶");
		rongXinTongDetailVo.setProductNum("10");
		rongXinTongDetailVo.setProductPrice(String.valueOf(88 * 100));
		rongXinTongDetailVo.setProductPlace("暂无信息");
		rongXinTongDetailVos.add(rongXinTongDetailVo);

		rongXinTongVo.setOrderId("1");
		rongXinTongVo.setOrderDate(TimeUtils.getRongXinTongStr(new Date()));
		rongXinTongVo.setOrderFee(String.valueOf(88 * 100 * 10));
		rongXinTongVo.setFee(String.valueOf(88 * 100 * 10));
		rongXinTongVo.setDetailCount("1");
		rongXinTongVo.setAgentId("hmx");
		rongXinTongVo.setTradeId("hmny00001");

		rongXinTongVo.setDetail(rongXinTongDetailVos);

		try {
			String json = JSONUtils.fromObject(rongXinTongVo);
			String sign = PKIUtil.sign(json);
			String tradeId = "hmny00001";
			WebServiceUtils.invokeRemoteFuc(json, sign, tradeId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return CommonCode.OK;
	}

	@SuppressWarnings("unchecked")
	public BaseDto<Object> forceCacnel(Integer orderId, User user) {

		OrderInfo orderInfo = orderDAOImpl.findById(orderId);

		int airBottleNum = orderAirBottleInfoDaoImpl.findOrderNumByOrder(orderId).intValue();

		int emptyBottleNum = orderEmptyBottleInfoDaoImpl.findOrderNumByOrder(orderId).intValue();

		if (airBottleNum > 0 || emptyBottleNum > 0) {
			return BaseDto.getFailResult(CommonCode.ORDER_CAN_NOT_CANCEL);
		}

		orderInfo.setState(new OrderInfoState(OrderInfoStateCode.ALREADY_CANCEL));

		orderDAOImpl.delete(orderInfo);

		returnMoney(orderInfo, user);

		return BaseDto.getSuccessResult(null);

	}

	public int getPirntTimes(Integer orderId) {

		OrderInfo orderInfo = orderDAOImpl.findById(orderId);

		if (orderInfo == null) {
			return 0;
		}

		return orderInfo.getPrint_times();
	}

	public CommonCode printCancel(Integer orderId, String printNo, User user) {

		OrderInfo orderInfo = orderDAOImpl.findById(orderId);

		if (orderInfo == null) {
			return CommonCode.ORDER_NOT_EXIST;
		}

		PrintCancelInfo printCancelInfo = new PrintCancelInfo();
		printCancelInfo.setCreate_time(new Date());
		printCancelInfo.setOperator(user);
		printCancelInfo.setOrder_id(orderId);
		printCancelInfo.setPrintNo(printNo);

		printCancelInfoDaoImpl.insert(printCancelInfo);

		orderInfo.setPrint_times(orderInfo.getPrint_times() - 1);
		orderDAOImpl.update(orderInfo);

		return CommonCode.OK;
	}

	public List<OrderCycleInfoVo> getOrderCycleInfo(List<String> l, Field filed) {

		List<OrderCycleInfoVo> list = orderDAOImpl.getOrderCycleInfo(l, filed);

		for (OrderCycleInfoVo orderCycleInfoVo : list) {

			int num = orderDAOImpl.getOrderCycleLastOrderNum(orderCycleInfoVo.getClientId(), orderCycleInfoVo.getEndTime());

			orderCycleInfoVo.setOrderBottleNum(orderCycleInfoVo.getOrderBottleNum() - num);
			orderCycleInfoVo.setOrderNum(orderCycleInfoVo.getOrderNum() - 1);
		}

		return list;

	}

	public Integer getPickUpInSotreNum(List<String> l, Field filed) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(l);
		tmpField.setFields(filed.getFields());

		tmpList.add("deliveryType.id = ?");
		tmpField.addInt(DeliveryTypeCode.PICKUP_IN_STORES);

		return orderDAOImpl.getPickUpInSotreNum(tmpList, tmpField);
	}

	public Integer getTelephoneOrderNum(List<String> l, Field filed) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(l);
		tmpField.setFields(filed.getFields());

		tmpList.add("deliveryType.id = ?");
		tmpField.addInt(DeliveryTypeCode.ORDER_DELIVERY);

		return orderDAOImpl.getTelephoneOrderNum(tmpList, tmpField);
	}

	public Integer getWeiXinOrderNum(List<String> l, Field filed) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(l);
		tmpField.setFields(filed.getFields());

		tmpList.add("operator.id = ?");
		tmpField.addInt(OperatorCode.NEW_WEIXIN);

		return orderDAOImpl.getWeiXinOrderNum(tmpList, tmpField);
	}

	public int getTodayOrderNumByClinetId(int clientId) {

		Date nowTime = new Date();

		String today = TimeUtils.getyyyyMMddStringByDate(nowTime);

		return orderDAOImpl.getTodayOrderNumByClinetId(clientId, today);
	}

	public CommonCode finishPartsOrder(Integer orderId) {

		OrderInfo orderInfo = orderDAOImpl.findById(orderId);

		if (orderInfo.getOrder_number() != 0) {

			return CommonCode.ORDER_IS_NOT_PARTS_ORDER;
		}

		if (orderInfo.getState().getId() == OrderInfoStateCode.ALREADY_FINISH) {
			return CommonCode.ORDER_HAS_FINISH;
		}

		orderInfo.setState(new OrderInfoState(OrderInfoStateCode.ALREADY_FINISH));
		orderDAOImpl.update(orderInfo);

		return CommonCode.OK;
	}

	public CommonCode editOrderStore(Integer orderId, Integer sotreId) {

		OrderInfo orderInfo = orderDAOImpl.findById(orderId);

		if (orderInfo == null) {
			return CommonCode.ORDER_NOT_EXIST;
		}

		SecondCategory secondCategory = secondCategoryDaoImpl.findById(sotreId);

		if (secondCategory == null) {
			return CommonCode.SYSTEM_EXCEPTION;
		}

		orderInfo.setSecondCategory(secondCategory);
		orderDAOImpl.update(orderInfo);

		return CommonCode.OK;
	}

	public CommonCode returnQRcode(Integer orderId, User user) {

		OrderInfo orderInfo = orderDAOImpl.findById(orderId);

		if (orderInfo == null) {
			return CommonCode.ORDER_NOT_EXIST;
		}

		Date nowTime = new Date();

		int clientId = orderInfo.getClientInfo().getId();

		// 重瓶处理
		List<OrderAirBottleInfo> orderAirBottleInfos = orderAirBottleInfoDaoImpl.findByOrderId(orderId);

		for (OrderAirBottleInfo orderAirBottleInfo : orderAirBottleInfos) {

			ClientAirBottleRecord clientAirBottleRecord = clientAirBottleRecordDaoImpl.getRecordByClientIdAndBottleId(clientId, orderAirBottleInfo
					.getAirBottleInfo().getId());
			clientAirBottleRecordDaoImpl.delete(clientAirBottleRecord);

			StoreInventoryInfo storeInventoryInfo = new StoreInventoryInfo(orderAirBottleInfo.getAirBottleInfo(), orderInfo.getSecondCategory(),
					new AirBottleState(AirBottleStateCode.HEAVY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), nowTime,
					user);
			storeInventoryInfoDaoImpl.insert(storeInventoryInfo);

			orderAirBottleInfoDaoImpl.delete(orderAirBottleInfo);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, orderAirBottleInfo.getAirBottleInfo(),
					orderInfo.getSecondCategory(), null, null, null, AirBottleTrackingRecordCode.SYSTEM_RETURN_TO_STORE, user);

		}

		// 空瓶处理
		List<OrderEmptyBottleInfo> orderEmptyBottleInfos = orderEmptyBottleInfoDaoImpl.findByOrderId(orderId);

		for (OrderEmptyBottleInfo orderEmptyBottleInfo : orderEmptyBottleInfos) {

			boolean checkInventory = false;

			// 判断是否在门店
			StoreInventoryInfo storeInventoryInfo = storeInventoryInfoDaoImpl.findByAirBottleInfo(orderEmptyBottleInfo.getAirBottleInfo().getAir_bottle_code());

			if (storeInventoryInfo != null) {
				checkInventory = true;
				storeInventoryInfoDaoImpl.delete(storeInventoryInfo);
			}

			if (!checkInventory) {
				return CommonCode.SYSTEM_EXCEPTION;
			}

			ClientAirBottleRecord clientAirBottleRecord = clientAirBottleRecordDaoImpl.getRecordByClientIdAndBottleId(clientId, orderEmptyBottleInfo
					.getAirBottleInfo().getId());

			clientAirBottleRecord.setEnd_time(null);
			clientAirBottleRecord.setState(new ClientAirBottleRecordState(ClientAirBottleReocrdStateCode.NO_RETURN));

			clientAirBottleRecordDaoImpl.update(clientAirBottleRecord);

			orderEmptyBottleInfoDaoImpl.delete(orderEmptyBottleInfo);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, orderEmptyBottleInfo.getAirBottleInfo(), null, null,
					null, orderInfo.getClientInfo(), AirBottleTrackingRecordCode.SYSTEM_RETURN_TO_CLIENT, user);
		}

		orderInfo.setDelivery_man(null);
		orderInfo.setState(new OrderInfoState(OrderInfoStateCode.ALREADY_ORDER_BUT_NO_DISPATCHING));
		orderDAOImpl.update(orderInfo);

		return CommonCode.OK;
	}

	public CommonCode invoice(Integer orderId, User user) {

		OrderInfo orderInfo = orderDAOImpl.findById(orderId);

		if (orderInfo.getInvoice_num() > 0) {
			return CommonCode.ORDER_HAS_INVOICE;
		}

		Date nowTime = new Date();

		orderInfo.addInvoice(user, nowTime);

		orderDAOImpl.update(orderInfo);

		InvoiceRecord invoiceRecord = new InvoiceRecord(user, nowTime, null, new InvoiceRecordState(InvoiceRecordStateCode.INVOICE), orderInfo.getId(),
				orderInfo.getOrder_code());
		invoiceRecordDaoImpl.insert(invoiceRecord);

		return CommonCode.OK;
	}

	public CommonCode cancelInvoice(Integer orderId, String remark, User user) {

		OrderInfo orderInfo = orderDAOImpl.findById(orderId);

		Date nowTime = new Date();

		orderInfo.cancelInvoice(remark, user, nowTime);

		orderDAOImpl.update(orderInfo);

		InvoiceRecord invoiceRecord = new InvoiceRecord(user, nowTime, remark, new InvoiceRecordState(InvoiceRecordStateCode.CANCEL_INVOICE),
				orderInfo.getId(), orderInfo.getOrder_code());
		invoiceRecordDaoImpl.insert(invoiceRecord);

		return CommonCode.OK;
	}

	@Override
	public boolean fillHeavyBottleInOrder(String bottleCodes, int orderId, User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fillEmptyBottleInOrder(int orderId, String bottleCodes, User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fillHeavyBottleByPickUpInSotres(String bottleCodes, int orderId, User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fillEmptyBottleByPickUpInSotres(String bottleCodes, int orderId, User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BaseDto<Object> fillHeavyBottleByPickUpInWarehouse(String bottleCodes, int orderId, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseDto<Object> fillEmptyBottleByPickUpInWarehouse(String bottleCodes, int orderId, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseDto<Object> cancelPickupInWH(Integer orderId, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonCode checkDispatch(Integer orderId) {

		OrderInfo orderInfo = orderDAOImpl.findById(orderId);

		if (orderInfo.getDeliveryType().getId() != DeliveryTypeCode.ORDER_DELIVERY) {
			return CommonCode.WAREHOUSE_BUSINESS_ORDER_NOT_DISPATCH;
		}

		if (orderInfo.getState().getId() != OrderInfoStateCode.ALREADY_ORDER_BUT_NO_DISPATCHING) {
			return CommonCode.WAREHOUSE_BUSINESS_ORDER_NOT_DISPATCH;
		}

		return CommonCode.OK;
	}

	@Override
	public OrderInfo findOrderInfoByOrderCode(String orderCode) {

		return orderDAOImpl.findByOrderCode(orderCode);
	}

	@Override
	public List<SaleReportInfoSummaryByDifferentPrice> getSubtotalList(List<String> l, Field filed) {
		return orderDAOImpl.getSubtotalList(l, filed);
	}

	@Override
	public OrderInfo addMobileOrderInfo(AppAddOrder appAddOrder) {

		Date nowTime = new Date();

		OrderInfo order = new OrderInfo();

		ClientInfo clientInfo = clientInfoDaoImpl.findById(appAddOrder.getClientId());
		order.setClientInfo(clientInfo);

		AirBottleType airBottleType = airBottleTypeDaoImpl.findById(appAddOrder.getAirBottleTypeId());
		order.setAirBottleType(airBottleType);

		order.setOrder_number(appAddOrder.getOrderNumber());

		order.setOrder_address(appAddOrder.getOrderAddress());

		order.setOrder_tel_number(appAddOrder.getOrderTelNumber());

		order.setRemark(appAddOrder.getRemark());

		// FloorSubsidies floorSubsidies = floorSubsidiesDaoImpl.findById(appAddOrder.getFloorSubsidiesId());

		order.setFloor_subsidies_money(0);

		order.setDelivery_fee(appAddOrder.getDeliveryFee());

		// Date appointmentTime = TimeUtils.formatAppointmentTime(appAddOrder.getOrderAppointmentTime());
		Date appointmentTime = nowTime;
		order.setOrder_appointment_time1(appointmentTime);

		PayType payType = new PayType(PayTypeCode.CASH);
		// payType.setId(appAddOrder.getPayTypeId());
		order.setPayType(payType);

		order.setCheck_fee(0);
		order.setDiscount_amount(0);
		order.setFee_total_amount(0);

		order.setPrice(airBottleType.getPrice());

		Double totalAmount = (order.getPrice() + order.getDelivery_fee() + order.getFloor_subsidies_money()) * order.getOrder_number();
		order.setTotal_amount(totalAmount);

		DeliveryType deliveryType = new DeliveryType();
		deliveryType.setId(DeliveryTypeCode.PICKUP_IN_STORES);
		order.setDeliveryType(deliveryType);

		OrderInfoState os = new OrderInfoState();
		os.setId(OrderInfoStateCode.ALREADY_ORDER_BUT_NO_DISPATCHING);
		order.setState(os);

		order.setOrder_time(nowTime);
		order.setReport_time(appointmentTime);

		User operator = userDaoImpl.findById(appAddOrder.getUserId());
		order.setOperator(operator);

		order.setPayState(new PayState(PayStateCode.NO_PAY));

		// 修改受理门店
		order.setSecondCategory(clientInfo.getSecondCategory());

		orderDAOImpl.insert(order);

		order.setOrder_code(CodeUtils.setOrderCode(order));

		orderDAOImpl.update(order);

		return order;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> addMobileDispatch(int orderId, int deliveryManId) {
		
		OrderInfo orderInfo = orderDAOImpl.findById(orderId);

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

		orderDAOImpl.update(orderInfo);
		
		return BaseDto.getSuccessResult(null);
	}


}
