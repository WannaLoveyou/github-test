package com.qian.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qian.code.ComplaintInfoStateCode;
import com.qian.dao.impl.ClientInfoDaoImpl;
import com.qian.dao.impl.ComplaintInfoDaoImpl;
import com.qian.entity.ClientInfo;
import com.qian.entity.ComplaintInfo;
import com.qian.entity.ComplaintInfoState;
import com.qian.service.ComplaintInfoService;
import com.qian.util.CodeUtils;
import com.qian.vo.Field;

@Service
public class ComplaintInfoServiceImpl implements ComplaintInfoService {

	@Autowired
	private ComplaintInfoDaoImpl complaintInfoDaoImpl;

	@Autowired
	private ClientInfoDaoImpl clientInfoDaoImpl;

	public ComplaintInfo add(ComplaintInfo complaintInfo) {

		ClientInfo clientInfo = clientInfoDaoImpl.findById(complaintInfo.getClientInfo().getId());
		complaintInfo.setSecondCategory(clientInfo.getSecondCategory());

		ComplaintInfoState complaintInfoState = new ComplaintInfoState();
		complaintInfoState.setId(ComplaintInfoStateCode.ALREADY_ORDER_BUT_NO_DEAL);
		complaintInfo.setComplaintInfoState(complaintInfoState);

		complaintInfo.setComplaint_time(new Date());
		complaintInfoDaoImpl.insert(complaintInfo);

		complaintInfo.setComplaint_code(CodeUtils.setComplaintCode(complaintInfo));
		complaintInfoDaoImpl.update(complaintInfo);

		return complaintInfo;
	}

	public List<ComplaintInfo> getComplaintInfo(int clientId) {
		return complaintInfoDaoImpl.getRepairInfo(clientId);
	}

	public List<ComplaintInfo> getcomplaintPageList(List<String> list, Field filed, int page, int rows) {
		return complaintInfoDaoImpl.getRepairInfoPageList(list, filed, page, rows);
	}

	public long getTotalNum() {
		return 0;
	}

	public long getTotalNum(List<String> list, Field filed) {
		return complaintInfoDaoImpl.getTotalNum(list, filed);
	}

	public ComplaintInfo findcomplaintById(int complaintId) {
		return complaintInfoDaoImpl.findById(complaintId);
	}

	public int updateComplaintState(ComplaintInfo complaintInfo) {
		return complaintInfoDaoImpl.update(complaintInfo);

	}

	public int addComplaintNote(int complaintId, String dealcomplaintNote) {
		ComplaintInfo complaintInfo = complaintInfoDaoImpl.findById(complaintId);
		complaintInfo.setDealcomplaintNote(dealcomplaintNote);
		ComplaintInfoState rs = new ComplaintInfoState();
		rs.setId(ComplaintInfoStateCode.ALREADY_DEAL);
		complaintInfo.setComplaintInfoState(rs);
		return complaintInfoDaoImpl.update(complaintInfo);
	}

}
