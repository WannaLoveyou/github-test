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

import com.qian.entity.ProductionUnit;
import com.qian.service.ProductionUnitService;

/**
 * @author Lu Kongwen
 * @version Create time：2016-6-16 下午2:51:59
 * @Description
 */
@Controller
@RequestMapping("/productionUnit")
public class ProductionUnitController {

	private static final String LIST = "productionUnit/list";

	@Autowired
	private ProductionUnitService productionUnitService;
	
	
	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {
		
		
		return LIST;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, ProductionUnit productionUnit) {
		productionUnitService.add(productionUnit);
		return "ok";
	}
	

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	String edit(HttpServletRequest request, Map<String, Object> map, ProductionUnit productionUnit) {
		productionUnitService.edit(productionUnit);
		return "ok";
	}
	
	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	String del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		productionUnitService.delByIds(idString);
		return "ok";
	}
	
	
	
	@RequestMapping(value = "getPageList")
	public @ResponseBody
	Map<String, Object> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = productionUnitService.getTotalNum();
		List<ProductionUnit> list = productionUnitService.getPageList(page, rows);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}
	
	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody
	List<ProductionUnit> getAllList(HttpServletRequest request, Map<String, Object> map) {
		List<ProductionUnit> list = productionUnitService.getAllList();
		return list;
	}
	
}
