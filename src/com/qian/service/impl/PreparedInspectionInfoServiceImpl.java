package com.qian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.code.AirBottleInventoryStateCode;
import com.qian.code.AirBottleStateCode;
import com.qian.code.AirBottleTrackingRecordCode;
import com.qian.code.InspectionStateCode;
import com.qian.dao.impl.AirBottleInfoDaoImpl;
import com.qian.dao.impl.AirBottleTrackingRecordDaoImpl;
import com.qian.dao.impl.ClientAirBottleRecordDaoImpl;
import com.qian.dao.impl.ClientInventoryInfoDaoImpl;
import com.qian.dao.impl.DeliveryManInventoryInfoDaoImpl;
import com.qian.dao.impl.InspectionOrderInfoDaoImpl;
import com.qian.dao.impl.InspectionOrderItemInfoDaoImpl;
import com.qian.dao.impl.PreparedInspectionInfoDaoImpl;
import com.qian.dao.impl.StoreInventoryInfoDaoImpl;
import com.qian.dao.impl.WarehouseInventoryInfoDaoImpl;
import com.qian.entity.AirBottleInfo;
import com.qian.entity.AirBottleInventoryState;
import com.qian.entity.AirBottleState;
import com.qian.entity.InspectionOrderInfo;
import com.qian.entity.InspectionOrderItemInfo;
import com.qian.entity.InspectionState;
import com.qian.entity.PreparedInspectionInfo;
import com.qian.entity.User;
import com.qian.entity.WarehouseInfo;
import com.qian.entity.WarehouseInventoryInfo;
import com.qian.mobile.entity.MobileAirBottleSumbitEntity;
import com.qian.service.PreparedInspectionInfoService;
import com.qian.util.AirBottleCodeInitUtils;
import com.qian.util.AirBottleTrackingRecordUtils;
import com.qian.util.StringUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

@Service
@Transactional
public class PreparedInspectionInfoServiceImpl implements PreparedInspectionInfoService {

	@Autowired
	private PreparedInspectionInfoDaoImpl preparedInspectionInfoDaoImpl;

	@Autowired
	private InspectionOrderInfoDaoImpl inspectionOrderInfoDaoImpl;

	@Autowired
	private InspectionOrderItemInfoDaoImpl inspectionOrderItemInfoDaoImpl;

	@Autowired
	private AirBottleInfoDaoImpl airBottleInfoDaoImpl;

	@Autowired
	private WarehouseInventoryInfoDaoImpl warehouseInventoryInfoDaoImpl;

	@Autowired
	private StoreInventoryInfoDaoImpl storeInventoryInfoDaoImpl;

	@Autowired
	private DeliveryManInventoryInfoDaoImpl deliveryManInventoryInfoDaoImpl;

	@Autowired
	private ClientAirBottleRecordDaoImpl clientAirBottleRecordDaoImpl;

	@Autowired
	private ClientInventoryInfoDaoImpl clientInventoryInfoDaoImpl;

	@Autowired
	private AirBottleTrackingRecordDaoImpl airBottleTrackingRecordDaoImpl;

	public BaseDto<Map<String, Object>> getPageList(List<String> l, Field field, int page, int rows) {

		Map<String, Object> result = new HashMap<String, Object>();
		Long total = preparedInspectionInfoDaoImpl.getTotalNum(l, field);
		List<PreparedInspectionInfo> list = preparedInspectionInfoDaoImpl.getPageList(l, field, page, rows);

		result.put("total", total);
		result.put("rows", list);

		return BaseDto.getSuccessResult(result);
	}

