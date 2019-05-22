package com.qian.pc.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.AirBottleBelong;
import com.qian.service.AirBottleBelongService;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2018-2-2 下午3:33:09
 * @Description
 */
@Controller
@RequestMapping("/airBottleBelong")
public class AirBottleBelongController {

	private static final String LIST = "airBottleBelong/list";

	@Autowired
	private AirBottleBelongService airBottleBelongService;
	
	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {
		return LIST;
	}
	
	@RequestMapping(value = "getPageList", method = RequestMethod.POST)
	public @ResponseBody BaseDto<Map<String, Object>> getPageList(HttpServletRequest request, Map<String, Object> map,
			int page, int rows) {

		List<String> l = new ArrayList<String>();
		Field field = new Field();

		ToolsBarUtils.getAirBottleBelongSearchCondition(request, l, field);

		return airBottleBelongService.getPageList(l, field, page, rows);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody BaseDto<Object> add(HttpServletRequest request, Map<String, Object> map, AirBottleBelong airBottleBelong) {

		return airBottleBelongService.add(airBottleBelong);
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody BaseDto<Object> edit(HttpServletRequest request, Map<String, Object> map, AirBottleBelong airBottleBelong,
			int entityId) {

		return airBottleBelongService.edit(airBottleBelong, entityId);
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody BaseDto<Object> del(HttpServletRequest request, Map<String, Object> map, String ids) {

		return airBottleBelongService.delByIds(ids);
	}
	
	@RequestMapping(value = "getAllList")
	public @ResponseBody List<AirBottleBelong> getPageList(HttpServletRequest request, Map<String, Object> map) {
		return airBottleBelongService.getAllList();
	}
}
