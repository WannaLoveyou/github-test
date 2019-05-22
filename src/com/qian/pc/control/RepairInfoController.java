package com.qian.pc.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.code.RepairInfoStateCode;
import com.qian.entity.RepairInfo;
import com.qian.entity.RepairInfoState;
import com.qian.entity.User;
import com.qian.service.RepairInfoService;
import com.qian.util.TimeUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

@Controller
@RequestMapping("/repairInfo")
public class RepairInfoController {

	private static final String LIST = "repairInfo/list";
	private static final String DISPATCH_REPAIR_LIST = "order/dispatch_repair_list";

	@Autowired
	private RepairInfoService repairInfoService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {

		return LIST;
	}

	@RequestMapping(value = "allList", method = RequestMethod.GET)
	public String getRepairInfoAllList() {
		return DISPATCH_REPAIR_LIST;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, RepairInfo repairInfo, String repair_appointment_time11, String repair_appointment_time22) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		repairInfo.setRepair_appointment_time1(TimeUtils.formatAppointmentTime(repair_appointment_time11));
		repairInfo.setRepair_appointment_time2(TimeUtils.formatAppointmentTime(repair_appointment_time22));
		repairInfo.setOperator(user);

		if (repairInfo.getRepair_price() == null) {
			repairInfo.setRepair_price(0.0);
		}

		repairInfoService.add(repairInfo);

		return "ok";
	}

	@RequestMapping(value = "getRepairInfo", method = RequestMethod.POST)
	public @ResponseBody
	List<RepairInfo> getRepairInfo(HttpServletRequest request, Map<String, Object> map, int clientId) {
		List<RepairInfo> list = repairInfoService.getRepairInfo(clientId);
		return list;
	}

	@RequestMapping(value = "getRepairInfoAllList", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getRepairInfoAllList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {
		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if (user.getSecondCategory() != null) {
			l.add("secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId()); // 取对应门店
		}

		ToolsBarUtils.getRepairInfoSearchCondition(request, l, filed);

		Map<String, Object> result = new HashMap<String, Object>();
		Long total = repairInfoService.getTotalNum(l, filed);
		List<RepairInfo> list = repairInfoService.getRepairInfoPageList(l, filed, page, rows);
		result.put("total", total);
		result.put("rows", list);

		return result;
	}

	@RequestMapping(value = "dispatch", method = RequestMethod.POST)
	public @ResponseBody
	String dispatch(HttpServletRequest request, Map<String, Object> map, Integer repairId, Integer repairManId) {

		RepairInfo repairInfo = repairInfoService.findRepairInfoById(repairId);

		User repairMan = new User();
		repairMan.setId(repairManId);

		RepairInfoState rs = new RepairInfoState();
		rs.setId(RepairInfoStateCode.ALREADY_DISPATCHING);

		repairInfo.setRepair_man(repairMan);
		repairInfo.setRepairInfoState(rs);

		repairInfoService.update(repairInfo);

		return "ok";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "cancelOrder", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto cancelOrder(HttpServletRequest request, Map<String, Object> map, Integer orderId) {

		RepairInfo repairInfo = repairInfoService.findRepairInfoById(orderId);

		if (repairInfo.getRepairInfoState().getId() == RepairInfoStateCode.ALREADY_FINISH) {
			return BaseDto.getFailResult(CommonCode.ORDER_CAN_NOT_CANCEL);
		}

		repairInfo.setRepairInfoState(new RepairInfoState(RepairInfoStateCode.ALREADY_CANCEL));
		repairInfoService.update(repairInfo);

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "finishOrder", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto finishOrder(HttpServletRequest request, Map<String, Object> map, Integer orderId) {

		RepairInfo repairInfo = repairInfoService.findRepairInfoById(orderId);

		if (repairInfo.getRepairInfoState().getId() != RepairInfoStateCode.ALREADY_DISPATCHING) {
			return BaseDto.getFailResult(CommonCode.ORDER_CAN_NOT_FINISH);
		}

		repairInfo.setRepairInfoState(new RepairInfoState(RepairInfoStateCode.ALREADY_FINISH));
		repairInfoService.update(repairInfo);

		return BaseDto.getSuccessResult(null);
	}
}
