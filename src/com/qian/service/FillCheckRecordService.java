package com.qian.service;

import java.util.List;
import java.util.Map;

import com.qian.mobile.entity.MobileFillCheckInfoSubmitEntity;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2018-10-23 下午9:06:04
 * @Description
 */
public interface FillCheckRecordService {

	public BaseDto<Object> sumbitFillCheckInfoBeforeFilling(MobileFillCheckInfoSubmitEntity entity);

	public BaseDto<Object> sumbitFillCheckInfoAfterFilling(MobileFillCheckInfoSubmitEntity entity);

	public List<List<Map<String, Object>>> getFillCheckRecordHeader();

	public List<Map<String, Object>> getFillCheckRecordMapList(List<String> l, Field field, int page, int rows);

	public Long getFillCheckRecordMapTotalNum(List<String> l, Field field);

}
