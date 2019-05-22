package com.qian.service;

import java.util.List;
import java.util.Map;

import com.qian.entity.AirBottleRentInfo;
import com.qian.entity.User;
import com.qian.mobile.entity.MobileAirBottleRentInfo;
import com.qian.mobile.entity.MobileAirBottleRentInfoEntity;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2018-3-27 下午9:11:59
 * @Description
 */
public interface AirBottleRentInfoService {

	public BaseDto<Object> add(AirBottleRentInfo airBottleRentInfo);

	public BaseDto<Map<String, Object>> getPageList(List<String> l, Field field, int page, int rows);

	public BaseDto<Object> back(int airBottleRentInfoId, double rentMoney, User user);

	public BaseDto<Double> getBackInfo(int airBottleRentInfoId);

	public BaseDto<MobileAirBottleRentInfo> add(MobileAirBottleRentInfoEntity entity);

	public List<AirBottleRentInfo> getAllList(List<String> l, Field field);

	public AirBottleRentInfo findById(int airBottleRentInfoId);

}
