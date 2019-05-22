package com.qian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.code.AirBottleInventoryStateCode;
import com.qian.code.AirBottleStateCode;
import com.qian.code.AirBottleTrackingRecordCode;
import com.qian.code.AirBottleUpCloudStateCode;
import com.qian.dao.impl.AirBottleInfoDaoImpl;
import com.qian.dao.impl.AirBottleTrackingRecordDaoImpl;
import com.qian.dao.impl.WarehouseInventoryInfoDaoImpl;
import com.qian.entity.AirBottleBelong;
import com.qian.entity.AirBottleInfo;
import com.qian.entity.AirBottleInventoryState;
import com.qian.entity.AirBottleState;
import com.qian.entity.AirBottleType;
import com.qian.entity.DetectionUnit;
import com.qian.entity.ProductionUnit;
import com.qian.entity.User;
import com.qian.entity.WarehouseInfo;
import com.qian.entity.WarehouseInventoryInfo;
import com.qian.mobile.entity.MobileAirBottleCheckEntity;
import com.qian.mobile.entity.MobileAirBottleInfo;
import com.qian.service.AirBottleInfoService;
import com.qian.util.AES;
import com.qian.util.AirBottleCodeInitUtils;
import com.qian.util.AirBottleTrackingRecordUtils;
import com.qian.util.StringUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

@Service
@Transactional
public class AirBottleInfoServiceImpl implements AirBottleInfoService {
	
	@Autowired
	private AirBottleTrackingRecordDaoImpl airBottleTrackingRecordDaoImpl;

	@Autowired
	private AirBottleInfoDaoImpl airBottleInfoDaoImpl;

	@Autowired
	private WarehouseInventoryInfoDaoImpl warehouseInventoryInfoDaoImpl;
	

	public List<AirBottleInfo> getPageList(int page, int rows) {

		return airBottleInfoDaoImpl.getPageList(page, rows);
	}

	public long getTotalNum() {

		return airBottleInfoDaoImpl.getTotalNum();
	}

	public int add(AirBottleInfo airBottleInfo, User user) {

		airBottleInfoDaoImpl.insert(airBottleInfo);

		// 初始化气瓶信息

		WarehouseInfo warehouseInfo = user.getWarehouseInfo();

		Date nowTime = new Date();

		WarehouseInventoryInfo warehouseInventoryInfo = new WarehouseInventoryInfo();

		warehouseInventoryInfo.setWarehouseInfo(warehouseInfo);

		warehouseInventoryInfo.setAirBottleInfo(airBottleInfo);

		warehouseInventoryInfo.setAirBottleState(new AirBottleState(AirBottleStateCode.EMPTY_BOTTLE));

		warehouseInventoryInfo.setAirBottleInventoryState(new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE));

		warehouseInventoryInfo.setCreate_time(nowTime);

		warehouseInventoryInfo.setOperator(user);

		warehouseInventoryInfoDaoImpl.insert(warehouseInventoryInfo);

