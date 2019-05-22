package com.qian.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.ClientInventoryInfoDaoImpl;
import com.qian.entity.ClientInventoryInfo;
import com.qian.mobile.entity.MobileAirBottleCheckEntity;
import com.qian.service.ClientInventoryInfoService;
import com.qian.util.AirBottleCodeInitUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.ClientInventoryStatisticsVo;
import com.qian.vo.CoordinateVo;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-3-2 上午10:36:32
 * @Description
 */
@Service
@Transactional
public class ClientInventoryInfoServiceImpl implements ClientInventoryInfoService {

	@Autowired
	private ClientInventoryInfoDaoImpl clientInventoryInfoDaoImpl;

	@Override
	public ClientInventoryInfo findByClientIdAndBottleCode(MobileAirBottleCheckEntity entity) {

		return clientInventoryInfoDaoImpl.findByClientIdAndBottleCode(entity.getClientId(),
				AirBottleCodeInitUtils.initCode(entity.getBottleCode()));
	}

	@Override
	public BaseDto<List<CoordinateVo>> getClientCoordinates(List<String> l, Field field) {
		List<CoordinateVo> result = new ArrayList<>();
		List<ClientInventoryInfo> infos = clientInventoryInfoDaoImpl.getAllList(l, field);
		for (ClientInventoryInfo info : infos) {
			result.add(new CoordinateVo(info));
		}
		return BaseDto.getSuccessResult(result);
	}
	
	@Override
	public List<ClientInventoryStatisticsVo> getClientInventoryStatistics(List<String> l, Field filed){
		return clientInventoryInfoDaoImpl.getClientInventoryStatistics(l, filed);
	}

}
