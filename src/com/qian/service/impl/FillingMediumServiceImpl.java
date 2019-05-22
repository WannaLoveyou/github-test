package com.qian.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.FillingMediumDaoImpl;
import com.qian.entity.FillingMedium;
import com.qian.service.FillingMediumService;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-27 下午6:35:13
 * @Description
 */
@Service
@Transactional
public class FillingMediumServiceImpl implements FillingMediumService {

	@Autowired
	private FillingMediumDaoImpl fillingMediumDaoImpl;

	@Override
	public List<FillingMedium> getAllList() {

		return fillingMediumDaoImpl.getAllList();
	}

	@Override
	public Map<String, Object> getPageList(List<String> l, Field field, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();

		Long total = fillingMediumDaoImpl.getTotalNum(l, field);
		List<FillingMedium> list = fillingMediumDaoImpl.getPageList(l, field, page, rows);

		result.put("total", total);
		result.put("rows", list);

		return result;
	}

	@Override
	public BaseDto<Object> add(FillingMedium fillingMedium) {
		fillingMediumDaoImpl.insert(fillingMedium);
		return BaseDto.getSuccessResult(null);
	}

	@Override
	public BaseDto<Object> edit(int entityId, FillingMedium fillingMedium) {
		FillingMedium oldFillingMedium = fillingMediumDaoImpl.findById(entityId);

		oldFillingMedium.setFilling_medium_name(fillingMedium.getFilling_medium_name());

		fillingMediumDaoImpl.update(oldFillingMedium);
		return BaseDto.getSuccessResult(null);
	}

	@Override
	public BaseDto<Object> delByIds(String[] ids) {
		for (String id : ids) {
			fillingMediumDaoImpl.delete(Integer.parseInt(id));
		}
		return BaseDto.getSuccessResult(null);
	}

}
