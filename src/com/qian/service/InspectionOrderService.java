package com.qian.service;

import java.util.List;
import java.util.Map;

import com.qian.entity.InspectionOrderItemInfo;
import com.qian.entity.User;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

public interface InspectionOrderService {

	public BaseDto<Map<String, Object>> getPageList(List<String> l, Field field, int page, int rows);

	public List<InspectionOrderItemInfo> getItemsInOrder(int orderId);

	public BaseDto<Object> sendInspection(int orderId);

	public BaseDto<Object> refreshInspectionOrder(int orderId);

	public BaseDto<Object> confirmInspectionOrder(int orderId);

	public BaseDto<Object> refreshInspectionOrderDetails(int orderId);

	public BaseDto<List<Map<String, Object>>> queryInspectionOrderDetailsResult(int orderDetailsId);

	public BaseDto<Object> initInspectionOrderData(int orderId, User user);

}
