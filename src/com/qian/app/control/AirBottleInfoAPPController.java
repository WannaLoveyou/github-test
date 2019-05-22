package com.qian.app.control;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.app.entity.AppAirBottleInfo;
import com.qian.entity.AirBottleInfo;
import com.qian.entity.AirBottleTrackingRecord;
import com.qian.service.AirBottleInfoService;
import com.qian.service.ModuleConfigurationInfoService;
import com.qian.service.impl.AirBottleTrackingRecordServiceImpl;
import com.qian.util.AirBottleCodeInitUtils;
import com.qian.util.HttpUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-9 下午3:35:00
 * @Description
 */
@Controller
@RequestMapping("/app/airBottleInfo")
public class AirBottleInfoAPPController {

	@Autowired
	private AirBottleInfoService airBottleInfoService;

	@Autowired
	private AirBottleTrackingRecordServiceImpl airBottleTrackingRecordServiceImpl;

	@Autowired
	private ModuleConfigurationInfoService moduleConfigurationInfoService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getAirBottleInfoByAirBottleCode", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<AppAirBottleInfo> getAirBottleInfoByAirBottleCode(HttpServletRequest request, Map<String, Object> map, String airBottleCode) {

		String code = AirBottleCodeInitUtils.initCode(airBottleCode);

		if (code == null) {
			return BaseDto.getFailResult(CommonCode.NOT_FIND_AIR_BOTTLE);
		}

		AirBottleInfo airBottleInfo = airBottleInfoService.findByAirBottleCode(code);

		if (airBottleInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_FIND_AIR_BOTTLE);
		}

		List<AirBottleTrackingRecord> airBottleTrackingRecords = airBottleTrackingRecordServiceImpl
				.getLastPartAirBottleTrackingRecordByAirBottle(airBottleInfo);

		return BaseDto.getSuccessResult(new AppAirBottleInfo(airBottleInfo, airBottleTrackingRecords));

	}

	@RequestMapping(value = "saveQRCodeReportRecordPhoto", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<String> saveQRCodeReportRecordPhoto(HttpServletRequest request, Map<String, Object> map, String randomStr, String randomId) throws IOException {

		String path = "D:/QRCodeReportPhoto/";

		File file = new File(path);

		if (!file.exists()) {
			file.mkdir();
		}

		String access_token = randomStr;
		String media_id = randomId;

		String url = HttpUtils.getWeiXinPhoto(path, access_token, media_id);

		return BaseDto.getSuccessResult(url);
	}

}
