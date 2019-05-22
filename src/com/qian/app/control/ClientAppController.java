package com.qian.app.control;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.app.entity.AppAddClientInfo;
import com.qian.app.entity.AppClinentInfo;
import com.qian.entity.ClientInfo;
import com.qian.entity.FloorSubsidies;
import com.qian.service.ClientInfoService;
import com.qian.service.WeChatActivityService;
import com.qian.util.StringUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-21 下午5:03:17
 * @Description
 */
@Controller
@RequestMapping("/app/client")
public class ClientAppController {

	@Autowired
	private ClientInfoService clientInfoService;

	@Autowired
	private WeChatActivityService weChatActivityService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getMyClientInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<AppClinentInfo> getMyClientInfo(HttpServletRequest request, Map<String, Object> map, int clientId) {

		ClientInfo clientInfo = clientInfoService.findById(clientId);

		if (clientInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_ACCOUNT);
		}

		return BaseDto.getSuccessResult(new AppClinentInfo(clientInfo));

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "editMyClientInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> editMyClientInfo(HttpServletRequest request, Map<String, Object> map, AppClinentInfo appClinentInfo) {

		ClientInfo clientInfo = clientInfoService.findById(appClinentInfo.getClientId());

		if (clientInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_ACCOUNT);
		}

		clientInfo.setClient_name(appClinentInfo.getClientName());
		clientInfo.setClient_address(appClinentInfo.getClientAddress());

		FloorSubsidies floorSubsidies = new FloorSubsidies();
		floorSubsidies.setId(appClinentInfo.getFloorSubsidiesId());

		clientInfo.setFloorSubsidies(floorSubsidies);

		clientInfoService.edit(clientInfo);

		return BaseDto.getSuccessResult(null);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "editMyPassword", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> editMyPassword(HttpServletRequest request, Map<String, Object> map, int clientId, String oldPassword, String newPassword) {

		ClientInfo clientInfo = clientInfoService.findById(clientId);

		if (clientInfo == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_ACCOUNT);
		}

		if (StringUtils.nonEmpty(clientInfo.getPassword()) && (!clientInfo.getPassword().equals(oldPassword))) {
			return BaseDto.getFailResult(CommonCode.NOT_CORRECT_PASSWORD);
		}

		clientInfo.setPassword(newPassword);

		clientInfoService.edit(clientInfo);

		return BaseDto.getSuccessResult(null);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getClinetCardCodeByNowTime", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<String> getClinetCardCodeByNowTime(HttpServletRequest request, Map<String, Object> map, int clientId) {

		ClientInfo clientInfo = clientInfoService.findById(clientId);

		Long nowTime = new Date().getTime();

		StringBuffer sb = new StringBuffer();

		if (!StringUtils.nonEmpty(clientInfo.getCard_code())) {
			return BaseDto.getFailResult(CommonCode.USER_HAS_NOT_CARD_CODE);
		}

		sb.append(clientInfo.getCard_code()).append("^").append(nowTime);

		String qrCode = sb.toString();

		clientInfo.setWeixin_qr_code(qrCode);

		clientInfoService.edit(clientInfo);

		return BaseDto.getSuccessResult(qrCode);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "resetPasswordBySMS", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<AppClinentInfo> resetPasswordBySMS(HttpServletRequest request, Map<String, Object> map, String mobileTelNumber) {

		CommonCode commonCode = clientInfoService.resetPasswordBySMS(mobileTelNumber);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "bindingOpenId", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<AppClinentInfo> bindingOpenId(HttpServletRequest request, Map<String, Object> map, Integer clientId, String openId, String logiMobileTelNumber) {

		CommonCode commonCode = clientInfoService.bindingOpenId(clientId, openId, logiMobileTelNumber);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "relieveBindingOpenId", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> relieveBindingOpenId(HttpServletRequest request, Map<String, Object> map, Integer clientId) {

		CommonCode commonCode = clientInfoService.relieveBindingOpenId(clientId);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addActivityInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<AppClinentInfo> addActivityInfo(HttpServletRequest request, Map<String, Object> map, String telNumber, String name) {

		CommonCode commonCode = weChatActivityService.addActivityInfo(telNumber, name);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);

	}

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addClientInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<AppClinentInfo> addClientInfo(HttpServletRequest request, Map<String, Object> map, AppAddClientInfo appAddClientInfo) {

		CommonCode commonCode = clientInfoService.addWechatClientInfo(request,appAddClientInfo);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getClientInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<AppClinentInfo> getClientInfo(HttpServletRequest request, Map<String, Object> map, AppAddClientInfo appAddClientInfo) {

		CommonCode commonCode = clientInfoService.addWechatClientInfo(request,appAddClientInfo);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);

	}
}
