package com.qian.pc.control;

import java.io.IOException;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qian.code.DisabledStateCode;
import com.qian.entity.ClientInfo;
import com.qian.entity.User;
import com.qian.service.ClientAirBottleRecrodService;
import com.qian.service.ClientInfoService;
import com.qian.util.SendSMSUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2015-11-10 上午9:05:58
 * @Description
 */

@Controller
@RequestMapping("/clientInfo")
public class ClientInfoController {

	private static final String LIST = "clientInfo/list";
	
	@Autowired
	private ClientInfoService clientInfoService;
	
	@Autowired
	private ClientAirBottleRecrodService clientAirBottleRecrodService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {

		return LIST;
	}

	@RequestMapping(value = "getPageList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = clientInfoService.getTotalNum();
		List<ClientInfo> list = clientInfoService.getPageList(page, rows);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}

	@RequestMapping(value = "getNewPageList", method = RequestMethod.GET)
	public @ResponseBody
	BaseDto<Map<String, Object>> getNewPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		if (user.getSecondCategory() != null) {
			l.add("secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId());
		}

		ToolsBarUtils.getClientInfoSearchCondition(request, l, filed);

		Long total = clientInfoService.getTotalNum(l, filed);
		List<ClientInfo> list = clientInfoService.getPageList(l, filed, page, rows);
		result.put("total", total);
		result.put("rows", list);
		return BaseDto.getSuccessResult(result);
	}

	@RequestMapping(value = "getNewAllList1", method = RequestMethod.GET)
	public @ResponseBody
	BaseDto<List<ClientInfo>> getNewAllList(HttpServletRequest request, Map<String, Object> map) {
		List<ClientInfo> list = clientInfoService.getAllList();
		return BaseDto.getSuccessResult(list);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, ClientInfo clientInfo) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		clientInfo.setCreate_people(user.getFull_name());
		clientInfo.setModify_people(user.getFull_name());
		clientInfo.setCreate_time(new Date());
		clientInfo.setModify_time(new Date());
		clientInfo.setCard_money((double) 0);
		clientInfoService.add(clientInfo, user);
		return "ok";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto edit(HttpServletRequest request, Map<String, Object> map, int clientId, ClientInfo clientInfo) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		CommonCode commonCode = clientInfoService.edit(clientId, clientInfo, user);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	String del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		clientInfoService.delByIds(idString);
		return "ok";
	}

	@RequestMapping(value = "getByMobile", method = RequestMethod.POST)
	public @ResponseBody
	ClientInfo getByMobile(HttpServletRequest request, Map<String, Object> map, int page) {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		if (user.getSecondCategory() != null) {
			l.add("secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId());
		}

		ToolsBarUtils.getClientInfoSearchCondition(request, l, filed);

		ClientInfo clientInfo = clientInfoService.getByMobile(l, filed, page);
		
		if(clientInfo != null){
			int bottleNum = clientAirBottleRecrodService.getBottleNumByClientId(clientInfo.getId());
			clientInfo.setBottleNum(bottleNum);
		}
	
		return clientInfo;
	}

	@RequestMapping(value = "getAllCount", method = RequestMethod.POST)
	public @ResponseBody
	long getAllCount(HttpServletRequest request, Map<String, Object> map) {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		if (user.getSecondCategory() != null) {
			l.add("disabled_state = ?");
			filed.addInt(DisabledStateCode.NOT_DISABLE);
		}
		
		l.add("secondCategory.id = ?");
		filed.addInt(user.getSecondCategory().getId());

		ToolsBarUtils.getClientInfoSearchCondition(request, l, filed);

		long count = clientInfoService.getTotalNum(l, filed);

		return count;
	}

	@RequestMapping(value = "getClientNameByclientCode", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<ClientInfo> getClientNameByclientCode(HttpServletRequest request, Map<String, Object> map, String client_code) {
		ClientInfo clientInfo = clientInfoService.getClientNameByclientCode(client_code);
		return BaseDto.getSuccessResult(clientInfo);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "checkClientByClientCodeAndClientId", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<ClientInfo> checkClientByClientCodeAndClientId(HttpServletRequest request, Map<String, Object> map, String client_code, int client_id) {
		ClientInfo clientInfo = clientInfoService.getClientInfoByClientCode(client_code);

		if (clientInfo.getId() == client_id) {
			return BaseDto.getSuccessResult(clientInfo);
		}

		return BaseDto.getFailResult(CommonCode.USER_NOT_MATCH);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "editByOrderView", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> editByOrderView(HttpServletRequest request, Map<String, Object> map, int client_id, String clientInfoStr) throws JsonParseException,
			JsonMappingException, IOException {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		ObjectMapper mapper = new ObjectMapper();

		ClientInfo clientInfo = mapper.readValue(clientInfoStr, new TypeReference<ClientInfo>() {
		});

		CommonCode commonCode = clientInfoService.editByOrderView(client_id, clientInfo, user);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "resetPasswordByClientIdForSMS", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<String> resetPasswordByClientIdForSMS(HttpServletRequest request, Map<String, Object> map, String mobileTelNumber, Integer clientId) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		CommonCode commonCode = clientInfoService.resetPasswordByClientIdForSMS(mobileTelNumber, clientId, user);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}
	
	@RequestMapping(value = "getSMSNum", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Integer> getSMSNum(HttpServletRequest request, Map<String, Object> map) {

		Integer num  = SendSMSUtils.getHuMenSMSNum();

		return BaseDto.getSuccessResult(num);
	}
	

	@RequestMapping(value = "getNowCardMoney", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Double> getNowCardMoney(HttpServletRequest request, Map<String, Object> map, Integer clientId) {

		ClientInfo clientInfo = clientInfoService.findById(clientId);

		return BaseDto.getSuccessResult(clientInfo.getCard_money());
	}

	@RequestMapping(value = "findByClientCode", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<ClientInfo> findByClientCode(HttpServletRequest request, Map<String, Object> map, String clientCode) {

		ClientInfo clientInfo = clientInfoService.findByClientCode(clientCode);

		return BaseDto.getSuccessResult(clientInfo);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "queryPassword", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<String> queryPassword(HttpServletRequest request, Map<String, Object> map, int clientId) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		if(user.getSecondCategory()!=null){
			return BaseDto.getFailResult(CommonCode.STORE_MAN_CAN_NOT_USE);
		}
		
		ClientInfo clientInfo = clientInfoService.findById(clientId);

		return BaseDto.getSuccessResult(clientInfo.getPassword());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "editPassword", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<String> editPassword(HttpServletRequest request, Map<String, Object> map, int clientId,String password) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		if(user.getSecondCategory()!=null){
			return BaseDto.getFailResult(CommonCode.STORE_MAN_CAN_NOT_USE);
		}
		
		ClientInfo clientInfo = clientInfoService.findById(clientId);
		clientInfo.setPassword(password);
		clientInfoService.edit(clientInfo);

		return BaseDto.getSuccessResult(null);
	}
	
	@RequestMapping(value = "cancel")
	public @ResponseBody String cancel(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		clientInfoService.disabled(idString);
		return "ok";
	}

}
