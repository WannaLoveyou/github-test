package com.qian.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.code.AirBottleInventoryStateCode;
import com.qian.code.AirBottleStateCode;
import com.qian.code.AirBottleTrackingRecordCode;
import com.qian.code.AirBottleUpCloudStateCode;
import com.qian.code.ClientAirBottleReocrdStateCode;
import com.qian.code.DeliveryTypeCode;
import com.qian.code.IsscrapCode;
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
import com.qian.entity.AirBottleInfo;
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
import com.qian.entity.StoreCustomAirBottlePriceInfo;
import com.qian.entity.User;
import com.qian.entity.WarehouseInfo;
import com.qian.entity.WarehouseInventoryInfo;
import com.qian.mobile.entity.MobileAirBottleCheckEntity;
import com.qian.mobile.entity.MobileAirBottleSumbitEntity;
import com.qian.mobile.entity.MobileOrderInfo;
import com.qian.service.WarehouseInventoryInfoService;
import com.qian.util.AirBottleCodeInitUtils;
import com.qian.util.AirBottleTrackingRecordUtils;
import com.qian.util.CodeUtils;
import com.qian.util.InitDataUtils;
import com.qian.vo.AirBottleInfoReportDetailVo;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;
import com.qian.vo.WarehouseInventoryInfoVo;

/**
 * @author Lu Kongwen
 * @version Create time：2018-1-26 上午11:57:57
 * @Description
 */
@Service
@Transactional
public class WarehouseInventoryInfoServiceImpl implements WarehouseInventoryInfoService {

	@Autowired
	private WarehouseInventoryInfoDaoImpl warehouseInventoryInfoDaoImpl;

	@Autowired
	private AirBottleInfoDaoImpl airBottleInfoDaoImpl;

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private DeliveryManInventoryInfoDaoImpl deliveryManInventoryInfoDaoImpl;

	@Autowired
	private AirBottleTrackingRecordDaoImpl airBottleTrackingRecordDaoImpl;

	@Autowired
	private ClientInventoryInfoDaoImpl clientInventoryInfoDaoImpl;

	@Autowired
	private ClientAirBottleRecordDaoImpl clientAirBottleRecordDaoImpl;

	@Autowired
	private ClientInfoDaoImpl clientInfoDaoImpl;

	@Autowired
	private StoreInventoryInfoDaoImpl storeInventoryInfoDaoImpl;

	@Autowired
	private AirBottleTypeDaoImpl airBottleTypeDaoImpl;

	@Autowired
	private StoreCustomAirBottlePriceInfoDaoImpl storeCustomAirBottlePriceInfoDaoImpl;

	@Autowired
	private OrderDaoImpl orderDaoImpl;

	@Autowired
	private OrderAirBottleInfoDaoImpl orderAirBottleInfoDaoImpl;

