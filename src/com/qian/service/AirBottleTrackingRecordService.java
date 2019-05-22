package com.qian.service;

import java.util.List;
import java.util.Map;

import com.qian.entity.AirBottleTrackingRecord;
import com.qian.vo.AirBottleTrackingRecordReportVo;
import com.qian.vo.AirBottleTrackingRecordCountVo;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;
import com.qian.vo.FillCheckRecordVo;
import com.qian.vo.ReturnBottleInfoVo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-5-22 下午4:15:58
 * @Description
 */
public interface AirBottleTrackingRecordService {

	public List<AirBottleTrackingRecord> getBottleProcessByCode(String bottleCode);
	
	public List<AirBottleTrackingRecord> getBottleProcessById(int bottleId);

	public List<AirBottleTrackingRecordReportVo> getAllAirBottleFinalTrackingRecord(List<String> l,
			Field filed, int page, int rows);

	public Long getAirBottleFinalTrackingRecordTotalNum(List<String> airBottleTrackingRecordSearchConditionList, Field airBottleTrackingRecordSearchConditionFiled);


	public List<ReturnBottleInfoVo> getStoreGetBottleNum(List<String> list, Field filed);

	public List<ReturnBottleInfoVo> getStoreReturnBottleNum(List<String> list, Field filed);

	public List<ReturnBottleInfoVo> getStoreGetBottleDetailNum(List<String> list, Field filed);

	public List<ReturnBottleInfoVo> getStoreReturnBottleDetailNum(List<String> list, Field filed);

	public List<ReturnBottleInfoVo> getDeliveryManGetBottleNum(List<String> list, Field filed);

	public List<ReturnBottleInfoVo> getDeliveryManReturnBottleNum(List<String> list, Field filed);

	public List<ReturnBottleInfoVo> getDeliveryManGetBottleDetailNum(List<String> list, Field filed);

	public List<ReturnBottleInfoVo> getDeliveryManReturnBottleDetailNum(List<String> list, Field filed);

	public List<ReturnBottleInfoVo> getWarehouseGetBottleDetailNum(List<String> list, Field filed);

	public List<ReturnBottleInfoVo> getWarehouseReturnBottleDetailNum(List<String> list, Field filed);

	public List<ReturnBottleInfoVo> getWarehouseForceReturnBottleDetailNum(List<String> list, Field filed);

	public Integer getMultipleSendNum();

	public CommonCode delMultipleSendRecord();

	public List<ReturnBottleInfoVo> getExceptionReturnBottleNum(List<String> list, Field filed);

	public List<ReturnBottleInfoVo> getExceptionReturnPassBottleNum(List<String> list, Field filed);

	public List<FillCheckRecordVo> getFillCheckRecordVoList(List<String> l, Field field, int page, int rows);

	public Long getFillCheckRecordVoTotalNum(List<String> l, Field field);

	public List<Map<String, Object>> getFillCheckRecordMapList(List<String> l, Field field, int page, int rows);

	public Long getFillCheckRecordMapTotalNum(List<String> l, Field field);
	
	public List<AirBottleTrackingRecordCountVo> getTotalInWarehouseGroupByDeliveryMan(List<String> l,Field field);
}
