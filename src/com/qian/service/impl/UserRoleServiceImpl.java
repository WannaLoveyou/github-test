package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.UserRoleDaoImpl;
import com.qian.entity.UserRole;
import com.qian.service.UserRoleService;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDaoImpl userRoleDaoImpl;

	public List<UserRole> find(int userId) {
		return userRoleDaoImpl.find(userId);
	}

}
