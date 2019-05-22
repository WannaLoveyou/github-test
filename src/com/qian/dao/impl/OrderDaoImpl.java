package com.qian.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.OrderInfo;
import com.qian.util.SqlUtils;
import com.qian.util.StringUtils;
import com.qian.util.TimeUtils;
import com.qian.vo.DeliveryManDeliveryFeeInfoVo;
import com.qian.vo.Field;
import com.qian.vo.OrderCycleInfoVo;
import com.qian.vo.SaleDetailReportInfoVo;
import com.qian.vo.SaleReportInfoSummaryByDifferentPrice;
import com.qian.vo.SaleReportInfoVo;
import com.qian.vo.SimpleOrderInfo;

@Component
public class OrderDaoImpl extends HBaseDao<OrderInfo> {

	public String getOrderStateByOrderId(int orderId) {

		Field field = new Field();
		field.addInt(orderId);

		@SuppressWarnings("unchecked")
		List<String> result = selectFromHQLByClass("select state.state_description from OrderInfo where id = ?", field, String.class);

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}
	
	public Date getLastOrderInfoDate(int clientId) {

		Field field = new Field();
		field.addInt(clientId);

		@SuppressWarnings("unchecked")
		List<Date> result = selectFromHQLByClass("select order_time from OrderInfo where clientInfo.id=? order by order_time desc", field, Date.class);

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<OrderInfo> getOrderInfoByLatest(int clientId) {
		Field field = new Field();
		field.addInt(clientId);
		return selectFromHQL(" from OrderInfo where clientInfo.id=? order by order_time desc ", field, 0, 20);
	}

	public List<OrderInfo> getNewOrderInfo(int clientId) {
		int stateId = 1;// 下单未派送
		Field filed = new Field();
		filed.addInt(clientId);
		filed.addInt(stateId);
		return selectFromHQL(" from OrderInfo where clientInfo.id=? and state.id=? ", filed);
	}

	public List<OrderInfo> getDispatchPageList(int page, int rows) {

		int stateId = 1;// 下单未派送
		Field field = new Field();
		field.addInt(stateId);
		return selectFromHQL(" from OrderInfo where state.id = ?", field, (page - 1) * rows, rows);
	}

	public long getTotalNum() {

		int stateId = 1;// 下单未派送
		Field field = new Field();
		field.addInt(stateId);
		return getTotalFromHQL(" from OrderInfo where state.id = ?", field);
	}

	public List<OrderInfo> getOrderInfoPageList(List<String> list, Field filed, int page, int rows) {

		int flag = 0;

		StringBuffer hql = new StringBuffer();
		hql.append(" from OrderInfo ");

		for (String s : list) {

			if (flag == 0) {
				hql.append("where ");
				flag = 1;
			} else {
				hql.append(" and ");
			}

			hql.append(s);
		}

		return selectFromHQL(hql.toString(), filed, (page - 1) * rows, rows);
	}

	public Long getTotalNum(List<String> list, Field filed) {

		int flag = 0;

		StringBuffer hql = new StringBuffer();
		hql.append(" from OrderInfo ");

		for (String s : list) {

			if (flag == 0) {
				hql.append("where ");
				flag = 1;
			} else {
				hql.append(" and ");
			}

			hql.append(s);
		}

		return getTotalFromHQL(hql.toString(), filed);
	}

	public List<OrderInfo> getOrderInfoPageList(List<String> list, Field filed) {

		return selectFromHQL(SqlUtils.initSearchConditionSql(list, "OrderInfo"), filed);
	}

	public List<SaleReportInfoVo> getAllOrderInfo(List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.SaleReportInfoVo(secondCategory.id,airBottleType.id,deliveryType.id,order_number,price,price*order_number,report_time,is_speed_up)");

		sb.append(SqlUtils.initSearchConditionSql(l, "OrderInfo"));

		@SuppressWarnings("unchecked")
		List<SaleReportInfoVo> result = selectFromHQLByClass(sb.toString(), filed, SaleReportInfoVo.class);

		return result;
	}

	public List<SaleDetailReportInfoVo> getAllOrderInfoByDate(List<String> l, Field field) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.SaleDetailReportInfoVo(date_format(report_time,'%Y-%m-%d'),secondCategory.id,airBottleType.id,deliveryType.id,sum(order_number),sum(total_amount))");

		sb.append(SqlUtils.initSearchConditionSql(l, "OrderInfo"));

		sb.append(" group by date_format(report_time,'%Y-%m-%d')");

		@SuppressWarnings("unchecked")
		List<SaleDetailReportInfoVo> result = selectFromHQLByClass(sb.toString(), field, SaleDetailReportInfoVo.class);

		return result;
	}

