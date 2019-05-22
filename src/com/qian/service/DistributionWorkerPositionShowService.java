package com.qian.service;

import java.util.List;

import com.qian.entity.DistributionWorkerPositionShow;
import com.qian.vo.BaseDto;
import com.qian.vo.DistributionWorkerPositionShowVo;
import com.qian.vo.Field;
import com.qian.vo.WorkerPositionVo;

/**
 * @author Wang LiangShun
 * @version Create time：2018年9月12日 上午9:47:04
 * @description
 */
public interface DistributionWorkerPositionShowService {
	public void add(DistributionWorkerPositionShowVo distributionWorkerPositionShowVo);

	public void edit(DistributionWorkerPositionShow distributionWorkerPositionShow);

	public void delByIds(String[] idString);

	public long getTotalNum();

	public List<DistributionWorkerPositionShow> getPageList(int page, int rows);

	public List<DistributionWorkerPositionShow> getAllList();
	
	public BaseDto<List<WorkerPositionVo>> getPositition(List<String> l, Field field,String orderImformationId);
}
