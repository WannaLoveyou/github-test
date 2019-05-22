package com.qian.pc.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.code.AirBottleInventoryStateCode;
import com.qian.entity.AirBottleState;
import com.qian.entity.AirBottleType;
import com.qian.entity.User;
import com.qian.entity.WarehouseInfo;
import com.qian.mobile.entity.MobileAirBottleSumbitEntity;
import com.qian.service.AirBottleStateService;
import com.qian.service.AirBottleTypeService;
import com.qian.service.WarehouseInfoService;
import com.qian.service.WarehouseInventoryInfoService;
import com.qian.util.StringUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.AirBottleInfoReportDetailVo;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;
import com.qian.vo.WarehouseInventoryInfoSummary;
import com.qian.vo.WarehouseInventoryInfoVo;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-2 下午7:57:41
 * @Description
 */
@Controller
@RequestMapping("/warehouseInventory")
public class WarehouseInventoryController {

	
	private static final String WAREHOUSE_INVENTORY_INFO_SUMMARY_LIST = "warehouse/warehouse_inventory_info_list";

	@Autowired
	private WarehouseInventoryInfoService warehouseInventoryInfoService;

	@Autowired
	private AirBottleTypeService airBottleTypeService;

	@Autowired
	private AirBottleStateService airBottleStateService;
	
	@Autowired
	private WarehouseInfoService warehouseInfoService;
	
	@RequiresUser
	@RequestMapping(value = "warehouseInventoryInfoSummaryList", method = RequestMethod.GET)
	public String warehouseInventoryInfoSummaryList(HttpServletRequest request, Map<String, Object> map) {

		return WAREHOUSE_INVENTORY_INFO_SUMMARY_LIST;
	}
	
	@RequestMapping(value = "getDetailsInventoryInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	List<AirBottleInfoReportDetailVo> getDetailsInventoryInfo(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		String warehouseId = request.getParameter("warehouseId");
		if (StringUtils.nonEmpty(warehouseId)) {
			l.add("warehouseInfo.id = ?");
			filed.addInt(Integer.parseInt(warehouseId));
		}

		String airBottleStateId = request.getParameter("airBottleStateId");
		if (StringUtils.nonEmpty(airBottleStateId)) {
			l.add("airBottleState.id = ?");
			filed.addInt(Integer.parseInt(airBottleStateId));
		}

		String airBottleTypeId = request.getParameter("airBottleTypeId");
		if (StringUtils.nonEmpty(airBottleTypeId)) {
			l.add("airBottleInfo.airBottleType.id = ?");
			filed.addInt(Integer.parseInt(airBottleTypeId));
		}

		List<AirBottleInfoReportDetailVo> list = warehouseInventoryInfoService.getDetailsInventoryInfo(l, filed);

		return list;
	}
	
	@RequestMapping(value = "getWarehouseInventoryInfo", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Map<String, Object>> getWarehouseInventoryInfo(HttpServletRequest request, Map<String, Object> map) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		if (user.getWarehouseInfo() != null) {
			l.add("warehouseInfo.id = ?");
			filed.addInt(user.getWarehouseInfo().getId());
		}
		
		l.add("airBottleInventoryState.id = ?");
		filed.addInt(AirBottleInventoryStateCode.ALREADY_RECEIVE);

		ToolsBarUtils.getWarehouseInventoryInfoSearchCondition(request, l, filed);

		List<WarehouseInventoryInfoVo> list = warehouseInventoryInfoService.getWarehouseInventoryInfoVo(l, filed);

		Map<String, Integer> warehouseInventoryInfoMap = new TreeMap<String, Integer>();

		StringBuffer keySb = new StringBuffer();

		for (WarehouseInventoryInfoVo warehouseInventoryInfoVo : list) {

			keySb.setLength(0);
			keySb.append(warehouseInventoryInfoVo.getWarehouseId());
			keySb.append("-");
			keySb.append(warehouseInventoryInfoVo.getAirBottleTypeId());
			keySb.append("-");
			keySb.append(warehouseInventoryInfoVo.getAirBottleStateId());

			String key = keySb.toString();

			Integer inventoryNum = warehouseInventoryInfoVo.getInventoryNum();

			warehouseInventoryInfoMap.put(key, inventoryNum);
		}

		List<WarehouseInventoryInfoSummary> warehouseInventoryInfoSummaryList = new ArrayList<WarehouseInventoryInfoSummary>();

		List<WarehouseInfo> warehouseInfoList = warehouseInfoService.getAllList();

		List<AirBottleType> airBottleTypeList = airBottleTypeService.getAllList();

		List<AirBottleState> airBottleStateList = airBottleStateService.getAllList();

		for(WarehouseInfo warehouseInfo:warehouseInfoList){
		for (AirBottleType airBottleType : airBottleTypeList) {
			for (AirBottleState airBottleState : airBottleStateList) {

				keySb.setLength(0);
				keySb.append(warehouseInfo.getId());
				keySb.append("-");
				keySb.append(airBottleType.getId());
				keySb.append("-");
				keySb.append(airBottleState.getId());

				String key = keySb.toString();

				Integer inventoryNum = warehouseInventoryInfoMap.get(key);
				if (inventoryNum == null) {
					inventoryNum = 0;
				}

				WarehouseInventoryInfoSummary warehouseInventoryInfoSummary = new WarehouseInventoryInfoSummary();
				warehouseInventoryInfoSummary.setWarehouse_id(warehouseInfo.getId());
				warehouseInventoryInfoSummary.setAir_bottle_type_id(airBottleType.getId());
				warehouseInventoryInfoSummary.setAir_bottle_state_id(airBottleState.getId());

				warehouseInventoryInfoSummary.setWarehouse_name(warehouseInfo.getWarehouse_name());
				warehouseInventoryInfoSummary.setAir_bottle_type_name(airBottleType.getAir_bottle_specifications());

				warehouseInventoryInfoSummary.setAir_bottle_state_description(airBottleState.getState_description());

				warehouseInventoryInfoSummary.setInventory_num(inventoryNum.intValue());
				warehouseInventoryInfoSummaryList.add(warehouseInventoryInfoSummary);
			}
		}}

		result.put("rows", warehouseInventoryInfoSummaryList);

		return BaseDto.getSuccessResult(result);

	}
	
	/**
	 * 强制报废
	 * @param request
	 * @param map
	 * @param bottleCodes
	 * @return
	 */
	@RequestMapping(value = "forceScrapBottleStorage", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Map<String, Object>> forceScrapBottleStorage(HttpServletRequest request, Map<String, Object> map,String bottleIds) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		return warehouseInventoryInfoService.forceScrapBottleStorage(bottleIds, user);
	}
	
	/**
	 * 撤销报废
	 * @param request
	 * @param map
	 * @param bottleCodes
	 * @return
	 */
	@RequestMapping(value = "cancelForceScrapBottleStorage", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Map<String, Object>> cancelForceScrapBottleStorage(HttpServletRequest request, Map<String, Object> map,String bottleIds) {
		
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		
		return warehouseInventoryInfoService.cancelForceScrapBottleStorage(bottleIds, user);
	}

}
