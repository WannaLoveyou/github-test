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

import com.qian.entity.FloorSubsidies;
import com.qian.service.FloorSubsidiesService;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-9 上午11:03:04
 * @Description
 */
@Controller
@RequestMapping("/floorSubsidies")
public class FloorSubsidiesController {

	private static final String LIST = "floorSubsidies/list";
	@Autowired
	private FloorSubsidiesService floorSubsidiesService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Map<String, Object> map) {
		return LIST;
	}

	@RequestMapping(value = "getPageList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = floorSubsidiesService.getTotalNum();
		List<FloorSubsidies> list = floorSubsidiesService.getPageList(page, rows);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}
	
	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody List<FloorSubsidies> selectList(Map<String, Object> map) {
		List<FloorSubsidies> floorSubsidiess = floorSubsidiesService.getAllList();
		map.put("floorSubsidiess", floorSubsidiess);
		return floorSubsidiess;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, FloorSubsidies floorSubsidies) {
		floorSubsidiesService.add(floorSubsidies);
		return "ok";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	String edit(HttpServletRequest request, Map<String, Object> map, FloorSubsidies floorSubsidies) {
		floorSubsidiesService.edit(floorSubsidies);
		return "ok";
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	String del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		floorSubsidiesService.delByIds(idString);
		return "ok";
	}

}
