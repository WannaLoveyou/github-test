package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.ICCardRecord;
import com.qian.util.SqlUtils;
import com.qian.vo.Field;
import com.qian.vo.ICCardRecordReportVo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-27 上午10:46:54
 * @Description
 */
@Component
public class ICCardRecordDaoImpl extends HBaseDao<ICCardRecord> {

	public List<ICCardRecordReportVo> getAllICCardRecord(List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.tgb.vo.ICCardRecordReportVo(clientInfo.client_code,clientInfo.client_name,clientInfo.thirdCategory.secondCategory.second_category_name,card_code,operator.id,create_time,state.state_description)");

		sb.append(SqlUtils.initSearchConditionSql(l, "ICCardRecord"));

		@SuppressWarnings("unchecked")
		List<ICCardRecordReportVo> result = selectFromHQLByClass(sb.toString(), filed, ICCardRecordReportVo.class);

		return result;
	}

}
