package com.qian.pc.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.StoreCustomAirBottlePriceInfo;
import com.qian.entity.User;
import com.qian.service.StoreCustomAirBottlePriceInfoService;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

@Controller
@RequestMapping("/storeCustomAirBottlePriceInfo")
public class StoreCustomAirBottlePriceInfoController {

	private static final String LIST = "storeCustomAirBottlePriceInfo/list";

	@Autowired
	private StoreCustomAirBottlePriceInfoService storeCustomAirBottlePriceInfoService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {
		return LIST;
	}
	
	@RequestMapping(value = "getPageList", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	BaseDto<Map<String, Object>> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<String> l = new ArrayList<String>();
		Field filed = new Field();
		ToolsBarUtils.getStoreCustomAirBottlePriceInfoSearchCondition(request, l, filed);

		Long total = storeCustomAirBottlePriceInfoService.getTotalNum(l, filed);
		List<StoreCustomAirBottlePriceInfo> list = storeCustomAirBottlePriceInfoService.getPageList(l, filed, page, rows);

		result.put("total", total);
		result.put("rows", list);

		return BaseDto.getSuccessResult(result);
	}
	
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> add(HttpServletRequest request, Map<String, Object> map, StoreCustomAirBottlePriceInfo storeCustomAirBottlePriceInfo) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		storeCustomAirBottlePriceInfo.setCreate_time(new Date());
		storeCustomAirBottlePriceInfo.setCreate_people(user.getFull_name());
		storeCustomAirBottlePriceInfo.setModify_time(new Date());
		storeCustomAirBottlePriceInfo.setModify_people(user.getFull_name());
		
		CommonCode commonCode = storeCustomAirBottlePriceInfoService.add(storeCustomAirBottlePriceInfo);

		return BaseDto.getUnKnowResult(null, commonCode);
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> edit(HttpServletRequest request, Map<String, Object> map, StoreCustomAirBottlePriceInfo storeCustomAirBottlePriceInfo) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		storeCustomAirBottlePriceInfo.setModify_time(new Date());
		storeCustomAirBottlePriceInfo.setModify_people(user.getFull_name());
		
		CommonCode commonCode = storeCustomAirBottlePriceInfoService.edit(storeCustomAirBottlePriceInfo);
		
		return BaseDto.getUnKnowResult(null, commonCode);
	}
	
	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		storeCustomAirBottlePriceInfoService.delByIds(idString);
		return BaseDto.getSuccessResult(null);
	}
}