	public List<DeliveryManDeliveryFeeInfoVo> getAllDeliveryManDeliveryFeeInfo(List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.DeliveryManDeliveryFeeInfoVo(airBottleType.id,order_number,delivery_man.id,floor_subsidies_money * order_number,delivery_fee * order_number)");

		sb.append(SqlUtils.initSearchConditionSql(l, "OrderInfo"));

		@SuppressWarnings("unchecked")
		List<DeliveryManDeliveryFeeInfoVo> result = selectFromHQLByClass(sb.toString(), filed, DeliveryManDeliveryFeeInfoVo.class);

		return result;
	}

	public Integer getOrderBottleNum(List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select sum(order_number)");

		sb.append(SqlUtils.initSearchConditionSql(l, "OrderInfo"));

		@SuppressWarnings("unchecked")
		List<Long> result = selectFromHQLByClass(sb.toString(), filed, Integer.class);

		if (result.size() > 0 && result.get(0) != null) {

			return result.get(0).intValue();
		}

		return 0;

	}

	public OrderInfo getByPrepayId(String prepayId) {

		Field field = new Field();
		field.addStr(prepayId);

		List<OrderInfo> result = selectFromHQL(" from OrderInfo where weixin_prepay_id = ?", field);

		if (result.size() > 0) {
			result.get(0);
		}

		return null;
	}

	public OrderInfo getLastOrderInfoByClientIdAndOrderId(int clientId, int orderId) {

		Field field = new Field();
		field.addInt(clientId);
		field.addInt(orderId);
		List<OrderInfo> result = selectFromHQL(" from OrderInfo where clientInfo.id=? and id < ? order by id desc ", field, 1, 1);

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}
	
	public OrderInfo findByOrderCode(String orderCode) {

		Field field = new Field();
		field.addStr(orderCode);
		List<OrderInfo> result = selectFromHQL(" from OrderInfo where order_code = ?", field);

		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<SimpleOrderInfo> getSimpleOrderInfoPageList(List<String> l, Field filed, int page, int rows, String sort, String order) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new map(id as id , order_code as order_code , clientInfo.id as client_id , clientInfo.client_code as client_code , clientInfo.card_code as card_code , clientInfo.client_name as client_name , clientInfo.mobile_tel_number_1 as mobile_tel_number_1, clientInfo.mobile_tel_number_2 as mobile_tel_number_2, clientInfo.fixed_tel_number_1 as fixed_tel_number_1, clientInfo.fixed_tel_number_2 as fixed_tel_number_2, airBottleType.air_bottle_specifications as air_bottle_specifications , deliveryType.id as delivery_type_id , payType.id as pay_type_id , order_number as order_number , order_address as order_address , order_tel_number as order_tel_number , delivery_man.id as delivery_man_id ,UNIX_TIMESTAMP(delivery_time) as delivery_time, operator.id as operator_id , remark as remark , state.state_description as state_description , floor_subsidies_money as floor_subsidies_money , delivery_fee as delivery_fee , check_fee as check_fee , price as price , discount_amount as discount_amount , total_amount as total_amount , UNIX_TIMESTAMP(order_time) as order_time , feeType as feeType , fee_total_amount as fee_total_amount , UNIX_TIMESTAMP(order_appointment_time1) as order_appointment_time1 , UNIX_TIMESTAMP(order_appointment_time2) as order_appointment_time2 , print_times as print_times , payState.id as pay_state_id , non_qrcode_heavy_bottle as non_qrcode_heavy_bottle , non_qrcode_empty_bottle as non_qrcode_empty_bottle , is_speed_up as is_speed_up , UNIX_TIMESTAMP(report_time) as report_time , secondCategory.id as secondCategory_id,invoice_num as invoice_num, invoice_operator.id as invoice_operator_id , UNIX_TIMESTAMP(invoice_time) as invoice_time,cancel_invoice_operator.id as cancel_invoice_operator_id, UNIX_TIMESTAMP(cancel_invoice_time) as cancel_invoice_time,cancel_invoice_remark as cancel_invoice_remark)");

		sb.append(SqlUtils.initSearchConditionSql(l, "OrderInfo"));
		
		if(StringUtils.nonEmpty(sort)){
			sb.append(" order by ").append(sort).append(" ").append(order);
		}
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = selectFromHQLByClass(sb.toString(), filed, (page - 1) * rows, rows, Map.class);

		List<SimpleOrderInfo> result = new ArrayList<SimpleOrderInfo>();

		for (Map<String, Object> map : list) {

			SimpleOrderInfo simpleOrderInfo = new SimpleOrderInfo(map);

			result.add(simpleOrderInfo);
		}

		return result;
	}

