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

import com.qian.entity.CardMoneyRechargeMoneyType;
import com.qian.service.CardMoneyRechargeMoneyTypeService;

/**
 * @author Lu Kongwen
 * @version Create time：2016-8-4 上午11:49:18
 * @Description
 */
@Controller
@RequestMapping("/cardMoneyRechargeMoneyType")
public class CardMoneyRechargeMoneyTypeController {

	private static final String LIST = "cardMoneyRechargeMoneyType/list";

	@Autowired
	private CardMoneyRechargeMoneyTypeService cardMoneyRechargeMoneyTypeService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {

		return LIST;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, CardMoneyRechargeMoneyType cardMoneyRechargeMoneyType) {
		cardMoneyRechargeMoneyTypeService.add(cardMoneyRechargeMoneyType);
		return "ok";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	String edit(HttpServletRequest request, Map<String, Object> map, CardMoneyRechargeMoneyType cardMoneyRechargeMoneyType) {
		cardMoneyRechargeMoneyTypeService.edit(cardMoneyRechargeMoneyType);
		return "ok";
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	String del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		cardMoneyRechargeMoneyTypeService.delByIds(idString);
		return "ok";
	}

	@RequestMapping(value = "getPageList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = cardMoneyRechargeMoneyTypeService.getTotalNum();
		List<CardMoneyRechargeMoneyType> list = cardMoneyRechargeMoneyTypeService.getPageList(page, rows);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}

	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody
	List<CardMoneyRechargeMoneyType> getAllList(HttpServletRequest request, Map<String, Object> map) {
		List<CardMoneyRechargeMoneyType> list = cardMoneyRechargeMoneyTypeService.getAllList();
		return list;
	}
}
