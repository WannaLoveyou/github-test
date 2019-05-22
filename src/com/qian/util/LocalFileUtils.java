package com.qian.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-18 上午11:13:40
 * @Description 本地配置表读取
 */
public class LocalFileUtils {

	private static final Logger log = Logger.getLogger(LocalFileUtils.class);

	public static String classesPath = LocalFileUtils.class.getResource("/").getPath();

	public final static String homePath = classesPath.substring(0, classesPath.indexOf("WEB-INF") + "WEB-INF".length());

	public static Map<Integer, String> orderStateMap;
	
	public static Map<Integer, String> clientAirBottleStateMap;

	public static void init() {

		ObjectMapper mapper = new ObjectMapper();

		try {
			initOrderStateMap(mapper, "order_state.json");
			initClientAirBottleStateMap(mapper, "client_air_bottle_state.json");
		} catch (Exception e) {
			log.error("本地配置表初始化失败：", e);
		}
	}

	private static void initClientAirBottleStateMap(ObjectMapper mapper, String filename) throws JsonParseException, JsonMappingException, IOException {
		
		String inputString = inputConfigStringFromFile(filename);

		clientAirBottleStateMap = mapper.readValue(inputString, new TypeReference<Map<Integer, String>>() {
		});
	}

	private static void initOrderStateMap(ObjectMapper mapper, String filename) throws JsonParseException, JsonMappingException, IOException {

		String inputString = inputConfigStringFromFile(filename);

		orderStateMap = mapper.readValue(inputString, new TypeReference<Map<Integer, String>>() {
		});
	}

	private static String inputConfigStringFromFile(String filename) throws UnsupportedEncodingException {

		String path = homePath + "/config/" + filename;

		File file = new File(path);

		try {
			return FileUtils.readFileToString(file);
		} catch (IOException e) {
			log.error("", e);
		}
		return null;
	}
}
