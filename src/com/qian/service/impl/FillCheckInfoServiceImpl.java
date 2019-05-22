package com.qian.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.FillCheckInfoDaoImpl;
import com.qian.entity.FillCheckInfo;
import com.qian.service.FillCheckInfoService;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

@Service
@Transactional
public class FillCheckInfoServiceImpl implements FillCheckInfoService {

	@Autowired
	private FillCheckInfoDaoImpl fillCheckInfoDaoImpl;

	@Override
	public Map<String, Object> getPageList(List<String> l, Field filed, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();

		Long total = fillCheckInfoDaoImpl.getTotalNum(l, filed);
		List<FillCheckInfo> list = fillCheckInfoDaoImpl.getPageList(l, filed, page, rows);

		result.put("total", total);
		result.put("rows", list);

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<Object> add(FillCheckInfo fillingCheckInfo) {
		if (fillCheckInfoDaoImpl.insert(fillingCheckInfo) != 1) {
			return BaseDto.getFailResult(CommonCode.SYSTEM_EXCEPTION);
		}
		return BaseDto.getSuccessResult(null);
	}

	@Override
	public BaseDto<Object> edit(FillCheckInfo fillingCheckInfo, int entityId) {
		FillCheckInfo oldInfo = fillCheckInfoDaoImpl.findById(entityId);
		oldInfo.setFill_check_code(fillingCheckInfo.getFill_check_code());
		oldInfo.setFill_check_description(fillingCheckInfo.getFill_check_description());
		oldInfo.setBefore_or_after_fill(fillingCheckInfo.getBefore_or_after_fill());
		fillCheckInfoDaoImpl.update(oldInfo);
		return BaseDto.getSuccessResult(null);
	}

	@Override
	public BaseDto<Object> delByIds(String[] ids) {
		for (String id : ids) {
			FillCheckInfo checkInfo = fillCheckInfoDaoImpl.findById(Integer.parseInt(id));
			fillCheckInfoDaoImpl.delete(checkInfo);
		}
		return BaseDto.getSuccessResult(null);
	}

	@Override
	public List<FillCheckInfo> getAllList(List<String> l, Field filed) {
		
		return fillCheckInfoDaoImpl.getAllList(l, filed);
	}

}
