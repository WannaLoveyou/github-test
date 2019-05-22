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
import com.qian.entity.User;
import com.qian.mobile.entity.MobileAirBottleCheckEntity;
import com.qian.mobile.entity.MobileAirBottleSumbitEntity;
import com.qian.mobile.entity.MobileOrderInfo;
import com.qian.mobile.entity.MobileTotalInventoryInfo;
import com.qian.service.DeliveryManInventoryInfoService;
import com.qian.util.AirBottleCodeInitUtils;
import com.qian.util.AirBottleTrackingRecordUtils;
import com.qian.util.CodeUtils;
import com.qian.util.InitDataUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-2-24 下午4:13:27
 * @Description
 */
@Service
@Transactional
public class DeliveryManInventoryInfoServiceImpl implements DeliveryManInventoryInfoService {

	@Autowired
	private WarehouseInventoryInfoDaoImpl warehouseInventoryInfoDaoImpl;

	@Autowired
	private DeliveryManInventoryInfoDaoImpl deliveryManInventoryInfoDaoImpl;

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private ClientInfoDaoImpl clientInfoDaoImpl;

	@Autowired
	private ClientInventoryInfoDaoImpl clientInventoryInfoDaoImpl;

	@Autowired
	private AirBottleTrackingRecordDaoImpl airBottleTrackingRecordDaoImpl;

	@Autowired
	private AirBottleInfoDaoImpl airBottleInfoDaoImpl;

	@Autowired
	private ClientAirBottleRecordDaoImpl clientAirBottleRecordDaoImpl;

	@Autowired
	private StoreInventoryInfoDaoImpl storeInventoryInfoDaoImpl;

	@Autowired
	private OrderAirBottleInfoDaoImpl orderAirBottleInfoDaoImpl;

	@Autowired
	private OrderEmptyBottleInfoDaoImpl orderEmptyBottleInfoDaoImpl;

	@Autowired
	private OrderDaoImpl orderDaoImpl;

	@Autowired
	private AirBottleTypeDaoImpl airBottleTypeDaoImpl;

	@Autowired
	private StoreCustomAirBottlePriceInfoDaoImpl storeCustomAirBottlePriceInfoDaoImpl;

	@Override
	public DeliveryManInventoryInfo findByDeliveryManIdAndBottleCode(MobileAirBottleCheckEntity entity, int airBottleState, int airBottleInventoryState) {

		return deliveryManInventoryInfoDaoImpl.findByDeliveryManIdAndBottleCode(entity.getDeliveryManId(),
				AirBottleCodeInitUtils.initCode(entity.getBottleCode()), airBottleState, airBottleInventoryState);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> heavyBottleOutToClient(MobileAirBottleSumbitEntity entity, User user) {

		Integer clientId = entity.getClientId();

		Integer orderId = entity.getOrderId();

		OrderInfo orderInfo = orderDaoImpl.findById(orderId);

		ClientInfo clientInfo = clientInfoDaoImpl.findById(clientId);

		int orderCheckNum = orderAirBottleInfoDaoImpl.findOrderNumByOrder(orderId).intValue();

		int sumbitNum = AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds()).size();

		if (orderCheckNum + sumbitNum > orderInfo.getOrder_number()) {
			return BaseDto.getFailResult(CommonCode.ORDER_OUT_OF_BOTTLE_NUM);
		}

		Date nowTime = new Date();

		for (Integer bottleId : AirBottleCodeInitUtils.getAirBottleIds(entity.getBottleIds())) {

			DeliveryManInventoryInfo dii = deliveryManInventoryInfoDaoImpl.findByDeliveryManIdAndBottleId(user.getId(), bottleId,
					AirBottleStateCode.HEAVY_BOTTLE, AirBottleInventoryStateCode.ALREADY_RECEIVE);
			if (dii == null) {
				return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
			}
			deliveryManInventoryInfoDaoImpl.delete(dii);

			ClientInventoryInfo clientInventoryInfo = new ClientInventoryInfo(clientInfo, dii.getAirBottleInfo(), new AirBottleState(
					AirBottleStateCode.HEAVY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), user, nowTime,
					entity.getLongitude(), entity.getLatitude());
			clientInventoryInfoDaoImpl.insert(clientInventoryInfo);

			// 订单重瓶记录
			OrderAirBottleInfo orderAirBottleInfo = new OrderAirBottleInfo(orderInfo, dii.getAirBottleInfo(), nowTime, user);
			orderAirBottleInfoDaoImpl.insert(orderAirBottleInfo);

			// 用户气瓶记录
			ClientAirBottleRecord clientAirBottleRecord = new ClientAirBottleRecord(clientInfo, dii.getAirBottleInfo(), nowTime);
			clientAirBottleRecordDaoImpl.insert(clientAirBottleRecord);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, dii.getAirBottleInfo(), null, user, null, clientInfo,
					AirBottleTrackingRecordCode.HEAVY_BOTTLE_OUT_IN_DELIVERY_MAN, user);

		}

		int orderNum = orderAirBottleInfoDaoImpl.findOrderNumByOrder(orderId).intValue();

		if (orderNum >= orderInfo.getOrder_number()) {
			orderInfo.setState(new OrderInfoState(OrderInfoStateCode.ALREADY_DELIVERY_BUT_NO_BACK)); // 已派送未回单
			orderDaoImpl.update(orderInfo);
		}