	@Autowired
	private OrderEmptyBottleInfoDaoImpl orderEmptyBottleInfoDaoImpl;

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> forceEmptyBottleStorage(MobileAirBottleSumbitEntity entity, User user) {

		Date nowTime = new Date();

		WarehouseInfo warehouseInfo = user.getWarehouseInfo();

		if (warehouseInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_WAREHOUSE_MAN);
		}

		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds())) {

			AirBottleInfo airBottleInfo = airBottleInfoDaoImpl.findById(bottleId);

			// 清除所有库存信息
			AirBottleTrackingRecordUtils.clearInventoryInfo(bottleId, warehouseInventoryInfoDaoImpl, storeInventoryInfoDaoImpl,
					deliveryManInventoryInfoDaoImpl, clientInventoryInfoDaoImpl, clientAirBottleRecordDaoImpl, nowTime);

			// 新增库存信息
			WarehouseInventoryInfo newWarehouseInventoryInfo = new WarehouseInventoryInfo(warehouseInfo, airBottleInfo, new AirBottleState(
					AirBottleStateCode.EMPTY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), user, nowTime);
			warehouseInventoryInfoDaoImpl.insert(newWarehouseInventoryInfo);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, airBottleInfo, null, null, warehouseInfo, null,
					AirBottleTrackingRecordCode.FORCE_EMPTY_BOTTLE_STORAGE_IN_WAREHOUSE, user);

		}

		return BaseDto.getSuccessResult(null);
	}

	@Override
	public WarehouseInventoryInfo findByWarehouseIdAndBottleCode(MobileAirBottleCheckEntity entity, int airBottleState, int airBottleInventoryState) {

		User user = userDaoImpl.findById(entity.getUserId());

		if (user.getWarehouseInfo() == null) {
			return null;
		}

		return warehouseInventoryInfoDaoImpl.findByWarehouseIdAndBottleCode(user.getWarehouseInfo().getId(),
				AirBottleCodeInitUtils.initCode(entity.getBottleCode()), airBottleState, airBottleInventoryState);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> heavyBottleOutToDeliveryMan(MobileAirBottleSumbitEntity entity, User user) {

		Integer deliveryManId = entity.getDeliveryManId();

		WarehouseInfo warehouseInfo = user.getWarehouseInfo();

		if (warehouseInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_WAREHOUSE_MAN);
		}

		User deliveryMan = userDaoImpl.findById(deliveryManId);// 查询出配送工信息

		Date nowTime = new Date();

		// 遍历气瓶编码
		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds())) {

			WarehouseInventoryInfo wii = warehouseInventoryInfoDaoImpl.findByWarehouseIdAndBottleId(warehouseInfo.getId(), bottleId,
					AirBottleStateCode.HEAVY_BOTTLE, AirBottleInventoryStateCode.ALREADY_RECEIVE);
			if (wii == null) {
				return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
			}
			warehouseInventoryInfoDaoImpl.delete(wii);

			DeliveryManInventoryInfo deliveryManii = new DeliveryManInventoryInfo(deliveryMan, wii.getAirBottleInfo(), new AirBottleState(
					AirBottleStateCode.HEAVY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), deliveryMan, nowTime);
			deliveryManInventoryInfoDaoImpl.insert(deliveryManii);

			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, wii.getAirBottleInfo(), null, deliveryMan, warehouseInfo,
					null, AirBottleTrackingRecordCode.HEAVY_BOTTLE_OUT_IN_WAREHOUSE, user);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> emptyBottleStorageFromDeliveryMan(MobileAirBottleSumbitEntity entity, User user) {

		Integer deliveryManId = entity.getDeliveryManId();

		User deliveryMan = userDaoImpl.findById(deliveryManId);

		WarehouseInfo warehouseInfo = user.getWarehouseInfo();

		if (warehouseInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_WAREHOUSE_MAN);
		}

		Date nowTime = new Date();

		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds())) {

			DeliveryManInventoryInfo deliveryManInventoryInfo = deliveryManInventoryInfoDaoImpl.findByDeliveryManIdAndBottleId(deliveryMan.getId(), bottleId,
					AirBottleStateCode.EMPTY_BOTTLE, AirBottleInventoryStateCode.ALREADY_RECEIVE);
			deliveryManInventoryInfoDaoImpl.delete(deliveryManInventoryInfo);

			// 新增库存信息
			WarehouseInventoryInfo warehouseInventoryInfo = new WarehouseInventoryInfo(warehouseInfo, deliveryManInventoryInfo.getAirBottleInfo(),
					new AirBottleState(AirBottleStateCode.EMPTY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), user,
					nowTime);
			warehouseInventoryInfoDaoImpl.insert(warehouseInventoryInfo);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, deliveryManInventoryInfo.getAirBottleInfo(), null,
					deliveryMan, warehouseInfo, null, AirBottleTrackingRecordCode.EMPTY_BOTTLE_STORAGE_IN_WAREHOUSE, user);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> heavyBottleBackFromDeliveryMan(MobileAirBottleSumbitEntity entity, User user) {

		Integer deliveryManId = entity.getDeliveryManId();

		User deliveryMan = userDaoImpl.findById(deliveryManId);

		WarehouseInfo warehouseInfo = user.getWarehouseInfo();

		if (warehouseInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_WAREHOUSE_MAN);
		}

		Date nowTime = new Date();

		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds())) {

			DeliveryManInventoryInfo dii = deliveryManInventoryInfoDaoImpl.findByDeliveryManIdAndBottleId(deliveryManId, bottleId,
					AirBottleStateCode.HEAVY_BOTTLE, AirBottleInventoryStateCode.ALREADY_RECEIVE);
			deliveryManInventoryInfoDaoImpl.delete(dii);

			WarehouseInventoryInfo warhouseInventory = new WarehouseInventoryInfo(warehouseInfo, dii.getAirBottleInfo(), new AirBottleState(
					AirBottleStateCode.HEAVY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), user, nowTime);
			warehouseInventoryInfoDaoImpl.insert(warhouseInventory);

			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, dii.getAirBottleInfo(), null, deliveryMan, warehouseInfo,
					null, AirBottleTrackingRecordCode.HEAVY_BOTTLE_STORAGE_IN_WAREHOUSE_FROM_DELIVERYMAN, user);

		}

		return BaseDto.getSuccessResult(null);

	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> heavyBottleStorageByFilling(MobileAirBottleSumbitEntity entity, User user) {

		WarehouseInfo warehouseInfo = user.getWarehouseInfo();

		if (warehouseInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_WAREHOUSE_MAN);
		}

		Date nowTime = new Date();

		// 遍历气瓶编码
		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds())) {

			WarehouseInventoryInfo wii = warehouseInventoryInfoDaoImpl.findByWarehouseIdAndBottleId(warehouseInfo.getId(), bottleId,
					AirBottleStateCode.EMPTY_BOTTLE, AirBottleInventoryStateCode.ALREADY_RECEIVE);
			if (wii == null) {
				return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
			}
			warehouseInventoryInfoDaoImpl.delete(wii);

			AirBottleInfo airBottleInfo = wii.getAirBottleInfo();

			WarehouseInventoryInfo warhouseInventory = new WarehouseInventoryInfo(warehouseInfo, airBottleInfo, new AirBottleState(
					AirBottleStateCode.HEAVY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), user, nowTime);

			warehouseInventoryInfoDaoImpl.insert(warhouseInventory);

			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, airBottleInfo, null, null, warehouseInfo, null,
					AirBottleTrackingRecordCode.HEAVY_BOTTLE_STORAGE_IN_WAREHOUSE, user);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> fillingPickUpInWarehouseForClient(MobileAirBottleSumbitEntity entity, User user) {

		Integer clientId = entity.getClientId();
		Integer bottleTypeId = entity.getAirBottleTypeId();
		String bottleIds = entity.getBottleIds();

		BaseDto<OrderInfo> orderDto = addPickUpInWarehouseOrder(clientId, bottleTypeId, bottleIds, user);

		if (orderDto.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(CommonCode.SYSTEM_EXCEPTION);
		}

		// 填充订单
		BaseDto<Object> b = fillingPickUpInWarehouseForClient(bottleIds, orderDto.getData().getId(), user);
		if (b.getCode() != CommonCode.OK.getCode()) {
			orderDaoImpl.delete(orderDto.getData()); // 网络延时导致重复单
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	private BaseDto<Object> fillingPickUpInWarehouseForClient(String bottleIds, int orderId, User user) {

		if (user.getSecondCategory() == null) {
			return BaseDto.getFailResult(CommonCode.NOT_STORE_MAN);
		}

		if (user.getWarehouseInfo() == null) {
			return BaseDto.getFailResult(CommonCode.NOT_WAREHOUSE_MAN);
		}

		OrderInfo orderInfo = orderDaoImpl.findById(orderId);

		Integer clientId = orderInfo.getClientInfo().getId();

		Date nowTime = new Date();

		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(bottleIds)) {

			ClientInventoryInfo clientInventoryInfo = clientInventoryInfoDaoImpl.findByClientIdAndBottleId(clientId, bottleId);

			if (clientInventoryInfo == null) {
				return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
			}
			clientInventoryInfo.setCreate_time(nowTime);
			clientInventoryInfo.setOperator(user);
			clientInventoryInfoDaoImpl.update(clientInventoryInfo);

			// 更新用户气瓶记录
			ClientAirBottleRecord clientAirBottleRecord = clientAirBottleRecordDaoImpl.getRecordByClientIdAndAirBottleId(clientId, bottleId);
			if (clientAirBottleRecord != null) {
				clientAirBottleRecord.setEnd_time(nowTime);
				clientAirBottleRecord.setState(new ClientAirBottleRecordState(ClientAirBottleReocrdStateCode.ALREAY_RETURN));
				clientAirBottleRecordDaoImpl.update(clientAirBottleRecord);
			}

			// 用户气瓶记录
			ClientAirBottleRecord newClientAirBottleRecord = new ClientAirBottleRecord(orderInfo.getClientInfo(), clientInventoryInfo.getAirBottleInfo(),
					nowTime);
			clientAirBottleRecordDaoImpl.insert(newClientAirBottleRecord);

			// 订单重瓶
			OrderAirBottleInfo orderAirBottleInfo = new OrderAirBottleInfo();
			orderAirBottleInfo.setOrderInfo(orderInfo);
			orderAirBottleInfo.setAirBottleInfo(clientInventoryInfo.getAirBottleInfo());
			orderAirBottleInfoDaoImpl.insert(orderAirBottleInfo);

			// 修改订单空瓶
			OrderEmptyBottleInfo orderEmptyBottleInfo = new OrderEmptyBottleInfo();
			orderEmptyBottleInfo.setOrderInfo(orderInfo);
			orderEmptyBottleInfo.setAirBottleInfo(clientInventoryInfo.getAirBottleInfo());
			orderEmptyBottleInfoDaoImpl.insert(orderEmptyBottleInfo);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, clientInventoryInfo.getAirBottleInfo(), null, null,
					user.getWarehouseInfo(), orderInfo.getClientInfo(), AirBottleTrackingRecordCode.HEAVY_BOTTLE_FILLING_IN_WAREHOUSE, user);

		}

		if (orderInfo.getPayType().getId() == PayTypeCode.CASH) {
			orderInfo.setPayState(new PayState(PayStateCode.HAS_PAY));
		}

		orderInfo.setState(new OrderInfoState(OrderInfoStateCode.ALREADY_FINISH)); // 已完成
		orderDaoImpl.update(orderInfo);

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	private BaseDto<OrderInfo> addPickUpInWarehouseOrder(Integer clientId, Integer bottleTypeId, String bottleIds, User user) {

		Date nowTime = new Date();

		int OrderNum = bottleIds.split(",").length;

		if (user.getSecondCategory() == null) {
			return BaseDto.getFailResult(CommonCode.NOT_STORE_MAN);
		}

		OrderInfo orderInfo = new OrderInfo();

		AirBottleType airBottleType = airBottleTypeDaoImpl.findById(bottleTypeId);
		orderInfo.setAirBottleType(airBottleType);

		ClientInfo clientInfo = clientInfoDaoImpl.findById(clientId);
		clientInfo.setLast_order_time(nowTime);
		clientInfoDaoImpl.update(clientInfo);

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
		orderInfo.setSecondCategory(user.getSecondCategory());

		orderDaoImpl.insert(orderInfo);

		orderInfo.setOrder_code(CodeUtils.setOrderCode(orderInfo));

		orderDaoImpl.update(orderInfo);

		return BaseDto.getSuccessResult(orderInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> forceFillingPickUpInWarehouseForClient(MobileAirBottleSumbitEntity entity, User user) {

		Integer clientId = entity.getClientId();
		Integer bottleTypeId = entity.getAirBottleTypeId();
		String bottleIds = entity.getBottleIds();

		BaseDto<OrderInfo> orderDto = addPickUpInWarehouseOrder(clientId, bottleTypeId, bottleIds, user);

		// 填充订单
		BaseDto<Object> b = forceFillingPickUpInWarehouseForClient(bottleIds, orderDto.getData().getId(), user);

		if (b.getCode() != CommonCode.OK.getCode()) {
			orderDaoImpl.delete(orderDto.getData()); // 网络延时导致重复单
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	private BaseDto<Object> forceFillingPickUpInWarehouseForClient(String bottleIds, int orderId, User user) {

		if (user.getSecondCategory() == null) {
			return BaseDto.getFailResult(CommonCode.NOT_STORE_MAN);
		}

		if (user.getWarehouseInfo() == null) {
			return BaseDto.getFailResult(CommonCode.NOT_WAREHOUSE_MAN);
		}

		OrderInfo orderInfo = orderDaoImpl.findById(orderId);

		Date nowTime = new Date();

		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(bottleIds)) {

			AirBottleInfo airBottleInfo = airBottleInfoDaoImpl.findById(bottleId);

			// 清除所有库存信息
			AirBottleTrackingRecordUtils.clearInventoryInfo(bottleId, warehouseInventoryInfoDaoImpl, storeInventoryInfoDaoImpl,
					deliveryManInventoryInfoDaoImpl, clientInventoryInfoDaoImpl, clientAirBottleRecordDaoImpl, nowTime);

			ClientInventoryInfo clientInventoryInfo = new ClientInventoryInfo(orderInfo.getClientInfo(), airBottleInfo, new AirBottleState(
					AirBottleStateCode.HEAVY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), user, nowTime);
			clientInventoryInfoDaoImpl.insert(clientInventoryInfo);

			// 用户气瓶记录
			ClientAirBottleRecord clientAirBottleRecord = new ClientAirBottleRecord(orderInfo.getClientInfo(), clientInventoryInfo.getAirBottleInfo(), nowTime);
			clientAirBottleRecordDaoImpl.insert(clientAirBottleRecord);

			// 订单重瓶
			OrderAirBottleInfo orderAirBottleInfo = new OrderAirBottleInfo();
			orderAirBottleInfo.setOrderInfo(orderInfo);
			orderAirBottleInfo.setAirBottleInfo(clientInventoryInfo.getAirBottleInfo());
			orderAirBottleInfoDaoImpl.insert(orderAirBottleInfo);

			// 修改订单空瓶
			OrderEmptyBottleInfo orderEmptyBottleInfo = new OrderEmptyBottleInfo();
			orderEmptyBottleInfo.setOrderInfo(orderInfo);
			orderEmptyBottleInfo.setAirBottleInfo(clientInventoryInfo.getAirBottleInfo());
			orderEmptyBottleInfoDaoImpl.insert(orderEmptyBottleInfo);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, clientInventoryInfo.getAirBottleInfo(), null, null,
					user.getWarehouseInfo(), orderInfo.getClientInfo(), AirBottleTrackingRecordCode.HEAVY_BOTTLE_FORCE_FILLING_IN_WAREHOUSE, user);

		}

		if (orderInfo.getPayType().getId() == PayTypeCode.CASH) {
			orderInfo.setPayState(new PayState(PayStateCode.HAS_PAY));
		}

		orderInfo.setState(new OrderInfoState(OrderInfoStateCode.ALREADY_FINISH)); // 已完成
		orderDaoImpl.update(orderInfo);

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<MobileOrderInfo> addMobileWarehouseOrder(MobileAirBottleSumbitEntity entity, User user) {

		Integer clientId = entity.getClientId();
		Integer bottleTypeId = entity.getAirBottleTypeId();
		String bottleIds = entity.getBottleIds();

		BaseDto<OrderInfo> orderDto = addPickUpInWarehouseOrder(clientId, bottleTypeId, bottleIds, user);

		entity.setOrderId(orderDto.getData().getId());

		BaseDto<Object> b = heavyBottleOutToClient(entity, user);

		if (b.getCode() != CommonCode.OK.getCode()) {
			orderDaoImpl.delete(orderDto.getData()); // 网络延时导致重复单
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult(new MobileOrderInfo(orderDto.getData()));
	}

	@SuppressWarnings("unchecked")
	public BaseDto<Object> heavyBottleOutToClient(MobileAirBottleSumbitEntity entity, User user) {

		Integer clientId = entity.getClientId();

		Integer orderId = entity.getOrderId();

		OrderInfo orderInfo = orderDaoImpl.findById(orderId);

		ClientInfo clientInfo = clientInfoDaoImpl.findById(clientId);

		WarehouseInfo warehouseInfo = user.getWarehouseInfo();

		if (warehouseInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_WAREHOUSE_MAN);
		}

		int orderCheckNum = orderAirBottleInfoDaoImpl.findOrderNumByOrder(orderId).intValue();

		int sumbitNum = AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds()).size();

		if (orderCheckNum + sumbitNum > orderInfo.getOrder_number()) {
			return BaseDto.getFailResult(CommonCode.ORDER_OUT_OF_BOTTLE_NUM);
		}

		Date nowTime = new Date();

		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds())) {

			WarehouseInventoryInfo wii = warehouseInventoryInfoDaoImpl.findByWarehouseIdAndBottleId(warehouseInfo.getId(), bottleId,
					AirBottleStateCode.HEAVY_BOTTLE, AirBottleInventoryStateCode.ALREADY_RECEIVE);
			if (wii == null) {
				return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
			}
			warehouseInventoryInfoDaoImpl.delete(wii);

			ClientInventoryInfo clientInventoryInfo = new ClientInventoryInfo(clientInfo, wii.getAirBottleInfo(), new AirBottleState(
					AirBottleStateCode.HEAVY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), user, nowTime);
			clientInventoryInfoDaoImpl.insert(clientInventoryInfo);

			// 订单重瓶记录
			OrderAirBottleInfo orderAirBottleInfo = new OrderAirBottleInfo(orderInfo, wii.getAirBottleInfo(), nowTime, user);
			orderAirBottleInfoDaoImpl.insert(orderAirBottleInfo);

			// 用户气瓶记录
			ClientAirBottleRecord clientAirBottleRecord = new ClientAirBottleRecord(clientInfo, wii.getAirBottleInfo(), nowTime);
			clientAirBottleRecordDaoImpl.insert(clientAirBottleRecord);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, wii.getAirBottleInfo(), null, user, warehouseInfo,
					clientInfo, AirBottleTrackingRecordCode.HEAVY_BOTTLE_OUT_IN_WAREHOUSE_TO_CLIENT, user);

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

		WarehouseInfo warehouseInfo = user.getWarehouseInfo();

		if (warehouseInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_WAREHOUSE_MAN);
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
			WarehouseInventoryInfo warhouseInventory = new WarehouseInventoryInfo(warehouseInfo, clientInventoryInfo.getAirBottleInfo(), new AirBottleState(
					AirBottleStateCode.EMPTY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), user, nowTime);
			warehouseInventoryInfoDaoImpl.insert(warhouseInventory);

			// 更新用户气瓶记录
			ClientAirBottleRecord clientAirBottleRecord = clientAirBottleRecordDaoImpl.getRecordByClientIdAndAirBottleId(clientId, bottleId);
			if (clientAirBottleRecord != null) {
				clientAirBottleRecord.setEnd_time(nowTime);
				clientAirBottleRecord.setState(new ClientAirBottleRecordState(ClientAirBottleReocrdStateCode.ALREAY_RETURN));
				clientAirBottleRecordDaoImpl.update(clientAirBottleRecord);
			}

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, clientInventoryInfo.getAirBottleInfo(), null, user,
					warehouseInfo, clientInfo, AirBottleTrackingRecordCode.EMPTY_BOTTLE_STORAGE_IN_WAREHOUSE_FROM_CLIENT, user);
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

		return warehouseInventoryInfoDaoImpl.getDetailsInventoryInfo(l, filed);
	}

	@Override
	public List<WarehouseInventoryInfoVo> getWarehouseInventoryInfoVo(List<String> l, Field filed) {

		return warehouseInventoryInfoDaoImpl.getWarehouseInventoryInfoVo(l, filed);
	}

	/**
	 * 强制报废
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Map<String, Object>> forceScrapBottleStorage(String bottleIds, User user) {
		String[] bottleIdsStr = bottleIds.split(",");

		Date nowTime = new Date();

		WarehouseInfo warehouseInfo = user.getWarehouseInfo();

		if (warehouseInfo == null) {
			return BaseDto.getFailResult(CommonCode.WAREHOUSE_NULL);
		}
		
		for (String bottleId : bottleIdsStr) {

			if (bottleId == "") {
				continue;
			}

			AirBottleInfo airBottleInfo = airBottleInfoDaoImpl.findById(Integer.valueOf(bottleId));

			if (airBottleInfo == null) {
				return BaseDto.getFailResult(CommonCode.NOT_FIND_AIR_BOTTLE);
			}

			// 判断是否是未报废的，只有未报废的才能强制报废
			if (airBottleInfo.getIsscrap() != IsscrapCode.NO_SCRAP) {
				return BaseDto.getFailResult(CommonCode.SYSTEM_EXCEPTION);
			}

			airBottleInfo.setIsscrap(IsscrapCode.SCRAP);
			airBottleInfo.setUpState(AirBottleUpCloudStateCode.WAIT_UP);
			airBottleInfoDaoImpl.update(airBottleInfo);

			// 清除所有库存信息
			AirBottleTrackingRecordUtils.clearInventoryInfo(airBottleInfo.getId(), warehouseInventoryInfoDaoImpl, storeInventoryInfoDaoImpl,
					deliveryManInventoryInfoDaoImpl, clientInventoryInfoDaoImpl, clientAirBottleRecordDaoImpl, nowTime);


			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, airBottleInfo, null, null, warehouseInfo, null,
					AirBottleTrackingRecordCode.FORCE_SCRAP_BOTTLE_STORAGE_IN_WAREHOUSE, user);

		}

		return BaseDto.getSuccessResult(null);
	}

	/**
	 * 撤销报废
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Map<String, Object>> cancelForceScrapBottleStorage(String bottleIds, User user) {
		String[] bottleIdsStr = bottleIds.split(",");

		for (String bottleId : bottleIdsStr) {

			if (bottleId == "") {
				continue;
			}

			AirBottleInfo airBottleInfo = airBottleInfoDaoImpl.findById(Integer.valueOf(bottleId));

			if (airBottleInfo == null) {
				return BaseDto.getFailResult(CommonCode.NOT_FIND_AIR_BOTTLE);
			}

			// 判断是否是报废的，只有报废的才能撤销报废
			if (airBottleInfo.getIsscrap() != IsscrapCode.SCRAP) {
				return BaseDto.getFailResult(CommonCode.SYSTEM_EXCEPTION);
			}

			airBottleInfo.setIsscrap(IsscrapCode.NO_SCRAP);
			airBottleInfo.setUpState(AirBottleUpCloudStateCode.WAIT_UP);
			airBottleInfoDaoImpl.update(airBottleInfo);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, airBottleInfo, null, null, null, null,
					AirBottleTrackingRecordCode.CANCEL_SCRAP, user);

		}

		return BaseDto.getSuccessResult(null);
	}

}
