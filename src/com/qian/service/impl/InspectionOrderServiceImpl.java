package com.qian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qian.code.AirBottleTrackingRecordCode;
import com.qian.code.AirBottleUpCloudStateCode;
import com.qian.code.InspectionStateCode;
import com.qian.code.IsscrapCode;
import com.qian.dao.impl.AirBottleInfoDaoImpl;
import com.qian.dao.impl.AirBottleTrackingRecordDaoImpl;
import com.qian.dao.impl.InspectionOrderInfoDaoImpl;
import com.qian.dao.impl.InspectionOrderItemInfoDaoImpl;
import com.qian.entity.AirBottleInfo;
import com.qian.entity.InspectionOrderInfo;
import com.qian.entity.InspectionOrderItemInfo;
import com.qian.entity.InspectionState;
import com.qian.entity.User;
import com.qian.service.InspectionOrderService;
import com.qian.util.AirBottleTrackingRecordUtils;
import com.qian.util.HttpUtils;
import com.qian.util.InspectionUtils;
import com.qian.util.ObjectUtils;
import com.qian.util.StringUtils;
import com.qian.util.TimeUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;
import com.qian.vo.QueryInspectionOrderVo;
import com.qian.vo.SendInspectionOrderVo;

@Service
@Transactional
public class InspectionOrderServiceImpl implements InspectionOrderService {

	private static Logger log = Logger.getLogger(InspectionOrderServiceImpl.class);

	@Autowired
	private InspectionOrderInfoDaoImpl infoDaoImpl;

	@Autowired
	private InspectionOrderItemInfoDaoImpl itemInfoDaoImpl;
	
	@Autowired
	private AirBottleInfoDaoImpl airBottleInfoDaoImpl;
	
	@Autowired
	private AirBottleTrackingRecordDaoImpl airBottleTrackingRecordDaoImpl;
	
	public BaseDto<Map<String, Object>> getPageList(List<String> l, Field field, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = infoDaoImpl.getTotalNum(l, field);
		List<InspectionOrderInfo> list = infoDaoImpl.getPageList(l, field, page, rows);

		result.put("total", total);
		result.put("rows", list);

		return BaseDto.getSuccessResult(result);
	}

	public List<InspectionOrderItemInfo> getItemsInOrder(int orderId) {

		return itemInfoDaoImpl.getItemsByOrderId(orderId);
	}

