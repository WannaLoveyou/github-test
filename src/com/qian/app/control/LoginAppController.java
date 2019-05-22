package com.qian.app.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qian.app.entity.AppClinentInfo;
import com.qian.app.entity.AppUserInfo;
import com.qian.entity.ClientInfo;
import com.qian.entity.User;
import com.qian.entity.WeChatLoginInfo;
import com.qian.service.ClientInfoService;
import com.qian.service.UserService;
import com.qian.service.WeChatLoginInfoService;
import com.qian.util.StringUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-20 上午8:58:40
 * @Description
 */
@Controller
@RequestMapping("/app/login")
public class LoginAppController {
	
	private static Logger logger = Logger.getLogger(LoginAppController.class);

	@Autowired
	private ClientInfoService clientInfoService;

	@Autowired
	private WeChatLoginInfoService weChatLoginInfoService;

	@Autowired
	private UserService userService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "appLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<AppClinentInfo>> appLogin(HttpServletRequest request, Map<String, Object> map, String account, String password) {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		if (!StringUtils.nonEmpty(account)) {
			return BaseDto.getFailResult(CommonCode.NOT_CORRECT_PASSWORD);
		}

		if (!StringUtils.nonEmpty(password)) {
			return BaseDto.getFailResult(CommonCode.NOT_CORRECT_PASSWORD);
		}

		l.add("(mobile_tel_number_1 = ? or mobile_tel_number_2 = ? or fixed_tel_number_1 = ? or fixed_tel_number_2 = ?)");
		filed.addStr(account);
		filed.addStr(account);
		filed.addStr(account);
		filed.addStr(account);

		l.add("password = ? ");
		filed.addStr(password);

		List<ClientInfo> list = clientInfoService.getPageList(l, filed, 1, 1000);

		if (list.size() == 0) {
			return BaseDto.getFailResult(CommonCode.NOT_CORRECT_PASSWORD);
		}

		List<AppClinentInfo> result = new ArrayList<AppClinentInfo>();

		for (ClientInfo clientInfo : list) {

			AppClinentInfo appClinentInfo = new AppClinentInfo(clientInfo);

			result.add(appClinentInfo);
		}

		return BaseDto.getSuccessResult(result);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "appGetClientInfoByOpenid", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<AppClinentInfo>> appGetClientInfoByOpenid(HttpServletRequest request, Map<String, Object> map, String openid) {
		
		if (!StringUtils.nonEmpty(openid)) {
			return BaseDto.getFailResult(CommonCode.NOT_CORRECT_PASSWORD);
		}

		List<ClientInfo> list = clientInfoService.findByOpenid(openid);
		if (list.size() == 0) {
			return BaseDto.getFailResult(CommonCode.DATA_IS_NULL);
		}

		List<AppClinentInfo> result = new ArrayList<AppClinentInfo>();

		for (ClientInfo clientInfo : list) {

			AppClinentInfo appClinentInfo = new AppClinentInfo(clientInfo);

			result.add(appClinentInfo);
		}

		return BaseDto.getSuccessResult(result);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "appLoginByOpenId", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<AppClinentInfo> appLoginByOpenId(HttpServletRequest request, Map<String, Object> map, String openId) {

		WeChatLoginInfo weChatLoginInfo = weChatLoginInfoService.findByOpenId(openId);

		if (weChatLoginInfo == null) {
			return BaseDto.getFailResult(CommonCode.USER_NOT_EXIST);
		}

		ClientInfo clientInfo = weChatLoginInfo.getClientInfo();

		if (!weChatLoginInfo.getPassword().equals(clientInfo.getPassword())) {
			return BaseDto.getFailResult(CommonCode.USER_NOT_EXIST);
		}

		AppClinentInfo appClinentInfo = new AppClinentInfo(clientInfo);
		appClinentInfo.setLogiMobileTelNumber(weChatLoginInfo.getLogin_mobile_tel_number());

		return BaseDto.getSuccessResult(appClinentInfo);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "employeeLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<AppUserInfo> employeeLogin(HttpServletRequest request, Map<String, Object> map, String account, String password) {

		if (!StringUtils.nonEmpty(account)) {
			return BaseDto.getFailResult(CommonCode.NOT_CORRECT_PASSWORD);
		}

		if (!StringUtils.nonEmpty(password)) {
			return BaseDto.getFailResult(CommonCode.NOT_CORRECT_PASSWORD);
		}

		User user = userService.findByUserName(account);

		if (user == null) {
			return BaseDto.getFailResult(CommonCode.NOT_CORRECT_PASSWORD);
		}

		if (!password.equals(user.getPassword())) {
			return BaseDto.getFailResult(CommonCode.NOT_CORRECT_PASSWORD);
		}

		AppUserInfo appUserInfo = new AppUserInfo(user);
		return BaseDto.getSuccessResult(appUserInfo);

	}
	
	@RequestMapping(value = "bindingOpenid", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public BaseDto<Integer> bindingOpenid(Integer clientId,String phone,String openid){
		logger.error("clientId="+clientId+"phone="+phone+",openid="+openid);
		return clientInfoService.bindingOpenid(clientId,phone,openid);
	}

}
