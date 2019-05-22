package com.qian.service;

import java.util.List;

import com.qian.entity.RepairInfo;
import com.qian.vo.Field;

public interface RepairInfoService {

	public RepairInfo add(RepairInfo repairInfo);

	public RepairInfo update(RepairInfo repairInfo);

	public List<RepairInfo> getRepairInfo(int clientId);

	public List<RepairInfo> getRepairInfoAllList();

	public List<RepairInfo> getRepairInfoPageList(List<String> list, Field filed, int page, int rows);

	public long getTotalNum();

	public long getTotalNum(List<String> list, Field filed);

	public RepairInfo findRepairInfoById(int repairId);

	public List<RepairInfo> getAllList(List<String> l, Field field);

}
