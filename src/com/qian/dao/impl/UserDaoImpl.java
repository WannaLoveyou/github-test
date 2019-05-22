package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.User;
import com.qian.util.SqlUtils;
import com.qian.vo.Field;

@Component
public class UserDaoImpl extends HBaseDao<User> {
	/**
	 * 获取用户列表
	 */
	public List<User> getPageList(int page, int num) {
		return selectFromHQL(" from User", (page - 1) * num, num);
	}

	public List<User> getAllList() {
		return selectFromHQL(" from User");
	}

	public User findByUserName(String username) {

		Field filed = new Field();
		filed.addStr(username);

		List<User> list = selectFromHQL(" from User where userName=? ", filed);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;

	}

	public long getAllCount() {
		return getTotalFromHQL(" from User");
	}

	public void delById(int id) {
		Field field = new Field();
		field.addInt(id);
		delete(" delete from User where id=?", field);
	}

	public void delByIds(String[] ids) {

		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete User as u where u.id = ?", afield);

	}

	public List<User> getDeliveryManList(Integer secondId) {


		Field filed = new Field();
		filed.addInt(secondId);
		filed.addInt(1);

		List<User> list = selectFromHQL("from User where secondCategory.id = ? and is_store_delivery_man = ?", filed);

		return list;
	}
	
	public List<User> getDeliveryManList() {


		Field filed = new Field();
		filed.addInt(1);

		List<User> list = selectFromHQL("from User where is_store_delivery_man = ?", filed);

		return list;
	}

	public User findByCardCode(String cardCode) {

		Field filed = new Field();
		filed.addStr(cardCode);

		List<User> list = selectFromHQL(" from User where card_code = ? ", filed);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public List<User> getPageList(List<String> l, Field filed, int page, int rows) {

		List<User> list = selectFromHQL(SqlUtils.initManyEntiySearchConditionSql(l, " distinct u", "User as u, UserRole as ur"), filed, (page - 1) * rows, rows);

		return list;
	}

	public long getAllCount(List<String> l, Field filed) {

		long num = getDistinctTotalFromHQL(SqlUtils.initManyEntiySearchConditionNumSql(l, "u", "User as u, UserRole as ur"), filed, "distinct u");

		return num;
	}

	public List<User> getAllDeliveryManList() {

		Integer roleId = 2;

		Field filed = new Field();
		filed.addInt(roleId);

		List<User> list = selectFromHQL("select u from User as u, UserRole as ur where u.id = ur.user.id and  ur.role.id = ?", filed);

		return list;
	}

	public User findDeliveryManByCode(String deliveryManCode) {

		Field field = new Field();
		field.addStr(deliveryManCode);

		List<User> list = selectFromHQL(" from User where user_code = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public List<User> getIsDeliveryManList(Integer secondCategoryId) {
		

		Field filed = new Field();
		filed.addInt(secondCategoryId);
		filed.addInt(1);

		List<User> list = selectFromHQL("from User where secondCategory.id = ? and is_store_delivery_man = ?", filed);

		return list;
	}



}
