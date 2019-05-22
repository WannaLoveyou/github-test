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

import com.qian.entity.FillingMedium;
import com.qian.service.FillingMediumService;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

@Controller
@RequestMapping("/fillingMedium")
public class FillingMediumController {

	private static final String LIST = "fillingMedium/list";

	@Autowired
	private FillingMediumService fillingMediumService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {

		return LIST;
	}

	@RequestMapping(value = "getPageList", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getPageList(HttpServletRequest request, Map<String, Object> map, int page,
			int rows) {
		List<String> l = new ArrayList<>();
		Field field = new Field();

		return fillingMediumService.getPageList(l, field, page, rows);
	}

	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody List<FillingMedium> getAllList(HttpServletRequest request, Map<String, Object> map) {
		return fillingMediumService.getAllList();
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody BaseDto<Object> add(FillingMedium fillingMedium) {
		return fillingMediumService.add(fillingMedium);
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody BaseDto<Object> edit(int entityId, FillingMedium fillingMedium) {
		return fillingMediumService.edit(entityId, fillingMedium);
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody BaseDto<Object> del(String ids) {
		String[] idsArr = ids.split(",");
		return fillingMediumService.delByIds(idsArr);
	}
}
