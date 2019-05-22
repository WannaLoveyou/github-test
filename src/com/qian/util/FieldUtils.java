package com.qian.util;

import java.util.ArrayList;
import java.util.List;

import com.qian.code.DisabledStateCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-2-20 上午11:28:52
 * @Description
 */
public class FieldUtils {

	public static List<Field> getDelFields(String[] idList) {

		List<Field> afield = new ArrayList<Field>();

		for (String s : idList) {

			Field field = new Field();
			field.addInt(DisabledStateCode.DISABLE);
			field.addInt(Integer.valueOf(s));

			afield.add(field);
		}

		return afield;
	}
}
