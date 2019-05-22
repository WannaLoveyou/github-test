package com.qian.service;

import java.util.List;
import java.util.Map;

import com.qian.entity.FillingMedium;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-27 下午6:32:01
 * @Description
 */
public interface FillingMediumService {

	public List<FillingMedium> getAllList();

	public Map<String, Object> getPageList(List<String> l, Field field, int page, int rows);

	public BaseDto<Object> add(FillingMedium fillingMedium);

	public BaseDto<Object> edit(int entityId, FillingMedium fillingMedium);

	public BaseDto<Object> delByIds(String[] ids);

}
