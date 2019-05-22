/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.ketacustom.controller.IndexController.java
 * Class:			IndexController
 * Date:			2012-8-2
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package com.qian.pc.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qian.entity.Module;
import com.qian.entity.Permission;
import com.qian.entity.User;
import com.qian.service.ModuleService;


@Controller
@RequestMapping("/index")
public class IndexController {

	private static final String INDEX = "list";

	@Autowired
	private ModuleService moduleService;
	
	@RequiresUser
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Map<String, Object> map) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		List<Module> list = moduleService.findAll();
		List<Module> module = moduleService.findByNoParent();
		makeTree(list,subject,module);
		map.put("childList", module.get(0).getChildren());
		map.put("user", user);
		return INDEX;
	}
	
	private void makeTree(List<Module> modules, Subject subject,List<Module> parentModule) {
		if (parentModule.size()==0 || modules.isEmpty()) {
			return ;
		}
		List<Module> tmp = new ArrayList<Module>();
		List<Module> childTmp = new ArrayList<Module>();
		for(Module c1 : parentModule){
			c1.setChildren(new ArrayList<Module>(0));
		for (Module c2 : modules) {
			// 只加入拥有view权限的Module
			c2.setChildren(new ArrayList<Module>(0));
			if(c2.getParent().getId()==c1.getId()){
				childTmp.add(c2);
			}
			if (subject.isPermitted(c2.getSn() + ":"
					+ Permission.PERMISSION_READ) && c2.getParent().getId()==c1.getId()) {
				c1.getChildren().add(c2);
				tmp.add(c2);
			}
			
		}
		}
		modules.removeAll(childTmp);
		makeTree(modules,subject,tmp);
	}
	
	
	@RequiresPermissions("Security:save")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, Map<String, Object> map) {
		return INDEX;
	}
	
}
