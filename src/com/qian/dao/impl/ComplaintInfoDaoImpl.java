package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.ComplaintInfo;
import com.qian.vo.Field;
@Component
public class ComplaintInfoDaoImpl extends HBaseDao<ComplaintInfo> {
	
public List<ComplaintInfo> getRepairInfo(int clientId){
		
	    Field field = new Field();
	    field.addInt(clientId);
		return selectFromHQL("from ComplaintInfo where clientInfo.id = ? order by complaint_time desc",field);
		
	}

public List<ComplaintInfo> getRepairInfoPageList(List<String> list, Field filed, int page, int rows) {

	int flag = 0;

	StringBuffer hql = new StringBuffer();
	hql.append(" from ComplaintInfo ");

	for (String s : list) {

		if (flag == 0) {
			hql.append("where ");
			flag = 1;
		} else {
			hql.append(" and ");
		}

		hql.append(s);
	}

	return selectFromHQL(hql.toString(), filed, (page - 1) * rows, rows);
}

public Long getTotalNum(List<String> list, Field filed) {

	int flag = 0;

	StringBuffer hql = new StringBuffer();
	hql.append(" from ComplaintInfo ");

	for (String s : list) {

		if (flag == 0) {
			hql.append("where ");
			flag = 1;
		} else {
			hql.append(" and ");
		}

		hql.append(s);
	}

	return getTotalFromHQL(hql.toString(), filed);
}


}
