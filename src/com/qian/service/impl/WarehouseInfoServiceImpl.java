package com.qian.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.qian.dao.impl.WarehouseInfoDaoImpl;
import com.qian.entity.User;
import com.qian.entity.WarehouseInfo;
import com.qian.service.WarehouseInfoService;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-2-18 下午5:25:00
 * @Description
 */
@Service
@Transactional
public class WarehouseInfoServiceImpl implements WarehouseInfoService {

	@Autowired
	private WarehouseInfoDaoImpl warehouseInfoDaoImpl;

	
	public long getTotalNum(List<String> l, Field field) {

		return warehouseInfoDaoImpl.getTotalNum(l, field);
	}

	
	public List<WarehouseInfo> getPageList(List<String> l, Field field, int page, int rows) {

		return warehouseInfoDaoImpl.getPageList(l, field, page, rows);
	}

	
	public CommonCode add(WarehouseInfo warehouseInfo) {

		warehouseInfoDaoImpl.insert(warehouseInfo);

		return CommonCode.OK;
	}

	
	public CommonCode edit(WarehouseInfo warehouseInfo, int entityId) {

		WarehouseInfo myWarehouseInfo = warehouseInfoDaoImpl.findById(entityId);

		myWarehouseInfo.setWarehouse_code(warehouseInfo.getWarehouse_code());
		myWarehouseInfo.setWarehouse_name(warehouseInfo.getWarehouse_name());
		myWarehouseInfo.setFilling_station_id(warehouseInfo.getFilling_station_id());

		warehouseInfoDaoImpl.update(myWarehouseInfo);

		return CommonCode.OK;
	}

	
	public CommonCode delByIds(String ids) {

		String[] idList = ids.split(",");

		warehouseInfoDaoImpl.delByIds(idList);

		return CommonCode.OK;
	}

	
	public WarehouseInfo findWarehouseInfoByCode(String warehouseCode) {

		return warehouseInfoDaoImpl.findWarehouseInfoByCode(warehouseCode);

	}

	
	public List<WarehouseInfo> getAllList() {

		return warehouseInfoDaoImpl.getAllList();
	}

	@Override
	public List<WarehouseInfo> getMyList(User user) {
		
		List<WarehouseInfo> list = new ArrayList<WarehouseInfo>();

		WarehouseInfo warehouseInfo = user.getWarehouseInfo();
		if (warehouseInfo != null) {
			list.add(warehouseInfo);
			return list;
		}

		return getAllList();
	}

}
