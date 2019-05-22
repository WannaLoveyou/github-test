package com.qian.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.app.entity.AppAddFamilyCheckOrder;
import com.qian.code.FamilyCheckInfoStateCode;
import com.qian.code.OperatorCode;
import com.qian.dao.impl.ClientInfoDaoImpl;
import com.qian.dao.impl.FamilyCheckContentInfoDaoImpl;
import com.qian.dao.impl.FamilyCheckInfoDaoImpl;
import com.qian.dao.impl.SecondCategoryDaoImpl;
import com.qian.dao.impl.UserDaoImpl;
import com.qian.entity.ClientInfo;
import com.qian.entity.FamilyCheckContentInfo;
import com.qian.entity.FamilyCheckInfo;
import com.qian.entity.FamilyCheckInfoState;
import com.qian.entity.User;
import com.qian.mobile.entity.MobileFamilyCheckOrder;
import com.qian.service.FamilyCheckInfoService;
import com.qian.util.CodeUtils;
import com.qian.util.InitDataUtils;
import com.qian.util.TimeUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CheckOperatorFamilyCheckInfoVo;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-9 下午3:11:00
 * @Description
 */
@Service
@Transactional
public class FamilyCheckInfoServiceImpl implements FamilyCheckInfoService {

	@Autowired
	private FamilyCheckInfoDaoImpl familyCheckInfoDaoImpl;

	@Autowired
	private ClientInfoDaoImpl clientInfoDaoImpl;

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private FamilyCheckContentInfoDaoImpl familyCheckContentInfoDaoImpl;
	
	@Autowired
	private SecondCategoryDaoImpl secondCategoryDaoImpl;

	public CommonCode addFamilyCheckOrder(FamilyCheckInfo familyCheckInfo) {

		familyCheckInfo.setCreate_time(new Date());

		familyCheckInfo.setFamilyCheckInfoState(new FamilyCheckInfoState(FamilyCheckInfoStateCode.T0_BE_ACCETED));

		familyCheckInfoDaoImpl.insert(familyCheckInfo);

		familyCheckInfo.setFamily_check_code(CodeUtils.setFamilyCheckCode(familyCheckInfo));

		familyCheckInfoDaoImpl.update(familyCheckInfo);

		return CommonCode.OK;
	}

	public List<FamilyCheckInfo> getPageList(List<String> l, Field filed, int page, int rows) {

		return familyCheckInfoDaoImpl.getPageList(l, filed, page, rows);
	}

	public Long getTotalNum(List<String> l, Field filed) {

		return familyCheckInfoDaoImpl.getTotalNum(l, filed);
	}

	public CommonCode addAppFamilyCheckOrder(AppAddFamilyCheckOrder appAddFamilyCheckOrder) {

		Date nowTime = new Date();

		FamilyCheckInfo familyCheckInfo = new FamilyCheckInfo();

		ClientInfo clientInfo = clientInfoDaoImpl.findById(appAddFamilyCheckOrder.getClientId());
		familyCheckInfo.setClientInfo(clientInfo);

		familyCheckInfo.setCreate_time(nowTime);

		if (appAddFamilyCheckOrder.getOrderAppointmentTime() == null) {
			familyCheckInfo.setAppointment_check_time(nowTime);

		} else {
			Date appointmentTime = TimeUtils.formatAppointmentTime(appAddFamilyCheckOrder.getOrderAppointmentTime());
			familyCheckInfo.setAppointment_check_time(appointmentTime);
		}

		User operator = userDaoImpl.findById(OperatorCode.NEW_WEIXIN);
		familyCheckInfo.setOperator(operator);

		familyCheckInfo.setFamily_check_address(appAddFamilyCheckOrder.getOrderAddress());

		familyCheckInfo.setFamily_check_tel_number(appAddFamilyCheckOrder.getOrderTelNumber());

		familyCheckInfo.setRemark(appAddFamilyCheckOrder.getRemark());

		familyCheckInfo.setFamilyCheckInfoState(new FamilyCheckInfoState(FamilyCheckInfoStateCode.T0_BE_ACCETED));

		familyCheckInfoDaoImpl.insert(familyCheckInfo);

		familyCheckInfo.setFamily_check_code(CodeUtils.setFamilyCheckCode(familyCheckInfo));

		familyCheckInfoDaoImpl.update(familyCheckInfo);

		return CommonCode.OK;
	}

	public CommonCode fillInFamilyCheckOrder(int familyCheckId, FamilyCheckContentInfo familyCheckContentInfo) {

		FamilyCheckInfo familyCheckInfo = familyCheckInfoDaoImpl.findById(familyCheckId);

		if (familyCheckInfo == null) {
			return CommonCode.ORDER_NOT_EXIST;
		}

		familyCheckContentInfoDaoImpl.insert(familyCheckContentInfo);
		familyCheckInfo.setFamilyCheckContentInfo(familyCheckContentInfo);
		familyCheckInfo.setFamilyCheckInfoState(new FamilyCheckInfoState(FamilyCheckInfoStateCode.HAS_CHECK_NO_PROPLEM));
		familyCheckInfoDaoImpl.update(familyCheckInfo);

		return CommonCode.OK;
	}

	public FamilyCheckContentInfo findFamilyCheckContentByFamilyCheckId(int familyCheckId) {

		FamilyCheckInfo familyCheckInfo = familyCheckInfoDaoImpl.findById(familyCheckId);

		if (familyCheckInfo == null) {
			return null;
		}

		return familyCheckInfo.getFamilyCheckContentInfo();
	}

