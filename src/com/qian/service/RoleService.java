package com.qian.service;

import java.util.List;

import com.qian.entity.Role;

public interface RoleService {
	public List<Role> findAll(int page,int num);

	public Role findByUserName(String username);
	
	public long getAllCount();

	public void saveRole(Role role);

	public void editRole(Role role);

	public void delByIds(String[] idString);
	
	public Role findById(int roleId);

	public void editRolePerMission(Role oldRole, String permissionIds);

	public List<Role> getAllList();
}
