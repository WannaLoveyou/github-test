package com.qian.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.RoleDaoImpl;
import com.qian.dao.impl.RolePermissionImpl;
import com.qian.entity.Permission;
import com.qian.entity.Role;
import com.qian.entity.RolePermission;
import com.qian.service.RoleService;
@Service
@Transactional
public class RoleServiceImpl  implements RoleService{
	@Autowired
	private RoleDaoImpl roleDAOImpl;
	
	@Autowired
	private RolePermissionImpl rolePermissionImpl;

	public List<Role> findAll(int page,int num) {
		return roleDAOImpl.findAll(page, num);
	}

	public Role findByUserName(String username) {
		return roleDAOImpl.findByUserName(username);
	}

	public long getAllCount() {
		return roleDAOImpl.getAllCount();
	}

	public void saveRole(Role role) {
		roleDAOImpl.insert(role);		
	}

	public void editRole(Role role) {
		roleDAOImpl.update(role);
		
	}

	public void delByIds(String ids[]) {
		for (String id : ids) {
			roleDAOImpl.delById(Integer.parseInt(id));
		}
	}

	public Role findById(int roleId) {
		return roleDAOImpl.findById(roleId);
	}

	public void editRolePerMission(Role oldRole, String permissionIds) {
		if(permissionIds!=""){
		 String str[] = permissionIds.split(",");
		    for (String string : str) {
		    	RolePermission rp = new RolePermission();
		    	Permission permission = new Permission();
		    	permission.setId(Integer.parseInt(string));
		    	rp.setRole(oldRole);
		    	rp.setPermission(permission);
		    	oldRole.getRolePermissions().add(rp);
			}
		}
		    List<RolePermission> list = new ArrayList<RolePermission>();
		    for(RolePermission rolePermission:oldRole.getRolePermissions()){
		    	if(rolePermission.getId()!=0){
		    		rolePermission.setRole(null);
		    		list.add(rolePermission);
		    	}
		    }
		    oldRole.getRolePermissions().removeAll(list);
		    rolePermissionImpl.delete(list);
		    editRole(oldRole);		
	}

	public List<Role> getAllList() {
		return roleDAOImpl.getAllList();
	}


}