	public FamilyCheckInfo getFamilyCheckOrderById(int familyCheckId) {

		return familyCheckInfoDaoImpl.findById(familyCheckId);
	}

	public CommonCode saveFamilyCheckPhoto(int familyCheckId, String url) {

		FamilyCheckInfo familyCheckInfo = familyCheckInfoDaoImpl.findById(familyCheckId);

		if (familyCheckInfo == null) {
			return CommonCode.ORDER_NOT_EXIST;
		}

		familyCheckInfo.setPhoto_urls(url);

		familyCheckInfoDaoImpl.update(familyCheckInfo);

		return CommonCode.OK;
	}

	public CommonCode addMobileFamilyCheckOrder(MobileFamilyCheckOrder mobileFamilyCheckOrder) {

		Date nowTime = new Date();

		FamilyCheckInfo familyCheckInfo = new FamilyCheckInfo();

		ClientInfo clientInfo = clientInfoDaoImpl.findById(mobileFamilyCheckOrder.getClientId());
		familyCheckInfo.setClientInfo(clientInfo);

		familyCheckInfo.setCreate_time(nowTime);

		familyCheckInfo.setAppointment_check_time(nowTime);

		familyCheckInfo.setOperator(mobileFamilyCheckOrder.getUser());
		familyCheckInfo.setCheck_operator(mobileFamilyCheckOrder.getUser());

		familyCheckInfo.setFamily_check_address(clientInfo.getClient_address());

		familyCheckInfo.setFamily_check_tel_number(InitDataUtils.findOrderNumber(clientInfo));

		familyCheckInfo.setRemark(null);

		familyCheckInfo.setCheck_time(nowTime);

		familyCheckInfo.setFamilyCheckInfoState(new FamilyCheckInfoState(FamilyCheckInfoStateCode.HAS_CHECK));

		if (mobileFamilyCheckOrder.getPhotoUrls().size() > 0) {
			familyCheckInfo.setPhoto_urls(mobileFamilyCheckOrder.getPhotoUrls().get(0));
		}

		familyCheckInfoDaoImpl.insert(familyCheckInfo);

		familyCheckInfo.setFamily_check_code(CodeUtils.setFamilyCheckCode(familyCheckInfo));

		familyCheckInfoDaoImpl.update(familyCheckInfo);

		// 更改用户最后入户安检时间
		clientInfo.setLast_family_check_time(nowTime);
		clientInfoDaoImpl.update(clientInfo);

		return CommonCode.OK;
	}

	public CommonCode updateMobileFamilyCheckOrder(MobileFamilyCheckOrder mobileFamilyCheckOrder) {

		Date nowTime = new Date();

		FamilyCheckInfo familyCheckInfo = familyCheckInfoDaoImpl.findById(mobileFamilyCheckOrder.getOrderId());

		familyCheckInfo.setFamilyCheckInfoState(new FamilyCheckInfoState(FamilyCheckInfoStateCode.HAS_CHECK));

		if (mobileFamilyCheckOrder.getPhotoUrls().size() > 0) {
			familyCheckInfo.setPhoto_urls(mobileFamilyCheckOrder.getPhotoUrls().get(0));
		}

		familyCheckInfo.setCheck_operator(mobileFamilyCheckOrder.getUser());
		familyCheckInfo.setCheck_time(nowTime);

		familyCheckInfoDaoImpl.update(familyCheckInfo);

		ClientInfo clientInfo = clientInfoDaoImpl.findById(familyCheckInfo.getClientInfo().getId());
		// 更改用户最后入户安检时间
		clientInfo.setLast_family_check_time(nowTime);
		clientInfoDaoImpl.update(clientInfo);

		return CommonCode.OK;
	}

	public List<CheckOperatorFamilyCheckInfoVo> getFamilyCheckClientNumByStore(List<String> list, Field filed) {

		List<CheckOperatorFamilyCheckInfoVo> checkOperatorFamilyCheckInfoVos = familyCheckInfoDaoImpl.getFamilyCheckClientNumByStore(list, filed);

		for (CheckOperatorFamilyCheckInfoVo checkOperatorFamilyCheckInfoVo : checkOperatorFamilyCheckInfoVos) {
			if (checkOperatorFamilyCheckInfoVo.getSecond_category_id() != null) {
				checkOperatorFamilyCheckInfoVo.setSecond_category_name(secondCategoryDaoImpl.findById(checkOperatorFamilyCheckInfoVo.getSecond_category_id())
						.getSecond_category_name());
			}
		}

		return checkOperatorFamilyCheckInfoVos;
	}
	
	/**
	 * 批量撤销
	 */
	@Override
	public BaseDto<Map<String,Object>> revokeFamilyCheck(String ids) {
		
		String[] idStr = ids.split(",");
		Integer num = 0;
		for (String string : idStr) {
			Integer id = Integer.parseInt(string);
			FamilyCheckInfo familyCheckInfo = familyCheckInfoDaoImpl.findById(id);
			
			if (familyCheckInfo.getFamilyCheckInfoState().getId()!=FamilyCheckInfoStateCode.T0_BE_ACCETED) continue;
			
			familyCheckInfo.setFamilyCheckInfoState(new FamilyCheckInfoState(FamilyCheckInfoStateCode.HAS_CANCEL));
			num += familyCheckInfoDaoImpl.update(familyCheckInfo);
		}
		
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("num", num);
		
		return BaseDto.getSuccessResult(res);
	}
}
