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

import com.qian.service.ClientInventoryInfoService;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CoordinateVo;
import com.qian.vo.Field;

import net.sf.json.JSONArray;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-10 上午9:05:58
 * @Description
 */

@Controller
@RequestMapping("/showReport")
public class ShowReportController {

	private static final String LIST = "showReport/list";
	private static final String CLIENT_COORDINATES_MAP = "showReport/client_coordinates_map";

	@Autowired
	private ClientInventoryInfoService clientInventoryInfoService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {

		return LIST;
	}

	@RequestMapping(value = "showMap", method = RequestMethod.GET)
	public String showMap(Map<String, Object> map, String create_time_begin_time, String create_time_end_time) {
		map.put("create_time_begin_time", create_time_begin_time);
		map.put("create_time_end_time", create_time_end_time);
		return CLIENT_COORDINATES_MAP;
	}

	@RequestMapping(value = "getClientCoordinates")
	public @ResponseBody BaseDto<List<CoordinateVo>> getClientCoordinates(HttpServletRequest request) {
		List<String> l = new ArrayList<>();
		Field field = new Field();
		ToolsBarUtils.getClientInfoSearchCondition(request, l, field);
		l.add("locationState.id != null");
		return clientInventoryInfoService.getClientCoordinates(l, field);
	}
}