		// 气瓶追踪
		AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, airBottleInfo, null, null, warehouseInfo, null,
				AirBottleTrackingRecordCode.EMPTY_BOTTLE_INIT_IN_WAREHOUSE, user);

		return 0;
	}

	public CommonCode delByIds(String[] ids) {

		airBottleInfoDaoImpl.delByIds(ids);
		
		return CommonCode.OK;

	}

	public int edit(AirBottleInfo airBottleInfoTemp, int airBottleId) {

		AirBottleInfo airBottleInfo = airBottleInfoDaoImpl.findById(airBottleId);

		if(StringUtils.nonEmpty(airBottleInfoTemp.getAir_bottle_code())){
			airBottleInfo.setAir_bottle_code(airBottleInfoTemp.getAir_bottle_code());
		}else{
			airBottleInfo.setAir_bottle_code(null);
		}
		airBottleInfo.setAir_bottle_seal_code(airBottleInfoTemp.getAir_bottle_seal_code());
		airBottleInfo.setFactory_number(airBottleInfoTemp.getFactory_number());
		airBottleInfo.setAirBottleType(airBottleInfoTemp.getAirBottleType());
		airBottleInfo.setAirBottleBelong(airBottleInfoTemp.getAirBottleBelong());
		airBottleInfo.setUse_cycle(airBottleInfoTemp.getUse_cycle());
		airBottleInfo.setProduce_time(airBottleInfoTemp.getProduce_time());
		airBottleInfo.setCheck_time(airBottleInfoTemp.getCheck_time());
		airBottleInfo.setNext_check_time(airBottleInfoTemp.getNext_check_time());
		airBottleInfo.setProductionUnit(airBottleInfoTemp.getProductionUnit());
		airBottleInfo.setDetectionUnit(airBottleInfoTemp.getDetectionUnit());
		airBottleInfo.setVolume(airBottleInfoTemp.getVolume());
		airBottleInfo.setBottle_weight(airBottleInfoTemp.getBottle_weight());

		if (airBottleInfoTemp.getImg1() != null) {
			airBottleInfo.setImg1(airBottleInfoTemp.getImg1());
		}
		
		if (airBottleInfoTemp.getImg2() != null) {
			airBottleInfo.setImg1(airBottleInfoTemp.getImg2());
		}
		
		airBottleInfo.setUpState(AirBottleUpCloudStateCode.WAIT_UP);
		
		return airBottleInfoDaoImpl.update(airBottleInfo);
	}

	public AirBottleInfo findByAirBottleCode(String code) {

		return airBottleInfoDaoImpl.findByAirBottleCode(code);
	}

	public Long getTotalNum(List<String> l, Field filed) {
		return airBottleInfoDaoImpl.getTotalFromHQL(l, filed);
	}

	public List<AirBottleInfo> getPageList(List<String> l, Field filed, int page, int rows) {
		return airBottleInfoDaoImpl.getPageList(l, filed, page, rows);
	}

	public AirBottleInfo findById(int id) {
		return airBottleInfoDaoImpl.findById(id);
	}

	public int edit(AirBottleInfo airBottleInfo) {
		return airBottleInfoDaoImpl.update(airBottleInfo);
	}

	@SuppressWarnings("unchecked")
	public BaseDto<Object> addAirBottleInfo(MobileAirBottleInfo mobileAirBottleInfo, User user) {
		
		Date nowTime = new Date();

		AirBottleInfo check = airBottleInfoDaoImpl.findByAirBottleSealCode(mobileAirBottleInfo.getAir_bottle_seal_code());
		if(check != null){
			return BaseDto.getFailResult(CommonCode.ALREAY_EXIST_AIR_BOTTLE_SEAL_CODE);
		}
		
		AirBottleInfo airBottleInfo = new AirBottleInfo(mobileAirBottleInfo, user, nowTime);
		
		airBottleInfoDaoImpl.insert(airBottleInfo);
		
		// 初始化仓库库存
		WarehouseInventoryInfo warehouseInventoryInfo = new WarehouseInventoryInfo(user.getWarehouseInfo(), airBottleInfo, new AirBottleState(
				AirBottleStateCode.EMPTY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), user, nowTime);

		warehouseInventoryInfoDaoImpl.insert(warehouseInventoryInfo);
		
		// 气瓶追踪
		AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, airBottleInfo, null, null, user.getWarehouseInfo(), null,
				AirBottleTrackingRecordCode.EMPTY_BOTTLE_INIT_IN_WAREHOUSE, user);

		return BaseDto.getSuccessResult(null);
	}

	@Override
	public AirBottleInfo findByAirBottleCode(MobileAirBottleCheckEntity entity) {
		
		return airBottleInfoDaoImpl.findByAirBottleCode(AirBottleCodeInitUtils.initCode(entity.getBottleCode()));

	}

	@Override
	public BaseDto<CommonCode> cleanQRCodeInfo() {
		
		List<AirBottleInfo> airBottleInfos = airBottleInfoDaoImpl.getAllList();
		
		List<AirBottleInfo> updates = new ArrayList<AirBottleInfo>();
		
		for(AirBottleInfo airBottleInfo : airBottleInfos){
			
			String decode = AES.decrypt(airBottleInfo.getAir_bottle_code());
			
			if(decode != null){
				airBottleInfo.setAir_bottle_code(decode);
				updates.add(airBottleInfo);
			}
		}
		
		
		airBottleInfoDaoImpl.update(updates);
		
		return BaseDto.getSuccessResult(null);
	}

	@Override
	public CommonCode saveNewAirBottleQRCode(MobileAirBottleInfo mobileAirBottleInfo, User user) {
		
		Date nowTime = new Date();

		AirBottleInfo airBottleInfo = airBottleInfoDaoImpl.findById(mobileAirBottleInfo.getAir_bottle_id());

		airBottleInfo.setAir_bottle_code(AirBottleCodeInitUtils.initCode(mobileAirBottleInfo.getAir_bottle_code()));

		airBottleInfo.setUpState(AirBottleUpCloudStateCode.WAIT_UP);
		airBottleInfoDaoImpl.update(airBottleInfo);

		// 初始化仓库库存
		WarehouseInventoryInfo warehouseInventoryInfo = new WarehouseInventoryInfo(user.getWarehouseInfo(), airBottleInfo, new AirBottleState(
				AirBottleStateCode.EMPTY_BOTTLE), new AirBottleInventoryState(AirBottleInventoryStateCode.ALREADY_RECEIVE), user, nowTime);
		warehouseInventoryInfoDaoImpl.insert(warehouseInventoryInfo);

		// 气瓶追踪
		AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, airBottleInfo, null, null, user.getWarehouseInfo(), null,
				AirBottleTrackingRecordCode.EMPTY_BOTTLE_INIT_IN_WAREHOUSE, user);

		return CommonCode.OK;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> editAirBottleInfo(MobileAirBottleInfo mobileAirBottleInfo, User user) {

		AirBottleInfo checkAirBottleInfo = airBottleInfoDaoImpl.findByAirBottleSealCode(mobileAirBottleInfo.getAir_bottle_seal_code());
		if(checkAirBottleInfo != null && checkAirBottleInfo.getId() != mobileAirBottleInfo.getAir_bottle_id()){
			return BaseDto.getFailResult(CommonCode.ALREAY_EXIST_AIR_BOTTLE_SEAL_CODE);
		}
		
		AirBottleInfo airBottleInfo = airBottleInfoDaoImpl.findById(mobileAirBottleInfo.getAir_bottle_id());

		airBottleInfo.setAir_bottle_seal_code(mobileAirBottleInfo.getAir_bottle_seal_code());
		airBottleInfo.setFactory_number(mobileAirBottleInfo.getFactory_number());
		airBottleInfo.setUse_cycle(mobileAirBottleInfo.getUse_cycle());
		airBottleInfo.setAirBottleType(new AirBottleType(mobileAirBottleInfo.getAir_bottle_type_id()));
		airBottleInfo.setProductionUnit(new ProductionUnit(mobileAirBottleInfo.getProduction_unit_id()));
		airBottleInfo.setVolume(mobileAirBottleInfo.getVolume());
		airBottleInfo.setBottle_weight(mobileAirBottleInfo.getBottle_weight());
		if (mobileAirBottleInfo.getDetection_unit_id() != null || 0 == mobileAirBottleInfo.getDetection_unit_id()) {
			airBottleInfo.setDetectionUnit(new DetectionUnit(mobileAirBottleInfo.getDetection_unit_id()));
		}
		if (mobileAirBottleInfo.getAir_bottle_belong_id() != null || 0 == mobileAirBottleInfo.getAir_bottle_belong_id()) {
			airBottleInfo.setAirBottleBelong(new AirBottleBelong(mobileAirBottleInfo.getAir_bottle_belong_id()));
		}
		
		airBottleInfo.setProduce_time(new Date(mobileAirBottleInfo.getProduce_time_tmp()));

		if (0 != mobileAirBottleInfo.getCheck_time_tmp()) {
			airBottleInfo.setCheck_time(new Date(mobileAirBottleInfo.getCheck_time_tmp()));
		}
		
		if (mobileAirBottleInfo.getPhotos() != null && mobileAirBottleInfo.getPhotos().size() >= 2) {
			airBottleInfo.setImg1(mobileAirBottleInfo.getPhotos().get(0));
			airBottleInfo.setImg2(mobileAirBottleInfo.getPhotos().get(1));
		}
		
		airBottleInfo.setNext_check_time(new Date(mobileAirBottleInfo.getNext_check_time_tmp()));
		
		airBottleInfoDaoImpl.update(airBottleInfo);
		
		return BaseDto.getSuccessResult(null);
	}

}
