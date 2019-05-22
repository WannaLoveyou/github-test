package com.qian.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.code.AirBottleInventoryStateCode;
import com.qian.code.AirBottleStateCode;
import com.qian.code.AirBottleTrackingRecordCode;
import com.qian.code.ClientAirBottleReocrdStateCode;
import com.qian.code.DeliveryTypeCode;
import com.qian.code.OrderInfoStateCode;
import com.qian.code.PayStateCode;
import com.qian.code.PayTypeCode;
import com.qian.dao.impl.AirBottleInfoDaoImpl;
import com.qian.dao.impl.AirBottleTrackingRecordDaoImpl;
import com.qian.dao.impl.AirBottleTypeDaoImpl;
import com.qian.dao.impl.ClientAirBottleRecordDaoImpl;
import com.qian.dao.impl.ClientInfoDaoImpl;
import com.qian.dao.impl.ClientInventoryInfoDaoImpl;
import com.qian.dao.impl.DeliveryManInventoryInfoDaoImpl;
import com.qian.dao.impl.OrderAirBottleInfoDaoImpl;
import com.qian.dao.impl.OrderDaoImpl;
import com.qian.dao.impl.OrderEmptyBottleInfoDaoImpl;
import com.qian.dao.impl.StoreCustomAirBottlePriceInfoDaoImpl;
import com.qian.dao.impl.StoreInventoryInfoDaoImpl;
import com.qian.dao.impl.UserDaoImpl;
import com.qian.dao.impl.WarehouseInventoryInfoDaoImpl;
import com.qian.entity.AirBottleInventoryState;
import com.qian.entity.AirBottleState;
import com.qian.entity.AirBottleType;
import com.qian.entity.ClientInfo;
import com.qian.entity.ClientInventoryInfo;
import com.qian.entity.DeliveryManInventoryInfo;
import com.qian.entity.DeliveryType;
import com.qian.entity.OrderAirBottleInfo;
import com.qian.entity.OrderEmptyBottleInfo;
import com.qian.entity.OrderInfo;
import com.qian.entity.OrderInfoState;
import com.qian.entity.PayState;
import com.qian.entity.PayType;
import com.qian.entity.SecondCategory;
import com.qian.entity.StoreCustomAirBottlePriceInfo;
import com.qian.entity.StoreInventoryInfo;
import com.qian.entity.User;
import com.qian.entity.WarehouseInfo;
import com.qian.entity.WarehouseInventoryInfo;
import com.qian.mobile.entity.MobileAirBottleCheckEntity;
import com.qian.mobile.entity.MobileAirBottleSumbitEntity;
import com.qian.mobile.entity.MobileOrderInfo;
import com.qian.service.StoreInventoryInfoService;
import com.qian.util.AirBottleCodeInitUtils;
import com.qian.util.AirBottleTrackingRecordUtils;
import com.qian.util.CodeUtils;
import com.qian.util.InitDataUtils;
import com.qian.vo.AirBottleInfoReportDetailVo;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;
import com.qian.vo.StoreInventoryInfoVo;

@Service
@Transactional
public class StoreInventoryInfoServiceImpl implements StoreInventoryInfoService {

	@Autowired
	private StoreInventoryInfoDaoImpl storeInventoryInfoDaoImpl;

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private DeliveryManInventoryInfoDaoImpl deliveryManInventoryInfoDaoImpl;

	@Autowired
	private AirBottleTrackingRecordDaoImpl airBottleTrackingRecordDaoImpl;

	@Autowired
	private AirBottleInfoDaoImpl airBottleInfoDaoImpl;

	@Autowired
	private ClientInfoDaoImpl clientInfoDaoImpl;

	@Autowired
	private ClientInventoryInfoDaoImpl clientInventoryInfoDaoImpl;


	@Autowired
	private AirBottleTypeDaoImpl airBottleTypeDaoImpl;

	@Autowired
	private StoreCustomAirBottlePriceInfoDaoImpl storeCustomAirBottlePriceInfoDaoImpl;

	@Autowired
	private OrderDaoImpl orderDaoImpl;

	@Autowired
	private OrderAirBottleInfoDaoImpl orderAirBottleInfoDaoImpl;

