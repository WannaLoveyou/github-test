package com.qian.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qian.dao.HBaseDao;
import com.qian.entity.DistributionWorkerPositionShow;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2018-2-1 下午5:05:44
 * @Description
 */
@Component
public class DistributionWorkerPositionShowDaoImpl extends HBaseDao<DistributionWorkerPositionShow> {
	public Long getTotalNum(){
		return getTotalFromHQL("from DistributionWorkerPositionShow");
	}
	
	public List<DistributionWorkerPositionShow> getPageList(int page,int rows){
		return selectFromHQL("from DistributionWorkerPositionShow", (page-1)*rows, rows);
	}
	
	@SuppressWarnings("unchecked")
	public List<DistributionWorkerPositionShow> getByUserId(Field f){
		return selectFromHQLByClass("from DistributionWorkerPositionShow where delivery_man_id=?",f,DistributionWorkerPositionShow.class);
	}
	
}
