package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.qian.dao.impl.PartFeeInfoDaoImpl;
import com.qian.entity.PartFeeInfo;
import com.qian.service.PartFeeInfoService;
import com.qian.vo.Field;
import com.qian.vo.PartSaleReportInfoVo;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PartFeeServiceInfoImpl implements PartFeeInfoService {
	@Autowired
	private PartFeeInfoDaoImpl partFeeInfoDaoImpl;

	public void add(PartFeeInfo partFeeInfo) {

		partFeeInfoDaoImpl.insert(partFeeInfo);
	}

	public PartFeeInfo findbyid(int id) {

		return partFeeInfoDaoImpl.findById(id);
	}

	public List<PartFeeInfo> findByOrderId(int orderId) {
		return partFeeInfoDaoImpl.findByOrderId(orderId);
	}

	public List<PartSaleReportInfoVo> getAllOrderInfo(List<String> list, Field filed) {

		return partFeeInfoDaoImpl.getAllOrderInfo(list, filed);
	}

}
