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

import com.qian.entity.ClientType;
import com.qian.service.ClientTypeService;
import com.qian.vo.BaseDto;

@Controller
@RequestMapping("/clientType")
public class ClientTypeController {

	private static final String LIST = "clientType/list";

	@Autowired
	private ClientTypeService clientTypeService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {

		return LIST;
	}

	@RequestMapping(value = "getPageList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = clientTypeService.toallNum();
		List<ClientType> list = clientTypeService.getPageList(page, rows);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}

	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody
	List<ClientType> getAllList(Map<String, Object> map) {
		List<ClientType> clientTypes = clientTypeService.getAllList();
		map.put("clientTypes", clientTypes);
		return clientTypes;
	}

	@RequestMapping(value = "getNewAllList", method = RequestMethod.GET)
	public @ResponseBody
	BaseDto<List<ClientType>> getNewAllList(HttpServletRequest request, Map<String, Object> map) {
		List<ClientType> list = clientTypeService.getAllList();
		return BaseDto.getSuccessResult(list);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, ClientType clientType) {
		clientTypeService.addClienType(clientType);
		return "ok";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	String edit(HttpServletRequest request, Map<String, Object> map, ClientType clientType) {
		clientTypeService.editClienType(clientType);
		return "ok";
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	String del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		clientTypeService.delByIds(idString);
		return "ok";
	}
}