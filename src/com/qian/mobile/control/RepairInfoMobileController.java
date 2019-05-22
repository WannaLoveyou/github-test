package com.qian.mobile.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.code.RepairInfoStateCode;
import com.qian.entity.RepairInfo;
import com.qian.entity.RepairInfoState;
import com.qian.mobile.entity.MobileRepairInfo;
import com.qian.service.RepairInfoService;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

@Controller
@RequestMapping("/mobile/repairInfo")
public class RepairInfoMobileController {

	@Autowired
	private RepairInfoService repairInfoService;

	/**
	 * 通过userId获取已经派工需要进行维修的订单
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "getRepairInfoByUserId")
	public @ResponseBody
	BaseDto<List<MobileRepairInfo>> getRepairInfoByUserId(int userId) {
		List<String> l = new ArrayList<String>();
		Field field = new Field();
		l.add("repairInfoState.id = ?");
		field.addInt(RepairInfoStateCode.ALREADY_DISPATCHING);
		l.add("repair_man.id = ?");
		field.addInt(userId);
		List<RepairInfo> allList = repairInfoService.getAllList(l, field);
		List<MobileRepairInfo> mobileResultList = new ArrayList<>(allList.size());
		for (RepairInfo repairInfo : allList) {
			mobileResultList.add(new MobileRepairInfo(repairInfo));
		}
		return BaseDto.getSuccessResult(mobileResultList);
	}

	/**
	 * 完成维修订单
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "finishOrder")
	public @ResponseBody
	BaseDto<Object> finishOrder(int orderId) {

		RepairInfo repairInfo = repairInfoService.findRepairInfoById(orderId);

		if (repairInfo.getRepairInfoState().getId() != RepairInfoStateCode.ALREADY_DISPATCHING) {
			return BaseDto.getFailResult(CommonCode.ORDER_CAN_NOT_FINISH);
		}

		repairInfo.setRepairInfoState(new RepairInfoState(RepairInfoStateCode.ALREADY_FINISH));
		repairInfoService.update(repairInfo);

		return BaseDto.getSuccessResult(null);
	}
}
