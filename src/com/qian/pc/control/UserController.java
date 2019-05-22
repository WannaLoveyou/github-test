package com.qian.pc.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.Role;
import com.qian.entity.User;
import com.qian.entity.UserRole;
import com.qian.service.UserService;
import com.qian.util.ExportHeadsUtils;
import com.qian.util.ExportUtils;
import com.qian.util.JSONUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.ExportOrderInfoReportHeads;
import com.qian.vo.Field;


/**
 * @author lizhaoyang
 * @version Create time：2015-11-13 上午9:05:58
 * @Description
 */

@Controller
@RequestMapping("/user")
public class UserController {

	private static final String LIST = "user/list";
	@Autowired
	private UserService userService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Map<String, Object> map) {
		return LIST;
	}

	@RequestMapping(value = "getList", method = RequestMethod.GET)
	public @ResponseBody
	String String(HttpServletRequest request, Map<String, Object> map, int page, int rows) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		ToolsBarUtils.getUserSearchCondition(request, l, filed);
		
		long total = userService.getAllCount(l, filed);

		JSONArray jsonArray = getListData(l, filed, page, rows);

		result.put("total", total);
		result.put("rows", jsonArray);
		return JSONUtils.fromObject(result);
	}
	
	@RequestMapping(value = "exportAllList", method = RequestMethod.POST)
	public void exportAllList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {

		List<ExportOrderInfoReportHeads> heads = ExportHeadsUtils.getExportOrderInfoReportHeads(request);

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		ToolsBarUtils.getUserSearchCondition(request, l, filed);
		
		JSONArray jsonArray = getListData(l, filed, null, null);

		ExportUtils.exportExcel(response, heads, jsonArray, "员工信息表.xls", "template-UserInfo.xls");

	}
	
	private JSONArray getListData(List<String> l, Field filed, Integer page, Integer rows){
		
		List<User> list = new ArrayList<User>();
		
		if (page == null || rows == null) {
			list = userService.getAllList(l, filed);
		}else {
			list = userService.getPageList(l, filed, page, rows);
		}

		JSONArray jsonArray = new JSONArray();
		for (User user : list) {
			String roleNames = "";
			String roleIds = "";
			List<UserRole> urList = user.getUserRoles();
			for (UserRole userRole : urList) {
				Role role = userRole.getRole();
				roleNames += role.getName() + ",";
				roleIds += role.getId() + ",";
			}
			JSONObject rowJSON = new JSONObject();
			rowJSON.put("id", user.getId());
			rowJSON.put("username", user.getUsername());
			rowJSON.put("password", user.getPassword());
			rowJSON.put("card_code", user.getCard_code());
			rowJSON.put("full_name", user.getFull_name());
			rowJSON.put("user_code", user.getUser_code());
			rowJSON.put("contactNumber", user.getContactNumber());
			rowJSON.put("limit_login", user.getLimit_login());
			rowJSON.put("is_wh_delivery_man", user.getIs_wh_delivery_man());
			rowJSON.put("is_store_delivery_man", user.getIs_store_delivery_man());
			rowJSON.put("is_wh_auditor", user.getIs_wh_auditor());
			rowJSON.put("is_safety_technology_department", user.getIs_safety_technology_department());


			if (user.getSecondCategory() != null) {
				rowJSON.put("secondCategory", user.getSecondCategory().getId());
				rowJSON.put("secondCategoryName", user.getSecondCategory().getSecond_category_name());
			}
			
			if (user.getWarehouseInfo() != null) {
				rowJSON.put("warehouseInfo", user.getWarehouseInfo().getId());
				rowJSON.put("warehouseInfoName", user.getWarehouseInfo().getWarehouse_name());
			}

			if (urList != null && urList.size() > 0) {
				rowJSON.put("roleNames", roleNames.substring(0, roleNames.length() - 1));
				rowJSON.put("roleIds", roleIds.substring(0, roleIds.length() - 1));
			}
			jsonArray.add(rowJSON);
		}
		
		return jsonArray;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, User user, String roleIds) {
		userService.saveUser(user, roleIds);
		return "ok";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	String edit(HttpServletRequest request, Map<String, Object> map, User user, String roleIds) {
		userService.editUser(user, roleIds);
		return "ok";
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	String del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		userService.delByIds(idString);
		return "ok";
	}
	
	@RequestMapping(value = "editWHAuditor", method = RequestMethod.POST)
	public @ResponseBody
	String editWHAuditor(HttpServletRequest request, Map<String, Object> map, String ids, int op) {
		String idString[] = ids.split(",");
		userService.editWHAuditor(idString, op);
		return "ok";
	}
	
	@RequestMapping(value = "editSafetyTechnologyDepartment", method = RequestMethod.POST)
	public @ResponseBody
	String editSafetyTechnologyDepartment(HttpServletRequest request, Map<String, Object> map, String ids, int op) {
		String idString[] = ids.split(",");
		userService.editSafetyTechnologyDepartment(idString, op);
		return "ok";
	}
	
	@RequestMapping(value = "editWHDeliveryMan", method = RequestMethod.POST)
	public @ResponseBody
	String editWHDeliveryMan(HttpServletRequest request, Map<String, Object> map, String ids, int op) {
		String idString[] = ids.split(",");
		userService.editWHDeliveryMan(idString, op);
		return "ok";
	}
	
	@RequestMapping(value = "editStoreDeliveryMan", method = RequestMethod.POST)
	public @ResponseBody
	String editStoreDeliveryMan(HttpServletRequest request, Map<String, Object> map, String ids, int op) {
		String idString[] = ids.split(",");
		userService.editStoreDeliveryMan(idString, op);
		return "ok";
	}

	@RequestMapping(value = "editLimitLogin", method = RequestMethod.POST)
	public @ResponseBody
	String editLimitLogin(HttpServletRequest request, Map<String, Object> map, String ids, int limitLoginFlag) {
		String idString[] = ids.split(",");
		userService.editLimitLogin(idString, limitLoginFlag);
		return "ok";
	}
	
	@RequestMapping(value = "clearLimitLogin", method = RequestMethod.POST)
	public @ResponseBody
	String clearLimitLogin(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		userService.clearLimitLogin(idString);
		return "ok";
	}

	@RequestMapping(value = "getDeliveryManListBySecondCategoryId", method = RequestMethod.GET)
	public @ResponseBody
	List<User> getDeliveryManListBySecondCategoryId(HttpServletRequest request, Map<String, Object> map, Integer secondCategoryId) {

		List<User> lists = userService.getDeliveryManList(secondCategoryId);

		return lists;
	}
	
	@RequestMapping(value = "getAllDeliveryManList", method = RequestMethod.GET)
	public @ResponseBody
	List<User> getDeliveryManList(HttpServletRequest request, Map<String, Object> map) {

		List<User> lists = userService.getDeliveryManList();

		return lists;
	}
	
	@RequestMapping(value = "getIsDeliveryManListBySecondCategoryId", method = RequestMethod.GET)
	public @ResponseBody
	List<User> getIsDeliveryManListBySecondCategoryId(HttpServletRequest request, Map<String, Object> map, Integer secondCategoryId) {

		List<User> lists = userService.getIsDeliveryManList(secondCategoryId);

		return lists;
	}
	
	@RequestMapping(value = "getSafetyTechnologyDepartmentUsers")
	public @ResponseBody
	List<User> getSafetyTechnologyDepartmentUsers(HttpServletRequest request, Map<String, Object> map) {
		
		List<User> lists = userService.getSafetyTechnologyDepartmentUsers();
		
		return lists;
	}

	@RequestMapping(value = "getUserRole", method = RequestMethod.POST)
	public @ResponseBody
	List<Role> getUserRole(HttpServletRequest request, Map<String, Object> map, int userId) {

		User user = userService.findById(userId);

		List<UserRole> lists = user.getUserRoles();
		List<Role> roleList = new ArrayList<Role>();
		for (UserRole userRole : lists) {
			roleList.add(userRole.getRole());
		}

		return roleList;
	}

	@RequestMapping(value = "getUsername", method = RequestMethod.GET)
	public @ResponseBody
	BaseDto<User> getusername(HttpServletRequest request, Map<Object, Object> map) {
		Subject subject = SecurityUtils.getSubject();
		return BaseDto.getSuccessResult((User) subject.getPrincipal());
	}

	@RequestMapping(value = "findUserById", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<User> findUserById(HttpServletRequest request, Map<String, Object> map, int userId) {

		User user = userService.findById(userId);

		return BaseDto.getSuccessResult(user);
	}

	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody
	List<User> getAllList(HttpServletRequest request, Map<String, Object> map) {
		List<User> list = userService.getAllList();
		return list;

	}

	@RequestMapping(value = "getUser", method = RequestMethod.POST)
	public @ResponseBody
	boolean getuser(HttpServletRequest request, Map<String, Object> map, String username, String id) {
		int ids = 0;
		if (id != null) {
			ids = Integer.parseInt(id);
		}
		User user = userService.findByUserName(username);
		if (user != null) {
			if (user.getId() == ids) {
				return true;
			}
			return false;
		} else {
			return true;
		}
	}

}
