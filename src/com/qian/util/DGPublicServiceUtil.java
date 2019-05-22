package com.qian.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.qian.vo.DGBottleInfoDataVo;
import com.qian.vo.DGDeliveryDataVo;
import com.qian.vo.DGFillingDataVo;
import com.qian.vo.DGHandOverDataVo;
import com.qian.vo.DGRecycleDataVo;
import com.qian.vo.DGSupplyDataVo;

public class DGPublicServiceUtil {

	private static Logger log = Logger.getLogger(DGPublicServiceUtil.class);
	
	static ResourceBundle resource = ResourceBundle.getBundle("properties/dgpublicservice");
	
	public final static String BASE_URL =  resource.getString("base_url");
	public final static String AREA = resource.getString("area");

	public final static String BOTTOLE_ENTERPRISE = PathUtils.getProperty("publicservice.bottoleEnterprise");

	/** 充装数据对接接口（最优先对接接口）参数封装可以参考下面代码 */
	public final static String SEND_FILLING_DATA_URL = BASE_URL + "/" + AREA+"/webApi/dgFillingScale";
	/** 供应记录接口（供应记录为供应站或者门店接收到气瓶） */
	public final static String SEND_SUPPLY_DATA_URL = BASE_URL + "/" + AREA+"/webApi/dgSupply";
	/** 配送记录对接接口（配送为送气工接收气瓶准备送给用户的操作） */
	public final static String SEND_DELIVERY_DATA_URL = BASE_URL + "/" + AREA+"/webApi/dgDeliveryApi";
	/** 交付记录对接接口（交付为从业人员将气瓶交付给用户的操作） */
	public final static String SEND_HANDOVER_DATA_URL = BASE_URL + "/" + AREA+"/webApi/dgHandOver";
	/** 回收气瓶对接接口（回收为气瓶在用户使用完毕后，从业人员去回收空瓶） */
	public final static String SEND_RECYCLE_DATA_URL = BASE_URL + "/" + AREA+"/webApi/dgRecycle";
	/** 站点信息对接接口 */
	public final static String SEND_STATION_DATA_URL = BASE_URL + "/" + AREA+"/webApi/dgStation";
	/** 从业人员对接接口对接接口 */
	public final static String SEND_STAFFINFO_DATA_URL = BASE_URL + "/" + AREA+"/webApi/dgstaffInfo";
	/** 气瓶数据对接接口 */
	public final static String SEND_BOTTLEINFO_DATA_URL = BASE_URL + "/" + AREA+"/webApi/dgBottleInfo";

	public final static int MAX_UPLOAD_SIZE = Integer.parseInt(resource.getString("maxUploadSize"));

	public final static String NULL_VALUE_STR = resource.getString("nullValueString");
	
	/**
	 * 充装数据
	 * @param paramList
	 */
	public static void sendFillingData(List<DGFillingDataVo> paramList) {
		List<DGFillingDataVo> sendList = new ArrayList<DGFillingDataVo>(MAX_UPLOAD_SIZE);

		for (int i = 1; i <= paramList.size(); i++) {

			paramList.get(i - 1).setBottoleEnterprise(BOTTOLE_ENTERPRISE);
			sendList.add(paramList.get(i - 1));

			if (i % MAX_UPLOAD_SIZE == 0 || i == paramList.size()) {
				Map<String,Object> param = new HashMap<String, Object>();
				param.put("postData", JSONUtils.fromArrayObject(sendList));
//				log.info("-----充装数据:"+param);
				String result = HttpUtils.httpPostByTimeOut(SEND_FILLING_DATA_URL, param);
				log.info("-----充装数据:"+result);
				sendList.clear();
			}

		}
	}
	
	/**
	 * 供应记录
	 * @param paramList
	 */
	public static void sendSupplyData(List<DGSupplyDataVo> paramList) {
		List<DGSupplyDataVo> sendList = new ArrayList<DGSupplyDataVo>(MAX_UPLOAD_SIZE);

		for (int i = 1; i <= paramList.size(); i++) {

			paramList.get(i - 1).setBottoleEnterprise(BOTTOLE_ENTERPRISE);
			sendList.add(paramList.get(i - 1));

			if (i % MAX_UPLOAD_SIZE == 0 || i == paramList.size()) {
				Map<String,Object> param = new HashMap<String, Object>();
				param.put("postData", JSONUtils.fromArrayObject(sendList));
//				log.info("-----供应记录:"+param);
				String result = HttpUtils.httpPostByTimeOut(SEND_SUPPLY_DATA_URL, param);
				log.info("-----供应记录:"+result);
				sendList.clear();
			}

		}
	}
	
