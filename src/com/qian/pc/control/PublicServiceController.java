package com.qian.pc.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.service.DGPublicServicePlatformService;
import com.qian.service.SZPublicServicePlatformService;
import com.qian.util.DGPublicServiceUtil;
import com.qian.util.SZPublicServiceUtil;
import com.qian.vo.BaseDto;
import com.qian.vo.DGDeliveryDataVo;
import com.qian.vo.DGFillingDataVo;
import com.qian.vo.DGHandOverDataVo;
import com.qian.vo.DGRecycleDataVo;
import com.qian.vo.DGSupplyDataVo;
import com.qian.vo.SZFillingDataVo;
import com.qian.vo.SZHandOverDataVo;
import com.qian.vo.SZRecycleDataVo;


/**
 * 公众平台数据上传controller
 * @author sc
 *
 */
@Controller
@RequestMapping("/publicService")
public class PublicServiceController {

	@Autowired
	private SZPublicServicePlatformService szPublicServicePlatformService;
	
	@Autowired
	private DGPublicServicePlatformService dgPublicServicePlatformService;
	
	/**
	 * 上传气瓶 sz
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "upSZAirBottles", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody BaseDto<String> upSZAirBottles(
			HttpServletRequest request,Map<String, Object> map) {

		/** 气瓶数据 并且发送数据至远程接口 */
		szPublicServicePlatformService.getSZBottleInfoDataList();
		
		return BaseDto.getSuccessResult(null);
	}
	
	/**
	 * 上传气瓶 dg
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "upDGAirBottles", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody BaseDto<String> upDGAirBottles(
			HttpServletRequest request,Map<String, Object> map) {
		
		/** 气瓶数据 并且发送数据至远程接口 */
		dgPublicServicePlatformService.getDGBottleInfoDataList();
		
		return BaseDto.getSuccessResult(null);
	}
	
	/**
	 * 上传流转记录dg
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "upDGFlowInfo", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody BaseDto<String> upDGFlowInfo(
			HttpServletRequest request,Map<String, Object> map) {
		
		// 当前时间
		long nowMillisecond = System.currentTimeMillis();

		/** 充装数据 */
		List<DGFillingDataVo> result = dgPublicServicePlatformService.getDGFillingDataList(nowMillisecond);
		// 发送数据至远程接口
		DGPublicServiceUtil.sendFillingData(result);

		/** 供应记录 */
		List<DGSupplyDataVo> supplys = dgPublicServicePlatformService.getDGSupplyDataList(nowMillisecond);
		// 发送数据至远程接口
		DGPublicServiceUtil.sendSupplyData(supplys);

		/** 配送记录 */
		List<DGDeliveryDataVo> deliverys = dgPublicServicePlatformService.getDGDeliveryDataList(nowMillisecond);
		// 发送数据至远程接口
		DGPublicServiceUtil.sendDeliveryData(deliverys);

		/** 交付记录 */
		List<DGHandOverDataVo> handOvers = dgPublicServicePlatformService.getDGHandOverDataList(nowMillisecond);
		// 发送数据至远程接口
		DGPublicServiceUtil.sendHandOverData(handOvers);

		/** 回收气瓶记录 */
		List<DGRecycleDataVo> recycles = dgPublicServicePlatformService.getDGRecycleDataList(nowMillisecond);
		// 发送数据至远程接口
		DGPublicServiceUtil.sendRecycleData(recycles);
		
		return BaseDto.getSuccessResult(null);
	}
	
	/**
	 * 上传流转记录sz
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "upSZFlowInfo", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody BaseDto<String> upSZFlowInfo(
			HttpServletRequest request,Map<String, Object> map) {
		
		// 当前时间
		long nowMillisecond = System.currentTimeMillis();

		/** 充装数据 */
		List<SZFillingDataVo> result = szPublicServicePlatformService.getSZFillingDataList(nowMillisecond);
		// 发送数据至远程接口
		SZPublicServiceUtil.sendFillingData(result);

		/** 交付记录 */
		List<SZHandOverDataVo> handOvers = szPublicServicePlatformService.getSZHandOverDataList(nowMillisecond);
		// 发送数据至远程接口
		SZPublicServiceUtil.sendHandOverData(handOvers);

		/** 回收气瓶记录 */
		List<SZRecycleDataVo> recycles = szPublicServicePlatformService.getSZRecycleDataList(nowMillisecond);
		// 发送数据至远程接口
		SZPublicServiceUtil.sendRecycleData(recycles);
		
		return BaseDto.getSuccessResult(null);
	}

}