	public BaseDto<Object> delByIds(String ids) {

		String[] idsArr = ids.split(",");
		List<PreparedInspectionInfo> delList = new ArrayList<PreparedInspectionInfo>();
		for (String id : idsArr) {
			delList.add(preparedInspectionInfoDaoImpl.findById(Integer.parseInt(id)));
		}
		preparedInspectionInfoDaoImpl.delete(delList);
		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	public BaseDto<Object> createInspectionOrder(List<String> l, Field field, User user) {

		List<PreparedInspectionInfo> preparedList = preparedInspectionInfoDaoImpl.getAllList(l, field);
		if (preparedList.size() == 0) {
			return BaseDto.getFailResult(CommonCode.SYSTEM_EXCEPTION);
		}
		
		WarehouseInfo warehouseInfo = user.getWarehouseInfo();

		if (warehouseInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_WAREHOUSE_MAN);
		}

		InspectionOrderInfo order = new InspectionOrderInfo(new Date(), user, warehouseInfo, new InspectionState(
				InspectionStateCode.NO_SUMBIT));
		inspectionOrderInfoDaoImpl.insert(order);

		order.setInspectionOrderNumber(String.valueOf(order.getId()));

		List<InspectionOrderItemInfo> items = new ArrayList<InspectionOrderItemInfo>();
		for (PreparedInspectionInfo preparedInspectionInfo : preparedList) {
			items.add(new InspectionOrderItemInfo(preparedInspectionInfo, order));
		}
		inspectionOrderItemInfoDaoImpl.insert(items);
		preparedInspectionInfoDaoImpl.delete(preparedList);
		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	public BaseDto<Object> bottleOutToInspection(MobileAirBottleSumbitEntity entity, User user) {

		String[] bottleCodesStr = entity.getBottleCodes().split(",");

		WarehouseInfo warehouseInfo = user.getWarehouseInfo();

		if (warehouseInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_WAREHOUSE_MAN);
		}

		Date nowTime = new Date();

		for (String bottleCode : bottleCodesStr) {

			if (!StringUtils.nonEmpty(bottleCode)) {
				continue;
			}

			String qrCode = new String(bottleCode);

			bottleCode = AirBottleCodeInitUtils.initCode(bottleCode);

			AirBottleInfo airBottleInfo = airBottleInfoDaoImpl.findByAirBottleCode(bottleCode);

			if (airBottleInfo == null) {
				return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
			}

			PreparedInspectionInfo preparedInspectionInfoCheck = preparedInspectionInfoDaoImpl.findByAirBottleId(airBottleInfo.getId());
			if (preparedInspectionInfoCheck != null) {
				return BaseDto.getFailResult(CommonCode.AIR_BOTTLE_HAS_INSPECTION);
			}

			// 清除所有库存信息
			AirBottleTrackingRecordUtils.clearInventoryInfo(airBottleInfo.getId(), warehouseInventoryInfoDaoImpl, storeInventoryInfoDaoImpl,
					deliveryManInventoryInfoDaoImpl, clientInventoryInfoDaoImpl, clientAirBottleRecordDaoImpl, nowTime);

			PreparedInspectionInfo preparedInspectionInfo = new PreparedInspectionInfo(airBottleInfo, warehouseInfo, user, nowTime, qrCode);
			preparedInspectionInfoDaoImpl.insert(preparedInspectionInfo);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, airBottleInfo, null, null, warehouseInfo, null,
					AirBottleTrackingRecordCode.BOTTLE_OUT_IN_WAREHOUSE_TO_INSPECTION, user);

		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	public BaseDto<Object> bottleBackFromInspection(MobileAirBottleSumbitEntity entity, User user) {

		String[] bottleCodesStr = entity.getBottleCodes().split(",");

		WarehouseInfo warehouseInfo = user.getWarehouseInfo();

		if (warehouseInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_WAREHOUSE_MAN);
		}

		Date nowTime = new Date();

		for (String bottleCode : bottleCodesStr) {

			if (!StringUtils.nonEmpty(bottleCode)) {
				continue;
			}

			bottleCode = AirBottleCodeInitUtils.initCode(bottleCode);

			AirBottleInfo airBottleInfo = airBottleInfoDaoImpl.findByAirBottleCode(bottleCode);

			if (airBottleInfo == null) {
				return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
			}

			// 清除所有库存信息
			AirBottleTrackingRecordUtils.clearInventoryInfo(airBottleInfo.getId(), warehouseInventoryInfoDaoImpl, storeInventoryInfoDaoImpl,
					deliveryManInventoryInfoDaoImpl, clientInventoryInfoDaoImpl, clientAirBottleRecordDaoImpl, nowTime);

			// 新增库存信息
			WarehouseInventoryInfo newWarehouseInventoryInfo = new WarehouseInventoryInfo(warehouseInfo, airBottleInfo, new AirBottleState(
					AirBottleStateCode.EMPTY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), user, nowTime);
			warehouseInventoryInfoDaoImpl.insert(newWarehouseInventoryInfo);

			// 气瓶追踪
			AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, airBottleInfo, null, null, warehouseInfo, null,
					AirBottleTrackingRecordCode.BOTTLE_STORAGE_IN_WAREHOUSE_FROM_INSPECTION, user);

		}

		return BaseDto.getSuccessResult(null);
	}

	public PreparedInspectionInfo findByAirBottleId(int airBottleId) {

		return preparedInspectionInfoDaoImpl.findByAirBottleId(airBottleId);
	}
}
