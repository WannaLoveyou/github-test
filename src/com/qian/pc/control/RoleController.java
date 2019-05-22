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

import com.qian.entity.Module;
import com.qian.entity.Role;
import com.qian.service.ModuleService;
import com.qian.service.RoleService;

/**
 * @author lizhaoyang
 * @version Create time：2015-11-13 上午9:05:58
 * @Description
 */

@Controller
@RequestMapping("/role")
public class RoleController {

	private static final String LIST = "role/list";
	private static final String EDIT = "role/edit";
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ModuleService moduleService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Map<String, Object> map) {
		return LIST;
	}

	@RequestMapping(value = "getList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Role> list = roleService.findAll(page, rows);
		long total = roleService.getAllCount();
		result.put("total", total);
		result.put("rows", list);
		return result;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, Role role) {
		roleService.saveRole(role);
		return "ok";
	}
	
	@RequestMapping(value = "editPage", method = RequestMethod.GET)
	public  String editPage(HttpServletRequest request, Map<String, Object> map,int roleId) {
		Role role = roleService.findById(roleId);
		Module parent = moduleService.findByNoParent().get(0);
		map.put("role", role);
		map.put("module", parent);
		return EDIT;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public @ResponseBody String edit( Role role,String permissionIds) {
		Role oldRole = roleService.findById(role.getId());
		oldRole.setName(role.getName());
		oldRole.setDescription(role.getDescription());
		roleService.editRolePerMission(oldRole,permissionIds);
	    return "ok";
		}
		
	
	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody String del(HttpServletRequest request, Map<String, Object> map,String ids) {
		String idString[]= ids.split(",");
		roleService.delByIds(idString);
		return "ok";
	}
	@RequestMapping(value = "getAllList", method = RequestMethod.POST)
	public @ResponseBody
	List<Role> getAllList(HttpServletRequest request, Map<String, Object> map) {
		List<Role> list = roleService.getAllList();
		return list;
	}
	
}
