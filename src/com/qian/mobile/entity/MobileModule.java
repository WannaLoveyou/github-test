package com.qian.mobile.entity;

import com.qian.entity.Module;

/**
 * @author Lu Kongwen
 * @version Create time：2016-5-5 下午3:59:12
 * @Description
 */
public class MobileModule {

	private int module_id;
	
	private String module_name;
	
	private String module_description;

	public MobileModule(Module module){
		
		this.module_id = module.getId();
		this.module_name = module.getName();
		this.module_description = module.getDescription();
	}
	
	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getModule_description() {
		return module_description;
	}

	public void setModule_description(String module_description) {
		this.module_description = module_description;
	}
	
	
}
