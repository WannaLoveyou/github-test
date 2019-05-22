package com.qian.mobile.control;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.app.entity.AppAddOrder;
import com.qian.entity.OrderInfo;
import com.qian.mobile.entity.MobileOrderInfo;
import com.qian.service.OrderService;
import com.qian.vo.BaseDto;

/**
 * @author Lu Kongwen
 * @version Create time：2019-1-14 下午4:59:47
 * @Description
 */
@Controller
@RequestMapping("/mobile/order")
public class OrderMobileController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "addMobileOrder", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<MobileOrderInfo> addMobileOrder(HttpServletRequest request, Map<String, Object> map, AppAddOrder appAddOrder) {

		OrderInfo orderInfo = orderService.addMobileOrderInfo(appAddOrder);

		return BaseDto.getSuccessResult(new MobileOrderInfo(orderInfo));

	}
	
	@RequestMapping(value = "dispatch", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> dispatch(HttpServletRequest request, Map<String, Object> map, int orderId , int deliveryManId) {
		
		return orderService.addMobileDispatch(orderId,deliveryManId);
	}
}
