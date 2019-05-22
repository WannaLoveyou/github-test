package com.qian.service;

import java.util.List;

import com.qian.vo.DGDeliveryDataVo;
import com.qian.vo.DGFillingDataVo;
import com.qian.vo.DGHandOverDataVo;
import com.qian.vo.DGRecycleDataVo;
import com.qian.vo.DGSupplyDataVo;

/**
 * 东莞城管局服务
 * 
 * @author Administrator
 *
 */
public interface DGPublicServicePlatformService {

	public List<DGFillingDataVo> getDGFillingDataList(long nowMillisecond);
	
	public List<DGSupplyDataVo> getDGSupplyDataList(long nowMillisecond);
	
	public List<DGDeliveryDataVo> getDGDeliveryDataList(long nowMillisecond);
	
	public List<DGHandOverDataVo> getDGHandOverDataList(long nowMillisecond);
	
	public List<DGRecycleDataVo> getDGRecycleDataList(long nowMillisecond);
	
	public void getDGBottleInfoDataList();

}
