package com.qian.pc.control;

import java.util.HashMap;
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

import com.qian.code.DeliveryTypeCode;
import com.qian.entity.DeliveryType;
import com.qian.entity.User;
import com.qian.service.DeliveryTypeService;

@Controller
@RequestMapping("/deliveryType")
public class DeliveryTypeController {
	private static final String LIST = "deliveryType/list";

	@Autowired
	private DeliveryTypeService deliveryTypeService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {

		return LIST;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, DeliveryType deliveryType) {
		deliveryTypeService.add(deliveryType);
		return "ok";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	String edit(HttpServletRequest request, Map<String, Object> map, DeliveryType deliveryType) {
		deliveryTypeService.edit(deliveryType);
		return "ok";
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	String del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		deliveryTypeService.delByIds(idString);
		return "ok";
	}

	@RequestMapping(value = "getPageList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = deliveryTypeService.getTotalNum();
		List<DeliveryType> list = deliveryTypeService.getPageList(page, rows);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}

	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody
	List<DeliveryType> getAllList(HttpServletRequest request, Map<String, Object> map) {
		List<DeliveryType> list = deliveryTypeService.getAllList();
		return list;
	}

	@RequestMapping(value = "getMyDeliveryType", method = RequestMethod.GET)
	public @ResponseBody
	List<DeliveryType> getMyDeliveryType(HttpServletRequest request, Map<String, Object> map) {

		int deliveryTypeId = DeliveryTypeCode.ORDER_DELIVERY;

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if (user.getSecondCategory() != null) {
			deliveryTypeId = DeliveryTypeCode.PICKUP_IN_STORES;
		}

		List<DeliveryType> list = deliveryTypeService.getMyDeliveryType(deliveryTypeId);

		return list;
	}

}
