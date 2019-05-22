package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.VisitInfo;
import com.qian.vo.Field;

@Component
public class VisitInfoDaoImpl extends HBaseDao<VisitInfo> {

	public List<VisitInfo> getVisitInfo(int clientId) {

		Field field = new Field();
		field.addInt(clientId);
		return selectFromHQL(
				"from VisitInfo where clientInfo.id = ? order by id desc",
				field);

	}

	public List<VisitInfo> getVisitInfoPageList(List<String> list,
			Field filed, int page, int rows) {

		int flag = 0;

		StringBuffer hql = new StringBuffer();
		hql.append(" from VisitInfo ");

		for (String s : list) {

			if (flag == 0) {
				hql.append("where ");
				flag = 1;
			} else {
				hql.append(" and ");
			}

			hql.append(s);
		}
		hql.append(" order by id desc");
		

		return selectFromHQL(hql.toString(), filed, (page - 1) * rows, rows);
	}

	public Long getTotalNum(List<String> list, Field filed) {

		int flag = 0;

		StringBuffer hql = new StringBuffer();
		hql.append(" from VisitInfo ");

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
