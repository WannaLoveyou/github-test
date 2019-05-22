package com.qian.service;

import java.util.List;

import com.qian.entity.Module;
import com.qian.entity.User;

public interface ModuleService {
	
	public List<Module> findByNoParent();
	
	public List<Module> findAll();
	
	public List<Module> findMobileModuleByUser(User user);
}