	@SuppressWarnings("unchecked")
	public BaseDto<Object> sendInspection(int orderId) {

		InspectionOrderInfo inspectionOrderInfo = infoDaoImpl.findById(orderId);

		List<InspectionOrderItemInfo> inspectionOrderItemInfos = itemInfoDaoImpl.getItemsByOrderId(orderId);

		SendInspectionOrderVo sendInspectionOrderVo = new SendInspectionOrderVo(inspectionOrderInfo, inspectionOrderItemInfos);
		Map<String, Object> map = null;

		try {
			map = ObjectUtils.objectToMap(sendInspectionOrderVo);

			log.info("提交检测系统信息-SEND_ORDER:" + map);

			String result = HttpUtils.httpPost(InspectionUtils.SEND_ORDER, map);

			log.info("检测系统返回信息-SEND_ORDER:" + result);

			ObjectMapper mapper = new ObjectMapper();

			BaseDto<Map<String, Object>> baseDto = mapper.readValue(result, new TypeReference<BaseDto<Map<String, Object>>>() {
			});

			if (baseDto.getCode() == CommonCode.OK.getCode()) {
				inspectionOrderInfo.setInspectionSystemOrderNumber(baseDto.getData().get("number").toString());
				inspectionOrderInfo.setInspectionState(new InspectionState(InspectionStateCode.HAS_SUMBIT));
				infoDaoImpl.update(inspectionOrderInfo);
				return BaseDto.getSuccessResult(null);
			}else{
				return BaseDto.getFailResult(CommonCode.SYSTEM_EXCEPTION,baseDto.getMsg());
			}

		} catch (Exception e) {
			log.error(e);
		}

		return BaseDto.getFailResult(CommonCode.SYSTEM_EXCEPTION);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> refreshInspectionOrder(int orderId) {

		InspectionOrderInfo inspectionOrderInfo = infoDaoImpl.findById(orderId);

		QueryInspectionOrderVo queryInspectionOrderVo = new QueryInspectionOrderVo(inspectionOrderInfo.getInspectionOrderNumber());

		Map<String, Object> map = null;

		try {
			map = ObjectUtils.objectToMap(queryInspectionOrderVo);

			log.info("提交检测系统信息-QUERY_ORDER_INFO:" + map);

			String result = HttpUtils.httpPost(InspectionUtils.QUERY_ORDER_INFO, map);

			log.info("检测系统返回信息-QUERY_ORDER_INFO:" + result);

			ObjectMapper mapper = new ObjectMapper();

			BaseDto<Map<String, Object>> baseDto = mapper.readValue(result, new TypeReference<BaseDto<Map<String, Object>>>() {
			});

			if (baseDto.getCode() == CommonCode.OK.getCode()) {
				inspectionOrderInfo.setInspectionSystemOrderNumber(baseDto.getData().get("number").toString());
				inspectionOrderInfo.setInspectionState(new InspectionState(InspectionStateCode.HAS_SUMBIT));
				inspectionOrderInfo.setInspectionSystemState(baseDto.getData().get("checkStatus").toString());
				inspectionOrderInfo.setInspectionSystemRefreshTime(new Date());
				infoDaoImpl.update(inspectionOrderInfo);
				return BaseDto.getSuccessResult(null);
			}else{
				return BaseDto.getFailResult(CommonCode.SYSTEM_EXCEPTION,baseDto.getMsg());
			}

		} catch (Exception e) {
			log.error(e);
		}
		
		return BaseDto.getFailResult(CommonCode.SYSTEM_EXCEPTION);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> refreshInspectionOrderDetails(int orderId) {
		
		InspectionOrderInfo inspectionOrderInfo = infoDaoImpl.findById(orderId);

		QueryInspectionOrderVo queryInspectionOrderVo = new QueryInspectionOrderVo(inspectionOrderInfo.getInspectionOrderNumber());

		Map<String, Object> map = null;

		try {
			map = ObjectUtils.objectToMap(queryInspectionOrderVo);

			log.info("提交检测系统信息-QUERY_ORDER_DETAILS_INFO:" + map);

			String result = HttpUtils.httpPost(InspectionUtils.QUERY_ORDER_DETAILS_INFO, map);

			log.info("检测系统返回信息-QUERY_ORDER_DETAILS_INFO:" + result);

			ObjectMapper mapper = new ObjectMapper();

			BaseDto<List<Map<String, Object>>> baseDto = mapper.readValue(result, new TypeReference<BaseDto<List<Map<String, Object>>>>() {
			});

			if (baseDto.getCode() == CommonCode.OK.getCode()) {
				
				initInspectionOrderDetails(orderId,baseDto.getData());
				return BaseDto.getSuccessResult(null);
			}else{
				return BaseDto.getFailResult(CommonCode.SYSTEM_EXCEPTION,baseDto.getMsg());
			}

		} catch (Exception e) {
			log.error(e);
		}
		
		return BaseDto.getFailResult(CommonCode.SYSTEM_EXCEPTION);
	}
	
	private void initInspectionOrderDetails(int orderId, List<Map<String, Object>> list){
		
		Map<String,Map<String,Object>> result = new TreeMap<String,Map<String,Object>>();
		
		for(Map<String, Object> map : list){
			result.put((String) map.get("gasNumberId"), map);
		}
		
		List<InspectionOrderItemInfo> inspectionOrderItemInfos = itemInfoDaoImpl.getItemsByOrderId(orderId);

		for(InspectionOrderItemInfo inspectionOrderItemInfo : inspectionOrderItemInfos){
			
			Map<String, Object> map = result.get(inspectionOrderItemInfo.getGasNumberId());
			
			if(map != null){
				
				if(map.get("newGasNumber") != null){
					inspectionOrderItemInfo.setNewGasNumber((String) map.get("newGasNumber"));
				}

				inspectionOrderItemInfo.setIsscrap((String) map.get("isscrap"));
				
				if(map.get("scrapText") != null){
					inspectionOrderItemInfo.setScrapText((String) map.get("scrapText"));
				}
				
				inspectionOrderItemInfo.setResult((String) map.get("result"));
				inspectionOrderItemInfo.setCheckDate(TimeUtils.getDateByyyyyMMdd((String) map.get("checkDate")));
				if(map.get("nextCheckDate") != null){
					inspectionOrderItemInfo.setNextCheckDate(TimeUtils.getDateByyyyyMMdd((String) map.get("nextCheckDate")));
				}				
				
				if(map.get("lastUseDate") != null){
					inspectionOrderItemInfo.setLastUseDate(TimeUtils.getDateByyyyyMMdd((String) map.get("lastUseDate")));
				}
				
				if(map.get("endUseDate") != null){
					inspectionOrderItemInfo.setEndUseDate(TimeUtils.getDateByyyyyMMdd((String) map.get("endUseDate")));
				}
			}
		}
		
		itemInfoDaoImpl.update(inspectionOrderItemInfos);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<List<Map<String, Object>>> queryInspectionOrderDetailsResult(int orderDetailsId) {
		
		InspectionOrderItemInfo inspectionOrderItemInfo = itemInfoDaoImpl.findById(orderDetailsId);
		
		QueryInspectionOrderVo queryInspectionOrderVo = new QueryInspectionOrderVo(inspectionOrderItemInfo.getInspectionOrderInfo().getInspectionOrderNumber(),inspectionOrderItemInfo.getGasNumberId());

		Map<String, Object> map = null;

		try {
			map = ObjectUtils.objectToMap(queryInspectionOrderVo);

			log.info("提交检测系统信息-QUERY_ORDER_DETAILS_INSPECTION_INFO:" + map);

			String result = HttpUtils.httpPost(InspectionUtils.QUERY_ORDER_DETAILS_INSPECTION_INFO, map);

			log.info("检测系统返回信息-QUERY_ORDER_DETAILS_INSPECTION_INFO:" + result);

			ObjectMapper mapper = new ObjectMapper();

			BaseDto<List<Map<String, Object>>> baseDto = mapper.readValue(result, new TypeReference<BaseDto<List<Map<String, Object>>>>() {
			});

			if (baseDto.getCode() == CommonCode.OK.getCode()) {
				
				return BaseDto.getSuccessResult(baseDto.getData());
			}else{
				return BaseDto.getFailResult(CommonCode.SYSTEM_EXCEPTION,baseDto.getMsg());
			}

		} catch (Exception e) {
			log.error(e);
		}
		
		return BaseDto.getFailResult(CommonCode.SYSTEM_EXCEPTION);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> confirmInspectionOrder(int orderId) {

		InspectionOrderInfo inspectionOrderInfo = infoDaoImpl.findById(orderId);

		QueryInspectionOrderVo queryInspectionOrderVo = new QueryInspectionOrderVo(inspectionOrderInfo.getInspectionOrderNumber());

		Map<String, Object> map = null;

		try {
			map = ObjectUtils.objectToMap(queryInspectionOrderVo);

			log.info("提交检测系统信息-CONFIRM_ORDER:" + map);

			String result = HttpUtils.httpPost(InspectionUtils.CONFIRM_ORDER, map);

			log.info("检测系统返回信息-CONFIRM_ORDER:" + result);

			ObjectMapper mapper = new ObjectMapper();

			BaseDto<Map<String, Object>> baseDto = mapper.readValue(result, new TypeReference<BaseDto<Map<String, Object>>>() {
			});

			if (baseDto.getCode() == CommonCode.OK.getCode()) {
				return BaseDto.getSuccessResult(null);
			}else{
				return BaseDto.getFailResult(CommonCode.SYSTEM_EXCEPTION,baseDto.getMsg());
			}

		} catch (Exception e) {
			log.error(e);
		}

		return BaseDto.getFailResult(CommonCode.SYSTEM_EXCEPTION);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> initInspectionOrderData(int orderId, User user) {
		
		InspectionOrderInfo inspectionOrderInfo = infoDaoImpl.findById(orderId);

		if(!StringUtils.nonEmpty(inspectionOrderInfo.getInspectionSystemState())){
			return BaseDto.getFailResult(CommonCode.NEED_REFRESH);
		}
		
		if(!"已确认".equals(inspectionOrderInfo.getInspectionSystemState())){
			return BaseDto.getFailResult(CommonCode.NEED_CONFIRM);
		}
		
		if(inspectionOrderInfo.getInspectionOrderInitDataTime() != null){
			return BaseDto.getFailResult(CommonCode.THE_SAME_OPERATION);
		}
		
		List<InspectionOrderItemInfo> inspectionOrderItemInfos = itemInfoDaoImpl.getItemsByOrderId(orderId);

		List<AirBottleInfo> airBottleInfos = new ArrayList<AirBottleInfo>();
		
		Date nowTime = new Date();
		
		for(InspectionOrderItemInfo inspectionOrderItemInfo : inspectionOrderItemInfos){
			
			AirBottleInfo airBottleInfo = inspectionOrderItemInfo.getAirBottleInfo();
			
			if(inspectionOrderItemInfo.getCheckDate() != null){
				airBottleInfo.setCheck_time(inspectionOrderItemInfo.getCheckDate());
			}

			//3个日期选1个作为下次检测日期
			if (inspectionOrderItemInfo.getNextCheckDate() != null) {
				airBottleInfo.setNext_check_time(inspectionOrderItemInfo.getNextCheckDate());
			}
			if(inspectionOrderItemInfo.getLastUseDate() != null){
				airBottleInfo.setNext_check_time(inspectionOrderItemInfo.getLastUseDate());
			}
			if(inspectionOrderItemInfo.getEndUseDate() != null){
				airBottleInfo.setNext_check_time(inspectionOrderItemInfo.getEndUseDate());
			}
			
			if(StringUtils.nonEmpty(inspectionOrderItemInfo.getNewGasNumber())){
				airBottleInfo.setAir_bottle_seal_code(inspectionOrderItemInfo.getNewGasNumber());
			}
			
			if("是".equals(inspectionOrderItemInfo.getIsscrap())){
				airBottleInfo.setIsscrap(IsscrapCode.SCRAP);
				airBottleInfo.setScrap_time(nowTime);
				airBottleInfo.setScrap_operator(user);
				
				//送检轨迹
				AirBottleTrackingRecordUtils.record(airBottleTrackingRecordDaoImpl, airBottleInfoDaoImpl, airBottleInfo, null, null, null, null,
						AirBottleTrackingRecordCode.INSPECTION_SCRAP, user);
			}
			

			
			airBottleInfo.setUpState(AirBottleUpCloudStateCode.WAIT_UP);
			airBottleInfos.add(airBottleInfo);
		}
		
		inspectionOrderInfo.setInspectionOrderInitDataTime(nowTime);
		infoDaoImpl.update(inspectionOrderInfo);
		
		airBottleInfoDaoImpl.update(airBottleInfos);
		
		return BaseDto.getSuccessResult(null);
	}

}
