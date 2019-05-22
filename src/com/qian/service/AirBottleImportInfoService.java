package com.qian.service;

import java.util.List;
import java.util.Map;

import com.qian.entity.AirBottleImportInfo;
import com.qian.entity.ModuleConfigurationInfo;
import com.qian.entity.OrderInfo;
import com.qian.entity.User;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-12-15 下午4:45:53
 * @Description
 */
public interface AirBottleImportInfoService {

	public CommonCode upLoadExcel(List<Map<String, String>> excvelMaps, User user);

	public Long getTotalNum(List<String> l, Field filed);

	public List<AirBottleImportInfo> getPageList(List<String> l, Field filed, int page, int rows);

	public CommonCode delByIds(String ids);

	public CommonCode add(AirBottleImportInfo airBottleImportInfo);

	public CommonCode edit(AirBottleImportInfo airBottleImportInfo, int entityId);

	public CommonCode transform(List<String> l, Field filed);

	public CommonCode batchDel(List<String> l, Field filed);
	
	public int getPirntTimes(Integer orderId);
	
	public AirBottleImportInfo findOrderInfoById(int orderId);

}
