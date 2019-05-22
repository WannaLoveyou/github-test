package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.ClientAirBottleTypeFee;
import com.qian.util.SqlUtils;
import com.qian.vo.Field;

@Component
public class ClientAirBottleTypeFeeDaoImpl extends HBaseDao<ClientAirBottleTypeFee> {
	public List<ClientAirBottleTypeFee> getPageList(List<String> l, Field filed, int page, int rows) {

		return selectFromHQL(SqlUtils.initSearchConditionSql(l, "ClientAirBottleTypeFee"), filed, (page - 1) * rows, rows);

	}

	public Long getTotalNum(List<String> l, Field filed) {

		return getTotalFromHQL(SqlUtils.initSearchConditionSql(l, "ClientAirBottleTypeFee"), filed);

	}

	public void delByIds(String[] ids) {

		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete ClientAirBottleTypeFee as a where a.id = ?", afield);

	}

	public List<ClientAirBottleTypeFee> getAllList() {

		return selectFromHQL(" from ClientAirBottleTypeFee");
	}

	public Double getFee(int airBottleTypeId, int clientId) {

		List<ClientAirBottleTypeFee> result = selectFromHQL("from ClientAirBottleTypeFee  where air_bottle_type_id=" + airBottleTypeId + " and client_id="
				+ clientId);
		if (result.size() > 0) {
			return result.get(0).getDelivery_fee();
		}
		return null;
	};

	public ClientAirBottleTypeFee getDeliveryandCheckFee(int airBottleTypeId, int clientId) {
		List<ClientAirBottleTypeFee> result = selectFromHQL("from ClientAirBottleTypeFee  where air_bottle_type_id=" + airBottleTypeId + " and client_id="
				+ clientId);
		if (result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	public int getClientAirBottleTypeFeeId(int airBottleTypeId, int clientId) {

		Long result = getTotalFromHQL("from ClientAirBottleTypeFee  where air_bottle_type_id=" + airBottleTypeId + " and client_id=" + clientId);

		return result.intValue();
	}

	public int getClientAirBottleTypeFeeIdByEdit(int clientAirBottleTypeFeeId, int airBottleTypeId, int clientId) {

		Long result = getTotalFromHQL("from ClientAirBottleTypeFee  where id!=" + clientAirBottleTypeFeeId + "and air_bottle_type_id=" + airBottleTypeId
				+ " and client_id=" + clientId);

		return result.intValue();
	}

	public List<ClientAirBottleTypeFee> findByClientId(int clientId) {

		Field field = new Field();
		field.addInt(clientId);

		List<ClientAirBottleTypeFee> result = selectFromHQL("from ClientAirBottleTypeFee  where  client_id = ?", field);

		return result;
	}
}
