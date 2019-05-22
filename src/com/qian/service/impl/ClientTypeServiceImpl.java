package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.ClientTypeDaoImpl;
import com.qian.entity.ClientType;
import com.qian.service.ClientTypeService;

@Service
@Transactional
public class ClientTypeServiceImpl implements ClientTypeService {
	@Autowired
	private ClientTypeDaoImpl clientTypeDAOImpl;

	public List<ClientType> getPageList(int page, int rows) {
		return clientTypeDAOImpl.getPageList(page, rows);
	}

	public long toallNum() {
		return clientTypeDAOImpl.toallNum();
	}

	public int addClienType(ClientType clientType) {
		return clientTypeDAOImpl.insert(clientType);
	}

	public int editClienType(ClientType clientType) {
		return clientTypeDAOImpl.update(clientType);
	}

	public void delByIds(String ids[]) {
		for (String id : ids) {
			clientTypeDAOImpl.delById(Integer.parseInt(id));
		}
	}

	public List<ClientType> getAllList() {
		// TODO Auto-generated method stub
		return clientTypeDAOImpl.getAllList();
	}

}
