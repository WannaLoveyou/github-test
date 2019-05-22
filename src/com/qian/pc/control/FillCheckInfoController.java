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

import com.qian.entity.FillCheckInfo;
import com.qian.service.FillCheckInfoService;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

@Controller
@RequestMapping("/fillCheckInfo")
public class FillCheckInfoController {

	private static final String LIST = "fillCheckInfo/list";

	@Autowired
	private FillCheckInfoService fillCheckInfoService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {

		return LIST;
	}

	@RequestMapping(value = "getPageList", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getList(HttpServletRequest request, int page, int rows) {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		return fillCheckInfoService.getPageList(l, filed, page, rows);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody BaseDto<Object> add(HttpServletRequest request, FillCheckInfo fillCheckInfo) {

		return fillCheckInfoService.add(fillCheckInfo);
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody BaseDto<Object> edit(FillCheckInfo fillCheckInfo, int entityId) {

		return fillCheckInfoService.edit(fillCheckInfo, entityId);
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody BaseDto<Object> del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		return fillCheckInfoService.delByIds(idString);
	}

}
