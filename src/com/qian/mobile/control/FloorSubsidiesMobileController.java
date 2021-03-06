package com.qian.mobile.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.app.entity.AppFloorSubsidies;
import com.qian.entity.FloorSubsidies;
import com.qian.service.FloorSubsidiesService;
import com.qian.vo.BaseDto;

/**
 * @author Lu Kongwen
 * @version Create time：2019-1-10 下午4:23:22
 * @Description
 */
@Controller
@RequestMapping("/mobile/floorSubsidies")
public class FloorSubsidiesMobileController {

	@Autowired
	private FloorSubsidiesService floorSubsidiesService;

	@RequestMapping(value = "getAllList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<List<AppFloorSubsidies>> getAllList(HttpServletRequest request, Map<String, Object> map) {

		List<FloorSubsidies> list = floorSubsidiesService.getAllList();

		List<AppFloorSubsidies> result = new ArrayList<AppFloorSubsidies>();

		for (FloorSubsidies floorSubsidies : list) {

			AppFloorSubsidies appFloorSubsidies = new AppFloorSubsidies(floorSubsidies);

			result.add(appFloorSubsidies);
		}

		return BaseDto.getSuccessResult(result);

	}
}
