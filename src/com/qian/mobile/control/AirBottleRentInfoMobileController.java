package com.qian.mobile.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.AirBottleRentInfo;
import com.qian.entity.User;
import com.qian.mobile.entity.MobileAirBottleRentInfo;
import com.qian.mobile.entity.MobileAirBottleRentInfoEntity;
import com.qian.service.AirBottleRentInfoService;
import com.qian.service.UserService;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2019-5-13 上午9:39:45
 * @Description
 */
@Controller
@RequestMapping("/mobile/airBottleRentInfo")
public class AirBottleRentInfoMobileController {

	@Autowired
	private AirBottleRentInfoService airBottleRentInfoService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "getListByClientId", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<List<MobileAirBottleRentInfo>> getListByClientId(HttpServletRequest request, Map<String, Object> map, int clientId, int airBottleRentInfoStateId) {

		List<String> l = new ArrayList<String>();
		Field field = new Field();

		l.add("clientInfo.id = ?");
		field.addInt(clientId);

		l.add("airBottleRentInfoState.id = ?");
		field.addInt(airBottleRentInfoStateId);

		ToolsBarUtils.getAirBottleRentInfoSearchCondition(request, l, field);

		List<AirBottleRentInfo> airBottleRentInfos = airBottleRentInfoService.getAllList(l, field);

		List<MobileAirBottleRentInfo> mobileAirBottleRentInfos = new ArrayList<>();

		for (AirBottleRentInfo airBottleRentInfo : airBottleRentInfos) {
			MobileAirBottleRentInfo mobileAirBottleRentInfo = new MobileAirBottleRentInfo(airBottleRentInfo);
			mobileAirBottleRentInfos.add(mobileAirBottleRentInfo);
		}

		return BaseDto.getSuccessResult(mobileAirBottleRentInfos);

	}

	@RequestMapping(value = "add")
	public @ResponseBody
	BaseDto<MobileAirBottleRentInfo> add(HttpServletRequest request, Map<String, Object> map, MobileAirBottleRentInfoEntity entity) {

		return airBottleRentInfoService.add(entity);
	}

	@RequestMapping(value = "getBackInfo")
	public @ResponseBody
	BaseDto<Double> getBackInfo(HttpServletRequest request, Map<String, Object> map, int airBottleRentInfoId) {

		return airBottleRentInfoService.getBackInfo(airBottleRentInfoId);
	}

	@RequestMapping(value = "back", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> back(HttpServletRequest request, Map<String, Object> map, int airBottleRentInfoId,double rentMoney,int userId) {

		User user = userService.findById(userId);

		return airBottleRentInfoService.back(airBottleRentInfoId,rentMoney,user);
	
	}

	@RequestMapping(value = "getByAirBottleRentInfoId", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<MobileAirBottleRentInfo> getByAirBottleRentInfoId(HttpServletRequest request, Map<String, Object> map, int airBottleRentInfoId) {

		return BaseDto.getSuccessResult(new MobileAirBottleRentInfo(airBottleRentInfoService.findById(airBottleRentInfoId)));

	}
}
