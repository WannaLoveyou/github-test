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

import com.qian.entity.RepairType;
import com.qian.service.RepairTypeService;
import com.qian.vo.BaseDto;

@Controller
@RequestMapping("/repairType")
public class RepairTypeController {
	
	public static final String LIST="repairType/list";
	
	@Autowired
	private RepairTypeService repairTypeService;
	
	@RequiresUser
	@RequestMapping(value="list", method = RequestMethod.GET)
	public String list(HttpServletRequest request,Map<String, Object> map){
		return LIST;
	}
	
	@RequestMapping(value="getPageList",method = RequestMethod.GET)
	public @ResponseBody
	BaseDto<Map<String, Object>> getPageList(HttpServletRequest request,Map<String, Object> map,int page,int rows){
		Map<String, Object> result=new HashMap<String, Object>();
		Long total=repairTypeService.getTotalNum();
		List<RepairType> list=repairTypeService.getPageList(page, rows);
		result.put("total", total);
		result.put("rows", list);
		return BaseDto.getSuccessResult(result);
		
	}
	
	@RequestMapping(value="getAllList",method = RequestMethod.GET)
	public @ResponseBody
	List<RepairType> getAllList(HttpServletRequest request,Map<String, Object> map){
		List<RepairType> list=repairTypeService.getAllList();
		return list;
		
	}
	
	@RequestMapping(value="add",method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> add(HttpServletRequest request,RepairType repairType){
		repairTypeService.add(repairType);
		return BaseDto.getSuccessResult(null);
	}
	
	@RequestMapping(value="del",method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> delByids(HttpServletRequest request,String ids){
		String idString[] = ids.split(",");
		repairTypeService.delByids(idString);
		return BaseDto.getSuccessResult(null);
	}
	
	@RequestMapping(value="edit",method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> edit(HttpServletRequest request,RepairType repairType){
		repairTypeService.edit(repairType);
		return BaseDto.getSuccessResult(null);
	}
	
}
