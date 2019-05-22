package com.qian.app.control;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.app.entity.AppAddOrder;
import com.qian.app.entity.AppOrderInfo;
import com.qian.code.ModuleConfigurationCode;
import com.qian.entity.ModuleConfigurationInfo;
import com.qian.entity.OrderInfo;
import com.qian.entity.WechatFirstOrderDiscountInfo;
import com.qian.service.ModuleConfigurationInfoService;
import com.qian.service.OrderService;
import com.qian.service.WechatFirstOrderDiscountInfoService;
import com.qian.util.JSONUtils;
import com.qian.util.PathUtils;
import com.qian.util.SignUtils;
import com.qian.util.TimeUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-21 下午4:18:07
 * @Description
 */
@Controller
@RequestMapping("/app/order")
public class OrderAppController {

	private static Logger log = Logger.getLogger(OrderAppController.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private ModuleConfigurationInfoService moduleConfigurationInfoService;

	@Autowired
	private WechatFirstOrderDiscountInfoService wechatFirstOrderDiscountInfoService;

	@RequestMapping(value = "getMyRecentOrderList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<AppOrderInfo>> getMyRecentOrderList(HttpServletRequest request, Map<String, Object> map, int clientId) {

		List<OrderInfo> list = orderService.getOrderInfoByLatest(clientId);

		List<AppOrderInfo> result = new ArrayList<AppOrderInfo>();

		for (OrderInfo orderInfo : list) {

			AppOrderInfo appOrderInfo = new AppOrderInfo(orderInfo);

			result.add(appOrderInfo);
		}

		Collections.sort(result, new Comparator<AppOrderInfo>() {
			public int compare(AppOrderInfo arg0, AppOrderInfo arg1) {
				if (TimeUtils.compareTimeByyyyyMMddHHmm(arg0.getOrderTime(), arg1.getOrderTime())) {
					return -1;
				} else {
					return 1;
				}
			}
		});

		int len = result.size() < 10 ? result.size() : 10;

		return BaseDto.getSuccessResult(result.subList(0, len));
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addAppOrder", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<AppOrderInfo> addAppOrder(HttpServletRequest request, Map<String, Object> map, AppAddOrder appAddOrder) {

		// 判断是否开启微信下单
		ModuleConfigurationInfo moduleConfigurationInfo = moduleConfigurationInfoService.findById(ModuleConfigurationCode.WEI_XIN);
		if (moduleConfigurationInfo.getIs_open() == 0) {
			return BaseDto.getFailResult(CommonCode.FUNCTION_NOT_OPEN);
		}

		OrderInfo orderInfo = orderService.addAppOrderInfo(appAddOrder);

		return BaseDto.getSuccessResult(new AppOrderInfo(orderInfo));

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "cancelAppOrder", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> cancelAppOrder(HttpServletRequest request, Map<String, Object> map, int orderId, String remark) {

		CommonCode commonCode = orderService.cancelAppOrder(orderId, remark);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "savePrepayId", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<AppOrderInfo> savePrepayId(HttpServletRequest request, Map<String, Object> map, int orderId, String prepayId) {

		CommonCode commonCode = orderService.savePrepayId(orderId, prepayId);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);

	}

	@RequestMapping(value = "weixinPaySuccess", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<AppOrderInfo> weixinPaySuccess(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {

		String inputLine;
		String notityXml = "";// 微信回调的报文（即回调结果）
		String resXml = "";// 返回给微信的报文
		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			request.getReader().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> resultMap = JSONUtils.xmlToJSON(notityXml);

		for (String key : resultMap.keySet()) {
			log.info("key=" + key + " value=" + resultMap.get(key));
		}

		boolean b = orderService.weixinPaySuccess(resultMap);

		if (b) {// 支付成功
			resXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml> ";
			// 此处修改订单状态

		} else {
			resXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml> ";
		}
		// 返回处理结果返回给微信端
		try {
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getWechatFirstOrderDiscountInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<ModuleConfigurationInfo> getWechatFirstOrderDiscountInfo(HttpServletRequest request, Map<String, Object> map, String openid) {

		// 判断是否开启微信首单优惠
		ModuleConfigurationInfo moduleConfigurationInfo = moduleConfigurationInfoService.findById(ModuleConfigurationCode.WECHAT_FIRST_ORDER_DISCOUNT);
		if (moduleConfigurationInfo.getIs_open() == 0) {
			return BaseDto.getFailResult(CommonCode.FUNCTION_NOT_OPEN);
		}

		WechatFirstOrderDiscountInfo wechatFirstOrderDiscountInfo = wechatFirstOrderDiscountInfoService.findByOpenId(openid);
		if (wechatFirstOrderDiscountInfo == null) {
			return BaseDto.getSuccessResult(moduleConfigurationInfo);
		}

		return BaseDto.getFailResult(CommonCode.HAS_NOT_MATCH_ACTIVE_RULE);

	}

}
