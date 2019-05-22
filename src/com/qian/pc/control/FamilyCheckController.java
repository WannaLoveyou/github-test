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

import com.qian.entity.FamilyCheckContentInfo;
import com.qian.entity.FamilyCheckInfo;
import com.qian.entity.User;
import com.qian.service.FamilyCheckInfoService;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-9 下午3:47:07
 * @Description
 */
@Controller
@RequestMapping("/familyCheck")
public class FamilyCheckController {

	@Autowired
	private FamilyCheckInfoService familyCheckInfoService;

	private static final String LIST = "familyCheck/list";

	private static final String CONTENT_LIST = "familyCheck/content_list";

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {

		return LIST;
	}

	@RequiresUser
	@RequestMapping(value = "contentList", method = RequestMethod.GET)
	public String contentList(HttpServletRequest request, Map<String, Object> map, int familyCheckId) {

		map.put("familyCheckId", familyCheckId);
		return CONTENT_LIST;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addFamilyCheckOrder", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> addFamilyCheckOrder(HttpServletRequest request, Map<String, Object> map, FamilyCheckInfo familyCheckInfo) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		familyCheckInfo.setOperator(user);

		CommonCode commonCode = familyCheckInfoService.addFamilyCheckOrder(familyCheckInfo);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@RequestMapping(value = "getPageList", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Map<String, Object>> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {
		
		Map<String, Object> result = new HashMap<String, Object>();

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		if (user.getSecondCategory() != null) {
			l.add("clientInfo.secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId());
		}

		ToolsBarUtils.getFamilyCheckInfoSearchCondition(request, l, filed);

		Long total = familyCheckInfoService.getTotalNum(l, filed);
		List<FamilyCheckInfo> list = familyCheckInfoService.getPageList(l, filed, page, rows);
		result.put("total", total);
		result.put("rows", list);

		return BaseDto.getSuccessResult(result);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "fillInFamilyCheckOrder", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> fillInFamilyCheckOrder(HttpServletRequest request, Map<String, Object> map, int familyCheckId, FamilyCheckContentInfo familyCheckContentInfo) {

		CommonCode commonCode = familyCheckInfoService.fillInFamilyCheckOrder(familyCheckId, familyCheckContentInfo);

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@RequestMapping(value = "getFamilyCheckOrderById", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<FamilyCheckInfo> getFamilyCheckOrderById(HttpServletRequest request, Map<String, Object> map, int familyCheckId) {

		FamilyCheckInfo familyCheckInfo = familyCheckInfoService.getFamilyCheckOrderById(familyCheckId);

		return BaseDto.getSuccessResult(familyCheckInfo);
	}
	
	
	@RequestMapping(value = "getFamilyCheckOrderContentById", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<FamilyCheckContentInfo> getFamilyCheckOrderContentById(HttpServletRequest request, Map<String, Object> map, int familyCheckId) {

		FamilyCheckInfo familyCheckInfo = familyCheckInfoService.getFamilyCheckOrderById(familyCheckId);

		FamilyCheckContentInfo familyCheckContentInfo = familyCheckInfo.getFamilyCheckContentInfo();
		
		return BaseDto.getSuccessResult(familyCheckContentInfo);
	}
	
	/**
	 * 批量撤销
	 */
	@RequestMapping(value = "revokeFamilyCheck", method = RequestMethod.POST)
	public @ResponseBody BaseDto<Map<String,Object>> revokeFamilyCheck(HttpServletRequest request, Map<String, Object> map, 
			String ids) {

		return familyCheckInfoService.revokeFamilyCheck(ids);
	}

}
