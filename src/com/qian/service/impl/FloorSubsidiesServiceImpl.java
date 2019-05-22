package com.qian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.dao.impl.FloorSubsidiesDaoImpl;
import com.qian.entity.FloorSubsidies;
import com.qian.service.FloorSubsidiesService;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-9 上午10:53:03
 * @Description
 */

@Service
@Transactional
public class FloorSubsidiesServiceImpl implements FloorSubsidiesService {

	@Autowired
	private FloorSubsidiesDaoImpl floorSubsidiesDAOImpl;

	public List<FloorSubsidies> getPageList(int page, int rows) {
		return floorSubsidiesDAOImpl.getPageList(page, rows);
	}

	public long getTotalNum() {
		return floorSubsidiesDAOImpl.getTotalNum();
	}

	public int add(FloorSubsidies floorSubsidies) {
		return floorSubsidiesDAOImpl.insert(floorSubsidies);
	}

	public int edit(FloorSubsidies floorSubsidies) {
		return floorSubsidiesDAOImpl.update(floorSubsidies);
	}

	public void delByIds(String[] ids) {
		floorSubsidiesDAOImpl.delByIds(ids);
	}

	public List<FloorSubsidies> getAllList() {
		return floorSubsidiesDAOImpl.getAllList();
	}

	public FloorSubsidies findByMoney(double floor_subsidies_money) {
		
		return floorSubsidiesDAOImpl.findByMoney(floor_subsidies_money);
	}

}
