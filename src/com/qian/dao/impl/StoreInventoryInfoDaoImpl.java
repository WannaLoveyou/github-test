package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.code.AirBottleInventoryStateCode;
import com.qian.code.AirBottleStateCode;
import com.qian.dao.HBaseDao;
import com.qian.entity.AirBottleInfo;
import com.qian.entity.SecondCategory;
import com.qian.entity.StoreInventoryInfo;
import com.qian.entity.User;
import com.qian.util.SqlUtils;
import com.qian.vo.AirBottleInfoReportDetailVo;
import com.qian.vo.Field;
import com.qian.vo.StoreInventoryInfoVo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-5-6 下午3:27:55
 * @Description
 */
@Component
public class StoreInventoryInfoDaoImpl extends HBaseDao<StoreInventoryInfo> {

	
	public StoreInventoryInfo findByAirBottleInfo(String bottleCode) {

		Field field = new Field();
		field.addStr(bottleCode);
		
		List<StoreInventoryInfo> list = selectFromHQL(" from StoreInventoryInfo where airBottleInfo.air_bottle_code = ?", field);
		
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		
		return null;
	}

	public StoreInventoryInfo findByAirBottleInfoAndSecondCategory(AirBottleInfo airBottleInfo, SecondCategory secondCategory) {

		Field field = new Field();
		field.addInt(airBottleInfo.getId());
		field.addInt(secondCategory.getId());
		List<StoreInventoryInfo> list = selectFromHQL(" from StoreInventoryInfo where airBottleInfo.id = ? and secondCategory.id = ? ", field);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public StoreInventoryInfo checkHeavyBottleFromStore(String bottleCode, int bottleTypeId, User user) {

		Field field = new Field();
		field.addStr(bottleCode);
		field.addInt(bottleTypeId);
		field.addInt(user.getSecondCategory().getId());

		List<StoreInventoryInfo> list = selectFromHQL(
				" from StoreInventoryInfo where airBottleInfo.air_bottle_code = ? and airBottleInfo.airBottleType.id = ? and secondCategory.id = ? ", field);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public Long getMyAirBottlesTotalNum(List<String> l, Field filed) {

		return getTotalFromHQL(SqlUtils.initSearchConditionSql(l, "StoreInventoryInfo"), filed);
	}

	public List<StoreInventoryInfo> getMyAirBottlesPageList(List<String> l, Field filed, int page, int rows) {

		return selectFromHQL(SqlUtils.initSearchConditionSql(l, "StoreInventoryInfo"), filed, (page - 1) * rows, rows);
	}

	public StoreInventoryInfo checkHeavyBottleFromStoreByBottleCode(String bottleCode, User user) {

		Field field = new Field();
		field.addStr(bottleCode);
		field.addInt(user.getSecondCategory().getId());
		field.addInt(AirBottleStateCode.HEAVY_BOTTLE);
		field.addInt(AirBottleInventoryStateCode.ALREADY_RECEIVE);

		List<StoreInventoryInfo> list = selectFromHQL(
				" from StoreInventoryInfo where airBottleInfo.air_bottle_code = ? and secondCategory.id = ? and airBottleState.id = ? and airBottleInventoryState.id = ?",
				field);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public StoreInventoryInfo checkEmptyBottleFromStoreByBottleCode(String bottleCode, User user) {

		Field field = new Field();
		field.addStr(bottleCode);
		field.addInt(user.getSecondCategory().getId());
		field.addInt(AirBottleStateCode.EMPTY_BOTTLE);
		field.addInt(AirBottleInventoryStateCode.ALREADY_RECEIVE);

		List<StoreInventoryInfo> list = selectFromHQL(
				" from StoreInventoryInfo where airBottleInfo.air_bottle_code = ? and secondCategory.id = ? and airBottleState.id = ? and airBottleInventoryState.id = ?",
				field);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public StoreInventoryInfo checkHeavyBottleFromStoreByBottleCodeAndBottleType(String bottleCode, int bottleTypeId, User user) {

		Field field = new Field();
		field.addStr(bottleCode);
		field.addInt(user.getSecondCategory().getId());
		field.addInt(AirBottleStateCode.HEAVY_BOTTLE);
		field.addInt(bottleTypeId);
		field.addInt(AirBottleInventoryStateCode.ALREADY_RECEIVE);

		List<StoreInventoryInfo> list = selectFromHQL(
				" from StoreInventoryInfo where airBottleInfo.air_bottle_code = ? and secondCategory.id = ? and airBottleState.id = ? and airBottleInfo.airBottleType.id = ? and airBottleInventoryState.id = ?",
				field);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<StoreInventoryInfoVo> getMyAirBottlesPageList(List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.tgb.vo.StoreInventoryInfoVo(secondCategory.id,airBottleInfo.airBottleType.id,airBottleState.id)");

		sb.append(SqlUtils.initSearchConditionSql(l, "StoreInventoryInfo"));

		@SuppressWarnings("unchecked")
		List<StoreInventoryInfoVo> result = selectFromHQLByClass(sb.toString(), filed, StoreInventoryInfoVo.class);

		return result;
		
	}

	public StoreInventoryInfo findStoreInventoryInfoBySecondCategoryIdAndCode(int secondCategoryId, String bottleCode) {
		
		Field field = new Field();
		field.addInt(secondCategoryId);
		field.addStr(bottleCode);
		field.addInt(AirBottleStateCode.HEAVY_BOTTLE);

		List<StoreInventoryInfo> list = selectFromHQL(
				" from StoreInventoryInfo where secondCategory.id = ? and airBottleInfo.air_bottle_code = ?  and airBottleState.id = ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public StoreInventoryInfo findByAirBottleId(Integer airBottleId) {
		
		Field field = new Field();
		field.addInt(airBottleId);
		
		List<StoreInventoryInfo> list = selectFromHQL(" from StoreInventoryInfo where airBottleInfo.id = ?", field);
		
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		
		return null;
	}

	public StoreInventoryInfo findByStoreIdAndBottleCode(int storeId, String bottleCode, int airBottleState, int airBottleInventoryState) {
		
		Field field = new Field();
		field.addInt(storeId);
		field.addStr(bottleCode);
		field.addInt(airBottleState);
		field.addInt(airBottleInventoryState);

		List<StoreInventoryInfo> list = selectFromHQL(
				" from StoreInventoryInfo where secondCategory.id = ? and  airBottleInfo.air_bottle_code = ?  and airBottleState.id = ? and airBottleInventoryState.id = ?",
				field);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public StoreInventoryInfo findByStoreIdAndBottleId(int storeId, Integer bottleId, int airBottleState, int airBottleInventoryState) {
		
		Field field = new Field();
		field.addInt(storeId);
		field.addInt(bottleId);
		field.addInt(airBottleState);
		field.addInt(airBottleInventoryState);

		List<StoreInventoryInfo> list = selectFromHQL(
				" from StoreInventoryInfo where secondCategory.id = ? and  airBottleInfo.id = ?  and airBottleState.id = ? and airBottleInventoryState.id = ?",
				field);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<AirBottleInfoReportDetailVo> getDetailsInventoryInfo(List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.AirBottleInfoReportDetailVo(airBottleInfo.air_bottle_code,airBottleInfo.air_bottle_seal_code,date_format(create_time,'%Y-%m-%d %h:%i'),operator.full_name)");

		sb.append(SqlUtils.initSearchConditionSql(l, "StoreInventoryInfo"));

		@SuppressWarnings("unchecked")
		List<AirBottleInfoReportDetailVo> result = selectFromHQLByClass(sb.toString(), filed, AirBottleInfoReportDetailVo.class);

		return result;
		
	}
	
	public List<StoreInventoryInfoVo> getStoreInventoryInfoVo(List<String> l, Field filed) {
		
		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.StoreInventoryInfoVo(secondCategory.id,airBottleInfo.airBottleType.id,airBottleState.id,count(*))");

		sb.append(SqlUtils.initSearchConditionSql(l, "StoreInventoryInfo"));
		
		sb.append(" group by secondCategory.id,airBottleInfo.airBottleType.id,airBottleState.id");

		@SuppressWarnings("unchecked")
		List<StoreInventoryInfoVo> result = selectFromHQLByClass(sb.toString(), filed, StoreInventoryInfoVo.class);

		return result;
	}

}
