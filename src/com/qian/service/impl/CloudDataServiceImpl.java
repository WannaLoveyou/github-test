package com.qian.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qian.code.AirBottleBlontUpCloudPlatformCode;
import com.qian.code.AirBottleUpCloudStateCode;
import com.qian.code.ModuleConfigurationCode;
import com.qian.dao.impl.AirBottleInfoDaoImpl;
import com.qian.dao.impl.AirBottleTrackingRecordDaoImpl;
import com.qian.dao.impl.ModuleConfigurationInfoDaoImpl;
import com.qian.entity.AirBottleInfo;
import com.qian.entity.AirBottleType;
import com.qian.entity.ModuleConfigurationInfo;
import com.qian.service.CloudDataService;
import com.qian.util.CloudDataUtil;
import com.qian.util.HttpRestUtil;
import com.qian.util.JSONUtils;
import com.qian.util.StringUtils;
import com.qian.vo.CheckDataVo;
import com.qian.vo.Field;
import com.qian.vo.FillingDataVo;
import com.qian.vo.GasCylinderDataVo;

/**
 * 云数据服务接口实现
 * @author DUANKK
 * @version 2018年4月9日16:32:07
 */
@Service
@Transactional
public class CloudDataServiceImpl implements CloudDataService {
	
	Logger logger = Logger.getLogger(this.getClass());

//	@Autowired
//	private CloudDaoImpl cloudDaoImpl;
	
	@Autowired
	private AirBottleInfoDaoImpl airBottleInfoDaoImpl;
	
	@Autowired
	private AirBottleTrackingRecordDaoImpl airBottleTrackingRecordDaoImpl;
	
	@Autowired
	private ModuleConfigurationInfoDaoImpl moduleConfigurationInfoDaoImpl;
	
