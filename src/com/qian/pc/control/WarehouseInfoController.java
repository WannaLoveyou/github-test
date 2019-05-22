package com.qian.pc.control;

import java.util.ArrayList;
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

import com.qian.entity.User;
import com.qian.entity.WarehouseInfo;
import com.qian.service.WarehouseInfoService;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-2-18 下午5:22:19
 * @Description
 */
@Controller
@RequestMapping("/warehouseInfo")
public class WarehouseInfoController {

	private static final String LIST = "warehouseInfo/list";

	@Autowired
	private WarehouseInfoService warehouseInfoService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {

		return LIST;
	}

	@RequestMapping(value = "getPageList", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Map<String, Object>> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {

		List<String> l = new ArrayList<String>();
		Field field = new Field();

		ToolsBarUtils.getWarehouseInfoSearchCondition(request, l, field);

		Map<String, Object> result = new HashMap<String, Object>();

		Long total = warehouseInfoService.getTotalNum(l, field);
		List<WarehouseInfo> list = warehouseInfoService.getPageList(l, field, page, rows);

		result.put("total", total);
		result.put("rows", list);
		return BaseDto.getSuccessResult(result);
	}

	@RequestMapping(value = "getAllList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	List<WarehouseInfo> getAllList(Map<String, Object> map) {
		
		List<WarehouseInfo> lists = warehouseInfoService.getAllList();
		
		return lists;
	}
	
	@RequestMapping(value = "getMyList", method = RequestMethod.GET)
	public @ResponseBody
	List<WarehouseInfo> getMyList(Map<String, Object> map) {

		Subject subject = SecurityUtils.getSubject();

		User user = (User) subject.getPrincipal();

		List<WarehouseInfo> lists = warehouseInfoService.getMyList(user);

		return lists;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> add(HttpServletRequest request, Map<String, Object> map, WarehouseInfo warehouseInfo) {

		CommonCode commonCode = warehouseInfoService.add(warehouseInfo);

		return BaseDto.getUnKnowResult(null,commonCode);
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> edit(HttpServletRequest request, Map<String, Object> map, WarehouseInfo warehouseInfo, int entityId) {

		CommonCode commonCode = warehouseInfoService.edit(warehouseInfo, entityId);

		return BaseDto.getUnKnowResult(null,commonCode);
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> del(HttpServletRequest request, Map<String, Object> map, String ids) {

		CommonCode commonCode = warehouseInfoService.delByIds(ids);

		return BaseDto.getUnKnowResult(null,commonCode);
	}
	
}
