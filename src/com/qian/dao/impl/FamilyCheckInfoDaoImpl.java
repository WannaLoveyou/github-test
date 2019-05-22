package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.FamilyCheckInfo;
import com.qian.util.SqlUtils;
import com.qian.vo.CheckOperatorFamilyCheckInfoVo;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-9 下午3:09:46
 * @Description
 */
@Component
public class FamilyCheckInfoDaoImpl extends HBaseDao<FamilyCheckInfo> {

	public List<FamilyCheckInfo> getPageList(List<String> l, Field filed, int page, int rows) {
		
		return selectFromHQL(SqlUtils.initSearchConditionSql(l, "FamilyCheckInfo"), filed, (page - 1) * rows, rows);
	}

	public Long getTotalNum(List<String> list, Field filed) {
		
		return getTotalFromHQL(SqlUtils.initSearchConditionSql(list, "FamilyCheckInfo"), filed);
	}
	
	public List<CheckOperatorFamilyCheckInfoVo> getFamilyCheckClientNumByStore(List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.CheckOperatorFamilyCheckInfoVo(check_operator.secondCategory.id,check_operator.id,check_operator.full_name,count(*),count(DISTINCT clientInfo.id))");

		sb.append(SqlUtils.initSearchConditionSql(l, "FamilyCheckInfo"));

		sb.append(" group by check_operator.id");

		@SuppressWarnings("unchecked")
		List<CheckOperatorFamilyCheckInfoVo> result = selectFromHQLByClass(sb.toString(), filed, CheckOperatorFamilyCheckInfoVo.class);

		return result;
	}

}
