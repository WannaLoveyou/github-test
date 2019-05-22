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

import com.qian.entity.AirBottleBelong;
import com.qian.entity.AirBottleInfo;
import com.qian.entity.AirBottleTrackingRecord;
import com.qian.entity.AirBottleType;
import com.qian.entity.DetectionUnit;
import com.qian.entity.ProductionUnit;
import com.qian.entity.User;
import com.qian.mobile.entity.MobileAirBottleBelong;
import com.qian.mobile.entity.MobileAirBottleCheckEntity;
import com.qian.mobile.entity.MobileAirBottleInfo;
import com.qian.mobile.entity.MobileAirBottleTrackingRecord;
import com.qian.mobile.entity.MobileAirBottleType;
import com.qian.mobile.entity.MobileDetectionUnit;
import com.qian.mobile.entity.MobileProductionUnit;
import com.qian.service.AirBottleBelongService;
import com.qian.service.AirBottleInfoService;
import com.qian.service.AirBottleTrackingRecordService;
import com.qian.service.AirBottleTypeService;
import com.qian.service.DetectionUnitService;
import com.qian.service.ProductionUnitService;
import com.qian.service.UserService;
import com.qian.util.AirBottleCodeInitUtils;
import com.qian.util.StringUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.util.UploadUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-6-4 下午2:37:38
 * @Description
 */
@Controller
@RequestMapping("/mobile/airBottleInfo")
public class AirBottleInfoMobileController {

	@Autowired
	private AirBottleInfoService airBottleInfoService;

	@Autowired
	private AirBottleTypeService airBottleTypeService;

	@Autowired
	private ProductionUnitService productionUnitService;

	@Autowired
	private DetectionUnitService detectionUnitService;

	@Autowired
	private UserService userService;

	@Autowired
	private AirBottleTrackingRecordService airBottleTrackingRecordService;

	@Autowired
	private AirBottleBelongService airBottleBelongService;

	@RequestMapping(value = "addAirBottleInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> addAirBottleInfo(HttpServletRequest request, Map<String, Object> map, MobileAirBottleInfo mobileAirBottleInfo, int userId) throws Exception {

		User user = userService.findById(userId);

		// 上传图片
		List<String> photos = UploadUtils.uploadAirBottleInfoPhotos(request);
		mobileAirBottleInfo.setPhotos(photos);

		return airBottleInfoService.addAirBottleInfo(mobileAirBottleInfo, user);
	}

