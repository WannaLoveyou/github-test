package com.qian.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.ModuleConfigurationInfoDaoImpl;
import com.qian.entity.ModuleConfigurationInfo;
import com.qian.service.ModuleConfigurationInfoService;
import com.qian.util.RedPacketUtils;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-4-21 下午2:29:46
 * @Description
 */
@Service
@Transactional
public class ModuleConfigurationInfoServiceImpl implements ModuleConfigurationInfoService {

	public static final Logger log = Logger.getLogger(RedPacketUtils.class);

	@Autowired
	private ModuleConfigurationInfoDaoImpl moduleConfigurationInfoImpl;

	public ModuleConfigurationInfo findById(int id) {

		return moduleConfigurationInfoImpl.findById(id);
	}

	public Long getTotalNum(List<String> l, Field filed) {

		return moduleConfigurationInfoImpl.getTotalNum(l, filed);
	}

	public List<ModuleConfigurationInfo> getPageList(List<String> l, Field filed, int page, int rows) {

		return moduleConfigurationInfoImpl.getPageList(l, filed, page, rows);
	}

	public void edit(ModuleConfigurationInfo moduleConfigurationInfo) {

		moduleConfigurationInfoImpl.update(moduleConfigurationInfo);
	}

}
