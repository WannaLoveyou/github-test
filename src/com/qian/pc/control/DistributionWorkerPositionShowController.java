package com.qian.pc.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.DistributionWorkerPositionShow;
import com.qian.service.DistributionWorkerPositionShowService;
import com.qian.util.StringUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.DistributionWorkerPositionShowVo;
import com.qian.vo.Field;
import com.qian.vo.WorkerPositionVo;

/**
 * @author Wang LiangShun
 * @version Create time：2018年9月12日 上午9:29:03
 * @description 配送工位置展示
 */
@Controller
@RequestMapping("/distributionWorkerPositionShow")
public class DistributionWorkerPositionShowController {

	private static final String LIST = "distributionWorkerPositionShow/list";

	private static final String MAP = "distributionWorkerPositionShow/worker_Position_Show_map";

	@Autowired
	private DistributionWorkerPositionShowService distributionWorkerPositionShowService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {

		return LIST;
	}

	@RequestMapping(value = "showMap", method = RequestMethod.GET)
	public String showMap(Map<String, Object> map, String create_time_begin_time, String create_time_end_time) {
		map.put("create_time_begin_time", create_time_begin_time);
		map.put("create_time_end_time", create_time_end_time);
		return MAP;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getWorkerPositions")
	public @ResponseBody BaseDto<List<WorkerPositionVo>> getClientCoordinates(HttpServletRequest request) {
		List<String> l = new ArrayList<>();
		Field field = new Field();
		ToolsBarUtils.getOrderPositionList(request, l, field);
		String orderImformationId = request.getParameter("order_imformation.id");
		if (StringUtils.isEmpty(orderImformationId)) {
			return BaseDto.getFailResult(CommonCode.ORDER_NOT_EXIST);
		}
		return distributionWorkerPositionShowService.getPositition(l, field, orderImformationId);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody String add(HttpServletRequest request, Map<String, Object> map,
			DistributionWorkerPositionShowVo distributionWorkerPositionShowVo) {
		distributionWorkerPositionShowService.add(distributionWorkerPositionShowVo);
		return "ok";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody String edit(HttpServletRequest request, Map<String, Object> map,
			DistributionWorkerPositionShow distributionWorkerPositionShow) {
		distributionWorkerPositionShowService.edit(distributionWorkerPositionShow);
		return "ok";
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody String del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		distributionWorkerPositionShowService.delByIds(idString);
		return "ok";
	}

	@RequestMapping(value = "getPageList", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getPageList(HttpServletRequest request, Map<String, Object> map, int page,
			int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = distributionWorkerPositionShowService.getTotalNum();
		List<DistributionWorkerPositionShow> list = distributionWorkerPositionShowService.getPageList(page, rows);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}

	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody List<DistributionWorkerPositionShow> getAllList(HttpServletRequest request,
			Map<String, Object> map) {
		List<DistributionWorkerPositionShow> list = distributionWorkerPositionShowService.getAllList();
		return list;
	}
}
