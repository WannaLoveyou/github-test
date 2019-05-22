package com.qian.pc.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.RepairInfoState;
import com.qian.service.RepairInfoStateService;

@Controller
@RequestMapping("/repairInfoState")
public class RepairInfoStateController {
	
	public static final String LIST="repairInfoState/list";
	
	@Autowired
	private RepairInfoStateService repairInfoStateService;
	
	@RequiresUser
	@RequestMapping(value="list", method = RequestMethod.GET)
	public String list(HttpServletRequest request,Map<String, Object> map){
		return LIST;
	}
	

	@RequestMapping(value="getAllList",method = RequestMethod.GET)
	public @ResponseBody
	List<RepairInfoState> getAllList(HttpServletRequest request,Map<String, Object> map){
		List<RepairInfoState> list=repairInfoStateService.getAllList();
		return list;
		
	}
	
	
}
