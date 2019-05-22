package com.qian.service;

import java.util.List;

import com.qian.vo.SZDeliveryDataVo;
import com.qian.vo.SZFillingDataVo;
import com.qian.vo.SZHandOverDataVo;
import com.qian.vo.SZRecycleDataVo;
import com.qian.vo.SZSupplyDataVo;

/**
 * 东莞城管局服务
 * 
 * @author Administrator
 *
 */
public interface SZPublicServicePlatformService {

	public List<SZFillingDataVo> getSZFillingDataList(long nowMillisecond);
	
	public List<SZSupplyDataVo> getSZSupplyDataList(long nowMillisecond);
	
	public List<SZDeliveryDataVo> getSZDeliveryDataList(long nowMillisecond);
	
	public List<SZHandOverDataVo> getSZHandOverDataList(long nowMillisecond);
	
	public List<SZRecycleDataVo> getSZRecycleDataList(long nowMillisecond);
	
	public void getSZBottleInfoDataList();

}
