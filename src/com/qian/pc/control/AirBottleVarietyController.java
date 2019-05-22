package com.qian.pc.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.AirBottleVariety;
import com.qian.service.AirBottleVarietyService;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-28 上午11:19:40
 * @Description
 */
@Controller
@RequestMapping("/airBottleVariety")
public class AirBottleVarietyController {

	@Autowired
	private AirBottleVarietyService airBottleVarietyService;

	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody
	List<AirBottleVariety> getAllList(HttpServletRequest request, Map<String, Object> map) {
		List<AirBottleVariety> list = airBottleVarietyService.getAllList();
		return list;
	}
}