	public List<OrderCycleInfoVo> getOrderCycleInfo(List<String> l, Field filed) {

		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.OrderCycleInfoVo(clientInfo.id,clientInfo.clientType.id,airBottleType.id,count(*),SUM(order_number),MIN(report_time),MAX(report_time))");

		sb.append(SqlUtils.initSearchConditionSql(l, "OrderInfo"));

		sb.append(" group by clientInfo.id,airBottleType.id");

		@SuppressWarnings("unchecked")
		List<OrderCycleInfoVo> result = selectFromHQLByClass(sb.toString(), filed, OrderCycleInfoVo.class);

		return result;
	}

	public Integer getOrderCycleLastOrderNum(Integer clientId, Date endDate) {

		Field filed = new Field();
		filed.addInt(clientId);
		filed.addDateTime(endDate.getTime());

		String sql = "select order_number from OrderInfo where clientInfo.id = ? and report_time = ?";

		@SuppressWarnings("unchecked")
		List<Integer> result = selectFromHQLByClass(sql, filed, Integer.class);

		if (result.size() > 0 && result.get(0) != null) {

			return result.get(0).intValue();
		}

		return 0;
	}

	public Integer getPickUpInSotreNum(List<String> l, Field filed) {
		
		Long result =  getTotalFromHQL(SqlUtils.initSearchConditionSql(l, "OrderInfo"), filed);

		return result.intValue();
	}

	public Integer getTelephoneOrderNum(List<String> l, Field filed) {
		
		Long result =  getTotalFromHQL(SqlUtils.initSearchConditionSql(l, "OrderInfo"), filed);

		return result.intValue();
	}

	public Integer getWeiXinOrderNum(List<String> l, Field filed) {
		
		Long result =  getTotalFromHQL(SqlUtils.initSearchConditionSql(l, "OrderInfo"), filed);

		return result.intValue();
	}

	public int getTodayOrderNumByClinetId(int clientId, String today) {
		
		Field field = new Field();
		field.addInt(clientId);
		field.addDate(today);
		field.addDate(TimeUtils.getNextDayString(today));
		
		Long result =  getTotalFromHQL(" from OrderInfo where clientInfo.id = ? and report_time >= ? and report_time < ?", field);
		
		return result.intValue();
	}
	
	public int getOrderStateIntegerByOrderId(int orderId) {

		Field field = new Field();
		field.addInt(orderId);

		@SuppressWarnings("unchecked")
		List<Integer> result = selectFromHQLByClass("select state.id from OrderInfo where id = ?", field, String.class);

		if (result.size() > 0) {
			return result.get(0);
		}

		return 0;
	}
	
	public List<SaleReportInfoSummaryByDifferentPrice> getSubtotalList(List<String> l, Field filed){
		
		StringBuffer sb = new StringBuffer();

		sb.append("select new com.qian.vo.SaleReportInfoSummaryByDifferentPrice(");

		sb.append("secondCategory.second_category_name");
		
		sb.append(",airBottleType.air_bottle_specifications");
		
		sb.append(",price");
		
		sb.append(",SUM(order_number)");
		
		sb.append(",SUM(price*order_number)");
		
		sb.append(",payType.pay_type_name");
		
		sb.append(")");
		
		sb.append(SqlUtils.initSearchConditionSql(l, "OrderInfo"));
		
		sb.append(" GROUP BY accept_second_category_id,air_bottle_type_id,pay_type_id,price");
		
		sb.append(" ORDER BY accept_second_category_id,air_bottle_type_id,pay_type_id,price");
		
		@SuppressWarnings("unchecked")
		List<SaleReportInfoSummaryByDifferentPrice> result = selectFromHQLByClass(sb.toString(), filed, SaleReportInfoSummaryByDifferentPrice.class);
		
		return result;
	}
	
	
}
