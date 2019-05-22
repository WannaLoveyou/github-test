package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.FillCheckRecordDetails;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2018-10-23 下午9:09:34
 * @Description
 */
@Component
public class FillCheckRecordDetailsDaoImpl extends HBaseDao<FillCheckRecordDetails> {

	public List<FillCheckRecordDetails> findByFillCheckRecordId(int fillCheckRecordId) {

		Field field = new Field();
		field.addInt(fillCheckRecordId);

		List<FillCheckRecordDetails> list = selectFromHQL(" from FillCheckRecordDetails where fillCheckRecord.id = ?", field);

		return list;
	}
}
