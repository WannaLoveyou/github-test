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

import com.qian.entity.FirstCategory;
import com.qian.service.FirstCategoryService;
import com.qian.vo.BaseDto;

/**
 * @author Lu Kongwen
 * @version Create time：2017-4-20 下午3:32:46
 * @Description
 */
@Controller
@RequestMapping("/firstCategory")
public class FirstCategoryController {

private static final String LIST = "category/first_category_list";
	
	@Autowired
	private FirstCategoryService firstService;
	
	
	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Map<String, Object> map) {
		return LIST;
	}

	@RequestMapping(value = "getPageList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = firstService.getTotalNum();
		List<FirstCategory> list = firstService.getPageList(page, rows);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}
	
	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody
	List<FirstCategory> getAllList(HttpServletRequest request, Map<String, Object> map) {
		List<FirstCategory> list = firstService.getAllList();
		return list;
	}
	@RequestMapping(value = "getNewAllList", method = RequestMethod.GET)
	public @ResponseBody
	BaseDto<List<FirstCategory>> getNewAllList(HttpServletRequest request, Map<String, Object> map) {
		List<FirstCategory> result = firstService.getAllList();
		return BaseDto.getSuccessResult(result);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, FirstCategory firstCategory) {
		firstService.add(firstCategory);
		return "ok";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	String edit(HttpServletRequest request, Map<String, Object> map,  FirstCategory firstCategory) {
		firstService.edit(firstCategory);
		return "ok";
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	String del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		firstService.delByIds(idString);
		return "ok";
	}
}