		return BaseDto.getSuccessResult(null);
	}

	@Override
	public DeliveryManInventoryInfo findByUserIdAndBottleCode(MobileAirBottleCheckEntity entity, int airBottleState, int airBottleInventoryState) {
		return deliveryManInventoryInfoDaoImpl.findByDeliveryManIdAndBottleCode(entity.getUserId(), AirBottleCodeInitUtils.initCode(entity.getBottleCode()),
				airBottleState, airBottleInventoryState);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> emptyBottleStorageFromClient(MobileAirBottleSumbitEntity entity, User user) {

		Integer clientId = entity.getClientId();

		Integer orderId = entity.getOrderId();

		OrderInfo orderInfo = orderDaoImpl.findById(orderId);

		ClientInfo clientInfo = clientInfoDaoImpl.findById(clientId);

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
			DeliveryManInventoryInfo deliveryManii = new DeliveryManInventoryInfo(user, clientInventoryInfo.getAirBottleInfo(), new AirBottleState(
					AirBottleStateCode.EMPTY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), user, nowTime);
			deliveryManInventoryInfoDaoImpl.insert(deliveryManii);

			// 更新用户气瓶记录
			ClientAirBottleRecord clientAirBottleRecord = clientAirBottleRecordDaoImpl.getRecordByClientIdAndAirBottleId(clientId, bottleId);
			if (clientAirBottleRecord != null) {
				clientAirBottleRecord.setEnd_time(nowTime);
				clientAirBottleRecord.setState(new ClientAirBottleRecordState(ClientAirBottleReocrdStateCode.ALREAY_RETURN));
				clientAirBottleRecordDaoImpl.update(clientAirBottleRecord);
			}

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, clientInventoryInfo.getAirBottleInfo(), null, user, null,
					clientInfo, AirBottleTrackingRecordCode.EMPTY_BOTTLE_STORAGE_IN_DELIVERY_MAN, user);
		}

		orderInfo.setState(new OrderInfoState(OrderInfoStateCode.ALREADY_FINISH));// 历史订单

		// 现金支付更改支付状态
		if (orderInfo.getPayType().getId() == PayTypeCode.CASH) {
			orderInfo.setPayState(new PayState(PayStateCode.HAS_PAY));
		}

		orderDaoImpl.update(orderInfo);

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> heavyBottleBackFromClient(MobileAirBottleSumbitEntity entity, User user) {

		Date nowTime = new Date();

		Integer orderId = entity.getOrderId();
		OrderInfo orderInfo = null;

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
			DeliveryManInventoryInfo deliveryManii = new DeliveryManInventoryInfo(user, clientInventoryInfo.getAirBottleInfo(), new AirBottleState(
					AirBottleStateCode.HEAVY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), user, nowTime);
			deliveryManInventoryInfoDaoImpl.insert(deliveryManii);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, clientInventoryInfo.getAirBottleInfo(), null, user, null,
					clientAirBottleRecord.getClientInfo(), AirBottleTrackingRecordCode.HEAVY_BOTTLE_BACK_FROM_CLIENT_IN_DELIVERY_MAN, user);

		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<MobileOrderInfo> addMobileDeliveryOrder(MobileAirBottleSumbitEntity entity, User user) {

		Date nowTime = new Date();

		int OrderNum = entity.getBottleIds().split(",").length;

		OrderInfo orderInfo = new OrderInfo();

		int bottleTypeId = entity.getAirBottleTypeId();

		int clientId = entity.getClientId();

		ClientInfo clientInfo = clientInfoDaoImpl.findById(clientId);

		SecondCategory secondCategory = user.getSecondCategory();

		if (secondCategory == null) {
			return BaseDto.getFailResult(CommonCode.NOT_STORE_MAN);
		}

		AirBottleType airBottleType = airBottleTypeDaoImpl.findById(bottleTypeId);
		orderInfo.setAirBottleType(airBottleType);

		double price = airBottleType.getPrice();
		double deliveryfee = airBottleType.getDelivery_fee();

		// 查询优惠门店
		StoreCustomAirBottlePriceInfo storeCustomAirBottlePriceInfo = storeCustomAirBottlePriceInfoDaoImpl.findByairBottleTypeIdAndSecondCategoryId(
				bottleTypeId, clientInfo.getSecondCategory().getId());
		if (storeCustomAirBottlePriceInfo != null) {
			if (storeCustomAirBottlePriceInfo.getCustom_price() != null) {
				price = storeCustomAirBottlePriceInfo.getCustom_price();
			}
			if (storeCustomAirBottlePriceInfo.getCustom_delivery_fee() != null) {
				deliveryfee = storeCustomAirBottlePriceInfo.getCustom_delivery_fee();
			}
		}

		orderInfo.setClientInfo(clientInfo);

		orderInfo.setOrder_appointment_time1(nowTime);
		orderInfo.setOrder_tel_number((InitDataUtils.findOrderNumber(clientInfo)));
		orderInfo.setOrder_address(clientInfo.getClient_address());

		orderInfo.setFloor_subsidies_money(0.0);
		orderInfo.setDelivery_fee(deliveryfee);

		orderInfo.setPrice(price);
		orderInfo.setOrder_number(OrderNum);
		orderInfo.setTotal_amount((orderInfo.getPrice() + orderInfo.getDelivery_fee()) * OrderNum);

		orderInfo.setCheck_fee(0);
		orderInfo.setDiscount_amount(0);
		orderInfo.setFee_total_amount(0);

		DeliveryType deliveryType = new DeliveryType();
		deliveryType.setId(DeliveryTypeCode.ORDER_DELIVERY);
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

		orderInfo.setDelivery_man(user);
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

	@Override
	public List<MobileTotalInventoryInfo> getDeliveryManTotalInventoryInfo(List<String> l, Field filed) {

		return deliveryManInventoryInfoDaoImpl.getDeliveryManTotalInventoryInfo(l, filed);
	}

}
