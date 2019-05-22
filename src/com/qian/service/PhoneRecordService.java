package com.qian.service;

import java.util.List;
import java.util.Map;

import com.qian.entity.PhoneRecord;
import com.qian.entity.PhoneRecordState;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

public interface PhoneRecordService {

	public BaseDto<Map<String, Object>> getPageList(List<String> l, Field field, int page, int rows);

	public BaseDto<Object> add(PhoneRecord record);

	public BaseDto<Object> callBack(int recordId);

	public BaseDto<Object> delByIds(String[] ids);

	public BaseDto<List<PhoneRecordState>> getAllStates();

}
