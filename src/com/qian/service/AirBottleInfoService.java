package com.qian.service;
import java.util.List;

import com.qian.entity.AirBottleInfo;
import com.qian.entity.User;
import com.qian.mobile.entity.MobileAirBottleCheckEntity;
import com.qian.mobile.entity.MobileAirBottleInfo;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

public interface AirBottleInfoService {
	
	public AirBottleInfo findById(int id);
	
	public List<AirBottleInfo> getPageList(int page, int rows);

	public long getTotalNum();
	
	public int add(AirBottleInfo airBottleInfo,User user);
	
	public int edit(AirBottleInfo airBottleInfo, int airBottleId);
	
	public int edit(AirBottleInfo airBottleInfo);
	
	public CommonCode delByIds(String ids[]);

	public AirBottleInfo findByAirBottleCode(String code);

	public Long getTotalNum(List<String> l, Field filed);

	public List<AirBottleInfo> getPageList(List<String> l, Field filed, int page, int rows);

	public BaseDto<Object> addAirBottleInfo(MobileAirBottleInfo mobileAirBottleInfo, User user);

	public AirBottleInfo findByAirBottleCode(MobileAirBottleCheckEntity entity);

	public BaseDto<CommonCode> cleanQRCodeInfo();

	public CommonCode saveNewAirBottleQRCode(MobileAirBottleInfo mobileAirBottleInfo, User user);

	public BaseDto<Object> editAirBottleInfo(MobileAirBottleInfo mobileAirBottleInfo, User user);
	
}
