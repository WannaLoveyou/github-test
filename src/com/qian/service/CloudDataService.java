package com.qian.service;

import java.util.Map;

/**
 * 云数据服务接口
 * @author DUANKK
 * @version 2018年4月9日16:32:07
 */
public interface CloudDataService {
	
	/**
	 * 登录身份认证
	 */
	public String authentication();
	
	/**
	 * 从数据库扫描一定数量(num)的钢瓶记录数据发送到云端
	 * @param num
	 * @return
	 */
	public Map<String,String> findGasCylinderDataSendToCloud();
	
	/**
	 * 从数据库扫描一定数量(num)的检测记录数据发送到云端
	 * @param num
	 * @return
	 */
	public Map<String,String> findCheckDataSendToCloud();
	
	/**
	 * 从数据库扫描一定数量(num)的充装记录数据发送到云端
	 * @param num
	 * @return
	 */
	public Map<String,String> findFillingDataSendToCloud();
	
	/**
	 * 手动上传数据
	 * @return
	 */
	public Map<String, String> sendCylinderDataToCloud(String userName, String unitName, String appKey, String dataStr);

}
