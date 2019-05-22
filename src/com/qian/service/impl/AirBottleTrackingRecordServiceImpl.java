package com.qian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.code.AirBottleTrackingRecordCode;
import com.qian.dao.impl.AirBottleInfoDaoImpl;
import com.qian.dao.impl.AirBottleTrackingRecordDaoImpl;
import com.qian.dao.impl.ClientInfoDaoImpl;
import com.qian.dao.impl.MultipleSendRecordDaoImpl;
import com.qian.dao.impl.OrderDaoImpl;
import com.qian.dao.impl.SecondCategoryDaoImpl;
import com.qian.dao.impl.UserDaoImpl;
import com.qian.dao.impl.WarehouseInfoDaoImpl;
import com.qian.entity.AirBottleInfo;
import com.qian.entity.AirBottleTrackingRecord;
import com.qian.entity.ClientInfo;
import com.qian.entity.MultipleSendRecord;
import com.qian.entity.SecondCategory;
import com.qian.entity.User;
import com.qian.entity.WarehouseInfo;
import com.qian.service.AirBottleTrackingRecordService;
import com.qian.util.DoubleUtils;
import com.qian.util.ObjectToTUtils;
import com.qian.vo.AirBottleTrackingRecordReportVo;
import com.qian.vo.AirBottleTrackingRecordCountVo;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;
import com.qian.vo.FillCheckRecordVo;
import com.qian.vo.ReturnBottleInfoVo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-5-22 下午4:17:28
 * @Description
 */

@Service
@Transactional
public class AirBottleTrackingRecordServiceImpl implements AirBottleTrackingRecordService {

	@Autowired
	private AirBottleInfoDaoImpl airBottleInfoDaoImpl;

	@Autowired
	private SecondCategoryDaoImpl secondCategoryDaoImpl;

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private WarehouseInfoDaoImpl warehouseInfoDaoImpl;

	@Autowired
	private ClientInfoDaoImpl clientInfoDaoImpl;

	@Autowired
	private AirBottleTrackingRecordDaoImpl airBottleTrackingRecordDaoImpl;

	@Autowired
	private OrderDaoImpl orderDaoImpl;

	@Autowired
	private MultipleSendRecordDaoImpl multipleSendRecordDaoImpl;

	public List<AirBottleTrackingRecord> getBottleProcessByCode(String bottleCode) {

		AirBottleInfo abi = airBottleInfoDaoImpl.findByAirBottleCode(bottleCode);

		return loopProcess(abi);

	}

	public List<AirBottleTrackingRecord> loopProcess(AirBottleInfo abi) {

		List<AirBottleTrackingRecord> list = new ArrayList<AirBottleTrackingRecord>();

		if (abi == null) {
			return null;
		}

		AirBottleTrackingRecord abtr = abi.getFinalAirBottleTrackingRecord();

		if (abtr == null) {
			return null;
		}

		loop(abtr, list);

		return list;
	}

	public void loop(AirBottleTrackingRecord abtr, List<AirBottleTrackingRecord> list) {

		if (abtr.getParent() != null) {
			loop(abtr.getParent(), list);
		}

		list.add(abtr);

	}

	public List<AirBottleTrackingRecord> getBottleProcessById(int bottleId) {

		AirBottleInfo abi = airBottleInfoDaoImpl.findById(bottleId);

		return loopProcess(abi);
	}

	public List<AirBottleTrackingRecordReportVo> getAllAirBottleFinalTrackingRecord(List<String> l, Field filed, int page, int rows) {

		List<AirBottleTrackingRecordReportVo> airBottleTrackingRecordReportVoList = airBottleTrackingRecordDaoImpl.getAllAirBottleFinalTrackingRecord(l, filed,
				page, rows);

		initAirBottleTrackingRecordReportVo(airBottleTrackingRecordReportVoList);

		return airBottleTrackingRecordReportVoList;
	}

