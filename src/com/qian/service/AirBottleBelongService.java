package com.qian.service;

import java.util.List;
import java.util.Map;

import com.qian.entity.AirBottleBelong;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2018-2-1 下午5:06:38
 * @Description
 */
public interface AirBottleBelongService {

	public List<AirBottleBelong> getAllList();

	public BaseDto<Map<String, Object>> getPageList(List<String> l, Field field, int page, int rows);

	public BaseDto<Object> delByIds(String ids);

	public BaseDto<Object> add(AirBottleBelong airBottleBelong);

	public BaseDto<Object> edit(AirBottleBelong airBottleBelong, int entityId);

}
