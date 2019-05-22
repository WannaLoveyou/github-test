package com.qian.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.cache.MobileLoginCache;
import com.qian.dao.impl.UserDaoImpl;
import com.qian.dao.impl.UserRoleDaoImpl;
import com.qian.entity.Role;
import com.qian.entity.User;
import com.qian.entity.UserRole;
import com.qian.service.UserService;
import com.qian.vo.Field;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDaoImpl userDAOImpl;

	@Autowired
	private UserRoleDaoImpl userRoleDaoImpl;

	public List<User> getPageList(int page, int num) {
		return userDAOImpl.getPageList(page, num);
	}

	public User findByUserName(String username) {
		return userDAOImpl.findByUserName(username);
	}

	public long getAllCount() {
		return userDAOImpl.getAllCount();
	}

	public void saveUser(User user, String roleIds) {
		if (user.getSecondCategory().getId() == 0) {
			user.setSecondCategory(null);
		}
		if (user.getWarehouseInfo().getId() == 0) {
			user.setWarehouseInfo(null);
		}
		if (!"".equals(roleIds)) {
			String str[] = roleIds.split(",");
			for (String string : str) {
				UserRole ur = new UserRole();
				Role role = new Role();
				role.setId(Integer.parseInt(string));
				ur.setUser(user);
				ur.setRole(role);
				user.getUserRoles().add(ur);
			}
		}

		userDAOImpl.insert(user);
	}

	public void editUser(User user, String roleIds) {
		if (user.getSecondCategory().getId() == 0) {
			user.setSecondCategory(null);
		}
		if (user.getWarehouseInfo().getId() == 0) {
			user.setWarehouseInfo(null);
		}
		User oldUser = userDAOImpl.findById(user.getId());
		oldUser.setUsername(user.getUsername());
		oldUser.setPassword(user.getPassword());
		oldUser.setFull_name(user.getFull_name());
		oldUser.setCard_code(user.getCard_code());
		oldUser.setUser_code(user.getUser_code());
		oldUser.setContactNumber(user.getContactNumber());
		oldUser.setSecondCategory(user.getSecondCategory());
		oldUser.setWarehouseInfo(user.getWarehouseInfo());
		userRoleDaoImpl.deleteByUserId(oldUser.getId());
		userDAOImpl.update(oldUser);
		if (!"".equals(roleIds)) {
			String str[] = roleIds.split(",");
			for (String string : str) {
				UserRole ur = new UserRole();
				Role role = new Role();
				role.setId(Integer.parseInt(string));
				ur.setUser(oldUser);
				ur.setRole(role);
				userRoleDaoImpl.insert(ur);
			}
		}
	}

	public void delByIds(String ids[]) {
		userDAOImpl.delByIds(ids);

	}

	public User findById(int id) {
		return userDAOImpl.findById(id);
	}

	public List<User> getDeliveryManList(Integer secondId) {
		return userDAOImpl.getDeliveryManList(secondId);
	}
	
	public List<User> getDeliveryManList() {
		return userDAOImpl.getDeliveryManList();
	}

	public List<User> getAllList() {
		return userDAOImpl.getAllList();
	}
	
	public List<User> getAllList(List<String> l, Field filed) {
		return userDAOImpl.getAllList(l, filed);
	}

	public User findByCardCode(String cardCode) {

		return userDAOImpl.findByCardCode(cardCode);
	}

	public List<User> getPageList(List<String> l, Field filed, int page, int rows) {

		return userDAOImpl.getPageList(l, filed, page, rows);
	}

	public long getAllCount(List<String> l, Field filed) {

		return userDAOImpl.getAllCount(l, filed);
	}

	public void editLimitLogin(String[] idString, int limitLoginFlag) {

		for (String userId : idString) {
			User user = userDAOImpl.findById(Integer.valueOf(userId));
			user.setLimit_login(limitLoginFlag);
			userDAOImpl.update(user);
		}

	}

	public void clearLimitLogin(String[] idString) {

		for (String userId : idString) {
			MobileLoginCache.clearLimitLogin(Integer.valueOf(userId));
		}

	}

	public List<User> getAllDeliveryManList() {

		return userDAOImpl.getAllDeliveryManList();
	}

	@Override
	public void editWHDeliveryMan(String[] idString, int op) {

		for (String userId : idString) {
			User user = userDAOImpl.findById(Integer.valueOf(userId));
			user.setIs_wh_delivery_man(op);
			userDAOImpl.update(user);
		}
	}

	@Override
	public void editStoreDeliveryMan(String[] idString, int op) {
		for (String userId : idString) {
			User user = userDAOImpl.findById(Integer.valueOf(userId));
			user.setIs_store_delivery_man(op);
			userDAOImpl.update(user);
		}
	}

	@Override
	public List<User> getIsDeliveryManList(Integer secondCategoryId) {

		return userDAOImpl.getIsDeliveryManList(secondCategoryId);
	}

	@Override
	public List<User> getSafetyTechnologyDepartmentUsers() {
		List<String> l = new ArrayList<>();
		l.add("is_safety_technology_department = ?");

		return userDAOImpl.getAllList(l, new Field().addInt(1));
	}

	@Override
	public void editWHAuditor(String[] idString, int op) {
		for (String userId : idString) {
			User user = userDAOImpl.findById(Integer.valueOf(userId));
			user.setIs_wh_auditor(op);
			userDAOImpl.update(user);
		}
	}

	@Override
	public void editSafetyTechnologyDepartment(String[] idString, int op) {
		for (String userId : idString) {
			User user = userDAOImpl.findById(Integer.valueOf(userId));
			user.setIs_safety_technology_department(op);
			userDAOImpl.update(user);
		}
	}

}
