package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.code.ClientAirBottleReocrdStateCode;
import com.qian.dao.HBaseDao;
import com.qian.service.impl.ClientAirBottleRecord;
import com.qian.util.SqlUtils;
import com.qian.vo.ClientAirBottleRecordVo;
import com.qian.vo.ClientInventoryInfoVo;
import com.qian.vo.Field;

@Component
public class ClientAirBottleRecordDaoImpl extends HBaseDao<ClientAirBottleRecord> {

	public List<ClientAirBottleRecordVo> getRecordByReport(int clientId) {

		Field field = new Field();
		field.addInt(clientId);

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.tgb.vo.ClientAirBottleRecordVo(clientInfo.id,airBottleInfo.air_bottle_code,airBottleInfo.airBottleType.air_bottle_specifications,begin_time,end_time,state.state_description)");

		sb.append(" from ClientAirBottleRecord where clientInfo.id = ?");

		@SuppressWarnings("unchecked")
		List<ClientAirBottleRecordVo> result = selectFromHQLByClass(sb.toString(), field, ClientAirBottleRecord.class);

		return result;
	}

	public List<ClientAirBottleRecord> getRecordByLeast(int clientId) {
		Field field = new Field();
		field.addInt(clientId);
		return selectFromHQL(" from ClientAirBottleRecord where clientInfo.id=?  order by id desc ", field, 0, 20);
	}

	public ClientAirBottleRecord getRecordByClientIdAndBottleId(int clientId, int airBottleId) {

		Field field = new Field();
		field.addInt(clientId);
		field.addInt(airBottleId);

		List<ClientAirBottleRecord> list = selectFromHQL(" from ClientAirBottleRecord where clientInfo.id=? and airBottleInfo.id = ? order by id desc", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public ClientAirBottleRecord getNoReturnClientAirBottleRecord(String airBottleCode) {

		Field field = new Field();
		field.addStr(airBottleCode);
		field.addInt(ClientAirBottleReocrdStateCode.NO_RETURN);

		List<ClientAirBottleRecord> list = selectFromHQL(
				" from ClientAirBottleRecord where airBottleInfo.air_bottle_code = ? and state.id = ?  order by id desc", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public ClientAirBottleRecord getRecordByClientIdAndAirBottleCode(int clientId, String airBottleCode) {

		Field field = new Field();
		field.addInt(clientId);
		field.addStr(airBottleCode);
		field.addInt(ClientAirBottleReocrdStateCode.NO_RETURN);

		List<ClientAirBottleRecord> list = selectFromHQL(
				" from ClientAirBottleRecord where clientInfo.id=? and  airBottleInfo.air_bottle_code = ? and state.id = ? order by id desc", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public ClientAirBottleRecord getRecordByClientIdAndAirBottleId(int clientId, int bottleId) {

		Field field = new Field();
		field.addInt(clientId);
		field.addInt(bottleId);
		field.addInt(ClientAirBottleReocrdStateCode.NO_RETURN);

		List<ClientAirBottleRecord> list = selectFromHQL(
				" from ClientAirBottleRecord where clientInfo.id= ? and  airBottleInfo.id = ? and state.id = ? order by id desc", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public List<ClientInventoryInfoVo> getClientInventoryList(List<String> l, Field filed, Integer bottle_num) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.tgb.vo.ClientInventoryInfoVo(clientInfo.id,clientInfo.thirdCategory.secondCategory.second_category_name,clientInfo.client_code,clientInfo.client_name,clientInfo.mobile_tel_number_1,clientInfo.mobile_tel_number_2,clientInfo.fixed_tel_number_1,clientInfo.fixed_tel_number_2,clientInfo.client_address,clientInfo.clientType.client_type_name,airBottleInfo.airBottleType.id,airBottleInfo.airBottleType.air_bottle_specifications,count(*) as bottle_num)");

		sb.append(SqlUtils.initSearchConditionSql(l, "ClientAirBottleRecord"));

		sb.append(" group by clientInfo.id,airBottleInfo.airBottleType.id");

		if (bottle_num != null) {
			sb.append(" having count(*) >=").append(bottle_num);
		}

		@SuppressWarnings("unchecked")
		List<ClientInventoryInfoVo> result = selectFromHQLByClass(sb.toString(), filed, ClientInventoryInfoVo.class);

		return result;
	}

	public Integer getBottleNumByClientId(int clientId) {

		Field field = new Field();
		field.addInt(clientId);
		field.addInt(ClientAirBottleReocrdStateCode.NO_RETURN);

		Long result = getTotalFromHQL(" from ClientAirBottleRecord where clientInfo.id = ? and state.id = ?", field);

		return result.intValue();
	}

	public ClientAirBottleRecord getNoReturnClientAirBottleRecordByAirBottleId(Integer airBottleId) {
		
		Field field = new Field();
		field.addInt(airBottleId);
		field.addInt(ClientAirBottleReocrdStateCode.NO_RETURN);

		List<ClientAirBottleRecord> list = selectFromHQL(
				" from ClientAirBottleRecord where airBottleInfo.id = ? and state.id = ?  order by id desc", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

}
