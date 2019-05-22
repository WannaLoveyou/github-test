package com.qian.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.AirBottleBelongDaoImpl;
import com.qian.entity.AirBottleBelong;
import com.qian.service.AirBottleBelongService;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2018-2-1 下午5:06:47
 * @Description
 */
@Service
@Transactional
public class AirBottleBelongServiceImpl implements AirBottleBelongService {

	@Autowired
	private AirBottleBelongDaoImpl airBottleBelongDaoImpl;

	@Override
	public List<AirBottleBelong> getAllList() {

		return airBottleBelongDaoImpl.getAllList();
	}

	@Override
	public BaseDto<Map<String, Object>> getPageList(List<String> l, Field field, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();

		Long total = airBottleBelongDaoImpl.getTotalNum(l, field);
		List<AirBottleBelong> list = airBottleBelongDaoImpl.getPageList(l, field, page, rows);

		result.put("total", total);
		result.put("rows", list);

		return BaseDto.getSuccessResult(result);
	}

	@Override
	public BaseDto<Object> delByIds(String ids) {

		String[] list = ids.split(",");
		
		
		for(String id: list){
			AirBottleBelong newAirBottleBelong = airBottleBelongDaoImpl.findById(Integer.parseInt(id));
			airBottleBelongDaoImpl.delete(newAirBottleBelong);
		}
		
		return BaseDto.getSuccessResult(null);
	}

	@Override
	public BaseDto<Object> add(AirBottleBelong airBottleBelong) {

		airBottleBelongDaoImpl.insert(airBottleBelong);

		return BaseDto.getSuccessResult(null);
	}

	@Override
	public BaseDto<Object> edit(AirBottleBelong airBottleBelong, int entityId) {
		
		AirBottleBelong newAirBottleBelong = airBottleBelongDaoImpl.findById(entityId);
		newAirBottleBelong.setAir_bottle_blong_code(airBottleBelong.getAir_bottle_blong_code());
		newAirBottleBelong.setBlong_name(airBottleBelong.getBlong_name());
		newAirBottleBelong.setUpload_cloud_platform(airBottleBelong.getUpload_cloud_platform());
		
		return BaseDto.getSuccessResult(null);
	}
}
