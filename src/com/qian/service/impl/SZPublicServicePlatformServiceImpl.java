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
import com.qian.service.SZPublicServicePlatformService;
import com.qian.util.SZPublicServiceUtil;
import com.qian.vo.SZBottleInfoDataVo;
import com.qian.vo.SZDeliveryDataVo;
import com.qian.vo.SZFillingDataVo;
import com.qian.vo.SZHandOverDataVo;
import com.qian.vo.SZRecycleDataVo;
import com.qian.vo.SZSupplyDataVo;
import com.qian.vo.Field;

@Service
public class SZPublicServicePlatformServiceImpl implements SZPublicServicePlatformService {

	@Autowired
	private AirBottleTrackingRecordDaoImpl airBottleTrackingRecordDaoImpl;
	
	@Autowired
	private AirBottleInfoDaoImpl airBottleInfoDaoImpl;
	
	static ResourceBundle resource = ResourceBundle.getBundle("properties/szpublicservice");
	public final static int MAX_UPLOAD_SIZE = Integer.parseInt(resource.getString("maxUploadSize"));
	public final static String NULL_VALUE_STR = resource.getString("nullValueString");

	@Override
	public List<SZFillingDataVo> getSZFillingDataList(long nowMillisecond) {
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

		return airBottleTrackingRecordDaoImpl.getSZFillingDataList(l, field);
	}
	
	@Override
	public List<SZSupplyDataVo> getSZSupplyDataList(long nowMillisecond) {
		List<String> l = new ArrayList<>();
		Field field = new Field();
		
		l.add("create_time > ?");
		// 30分钟前
		field.addDateTime(nowMillisecond - 30 * 60 * 1000);
		l.add("create_time <= ?");
		field.addDateTime(nowMillisecond);
		l.add("state.id = ?");
		field.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_STORAGE_IN_SOTRE);
		
		return airBottleTrackingRecordDaoImpl.getSZSupplyDataList(l, field);
	}
	
	@Override
	public List<SZDeliveryDataVo> getSZDeliveryDataList(long nowMillisecond) {
		List<String> l = new ArrayList<>();
		Field field = new Field();
		
		l.add("create_time > ?");
		// 30分钟前
		field.addDateTime(nowMillisecond - 30 * 60 * 1000);
		l.add("create_time <= ?");
		field.addDateTime(nowMillisecond);
		l.add("state.id = ?");
		field.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_OUT_SOTRE_TO_DELIVERY_MAN);
		
		return airBottleTrackingRecordDaoImpl.getSZDeliveryDataList(l, field);
	}
	
	@Override
	public List<SZHandOverDataVo> getSZHandOverDataList(long nowMillisecond) {
		List<String> l = new ArrayList<>();
		Field field = new Field();
		
	
		l.add("create_time > ?");
		// 30分钟前
		field.addDateTime(nowMillisecond - 30 * 60 * 1000);
		l.add("create_time <= ?");
		field.addDateTime(nowMillisecond);
		l.add("state.id = ?");
		field.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_OUT_IN_DELIVERY_MAN);

		return airBottleTrackingRecordDaoImpl.getSZHandOverDataList(l, field);
	}
	
	@Override
	public List<SZRecycleDataVo> getSZRecycleDataList(long nowMillisecond) {
		List<String> l = new ArrayList<>();
		Field field = new Field();
		
		
		l.add("create_time > ?");
		// 30分钟前
		field.addDateTime(nowMillisecond - 30 * 60 * 1000);
		l.add("create_time <= ?");
		field.addDateTime(nowMillisecond);
		l.add("state.id = ?");
		field.addInt(AirBottleTrackingRecordCode.EMPTY_BOTTLE_STORAGE_IN_DELIVERY_MAN);

		return airBottleTrackingRecordDaoImpl.getSZRecycleDataList(l, field);
	}
	
	@Override
	public void getSZBottleInfoDataList() {
		List<String> l = new ArrayList<>();
		Field field = new Field();
		
		l.add("publicServiceUpState = ?");
		field.addInt(AirBottleUpPublicServiceStateCode.WAIT_UP);
		l.add("air_bottle_code is not null");
		
		List<AirBottleInfo> list = airBottleInfoDaoImpl.getBottleInfoDataList(l, field,MAX_UPLOAD_SIZE);
//		List<SZBottleInfoDataVo> list = airBottleInfoDaoImpl.getSZBottleInfoDataList(l, field,MAX_UPLOAD_SIZE);
		
		List<SZBottleInfoDataVo> list2 = new LinkedList<SZBottleInfoDataVo>();
		for (AirBottleInfo info : list) {
			SZBottleInfoDataVo szBottleInfoDataVo = new SZBottleInfoDataVo(info);
			szBottleInfoDataVo.setTestUnit(NULL_VALUE_STR);
			list2.add(szBottleInfoDataVo);
		}
		
		boolean isSucc = false;
		
		if (list2.size()>0) {
			// 发送数据至远程接口
			isSucc = SZPublicServiceUtil.sendBottleInfoData(list2);
		}
		
		if (isSucc) {
			//更新数据为已经上传过的
			List<String> set_l = new ArrayList<String>();
			Field set_field = new Field();
			set_l.add("publicServiceUpState = ?");
			set_field.addInt(AirBottleUpPublicServiceStateCode.UPPED);
			
			List<String> where_l = new ArrayList<String>();
			Field where_field = null;
			for (SZBottleInfoDataVo bottleInfo : list2) {
				where_l.clear();
				where_field = new Field();
				where_l.add(" id = ?");
				where_field.addInt(bottleInfo.getId());
				airBottleInfoDaoImpl.update(set_l, set_field, where_l, where_field, "AirBottleInfo");
			}
		}
	}

}
