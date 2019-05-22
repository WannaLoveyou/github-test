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

import com.qian.entity.ComplaintType;
import com.qian.service.ComplaintTypeService;
import com.qian.vo.BaseDto;

@Controller
@RequestMapping("/complaintType")
public class ComplaintTypeController {
	
private static final String LIST = "complaintType/list";
	
	@Autowired
	private ComplaintTypeService complaintTypeService;
	
	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {
		return LIST;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, ComplaintType complaintType) {
		complaintTypeService.add(complaintType);
		return "ok";
	}
	

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	String edit(HttpServletRequest request, Map<String, Object> map, ComplaintType complaintType) {
		complaintTypeService.edit(complaintType);
		return "ok";
	}
	
	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	String del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		complaintTypeService.delByids(idString);
		return "ok";
	}
	
	@RequestMapping(value = "getPageList", method = RequestMethod.GET)
	public @ResponseBody 
	BaseDto<Map<String, Object>> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = complaintTypeService.getTotalNum();
		List<ComplaintType> list = complaintTypeService.getPageList(page, rows);
		result.put("total", total);
		result.put("rows", list);
		return BaseDto.getSuccessResult(result);
	}
	
	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody
	List<ComplaintType> getAllList(HttpServletRequest request, Map<String, Object> map) {
		List<ComplaintType> list = complaintTypeService.getAllList();
		return list;
	}
	
	
}
