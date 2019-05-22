package com.qian.pc.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.SecondCategory;
import com.qian.entity.User;
import com.qian.service.SecondCategoryService;
import com.qian.util.ExportHeadsUtils;
import com.qian.util.ExportUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.ExportOrderInfoReportHeads;

@Controller
@RequestMapping("/secondCategory")
public class SecondCategoryController {
	@Autowired
	
	private SecondCategoryService secondCategoryService;
	
	private static final String LIST = "category/second_category_list";
	
	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Map<String, Object> map) {
		return LIST;

	}
	
	@RequestMapping(value = "getMyList", method = RequestMethod.GET)
	public @ResponseBody
	List<SecondCategory> getMyList(Map<String, Object> map) {

		Subject subject = SecurityUtils.getSubject();

		User user = (User) subject.getPrincipal();

		List<SecondCategory> lists = secondCategoryService.getMyList(user);

		return lists;
	}
	
	@RequestMapping(value = "getPageList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = secondCategoryService.getTotalNum();
		List<SecondCategory> list = secondCategoryService.getPageList(page, rows);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}
	
	@RequestMapping(value = "exportAllList", method = RequestMethod.POST)
	public void exportAllList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {

		List<ExportOrderInfoReportHeads> heads = ExportHeadsUtils.getExportOrderInfoReportHeads(request);
		
		List<SecondCategory> list = secondCategoryService.getAllList();
		
		ExportUtils.exportExcel(response, heads, list, "门店信息表.xls", "template-StoreInfo.xls");

	}
	
	@RequestMapping(value = "getAllList", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	List<SecondCategory> getAllList(Map<String, Object> map) {
		List<SecondCategory> lists = secondCategoryService.getAllList();
		return lists;
	}
	
	
	@RequestMapping(value = "getListByFirstId", method = RequestMethod.GET)
	public @ResponseBody
	List<SecondCategory> getListByFirstId(Map<String, Object> map, int firstId) {
		List<SecondCategory> lists = secondCategoryService.getListByFirstId(firstId);
		return lists;
	}
	
	@RequestMapping(value = "getNewListByFirstId", method = RequestMethod.GET)
	public @ResponseBody
	BaseDto<List<SecondCategory>> getNewListByFirstId(Map<String, Object> map, int firstId) {
		List<SecondCategory> result = secondCategoryService.getListByFirstId(firstId);
		return BaseDto.getSuccessResult(result);
	}
	


	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, SecondCategory secondCategory) {
		secondCategoryService.add(secondCategory);
		return "ok";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	String edit(HttpServletRequest request, Map<String, Object> map, SecondCategory secondCategory) {
		secondCategoryService.edit(secondCategory);
		return "ok";
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	String del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		secondCategoryService.delByIds(idString);
		return "ok";
	}
}
