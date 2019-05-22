package com.qian.service;

import java.util.List;

import com.qian.entity.VisitInfo;
import com.qian.vo.Field;

public interface VisitInfoService {
	
	public VisitInfo add(VisitInfo visitInfo);
	
	public List<VisitInfo> getVisitInfo(int clientId);
	
	public List<VisitInfo> getVisitPageList(List<String> list, Field filed, int page, int rows);

	public long getTotalNum();

	public long getTotalNum(List<String> list, Field filed);
	
	public VisitInfo findVisitById(int visitId);
	
	public int updateVisitState(VisitInfo visitInfo);

}
