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

import com.qian.entity.PartType;
import com.qian.service.PartTypeService;
import com.qian.vo.BaseDto;

@Controller
@RequestMapping("/partType")
public class PartTypeController {
	private static final String LIST = "partType/list";
	
	@Autowired
	private PartTypeService partTypeService;
	
	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {
		return LIST;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, PartType partType) {
		partTypeService.add(partType);
		return "ok";
	}
	

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	String edit(HttpServletRequest request, Map<String, Object> map, PartType partType) {
		partTypeService.edit(partType);
		return "ok";
	}
	
	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	String del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		partTypeService.delByIds(idString);
		return "ok";
	}
	
	@RequestMapping(value = "getPageList", method = RequestMethod.GET)
	public @ResponseBody 
	BaseDto<Map<String, Object>> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = partTypeService.getTotalNum();
		List<PartType> list = partTypeService.getPageList(page, rows);
		result.put("total", total);
		result.put("rows", list);
		return BaseDto.getSuccessResult(result);
	}
	
	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody
	BaseDto<List<PartType>> getAllList(HttpServletRequest request, Map<String, Object> map) {
		List<PartType> list = partTypeService.getAllList();
		return BaseDto.getSuccessResult(list);
	}
	
	
}
