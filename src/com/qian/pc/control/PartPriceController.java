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

import com.qian.entity.PartPrice;
import com.qian.service.PartPriceService;
import com.qian.vo.BaseDto;

@Controller
@RequestMapping("/partPrice")
public class PartPriceController {
	
	private static final String LIST = "partPrice/list";
	@Autowired
	private PartPriceService partPriceService;
	
	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {
		return LIST;
	}

	@RequestMapping(value="getPageList",method = RequestMethod.GET)
	public @ResponseBody
	BaseDto<Map<String, Object>> getPageList(HttpServletRequest request,Map<String, Object> map,int page,int rows){
		Map<String, Object> result=new HashMap<String, Object>();
		Long total=partPriceService.getTotalNum();
		List<PartPrice> list=partPriceService.getPageList(page, rows);
		result.put("total", total);
		result.put("rows", list);
		return BaseDto.getSuccessResult(result);
	} 
	@RequestMapping(value = "getAllList", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	BaseDto<List<PartPrice>> getAllList(HttpServletRequest request, Map<String, Object> map) {
		List<PartPrice> result = partPriceService.getAllList();
		
		return BaseDto.getSuccessResult(result);
	}
	
	@RequestMapping(value = "getAllList1", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	List<PartPrice> getAllList1(HttpServletRequest request, Map<String, Object> map) {
		List<PartPrice> result = partPriceService.getAllList();
		
		return result;
	}
	
	@RequestMapping(value = "getListByBottleType", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	List<PartPrice> getListByBottleType(HttpServletRequest request, Map<String, Object> map , int bottleTypeId) {
		
		List<PartPrice> result = partPriceService.getListByBottleType(bottleTypeId);
		
		return result;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> add(HttpServletRequest request, Map<String, Object> map, PartPrice partPrice) {
		partPriceService.add(partPrice);
		return BaseDto.getSuccessResult(null);
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> edit(HttpServletRequest request, Map<String, Object> map, PartPrice partPrice) {
		partPriceService.edit(partPrice);
		return BaseDto.getSuccessResult(null);
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		partPriceService.delByIds(idString);
		return BaseDto.getSuccessResult(null);
	}
}