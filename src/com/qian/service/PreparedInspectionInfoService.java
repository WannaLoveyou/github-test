package com.qian.service;

import java.util.List;
import java.util.Map;

import com.qian.entity.PreparedInspectionInfo;
import com.qian.entity.User;
import com.qian.mobile.entity.MobileAirBottleSumbitEntity;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

public interface PreparedInspectionInfoService {

	public BaseDto<Map<String, Object>> getPageList(List<String> l, Field field, int page, int rows);

	public BaseDto<Object> createInspectionOrder(List<String> l, Field field, User user);

	public BaseDto<Object> delByIds(String ids);

	public BaseDto<Object> bottleOutToInspection(MobileAirBottleSumbitEntity entity, User user);

	public BaseDto<Object> bottleBackFromInspection(MobileAirBottleSumbitEntity entity, User user);

	public PreparedInspectionInfo findByAirBottleId(int airBottleId);

}
