package com.qian.mobile.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.app.entity.AppFamilyCheckInfo;
import com.qian.code.FamilyCheckInfoStateCode;
import com.qian.entity.FamilyCheckInfo;
import com.qian.entity.User;
import com.qian.mobile.entity.MobileFamilyCheckOrder;
import com.qian.service.FamilyCheckInfoService;
import com.qian.service.UserService;
import com.qian.util.UploadUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-10-31 下午2:23:42
 * @Description
 */
@Controller
@RequestMapping("/mobile/familyCheck")
public class FamilyCheckMobileController {

	@Autowired
	private FamilyCheckInfoService familyCheckInfoService;
	
	@Autowired
	private UserService userService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addMobileFamilyCheckOrder", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> addMobileFamilyCheckOrder(HttpServletRequest request, Map<String, Object> map, MobileFamilyCheckOrder mobileFamilyCheckOrder) {

		User user = userService.findById(mobileFamilyCheckOrder.getUserId());
		
		List<String> photos = UploadUtils.uploadFamilyCheckOrderPhotos(request);
		
		mobileFamilyCheckOrder.setUser(user);
		mobileFamilyCheckOrder.setPhotoUrls(photos);
		
		CommonCode commonCode = familyCheckInfoService.addMobileFamilyCheckOrder(mobileFamilyCheckOrder);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}
	
	@RequestMapping(value = "getSotreFamilyCheckOrder")
	public @ResponseBody
	BaseDto<List<AppFamilyCheckInfo>> getSotreFamilyCheckOrder(HttpServletRequest request, Map<String, Object> map,int userId) {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();
		
		User user = userService.findById(userId);
		
		if (user.getSecondCategory() != null) {
			l.add("clientInfo.secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId());
		}
		
		l.add("familyCheckInfoState.id = ?");
		filed.addInt(FamilyCheckInfoStateCode.T0_BE_ACCETED);
		
		List<FamilyCheckInfo> list = familyCheckInfoService.getPageList(l, filed, 1, 1000000);

		List<AppFamilyCheckInfo> result = new ArrayList<AppFamilyCheckInfo>();
		
		for(FamilyCheckInfo familyCheckInfo:list){
			
			AppFamilyCheckInfo appFamilyCheckInfo = new AppFamilyCheckInfo(familyCheckInfo);
			result.add(appFamilyCheckInfo);
		}

		return BaseDto.getSuccessResult(result);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "updateMobileFamilyCheckOrder", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> updateMobileFamilyCheckOrder(HttpServletRequest request, Map<String, Object> map, MobileFamilyCheckOrder mobileFamilyCheckOrder) {

		User user = userService.findById(mobileFamilyCheckOrder.getUserId());
		
		List<String> photos = UploadUtils.uploadFamilyCheckOrderPhotos(request);
		
		mobileFamilyCheckOrder.setUser(user);
		mobileFamilyCheckOrder.setPhotoUrls(photos);
		
		CommonCode commonCode = familyCheckInfoService.updateMobileFamilyCheckOrder(mobileFamilyCheckOrder);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}
	
	
}
