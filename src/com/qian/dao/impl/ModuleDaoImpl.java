package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.Module;

@Component
public class ModuleDaoImpl extends HBaseDao<Module> {
	
	public List<Module> findByNoParent() {

		String hql = " from Module m where m.parent is null";
        return selectFromHQL(hql);
	}
	
	public List<Module> findAll() {
		String hql = " from Module m where m.parent is not null order by m.priority ASC";
        return selectFromHQL(hql);
	}
	
	public List<Module> findAllMobile(){
		
		String hql = " from Module m where id > 10000 and m.parent is not null order by m.priority ASC";
        return selectFromHQL(hql);
	}
}