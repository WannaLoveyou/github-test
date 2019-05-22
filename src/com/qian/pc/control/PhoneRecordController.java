package com.qian.pc.control;

import java.util.ArrayList;
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

import com.qian.code.PermissionCode;
import com.qian.code.PhoneRecordStateCode;
import com.qian.entity.PhoneRecord;
import com.qian.entity.PhoneRecordState;
import com.qian.entity.User;
import com.qian.service.PhoneRecordService;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

@Controller
@RequestMapping("/phoneRecord")
public class PhoneRecordController {

	private static final String LIST = "phoneRecord/list";

	@Autowired
	private PhoneRecordService phoneRecordService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list() {

		return LIST;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> add(String phoneNumber) {

		return phoneRecordService.add(new PhoneRecord(phoneNumber));
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> del(String ids) {

		String idString[] = ids.split(",");

		return phoneRecordService.delByIds(idString);
	}

	@RequestMapping(value = "callBack", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> callBack(int recordId) {

		return phoneRecordService.callBack(recordId);
	}

	@RequestMapping(value = "getPageList")
	public @ResponseBody
	BaseDto<Map<String, Object>> getPageList(HttpServletRequest request, int page, int rows) {
		List<String> l = new ArrayList<>();
		Field field = new Field();
		ToolsBarUtils.getPhoneRecordSearchCondition(request, l, field);

		return phoneRecordService.getPageList(l, field, page, rows);
	}

	@RequestMapping(value = "getAllStates")
	public @ResponseBody
	BaseDto<List<PhoneRecordState>> getAllStates() {

		return phoneRecordService.getAllStates();
	}

	@RequestMapping(value = "checkCallsRecordPermission", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<?> checkCallsRecordPermission(HttpServletRequest request, Map<String, Object> map) {

		Subject subject = SecurityUtils.getSubject();

		boolean b = subject.isPermitted(PermissionCode.CALLS_RECORD);

		return BaseDto.getSuccessResult(b);
	}

	@RequestMapping(value = "addReceivedCallsRecord")
	public @ResponseBody
	BaseDto<?> addReceivedCallsRecord(HttpServletRequest request, Map<String, Object> map, String phoneNumber) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		
		PhoneRecord phoneRecord = new PhoneRecord(phoneNumber);
		phoneRecord.setPhoneRecordState(new PhoneRecordState(PhoneRecordStateCode.HAS_RECEIVED));
		phoneRecord.setAnswerOperator(user);

		phoneRecordService.add(phoneRecord);

		return BaseDto.getSuccessResult(null);
	}
}
