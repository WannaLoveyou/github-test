package com.qian.mobile.control;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.AppVersionInfo;
import com.qian.mobile.entity.MobileAppVersionInfo;
import com.qian.service.AppVersionInfoService;
import com.qian.vo.BaseDto;

/**
 * @author Lu Kongwen
 * @version Create time：2016-7-5 下午3:01:52
 * @Description
 */
@Controller
@RequestMapping("/mobile/appVersionInfo")
public class AppVersionInfoMobileController {

	@Autowired
	private AppVersionInfoService appVersionInfoService;

	@RequestMapping(value = "getAppUpdateVersionInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAppVersionInfo> getAppUpdateVersionInfo(HttpServletRequest request, Map<String, Object> map, int versionCode) {

		AppVersionInfo appVersionInfo = appVersionInfoService.getNowAppVersionInfo();

		if (appVersionInfo != null) {
			if (appVersionInfo.getVersion_code() > versionCode) {
				return BaseDto.getSuccessResult(new MobileAppVersionInfo(appVersionInfo));
			}
		}

		return BaseDto.getSuccessResult(null);

	}
	
	@RequestMapping(value = "getAppManualUpdateVersionInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileAppVersionInfo> getAppManualUpdateVersionInfo(HttpServletRequest request, Map<String, Object> map, int versionCode) {

		AppVersionInfo appVersionInfo = appVersionInfoService.getNowAppManualVersionInfo();

		if (appVersionInfo != null) {
			if (appVersionInfo.getVersion_code() > versionCode) {
				return BaseDto.getSuccessResult(new MobileAppVersionInfo(appVersionInfo));
			}
		}

		return BaseDto.getSuccessResult(null);

	}
	
	
	
}
