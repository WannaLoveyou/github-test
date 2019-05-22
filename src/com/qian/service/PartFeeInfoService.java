package com.qian.service;

import java.util.List;

import com.qian.entity.PartFeeInfo;
import com.qian.vo.Field;
import com.qian.vo.PartSaleReportInfoVo;

public interface PartFeeInfoService {
	
	public void add(PartFeeInfo partFeeInfo);
	
	public PartFeeInfo findbyid(int id);
	
	public List<PartFeeInfo> findByOrderId(int orderId);

	public List<PartSaleReportInfoVo> getAllOrderInfo(List<String> orderInfoSearchConditionList, Field orderInfoSearchConditionFiled);
}