	@Autowired
	private ClientAirBottleRecordDaoImpl clientAirBottleRecordDaoImpl;

	@Autowired
	private OrderEmptyBottleInfoDaoImpl orderEmptyBottleInfoDaoImpl;

	@Autowired
	private WarehouseInventoryInfoDaoImpl warehouseInventoryInfoDaoImpl;
	
	@Override
	public StoreInventoryInfo findByStoreIdAndBottleCode(MobileAirBottleCheckEntity entity, int airBottleState, int airBottleInventoryState) {

		User user = userDaoImpl.findById(entity.getUserId());

		if (user.getSecondCategory() == null) {
			return null;
		}

		return storeInventoryInfoDaoImpl.findByStoreIdAndBottleCode(user.getSecondCategory().getId(), AirBottleCodeInitUtils.initCode(entity.getBottleCode()),
				airBottleState, airBottleInventoryState);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> heavyBottleStorageFromDeliveryMan(MobileAirBottleSumbitEntity entity, User user) {

		Integer deliveryManId = entity.getDeliveryManId();

		User deliveryMan = userDaoImpl.findById(deliveryManId);

		SecondCategory secondCategory = user.getSecondCategory();

		if (secondCategory == null) {
			return BaseDto.getFailResult(CommonCode.NOT_STORE_MAN);
		}

		Date nowTime = new Date();

		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds())) {

			DeliveryManInventoryInfo dii = deliveryManInventoryInfoDaoImpl.findByDeliveryManIdAndBottleId(deliveryManId, bottleId,
					AirBottleStateCode.HEAVY_BOTTLE, AirBottleInventoryStateCode.ALREADY_RECEIVE);
			deliveryManInventoryInfoDaoImpl.delete(dii);

			StoreInventoryInfo storeInventoryInfo = new StoreInventoryInfo(dii.getAirBottleInfo(), secondCategory, new AirBottleState(
					AirBottleStateCode.HEAVY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), nowTime, user);
			storeInventoryInfoDaoImpl.insert(storeInventoryInfo);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, dii.getAirBottleInfo(), secondCategory, deliveryMan,
					null, null, AirBottleTrackingRecordCode.HEAVY_BOTTLE_STORAGE_IN_SOTRE, user);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> heavyBottleOutToDeliveryMan(MobileAirBottleSumbitEntity entity, User user) {
		
		Integer deliveryManId = entity.getDeliveryManId();// 配送工ID

		SecondCategory secondCategory = user.getSecondCategory();

		User deliveryMan = userDaoImpl.findById(deliveryManId);// 查询出配送工信息

		if (secondCategory == null) {
			return BaseDto.getFailResult(CommonCode.NOT_STORE_MAN);
		}

		Date nowTime = new Date();

		// 遍历气瓶编码
		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds())) {

			StoreInventoryInfo storeInventoryInfo = storeInventoryInfoDaoImpl.findByStoreIdAndBottleId(secondCategory.getId(), bottleId,
					AirBottleStateCode.HEAVY_BOTTLE, AirBottleInventoryStateCode.ALREADY_RECEIVE);

			if (storeInventoryInfo == null) {
				return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
			}
			storeInventoryInfoDaoImpl.delete(storeInventoryInfo);

			DeliveryManInventoryInfo deliveryManii = new DeliveryManInventoryInfo(deliveryMan, storeInventoryInfo.getAirBottleInfo(), new AirBottleState(
					AirBottleStateCode.HEAVY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), deliveryMan, nowTime);
			deliveryManInventoryInfoDaoImpl.insert(deliveryManii);

			// 气瓶轨迹记录
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, storeInventoryInfo.getAirBottleInfo(), secondCategory,
					deliveryMan, null, null, AirBottleTrackingRecordCode.HEAVY_BOTTLE_OUT_SOTRE_TO_DELIVERY_MAN, user);

		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> emptyBottleOutToDeliveryMan(MobileAirBottleSumbitEntity entity, User user) {

		Integer deliveryManId = entity.getDeliveryManId();// 配送工ID

		SecondCategory secondCategory = user.getSecondCategory();

		User deliveryMan = userDaoImpl.findById(deliveryManId);// 查询出配送工信息

		if (secondCategory == null) {
			return BaseDto.getFailResult(CommonCode.NOT_STORE_MAN);
		}

		Date nowTime = new Date();

		// 遍历气瓶编码
		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds())) {

			StoreInventoryInfo storeInventoryInfo = storeInventoryInfoDaoImpl.findByStoreIdAndBottleId(secondCategory.getId(), bottleId,
					AirBottleStateCode.EMPTY_BOTTLE, AirBottleInventoryStateCode.ALREADY_RECEIVE);

			if (storeInventoryInfo == null) {
				return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
			}
			storeInventoryInfoDaoImpl.delete(storeInventoryInfo);

			DeliveryManInventoryInfo deliveryManii = new DeliveryManInventoryInfo(deliveryMan, storeInventoryInfo.getAirBottleInfo(), new AirBottleState(
					AirBottleStateCode.EMPTY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), deliveryMan, nowTime);
			deliveryManInventoryInfoDaoImpl.insert(deliveryManii);

			// 气瓶轨迹记录
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, storeInventoryInfo.getAirBottleInfo(), secondCategory,
					deliveryMan, null, null, AirBottleTrackingRecordCode.EMPTY_BOTTLE_OUT_IN_STORE, user);

		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> emptyBottleStorageFromDeliveryMan(MobileAirBottleSumbitEntity entity, User user) {
		
		Integer deliveryManId = entity.getDeliveryManId();

		User deliveryMan = userDaoImpl.findById(deliveryManId);

		SecondCategory secondCategory = user.getSecondCategory();

		if (secondCategory == null) {
			return BaseDto.getFailResult(CommonCode.NOT_STORE_MAN);
		}

		Date nowTime = new Date();

		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds())) {

			DeliveryManInventoryInfo dii = deliveryManInventoryInfoDaoImpl.findByDeliveryManIdAndBottleId(deliveryManId, bottleId,
					AirBottleStateCode.EMPTY_BOTTLE, AirBottleInventoryStateCode.ALREADY_RECEIVE);
			deliveryManInventoryInfoDaoImpl.delete(dii);

			StoreInventoryInfo storeInventoryInfo = new StoreInventoryInfo(dii.getAirBottleInfo(), secondCategory, new AirBottleState(
					AirBottleStateCode.EMPTY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), nowTime, user);
			storeInventoryInfoDaoImpl.insert(storeInventoryInfo);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, dii.getAirBottleInfo(), secondCategory, deliveryMan,
					null, null, AirBottleTrackingRecordCode.EMPTY_BOTTLE_STORAGE_IN_STORE, user);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<MobileOrderInfo> addMobileStoreOrder(MobileAirBottleSumbitEntity entity, User user) {

		Date nowTime = new Date();

		int OrderNum = entity.getBottleIds().split(",").length;

		OrderInfo orderInfo = new OrderInfo();

		int bottleTypeId = entity.getAirBottleTypeId();

		int clientId = entity.getClientId();

		ClientInfo clientInfo = clientInfoDaoImpl.findById(clientId);
		clientInfo.setLast_order_time(nowTime);
		clientInfoDaoImpl.update(clientInfo);

		SecondCategory secondCategory = user.getSecondCategory();

		if (secondCategory == null) {
			return BaseDto.getFailResult(CommonCode.NOT_STORE_MAN);
		}

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
		
		orderInfo.setPayState(new PayState(PayStateCode.NO_PAY));

		orderInfo.setOrder_time(nowTime);
		orderInfo.setReport_time(nowTime);

		orderInfo.setOperator(user);

		orderInfo.setRemark("");

		// 修改受理门店
		orderInfo.setSecondCategory(secondCategory);

		orderDaoImpl.insert(orderInfo);

		orderInfo.setOrder_code(CodeUtils.setOrderCode(orderInfo));

		orderDaoImpl.update(orderInfo);

		entity.setOrderId(orderInfo.getId());
		BaseDto<Object> b = heavyBottleOutToClient(entity, user);

		if (b.getCode() != CommonCode.OK.getCode()) {
			orderDaoImpl.delete(orderInfo); // 网络延时导致重复单
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult(new MobileOrderInfo(orderInfo));
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> heavyBottleOutToClient(MobileAirBottleSumbitEntity entity, User user) {

		Integer clientId = entity.getClientId();

		Integer orderId = entity.getOrderId();

		OrderInfo orderInfo = orderDaoImpl.findById(orderId);

		ClientInfo clientInfo = clientInfoDaoImpl.findById(clientId);

		SecondCategory secondCategory = user.getSecondCategory();

		if (secondCategory == null) {
			return BaseDto.getFailResult(CommonCode.NOT_STORE_MAN);
		}

		int orderCheckNum = orderAirBottleInfoDaoImpl.findOrderNumByOrder(orderId).intValue();

		int sumbitNum = AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds()).size();

		if (orderCheckNum + sumbitNum > orderInfo.getOrder_number()) {
			return BaseDto.getFailResult(CommonCode.ORDER_OUT_OF_BOTTLE_NUM);
		}

		Date nowTime = new Date();

		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds())) {

			StoreInventoryInfo storeInventoryInfo = storeInventoryInfoDaoImpl.findByStoreIdAndBottleId(secondCategory.getId(), bottleId,
					AirBottleStateCode.HEAVY_BOTTLE, AirBottleInventoryStateCode.ALREADY_RECEIVE);

			if (storeInventoryInfo == null) {
				return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
			}
			storeInventoryInfoDaoImpl.delete(storeInventoryInfo);

			ClientInventoryInfo clientInventoryInfo = new ClientInventoryInfo(clientInfo, storeInventoryInfo.getAirBottleInfo(), new AirBottleState(
					AirBottleStateCode.HEAVY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), user, nowTime);
			clientInventoryInfoDaoImpl.insert(clientInventoryInfo);

			// 订单重瓶记录
			OrderAirBottleInfo orderAirBottleInfo = new OrderAirBottleInfo(orderInfo, storeInventoryInfo.getAirBottleInfo(), nowTime, user);
			orderAirBottleInfoDaoImpl.insert(orderAirBottleInfo);

			// 用户气瓶记录
			ClientAirBottleRecord clientAirBottleRecord = new ClientAirBottleRecord(clientInfo, storeInventoryInfo.getAirBottleInfo(), nowTime);
			clientAirBottleRecordDaoImpl.insert(clientAirBottleRecord);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, storeInventoryInfo.getAirBottleInfo(), null, user, null,
					clientInfo, AirBottleTrackingRecordCode.HEAVY_BOTTLE_OUT_SOTRE_TO_CLIENT, user);

		}

		int orderNum = orderAirBottleInfoDaoImpl.findOrderNumByOrder(orderId).intValue();

		if (orderNum >= orderInfo.getOrder_number()) {
			orderInfo.setState(new OrderInfoState(OrderInfoStateCode.ALREADY_DELIVERY_BUT_NO_BACK)); // 已派送未回单
			orderDaoImpl.update(orderInfo);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> emptyBottleStorageFromClient(MobileAirBottleSumbitEntity entity, User user) {

		Integer clientId = entity.getClientId();

		Integer orderId = entity.getOrderId();

		OrderInfo orderInfo = orderDaoImpl.findById(orderId);

		ClientInfo clientInfo = clientInfoDaoImpl.findById(clientId);

		SecondCategory secondCategory = user.getSecondCategory();

		if (secondCategory == null) {
			return BaseDto.getFailResult(CommonCode.NOT_STORE_MAN);
		}

		Date nowTime = new Date();

		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds())) {

			ClientInventoryInfo clientInventoryInfo = clientInventoryInfoDaoImpl.findByClientIdAndBottleId(clientId, bottleId);

			if (clientInventoryInfo == null) {
				return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
			}
			clientInventoryInfoDaoImpl.delete(clientInventoryInfo);

			// 重瓶空瓶在同一张订单中不允许提交
			OrderAirBottleInfo orderAirBottleInfo = orderAirBottleInfoDaoImpl.findByOrderIdAndBottleId(orderId, bottleId);
			if (orderAirBottleInfo != null) {
				return BaseDto.getFailResult(CommonCode.ORDER_CAN_HAS_SAME_HEAVY_EMPTY_BOTTLE);
			}

			// 订单空瓶信息
			OrderEmptyBottleInfo orderEmptyBottleInfo = new OrderEmptyBottleInfo(orderInfo, clientInventoryInfo.getAirBottleInfo(), nowTime, user);
			orderEmptyBottleInfoDaoImpl.insert(orderEmptyBottleInfo);

			// 新增库存信息
			StoreInventoryInfo storeInventoryInfo = new StoreInventoryInfo(clientInventoryInfo.getAirBottleInfo(), secondCategory, new AirBottleState(
					AirBottleStateCode.EMPTY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), nowTime, user);
			storeInventoryInfoDaoImpl.insert(storeInventoryInfo);

			// 更新用户气瓶记录
			ClientAirBottleRecord clientAirBottleRecord = clientAirBottleRecordDaoImpl.getRecordByClientIdAndAirBottleId(clientId, bottleId);
			if (clientAirBottleRecord != null) {
				clientAirBottleRecord.setEnd_time(nowTime);
				clientAirBottleRecord.setState(new ClientAirBottleRecordState(ClientAirBottleReocrdStateCode.ALREAY_RETURN));
				clientAirBottleRecordDaoImpl.update(clientAirBottleRecord);
			}

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, clientInventoryInfo.getAirBottleInfo(), null, user, null,
					clientInfo, AirBottleTrackingRecordCode.EMPTY_BOTTLE_STORAGE_IN_STORE, user);
		}

		orderInfo.setState(new OrderInfoState(OrderInfoStateCode.ALREADY_FINISH));// 历史订单

		// 现金支付更改支付状态
		if (orderInfo.getPayType().getId() == PayTypeCode.CASH) {
			orderInfo.setPayState(new PayState(PayStateCode.HAS_PAY));
		}

		orderDaoImpl.update(orderInfo);

		return BaseDto.getSuccessResult(null);
	}

	@Override
	public List<AirBottleInfoReportDetailVo> getDetailsInventoryInfo(List<String> l, Field filed) {

		return storeInventoryInfoDaoImpl.getDetailsInventoryInfo(l, filed);
	}
	
	@Override
	public List<StoreInventoryInfoVo> getStoreInventoryInfoVo(List<String> l, Field filed) {
		
		return storeInventoryInfoDaoImpl.getStoreInventoryInfoVo(l,filed);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> heavyBottleBackFromClient(MobileAirBottleSumbitEntity entity, User user) {
		
		Date nowTime = new Date();

		Integer orderId = entity.getOrderId();
		OrderInfo orderInfo = null;

		if(user.getSecondCategory() == null){
			return BaseDto.getFailResult(CommonCode.NOT_STORE_MAN);
		}
		
		if (orderId != null) {
			orderInfo = orderDaoImpl.findById(orderId);
		} else {
			orderInfo = orderDaoImpl.findByOrderCode(entity.getOrderCode());
			if (orderInfo == null) {
				return BaseDto.getFailResult(CommonCode.ORDER_NOT_EXIST);
			}
			orderId = orderInfo.getId();
		}

		Integer clientId = orderInfo.getClientInfo().getId();

		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds())) {

			List<OrderEmptyBottleInfo> orderEmptyBottleInfos = orderEmptyBottleInfoDaoImpl.findByOrderId(orderId);

			if (orderEmptyBottleInfos.size() > 0) {
				return BaseDto.getFailResult(CommonCode.ORDER_HAS_EMPTY_BOTTLE_NOT_FINISH);
			}

			ClientInventoryInfo clientInventoryInfo = clientInventoryInfoDaoImpl.findByClientIdAndBottleId(clientId, bottleId);

			if (clientInventoryInfo == null) {
				return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
			}

			OrderAirBottleInfo orderAirBottleInfo = orderAirBottleInfoDaoImpl.findByOrderIdAndBottleId(orderId, bottleId);

			if (orderAirBottleInfo == null) {
				return BaseDto.getFailResult(CommonCode.ORDER_AND_CLIENT_NOT_MATCH);
			}

			orderAirBottleInfoDaoImpl.delete(orderAirBottleInfo);
			clientInventoryInfoDaoImpl.delete(clientInventoryInfo);

			int orderNum = orderAirBottleInfoDaoImpl.findOrderNumByOrder(orderId).intValue();

			if (orderNum < orderInfo.getOrder_number()) {

				orderInfo.setState(new OrderInfoState(OrderInfoStateCode.ALREADY_DISPATCHING_BUT_NO_DELIVERY));

				orderDaoImpl.update(orderInfo);

			}

			// 更新用户气瓶记录
			ClientAirBottleRecord clientAirBottleRecord = clientAirBottleRecordDaoImpl.getRecordByClientIdAndAirBottleId(clientId, bottleId);
			if (clientAirBottleRecord != null) {
				clientAirBottleRecord.setEnd_time(nowTime);
				clientAirBottleRecord.setState(new ClientAirBottleRecordState(ClientAirBottleReocrdStateCode.BACK_RETURN));
				clientAirBottleRecordDaoImpl.update(clientAirBottleRecord);
			}

			// 新增库存信息
			StoreInventoryInfo storeInventoryInfo = new StoreInventoryInfo(clientInventoryInfo.getAirBottleInfo(), user.getSecondCategory(), new AirBottleState(
					AirBottleStateCode.HEAVY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), nowTime, user);
			storeInventoryInfoDaoImpl.insert(storeInventoryInfo);


			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, clientInventoryInfo.getAirBottleInfo(), null, user, null,
					clientAirBottleRecord.getClientInfo(), AirBottleTrackingRecordCode.HEAVY_BOTTLE_BACK_FROM_CLIENT_IN_DELIVERY_MAN, user);

		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> heavyBottleBackFromDeliveryMan(MobileAirBottleSumbitEntity entity, User user) {
		
		Integer deliveryManId = entity.getDeliveryManId();

		User deliveryMan = userDaoImpl.findById(deliveryManId);

		SecondCategory secondCategory = user.getSecondCategory();

		if (secondCategory == null) {
			return BaseDto.getFailResult(CommonCode.NOT_STORE_MAN);
		}

		Date nowTime = new Date();

		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds())) {

			DeliveryManInventoryInfo dii = deliveryManInventoryInfoDaoImpl.findByDeliveryManIdAndBottleId(deliveryManId, bottleId,
					AirBottleStateCode.HEAVY_BOTTLE, AirBottleInventoryStateCode.ALREADY_RECEIVE);
			deliveryManInventoryInfoDaoImpl.delete(dii);

			StoreInventoryInfo storeInventoryInfo = new StoreInventoryInfo(dii.getAirBottleInfo(), secondCategory, new AirBottleState(
					AirBottleStateCode.HEAVY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), nowTime, user);
			storeInventoryInfoDaoImpl.insert(storeInventoryInfo);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, dii.getAirBottleInfo(), secondCategory, deliveryMan,
					null, null, AirBottleTrackingRecordCode.HEAVY_BOTTLE_BACK_FROM_CLIENT_BY_DELIVERY_MAN, user);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> heavyBottlePickFromWarehouse(MobileAirBottleSumbitEntity entity, User user) {
		
		WarehouseInfo warehouseInfo = user.getWarehouseInfo();

		SecondCategory secondCategory = user.getSecondCategory();

		if (warehouseInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_WAREHOUSE_MAN);
		}

		
		if (secondCategory == null) {
			return BaseDto.getFailResult(CommonCode.NOT_STORE_MAN);
		}

		Date nowTime = new Date();

		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds())) {

			WarehouseInventoryInfo wii = warehouseInventoryInfoDaoImpl.findByWarehouseIdAndBottleId(warehouseInfo.getId(), bottleId,
					AirBottleStateCode.HEAVY_BOTTLE, AirBottleInventoryStateCode.ALREADY_RECEIVE);
			
			if (wii == null) {
				return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
			}
			
			warehouseInventoryInfoDaoImpl.delete(wii);

			StoreInventoryInfo storeInventoryInfo = new StoreInventoryInfo(wii.getAirBottleInfo(), secondCategory, new AirBottleState(
					AirBottleStateCode.HEAVY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), nowTime, user);
			storeInventoryInfoDaoImpl.insert(storeInventoryInfo);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, wii.getAirBottleInfo(), secondCategory, null,
					warehouseInfo, null, AirBottleTrackingRecordCode.HEAVY_BOTTLE_PICK_FROM_WAREHOUSE_IN_STORE, user);
		}

		return BaseDto.getSuccessResult(null);
	}

}
