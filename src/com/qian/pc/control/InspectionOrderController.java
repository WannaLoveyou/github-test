package com.qian.pc.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.InspectionOrderItemInfo;
import com.qian.entity.User;
import com.qian.service.InspectionOrderService;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-12-15 下午4:50:46
 * @Description
 */
@Controller
@RequestMapping("/inspectionOrder")
public class InspectionOrderController {

	private static final String LIST = "inspectionOrder/list";

	@Autowired
	private InspectionOrderService inspectionOrderService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list() {

		return LIST;
	}

	@RequestMapping(value = "getPageList")
	public @ResponseBody
	BaseDto<Map<String, Object>> getPageList(HttpServletRequest request, int page, int rows) {
		List<String> l = new ArrayList<String>();
		Field field = new Field();
		
		ToolsBarUtils.getInspectionOrderCondition(request, l, field);
		return inspectionOrderService.getPageList(l, field, page, rows);
	}

	@RequestMapping(value = "getItemsInOrder")
	public @ResponseBody
	List<InspectionOrderItemInfo> getItemsInOrder(int orderId) {
		
		return inspectionOrderService.getItemsInOrder(orderId);
	}
	
	@RequestMapping(value = "sendInspection")
	public @ResponseBody
	BaseDto<Object> sendInspection(int orderId) {
		
		return inspectionOrderService.sendInspection(orderId);
	}
	
	@RequestMapping(value = "refreshInspectionOrder")
	public @ResponseBody
	BaseDto<Object> refreshInspectionOrder(int orderId) {
		
		return inspectionOrderService.refreshInspectionOrder(orderId);
	}
	
	@RequestMapping(value = "refreshInspectionOrderDetails")
	public @ResponseBody
	BaseDto<Object> refreshInspectionOrderDetails(int orderId) {
		
		return inspectionOrderService.refreshInspectionOrderDetails(orderId);
	}
	
	@RequestMapping(value = "queryInspectionOrderDetailsResult")
	public @ResponseBody
	BaseDto<List<Map<String, Object>>> queryInspectionOrderDetailsResult(int orderDetailsId) {
		
		return inspectionOrderService.queryInspectionOrderDetailsResult(orderDetailsId);
	}
	
	@RequestMapping(value = "confirmInspectionOrder")
	public @ResponseBody
	BaseDto<Object> confirmInspectionOrder(int orderId) {
		
		return inspectionOrderService.confirmInspectionOrder(orderId);
	}
	
	@RequestMapping(value = "initInspectionOrderData")
	public @ResponseBody
	BaseDto<Object> initInspectionOrderData(int orderId) {
		
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		
		return inspectionOrderService.initInspectionOrderData(orderId,user);
	}
}
