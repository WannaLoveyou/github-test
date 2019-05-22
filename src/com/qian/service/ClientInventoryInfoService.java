package com.qian.service;

import java.util.List;

import com.qian.entity.ClientInventoryInfo;
import com.qian.mobile.entity.MobileAirBottleCheckEntity;
import com.qian.vo.BaseDto;
import com.qian.vo.ClientInventoryStatisticsVo;
import com.qian.vo.CoordinateVo;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-3-2 上午10:36:19
 * @Description
 */
public interface ClientInventoryInfoService {

	public ClientInventoryInfo findByClientIdAndBottleCode(MobileAirBottleCheckEntity entity);
	
	public BaseDto<List<CoordinateVo>> getClientCoordinates(List<String> l, Field field);

	public List<ClientInventoryStatisticsVo> getClientInventoryStatistics(List<String> l, Field filed);
}
