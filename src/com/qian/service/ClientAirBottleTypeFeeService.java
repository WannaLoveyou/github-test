package com.qian.service;

import java.util.List;

import com.qian.entity.ClientAirBottleTypeFee;
import com.qian.vo.Field;

public interface ClientAirBottleTypeFeeService {
	
	public List<ClientAirBottleTypeFee> getPageList(List<String> l, Field filed, int page,int rows);
	
	public Long getTotalNum(List<String> l, Field filed);
	
	public List<ClientAirBottleTypeFee> getAllList();
	
	
	public int add(ClientAirBottleTypeFee clientAirBottleTypeFee);

	public int edit(ClientAirBottleTypeFee clientAirBottleTypeFee);
	
	public void delByIds(String ids[]);
	
	public Double getFee(int airBottleTypeId,int clientId);
	
	public ClientAirBottleTypeFee getDeliveryandCheckFee(int airBottleTypeId,int clientId);
	
	public int getClientAirBottleTypeFeeId(int airBottleTypeId,int clientId);

	public int getClientAirBottleTypeFeeIdByEdit(int id, int id2, int id3);
}
