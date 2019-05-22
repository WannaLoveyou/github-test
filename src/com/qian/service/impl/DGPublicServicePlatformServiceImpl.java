package com.qian.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qian.code.AirBottleTrackingRecordCode;
import com.qian.code.AirBottleUpPublicServiceStateCode;
import com.qian.dao.impl.AirBottleInfoDaoImpl;
import com.qian.dao.impl.AirBottleTrackingRecordDaoImpl;
import com.qian.entity.AirBottleInfo;
import com.qian.service.DGPublicServicePlatformService;
import com.qian.util.DGPublicServiceUtil;
import com.qian.vo.DGBottleInfoDataVo;
import com.qian.vo.DGDeliveryDataVo;
import com.qian.vo.DGFillingDataVo;
import com.qian.vo.DGHandOverDataVo;
import com.qian.vo.DGRecycleDataVo;
import com.qian.vo.DGSupplyDataVo;
import com.qian.vo.Field;

@Service
public class DGPublicServicePlatformServiceImpl implements DGPublicServicePlatformService {

	@Autowired
	private AirBottleTrackingRecordDaoImpl airBottleTrackingRecordDaoImpl;
	
	@Autowired
	private AirBottleInfoDaoImpl airBottleInfoDaoImpl;
	
	static ResourceBundle resource = ResourceBundle.getBundle("properties/dgpublicservice");
	public final static int MAX_UPLOAD_SIZE = Integer.parseInt(resource.getString("maxUploadSize"));

	@Override
	public List<DGFillingDataVo> getDGFillingDataList(long nowMillisecond) {
		List<String> l = new ArrayList<>();
		Field field = new Field();


		l.add("create_time > ?");
		// 30分钟前
		field.addDateTime(nowMillisecond - 30 * 60 * 1000);
		l.add("create_time <= ?");
		field.addDateTime(nowMillisecond);
		l.add("(state.id = ? or state.id = ? or state.id = ?)");
		field.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_STORAGE_IN_WAREHOUSE);
		field.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_FILLING_IN_WAREHOUSE);
		field.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_FORCE_FILLING_IN_WAREHOUSE);

		return airBottleTrackingRecordDaoImpl.getDGFillingDataList(l, field);
	}
	
	@Override
	public List<DGSupplyDataVo> getDGSupplyDataList(long nowMillisecond) {
		List<String> l = new ArrayList<>();
		Field field = new Field();
		
		l.add("create_time > ?");
		// 30分钟前
		field.addDateTime(nowMillisecond - 30 * 60 * 1000);
		l.add("create_time <= ?");
		field.addDateTime(nowMillisecond);
		l.add("state.id = ?");
		field.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_STORAGE_IN_SOTRE);
		
		return airBottleTrackingRecordDaoImpl.getDGSupplyDataList(l, field);
	}
	
	@Override
	public List<DGDeliveryDataVo> getDGDeliveryDataList(long nowMillisecond) {
		List<String> l = new ArrayList<>();
		Field field = new Field();
		
		l.add("create_time > ?");
		// 30分钟前
		field.addDateTime(nowMillisecond - 30 * 60 * 1000);
		l.add("create_time <= ?");
		field.addDateTime(nowMillisecond);
		l.add("state.id = ?");
		field.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_OUT_SOTRE_TO_DELIVERY_MAN);
		
		return airBottleTrackingRecordDaoImpl.getDGDeliveryDataList(l, field);
	}
	
	@Override
	public List<DGHandOverDataVo> getDGHandOverDataList(long nowMillisecond) {
		List<String> l = new ArrayList<>();
		Field field = new Field();
		
	
		l.add("create_time > ?");
		// 30分钟前
		field.addDateTime(nowMillisecond - 30 * 60 * 1000);
		l.add("create_time <= ?");
		field.addDateTime(nowMillisecond);
		l.add("state.id = ?");
		field.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_OUT_IN_DELIVERY_MAN);

		return airBottleTrackingRecordDaoImpl.getDGHandOverDataList(l, field);
	}
	
	@Override
	public List<DGRecycleDataVo> getDGRecycleDataList(long nowMillisecond) {
		List<String> l = new ArrayList<>();
		Field field = new Field();
		
		
		l.add("create_time > ?");
		// 30分钟前
		field.addDateTime(nowMillisecond - 30 * 60 * 1000);
		l.add("create_time <= ?");
		field.addDateTime(nowMillisecond);
		l.add("state.id = ?");
		field.addInt(AirBottleTrackingRecordCode.EMPTY_BOTTLE_STORAGE_IN_DELIVERY_MAN);

		return airBottleTrackingRecordDaoImpl.getDGRecycleDataList(l, field);
	}
	
	@Override
	public void getDGBottleInfoDataList() {
		List<String> l = new ArrayList<>();
		Field field = new Field();
		
		l.add("publicServiceUpState = ?");
		field.addInt(AirBottleUpPublicServiceStateCode.WAIT_UP);
		l.add("air_bottle_code is not null");
		
		List<AirBottleInfo> list = airBottleInfoDaoImpl.getBottleInfoDataList(l, field,MAX_UPLOAD_SIZE);
//		List<DGBottleInfoDataVo> list = airBottleInfoDaoImpl.getDGBottleInfoDataList(l, field,MAX_UPLOAD_SIZE);
		
		List<DGBottleInfoDataVo> list2 = new LinkedList<DGBottleInfoDataVo>();
		for (AirBottleInfo airBottleInfo : list) {
			DGBottleInfoDataVo dgBottleInfoDataVo = new DGBottleInfoDataVo(airBottleInfo);
			list2.add(dgBottleInfoDataVo);
		}
		
		boolean isSucc = false;
		
		if (list2.size()>0) {
			// 发送数据至远程接口
			isSucc = DGPublicServiceUtil.sendBottleInfoData(list2);
		}
		
		if (isSucc) {
			//更新数据为已经上传过的
			List<String> set_l = new ArrayList<String>();
			Field set_field = new Field();
			set_l.add("publicServiceUpState = ?");
			set_field.addInt(AirBottleUpPublicServiceStateCode.UPPED);
			
			List<String> where_l = new ArrayList<String>();
			Field where_field = null;
			for (DGBottleInfoDataVo bottleInfo : list2) {
				where_l.clear();
				where_field = new Field();
				where_l.add(" id = ?");
				where_field.addInt(bottleInfo.getId());
				airBottleInfoDaoImpl.update(set_l, set_field, where_l, where_field, "AirBottleInfo");
			}
		}
	}

}
