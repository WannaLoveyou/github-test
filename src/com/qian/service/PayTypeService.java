package com.qian.service;
import java.util.List;

import com.qian.entity.PayType;

public interface PayTypeService {
	
	public List<PayType> getPageList(int page, int rows);

	public long getTotalNum();

	public int add(PayType payType);
	
	public int edit(PayType payType);
	
	public void delByIds(String ids[]);

	public List<PayType> getAllList();
}
