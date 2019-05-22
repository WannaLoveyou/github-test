package com.qian.service;

import java.util.List;

import com.qian.entity.ModuleConfigurationInfo;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-4-21 下午2:29:32
 * @Description
 */
public interface ModuleConfigurationInfoService {

	public ModuleConfigurationInfo findById(int weiXin);

	public Long getTotalNum(List<String> l, Field filed);

	public List<ModuleConfigurationInfo> getPageList(List<String> l, Field filed, int page, int rows);

	public void edit(ModuleConfigurationInfo moduleConfigurationInfo);

}
