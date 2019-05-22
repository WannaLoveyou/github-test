package com.qian.service;

import java.util.List;
import java.util.Map;

import com.qian.entity.FillCheckInfo;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

public interface FillCheckInfoService {

	public Map<String, Object> getPageList(List<String> l, Field filed, int page, int rows);

	public BaseDto<Object> add(FillCheckInfo fillCheckInfo);

	public BaseDto<Object> edit(FillCheckInfo fillCheckInfo, int entityId);

	public BaseDto<Object> delByIds(String ids[]);

	public List<FillCheckInfo> getAllList(List<String> l, Field filed);
}
