package com.qian.service;

import java.util.List;

import com.qian.entity.User;
import com.qian.vo.Field;

public interface UserService {
	public List<User> getPageList(int page, int num);

	public User findByUserName(String username);

	public long getAllCount();

	public void saveUser(User user, String roleIds);

	public void editUser(User user, String roleIds);

	public void delByIds(String[] idString);

	public User findById(int id);

	public List<User> getDeliveryManList(Integer secondId);
	
	public List<User> getAllDeliveryManList();

	public List<User> getAllList();
	
	public List<User> getAllList(List<String> l, Field filed);

	public User findByCardCode(String cardCode);

	public List<User> getPageList(List<String> l, Field filed, int page, int rows);

	public long getAllCount(List<String> l, Field filed);

	public void editLimitLogin(String[] idString, int limitLoginFlag);

	public void clearLimitLogin(String[] idString);

	public void editWHDeliveryMan(java.lang.String[] idString, int op);

	public void editStoreDeliveryMan(java.lang.String[] idString, int op);

	public List<User> getIsDeliveryManList(Integer secondCategoryId);

	public List<User> getSafetyTechnologyDepartmentUsers();

	public void editWHAuditor(java.lang.String[] idString, int op);

	public void editSafetyTechnologyDepartment(java.lang.String[] idString, int op);

	public List<User> getDeliveryManList();

}