	@RequestMapping(value = "editAirBottleInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> editAirBottleInfo(HttpServletRequest request, Map<String, Object> map, MobileAirBottleInfo mobileAirBottleInfo, int userId)
			throws Exception {

		User user = userService.findById(userId);

		// 上传图片
		List<String> photos = UploadUtils.uploadAirBottleInfoPhotos(request);
		mobileAirBottleInfo.setPhotos(photos);

		return airBottleInfoService.editAirBottleInfo(mobileAirBottleInfo, user);

	}

	@RequestMapping(value = "getAllAirBottleTypeList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileAirBottleType>> getAllAirBottleTypeList(HttpServletRequest request, Map<String, Object> map) {

		List<AirBottleType> list = airBottleTypeService.getAllList();

		List<MobileAirBottleType> result = new ArrayList<MobileAirBottleType>();

		for (AirBottleType airBottleType : list) {

			MobileAirBottleType mobileAirBottleType = new MobileAirBottleType(airBottleType);

			result.add(mobileAirBottleType);
		}

		return BaseDto.getSuccessResult(result);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getAirBottleInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleInfo> getAirBottleInfo(HttpServletRequest request, Map<String, Object> map, MobileAirBottleCheckEntity entity) throws Exception {

		AirBottleInfo airBottleInfo = airBottleInfoService.findByAirBottleCode(entity);

		User user = userService.findById(entity.getUserId());

		if (airBottleInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		List<MobileAirBottleTrackingRecord> mobileAirBottleTrackingRecords = new ArrayList<MobileAirBottleTrackingRecord>();

		List<AirBottleTrackingRecord> list = airBottleTrackingRecordService.getBottleProcessById(airBottleInfo.getId());

		for (AirBottleTrackingRecord airBottleTrackingRecord : list) {

			MobileAirBottleTrackingRecord mobileAirBottleTrackingRecord = new MobileAirBottleTrackingRecord(airBottleTrackingRecord, user);

			mobileAirBottleTrackingRecords.add(mobileAirBottleTrackingRecord);
		}

		MobileAirBottleInfo mobileAirBottleInfo = new MobileAirBottleInfo(airBottleInfo, mobileAirBottleTrackingRecords);

		return BaseDto.getSuccessResult(mobileAirBottleInfo);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getAirBottleInfoBySealCode", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileAirBottleInfo>> getAirBottleInfoBySealCode(HttpServletRequest request, Map<String, Object> map, String sealCode) throws Exception {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		StringBuffer seal_code_str = new StringBuffer();
		seal_code_str.append("%").append(sealCode).append("%");

		l.add("air_bottle_seal_code like ?");
		filed.addStr(seal_code_str.toString());

		List<AirBottleInfo> list = airBottleInfoService.getPageList(l, filed, 1, 100);

		if (list.size() == 0) {
			return BaseDto.getFailResult(CommonCode.NOT_FIND_AIR_BOTTLE);
		}

		List<MobileAirBottleInfo> mobileAirBottleInfoList = new ArrayList<MobileAirBottleInfo>();

		for (AirBottleInfo airBottleInfo : list) {

			MobileAirBottleInfo mobileAirBottleInfo = new MobileAirBottleInfo(airBottleInfo);

			mobileAirBottleInfoList.add(mobileAirBottleInfo);
		}

		return BaseDto.getSuccessResult(mobileAirBottleInfoList);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "findQRCodeAirBottleInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileAirBottleInfo>> findQRCodeAirBottleInfo(HttpServletRequest request, Map<String, Object> map) throws Exception {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		ToolsBarUtils.getAirBottleSearchCondition(request, l, filed);

		List<AirBottleInfo> list = airBottleInfoService.getPageList(l, filed, 1, 50);

		List<MobileAirBottleInfo> mobileAirBottleInfos = new ArrayList<MobileAirBottleInfo>();

		for (AirBottleInfo airBottleInfo : list) {
			if (StringUtils.nonEmpty(airBottleInfo.getAir_bottle_code())) {
				mobileAirBottleInfos.add(new MobileAirBottleInfo(airBottleInfo));
			}
		}

		if (mobileAirBottleInfos.size() == 0) {
			return BaseDto.getFailResult(CommonCode.NOT_FIND_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult(mobileAirBottleInfos);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "replaceAirBottleQRcode", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAirBottleInfo> replaceAirBottleQRcode(HttpServletRequest request, Map<String, Object> map, int bottleId, String bottleCode) throws Exception {

		AirBottleInfo airBottleInfo = airBottleInfoService.findById(bottleId);

		String code = AirBottleCodeInitUtils.initCode(bottleCode);

		AirBottleInfo newAirBottleInfo = airBottleInfoService.findByAirBottleCode(code);

		if (newAirBottleInfo != null) {
			return BaseDto.getFailResult(CommonCode.ALREAY_EXIST_AIR_BOTTLE);
		}

		airBottleInfo.setAir_bottle_code(code);

		airBottleInfoService.edit(airBottleInfo);

		return BaseDto.getSuccessResult(null);
	}

	@RequestMapping(value = "getAllProductionUnitList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileProductionUnit>> getAllProductionUnitList(HttpServletRequest request, Map<String, Object> map) {

		List<ProductionUnit> list = productionUnitService.getAllList();

		List<MobileProductionUnit> result = new ArrayList<MobileProductionUnit>();

		for (ProductionUnit productionUnit : list) {

			MobileProductionUnit mobileProductionUnit = new MobileProductionUnit(productionUnit);

			result.add(mobileProductionUnit);
		}

		return BaseDto.getSuccessResult(result);
	}

	@RequestMapping(value = "getAllDetectionUnitList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileDetectionUnit>> getAllDetectionUnitList(HttpServletRequest request, Map<String, Object> map) {

		List<DetectionUnit> list = detectionUnitService.getAllList();

		List<MobileDetectionUnit> result = new ArrayList<MobileDetectionUnit>();

		for (DetectionUnit detectionUnit : list) {

			MobileDetectionUnit mobileDetectionUnit = new MobileDetectionUnit(detectionUnit);

			result.add(mobileDetectionUnit);
		}

		return BaseDto.getSuccessResult(result);
	}

	@RequestMapping(value = "getAllAirBottleBelongList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileAirBottleBelong>> getAllAirBottleBelongList(HttpServletRequest request, Map<String, Object> map) {

		List<AirBottleBelong> list = airBottleBelongService.getAllList();

		List<MobileAirBottleBelong> result = new ArrayList<MobileAirBottleBelong>();

		for (AirBottleBelong airBottleBelong : list) {

			MobileAirBottleBelong mobileAirBottleBelong = new MobileAirBottleBelong(airBottleBelong);

			result.add(mobileAirBottleBelong);
		}

		return BaseDto.getSuccessResult(result);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "checkAirBottleCodeExist", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<AirBottleInfo> checkAirBottleCodeExist(HttpServletRequest request, Map<String, Object> map, String bottleCode) throws Exception {

		String code = AirBottleCodeInitUtils.initCode(bottleCode);

		if (code == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_AIR_BOTTLE);
		}

		AirBottleInfo airBottleInfo = airBottleInfoService.findByAirBottleCode(code);

		if (airBottleInfo == null) {
			return BaseDto.getSuccessResult(null);
		}

		return BaseDto.getFailResult(CommonCode.ALREAY_EXIST_AIR_BOTTLE);
	}

	@RequestMapping(value = "getAirBottleProcess", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileAirBottleTrackingRecord>> getAirBottleProcess(HttpServletRequest request, Map<String, Object> map, int bottleId, int userId)
			throws Exception {

		User user = userService.findById(userId);

		List<MobileAirBottleTrackingRecord> result = new ArrayList<MobileAirBottleTrackingRecord>();

		List<AirBottleTrackingRecord> list = airBottleTrackingRecordService.getBottleProcessById(bottleId);

		for (AirBottleTrackingRecord airBottleTrackingRecord : list) {

			MobileAirBottleTrackingRecord mobileAirBottleTrackingRecord = new MobileAirBottleTrackingRecord(airBottleTrackingRecord, user);

			result.add(mobileAirBottleTrackingRecord);
		}

		return BaseDto.getSuccessResult(result);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "findNonQRCodeAirBottleInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileAirBottleInfo>> findNonQRCodeAirBottleInfo(HttpServletRequest request, Map<String, Object> map) throws Exception {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		ToolsBarUtils.getAirBottleSearchCondition(request, l, filed);

		List<AirBottleInfo> list = airBottleInfoService.getPageList(l, filed, 1, 50);

		List<MobileAirBottleInfo> mobileAirBottleInfos = new ArrayList<MobileAirBottleInfo>();

		for (AirBottleInfo airBottleInfo : list) {
			if (!StringUtils.nonEmpty(airBottleInfo.getAir_bottle_code())) {
				mobileAirBottleInfos.add(new MobileAirBottleInfo(airBottleInfo));
			}
		}

		if (mobileAirBottleInfos.size() == 0) {
			return BaseDto.getFailResult(CommonCode.NOT_FIND_AIR_BOTTLE);
		}

		return BaseDto.getSuccessResult(mobileAirBottleInfos);
	}

	@RequestMapping(value = "saveNewAirBottleQRCode", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> saveAirBottleQRCode(HttpServletRequest request, Map<String, Object> map, MobileAirBottleInfo mobileAirBottleInfo, int userId)
			throws Exception {

		User user = userService.findById(userId);

		CommonCode commonCode = airBottleInfoService.saveNewAirBottleQRCode(mobileAirBottleInfo, user);

		return BaseDto.getResult(commonCode, null);
	}
}
