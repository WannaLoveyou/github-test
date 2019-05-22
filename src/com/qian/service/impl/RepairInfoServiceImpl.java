package com.qian.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qian.code.RepairInfoStateCode;
import com.qian.dao.impl.ClientInfoDaoImpl;
import com.qian.dao.impl.RepairInfoDaoImpl;
import com.qian.entity.ClientInfo;
import com.qian.entity.RepairInfo;
import com.qian.entity.RepairInfoState;
import com.qian.service.RepairInfoService;
import com.qian.util.CodeUtils;
import com.qian.vo.Field;

@Service
public class RepairInfoServiceImpl implements RepairInfoService {

	@Autowired
	private RepairInfoDaoImpl repairInfoDaoImpl;

	@Autowired
	private ClientInfoDaoImpl clientInfoDaoImpl;

	public RepairInfo add(RepairInfo repairInfo) {

		ClientInfo clientInfo = clientInfoDaoImpl.findById(repairInfo.getClientInfo().getId());

		repairInfo.setSecondCategory(clientInfo.getSecondCategory());

		repairInfo.setAccept_time(new Date());

		RepairInfoState repairInfoState = new RepairInfoState();
		repairInfoState.setId(RepairInfoStateCode.ALREADY_ORDER_BUT_NO_DISPATCHING);
		repairInfo.setRepairInfoState(repairInfoState);

		repairInfoDaoImpl.insert(repairInfo);

		repairInfo.setRepair_code(CodeUtils.setRepairCode(repairInfo));

		repairInfoDaoImpl.update(repairInfo);

		return repairInfo;
	}

	public List<RepairInfo> getRepairInfo(int clientId) {
		return repairInfoDaoImpl.getRepairInfo(clientId);
	}

	public List<RepairInfo> getRepairInfoAllList() {
		return repairInfoDaoImpl.getRepairInfoAllList();
	}

	public long getTotalNum() {
		return 0;
	}

	public List<RepairInfo> getRepairInfoPageList(List<String> list, Field filed, int page, int rows) {
		return repairInfoDaoImpl.getRepairInfoPageList(list, filed, page, rows);
	}

	public long getTotalNum(List<String> list, Field filed) {
		return repairInfoDaoImpl.getTotalNum(list, filed);
	}

	public RepairInfo findRepairInfoById(int repairId) {
		return repairInfoDaoImpl.findById(repairId);
	}

	public RepairInfo update(RepairInfo repairInfo) {

		repairInfoDaoImpl.update(repairInfo);
		return repairInfo;
	}

	@Override
	public List<RepairInfo> getAllList(List<String> l, Field field) {

		return repairInfoDaoImpl.getAllList(l, field);
	}

}
