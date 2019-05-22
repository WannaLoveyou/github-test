package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.ClientAirBottleRecordDaoImpl;
import com.qian.service.ClientAirBottleRecrodService;
import com.qian.util.AirBottleCodeInitUtils;
import com.qian.vo.ClientAirBottleRecordVo;
import com.qian.vo.ClientInventoryInfoVo;
import com.qian.vo.Field;

@Service
@Transactional
public class ClientAirBottleRecrodServiceImpl implements ClientAirBottleRecrodService {
	@Autowired
	private ClientAirBottleRecordDaoImpl clientAirBottleRecordDaoImplDAOImpl;

	public List<ClientAirBottleRecord> getRecordByLeast(int clientId) {
		return clientAirBottleRecordDaoImplDAOImpl.getRecordByLeast(clientId);
	}

	public ClientAirBottleRecord getNoReturnClientAirBottleRecord(String airBottleCode) {

		return clientAirBottleRecordDaoImplDAOImpl.getNoReturnClientAirBottleRecord(AirBottleCodeInitUtils.initCode(airBottleCode));

	}

	public ClientAirBottleRecord getRecordByClientIdAndAirBottleCode(int clientId, String airBottleCode) {

		return clientAirBottleRecordDaoImplDAOImpl.getRecordByClientIdAndAirBottleCode(clientId, AirBottleCodeInitUtils.initCode(airBottleCode));
	}

	public List<ClientAirBottleRecordVo> getRecordByReport(int clientId) {

		return clientAirBottleRecordDaoImplDAOImpl.getRecordByReport(clientId);

	}

	public List<ClientInventoryInfoVo> getClientInventoryList(List<String> l, Field filed, Integer bottle_num) {

		return clientAirBottleRecordDaoImplDAOImpl.getClientInventoryList(l, filed, bottle_num);
	}

	public Integer getBottleNumByClientId(int clientId) {
		
		return clientAirBottleRecordDaoImplDAOImpl.getBottleNumByClientId(clientId);
	}

}
