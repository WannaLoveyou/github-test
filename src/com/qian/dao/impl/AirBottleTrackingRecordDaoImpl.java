package com.qian.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.AirBottleTrackingRecord;
import com.qian.util.SqlUtils;
import com.qian.vo.AirBottleTrackingRecordReportVo;
import com.qian.vo.AirBottleTrackingRecordCountVo;
import com.qian.vo.DGDeliveryDataVo;
import com.qian.vo.DGFillingDataVo;
import com.qian.vo.DGHandOverDataVo;
import com.qian.vo.DGRecycleDataVo;
import com.qian.vo.DGSupplyDataVo;
import com.qian.vo.SZDeliveryDataVo;
import com.qian.vo.SZFillingDataVo;
import com.qian.vo.SZHandOverDataVo;
import com.qian.vo.SZRecycleDataVo;
import com.qian.vo.SZSupplyDataVo;
import com.qian.vo.HeavyBottleOutToDeliveryManInWarehouseVo;
import com.qian.vo.Field;
import com.qian.vo.FillCheckRecordVo;
import com.qian.vo.ReturnBottleInfoVo;

/**
 * @author Lu Kongwen
 * @version Create time：2015-12-18 下午4:07:37
 * @Description
 */

@Component
public class AirBottleTrackingRecordDaoImpl extends
		HBaseDao<AirBottleTrackingRecord> {

	public List<AirBottleTrackingRecord> findByAirBottleId(Integer id) {

		Field field = new Field();
		field.addInt(id);

		List<AirBottleTrackingRecord> list = selectFromHQL(
				" from AirBottleTrackingRecord where airBottleInfo.id = ?",
				field);

		return list;
	}

	public List<AirBottleTrackingRecord> findByAirBottleIdDesc(Integer id) {

		Field field = new Field();
		field.addInt(id);

		List<AirBottleTrackingRecord> list = selectFromHQL(
				" from AirBottleTrackingRecord where airBottleInfo.id = ? order by id desc",
				field);

		return list;
	}

	public List<AirBottleTrackingRecordReportVo> getAllAirBottleFinalTrackingRecord(
			List<String> l, Field filed, int page, int rows) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.AirBottleTrackingRecordReportVo(airBottleInfo.air_bottle_code,airBottleInfo.air_bottle_seal_code,airBottleInfo.airBottleType.air_bottle_specifications,secondCategory.id,deliveryMan.id,operator.id,warehouseInfo.id,clientInfo.id,state.state_description,create_time)");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		@SuppressWarnings("unchecked")
		List<AirBottleTrackingRecordReportVo> airBottleTrackingRecordVoList = selectFromHQLByClass(
				sb.toString(), filed, (page - 1) * rows, rows,
				AirBottleTrackingRecordReportVo.class);

		return airBottleTrackingRecordVoList;
	}

	public Long getAirBottleFinalTrackingRecordTotalNum(List<String> l,
			Field filed) {

		return getTotalFromHQL(
				SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"),
				filed);

	}

	public List<ReturnBottleInfoVo> getStoreGetBottleNum(List<String> l,
			Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.ReturnBottleInfoVo('',secondCategory.id,airBottleInfo.airBottleType.id,0,0L,count(*))");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		sb.append(" group by secondCategory.id,airBottleInfo.airBottleType.id");

		@SuppressWarnings("unchecked")
		List<ReturnBottleInfoVo> airBottleTrackingRecordVoList = selectFromHQLByClass(
				sb.toString(), filed, ReturnBottleInfoVo.class);

		return airBottleTrackingRecordVoList;
	}

	public List<ReturnBottleInfoVo> getStoreReturnBottleNum(List<String> l,
			Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.ReturnBottleInfoVo('',secondCategory.id,airBottleInfo.airBottleType.id,0,count(*),0L)");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		sb.append(" group by secondCategory.id,airBottleInfo.airBottleType.id");

		@SuppressWarnings("unchecked")
		List<ReturnBottleInfoVo> airBottleTrackingRecordVoList = selectFromHQLByClass(
				sb.toString(), filed, ReturnBottleInfoVo.class);

		return airBottleTrackingRecordVoList;
	}

	public List<ReturnBottleInfoVo> getStoreGetBottleDetailNum(List<String> l,
			Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.ReturnBottleInfoVo(date_format(create_time,'%Y-%m-%d'),secondCategory.id,airBottleInfo.airBottleType.id,0,0L,count(*))");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		sb.append(" group by secondCategory.id,airBottleInfo.airBottleType.id,date_format(create_time,'%Y-%m-%d')");

		@SuppressWarnings("unchecked")
		List<ReturnBottleInfoVo> airBottleTrackingRecordVoList = selectFromHQLByClass(
				sb.toString(), filed, ReturnBottleInfoVo.class);

		return airBottleTrackingRecordVoList;
	}

	public List<ReturnBottleInfoVo> getStoreReturnBottleDetailNum(
			List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.ReturnBottleInfoVo(date_format(create_time,'%Y-%m-%d'),secondCategory.id,airBottleInfo.airBottleType.id,0,count(*),0L)");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		sb.append(" group by secondCategory.id,airBottleInfo.airBottleType.id,date_format(create_time,'%Y-%m-%d')");

		@SuppressWarnings("unchecked")
		List<ReturnBottleInfoVo> airBottleTrackingRecordVoList = selectFromHQLByClass(
				sb.toString(), filed, ReturnBottleInfoVo.class);

		return airBottleTrackingRecordVoList;
	}

	public List<ReturnBottleInfoVo> getDeliveryManGetBottleNum(List<String> l,
			Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.ReturnBottleInfoVo('',0,airBottleInfo.airBottleType.id,deliveryMan.id,0L,count(*))");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		sb.append(" group by deliveryMan.id,airBottleInfo.airBottleType.id");

		@SuppressWarnings("unchecked")
		List<ReturnBottleInfoVo> airBottleTrackingRecordVoList = selectFromHQLByClass(
				sb.toString(), filed, ReturnBottleInfoVo.class);

		return airBottleTrackingRecordVoList;
	}

	public List<ReturnBottleInfoVo> getDeliveryManReturnBottleNum(
			List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.ReturnBottleInfoVo('',0,airBottleInfo.airBottleType.id,deliveryMan.id,count(*),0L)");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		sb.append(" group by deliveryMan.id,airBottleInfo.airBottleType.id");

		@SuppressWarnings("unchecked")
		List<ReturnBottleInfoVo> airBottleTrackingRecordVoList = selectFromHQLByClass(
				sb.toString(), filed, ReturnBottleInfoVo.class);

		return airBottleTrackingRecordVoList;
	}

	public List<ReturnBottleInfoVo> getDeliveryManGetBottleDetailNum(
			List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.ReturnBottleInfoVo(date_format(create_time,'%Y-%m-%d'),0,airBottleInfo.airBottleType.id,deliveryMan.id,0L,count(*))");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		sb.append(" group by deliveryMan.id,airBottleInfo.airBottleType.id,date_format(create_time,'%Y-%m-%d')");

		@SuppressWarnings("unchecked")
		List<ReturnBottleInfoVo> airBottleTrackingRecordVoList = selectFromHQLByClass(
				sb.toString(), filed, ReturnBottleInfoVo.class);

		return airBottleTrackingRecordVoList;
	}

	public List<ReturnBottleInfoVo> getDeliveryManReturnBottleDetailNum(
			List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.ReturnBottleInfoVo(date_format(create_time,'%Y-%m-%d'),0,airBottleInfo.airBottleType.id,deliveryMan.id,count(*),0L)");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		sb.append(" group by deliveryMan.id,airBottleInfo.airBottleType.id,date_format(create_time,'%Y-%m-%d')");

		@SuppressWarnings("unchecked")
		List<ReturnBottleInfoVo> airBottleTrackingRecordVoList = selectFromHQLByClass(
				sb.toString(), filed, ReturnBottleInfoVo.class);

		return airBottleTrackingRecordVoList;
	}

	public List<ReturnBottleInfoVo> getWarehouseGetBottleDetailNum(
			List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.ReturnBottleInfoVo(date_format(create_time,'%Y-%m-%d'),0,airBottleInfo.airBottleType.id,0,0L,count(*))");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		sb.append(" group by airBottleInfo.airBottleType.id,date_format(create_time,'%Y-%m-%d')");

		@SuppressWarnings("unchecked")
		List<ReturnBottleInfoVo> airBottleTrackingRecordVoList = selectFromHQLByClass(
				sb.toString(), filed, ReturnBottleInfoVo.class);

		return airBottleTrackingRecordVoList;
	}

	public List<ReturnBottleInfoVo> getWarehouseReturnBottleDetailNum(
			List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.ReturnBottleInfoVo(date_format(create_time,'%Y-%m-%d'),0,airBottleInfo.airBottleType.id,0,count(*),0L)");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		sb.append(" group by airBottleInfo.airBottleType.id,date_format(create_time,'%Y-%m-%d')");

		@SuppressWarnings("unchecked")
		List<ReturnBottleInfoVo> airBottleTrackingRecordVoList = selectFromHQLByClass(
				sb.toString(), filed, ReturnBottleInfoVo.class);

		return airBottleTrackingRecordVoList;
	}

	public List<ReturnBottleInfoVo> getWarehouseForceReturnBottleDetailNum(
			List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.ReturnBottleInfoVo(date_format(create_time,'%Y-%m-%d'),0,airBottleInfo.airBottleType.id,0,count(*),0L)");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		sb.append(" group by airBottleInfo.airBottleType.id,date_format(create_time,'%Y-%m-%d')");

		@SuppressWarnings("unchecked")
		List<ReturnBottleInfoVo> airBottleTrackingRecordVoList = selectFromHQLByClass(
				sb.toString(), filed, ReturnBottleInfoVo.class);

		return airBottleTrackingRecordVoList;
	}

	public Integer getMultipleSendNum() {

		String sql = "SELECT count(*) as count FROM air_bottle_tracking_record WHERE parent_id IN (select parent_id FROM (select `parent_id` from air_bottle_tracking_record group by `parent_id` having count(parent_id) >1 AND parent_id is not null) as table1) AND id NOT IN (select final_tracking_record_id FROM (select final_tracking_record_id from air_bottle_imformation  where final_tracking_record_id is not null) as table2)";

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> lists = selectFromSQLByMap(sql);

		if (lists.size() > 0) {
			return Integer.valueOf(lists.get(0).get("count").toString());
		}

		return 0;

	}

	public List<Map<String, Object>> getMultipleSendRecord() {

		String sql = "SELECT air_bottle_id,state_id,count(*) as count,date_format(create_time,'%Y-%m-%d') as create_time FROM air_bottle_tracking_record WHERE parent_id IN (select parent_id FROM (select `parent_id` from air_bottle_tracking_record group by `parent_id` having count(parent_id) >1 AND parent_id is not null) as table1) AND id NOT IN (select final_tracking_record_id FROM (select final_tracking_record_id from air_bottle_imformation  where final_tracking_record_id is not null) as table2) GROUP BY air_bottle_id,state_id,date_format(create_time,'%Y-%m-%d')";

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> lists = selectFromSQLByMap(sql);

		return lists;
	}

	public void delMultipleSendRecord() {

		String sql = "DELETE  FROM air_bottle_tracking_record WHERE parent_id IN (select parent_id FROM (select `parent_id` from air_bottle_tracking_record group by `parent_id` having count(parent_id) >1 AND parent_id is not null) as table1) AND id NOT IN (select final_tracking_record_id FROM (select final_tracking_record_id from air_bottle_imformation  where final_tracking_record_id is not null) as table2)";

		updateSQL(sql);
	}

	public List<ReturnBottleInfoVo> getExceptionReturnBottleNum(List<String> l,
			Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.ReturnBottleInfoVo(date_format(create_time,'%Y-%m-%d'),0,airBottleInfo.airBottleType.id,0,count(*),0L)");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		sb.append(" group by airBottleInfo.airBottleType.id,date_format(create_time,'%Y-%m-%d')");

		@SuppressWarnings("unchecked")
		List<ReturnBottleInfoVo> airBottleTrackingRecordVoList = selectFromHQLByClass(
				sb.toString(), filed, ReturnBottleInfoVo.class);

		return airBottleTrackingRecordVoList;
	}

	public List<ReturnBottleInfoVo> getExceptionReturnPassBottleNum(
			List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.ReturnBottleInfoVo(date_format(create_time,'%Y-%m-%d'),0,airBottleInfo.airBottleType.id,0,count(*),0L)");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		sb.append(" group by airBottleInfo.airBottleType.id,date_format(create_time,'%Y-%m-%d')");

		@SuppressWarnings("unchecked")
		List<ReturnBottleInfoVo> airBottleTrackingRecordVoList = selectFromHQLByClass(
				sb.toString(), filed, ReturnBottleInfoVo.class);

		return airBottleTrackingRecordVoList;
	}

	public List<FillCheckRecordVo> getFillCheckRecordVoList(List<String> l,
			Field field, int page, int rows) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.FillCheckRecordVo(create_time,airBottleInfo.air_bottle_seal_code,airBottleInfo.airBottleType.volume,airBottleInfo.produce_time,airBottleInfo.check_time,operator.id,operator.full_name,airBottleInfo.airBottleType.bottle_weight,airBottleInfo.airBottleType.total_weight)");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		@SuppressWarnings("unchecked")
		List<FillCheckRecordVo> fillCheckRecordVoList = selectFromHQLByClass(
				sb.toString(), field, (page - 1) * rows, rows,
				FillCheckRecordVo.class);

		return fillCheckRecordVoList;
	}

	public Long getFillCheckRecordVoTotalNum(List<String> l, Field field) {

		return getTotalFromHQL(
				SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"),
				field);

	}

	public Date getFirstRecordDate(int airBottleId) {

		Field field = new Field();
		field.addInt(airBottleId);

		@SuppressWarnings("unchecked")
		List<Date> result = selectFromHQLByClass(
				"select create_time from AirBottleTrackingRecord where airBottleInfo.id = ?",
				field, 0, 1, Date.class);

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<Map<String, Object>> getFillCheckRecordMapList(List<String> l,
			Field field, int page, int rows) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new map(date_format(create_time,'%Y-%m-%d') as fill_date , airBottleInfo.air_bottle_seal_code as air_bottle_seal_code , airBottleInfo.airBottleType.volume as volume , airBottleInfo.airBottleType.bottle_weight as bottle_weight , airBottleInfo.airBottleType.total_weight as total_weight , operator.id as fill_operator , operator.full_name as check_operator)");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = selectFromHQLByClass(sb.toString(),
				field, (page - 1) * rows, rows, Map.class);

		return list;
	}

	public List<DGFillingDataVo> getDGFillingDataList(List<String> l,
			Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.DGFillingDataVo("
				+ "airBottleInfo.air_bottle_seal_code,"
				+ "airBottleInfo.air_bottle_code,"
				+ "airBottleInfo.airBottleType.publicServiceMaterialType.id,"
				+ "warehouseInfo.filling_station_id," + "create_time" + ")");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		@SuppressWarnings("unchecked")
		List<DGFillingDataVo> result = selectFromHQLByClass(sb.toString(),
				filed, DGFillingDataVo.class);

		return result;
	}

	public List<DGSupplyDataVo> getDGSupplyDataList(List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.DGSupplyDataVo("
				+ "airBottleInfo.air_bottle_seal_code,"
				+ "airBottleInfo.air_bottle_code,"
				+ "airBottleInfo.airBottleType.publicServiceMaterialType.id,"
				+ "secondCategory.second_category_name," + "create_time" + ")");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		@SuppressWarnings("unchecked")
		List<DGSupplyDataVo> result = selectFromHQLByClass(sb.toString(),
				filed, DGSupplyDataVo.class);

		return result;
	}

	public List<DGDeliveryDataVo> getDGDeliveryDataList(List<String> l,
			Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.DGDeliveryDataVo("
				+ "airBottleInfo.air_bottle_seal_code,"
				+ "airBottleInfo.air_bottle_code,"
				+ "airBottleInfo.airBottleType.publicServiceMaterialType.id,"
				+ "operator.full_name," + "create_time,"
				+ "secondCategory.second_category_name" + ")");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		@SuppressWarnings("unchecked")
		List<DGDeliveryDataVo> result = selectFromHQLByClass(sb.toString(),
				filed, DGDeliveryDataVo.class);

		return result;
	}

	public List<DGHandOverDataVo> getDGHandOverDataList(List<String> l,
			Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.DGHandOverDataVo(airBottleInfo.air_bottle_seal_code,"
				+ "airBottleInfo.air_bottle_code,"
				+ "airBottleInfo.airBottleType.publicServiceMaterialType.id,"
				+ "operator.full_name,"
				+ "create_time,"
				+ "clientInfo.client_name," + "clientInfo.card_code" + ")");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		@SuppressWarnings("unchecked")
		List<DGHandOverDataVo> result = selectFromHQLByClass(sb.toString(),
				filed, DGHandOverDataVo.class);

		return result;
	}

	public List<DGRecycleDataVo> getDGRecycleDataList(List<String> l,
			Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.DGRecycleDataVo("
				+ "airBottleInfo.air_bottle_seal_code,"
				+ "airBottleInfo.air_bottle_code,"
				+ "airBottleInfo.airBottleType.publicServiceMaterialType.id,"
				+ "operator.full_name," + "create_time,"
				+ "clientInfo.client_name" + ")");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		@SuppressWarnings("unchecked")
		List<DGRecycleDataVo> result = selectFromHQLByClass(sb.toString(),
				filed, DGRecycleDataVo.class);

		return result;
	}
	
	public List<SZFillingDataVo> getSZFillingDataList(List<String> l,
			Field filed) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select new com.qian.vo.SZFillingDataVo("
				+ "airBottleInfo.air_bottle_seal_code,"
				+ "airBottleInfo.air_bottle_code,"
				+ "airBottleInfo.airBottleType.publicServiceMaterialType.id,"
				+ "warehouseInfo.filling_station_id," + "create_time" + ")");
		
		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));
		
		@SuppressWarnings("unchecked")
		List<SZFillingDataVo> result = selectFromHQLByClass(sb.toString(),
				filed, SZFillingDataVo.class);
		
		return result;
	}
	
	public List<SZSupplyDataVo> getSZSupplyDataList(List<String> l, Field filed) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select new com.qian.vo.SZSupplyDataVo("
				+ "airBottleInfo.air_bottle_seal_code,"
				+ "airBottleInfo.air_bottle_code,"
				+ "airBottleInfo.airBottleType.publicServiceMaterialType.id,"
				+ "secondCategory.second_category_name," + "create_time" + ")");
		
		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));
		
		@SuppressWarnings("unchecked")
		List<SZSupplyDataVo> result = selectFromHQLByClass(sb.toString(),
				filed, SZSupplyDataVo.class);
		
		return result;
	}
	
	public List<SZDeliveryDataVo> getSZDeliveryDataList(List<String> l,
			Field filed) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select new com.qian.vo.SZDeliveryDataVo("
				+ "airBottleInfo.air_bottle_seal_code,"
				+ "airBottleInfo.air_bottle_code,"
				+ "airBottleInfo.airBottleType.publicServiceMaterialType.id,"
				+ "operator.full_name," + "create_time,"
				+ "secondCategory.second_category_name" + ")");
		
		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));
		
		@SuppressWarnings("unchecked")
		List<SZDeliveryDataVo> result = selectFromHQLByClass(sb.toString(),
				filed, SZDeliveryDataVo.class);
		
		return result;
	}
	
	public List<SZHandOverDataVo> getSZHandOverDataList(List<String> l,
			Field filed) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select new com.qian.vo.SZHandOverDataVo(airBottleInfo.air_bottle_seal_code,"
				+ "airBottleInfo.air_bottle_code,"
				+ "airBottleInfo.airBottleType.publicServiceMaterialType.id,"
				+ "operator.full_name,"
				+ "create_time,"
				+ "clientInfo.client_name," + "clientInfo.card_code" + ")");
		
		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));
		
		@SuppressWarnings("unchecked")
		List<SZHandOverDataVo> result = selectFromHQLByClass(sb.toString(),
				filed, SZHandOverDataVo.class);
		
		return result;
	}
	
	public List<SZRecycleDataVo> getSZRecycleDataList(List<String> l,
			Field filed) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select new com.qian.vo.SZRecycleDataVo("
				+ "airBottleInfo.air_bottle_seal_code,"
				+ "airBottleInfo.air_bottle_code,"
				+ "airBottleInfo.airBottleType.publicServiceMaterialType.id,"
				+ "operator.full_name," + "create_time,"
				+ "clientInfo.client_name" + ")");
		
		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));
		
		@SuppressWarnings("unchecked")
		List<SZRecycleDataVo> result = selectFromHQLByClass(sb.toString(),
				filed, SZRecycleDataVo.class);
		
		return result;
	}

	public List<AirBottleTrackingRecordCountVo> getTotalInWarehouseGroupByDeliveryMan(
			List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.AirBottleTrackingRecordCountVo("
				+ "airBottleInfo.id," 
				+ "deliveryMan.id," 
				+ "deliveryMan.full_name," 
				+ "operator.id," 
				+ "operator.full_name," 
				+ "warehouseInfo.id," 
				+ "warehouseInfo.warehouse_name," 
				+ "state.id," 
				+ "create_time," 
				+ "parent.id," 
				+ "is_final," 
				+ "count(*)" + ")");

		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleTrackingRecord"));

		sb.append(" group by deliveryMan.id");

		@SuppressWarnings("unchecked")
		List<AirBottleTrackingRecordCountVo> result = selectFromHQLByClass(
				sb.toString(), filed,
				HeavyBottleOutToDeliveryManInWarehouseVo.class);

		return result;
	}
}
