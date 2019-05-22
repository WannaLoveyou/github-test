package com.qian.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.code.PhoneRecordStateCode;
import com.qian.dao.impl.PhoneRecordDaoImpl;
import com.qian.dao.impl.PhoneRecordStateDaoImpl;
import com.qian.entity.PhoneRecord;
import com.qian.entity.PhoneRecordState;
import com.qian.service.PhoneRecordService;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

@Service
@Transactional
public class PhoneRecordServiceImpl implements PhoneRecordService {

	@Autowired
	private PhoneRecordDaoImpl phoneRecordDaoImpl;

	@Autowired
	private PhoneRecordStateDaoImpl phoneRecordStateDaoImpl;

	@Override
	public BaseDto<Map<String, Object>> getPageList(List<String> l, Field field, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = phoneRecordDaoImpl.getTotalNum(l, field);
		List<PhoneRecord> list = phoneRecordDaoImpl.getPageListOrderByCallIn(l, field, page, rows);

		result.put("total", total);
		result.put("rows", list);

		return BaseDto.getSuccessResult(result);
	}

	@Override
	public BaseDto<Object> add(PhoneRecord record) {
		phoneRecordDaoImpl.insert(record);
		return BaseDto.getSuccessResult(null);
	}

	@Override
	public BaseDto<Object> callBack(int recordId) {
		PhoneRecord record = phoneRecordDaoImpl.findById(recordId);
		if (record.getPhoneRecordState().getId() == PhoneRecordStateCode.HAS_CALLED_BACK) {
			return BaseDto.getFailResult(CommonCode.THE_RECORD_CAN_NOT_HANDLE);
		}
		record.setCallBackTime(new Date());
		record.setPhoneRecordState(new PhoneRecordState(PhoneRecordStateCode.HAS_CALLED_BACK));
		phoneRecordDaoImpl.update(record);
		return BaseDto.getSuccessResult(null);
	}

	@Override
	public BaseDto<Object> delByIds(String[] ids) {
		for (String id : ids) {
			phoneRecordDaoImpl.delete(Integer.parseInt(id));
		}
		return BaseDto.getSuccessResult(null);
	}

	@Override
	public BaseDto<List<PhoneRecordState>> getAllStates() {
		return BaseDto.getSuccessResult(phoneRecordStateDaoImpl.getAllList());
	}

}
