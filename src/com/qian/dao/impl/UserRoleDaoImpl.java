package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.UserRole;
import com.qian.vo.Field;

@Component
public class UserRoleDaoImpl extends HBaseDao<UserRole> {

	public List<UserRole> find(int userId) {
		Field filed = new Field();
		filed.addInt(userId);
		return selectFromHQL(" from UserRole where user.id=? ", filed);

	}

	public void deleteByUserId(int userId) {
		Field filed = new Field();
		filed.addInt(userId);
		 delete(" delete from security_user_role where user_id=? ", filed);
	}

}
