package com.qian.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Lu Kongwen
 * @version Create time：2018-3-16 下午8:48:46
 * @Description
 */
public class PathUtils {

	public static String PROJECT_NAME;
	
	private static Properties project;
	
	private static Properties common;

	public static void init() {

		initProjectName();
		initCommonPath();
				
	}
	
	public static String getProperty(String key){
		String property = common.getProperty(PathUtils.PROJECT_NAME + "." + key);
		return property == null ? null : property.trim(); 
	}
	
	private static void initProjectName() {

		String path = PathUtils.class.getResource("/properties").getPath();
		Properties pro = new Properties();
		try {
			File dic = new File(path + "/project.properties");
			pro.load(new FileInputStream(dic));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		project = pro;

		PROJECT_NAME = project.getProperty("project.name").trim();

	}

	private static void initCommonPath() {

		String path = PathUtils.class.getResource("/properties").getPath();
		Properties pro = new Properties();
		try {
			File dic = new File(path + "/" + PROJECT_NAME + ".properties");
			pro.load(new FileInputStream(dic));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		common = pro;

	}
}
