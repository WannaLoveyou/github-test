package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.InvoiceRecordState;

/**
 * @author Lu Kongwen
 * @version Create time：2017-10-14 下午3:29:02
 * @Description
 */
@Component
public class InvoiceRecordStateDaoImpl  extends HBaseDao<InvoiceRecordState> {

	public List<InvoiceRecordState> getAllList() {
		
		return selectFromHQL(" from InvoiceRecordState");
	}

}
