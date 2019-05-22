package com.qian.service;

import java.util.List;
import java.util.Map;

import com.qian.app.entity.AppAddFamilyCheckOrder;
import com.qian.entity.FamilyCheckContentInfo;
import com.qian.entity.FamilyCheckInfo;
import com.qian.mobile.entity.MobileFamilyCheckOrder;
import com.qian.vo.BaseDto;
import com.qian.vo.CheckOperatorFamilyCheckInfoVo;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-9 下午3:10:46
 * @Description
 */
public interface FamilyCheckInfoService {

	public CommonCode addFamilyCheckOrder(FamilyCheckInfo familyCheckInfo);

	public List<FamilyCheckInfo> getPageList(List<String> l, Field filed, int page, int rows);

	public Long getTotalNum(List<String> l, Field filed);

	public CommonCode addAppFamilyCheckOrder(AppAddFamilyCheckOrder appAddFamilyCheckOrder);

	public CommonCode fillInFamilyCheckOrder(int familyCheckId, FamilyCheckContentInfo familyCheckContentInfo);

	public FamilyCheckContentInfo findFamilyCheckContentByFamilyCheckId(int familyCheckId);

	public FamilyCheckInfo getFamilyCheckOrderById(int familyCheckId);

	public CommonCode saveFamilyCheckPhoto(int familyCheckId, String url);

	public CommonCode addMobileFamilyCheckOrder(MobileFamilyCheckOrder mobileFamilyCheckOrder);

	public CommonCode updateMobileFamilyCheckOrder(MobileFamilyCheckOrder mobileFamilyCheckOrder);
	
	public List<CheckOperatorFamilyCheckInfoVo> getFamilyCheckClientNumByStore(List<String> list, Field filed);

	/**
	 * 批量撤销
	 */
	public BaseDto<Map<String,Object>> revokeFamilyCheck(String ids);
}
