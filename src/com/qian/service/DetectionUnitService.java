package com.qian.service;

import java.util.List;

import com.qian.entity.DetectionUnit;

/**
 * @author Lu Kongwen
 * @version Create time：2016-6-16 下午2:53:39
 * @Description
 */
public interface DetectionUnitService {

	public List<DetectionUnit> getPageList(int page, int rows);

	public long getTotalNum();

	public int add(DetectionUnit detectionUnit);

	public int edit(DetectionUnit detectionUnit);

	public void delByIds(String ids[]);

	public List<DetectionUnit> getAllList();
}
