package com.qian.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qian.dao.impl.ClientInfoDaoImpl;
import com.qian.dao.impl.VisitInfoDaoImpl;
import com.qian.entity.ClientInfo;
import com.qian.entity.VisitInfo;
import com.qian.service.VisitInfoService;
import com.qian.util.CodeUtils;
import com.qian.vo.Field;

@Service
public class VisitInfoServiceImpl implements VisitInfoService {

	@Autowired
	private VisitInfoDaoImpl visitInfoDaoImpl;

	@Autowired
	private ClientInfoDaoImpl clientInfoDaoImpl;

	public VisitInfo add(VisitInfo visitInfo) {

		ClientInfo clientInfo = clientInfoDaoImpl.findById(visitInfo.getClientInfo().getId());
		visitInfo.setSecondCategory(clientInfo.getSecondCategory());
		visitInfo.setCreate_time(new Date());

		visitInfoDaoImpl.insert(visitInfo);

		visitInfo.setVisit_code(CodeUtils.setVisitCode(visitInfo));
		visitInfoDaoImpl.update(visitInfo);

		return visitInfo;
	}

	public List<VisitInfo> getVisitInfo(int clientId) {
		return visitInfoDaoImpl.getVisitInfo(clientId);
	}

	public List<VisitInfo> getVisitPageList(List<String> list, Field filed, int page, int rows) {
		return visitInfoDaoImpl.getVisitInfoPageList(list, filed, page, rows);
	}

	public long getTotalNum() {
		return 0;
	}

	public long getTotalNum(List<String> list, Field filed) {
		return visitInfoDaoImpl.getTotalNum(list, filed);
	}

	public VisitInfo findVisitById(int visitId) {
		return visitInfoDaoImpl.findById(visitId);
	}

	public int updateVisitState(VisitInfo visitInfo) {
		return visitInfoDaoImpl.update(visitInfo);

	}

}
