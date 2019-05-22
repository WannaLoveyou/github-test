package com.qian.util;

import java.util.Date;

import org.apache.log4j.Logger;

import com.qian.code.ClientAirBottleReocrdStateCode;
import com.qian.dao.impl.AirBottleInfoDaoImpl;
import com.qian.dao.impl.AirBottleTrackingRecordDaoImpl;
import com.qian.dao.impl.ClientAirBottleRecordDaoImpl;
import com.qian.dao.impl.ClientInventoryInfoDaoImpl;
import com.qian.dao.impl.DeliveryManInventoryInfoDaoImpl;
import com.qian.dao.impl.StoreInventoryInfoDaoImpl;
import com.qian.dao.impl.WarehouseInventoryInfoDaoImpl;
import com.qian.entity.AirBottleInfo;
import com.qian.entity.AirBottleTrackingRecord;
import com.qian.entity.AirBottleTrackingRecordState;
import com.qian.entity.ClientInfo;
import com.qian.entity.ClientInventoryInfo;
import com.qian.entity.DeliveryManInventoryInfo;
import com.qian.entity.SecondCategory;
import com.qian.entity.StoreInventoryInfo;
import com.qian.entity.User;
import com.qian.entity.WarehouseInfo;
import com.qian.entity.WarehouseInventoryInfo;
import com.qian.service.impl.ClientAirBottleRecord;
import com.qian.service.impl.ClientAirBottleRecordState;

/*
 * @author Lu Kongwen
 * @version Create time：2015-12-18 下午2:28:54
 * @Description
 */
public class AirBottleTrackingRecordUtils {

	private static Logger log = Logger.getLogger(AirBottleTrackingRecordUtils.class);

	
	public static void record(AirBottleTrackingRecordDaoImpl airBottleTrackingRecordDaoImpl, AirBottleInfoDaoImpl airBottleInfoDaoImpl,
			AirBottleInfo airBottleInfo, SecondCategory secondCategory, User deliveryMan, WarehouseInfo warehouseInfo, ClientInfo clientInfo, int stateId,
			User operator) {

		AirBottleTrackingRecord airBottleTrackingRecord = getAirBottleTrackingRecordState(airBottleInfo, stateId);
		
		airBottleTrackingRecord.setSecondCategory(secondCategory);
		airBottleTrackingRecord.setDeliveryMan(deliveryMan);
		airBottleTrackingRecord.setWarehouseInfo(warehouseInfo);
		airBottleTrackingRecord.setClientInfo(clientInfo);
		airBottleTrackingRecord.setOperator(operator);

		saveAirBottleTrackingRecord(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, airBottleInfo, airBottleTrackingRecord);

	}

	private static AirBottleTrackingRecord getAirBottleTrackingRecordState(AirBottleInfo airBottleInfo, int stateId) {

		AirBottleTrackingRecord airBottleTrackingRecord = new AirBottleTrackingRecord();

		AirBottleTrackingRecordState state = new AirBottleTrackingRecordState();
		state.setId(stateId);

		Date date = new Date();

		airBottleTrackingRecord.setAirBottleInfo(airBottleInfo);
		airBottleTrackingRecord.setState(state);
		airBottleTrackingRecord.setParent(airBottleInfo.getFinalAirBottleTrackingRecord());
		airBottleTrackingRecord.setCreate_time(date);
		airBottleTrackingRecord.setIs_final(1); // 1为true

		return airBottleTrackingRecord;
	}

	private static void saveAirBottleTrackingRecord(AirBottleTrackingRecordDaoImpl airBottleTrackingRecordDaoImpl, AirBottleInfoDaoImpl airBottleInfoDaoImpl,
			AirBottleInfo airBottleInfo, AirBottleTrackingRecord airBottleTrackingRecord) {

		airBottleTrackingRecordDaoImpl.insert(airBottleTrackingRecord);

		Integer finalTrackingRecordId = airBottleInfoDaoImpl.getAirBottleTrackingRecordId(airBottleInfo.getId());

		if (finalTrackingRecordId != null) {
			AirBottleTrackingRecord oldAirBottleTrackingRecord = airBottleTrackingRecordDaoImpl.findById(finalTrackingRecordId);
			oldAirBottleTrackingRecord.setIs_final(0); // 0为false
			airBottleTrackingRecordDaoImpl.update(oldAirBottleTrackingRecord);
		}

		airBottleInfo.setFinalAirBottleTrackingRecord(airBottleTrackingRecord);
		airBottleInfoDaoImpl.update(airBottleInfo);
	}
	

	public static void clearInventoryInfo(Integer airBottleId, WarehouseInventoryInfoDaoImpl warehouseInventoryInfoDaoImpl,StoreInventoryInfoDaoImpl storeInventoryInfoDaoImpl,
			DeliveryManInventoryInfoDaoImpl deliveryManInventoryInfoDaoImpl, ClientInventoryInfoDaoImpl clientInventoryInfoDaoImpl,
			ClientAirBottleRecordDaoImpl clientAirBottleRecordDaoImpl, Date nowTime) {

		// 判断是否在仓库
		WarehouseInventoryInfo warehouseInventoryInfo = warehouseInventoryInfoDaoImpl.findByAirBottleId(airBottleId);
		if (warehouseInventoryInfo != null) {
			warehouseInventoryInfoDaoImpl.delete(warehouseInventoryInfo);
		}

		// 判断是否在配送工身上
		DeliveryManInventoryInfo deliveryManInventoryInfo = deliveryManInventoryInfoDaoImpl.findByAirBottleId(airBottleId);
		if (deliveryManInventoryInfo != null) {
			deliveryManInventoryInfoDaoImpl.delete(deliveryManInventoryInfo);
		}

		// 判断是否在客户
		ClientInventoryInfo clientInventoryInfo = clientInventoryInfoDaoImpl.findByAirBottleId(airBottleId);
		if (clientInventoryInfo != null) {
			clientInventoryInfoDaoImpl.delete(clientInventoryInfo);
		}

		// 判断是否在门店
		StoreInventoryInfo storeInventoryInfo = storeInventoryInfoDaoImpl.findByAirBottleId(airBottleId);
		if (storeInventoryInfo != null) {
			storeInventoryInfoDaoImpl.delete(storeInventoryInfo);
		}

		// 更新用户气瓶记录
		ClientAirBottleRecord clientAirBottleRecord = clientAirBottleRecordDaoImpl.getNoReturnClientAirBottleRecordByAirBottleId(airBottleId);
		if (clientAirBottleRecord != null) {
			clientAirBottleRecord.setEnd_time(nowTime);
			clientAirBottleRecord.setState(new ClientAirBottleRecordState(ClientAirBottleReocrdStateCode.FORCE_RETURN));
			clientAirBottleRecordDaoImpl.update(clientAirBottleRecord);
		}
	}
}
