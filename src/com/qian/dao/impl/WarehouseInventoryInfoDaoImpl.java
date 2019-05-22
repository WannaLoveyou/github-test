package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.code.AirBottleStateCode;
import com.qian.dao.HBaseDao;
import com.qian.entity.AirBottleInfo;
import com.qian.entity.WarehouseInfo;
import com.qian.entity.WarehouseInventoryInfo;
import com.qian.util.SqlUtils;
import com.qian.vo.AirBottleInfoReportDetailVo;
import com.qian.vo.Field;
import com.qian.vo.WarehouseInventoryInfoVo;

/**
 * @author Lu Kongwen
 * @version Create time：2017-2-24 上午9:34:25
 * @Description
 */
@Component
public class WarehouseInventoryInfoDaoImpl extends HBaseDao<WarehouseInventoryInfo> {

	public WarehouseInventoryInfo findByAirBottleCode(String bottleCode) {

		Field field = new Field();
		field.addStr(bottleCode);

		List<WarehouseInventoryInfo> list = selectFromHQL(
				" from WarehouseInventoryInfo where airBottleInfo.air_bottle_code = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
	
	public WarehouseInventoryInfo findHeavyBottleByAirBottleCode(String bottleCode) {

		Field field = new Field();
		field.addStr(bottleCode);
		field.addInt(AirBottleStateCode.HEAVY_BOTTLE);

		List<WarehouseInventoryInfo> list = selectFromHQL(
				" from WarehouseInventoryInfo where airBottleInfo.air_bottle_code = ? and airBottleState.id = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public WarehouseInventoryInfo findByWarehouseIdAndBottleCode(int warehouseId,String bottleCode) {
		
		Field field = new Field();
		field.addInt(warehouseId);
		field.addStr(bottleCode);
		
		List<WarehouseInventoryInfo> list = selectFromHQL(
				" from WarehouseInventoryInfo where warehouseInfo.id = ? and airBottleInfo.air_bottle_code = ?", field);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public WarehouseInventoryInfo findByAirBottleInfoAndWarehouseInfo(AirBottleInfo airBottleInfo, WarehouseInfo warehouseInfo) {

		Field field = new Field();
		field.addInt(airBottleInfo.getId());
		field.addInt(warehouseInfo.getId());
		List<WarehouseInventoryInfo> list = selectFromHQL(" from WarehouseInventoryInfo where airBottleInfo.id = ? and warehouseInfo.id = ? ", field);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public WarehouseInventoryInfo findByAirBottleId(Integer airBottleId) {
		
		Field field = new Field();
		field.addInt(airBottleId);

		List<WarehouseInventoryInfo> list = selectFromHQL(
				" from WarehouseInventoryInfo where airBottleInfo.id = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public WarehouseInventoryInfo findByWarehouseIdAndBottleCode(Integer warehouseId, String bottleCode, int airBottleState, int airBottleInventoryState) {

		Field field = new Field();
		field.addInt(warehouseId);
		field.addStr(bottleCode);
		field.addInt(airBottleState);
		field.addInt(airBottleInventoryState);
		
		List<WarehouseInventoryInfo> list = selectFromHQL(" from WarehouseInventoryInfo where warehouseInfo.id = ? and airBottleInfo.air_bottle_code = ? and airBottleState.id = ? and airBottleInventoryState.id = ?",
				field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public WarehouseInventoryInfo findByWarehouseIdAndBottleId(Integer warehouseId, Integer bottleId, int airBottleState, int airBottleInventoryState) {
		
		Field field = new Field();
		field.addInt(warehouseId);
		field.addInt(bottleId);
		field.addInt(airBottleState);
		field.addInt(airBottleInventoryState);
		
		List<WarehouseInventoryInfo> list = selectFromHQL(" from WarehouseInventoryInfo where warehouseInfo.id = ? and airBottleInfo.id = ? and airBottleState.id = ? and airBottleInventoryState.id = ?",
				field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
	
	public List<AirBottleInfoReportDetailVo> getDetailsInventoryInfo(List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.AirBottleInfoReportDetailVo(airBottleInfo.air_bottle_code,airBottleInfo.air_bottle_seal_code,date_format(create_time,'%Y-%m-%d %h:%i'),operator.full_name)");

		sb.append(SqlUtils.initSearchConditionSql(l, "WarehouseInventoryInfo"));

		@SuppressWarnings("unchecked")
		List<AirBottleInfoReportDetailVo> result = selectFromHQLByClass(sb.toString(), filed, AirBottleInfoReportDetailVo.class);

		return result;
		
	}

	public List<WarehouseInventoryInfoVo> getWarehouseInventoryInfoVo(List<String> l, Field filed) {
		
		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.WarehouseInventoryInfoVo(warehouseInfo.id,airBottleInfo.airBottleType.id,airBottleState.id,count(*))");

		sb.append(SqlUtils.initSearchConditionSql(l, "WarehouseInventoryInfo"));
		
		sb.append(" group by warehouseInfo.id,airBottleInfo.airBottleType.id,airBottleState.id");

		@SuppressWarnings("unchecked")
		List<WarehouseInventoryInfoVo> result = selectFromHQLByClass(sb.toString(), filed, WarehouseInventoryInfoVo.class);

		return result;
	}

	

}
