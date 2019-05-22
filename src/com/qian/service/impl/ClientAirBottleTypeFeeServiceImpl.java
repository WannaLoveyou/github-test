package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qian.dao.impl.ClientAirBottleTypeFeeDaoImpl;
import com.qian.entity.ClientAirBottleTypeFee;
import com.qian.service.ClientAirBottleTypeFeeService;
import com.qian.vo.Field;
@Service
public class ClientAirBottleTypeFeeServiceImpl implements ClientAirBottleTypeFeeService {
	@Autowired
	private ClientAirBottleTypeFeeDaoImpl clientAirBottleTypeFeeDaoImpl;

	public List<ClientAirBottleTypeFee> getPageList(List<String> l, Field filed,int page, int rows) {
		return clientAirBottleTypeFeeDaoImpl.getPageList(l,filed,page, rows);
	}

	public Long getTotalNum(List<String> l, Field filed) {
		return clientAirBottleTypeFeeDaoImpl.getTotalNum(l,filed);
	}

	public List<ClientAirBottleTypeFee> getAllList() {
		return clientAirBottleTypeFeeDaoImpl.getAllList();
	}

	public int add(ClientAirBottleTypeFee clientAirBottleTypeFee) {
		return clientAirBottleTypeFeeDaoImpl.insert(clientAirBottleTypeFee);
	}

	public int edit(ClientAirBottleTypeFee clientAirBottleTypeFee) {
		return clientAirBottleTypeFeeDaoImpl.update(clientAirBottleTypeFee);
	}

	public void delByIds(String[] ids) {
		clientAirBottleTypeFeeDaoImpl.delByIds(ids);
	}


	public Double getFee(int airBottleTypeId,int clientId) {
		
		return clientAirBottleTypeFeeDaoImpl.getFee(airBottleTypeId, clientId);
	}
	
	public ClientAirBottleTypeFee getDeliveryandCheckFee(int airBottleTypeId,int clientId) {
		
		return clientAirBottleTypeFeeDaoImpl.getDeliveryandCheckFee(airBottleTypeId, clientId);
	}

	public int getClientAirBottleTypeFeeId(int airBottleTypeId, int clientId) {
		
		return clientAirBottleTypeFeeDaoImpl.getClientAirBottleTypeFeeId(airBottleTypeId, clientId);
	}

	public int getClientAirBottleTypeFeeIdByEdit(int ClientAirBottleTypeFeeId, int airBottleTypeId, int clientId) {
		
		return clientAirBottleTypeFeeDaoImpl.getClientAirBottleTypeFeeIdByEdit(ClientAirBottleTypeFeeId,airBottleTypeId, clientId);
	}


	
}
