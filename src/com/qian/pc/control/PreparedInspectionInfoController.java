package com.qian.pc.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.User;
import com.qian.service.PreparedInspectionInfoService;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-12-15 下午4:50:46
 * @Description
 */
@Controller
@RequestMapping("/preparedInspectionInfo")
public class PreparedInspectionInfoController {

	private static final String LIST = "preparedInspectionInfo/list";

	@Autowired
	private PreparedInspectionInfoService preparedInspectionInfoService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list() {

		return LIST;
	}

	@RequestMapping(value = "getPageList")
	public @ResponseBody
	BaseDto<Map<String, Object>> getPageList(HttpServletRequest request, int page, int rows) {
		List<String> l = new ArrayList<String>();
		Field field = new Field();
		
		ToolsBarUtils.getPreparedInspectionInfoCondition(request, l, field);
		
		return preparedInspectionInfoService.getPageList(l, field, page, rows);
	}

	@RequestMapping(value = "createInspectionOrder")
	public @ResponseBody
	BaseDto<Object> createInspectionOrder(HttpServletRequest request) {
		List<String> l = new ArrayList<String>();
		Field field = new Field();
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		
		ToolsBarUtils.getPreparedInspectionInfoCondition(request, l, field);
		if (user.getWarehouseInfo() != null) {
			l.add("warehouseInfo.id = ?");
			field.addInt(user.getWarehouseInfo().getId());
		}
		return preparedInspectionInfoService.createInspectionOrder(l, field, user);
	}
	
	@RequestMapping(value = "delByIds")
	public @ResponseBody
	BaseDto<Object> delByIds(String ids) {
		
		return preparedInspectionInfoService.delByIds(ids);
	}
}
