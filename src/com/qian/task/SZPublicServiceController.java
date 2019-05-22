package com.qian.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qian.code.ModuleConfigurationCode;
import com.qian.entity.ModuleConfigurationInfo;
import com.qian.service.AirBottleInfoService;
import com.qian.service.ModuleConfigurationInfoService;
import com.qian.service.SZPublicServicePlatformService;
import com.qian.util.SZPublicServiceUtil;
import com.qian.vo.SZFillingDataVo;
import com.qian.vo.SZHandOverDataVo;
import com.qian.vo.SZRecycleDataVo;

@Component
@Lazy(false)
public class SZPublicServiceController {

	// private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private SZPublicServicePlatformService szPublicServicePlatformService;

	@Autowired
	private ModuleConfigurationInfoService moduleConfigurationInfoService;

	@Autowired
	private AirBottleInfoService airBottleInfoService;

	@Scheduled(cron = "0 0/30 * * * ? ")
//	@Scheduled(cron = "0 0/1 * * * ? ")
	@Transactional(readOnly = false)
	public void uploadFillingData() {
		// 判断是否开启数据上传
		ModuleConfigurationInfo moduleConfigurationInfo = moduleConfigurationInfoService.findById(ModuleConfigurationCode.SZ_PUBLIC_SERVICE_PLATFORM);
		if (moduleConfigurationInfo != null && moduleConfigurationInfo.getIs_open() == 1) {

			// 当前时间
			long nowMillisecond = System.currentTimeMillis();

			/** 充装数据 */
			List<SZFillingDataVo> result = szPublicServicePlatformService.getSZFillingDataList(nowMillisecond);
			// 发送数据至远程接口
			SZPublicServiceUtil.sendFillingData(result);

//			/** 供应记录 */
//			List<SZSupplyDataVo> supplys = szPublicServicePlatformService.getSZSupplyDataList(nowMillisecond);
//			// 发送数据至远程接口
//			SZPublicServiceUtil.sendSupplyData(supplys);
//
//			/** 配送记录 */
//			List<SZDeliveryDataVo> deliverys = szPublicServicePlatformService.getSZDeliveryDataList(nowMillisecond);
//			// 发送数据至远程接口
//			SZPublicServiceUtil.sendDeliveryData(deliverys);

			/** 交付记录 */
			List<SZHandOverDataVo> handOvers = szPublicServicePlatformService.getSZHandOverDataList(nowMillisecond);
			// 发送数据至远程接口
			SZPublicServiceUtil.sendHandOverData(handOvers);

			/** 回收气瓶记录 */
			List<SZRecycleDataVo> recycles = szPublicServicePlatformService.getSZRecycleDataList(nowMillisecond);
			// 发送数据至远程接口
			SZPublicServiceUtil.sendRecycleData(recycles);
		}
	}

	@Scheduled(cron = "0 0 0/1 * * ? ")
//	@Scheduled(cron = "0 0/1 * * * ? ")
	@Transactional(readOnly = false)
	public void uploadBottleInfoData() {
		// 判断是否开启数据上传
		ModuleConfigurationInfo moduleConfigurationInfo = moduleConfigurationInfoService.findById(ModuleConfigurationCode.SZ_PUBLIC_SERVICE_PLATFORM);
		if (moduleConfigurationInfo != null && moduleConfigurationInfo.getIs_open() == 1) {

			/** 气瓶数据 并且发送数据至远程接口 */
			szPublicServicePlatformService.getSZBottleInfoDataList();

		}
	}

}
