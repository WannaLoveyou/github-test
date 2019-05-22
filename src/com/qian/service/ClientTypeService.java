package com.qian.service;
import java.util.List;

import com.qian.entity.ClientType;

public interface ClientTypeService {
	public List<ClientType> getAllList();
	
	public List<ClientType> getPageList(int page, int rows);
	
	public long toallNum();

	public int addClienType(ClientType clientType);

	public int editClienType(ClientType clientType);
	
	public void delByIds(String ids[]);

}
