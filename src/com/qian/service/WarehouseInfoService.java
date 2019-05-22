package com.qian.service;

import java.util.List;

import com.qian.entity.User;
import com.qian.entity.WarehouseInfo;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-2-18 下午5:24:45
 * @Description
 */
public interface WarehouseInfoService {

	public long getTotalNum(List<String> l, Field field);

	public List<WarehouseInfo> getPageList(List<String> l, Field field, int page, int rows);

	public CommonCode add(WarehouseInfo warehouseInfo);

	public CommonCode edit(WarehouseInfo warehouseInfo, int entityId);

	public CommonCode delByIds(String ids);

	public WarehouseInfo findWarehouseInfoByCode(String warehouseCode);

	public List<WarehouseInfo> getAllList();

	public List<WarehouseInfo> getMyList(User user);

}
