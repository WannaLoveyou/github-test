package com.qian.pc.control;

import java.util.ArrayList;
import java.util.Date;
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

import com.qian.code.AirBottleRentInfoStateCode;
import com.qian.entity.AirBottleRentInfo;
import com.qian.entity.AirBottleRentInfoState;
import com.qian.entity.User;
import com.qian.service.AirBottleRentInfoService;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2018-3-27 下午9:25:14
 * @Description
 */
@Controller
@RequestMapping("/airBottleRentInfo")
public class AirBottleRentInfoController {

	private static final String LIST = "airBottleRentInfo/list";

	@Autowired
	private AirBottleRentInfoService airBottleRentInfoService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {
		return LIST;
	}

	@RequestMapping(value = "getPageList", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Map<String, Object>> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {

		List<String> l = new ArrayList<String>();
		Field field = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if (user.getSecondCategory() != null) {
			l.add("secondCategory.id = ?");
			field.addInt(user.getSecondCategory().getId()); // 取对应门店
		}
		
		ToolsBarUtils.getAirBottleRentInfoSearchCondition(request, l, field);

		return airBottleRentInfoService.getPageList(l, field, page, rows);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> add(HttpServletRequest request, Map<String, Object> map, AirBottleRentInfo airBottleRentInfo) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		airBottleRentInfo.setRent_operator(user);
		airBottleRentInfo.setSecondCategory(user.getSecondCategory());
		airBottleRentInfo.setRent_time(new Date());
		airBottleRentInfo.setAirBottleRentInfoState(new AirBottleRentInfoState(AirBottleRentInfoStateCode.NO_BACK));

		
		return airBottleRentInfoService.add(airBottleRentInfo);
	}
	
	@RequestMapping(value = "back", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> back(HttpServletRequest request, Map<String, Object> map, int airBottleRentInfoId,double rentMoney) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		return airBottleRentInfoService.back(airBottleRentInfoId,rentMoney,user);
	}
	
	@RequestMapping(value = "getBackInfo", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Double> getBackInfo(HttpServletRequest request, Map<String, Object> map, int airBottleRentInfoId) {

		return airBottleRentInfoService.getBackInfo(airBottleRentInfoId);
	}
}
