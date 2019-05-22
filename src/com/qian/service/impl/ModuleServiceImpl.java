package com.qian.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.ModuleDaoImpl;
import com.qian.dao.impl.UserRoleDaoImpl;
import com.qian.entity.Module;
import com.qian.entity.Permission;
import com.qian.entity.Role;
import com.qian.entity.RolePermission;
import com.qian.entity.User;
import com.qian.entity.UserRole;
import com.qian.service.ModuleService;

@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleDaoImpl moduleDaoImpl;

	@Autowired
	private UserRoleDaoImpl userRoleDaoImpl;

	public List<Module> findByNoParent() {
		return moduleDaoImpl.findByNoParent();
	}

	public List<Module> findAll() {
		return moduleDaoImpl.findAll();
	}

	public List<Module> findMobileModuleByUser(User user) {

		List<Module> result = new ArrayList<Module>(0);

		List<UserRole> userRoles = userRoleDaoImpl.find(user.getId());

		List<Role> myRoles = new ArrayList<Role>(0);

		Set<Integer> myPermissionSet = new HashSet<Integer>();

		for (UserRole userRole : userRoles) {
			myRoles.add(userRole.getRole());
		}

		for (Role role : myRoles) {

			List<RolePermission> rolePermissions = role.getRolePermissions();

			for (RolePermission rolePermission : rolePermissions) {
				myPermissionSet.add(rolePermission.getPermission().getId());
			}

		}

		List<Module> mobileModules = moduleDaoImpl.findAllMobile();

		for (Module module : mobileModules) {

			List<Permission> permissions = module.getPermissions();

			for (Permission permission : permissions) {

				if (permission.getShortName().equals("view")) {
					
					if (myPermissionSet.contains(permission.getId())) {

						result.add(module);

						break;
					}
				}
			}
		}

		return result;
	}

}