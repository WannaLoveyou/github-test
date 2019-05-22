package com.qian.util;

import javax.servlet.ServletContextEvent;

import org.springframework.web.util.Log4jWebConfigurer;

/**
 * @author Lu Kongwen
 * @version Create time：2018-3-19 上午9:57:20
 * @Description
 */
public class Log4jUtils {

	public static void startUp(ServletContextEvent event){
		
		String log4jHome = PathUtils.getProperty("log4j.home");
		
		System.setProperty("log4j.home", log4jHome);
		Log4jWebConfigurer.initLogging(event.getServletContext());
	}
	
	public static void shutDown(ServletContextEvent event){
		Log4jWebConfigurer.shutdownLogging(event.getServletContext());
	}
}