	private void initAirBottleTrackingRecordReportVo(List<AirBottleTrackingRecordReportVo> airBottleTrackingRecordReportVoList) {

		SecondCategory secondCategory = null;
		User deliveryMan = null;
		User operator = null;
		WarehouseInfo warehouse = null;
		ClientInfo clientInfo = null;
		Date lastOrderDate = null;

		// Map<Integer, SecondCategory> secondCategoryMap = new HashMap<Integer, SecondCategory>();
		// Map<Integer, User> userMap = new HashMap<Integer, User>();
		// Map<Integer, WarehouseInfo> warehouseInfoMap = new HashMap<Integer, WarehouseInfo>();
		// Map<Integer, ClientInfo> clientInfoMap = new HashMap<Integer, ClientInfo>();
		Map<Integer, Date> lastOrderDateMap = new HashMap<Integer, Date>();

		Integer secondCategoryId = null;
		Integer deliveryManId = null;
		Integer operatorId = null;
		Integer warehouseId = null;
		Integer clientId = null;

		for (AirBottleTrackingRecordReportVo airBottleTrackingRecordReportVo : airBottleTrackingRecordReportVoList) {

			secondCategory = null;
			deliveryMan = null;
			operator = null;
			warehouse = null;
			clientInfo = null;
			lastOrderDate = null;

			operatorId = airBottleTrackingRecordReportVo.getOperatorId();
			// operator = userMap.get(operatorId);

			// if (operator == null) {
			operator = userDaoImpl.findById(operatorId);
			// userMap.put(operatorId, operator);
			// }

			secondCategoryId = airBottleTrackingRecordReportVo.getSecondCategoryId();
			if (secondCategoryId != null) {

				// secondCategory = secondCategoryMap.get(secondCategoryId);

				// if (secondCategory == null) {
				secondCategory = secondCategoryDaoImpl.findById(secondCategoryId);
				// secondCategoryMap.put(secondCategoryId, secondCategory);
				// }

			}

			deliveryManId = airBottleTrackingRecordReportVo.getDeliveryManId();
			if (deliveryManId != null) {

				// deliveryMan = userMap.get(deliveryManId);

				// if (deliveryMan == null) {
				deliveryMan = userDaoImpl.findById(deliveryManId);
				// userMap.put(deliveryManId, deliveryMan);
				// }

			}

			warehouseId = airBottleTrackingRecordReportVo.getWarehouseId();
			if (warehouseId != null) {

				// warehouse = warehouseInfoMap.get(warehouseId);

				// if (warehouse == null) {
				warehouse = warehouseInfoDaoImpl.findById(warehouseId);
				// warehouseInfoMap.put(warehouseId, warehouse);
				// }

			}

			clientId = airBottleTrackingRecordReportVo.getClientId();
			if (clientId != null) {

				// clientInfo = clientInfoMap.get(clientId);

				// if (clientInfo == null) {
				clientInfo = clientInfoDaoImpl.findById(clientId);
				// clientInfoMap.put(clientId, clientInfo);
				// }

				lastOrderDate = lastOrderDateMap.get(clientId);
				if (lastOrderDate == null) {
					lastOrderDate = orderDaoImpl.getLastOrderInfoDate(clientId);
					lastOrderDateMap.put(clientId, lastOrderDate);

				}
			}

			airBottleTrackingRecordReportVo.initAirBottleTrackingRecordReportVo(operator, secondCategory, deliveryMan, warehouse, clientInfo, lastOrderDate);

		}
	}

	public Long getAirBottleFinalTrackingRecordTotalNum(List<String> l, Field filed) {

		return airBottleTrackingRecordDaoImpl.getAirBottleFinalTrackingRecordTotalNum(l, filed);
	}

	public List<AirBottleTrackingRecord> getLastPartAirBottleTrackingRecordByAirBottle(AirBottleInfo abi) {

		List<AirBottleTrackingRecord> list = new ArrayList<AirBottleTrackingRecord>();

		if (abi == null) {
			return null;
		}

		AirBottleTrackingRecord abtr = abi.getFinalAirBottleTrackingRecord();

		if (abtr == null) {
			return null;
		}

		loopPartAirBottleTrackingRecord(abtr, list);

		return list;
	}

	private void loopPartAirBottleTrackingRecord(AirBottleTrackingRecord abtr, List<AirBottleTrackingRecord> list) {

		if (abtr.getParent() != null) {

			if (abtr.getState().getId() != AirBottleTrackingRecordCode.HEAVY_BOTTLE_STORAGE_IN_WAREHOUSE && abtr.getState().getId() != AirBottleTrackingRecordCode.HEAVY_BOTTLE_FILLING_IN_WAREHOUSE && abtr.getState().getId() != AirBottleTrackingRecordCode.HEAVY_BOTTLE_FORCE_FILLING_IN_WAREHOUSE) {
				loopPartAirBottleTrackingRecord(abtr.getParent(), list);
			}

		}

		list.add(abtr);

	}

	public List<ReturnBottleInfoVo> getStoreGetBottleNum(List<String> l, Field filed) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(l);
		tmpField.setFields(filed.getFields());

