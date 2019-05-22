package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.PartFeeInfo;
import com.qian.util.SqlUtils;
import com.qian.vo.Field;
import com.qian.vo.PartSaleReportInfoVo;

@Component
public class PartFeeInfoDaoImpl extends HBaseDao<PartFeeInfo> {

	public List<PartFeeInfo> getAllList() {

		return selectFromHQL("from PartFeeInfo");
	};

	public List<PartFeeInfo> findByOrderId(int orderId) {

		Field field = new Field();
		field.addInt(orderId);

		List<PartFeeInfo> result = selectFromHQL(" from PartFeeInfo where orderInfo.id = ?", field);

		return result;
	}

	public List<PartSaleReportInfoVo> getAllOrderInfo(List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.PartSaleReportInfoVo(date_format(orderInfo.report_time,'%Y-%m-%d'),orderInfo.secondCategory.id,orderInfo.airBottleType.id,partType.id,number,price,totalprice)");

		sb.append(SqlUtils.initSearchConditionSql(l, "PartFeeInfo"));

		@SuppressWarnings("unchecked")
		List<PartSaleReportInfoVo> result = selectFromHQLByClass(sb.toString(), filed, PartSaleReportInfoVo.class);

		return result;
	}
}
