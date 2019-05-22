package com.qian.pc.control;

import java.util.ArrayList;
import java.util.Date;
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

import com.qian.entity.ClientAirBottleTypeFee;
import com.qian.entity.User;
import com.qian.service.ClientAirBottleTypeFeeService;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

@Controller
@RequestMapping("/clientAirBottleTypeFee")
public class ClientAirBottleTypeFeeController {

	private static final String LIST = "clientAirBottleTypeFee/list";
	@Autowired
	private ClientAirBottleTypeFeeService clientAirBottleTypeFeeService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {
		return LIST;
	}

	@RequestMapping(value = "getPageList")
	public @ResponseBody
	BaseDto<Map<String, Object>> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<String> l = new ArrayList<String>();
		Field filed = new Field();
		ToolsBarUtils.getClientAirBottleTypeFeeSearchCondition(request, l, filed);

		Long total = clientAirBottleTypeFeeService.getTotalNum(l, filed);
		List<ClientAirBottleTypeFee> list = clientAirBottleTypeFeeService.getPageList(l, filed, page, rows);

		result.put("total", total);
		result.put("rows", list);

		return BaseDto.getSuccessResult(result);
	}

	@RequestMapping(value = "getAllList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<ClientAirBottleTypeFee>> getAllList(HttpServletRequest request, Map<String, Object> map) {
		List<ClientAirBottleTypeFee> result = clientAirBottleTypeFeeService.getAllList();
		return BaseDto.getSuccessResult(result);
	}

	/*
	 * @RequestMapping(value = "getDeliveryFeeByAirBottleType", method = {RequestMethod.GET,RequestMethod.POST}) public @ResponseBody BaseDto<Double>
	 * getDeliveryFeeByAirBottleType(HttpServletRequest request, Map<String, Object> map,int airBottleTypeId,int clientId) { Double
	 * fee=clientAirBottleTypeFeeService.getFee(airBottleTypeId, clientId); return BaseDto.getSuccessResult(fee); }
	 */

	@RequestMapping(value = "getDeliveryFeeByAirBottleType", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<ClientAirBottleTypeFee> getDeliveryFeeByAirBottleType(HttpServletRequest request, Map<String, Object> map, int airBottleTypeId, int clientId) {

		ClientAirBottleTypeFee clientAirBottleTypeFee = clientAirBottleTypeFeeService.getDeliveryandCheckFee(airBottleTypeId, clientId);

		return BaseDto.getSuccessResult(clientAirBottleTypeFee);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> add(HttpServletRequest request, Map<String, Object> map, ClientAirBottleTypeFee clientAirBottleTypeFee) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		int num = clientAirBottleTypeFeeService.getClientAirBottleTypeFeeId(clientAirBottleTypeFee.getAirBottleType().getId(),
				clientAirBottleTypeFee.getClientInfo().getId());

		if (num != 0) {
			return BaseDto.getFailResult(CommonCode.CLIENT_AIR_BOTTLE_TYPE_FEE_HAS_EXIST);
		}

		clientAirBottleTypeFee.setCreate_time(new Date());
		clientAirBottleTypeFee.setCreate_people(user.getFull_name());
		clientAirBottleTypeFeeService.add(clientAirBottleTypeFee);

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> edit(HttpServletRequest request, Map<String, Object> map, ClientAirBottleTypeFee clientAirBottleTypeFee) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		int num = clientAirBottleTypeFeeService.getClientAirBottleTypeFeeIdByEdit(clientAirBottleTypeFee.getId(),clientAirBottleTypeFee.getAirBottleType().getId(),
				clientAirBottleTypeFee.getClientInfo().getId());

		if (num != 0) {
			return BaseDto.getFailResult(CommonCode.CLIENT_AIR_BOTTLE_TYPE_FEE_HAS_EXIST);
		}
		
		clientAirBottleTypeFee.setModify_time(new Date());
		clientAirBottleTypeFee.setModify_people(user.getFull_name());
		clientAirBottleTypeFeeService.edit(clientAirBottleTypeFee);
		return BaseDto.getSuccessResult(null);
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		clientAirBottleTypeFeeService.delByIds(idString);
		return BaseDto.getSuccessResult(null);
	}
}