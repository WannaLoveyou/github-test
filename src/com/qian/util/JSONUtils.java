package com.qian.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.util.DocumentHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * <p>
 * JSON处理工具类
 * </p>
 * <p>
 * 创建日期: 2013-3-29
 * </p>
 * <p>
 * 版权: Copyright (c) 2013
 * </p>
 * <p>
 * 李朝阳
 * </p>
 * 
 * @version 1.0
 **/
public class JSONUtils {
	/**
	 * 默认的JsonConfig配置
	 * 
	 * @return
	 */
	private static JsonConfig getJsonConfig() {
		JsonConfig config = new JsonConfig();
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		// config.setExcludes(new String[]{"handler", "hibernateLazyInitializer"});
		return config;
	}

	/**
	 * 将对象转换为json，使用默认的JsonConfig配置
	 * 
	 * @param obj
	 * @return
	 */
	public static String fromObject(Object obj) {
		return fromObject(obj, getJsonConfig());
	}

	/**
	 * 将对象转换为json，使用默认的JsonConfig配置
	 * 
	 * @param obj
	 * @return
	 */
	public static String fromArrayObject(Object obj) {
		return fromArrayObject(obj, getJsonConfig());
	}

	/**
	 * 将对象转换为json，自定义JsonConfig配置
	 * 
	 * @param obj
	 * @param config
	 * @return
	 */
	public static String fromArrayObject(Object obj, JsonConfig config) {
		JSONArray json = JSONArray.fromObject(obj, config);
		return String.valueOf(json);
	}

	/**
	 * 将对象转换为json，自定义JsonConfig配置
	 * 
	 * @param obj
	 * @param config
	 * @return
	 */
	public static String fromObject(Object obj, JsonConfig config) {
		JSONObject json = JSONObject.fromObject(obj, config);
		return String.valueOf(json);
	}

	public static Map<String, Object> xmlToJSON(String xml) {
		Map<String, Object> objMap = new HashMap<String, Object>();
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			
			Document d = DocumentHelper.readDocument(is);
			NodeList nl = d.getElementsByTagName("xml");
			Node dd = nl.item(0);
			nl = dd.getChildNodes();
			for (int i = 0; i < nl.getLength(); i++) {
				objMap.put(nl.item(i).getNodeName(), nl.item(i).getTextContent());
			}
			return objMap;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
			}
		}
	}

	public static Map<String, Object> xmlToJSONFromHuMenSMS(String xml) {
		
		Map<String, Object> objMap = new HashMap<String, Object>();
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(xml.getBytes("gbk"));
			Document d = DocumentHelper.readDocument(is);
			NodeList nl = d.getElementsByTagName("Messages");
			Node dd = nl.item(0);
			nl = dd.getChildNodes();
			for (int i = 0; i < nl.getLength(); i++) {
				objMap.put(nl.item(i).getNodeName(), nl.item(i).getTextContent());
			}
			return objMap;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
			}
		}
	}
	
	/**
	 * 将jsonString 转换成实体对象
	 * 
	 * @param jsonString
	 * @param pojoCalss
	 * @return
	 */
	public static <T> T jsonToObject(String jsonString, Class<T> pojoCalss) {    
        try{    
            Object pojo;    
            net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(jsonString);    
            pojo = net.sf.json.JSONObject.toBean(jsonObject, pojoCalss);    
            return (T)pojo;    
        }catch(Exception ex){    
            ex.printStackTrace();    
                
            return null;    
        }    
    }  

}