package com.qian.service;

import java.util.List;

import com.qian.entity.ComplaintInfo;
import com.qian.vo.Field;

public interface ComplaintInfoService {
	
	public ComplaintInfo add(ComplaintInfo complaintInfo);
	
	public List<ComplaintInfo> getComplaintInfo(int clientId);
	
	public List<ComplaintInfo> getcomplaintPageList(List<String> list, Field filed, int page, int rows);

	public long getTotalNum();

	public long getTotalNum(List<String> list, Field filed);
	
	public ComplaintInfo findcomplaintById(int complaintId);
	
	public int updateComplaintState(ComplaintInfo complaintInfo);
	
	public int addComplaintNote(int complaintId,String dealcomplaintNote);
	
	

}