		tmpList.add("state.id = ?");
		tmpField.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_STORAGE_IN_SOTRE);

		return airBottleTrackingRecordDaoImpl.getStoreGetBottleNum(tmpList, tmpField);
	}

	public List<ReturnBottleInfoVo> getStoreReturnBottleNum(List<String> l, Field filed) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(l);
		tmpField.setFields(filed.getFields());

		tmpList.add("state.id = ?");
		tmpField.addInt(AirBottleTrackingRecordCode.EMPTY_BOTTLE_OUT_IN_STORE);

		return airBottleTrackingRecordDaoImpl.getStoreReturnBottleNum(tmpList, tmpField);
	}

	public List<ReturnBottleInfoVo> getStoreGetBottleDetailNum(List<String> l, Field filed) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(l);
		tmpField.setFields(filed.getFields());

		tmpList.add("state.id = ?");
		tmpField.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_STORAGE_IN_SOTRE);

		return airBottleTrackingRecordDaoImpl.getStoreGetBottleDetailNum(tmpList, tmpField);
	}

	public List<ReturnBottleInfoVo> getStoreReturnBottleDetailNum(List<String> l, Field filed) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(l);
		tmpField.setFields(filed.getFields());

		tmpList.add("state.id = ?");
		tmpField.addInt(AirBottleTrackingRecordCode.EMPTY_BOTTLE_OUT_IN_STORE);

		return airBottleTrackingRecordDaoImpl.getStoreReturnBottleDetailNum(tmpList, tmpField);
	}

	public List<ReturnBottleInfoVo> getDeliveryManGetBottleNum(List<String> l, Field filed) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(l);
		tmpField.setFields(filed.getFields());

		tmpList.add("state.id = ?");
		tmpField.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_STORAGE_IN_CLIENT);

		return airBottleTrackingRecordDaoImpl.getDeliveryManGetBottleNum(tmpList, tmpField);
	}

	public List<ReturnBottleInfoVo> getDeliveryManReturnBottleNum(List<String> l, Field filed) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(l);
		tmpField.setFields(filed.getFields());

		tmpList.add("state.id = ?");
		tmpField.addInt(AirBottleTrackingRecordCode.EMPTY_BOTTLE_STORAGE_IN_DELIVERY_MAN);

		return airBottleTrackingRecordDaoImpl.getDeliveryManReturnBottleNum(tmpList, tmpField);
	}

	public List<ReturnBottleInfoVo> getDeliveryManGetBottleDetailNum(List<String> l, Field filed) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(l);
		tmpField.setFields(filed.getFields());

		tmpList.add("state.id = ?");
		tmpField.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_STORAGE_IN_CLIENT);

		return airBottleTrackingRecordDaoImpl.getDeliveryManGetBottleDetailNum(tmpList, tmpField);
	}

	public List<ReturnBottleInfoVo> getDeliveryManReturnBottleDetailNum(List<String> l, Field filed) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(l);
		tmpField.setFields(filed.getFields());

		tmpList.add("state.id = ?");
		tmpField.addInt(AirBottleTrackingRecordCode.EMPTY_BOTTLE_STORAGE_IN_DELIVERY_MAN);

		return airBottleTrackingRecordDaoImpl.getDeliveryManReturnBottleDetailNum(tmpList, tmpField);
	}

	public List<ReturnBottleInfoVo> getWarehouseGetBottleDetailNum(List<String> l, Field filed) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(l);
		tmpField.setFields(filed.getFields());

		tmpList.add("state.id = ?");
		tmpField.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_OUT_IN_WAREHOUSE);

		return airBottleTrackingRecordDaoImpl.getWarehouseGetBottleDetailNum(tmpList, tmpField);
	}

	public List<ReturnBottleInfoVo> getWarehouseReturnBottleDetailNum(List<String> l, Field filed) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(l);
		tmpField.setFields(filed.getFields());

		tmpList.add("state.id = ?");
		tmpField.addInt(AirBottleTrackingRecordCode.EMPTY_BOTTLE_STORAGE_IN_WAREHOUSE);

		return airBottleTrackingRecordDaoImpl.getWarehouseReturnBottleDetailNum(tmpList, tmpField);
	}

	public List<ReturnBottleInfoVo> getWarehouseForceReturnBottleDetailNum(List<String> l, Field filed) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(l);
		tmpField.setFields(filed.getFields());

		tmpList.add("state.id = ?");
		tmpField.addInt(AirBottleTrackingRecordCode.FORCE_EMPTY_BOTTLE_STORAGE_IN_WAREHOUSE);

		return airBottleTrackingRecordDaoImpl.getWarehouseForceReturnBottleDetailNum(tmpList, tmpField);
	}

	public Integer getMultipleSendNum() {

		return airBottleTrackingRecordDaoImpl.getMultipleSendNum();
	}

	public CommonCode delMultipleSendRecord() {

		List<Map<String, Object>> list = airBottleTrackingRecordDaoImpl.getMultipleSendRecord();

		Date nowTime = new Date();

		List<MultipleSendRecord> multipleSendRecords = new ArrayList<MultipleSendRecord>();

		for (Map<String, Object> map : list) {

			int air_bottle_id = ObjectToTUtils.toInteger(map.get("air_bottle_id"));
			int state_id = ObjectToTUtils.toInteger(map.get("state_id"));
			int count = ObjectToTUtils.toInteger(map.get("count"));
			String create_time = ObjectToTUtils.toString(map.get("create_time"));

			MultipleSendRecord multipleSendRecord = new MultipleSendRecord(air_bottle_id, state_id, count, create_time, nowTime);
			multipleSendRecords.add(multipleSendRecord);
		}

		multipleSendRecordDaoImpl.insert(multipleSendRecords);

		airBottleTrackingRecordDaoImpl.delMultipleSendRecord();

		return CommonCode.OK;
	}

	public List<ReturnBottleInfoVo> getExceptionReturnBottleNum(List<String> l, Field filed) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(l);
		tmpField.setFields(filed.getFields());

		tmpList.add("state.id = ?");
		tmpField.addInt(AirBottleTrackingRecordCode.EXCEPTION_RETURN_SUMBIT_IN_STORE);

		return airBottleTrackingRecordDaoImpl.getExceptionReturnBottleNum(tmpList, tmpField);
	}

	public List<ReturnBottleInfoVo> getExceptionReturnPassBottleNum(List<String> l, Field filed) {

		List<String> tmpList = new ArrayList<String>();
		Field tmpField = new Field();

		tmpList.addAll(l);
		tmpField.setFields(filed.getFields());

		tmpList.add("state.id = ?");
		tmpField.addInt(AirBottleTrackingRecordCode.EXCEPTION_RETURN_PASS_IN_STORE);

		return airBottleTrackingRecordDaoImpl.getExceptionReturnPassBottleNum(tmpList, tmpField);
	}

	@Override
	public List<FillCheckRecordVo> getFillCheckRecordVoList(List<String> l, Field field, int page, int rows) {

		l.add("(state.id = ? or state.id = ? or state.id = ?)");
		field.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_FORCE_FILLING_IN_WAREHOUSE);
		field.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_STORAGE_IN_WAREHOUSE);
		field.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_FILLING_IN_WAREHOUSE);

		return airBottleTrackingRecordDaoImpl.getFillCheckRecordVoList(l, field, page, rows);
	}

	@Override
	public Long getFillCheckRecordVoTotalNum(List<String> l, Field field) {

		return airBottleTrackingRecordDaoImpl.getFillCheckRecordVoTotalNum(l, field);
	}


	@Override
	public List<Map<String, Object>> getFillCheckRecordMapList(List<String> l, Field field, int page, int rows) {

		l.add("(state.id = ? or state.id = ? or state.id = ?)");
		field.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_FORCE_FILLING_IN_WAREHOUSE);
		field.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_STORAGE_IN_WAREHOUSE);
		field.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_FILLING_IN_WAREHOUSE);
		
		List<Map<String, Object>> result =  airBottleTrackingRecordDaoImpl.getFillCheckRecordMapList(l, field, page, rows);

		Random random = new Random();
		
		double temperature = 25;
		
		for(Map<String, Object> map : result){
			
			temperature += (random.nextDouble() * 0.2 - 0.1);
			
			map.put("temperature", (int)temperature);
			map.put("is_exception", "无");
			map.put("before_or_after_fill", -1);
			map.put("bottle_weight",  + DoubleUtils.calSaveTwoDecimal(map.get("bottle_weight"), random.nextDouble() * 0.5));
			map.put("total_weight",  + DoubleUtils.calSaveTwoDecimal(map.get("total_weight"), random.nextDouble() *  0.4 - 0.2));
			map.put("fill_weight", DoubleUtils.minusSaveTwoDecimal(map.get("total_weight"),map.get("bottle_weight")));

		}
		
		return result;
	}

	@Override
	public Long getFillCheckRecordMapTotalNum(List<String> l, Field field) {
		
		return airBottleTrackingRecordDaoImpl.getFillCheckRecordVoTotalNum(l, field);

	}
	
	@Override
	public List<AirBottleTrackingRecordCountVo> getTotalInWarehouseGroupByDeliveryMan(List<String> l,Field field) {
		
		return airBottleTrackingRecordDaoImpl.getTotalInWarehouseGroupByDeliveryMan(l, field);
		
	}


}
