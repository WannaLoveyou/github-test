package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.DeliveryManInventoryInfo;
import com.qian.mobile.entity.MobileTotalInventoryInfo;
import com.qian.util.SqlUtils;
import com.qian.vo.AirBottleInfoReportDetailVo;
import com.qian.vo.DeliveryManInventoryInfoVo;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-2-24 上午9:53:00
 * @Description
 */
@Component
public class DeliveryManInventoryInfoDaoImpl extends HBaseDao<DeliveryManInventoryInfo> {

	public DeliveryManInventoryInfo findByAirBottleCode(String bottleCode) {

		Field field = new Field();
		field.addStr(bottleCode);

		List<DeliveryManInventoryInfo> list = selectFromHQL(" from DeliveryManInventoryInfo where airBottleInfo.air_bottle_code= ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
	
	public DeliveryManInventoryInfo findByAirBottleCode(String bottleCode,int airBottleState,int airBottleInventoryState) {

		Field field = new Field();
		field.addStr(bottleCode);
		field.addInt(airBottleState);
		field.addInt(airBottleInventoryState);

		List<DeliveryManInventoryInfo> list = selectFromHQL(" from DeliveryManInventoryInfo where airBottleInfo.air_bottle_code = ? and airBottleState.id = ? and airBottleInventoryState.id = ?",
				field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public DeliveryManInventoryInfo findByDeliveryManIdAndBottleCode(Integer deliveryManId, String bottleCode,int airBottleState,int airBottleInventoryState) {

		Field field = new Field();
		field.addInt(deliveryManId);
		field.addStr(bottleCode);
		field.addInt(airBottleState);
		field.addInt(airBottleInventoryState);
		

		List<DeliveryManInventoryInfo> list = selectFromHQL(" from DeliveryManInventoryInfo where delivery_man.id= ? and airBottleInfo.air_bottle_code = ? and airBottleState.id = ? and airBottleInventoryState.id = ?",
				field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
	

	public DeliveryManInventoryInfo findByDeliveryManIdAndBottleCode(Integer deliveryManId, String bottleCode) {

		Field field = new Field();
		field.addInt(deliveryManId);
		field.addStr(bottleCode);
		

		List<DeliveryManInventoryInfo> list = selectFromHQL(" from DeliveryManInventoryInfo where delivery_man.id= ? and airBottleInfo.air_bottle_code = ?",
				field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public List<DeliveryManInventoryInfo> getInventoryInfoListByDeliveryManId(int deliveryManId, int airBottleState,int airBottleInventoryState) {
		
		Field field = new Field();
		field.addInt(deliveryManId);
		field.addInt(airBottleState);
		field.addInt(airBottleInventoryState);
		
		List<DeliveryManInventoryInfo> list = selectFromHQL(" from DeliveryManInventoryInfo where delivery_man.id= ?  and airBottleState.id = ? and airBottleInventoryState.id = ?",
				field);

		return list;
	}

	public List<DeliveryManInventoryInfoVo> getDeliveryManInventory(List<String> l, Field filed) {
		
		StringBuffer sb = new StringBuffer();

		sb.append("select new com.tgb.vo.DeliveryManInventoryInfoVo(delivery_man.id,airBottleInfo.materialType.id,airBottleState.id,count(*))");

		sb.append(SqlUtils.initSearchConditionSql(l, "DeliveryManInventoryInfo"));

		sb.append(" group by delivery_man.id,airBottleInfo.materialType.id,airBottleState.id");

		@SuppressWarnings("unchecked")
		List<DeliveryManInventoryInfoVo> result = selectFromHQLByClass(sb.toString(), filed, DeliveryManInventoryInfoVo.class);

		return result;
	}

	public List<AirBottleInfoReportDetailVo> getDetailsInventoryInfo(List<String> l, Field filed) {
		
		StringBuffer sb = new StringBuffer();

		sb.append("select new com.tgb.vo.AirBottleInfoReportDetailVo(airBottleInfo.air_bottle_code,airBottleInfo.air_bottle_seal_code,date_format(create_time,'%Y-%m-%d %h:%i'),operator.full_name)");

		sb.append(SqlUtils.initSearchConditionSql(l, "DeliveryManInventoryInfo"));

		@SuppressWarnings("unchecked")
		List<AirBottleInfoReportDetailVo> result = selectFromHQLByClass(sb.toString(), filed, AirBottleInfoReportDetailVo.class);

		return result;
	}

	public List<DeliveryManInventoryInfo> getDeliveryManInventoryInfoList(List<String> l, Field filed) {
		
		return selectFromHQL(SqlUtils.initSearchConditionSql(l, getEntityName()), filed);
	}

	public DeliveryManInventoryInfo findByAirBottleId(Integer airBottleId) {
		
		Field field = new Field();
		field.addInt(airBottleId);

		List<DeliveryManInventoryInfo> list = selectFromHQL(" from DeliveryManInventoryInfo where airBottleInfo.id= ?", field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public DeliveryManInventoryInfo findByDeliveryManIdAndBottleId(int deliveryManId, Integer bottleId, int airBottleState, int airBottleInventoryState) {
		
		Field field = new Field();
		field.addInt(deliveryManId);
		field.addInt(bottleId);
		field.addInt(airBottleState);
		field.addInt(airBottleInventoryState);
		
		List<DeliveryManInventoryInfo> list = selectFromHQL(" from DeliveryManInventoryInfo where delivery_man.id = ? and airBottleInfo.id = ? and airBottleState.id = ? and airBottleInventoryState.id = ?",
				field);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public List<MobileTotalInventoryInfo> getDeliveryManTotalInventoryInfo(List<String> l, Field filed) {
		
		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.mobile.entity.MobileTotalInventoryInfo(delivery_man.id,delivery_man.full_name,airBottleInfo.airBottleType.id,airBottleInfo.airBottleType.air_bottle_specifications,airBottleState.id,airBottleState.state_description,count(*))");

		sb.append(SqlUtils.initSearchConditionSql(l, "DeliveryManInventoryInfo"));

		sb.append(" group by delivery_man.id,airBottleInfo.airBottleType.id,airBottleState.id");

		@SuppressWarnings("unchecked")
		List<MobileTotalInventoryInfo> result = selectFromHQLByClass(sb.toString(), filed, MobileTotalInventoryInfo.class);

		return result;
	}

}
