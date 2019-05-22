package com.qian.task;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qian.service.CloudDataService;

/**
 * 云数据上传定时任务
 * @author sc
 *
 */
@Component
@Lazy(false)
public class CloudDataTaskController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private CloudDataService cloudDataService;

	/**
	 * 定时任务
	 * 搜索一定数量的钢瓶数据发送到云端
	 * 每20分钟执行一次（正式）
	 * @Scheduled(cron = "0 0/20 * * * ? ")
	 * 每1分钟执行一次（测试）
	 * @Scheduled(cron = "0 0/1 * * * ? ")
	 */
	@Scheduled(cron = "0 0/20 * * * ? ")
	@Transactional(readOnly=false)
	public void upGas() {
		Map<String, String> resMap = cloudDataService.findGasCylinderDataSendToCloud();
		String msg = resMap.get("msg");
		logger.info(msg);
	}
	
	
	/**
	 * 每天凌晨2点，没25秒执行一次
	 */
//	@Scheduled(cron = "0/25 * 2 * * ? ")
//	@Transactional(readOnly=false)
//	public void upCheck() {
//		Map<String, String> resMap = cloudDataService.findCheckDataSendToCloud(1000);
//		String code = resMap.get("code");
//		String msg = "";
//		if ("0".equals(code)) {
//			msg = resMap.get("msg");
//		}else{
//			msg = resMap.get("msg");
//		}
//		logger.info(msg);
//	}
	
	
	/**
	 * 每天凌晨3点，没25秒执行一次
	 */
//	@Scheduled(cron = "0/25 * 3 * * ? ")
//	@Transactional(readOnly=false)
//	public void upFilling() {
//		Map<String, String> resMap = cloudDataService.findFillingDataSendToCloud(1000);
//		String code = resMap.get("code");
//		String msg = "";
//		if ("0".equals(code)) {
//			msg = resMap.get("msg");
//		}else{
//			msg = resMap.get("msg");
//		}
//		logger.info(msg);
//	}
	
//	@Scheduled(cron = "0/5 * * * * ? ")
//	@Transactional(readOnly=false)
//	public void test(){
//		System.out.println(new Date()+"测试定时任务");
//	}
}