	/**
	 * 配送记录
	 * @param paramList
	 */
	public static void sendDeliveryData(List<DGDeliveryDataVo> paramList) {
		List<DGDeliveryDataVo> sendList = new ArrayList<DGDeliveryDataVo>(MAX_UPLOAD_SIZE);
		
		for (int i = 1; i <= paramList.size(); i++) {
			
			paramList.get(i - 1).setBottoleEnterprise(BOTTOLE_ENTERPRISE);
			sendList.add(paramList.get(i - 1));
			
			if (i % MAX_UPLOAD_SIZE == 0 || i == paramList.size()) {
				Map<String,Object> param = new HashMap<String, Object>();
				param.put("postData", JSONUtils.fromArrayObject(sendList));
//				log.info("-----交付记录:"+param);
				String result = HttpUtils.httpPostByTimeOut(SEND_DELIVERY_DATA_URL, param);
				log.info("-----配送记录:"+result);
				sendList.clear();
			}
			
		}
	}
	
	/**
	 * 交付记录
	 * @param paramList
	 */
	public static void sendHandOverData(List<DGHandOverDataVo> paramList) {
		List<DGHandOverDataVo> sendList = new ArrayList<DGHandOverDataVo>(MAX_UPLOAD_SIZE);
		
		for (int i = 1; i <= paramList.size(); i++) {
			
			paramList.get(i - 1).setBottoleEnterprise(BOTTOLE_ENTERPRISE);
			sendList.add(paramList.get(i - 1));
			
			if (i % MAX_UPLOAD_SIZE == 0 || i == paramList.size()) {
				Map<String,Object> param = new HashMap<String, Object>();
				param.put("postData", JSONUtils.fromArrayObject(sendList));
//				log.info("-----交付记录:"+param);
				String result = HttpUtils.httpPostByTimeOut(SEND_HANDOVER_DATA_URL, param);
				log.info("-----交付记录:"+result);
				sendList.clear();
			}
			
		}
	}
	
	/**
	 * 回收气瓶记录
	 * @param paramList
	 */
	public static void sendRecycleData(List<DGRecycleDataVo> paramList) {
		List<DGRecycleDataVo> sendList = new ArrayList<DGRecycleDataVo>(MAX_UPLOAD_SIZE);
		
		for (int i = 1; i <= paramList.size(); i++) {
			
			paramList.get(i - 1).setBottoleEnterprise(BOTTOLE_ENTERPRISE);
			sendList.add(paramList.get(i - 1));
			
			if (i % MAX_UPLOAD_SIZE == 0 || i == paramList.size()) {
				Map<String,Object> param = new HashMap<String, Object>();
				param.put("postData", JSONUtils.fromArrayObject(sendList));
//				log.info("-----回收气瓶记录:"+param);
				String result = HttpUtils.httpPostByTimeOut(SEND_RECYCLE_DATA_URL, param);
				log.info("-----回收气瓶记录:"+result);
				sendList.clear();
			}
			
		}
	}
	
	/**
	 * 气瓶数据
	 * @param paramList
	 */
	public static boolean sendBottleInfoData(List<DGBottleInfoDataVo> paramList) {
		boolean isSucc = true;
		
		for (int i = 0; i < paramList.size(); i++) {
			
			paramList.get(i).setEnterpriseName(BOTTOLE_ENTERPRISE);
			
		}
			
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("postData", JSONUtils.fromArrayObject(paramList));
//		log.info("-------气瓶数据,请求数据:"+param);
		String result = HttpUtils.httpPostByTimeOut(SEND_BOTTLEINFO_DATA_URL, param);
		log.info("-------气瓶数据:"+result);
		
		//判断气瓶数据是否上传成功
		JSONObject jsonResult = JSONObject.fromObject(result);
		Integer status = (int)jsonResult.get("status");
		if (0==status) {
			isSucc = false;
		}
		
		return isSucc;
	}

}
