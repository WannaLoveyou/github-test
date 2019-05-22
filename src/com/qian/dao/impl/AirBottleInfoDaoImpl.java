package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.AirBottleInfo;
import com.qian.util.SqlUtils;
import com.qian.vo.DGBottleInfoDataVo;
import com.qian.vo.Field;
import com.qian.vo.SZBottleInfoDataVo;

@Component
public class AirBottleInfoDaoImpl extends HBaseDao<AirBottleInfo> {
	public List<AirBottleInfo> getPageList(int page, int rows) {
		return selectFromHQL(" from AirBottleInfo", (page - 1) * rows, rows);
	}

	public long getTotalNum() {
		return getTotalFromHQL(" from AirBottleInfo");
	}

	public void delById(int id) {
		Field field = new Field();
		field.addInt(id);
		delete(" delete from air_bottle_imformation where id=?", field);
	}

	public void delByIds(String[] ids) {

		List<Field> afield = new ArrayList<Field>();

		for (String s : ids) {
			Field field = new Field();
			field.addInt(Integer.parseInt(s));
			afield.add(field);
		}

		delete("delete AirBottleInfo as s where s.id = ?", afield);

	}

	public AirBottleInfo findByAirBottleCode(String code) {

		Field field = new Field();
		field.addStr(code);

		List<AirBottleInfo> list = selectFromHQL(" from AirBottleInfo where air_bottle_code= ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
	
	public AirBottleInfo findByAirBottleSealCode(String code) {

		Field field = new Field();
		field.addStr(code);

		List<AirBottleInfo> list = selectFromHQL(" from AirBottleInfo where air_bottle_seal_code = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public Long getTotalFromHQL(List<String> l, Field filed) {
		
		return getTotalFromHQL(SqlUtils.initSearchConditionSql(l, "AirBottleInfo"), filed);
	}

	public List<AirBottleInfo> getPageList(List<String> l, Field filed, int page, int rows) {
		return selectFromHQL(SqlUtils.initSearchConditionSql(l, "AirBottleInfo"), filed, (page - 1) * rows, rows);
	}

	public Integer getAirBottleTrackingRecordId(int airBottleId) {
		
		Field field = new Field();
		field.addInt(airBottleId);
		
		@SuppressWarnings("unchecked")
		List<Integer> result = selectFromHQLByClass("select finalAirBottleTrackingRecord.id from AirBottleInfo where id = ? ", field, Integer.class);
		
		if (result.size() > 0 && result.get(0) != null) {

			return result.get(0).intValue();
		}
		
		return null;
	}

	public boolean checkExistByAirBottleSealCode(String bottleCode) {

		Field field = new Field();
		field.addStr(bottleCode);
		long result = getTotalFromHQL(" from AirBottleInfo where air_bottle_seal_code= ? ", field);

		if (result > 0) {
			return true;
		}

		return false;
	}
	
	public List<AirBottleInfo> getBottleInfoDataList(List<String> l, Field filed,int limit){
		return selectFromHQL(SqlUtils.initSearchConditionSql(l, "AirBottleInfo"), filed, 0, limit);
	}
	
	public List<DGBottleInfoDataVo> getDGBottleInfoDataList(List<String> l, Field filed,int limit) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select new com.qian.vo.DGBottleInfoDataVo(" +
				"id," +
				"air_bottle_seal_code," +
				"air_bottle_code," +
				"airBottleType.publicServiceMaterialType.id," +
				"bottle_weight," +
				"productionUnit.production_unit_name," +
				"produce_time," +
				"scrap_time," +
				"check_time," +
				"next_check_time," +
				"detectionUnit.detection_unit_name," +
				"create_time," +
				"factory_number" +
				")");
		
		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleInfo"));
		
		@SuppressWarnings("unchecked")
		List<DGBottleInfoDataVo> result = selectFromHQLByClass(sb.toString(), filed,1,limit, DGBottleInfoDataVo.class);
		
		return result;
	}
	
	public List<SZBottleInfoDataVo> getSZBottleInfoDataList(List<String> l, Field filed,int limit) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select new com.qian.vo.SZBottleInfoDataVo(" +
				"id," +
				"air_bottle_seal_code," +
				"air_bottle_code," +
				"airBottleType.publicServiceMaterialType.id," +
				"bottle_weight," +
				"productionUnit.production_unit_name," +
				"produce_time," +
				"scrap_time," +
				"check_time," +
				"next_check_time," +
				"detectionUnit.detection_unit_name," +
				"create_time," +
				"factory_number," +
				"img1," +
				"img2" +
				")");
		
		sb.append(SqlUtils.initSearchConditionSql(l, "AirBottleInfo"));
		
		@SuppressWarnings("unchecked")
		List<SZBottleInfoDataVo> result = selectFromHQLByClass(sb.toString(), filed,1,limit, SZBottleInfoDataVo.class);
		
		return result;
	}

}
