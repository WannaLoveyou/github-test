package com.qian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.code.OrderInfoStateCode;
import com.qian.dao.impl.DistributionWorkerPositionShowDaoImpl;
import com.qian.dao.impl.OrderDaoImpl;
import com.qian.entity.DistributionWorkerPositionShow;
import com.qian.service.DistributionWorkerPositionShowService;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.DistributionWorkerPositionShowVo;
import com.qian.vo.Field;
import com.qian.vo.WorkerPositionVo;

/**
 * @author Wang LiangShun
 * @version Create time：2018年9月12日 上午9:52:17
 * @description
 */
@Service
@Transactional
public class DistributionWorkerPositionShowServiceImpl implements DistributionWorkerPositionShowService {

	@Autowired
	private DistributionWorkerPositionShowDaoImpl distributionWorkerPositionShowDaoImpl;
	
	@Autowired
	private OrderDaoImpl orderDAOImpl;
	
	
	
	@Override
	public void add(DistributionWorkerPositionShowVo distributionWorkerPositionShowVo) {
		DistributionWorkerPositionShow distributionWorkerPositionShow = new DistributionWorkerPositionShow(distributionWorkerPositionShowVo);
		
		Field f = new Field();
		f.addInt(distributionWorkerPositionShowVo.getDelivery_man_id());
		List<DistributionWorkerPositionShow> dbwpsList = distributionWorkerPositionShowDaoImpl.getByUserId(f);
		if(dbwpsList.isEmpty()){
			distributionWorkerPositionShowDaoImpl.insert(distributionWorkerPositionShow);
		}else{
			DistributionWorkerPositionShow dbwps =dbwpsList.get(0);
			dbwps.setCreate_time(new Date());
			dbwps.setLatitude(distributionWorkerPositionShow.getLatitude());
			dbwps.setLongitude(distributionWorkerPositionShow.getLongitude());
			distributionWorkerPositionShowDaoImpl.update(dbwps);
		}

	}

	@Override
	public void edit(DistributionWorkerPositionShow distributionWorkerPositionShow) {
		distributionWorkerPositionShowDaoImpl.update(distributionWorkerPositionShow);

	}

	@Override
	public void delByIds(String[] idString) {

		for (String id : idString) {
			DistributionWorkerPositionShow newDistributionWorkerPositionShow = distributionWorkerPositionShowDaoImpl
					.findById(Integer.parseInt(id));
			distributionWorkerPositionShowDaoImpl.delete(newDistributionWorkerPositionShow);
		}
	}

	@Override
	public long getTotalNum() {
		return distributionWorkerPositionShowDaoImpl.getTotalNum();
	}

	@Override
	public List<DistributionWorkerPositionShow> getPageList(int page, int rows) {
		
		List<DistributionWorkerPositionShow> list = distributionWorkerPositionShowDaoImpl.getPageList(page, rows);

		return list;
	}

	@Override
	public List<DistributionWorkerPositionShow> getAllList() {
		return distributionWorkerPositionShowDaoImpl.getAllList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseDto<List<WorkerPositionVo>> getPositition(List<String> l, Field field,String orderImformationId) {
		
		int orderState = orderDAOImpl.getOrderStateIntegerByOrderId(Integer.parseInt(orderImformationId));
		if(orderState == OrderInfoStateCode.ALREADY_DISPATCHING_BUT_NO_DELIVERY){
			
			List<WorkerPositionVo> result = new ArrayList<>();
			List<DistributionWorkerPositionShow> infos = distributionWorkerPositionShowDaoImpl.getAllList(l, field);
			
			for (DistributionWorkerPositionShow info : infos) {
				result.add(new WorkerPositionVo(info));
			}
			return BaseDto.getSuccessResult(result);
		}else{
			return BaseDto.getFailResult(CommonCode.POSITION_INFO_EMPTY);
		}
	}


}
