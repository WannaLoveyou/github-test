package com.qian.pc.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.code.AirBottleInventoryStateCode;
import com.qian.entity.AirBottleState;
import com.qian.entity.AirBottleType;
import com.qian.entity.SecondCategory;
import com.qian.entity.User;
import com.qian.mobile.entity.MobileAirBottleSumbitEntity;
import com.qian.service.AirBottleStateService;
import com.qian.service.AirBottleTypeService;
import com.qian.service.SecondCategoryService;
import com.qian.service.StoreInventoryInfoService;
import com.qian.util.StringUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.AirBottleInfoReportDetailVo;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;
import com.qian.vo.StoreInventoryInfoSummary;
import com.qian.vo.StoreInventoryInfoVo;
/**
 * @author Lu Kongwen
 * @version Create time：2016-5-7 下午3:07:31
 * @Description
 */
@Controller
@RequestMapping("/storeInventory")
public class StoreInventoryController {

	private static final String SOTRE_INVENTORY_INFO_LIST = "store/store_inventory_info_list";

	@Autowired
	private StoreInventoryInfoService storeInventoryInfoService;
	
	@Autowired
	private SecondCategoryService secondCategoryService;
	
	@Autowired
	private AirBottleTypeService airBottleTypeService;
	
	@Autowired
	private AirBottleStateService airBottleStateService;
	
	
	@RequestMapping(value = "storeInventoryInfoList", method = RequestMethod.GET)
	public String storeHeavyBottleOutList(HttpServletRequest request, Map<String, Object> map) {
		return SOTRE_INVENTORY_INFO_LIST;
	}
	
	@RequestMapping(value = "getDetailsInventoryInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	List<AirBottleInfoReportDetailVo> getDetailsInventoryInfo(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		String secondCategoryId = request.getParameter("storeId");
		if (StringUtils.nonEmpty(secondCategoryId)) {
			l.add("secondCategory.id = ?");
			filed.addInt(Integer.parseInt(secondCategoryId));
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

		List<AirBottleInfoReportDetailVo> list = storeInventoryInfoService.getDetailsInventoryInfo(l, filed);

		return list;
	}


	@RequestMapping(value = "getStoreInventoryInfo", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	BaseDto<Map<String, Object>> getStoreInventoryInfo(HttpServletRequest request, Map<String, Object> map) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		Map<String, Object> result = new HashMap<String, Object>();
		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		if (user.getSecondCategory() != null) {
			l.add("secondCategory.id = ?");
			filed.addInt(user.getSecondCategory().getId());
		}

		l.add("airBottleInventoryState.id = ?");
		filed.addInt(AirBottleInventoryStateCode.ALREADY_RECEIVE);

		ToolsBarUtils.getStoreInventoryInfoSearchCondition(request, l, filed);

		List<StoreInventoryInfoVo> list = storeInventoryInfoService.getStoreInventoryInfoVo(l, filed);

		Map<String, Integer> storeInventoryInfoMap = new TreeMap<String, Integer>();

		StringBuffer keySb = new StringBuffer();

		for (StoreInventoryInfoVo storeInventoryInfoVo : list) {

			keySb.setLength(0);
			keySb.append(storeInventoryInfoVo.getSecondCategoryId());
			keySb.append("-");
			keySb.append(storeInventoryInfoVo.getAirBottleTypeId());
			keySb.append("-");
			keySb.append(storeInventoryInfoVo.getAirBottleStateId());

			String key = keySb.toString();

			Integer inventoryNum = storeInventoryInfoVo.getInventoryNum();

			storeInventoryInfoMap.put(key, inventoryNum);
		}

		List<StoreInventoryInfoSummary> storeInventoryInfoSummaryList = new ArrayList<StoreInventoryInfoSummary>();

		List<SecondCategory> secondCategorylist = secondCategoryService.getMyList(user);

		List<AirBottleType> airBottleTypeList = airBottleTypeService.getAllList();

		List<AirBottleState> airBottleStateList = airBottleStateService.getAllList();

		for (SecondCategory secondCategory : secondCategorylist) {

			for (AirBottleType airBottleType : airBottleTypeList) {
				for (AirBottleState airBottleState : airBottleStateList) {

					keySb.setLength(0);
					keySb.append(secondCategory.getId());
					keySb.append("-");
					keySb.append(airBottleType.getId());
					keySb.append("-");
					keySb.append(airBottleState.getId());

					String key = keySb.toString();

					Integer inventoryNum = storeInventoryInfoMap.get(key);
					if (inventoryNum != null) {
						StoreInventoryInfoSummary storeInventoryInfoSummary = new StoreInventoryInfoSummary();
						storeInventoryInfoSummary.setStore_id(secondCategory.getId());
						storeInventoryInfoSummary.setAir_bottle_type_id(airBottleType.getId());
						storeInventoryInfoSummary.setAir_bottle_state_id(airBottleState.getId());

						storeInventoryInfoSummary.setStore_name(secondCategory.getSecond_category_name());
						storeInventoryInfoSummary.setAir_bottle_type_name(airBottleType.getAir_bottle_specifications());

						storeInventoryInfoSummary.setAir_bottle_state_description(airBottleState.getState_description());

						storeInventoryInfoSummary.setInventory_num(inventoryNum.intValue());
						storeInventoryInfoSummaryList.add(storeInventoryInfoSummary);
					}

				
				}
			}
		}

		result.put("rows", storeInventoryInfoSummaryList);

		return BaseDto.getSuccessResult(result);

	}

}
