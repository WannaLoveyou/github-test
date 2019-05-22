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

import com.qian.cache.MobileLoginCache;
import com.qian.entity.Module;
import com.qian.entity.User;
import com.qian.mobile.entity.MobileLoginUser;
import com.qian.mobile.entity.MobileModule;
import com.qian.service.ModuleService;
import com.qian.service.UserService;
import com.qian.util.StringUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;

/**
 * @author Lu Kongwen
 * @version Create time：2016-1-20 上午9:34:22
 * @Description
 */
@Controller
@RequestMapping("/mobile/login")
public class LoginMoblieController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModuleService moduleService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "login", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileLoginUser> login(HttpServletRequest request, Map<String, Object> map, String username, String password, String deviceCode) {

		User user = userService.findByUserName(username);

		if (user == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_ACCOUNT);
		}

		if (!(user.getPassword().equals(password))) {
			return BaseDto.getFailResult(CommonCode.NOT_CORRECT_PASSWORD);
		}

		if (deviceCode == null) {
			return BaseDto.getFailResult(CommonCode.NOT_HAS_DEVICE_CODE);
		}

		if (!MobileLoginCache.checkLimitLogin(user, deviceCode)) {
			return BaseDto.getFailResult(CommonCode.HAS_ALREAY_LOGIN);
		}

		List<Module> modules = moduleService.findMobileModuleByUser(user);

		List<MobileModule> mobileModules = new ArrayList<MobileModule>();

		for (Module module : modules) {

			MobileModule mobileModule = new MobileModule(module);

			mobileModules.add(mobileModule);
		}

		MobileLoginUser mobileLoginUser = new MobileLoginUser(user, mobileModules);

		return BaseDto.getSuccessResult(mobileLoginUser);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "loginByCardCode", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileLoginUser> loginByCardCode(HttpServletRequest request, Map<String, Object> map, String cardCode, String deviceCode) {

		if (!StringUtils.nonEmpty(cardCode)) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_ACCOUNT);
		}

		User user = userService.findByCardCode(cardCode);

		if (user == null) {
			return BaseDto.getFailResult(CommonCode.NOT_EXIST_ACCOUNT);
		}

		if (deviceCode == null) {
			return BaseDto.getFailResult(CommonCode.NOT_HAS_DEVICE_CODE);
		}

		if (!MobileLoginCache.checkLimitLogin(user, deviceCode)) {
			return BaseDto.getFailResult(CommonCode.HAS_ALREAY_LOGIN);
		}

		List<Module> modules = moduleService.findMobileModuleByUser(user);

		List<MobileModule> mobileModules = new ArrayList<MobileModule>();

		for (Module module : modules) {

			MobileModule mobileModule = new MobileModule(module);

			mobileModules.add(mobileModule);
		}

		MobileLoginUser mobileLoginUser = new MobileLoginUser(user, mobileModules);

		return BaseDto.getSuccessResult(mobileLoginUser);
	}

	@RequestMapping(value = "logOut", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileLoginUser> logOut(HttpServletRequest request, Map<String, Object> map, int userId) {

		MobileLoginCache.clearLimitLogin(userId);

		return BaseDto.getSuccessResult(null);

	}
}
