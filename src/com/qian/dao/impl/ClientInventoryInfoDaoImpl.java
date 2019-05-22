package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.ClientInventoryInfo;
import com.qian.util.SqlUtils;
import com.qian.vo.Field;
import com.qian.vo.ClientInventoryStatisticsVo;

/**
 * @author Lu Kongwen
 * @version Create time：2017-2-28 下午3:56:49
 * @Description
 */
@Component
public class ClientInventoryInfoDaoImpl extends HBaseDao<ClientInventoryInfo> {

	public ClientInventoryInfo findByAirBottleCode(String bottleCode) {
		
		Field field = new Field();
		field.addStr(bottleCode);

		List<ClientInventoryInfo> list = selectFromHQL(
				" from ClientInventoryInfo where airBottleInfo.air_bottle_code= ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public ClientInventoryInfo findByClientIdAndBottleCode(Integer clientId, String bottleCode) {
		
		Field field = new Field();
		field.addInt(clientId);
		field.addStr(bottleCode);

		List<ClientInventoryInfo> list = selectFromHQL(
				" from ClientInventoryInfo where clientInfo.id = ? and airBottleInfo.air_bottle_code = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public ClientInventoryInfo findByAirBottleId(Integer airBottleId) {
		
		Field field = new Field();
		field.addInt(airBottleId);
		
		List<ClientInventoryInfo> list = selectFromHQL(
				" from ClientInventoryInfo where airBottleInfo.id = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public ClientInventoryInfo findByClientIdAndBottleId(Integer clientId, Integer bottleId) {
		
		Field field = new Field();
		field.addInt(clientId);
		field.addInt(bottleId);

		List<ClientInventoryInfo> list = selectFromHQL(
				" from ClientInventoryInfo where clientInfo.id = ? and airBottleInfo.id = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
	
	public List<ClientInventoryStatisticsVo> getClientInventoryStatistics(List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.ClientInventoryStatisticsVo(clientInfo.id,clientInfo.secondCategory.second_category_name,clientInfo.client_code," +
				"clientInfo.client_name,clientInfo.clientType.client_type_name,clientInfo.mobile_tel_number_1," +
				"clientInfo.mobile_tel_number_2,clientInfo.fixed_tel_number_1,clientInfo.fixed_tel_number_2," +
				"clientInfo.client_address,airBottleInfo.airBottleType.id,airBottleInfo.airBottleType.air_bottle_specifications," +
				"airBottleInfo.airBottleBelong.blong_name,count(*))");

		sb.append(SqlUtils.initSearchConditionSql(l, "ClientInventoryInfo"));
		
		sb.append(" group by clientInfo.secondCategory.id,clientInfo.id,airBottleInfo.airBottleType.id,airBottleInfo.airBottleBelong.id");

		@SuppressWarnings("unchecked")
		List<ClientInventoryStatisticsVo> result = selectFromHQLByClass(sb.toString(), filed, ClientInventoryStatisticsVo.class);

		return result;
		
	}

}
