package com.qian.util;

import java.util.List;

/**
 * @author Lu Kongwen
 * @version Create time：2016-1-9 上午10:53:12
 * @Description
 */
public class SqlUtils {

	public static String initManyEntiySearchConditionNumSql(List<String> list, String returnEntiy, String searchEntiy) {

		int flag = 0;

		StringBuffer hql = new StringBuffer();
		hql.append(" from " + searchEntiy);

		for (String s : list) {

			if (flag == 0) {
				hql.append(" where ");
				flag = 1;
			} else {
				hql.append(" and ");
			}

			hql.append(s);
		}
		return hql.toString();
	}

	public static String initManyEntiySearchConditionSql(List<String> list, String returnEntiy, String searchEntiy) {

		int flag = 0;

		StringBuffer hql = new StringBuffer();
		hql.append(" select " + returnEntiy);
		hql.append(" from " + searchEntiy);

		for (String s : list) {

			if (flag == 0) {
				hql.append(" where ");
				flag = 1;
			} else {
				hql.append(" and ");
			}

			hql.append(s);
		}
		return hql.toString();
	}

	public static String initSearchConditionSql(List<String> list, String Entiy) {

		int flag = 0;

		StringBuffer hql = new StringBuffer();
		hql.append(" from " + Entiy);

		for (String s : list) {

			if (flag == 0) {
				hql.append(" where ");
				flag = 1;
			} else {
				hql.append(" and ");
			}

			hql.append(s);
		}
		return hql.toString();
	}

	public static String initSpecialConditionsSearchConditionSql(List<String> list, String Entiy, String specialConditionsSql) {

		int flag = 0;

		StringBuffer hql = new StringBuffer();
		hql.append(" from " + Entiy).append(" ").append(specialConditionsSql);

		for (String s : list) {

			if (flag == 0) {
				hql.append(" where ");
				flag = 1;
			} else {
				hql.append(" and ");
			}

			hql.append(s);
		}
		return hql.toString();
	}

	public static String initDelEntitySql(String Entiy) {

		StringBuffer hql = new StringBuffer();
		hql.append(" update " + Entiy + " e set e.disabled_state= ? where e.id= ? ");

		return hql.toString();
	}

	public static String initRealDelEntityHql(String entityName) {

		StringBuffer hql = new StringBuffer();
		hql.append(" delete from " + entityName +" where id = ? ");

		return hql.toString();
	}
	
	public static String initUpdateConditionsSql(List<String> set_l, List<String> where_l, String Entiy) {

		int flag = 0;
		int flag2 = 0;

		StringBuffer hql = new StringBuffer();
		hql.append(" UPDATE " + Entiy);

		for (String s : set_l) {

			if (flag == 0) {
				hql.append(" SET ");
				flag = 1;
			} else {
				hql.append(" , ");
			}

			hql.append(s);
		}
		
		for (String s : where_l) {
			
			if (flag2 == 0) {
				hql.append(" WHERE ");
				flag2 = 1;
			} else {
				hql.append(" AND ");
			}
			
			hql.append(s);
		}
		return hql.toString();
	}
	
}
