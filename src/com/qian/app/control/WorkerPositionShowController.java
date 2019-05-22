package com.qian.app.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.service.DistributionWorkerPositionShowService;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.DistributionWorkerPositionShowVo;
import com.qian.vo.Field;
import com.qian.vo.WorkerPositionVo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-21 下午4:18:07
 * @Description
 */
@Controller
@RequestMapping("/app/workerPositionShow")
public class WorkerPositionShowController {

	@Autowired
	private DistributionWorkerPositionShowService distributionWorkerPositionShowService;
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, Map<String, Object> map, DistributionWorkerPositionShowVo distributionWorkerPositionShowVo) {
		distributionWorkerPositionShowService.add(distributionWorkerPositionShowVo);
		return "ok";
	}
	
	@RequestMapping(value = "getWorkerPositions")
	public @ResponseBody BaseDto<List<WorkerPositionVo>> getClientCoordinates(HttpServletRequest request) {
		List<String> l = new ArrayList<>();
		Field field = new Field();
		ToolsBarUtils.getOrderPositionList(request, l, field);
		String orderImformationId = request.getParameter("order_imformation.id");
		
		return distributionWorkerPositionShowService.getPositition(l, field,orderImformationId);
	}

}
