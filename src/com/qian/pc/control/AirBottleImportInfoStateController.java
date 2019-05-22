package com.qian.pc.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.AirBottleImportInfoState;
import com.qian.service.AirBottleImportInfoStateService;

/**
 * @author Lu Kongwen
 * @version Create time：2017-12-22 下午2:59:13
 * @Description
 */
@Controller
@RequestMapping("/airBottleImportInfoState")
public class AirBottleImportInfoStateController {

	@Autowired
	private AirBottleImportInfoStateService airBottleImportInfoStateService;
	
	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody
	List<AirBottleImportInfoState> getAllList(HttpServletRequest request, Map<String, Object> map) {
		
		List<AirBottleImportInfoState>list = airBottleImportInfoStateService.getAllList();
		
		return list;
	}
}
