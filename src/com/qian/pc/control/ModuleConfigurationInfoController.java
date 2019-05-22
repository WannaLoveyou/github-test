package com.qian.pc.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.ModuleConfigurationInfo;
import com.qian.service.ModuleConfigurationInfoService;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-9-25 上午10:37:45
 * @Description
 */
@Controller
@RequestMapping("/moduleConfigurationInfo")
public class ModuleConfigurationInfoController {

	private static final String LIST = "moduleConfigurationInfo/list";

	@Autowired
	private ModuleConfigurationInfoService moduleConfigurationInfoService;
	
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
		ToolsBarUtils.getModuleConfigurationInfoSearchCondition(request, l, filed);

		Long total = moduleConfigurationInfoService.getTotalNum(l, filed);
		List<ModuleConfigurationInfo> list = moduleConfigurationInfoService.getPageList(l, filed, page, rows);

		result.put("total", total);
		result.put("rows", list);

		return BaseDto.getSuccessResult(result);
	}
	
	@RequestMapping(value = "editModuleConfigurationInfo", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	BaseDto<Map<String, Object>> editModuleConfigurationInfo(HttpServletRequest request, Map<String, Object> map, int moduleId, int isOpen) {

		ModuleConfigurationInfo moduleConfigurationInfo = moduleConfigurationInfoService.findById(moduleId);
		moduleConfigurationInfo.setIs_open(isOpen);
		moduleConfigurationInfoService.edit(moduleConfigurationInfo);

		return BaseDto.getSuccessResult(null);
	}
	
}
