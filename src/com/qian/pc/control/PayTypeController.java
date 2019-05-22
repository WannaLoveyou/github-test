package com.qian.pc.control;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.PayType;
import com.qian.service.PayTypeService;

@Controller
@RequestMapping("/payType")
public class PayTypeController {
	
	private static final String LIST = "payType/list";
	
	@Autowired
	private PayTypeService payTypeService;
	
	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {
		
		return LIST;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, PayType payType) {
		payTypeService.add(payType);
		return "ok";
	}
	
	
	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	String del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		payTypeService.delByIds(idString);
		return "ok";
	}
	

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	String edit(HttpServletRequest request, Map<String, Object> map, PayType payType) {
		payTypeService.edit(payType);
		return "ok";
	}
	
	@RequestMapping(value = "getPageList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = payTypeService.getTotalNum();
		List<PayType> list = payTypeService.getPageList(page, rows);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}
	
	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody
	List<PayType> getAllList(HttpServletRequest request, Map<String, Object> map) {
		List<PayType> list = payTypeService.getAllList();
		return list;
	}
}
