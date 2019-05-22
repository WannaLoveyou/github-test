package com.qian.mobile.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.app.entity.AppAddClientInfo;
import com.qian.entity.ClientInfo;
import com.qian.mobile.entity.MobileClientInfo;
import com.qian.service.ClientInfoService;
import com.qian.service.OrderService;
import com.qian.service.UserService;
import com.qian.util.StringUtils;
import com.qian.util.TimeUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

@Controller
@RequestMapping("/mobile/client")
public class ClientInfoMoblieController {

	public static final int USER_QRCODE_CARD_CODE_OVERDUE_TIME = 5;

	public static final String ANONYMOUS_CLIENT_CARD_CODE = "00";

	@Autowired
	private UserService userService;

	@Autowired
	private ClientInfoService clientInfoService;

	@Autowired
	private OrderService orderService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "checkClientOnlyByCardCode", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileClientInfo> checkClientOnlyByCardCode(HttpServletRequest request, Map<String, Object> map, String cardCode) {

		String qrCode = null;

		int index = cardCode.indexOf("^");

		if (index != -1) {

			qrCode = cardCode;

			String time = cardCode.substring(index + 1, cardCode.length());
			String code = cardCode.substring(0, index);

			long minutes = TimeUtils.getDistanceMinutes(Long.parseLong(time), new Date().getTime());

			if (minutes >= USER_QRCODE_CARD_CODE_OVERDUE_TIME) {
				return BaseDto.getFailResult(CommonCode.USER_QRCODE_CARD_CODE_OVERDUE);
			} else {
				cardCode = code;
			}
		}

		ClientInfo clientInfo = clientInfoService.getClientInfoByCardCode(cardCode);

		if (index != -1) {
			if (!qrCode.equals(clientInfo.getWeixin_qr_code())) {
				return BaseDto.getFailResult(CommonCode.USER_QRCODE_CARD_CODE_OVERDUE);
			} else {
				clientInfo.setWeixin_qr_code(null);
				clientInfoService.edit(clientInfo);
			}
		}

		if (clientInfo == null) {
			return BaseDto.getFailResult(CommonCode.CARD_INFO_EMPTY);
		}

		MobileClientInfo mobileClinentInfo = new MobileClientInfo(clientInfo);

		return BaseDto.getSuccessResult(mobileClinentInfo);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getAnonymousClientInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileClientInfo> getAnonymousClientInfo(HttpServletRequest request, Map<String, Object> map) {

		ClientInfo clientInfo = clientInfoService.getClientInfoByCardCode(ANONYMOUS_CLIENT_CARD_CODE);

		if (clientInfo == null) {
			return BaseDto.getFailResult(CommonCode.CAN_NOT_FIND_ANONYMOUS_CLIENT);
		}

		MobileClientInfo mobileClinentInfo = new MobileClientInfo(clientInfo);

		return BaseDto.getSuccessResult(mobileClinentInfo);

	}

	@RequestMapping(value = "addClientInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileClientInfo> addClientInfo(HttpServletRequest request, Map<String, Object> map, AppAddClientInfo appAddClientInfo) {

		ClientInfo clientInfo = clientInfoService.addMobileClientInfo(request, appAddClientInfo);

		return BaseDto.getSuccessResult(new MobileClientInfo(clientInfo));

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getByTelNumber", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<MobileClientInfo>> getByTelNumber(HttpServletRequest request, Map<String, Object> map, String telNumber) {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		if (!StringUtils.nonEmpty(telNumber)) {
			return BaseDto.getFailResult(CommonCode.NOT_CORRECT_PASSWORD);
		}

		l.add("(mobile_tel_number_1 = ? or mobile_tel_number_2 = ? or fixed_tel_number_1 = ? or fixed_tel_number_2 = ?)");
		filed.addStr(telNumber);
		filed.addStr(telNumber);
		filed.addStr(telNumber);
		filed.addStr(telNumber);

		List<ClientInfo> list = clientInfoService.getPageList(l, filed, 1, 1000);

		if (list.size() == 0) {
			return BaseDto.getFailResult(CommonCode.USER_NOT_EXIST);
		}

		List<MobileClientInfo> result = new ArrayList<MobileClientInfo>();

		for (ClientInfo clientInfo : list) {

			MobileClientInfo mobileClientInfo = new MobileClientInfo(clientInfo);

			result.add(mobileClientInfo);
		}

		return BaseDto.getSuccessResult(result);
	}
}
