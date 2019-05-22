package com.qian.mobile.control;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.code.AirBottleTrackingRecordCode;
import com.qian.entity.AirBottleInfo;
import com.qian.entity.PreparedInspectionInfo;
import com.qian.entity.User;
import com.qian.mobile.entity.MobileAirBottleCheckEntity;
import com.qian.mobile.entity.MobileAirBottleScanReturnEntity;
import com.qian.mobile.entity.MobileAirBottleSumbitEntity;
import com.qian.service.AirBottleInfoService;
import com.qian.service.PreparedInspectionInfoService;
import com.qian.service.UserService;
import com.qian.util.AirBottleCodeInitUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;

/**
 * @author Lu Kongwen
 * @version Create time：2018-3-21 上午9:01:21
 * @Description
 */
@Controller
@RequestMapping("/mobile/inspection")
public class InspectionMobileController {

	@Autowired
	private UserService userService;

	@Autowired
	private PreparedInspectionInfoService preparedInspectionInfoService;

	@Autowired
	private AirBottleInfoService airBottleInfoService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "bottleOutToInspection", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> bottleOutToInspection(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		if (user == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_ACCOUNT);
		}

		return preparedInspectionInfoService.bottleOutToInspection(entity, user);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "bottleOutToInspectionCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> bottleOutToInspectionCheck(HttpServletRequest request, Map<String, Object> map, MobileAirBottleCheckEntity entity) {

		AirBottleInfo airBottleInfo = airBottleInfoService.findByAirBottleCode(AirBottleCodeInitUtils.initCode(entity.getBottleCode()));

		if (airBottleInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		PreparedInspectionInfo preparedInspectionInfoCheck = preparedInspectionInfoService.findByAirBottleId(airBottleInfo.getId());
		if (preparedInspectionInfoCheck != null) {
			return BaseDto.getFailResult(CommonCode.AIR_BOTTLE_HAS_INSPECTION);
		}
		
//		if(airBottleInfo.getFinalAirBottleTrackingRecord().getState().getId() == AirBottleTrackingRecordCode.BOTTLE_OUT_IN_WAREHOUSE_TO_INSPECTION){
//			return BaseDto.getFailResult(CommonCode.AIR_BOTTLE_HAS_NOT_SEND_INSPECTION);
//		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(airBottleInfo)));
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "bottleBackFromInspection", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> bottleBackFromInspection(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		User user = userService.findById(entity.getUserId());

		if (user == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_ACCOUNT);
		}

		return preparedInspectionInfoService.bottleBackFromInspection(entity, user);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "bottleBackFromInspectionCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleScanReturnEntity> bottleBackFromInspectionCheck(HttpServletRequest request, Map<String, Object> map, MobileAirBottleCheckEntity entity) {

		AirBottleInfo airBottleInfo = airBottleInfoService.findByAirBottleCode(AirBottleCodeInitUtils.initCode(entity.getBottleCode()));

		if (airBottleInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}
		
		if(airBottleInfo.getFinalAirBottleTrackingRecord().getState().getId() != AirBottleTrackingRecordCode.BOTTLE_OUT_IN_WAREHOUSE_TO_INSPECTION){
			return BaseDto.getFailResult(CommonCode.AIR_BOTTLE_HAS_NOT_SEND_INSPECTION);
		}

		return BaseDto.getSuccessResult((new MobileAirBottleScanReturnEntity(airBottleInfo)));
	}
}
