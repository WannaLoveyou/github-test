package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.RepairInfo;
import com.qian.vo.Field;
@Component
public class RepairInfoDaoImpl extends HBaseDao<RepairInfo> {
	
	public List<RepairInfo> getRepairInfo(int clientId){
		
		Field field = new Field();
		field.addInt(clientId);
		return selectFromHQL("from RepairInfo where clientInfo.id = ? order by accept_time desc", field);
		
	}
	
	public List<RepairInfo> getRepairInfoAllList(){
		
		return selectFromHQL("from RepairInfo");
		
	}
	
	public List<RepairInfo> getRepairInfoPageList(List<String> list, Field filed, int page, int rows) {

		int flag = 0;

		StringBuffer hql = new StringBuffer();
		hql.append(" from RepairInfo ");

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
		hql.append(" from RepairInfo ");

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