	/**
	 * 登录身份认证
	 */
	public String authentication(){
		
		String TokenKey = null;
		
		String url = CloudDataUtil.URL+CloudDataUtil.AUTHENTICATION;
		
		//请求数据
		JSONObject arr = new JSONObject();
		arr.put("userName", CloudDataUtil.USERNAME);
		arr.put("unitName", CloudDataUtil.UNITNAME);
		String APPKEY = CloudDataUtil.APPKEY;
		//梅州气站有app_key,东莞气站没有app_key
		if (StringUtils.nonEmpty(APPKEY)) {
			arr.put("app_key", APPKEY);
		}
		
		try {
			
			logger.info("请求url:"+url+";---请求数据："+arr);
			String result = HttpRestUtil.postJson(url, arr);
			logger.info("云平台返回结果："+result);
			
			JSONObject jsonResult = JSONObject.fromObject(result);
			JSONObject jsonData = jsonResult.getJSONArray("value").getJSONObject(0);
			if (jsonData.getInt("status")==0) {
				TokenKey=jsonData.getString("statusCode");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TokenKey;
	}
	
	/**
	 * 从数据库扫描一定数量的钢瓶数据发送到云端
	 */
	public Map<String, String> findGasCylinderDataSendToCloud() {
		
		//默认返回成功
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("code", "0");
		
		try {
			
			// 判断是否开启数据上传功能
			ModuleConfigurationInfo moduleConfigurationInfo = moduleConfigurationInfoDaoImpl.findById(ModuleConfigurationCode.AIR_BOTTLE_INFO_UPLOAD_TO_CLOUD_PLATFORM);
			if (moduleConfigurationInfo==null || moduleConfigurationInfo.getIs_open() == 0) {
				retMap.put("code", "1");
				retMap.put("msg", "功能关闭");
				return retMap;
			}
			
			//检索需要上传的气瓶列表
			List<String> l = new ArrayList<String>();
			Field field = new Field();
			l.add("upState = ? ");
			field.addInt(AirBottleUpCloudStateCode.WAIT_UP);
			l.add("airBottleBelong.upload_cloud_platform = ? ");
			field.addInt(AirBottleBlontUpCloudPlatformCode.UP);
			List<AirBottleInfo> airBottleInfos =  airBottleInfoDaoImpl.getPageList(l, field, 1, CloudDataUtil.UP_NUMBER);
			
			if (airBottleInfos.size()<=0) {
				retMap.put("code", "1");
				retMap.put("msg", "没有数据可上传");
				return retMap;
			}
			
			//获取身份认证
			String TokenKey = this.authentication();
			if (TokenKey == null) {
				retMap.put("code", "1");
				retMap.put("msg", "没有拿到TOKENKEY");
				return retMap;
			}
			
			//封装成云数据格式的数据集合
			List<GasCylinderDataVo> list = new LinkedList<GasCylinderDataVo>();
			//上传气瓶id集合
			List<Integer> idList = new ArrayList<Integer>();
			
			for (int i = 0; i < airBottleInfos.size(); i++) {
				AirBottleInfo a = airBottleInfos.get(i);
				AirBottleType type = a.getAirBottleType();
				
				String cloudId;
				//判断是否是第一次上传云平台
				if (a.getCloudId()==null||"".equals(a.getCloudId())) {
					
					cloudId = UUID.randomUUID().toString().replace("-", "").toUpperCase();
					
					List<String> set_l = new ArrayList<String>();
					Field set_field = new Field();
					set_l.add("cloudId = ?");
					set_field.addStr(cloudId);
					
					List<String> where_l = new ArrayList<String>();
					Field where_field = new Field();
					where_l.add("id = ?");
					where_field.addInt(a.getId());
					
					airBottleInfoDaoImpl.update(set_l, set_field, where_l, where_field, "AirBottleInfo");
				}else{
					cloudId = a.getCloudId();
				}
				
				GasCylinderDataVo v = new GasCylinderDataVo(TokenKey,cloudId,a,type);
				list.add(v);
				idList.add(a.getId());
			}
			
//			if (list.size()<=0) {
//				retMap.put("code", "1");
//				retMap.put("msg", "没有数据可上传");
//				return retMap;
//			}
			
			//封装数据
			JSONArray data = JSONArray.fromObject(list);
			JSONObject param = new JSONObject();
			param.put("data", data);
			
			JSONObject allParam = new JSONObject();
			allParam.put("gdsei", "'"+param+"'");  
		
			//url
			String url = CloudDataUtil.URL+CloudDataUtil.UPLOAD_GAS_CYLINDER;
			
			//上传并接收返回值
			logger.info("请求url:"+url+";---请求数据："+allParam);
			String result = HttpRestUtil.postJson(url, allParam);
			logger.info("云平台返回结果："+result);
			
			//解析返回值为json
			JSONObject jsonResult = JSONObject.fromObject(result);
			
			//获取返回消息编号
			int errcode = jsonResult.getInt("errcode");
			
			//判断返回消息编号
			if (errcode==0) {
				//更新数据为已经上传过的
				List<String> set_l = new ArrayList<String>();
				Field set_field = new Field();
				set_l.add("upState = ?");
				set_field.addInt(AirBottleUpCloudStateCode.UPPED);
				
				List<String> where_l = new ArrayList<String>();
				Field where_field = null;
				for (int i = 0; i < idList.size(); i++) {
					where_l.clear();
					where_field = new Field();
					where_l.add(" id = ?");
					where_field.addInt(idList.get(i));
					airBottleInfoDaoImpl.update(set_l, set_field, where_l, where_field, "AirBottleInfo");
				}

				//拿到返回信息
				JSONObject jsonData = jsonResult.getJSONArray("value").getJSONObject(0);
				if (jsonData.getInt("status")==0) {
					retMap.put("msg", jsonData.getString("statusCode"));
				}else{
					retMap.put("code", "1");
					retMap.put("msg", "传输失败");
				}
			}else{
				retMap.put("code", "1");
				retMap.put("msg", "传输失败");
			}
		} catch (Exception e) {
			retMap.put("code", "1");
			retMap.put("msg", "传输失败");
			e.printStackTrace();
		}
		
		return retMap;
	}
	
	/**
	 * 从数据库扫描一定数量(num)的检测记录数据发送到云端
	 * @param num
	 * @return
	 */
	public Map<String, String> findCheckDataSendToCloud() {
		Map<String, String> retMap = new HashMap<String, String>();
		
		// 判断是否开启数据上传
		ModuleConfigurationInfo moduleConfigurationInfo = moduleConfigurationInfoDaoImpl.findById(ModuleConfigurationCode.AIR_BOTTLE_INFO_UPLOAD_TO_CLOUD_PLATFORM);
		if (moduleConfigurationInfo.getIs_open() == 0) {
			retMap.put("code", "1");
			retMap.put("msg", "功能关闭");
			return retMap;
		}
		
		//获取身份认证
		String TokenKey = this.authentication();
		String url = CloudDataUtil.URL+CloudDataUtil.UPLOAD_CHECK_RECORD;
		
		// TODO 从数据库提取数据
		List<CheckDataVo> list = new LinkedList<CheckDataVo>();
		CheckDataVo vo;
		vo = new CheckDataVo(TokenKey,"ECF96DA08A414380A7690FBAF975CAC9", "江苏民生重工有限公司", null, "2016-06-01", 
				"SED905075", "QPB4-SED160601153526", "东莞市常泰液化石油气钢瓶检测有限公司", "合格", "2016-06-01", 
				"2020-06-01", null, null, "东莞市富民燃气有限公司", "SED");
		list.add(vo);
		vo = new CheckDataVo(TokenKey,"2CA93988D203478782DF216DED14604D", "江苏民生重工有限公司", null, "2016-06-01", 
				"SED905036", "QPB4-SED160601153526", "东莞市常泰液化石油气钢瓶检测有限公司", "合格", "2016-06-01", 
				"2020-06-01", null, null, "东莞市富民燃气有限公司", "SED");
		list.add(vo);
		//封装数据
		JSONArray data = JSONArray.fromObject(list);
		JSONObject param = new JSONObject();
		param.put("data", data);
		
		JSONObject allParam = new JSONObject();
		allParam.put("gdsei", "'"+param+"'");
		
		try {
			logger.info("请求url:"+url);
			logger.info("请求数据："+allParam);
			String result = HttpRestUtil.postJson(url, allParam);
			logger.info(result);
			JSONObject jsonResult = JSONObject.fromObject(result);
			int errcode = jsonResult.getInt("errcode");
			if (errcode==0) {
				//TODO 更新数据为已经上传过的
//				for (int i = 0; i < list.size(); i++) {
//					GasCylinderDataVo v = list.get(i);
//					cloudDaoImpl.updateStatusByJARNOAndMAKENO(v.getMAKENO(),v.getJARNO());
//				} 
				//拿到返回信息
				JSONObject jsonData = jsonResult.getJSONArray("value").getJSONObject(0);
				if (jsonData.getInt("status")==0) {
					retMap.put("msg", jsonData.getString("statusCode"));
				}
			}else{
				retMap.put("code", "1");
				retMap.put("msg", "传输失败");
			}
		} catch (Exception e) {
			retMap.put("code", "1");
			retMap.put("msg", "传输失败");
			logger.error(e);
		}
		
		return retMap;
	}
	
	/**
	 * 从数据库扫描一定数量(num)的充装记录数据发送到云端
	 * @param num
	 * @return
	 */
	public Map<String,String> findFillingDataSendToCloud() {
		Map<String, String> retMap = new HashMap<String, String>();
		
		// 判断是否开启数据上传
		ModuleConfigurationInfo moduleConfigurationInfo = moduleConfigurationInfoDaoImpl.findById(ModuleConfigurationCode.AIR_BOTTLE_INFO_UPLOAD_TO_CLOUD_PLATFORM);
		if (moduleConfigurationInfo.getIs_open() == 0) {
			retMap.put("code", "1");
			retMap.put("msg", "功能关闭");
			return retMap;
		}
		
		//获取身份认证
		String TokenKey = this.authentication();
		String url = CloudDataUtil.URL+CloudDataUtil.UPLOAD_FILLING_RECORD;
		
		// TODO 从数据库提取数据
		List<FillingDataVo> list = new LinkedList<FillingDataVo>();
		FillingDataVo vo;
		vo = new FillingDataVo(TokenKey,"ECF96DA08A414380A7690FBAF975CAC9", "江苏民生重工有限公司", null, "2016-06-01", 
				"SED905075", "正常", "正常", "2018-04-12", null, "东莞市富民燃气有限公司", "SED");
		list.add(vo);
		vo = new FillingDataVo(TokenKey,"2CA93988D203478782DF216DED14604D", "江苏民生重工有限公司", null, "2016-06-01", 
				"SED905036", "正常", "正常", "2018-04-12", null, "东莞市富民燃气有限公司", "SED");
		list.add(vo);
		//封装数据
		JSONArray data = JSONArray.fromObject(list);
		JSONObject param = new JSONObject();
		param.put("data", data);

		JSONObject allParam = new JSONObject();
		allParam.put("gdsei", "'"+param+"'");
		
		try {
			logger.info("请求url:"+url);
			logger.info("请求数据："+allParam);
			String result = HttpRestUtil.postJson(url, allParam);
			logger.info(result);
			JSONObject jsonResult = JSONObject.fromObject(result);
			int errcode = jsonResult.getInt("errcode");
			if (errcode==0) {
				//TODO 更新数据为已经上传过的
//				for (int i = 0; i < list.size(); i++) {
//					GasCylinderDataVo v = list.get(i);
//					cloudDaoImpl.updateStatusByJARNOAndMAKENO(v.getMAKENO(),v.getJARNO());
//				} 
				//拿到返回信息
				JSONObject jsonData = jsonResult.getJSONArray("value").getJSONObject(0);
				if (jsonData.getInt("status")==0) {
					retMap.put("msg", jsonData.getString("statusCode"));
				}
			}else{
				retMap.put("code", "1");
				retMap.put("msg", "传输失败");
			}
		} catch (Exception e) {
			retMap.put("code", "1");
			retMap.put("msg", "传输失败");
			e.printStackTrace();
		}
		
		return retMap;
	}
	
	/**
	 * 手动上传数据
	 * @return
	 */
	public Map<String, String> sendCylinderDataToCloud(String userName, String unitName, String appKey, String dataStr) {
		
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("code", "0");
		
		if (userName == null || userName.length()<1 || unitName == null || unitName.length()<1) {
			retMap.put("code", "1");
			retMap.put("msg", "身份信息空");
			return retMap;
		}
		
		if (dataStr == null || dataStr.length()<1) {
			retMap.put("code", "1");
			retMap.put("msg", "数据为空");
			return retMap;
		}
		
		try {
			
			//获取身份认证
			String TokenKey = null;
			String authUrl = CloudDataUtil.URL+CloudDataUtil.AUTHENTICATION;
			//数据
			JSONObject arr = new JSONObject();
			arr.put("userName", userName);
			arr.put("unitName", unitName);
			if (appKey!=null && !"".equals(appKey)) {
				arr.put("app_key", appKey);
			}
			
			logger.info("请求url:"+authUrl);
			logger.info("请求数据："+arr);
			String result = HttpRestUtil.postJson(authUrl, arr);
			logger.info(result);
			JSONObject jsonResult = JSONObject.fromObject(result);
			JSONObject jsonData = jsonResult.getJSONArray("value").getJSONObject(0);
			if (jsonData.getInt("status")==0) {
				TokenKey=jsonData.getString("statusCode");
			}
			
			if (TokenKey == null) {
				retMap.put("code", "1");
				retMap.put("msg", "没有拿到TOKENKEY");
				return retMap;
			}
			
			//url
			String url = CloudDataUtil.URL+CloudDataUtil.UPLOAD_GAS_CYLINDER;
			
			//封装成云数据格式的数据集合
			JSONArray dataJsonList = JSONArray.fromObject(dataStr);
			
			List<GasCylinderDataVo> list = new LinkedList<GasCylinderDataVo>();
			
			for(int i=0;i<dataJsonList.size();i++){
				JSONObject dataJson = (JSONObject) dataJsonList.get(i);
				GasCylinderDataVo vo = (GasCylinderDataVo)JSONUtils.jsonToObject(dataJson.toString(),GasCylinderDataVo.class);
				vo.setTokenKey(TokenKey);
				vo.setID(UUID.randomUUID().toString().replace("-", "").toUpperCase());
				list.add(vo);
			}
			
			//封装数据
			JSONArray data = JSONArray.fromObject(list);
			JSONObject param = new JSONObject();
			param.put("data", data);
			
			JSONObject allParam = new JSONObject();
			allParam.put("gdsei", "'"+param+"'");  
		
			logger.info("请求url:"+url);
			logger.info("请求数据："+allParam);
			
			//上传并接收返回值
			String result1 = HttpRestUtil.postJson(url, allParam);
			logger.info(result1);
			
			//解析返回值为json
			JSONObject jsonResult1 = JSONObject.fromObject(result1);
			
			//获取返回消息编号
			int errcode = jsonResult1.getInt("errcode");
			
			//判断返回消息编号
			if (errcode==0) {

				//拿到返回信息
				JSONObject jsonData1 = jsonResult1.getJSONArray("value").getJSONObject(0);
				if (jsonData1.getInt("status")==0) {
					retMap.put("msg", jsonData1.getString("statusCode"));
				}
			}else{
				retMap.put("code", "1");
				retMap.put("msg", "传输失败");
			}
		} catch (Exception e) {
			retMap.put("code", "1");
			retMap.put("msg", "传输失败");
			e.printStackTrace();
		}
		
		return retMap;
	}
	
}
