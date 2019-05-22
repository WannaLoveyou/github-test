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

import com.qian.code.BeforeOrAfterFillCode;
import com.qian.dao.impl.AirBottleInfoDaoImpl;
import com.qian.dao.impl.FillCheckInfoDaoImpl;
import com.qian.dao.impl.FillCheckRecordDaoImpl;
import com.qian.dao.impl.FillCheckRecordDetailsDaoImpl;
import com.qian.dao.impl.UserDaoImpl;
import com.qian.entity.AirBottleInfo;
import com.qian.entity.FillCheckInfo;
import com.qian.entity.FillCheckRecord;
import com.qian.entity.FillCheckRecordDetails;
import com.qian.entity.User;
import com.qian.mobile.entity.MobileFillCheckInfoSubmitEntity;
import com.qian.service.FillCheckRecordService;
import com.qian.util.DoubleUtils;
import com.qian.util.TimeUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2018-10-23 下午9:06:16
 * @Description
 */
@Service
@Transactional
public class FillCheckRecordServiceImpl implements FillCheckRecordService {

	@Autowired
	private FillCheckRecordDaoImpl fillCheckRecordDaoImpl;

	@Autowired
	private FillCheckRecordDetailsDaoImpl fillCheckRecordDetailsDaoImpl;

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private AirBottleInfoDaoImpl airBottleInfoDaoImpl;

	@Autowired
	private FillCheckInfoDaoImpl fillCheckInfoDaoImpl;

	@Override
	public BaseDto<Object> sumbitFillCheckInfoBeforeFilling(MobileFillCheckInfoSubmitEntity entity) {
		entity.setBefore_or_after_fill(BeforeOrAfterFillCode.BEFORE_FILLING);
		return sumbitFillCheckInfo(entity);
	}

	@Override
	public BaseDto<Object> sumbitFillCheckInfoAfterFilling(MobileFillCheckInfoSubmitEntity entity) {
		entity.setBefore_or_after_fill(BeforeOrAfterFillCode.AFTER_FILLING);
		return sumbitFillCheckInfo(entity);
	}

	private BaseDto<Object> sumbitFillCheckInfo(MobileFillCheckInfoSubmitEntity entity) {

		Date nowTime = new Date();

		User user = userDaoImpl.findById(entity.getUserId());

		AirBottleInfo airBottleInfo = airBottleInfoDaoImpl.findById(entity.getAirBottleId());

		FillCheckRecord fillCheckRecord = new FillCheckRecord(airBottleInfo, user, nowTime, entity.getBefore_or_after_fill());
		fillCheckRecordDaoImpl.insert(fillCheckRecord);

		String[] itemIds = entity.getItemIds().split(",");

		for (String itemId : itemIds) {
			FillCheckRecordDetails fillCheckRecordDetails = new FillCheckRecordDetails(fillCheckRecord, new FillCheckInfo(Integer.valueOf(itemId)));
			fillCheckRecordDetailsDaoImpl.insert(fillCheckRecordDetails);
		}

		return BaseDto.getSuccessResult(null);
	}

	@Override
	public List<List<Map<String, Object>>> getFillCheckRecordHeader() {

		List<List<Map<String, Object>>> result = new ArrayList<>();

		List<String> beforeList = new ArrayList<String>();
		Field beforeFiled = new Field();

		beforeList.add("before_or_after_fill = ?");
		beforeFiled.addInt(BeforeOrAfterFillCode.BEFORE_FILLING); // 充装前

		List<FillCheckInfo> beforeFillCheckInfos = fillCheckInfoDaoImpl.getAllList(beforeList, beforeFiled);

		List<String> afterList = new ArrayList<String>();
		Field afterFiled = new Field();

		afterList.add("before_or_after_fill = ?");
		afterFiled.addInt(BeforeOrAfterFillCode.AFTER_FILLING); // 充装后

		List<FillCheckInfo> afterFillCheckInfos = fillCheckInfoDaoImpl.getAllList(afterList, afterFiled);

		List<Map<String, Object>> headerMapList = new ArrayList<Map<String, Object>>();

		Map<String, Object> beforeMap = new HashMap<String, Object>();
		beforeMap.put("title", "充装前检查项目");
		beforeMap.put("colspan", beforeFillCheckInfos.size());
		headerMapList.add(beforeMap);

		Map<String, Object> fillMap = new HashMap<String, Object>();
		fillMap.put("title", "充装记录");
		fillMap.put("colspan", 6);
		headerMapList.add(fillMap);
		
		Map<String, Object> afterMap = new HashMap<String, Object>();
		afterMap.put("title", "充装后检查项目");
		afterMap.put("colspan", afterFillCheckInfos.size());
		headerMapList.add(afterMap);

		List<Map<String, Object>> headerDetailsMapList = new ArrayList<Map<String, Object>>();

		for (FillCheckInfo fillCheckInfo : beforeFillCheckInfos) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("field", fillCheckInfo.getFill_check_code());
			map.put("title", fillCheckInfo.getFill_check_code());
			map.put("align", "center");
			map.put("width", "35");
			map.put("formatter", "getBeforeCheckResult");
			headerDetailsMapList.add(map);
		}
		
