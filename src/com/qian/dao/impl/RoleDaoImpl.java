package com.qian.dao.impl;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.Role;
import com.qian.vo.Field;

@Component
public class RoleDaoImpl extends HBaseDao<Role> {

	/**
	 * 获取用户列表
	 */
	public List<Role> findAll(int page,int num){
		return selectFromHQL(" from Role", (page-1)*num, num);
	}
	
	public Role findByUserName(String username) {
		Field filed = new Field();
		filed.addStr(username);
		return selectFromHQL(" from Role where name=? ", filed)
				.get(0);
	}

	public long getAllCount() {
		return getTotalFromHQL(" from Role");
	}
	
	public void delById(int id){
		Field field = new Field();
		field.addInt(id);
		delete(" delete from security_role where id=?",field);
	}

	public List<Role> getAllList() {
		return selectFromHQL(" from Role");
	}

}
	
	
	
	
	
	
	
	
	
	
	
