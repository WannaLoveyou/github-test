package com.qian.pc.control;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.qian.entity.VisitInfo;
import com.qian.entity.User;
import com.qian.service.VisitInfoService;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.Field;

@Controller
@RequestMapping("visit")
public class VisitInfoController {
	
	private static final String LIST = "visit/list";
	
	@Autowired
	private VisitInfoService visitInfoService;
	
	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {
		
		return LIST;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, VisitInfo visitInfo) {
		
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		
		visitInfo.setOperator(user);
		
		visitInfoService.add(visitInfo);
		return "ok";
	}
	
	@RequestMapping(value = "getVisitInfo", method = RequestMethod.POST)
	public @ResponseBody
	List<VisitInfo> getVisitInfo(HttpServletRequest request, Map<String, Object> map, int clientId){
		List<VisitInfo> list= visitInfoService.getVisitInfo(clientId);
		return list;
	}
	
	@RequestMapping(value = "getVisitInfoAllList", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getVisitInfoAllList(HttpServletRequest request, Map<String, Object> map, int page, int rows){
		
		List<String> l = new ArrayList<String>();
		Field filed = new Field();
		
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if (user.getSecondCategory() != null) {
			l.add("secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId()); // 取对应门店
		}
		
		ToolsBarUtils.getVisitInfoSearchCondition(request, l, filed);
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = visitInfoService.getTotalNum(l, filed);
		List<VisitInfo> list = visitInfoService.getVisitPageList(l, filed, page, rows);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}

}
