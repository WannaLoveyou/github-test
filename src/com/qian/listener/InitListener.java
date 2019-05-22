package com.qian.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.qian.cache.MobileLoginCache;
import com.qian.util.Log4jUtils;
import com.qian.util.PathUtils;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-18 上午11:06:54
 * @Description 系统初始化监听器
 */
public class InitListener implements ServletContextListener {

	// 项目启动时调用
	public void contextInitialized(ServletContextEvent arg0) {

		// 优先
		PathUtils.init();

		MobileLoginCache.init();

		Log4jUtils.startUp(arg0);

	}

	// 项目关闭时调用
	public void contextDestroyed(ServletContextEvent arg0) {

		Log4jUtils.shutDown(arg0);
	}

}