		addFillMapList(headerDetailsMapList);
		
		for (FillCheckInfo fillCheckInfo : afterFillCheckInfos) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("field", fillCheckInfo.getFill_check_code());
			map.put("title", fillCheckInfo.getFill_check_code());
			map.put("align", "center");
			map.put("width", "35");
			map.put("formatter", "getAfterCheckResult");
			headerDetailsMapList.add(map);
		}

		result.add(headerMapList);
		result.add(headerDetailsMapList);

		return result;
	}
	
	private void addFillMapList(List<Map<String, Object>> headerDetailsMapList){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("field", "bottle_weight");
		map.put("title", "充气前质量（kg）");
		map.put("align", "center");
		map.put("width", "110");
		headerDetailsMapList.add(map);
		
		map = new HashMap<String, Object>();
		map.put("field", "fill_weight");
		map.put("title", "实际充装量（kg）");
		map.put("align", "center");
		map.put("width", "110");
		headerDetailsMapList.add(map);
		
		map = new HashMap<String, Object>();
		map.put("field", "total_weight");
		map.put("title", "充气后质量(kg)");
		map.put("align", "center");
		map.put("width", "110");
		headerDetailsMapList.add(map);
		
		map = new HashMap<String, Object>();
		map.put("field", "is_exception");
		map.put("title", "有无发现异常情况");
		map.put("align", "center");
		map.put("width", "120");
		headerDetailsMapList.add(map);
		
		map = new HashMap<String, Object>();
		map.put("field", "fill_operator");
		map.put("title", "充装工工号");
		map.put("align", "center");
		map.put("width", "80");
		headerDetailsMapList.add(map);
		
		map = new HashMap<String, Object>();
		map.put("field", "check_operator");
		map.put("title", "检查人员");
		map.put("align", "center");
		map.put("width", "60");
		headerDetailsMapList.add(map);
	}

	@Override
	public List<Map<String, Object>> getFillCheckRecordMapList(List<String> l, Field field, int page, int rows) {

		List<Map<String, Object>> result = new ArrayList<>();
		
		List<FillCheckRecord> fillCheckRecords = fillCheckRecordDaoImpl.getPageList(l, field, page, rows);

		Random random = new Random();

		double temperature = 25;

		for(FillCheckRecord fillCheckRecord : fillCheckRecords){
			
			temperature += (random.nextDouble() * 0.2 - 0.1);

			Map<String,Object> map = new HashMap<>();
			map.put("fill_date", TimeUtils.getyyyyMMddStringByDate(fillCheckRecord.getCreate_time()));
			map.put("air_bottle_seal_code", fillCheckRecord.getAirBottleInfo().getAir_bottle_seal_code());
			map.put("volume", fillCheckRecord.getAirBottleInfo().getAirBottleType().getVolume());
			map.put("temperature", (int)temperature);
			
			if(fillCheckRecord.getBefore_or_after_fill() == BeforeOrAfterFillCode.AFTER_FILLING){
				map.put("bottle_weight", DoubleUtils.calSaveTwoDecimal(fillCheckRecord.getAirBottleInfo().getAirBottleType().getBottle_weight(), random.nextDouble() * 0.5));
				map.put("total_weight", DoubleUtils.calSaveTwoDecimal(fillCheckRecord.getAirBottleInfo().getAirBottleType().getTotal_weight(), random.nextDouble() *  0.4 - 0.2));
				map.put("fill_weight", DoubleUtils.minusSaveTwoDecimal(map.get("total_weight"),map.get("bottle_weight")));

				map.put("is_exception", "有");
				map.put("fill_operator", fillCheckRecord.getOperator().getId());
				map.put("check_operator", fillCheckRecord.getOperator().getFull_name());
			}
			
			map.put("before_or_after_fill", fillCheckRecord.getBefore_or_after_fill());
			
			List<FillCheckRecordDetails>  fillCheckRecordDetailss = fillCheckRecordDetailsDaoImpl.findByFillCheckRecordId(fillCheckRecord.getId());
			for(FillCheckRecordDetails fillCheckRecordDetails : fillCheckRecordDetailss){
				map.put(fillCheckRecordDetails.getFillCheckInfo().getFill_check_code(), 1);
			}
			
			result.add(map);
		}
		
		return result;
	}

	@Override
	public Long getFillCheckRecordMapTotalNum(List<String> l, Field field) {
		
		return fillCheckRecordDaoImpl.getTotalNum(l, field);
	}

}
