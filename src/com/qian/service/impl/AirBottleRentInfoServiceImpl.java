package com.qian.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.code.AirBottleRentInfoStateCode;
import com.qian.dao.impl.AirBottleRentInfoDaoImpl;
import com.qian.dao.impl.AirBottleTypeDaoImpl;
import com.qian.dao.impl.ClientInfoDaoImpl;
import com.qian.dao.impl.UserDaoImpl;
import com.qian.entity.AirBottleRentInfo;
import com.qian.entity.AirBottleRentInfoState;
import com.qian.entity.User;
import com.qian.mobile.entity.MobileAirBottleRentInfo;
import com.qian.mobile.entity.MobileAirBottleRentInfoEntity;
import com.qian.service.AirBottleRentInfoService;
import com.qian.util.TimeUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2018-3-27 下午9:12:13
 * @Description
 */
@Service
@Transactional
public class AirBottleRentInfoServiceImpl implements AirBottleRentInfoService {

	@Autowired
	private AirBottleRentInfoDaoImpl airBottleRentInfoDaoImpl;
	
	@Autowired
	private ClientInfoDaoImpl clientInfoDaoImpl;
	
	@Autowired
	private UserDaoImpl userDaoImpl;
	
	@Autowired
	private AirBottleTypeDaoImpl airBottleTypeDaoImpl;
	
	
	@Override
	public BaseDto<Object> add(AirBottleRentInfo airBottleRentInfo) {

		airBottleRentInfoDaoImpl.insert(airBottleRentInfo);

		return BaseDto.getSuccessResult(null);
	}

	@Override
	public BaseDto<Map<String, Object>> getPageList(List<String> l, Field field, int page, int rows) {

		Map<String, Object> result = new HashMap<String, Object>();

		Long total = airBottleRentInfoDaoImpl.getTotalNum(l, field);
		List<AirBottleRentInfo> list = airBottleRentInfoDaoImpl.getPageList(l, field, page, rows);

		result.put("total", total);
		result.put("rows", list);

		return BaseDto.getSuccessResult(result);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> back(int airBottleRentInfoId,double rentMoney, User user) {
		
		AirBottleRentInfo airBottleRentInfo = airBottleRentInfoDaoImpl.findById(airBottleRentInfoId);
		
		if(airBottleRentInfo.getAirBottleRentInfoState().getId() != AirBottleRentInfoStateCode.NO_BACK){
			return BaseDto.getFailResult(CommonCode.NEED_REFRESH);
		}
		
		airBottleRentInfo.setTotal_rent_money(rentMoney);
		airBottleRentInfo.setBack_deposit(airBottleRentInfo.getRent_deposit() - rentMoney);
		
		airBottleRentInfo.setBack_time(new Date());
		airBottleRentInfo.setBack_operator(user);
		airBottleRentInfo.setBackSecondCategory(user.getSecondCategory());
		airBottleRentInfo.setAirBottleRentInfoState(new AirBottleRentInfoState(AirBottleRentInfoStateCode.HAS_BACK));

		return BaseDto.getSuccessResult(null);
	}
	
	@Override
	public BaseDto<Double> getBackInfo(int airBottleRentInfoId) {
		
		Date nowTime = new Date();
		
		AirBottleRentInfo airBottleRentInfo = airBottleRentInfoDaoImpl.findById(airBottleRentInfoId);

		double money = 0;
		
		if(airBottleRentInfo.getRent_money_for_month() != null){
			int month = TimeUtils.compareTimeByMonth(airBottleRentInfo.getRent_time(), nowTime);
			money = airBottleRentInfo.getRent_money_for_month() * month;
		}
		
		if(airBottleRentInfo.getRent_money_for_year() != null){
			int year = TimeUtils.compareTimeByYear(airBottleRentInfo.getRent_time(), nowTime);
			money = airBottleRentInfo.getRent_money_for_year() * year;
		}
		
		
		return BaseDto.getSuccessResult(money);
	}

	@Override
	public BaseDto<MobileAirBottleRentInfo> add(MobileAirBottleRentInfoEntity entity) {

		entity.setClientInfo(clientInfoDaoImpl.findById(entity.getClientId()));
		entity.setUser(userDaoImpl.findById(entity.getUserId()));
		entity.setAirBottleType(airBottleTypeDaoImpl.findById(entity.getAirBottleTypeId()));
		
		AirBottleRentInfo airBottleRentInfo = new AirBottleRentInfo(entity);

		airBottleRentInfoDaoImpl.insert(airBottleRentInfo);
		
		return BaseDto.getSuccessResult(new MobileAirBottleRentInfo(airBottleRentInfo));
	}

	@Override
	public List<AirBottleRentInfo> getAllList(List<String> l, Field field) {
	
		return airBottleRentInfoDaoImpl.getAllList(l, field);
	}

	@Override
	public AirBottleRentInfo findById(int airBottleRentInfoId) {
		
		return airBottleRentInfoDaoImpl.findById(airBottleRentInfoId);
	}


}
