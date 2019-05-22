package com.qian.service;

import java.util.List;

import com.qian.service.impl.ClientAirBottleRecord;
import com.qian.vo.ClientAirBottleRecordVo;
import com.qian.vo.ClientInventoryInfoVo;
import com.qian.vo.Field;

public interface ClientAirBottleRecrodService {
	public List<ClientAirBottleRecord> getRecordByLeast(int clientId);

	public ClientAirBottleRecord getNoReturnClientAirBottleRecord(String airBottleCode);

	public ClientAirBottleRecord getRecordByClientIdAndAirBottleCode(int clientId, String airBottleCode);

	public List<ClientAirBottleRecordVo> getRecordByReport(int clientId);

	public List<ClientInventoryInfoVo> getClientInventoryList(List<String> l, Field filed, Integer bottle_num);
	
	public Integer getBottleNumByClientId(int clientId);
}
