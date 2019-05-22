package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.ClientInfo;
import com.qian.util.SqlUtils;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-9 下午3:43:11
 * @Description
 */
@Component
public class ClientInfoDaoImpl extends HBaseDao<ClientInfo> {

	public List<ClientInfo> getPageList(int page, int rows) {
		return selectFromHQL(" from ClientInfo", (page - 1) * rows, rows);
	}

	public List<ClientInfo> getAllList() {
		return selectFromHQL("from ClientInfo");
	};

	public long getTotalNum() {
		return getTotalFromHQL(" from ClientInfo");
	}

	public void delById(int id) {
		Field field = new Field();
		field.addInt(id);
		delete(" delete from client_imformation where id=?", field);
	}

	public void delByIds(String[] ids) {

		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete ClientInfo as c where c.id = ?", afield);

	}

	public List<ClientInfo> getByMobile(String mobile) {

		Field field = new Field();
		field.addStr(mobile);
		field.addStr(mobile);
		field.addStr(mobile);
		field.addStr(mobile);

		List<ClientInfo> list = selectFromHQL(
				" from ClientInfo where mobile_tel_number_1 =? or mobile_tel_number_2 =? or fixed_tel_number_1 =? or fixed_tel_number_2=? ", field);

		return list;
	}

	public long getAllCount(String mobile, String client_code, String client_name) {
		Field field = new Field();
		field.addStr(mobile);
		field.addStr(mobile);
		field.addStr(mobile);
		field.addStr(mobile);
		field.addStr(client_code);
		StringBuffer client_name_str = new StringBuffer();
		client_name_str.append("%").append(client_name).append("%");
		field.addStr(client_name_str.toString());

		long Count = getTotalFromHQL(
				" from ClientInfo where mobile_tel_number_1 =? or mobile_tel_number_2 =? or fixed_tel_number_1 =? or fixed_tel_number_2= ? or client_code = ? or client_name like ?",
				field);

		return Count;
	}

	public ClientInfo getClientNameByclientCode(String client_code) {
		Field field = new Field();
		field.addStr(client_code);
		List<ClientInfo> list = selectFromHQL("from ClientInfo where client_code= ?", field);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public Long getTotalNum(List<String> list, Field filed) {

		return getTotalFromHQL(SqlUtils.initSearchConditionSql(list, "ClientInfo"), filed);
	}

	public List<ClientInfo> getPageList(List<String> list, Field filed, int page, int rows) {
		return selectFromHQL(SqlUtils.initSearchConditionSql(list, "ClientInfo"), filed, (page - 1) * rows, rows);
	}

	public List<ClientInfo> getPageList(List<String> list, Field filed) {
		return selectFromHQL(SqlUtils.initSearchConditionSql(list, "ClientInfo"), filed);

	}

	public ClientInfo getClientInfoByClientCode(String client_code) {

		Field field = new Field();

		field.addStr(client_code);

		List<ClientInfo> list = selectFromHQL("from ClientInfo where client_code= ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public ClientInfo getClientInfoByCardCode(String cardCode) {

		Field field = new Field();

		field.addStr(cardCode);

		List<ClientInfo> list = selectFromHQL("from ClientInfo where card_code = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public ClientInfo getClientInfoByOldClientCode(String oldClientCode) {

		Field field = new Field();

		field.addStr(oldClientCode);

		List<ClientInfo> list = selectFromHQL("from ClientInfo where old_client_code = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public ClientInfo findByClientCode(String clientCode) {

		Field field = new Field();

		field.addStr(clientCode);

		List<ClientInfo> list = selectFromHQL("from ClientInfo where client_code = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public int getCardCodeCountNoOwn(String card_code, int clientId) {

		Field field = new Field();

		field.addStr(card_code);
		field.addInt(clientId);

		Long count = getTotalFromHQL("from ClientInfo where card_code = ? and id != ?", field);

		return count.intValue();

	}

	public List<ClientInfo> findByOpenid(String openid) {
		
		Field filed = new Field();
		filed.addStr(openid);

		List<ClientInfo> list = selectFromHQL("select c from ClientInfo as c, WeChatLoginInfo as w where c.id = w.clientInfo.id and w.wechat_openid=?", filed);

		return list;
	}

	public Map<Integer, Integer> getClientNumByStore(List<String> l, Field filed) {
		
		Map<Integer, Integer> result = new HashMap<Integer,Integer>();
		
		StringBuffer sb = new StringBuffer();

		sb.append("select new map(secondCategory.id as secondCategoryId,count(*) as num)");

		sb.append(SqlUtils.initSearchConditionSql(l, "ClientInfo"));
		
		sb.append(" group by secondCategory.id");
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = selectFromHQLByClass(sb.toString(), filed, Map.class);

		for(Map<String, Object> map : list){
			result.put(Integer.valueOf(String.valueOf(map.get("secondCategoryId"))), Integer.valueOf(String.valueOf(map.get("num"))));
		}
		
		return result;
	}
	
	public void disabled(String[] ids) {

		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("update ClientInfo as c set disabled_state=1 where c.id = ?", afield);

	}
}
