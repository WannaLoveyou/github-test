package com.qian.pc.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.code.AirBottleTrackingRecordCode;
import com.qian.entity.User;
import com.qian.service.AirBottleTrackingRecordService;
import com.qian.service.UserService;
import com.qian.util.ExportHeadsUtils;
import com.qian.util.ExportUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.AirBottleTrackingRecordCountVo;
import com.qian.vo.HeavyBottleOutToDeliveryManInWarehouseVo;
import com.qian.vo.ExportOrderInfoReportHeads;
import com.qian.vo.Field;

/**
 * 
 * @author sc
 *
 */
@Controller
@RequestMapping("/heavyBottleOutToDeliveryManInWarehouseReport")
public class HeavyBottleOutToDeliveryManInWarehouseReportController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AirBottleTrackingRecordService airBottleTrackingRecordService;

	private static final String LIST = "heavyBottleOutToDeliveryManInWarehouseReport/list";

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {
		
		return LIST;
	}
	
	@RequestMapping(value = "getListData", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getListData(HttpServletRequest request, Map<String, Object> map) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<HeavyBottleOutToDeliveryManInWarehouseVo> list = getHeavyBottleOutToDeliveryManInWarehouseList(request, map);
		
		result.put("rows", list);
		
		Integer total = 0;
		
		for (HeavyBottleOutToDeliveryManInWarehouseVo deliveryManReavyBottleOutVo : list) {
			total += deliveryManReavyBottleOutVo.getTotal();
		}
		
		HeavyBottleOutToDeliveryManInWarehouseVo deliveryManReavyBottleOutVoFotter = new HeavyBottleOutToDeliveryManInWarehouseVo(null, (long)total);
		
		result.put("footer", deliveryManReavyBottleOutVoFotter);
		
		return result;
		
	}
	
	@RequestMapping(value = "exportReport", method = RequestMethod.POST)
	public void exportReport(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {
		
		List<ExportOrderInfoReportHeads> heads = ExportHeadsUtils.getExportOrderInfoReportHeads(request);
		
		List<HeavyBottleOutToDeliveryManInWarehouseVo> list = getHeavyBottleOutToDeliveryManInWarehouseList(request, map);
		
		ExportUtils
		.exportExcel(response, heads, list, "送气工重瓶出库汇总报表.xls", "template-DeliveryManReavyBottleOutReportInfo.xls");
		
	}

	private List<HeavyBottleOutToDeliveryManInWarehouseVo> getHeavyBottleOutToDeliveryManInWarehouseList(HttpServletRequest request, Map<String, Object> map) {
		
		List<String> l = new ArrayList<String>();
		Field field = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		if (user.getSecondCategory() != null) {

			l.add("deliveryMan.secondCategory.id = ?");
			field.addInt(user.getSecondCategory().getId()); // 取对应门店

		}
		
		l.add("state.id = ?");
		field.addInt(AirBottleTrackingRecordCode.HEAVY_BOTTLE_OUT_IN_WAREHOUSE); // 

		ToolsBarUtils.getAirBottleTrackingRecordSearchCondition(request, l, field);

		List<AirBottleTrackingRecordCountVo> list = airBottleTrackingRecordService.getTotalInWarehouseGroupByDeliveryMan(l, field);
		
		List<HeavyBottleOutToDeliveryManInWarehouseVo> result = new ArrayList<HeavyBottleOutToDeliveryManInWarehouseVo>();
		
		for (AirBottleTrackingRecordCountVo airBottleTrackingRecordTotalVo : list) {
			HeavyBottleOutToDeliveryManInWarehouseVo vo = new HeavyBottleOutToDeliveryManInWarehouseVo(airBottleTrackingRecordTotalVo);
			result.add(vo);
		}
		
		return result;
		
	}
}
