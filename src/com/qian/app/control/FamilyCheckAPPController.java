package com.qian.app.control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.app.entity.AppAddFamilyCheckOrder;
import com.qian.app.entity.AppFamilyCheckInfo;
import com.qian.entity.FamilyCheckContentInfo;
import com.qian.entity.FamilyCheckInfo;
import com.qian.service.FamilyCheckInfoService;
import com.qian.util.HttpUtils;
import com.qian.util.StringUtils;
import com.qian.util.TimeUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-13 下午3:53:09
 * @Description
 */
@Controller
@RequestMapping("/app/familyCheck")
public class FamilyCheckAPPController {

	@Autowired
	private FamilyCheckInfoService familyCheckInfoService;

	@RequestMapping(value = "getPageList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Map<String, Object>> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		String begin_appointment_time = request.getParameter("begin_appointment_time");
		if (StringUtils.nonEmpty(begin_appointment_time)) {
			l.add("appointment_check_time >= ?");
			filed.addDate(begin_appointment_time);
		}

		String end_appointment_time = request.getParameter("end_appointment_time");
		if (StringUtils.nonEmpty(end_appointment_time)) {
			l.add("appointment_check_time < ?");
			filed.addDate(TimeUtils.getNextDayString(end_appointment_time));
		}

		String tel_number = request.getParameter("tel_number");
		if (StringUtils.nonEmpty(tel_number)) {
			l.add("(clientInfo.mobile_tel_number_1 = ? or clientInfo.mobile_tel_number_2 = ? or clientInfo.fixed_tel_number_1 = ? or clientInfo.fixed_tel_number_2 = ? or family_check_tel_number = ?)");
			filed.addStr(tel_number);
			filed.addStr(tel_number);
			filed.addStr(tel_number);
			filed.addStr(tel_number);
			filed.addStr(tel_number);
		}

		String client_code = request.getParameter("client_code");
		if (StringUtils.nonEmpty(client_code)) {
			l.add("clientInfo.client_code = ?");
			filed.addStr(client_code);
		}

		Long total = familyCheckInfoService.getTotalNum(l, filed);
		List<FamilyCheckInfo> list = familyCheckInfoService.getPageList(l, filed, page, rows);

		List<AppFamilyCheckInfo> appFamilyCheckInfos = new ArrayList<AppFamilyCheckInfo>();

		for (FamilyCheckInfo familyCheckInfo : list) {

			AppFamilyCheckInfo appFamilyCheckInfo = new AppFamilyCheckInfo(familyCheckInfo);

			appFamilyCheckInfos.add(appFamilyCheckInfo);
		}

		result.put("total", total);
		result.put("rows", appFamilyCheckInfos);

		return BaseDto.getSuccessResult(result);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addFamilyCheckOrder", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> addFamilyCheckOrder(HttpServletRequest request, Map<String, Object> map, AppAddFamilyCheckOrder appAddFamilyCheckOrder) {

		CommonCode commonCode = familyCheckInfoService.addAppFamilyCheckOrder(appAddFamilyCheckOrder);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "fillInFamilyCheckOrder", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> fillInFamilyCheckOrder(HttpServletRequest request, Map<String, Object> map, int familyCheckId, FamilyCheckContentInfo familyCheckContentInfo) {

		CommonCode commonCode = familyCheckInfoService.fillInFamilyCheckOrder(familyCheckId, familyCheckContentInfo);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@RequestMapping(value = "findFamilyCheckContentByFamilyCheckId", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<FamilyCheckContentInfo> findFamilyCheckContentByFamilyCheckId(HttpServletRequest request, Map<String, Object> map, int familyCheckId) {

		FamilyCheckContentInfo familyCheckContentInfo = familyCheckInfoService.findFamilyCheckContentByFamilyCheckId(familyCheckId);

		return BaseDto.getSuccessResult(familyCheckContentInfo);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "saveFamilyCheckPhoto", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<String> saveFamilyCheckPhoto(HttpServletRequest request, Map<String, Object> map, String randomStr, String randomId, int familyCheckId)
			throws IOException {

		String path = "D:/FamilyCheckPhoto/";

		File file = new File(path);

		if (!file.exists()) {
			file.mkdir();
		}

		String access_token = randomStr;
		String media_id = randomId;

		String url = HttpUtils.getWeiXinPhoto(path, access_token, media_id);
		
		CommonCode commonCode = familyCheckInfoService.saveFamilyCheckPhoto(familyCheckId,url);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

}
