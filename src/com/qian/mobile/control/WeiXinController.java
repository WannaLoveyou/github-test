package com.qian.mobile.control;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qian.entity.ClientInfo;
import com.qian.service.ClientInfoService;
import com.qian.util.StringUtils;
import com.qian.util.Test;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-7-26 下午3:27:34
 * @Description
 */
@Controller
@RequestMapping("/mobile/weiXin")
public class WeiXinController {

	@Autowired
	private ClientInfoService clientInfoService;

	private static final String WEIXIN_LOGIN_LIST = "wechat/weixin_login";

	private static final String MY_CARD_CODE_INFO_LIST = "wechat/my_card_code_Info_list";

	private static final String INDEX = "wechat/index";

	@RequestMapping(value = "weiXinLogin", method = RequestMethod.GET)
	public String weiXinLogin(HttpServletRequest request, Map<String, Object> map) {

		return WEIXIN_LOGIN_LIST;
	}

	@RequestMapping(value = "clinetInfoIndex", method = RequestMethod.POST)
	public ModelAndView clinetInfoIndex(HttpServletRequest request, Map<String, Object> map, String account, String password) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		if (StringUtils.nonEmpty(account)) {
			l.add("(mobile_tel_number_1 = ? or mobile_tel_number_2 = ? or fixed_tel_number_1 = ? or fixed_tel_number_2 = ?)");
			filed.addStr(account);
			filed.addStr(account);
			filed.addStr(account);
			filed.addStr(account);
		} else {

			result.put("errorMsg", CommonCode.NOT_CORRECT_PASSWORD.getMsg());

			return new ModelAndView(WEIXIN_LOGIN_LIST, result);
		}

		if (StringUtils.nonEmpty(password)) {

			l.add("password = ? ");
			filed.addStr(password);

		} else {
			result.put("errorMsg", CommonCode.NOT_CORRECT_PASSWORD.getMsg());

			return new ModelAndView(WEIXIN_LOGIN_LIST, result);
		}

		List<ClientInfo> list = clientInfoService.getPageList(l, filed, 1, 1000);

		if (list.size() == 0) {
			result.put("errorMsg", CommonCode.NOT_CORRECT_PASSWORD.getMsg());

			return new ModelAndView(WEIXIN_LOGIN_LIST, result);
		}

		if (list.size() == 1) {

			result.put("clientInfo", list.get(0));

			return new ModelAndView(MY_CARD_CODE_INFO_LIST, result);

		} else {
			result.put("list", list);

			return new ModelAndView(INDEX, result);
		}

	}

	@RequestMapping(value = "selectClinetInfo", method = RequestMethod.GET)
	public ModelAndView selectClinetInfo(HttpServletRequest request, Map<String, Object> map, int clientId) {

		ClientInfo clientInfo = clientInfoService.findById(clientId);

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("clientInfo", clientInfo);

		return new ModelAndView(MY_CARD_CODE_INFO_LIST, result);
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

	@RequestMapping(value = "getMyCardCodeInfoList", method = RequestMethod.GET)
	public String getMyCardCodeInfoList(HttpServletRequest request, Map<String, Object> map) {

		return MY_CARD_CODE_INFO_LIST;
	}

	@RequestMapping(value = "getWeiXinCode", method = { RequestMethod.GET, RequestMethod.POST })
	public BaseDto<String> getWeiXinCode(HttpServletRequest request, Map<String, Object> map) {

		String url = "https://open.weixin.qq.com/connect/qrconnect";

		StringBuffer sb = new StringBuffer();
		sb.append("appid").append("wx8195b7beb722c9c5").append("&");
		sb.append("redirect_uri").append(URLEncoder.encode("http://127.0.0.1:8080/energymanage/weiXin/getWeiXinCode")).append("&");
		sb.append("response_type").append("code").append("&");
		sb.append("scope").append("snsapi_login").append("&");
		sb.append("state").append("STATE#wechat_redirect").append("&");

		String sr = Test.sendPost(url, sb.toString());
		return BaseDto.getSuccessResult(sr);

	}
}
