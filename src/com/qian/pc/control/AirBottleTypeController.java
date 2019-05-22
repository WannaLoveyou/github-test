package com.qian.pc.control;

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

import com.qian.entity.AirBottleType;
import com.qian.entity.StoreCustomAirBottlePriceInfo;
import com.qian.service.AirBottleTypeService;
import com.qian.service.StoreCustomAirBottlePriceInfoService;
import com.qian.vo.BaseDto;

@Controller
@RequestMapping("/airbottleType")
public class AirBottleTypeController {
	private static final String LIST = "airbottleType/list";

	@Autowired
	private AirBottleTypeService airBottleTypeService;

	@Autowired
	private StoreCustomAirBottlePriceInfoService storeCustomAirBottlePriceInfoService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {

		return LIST;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody String add(HttpServletRequest request, Map<String, Object> map, AirBottleType airBottleType) {
		airBottleTypeService.add(airBottleType);
		return "ok";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody String edit(HttpServletRequest request, Map<String, Object> map, AirBottleType airBottleType,
			int airBottleTypeId) {
		airBottleTypeService.edit(airBottleType, airBottleTypeId);
		return "ok";
	}

	@RequestMapping(value = "editSpecial", method = RequestMethod.POST)
	public @ResponseBody String editSpecial(HttpServletRequest request, Map<String, Object> map,
			AirBottleType airBottleType, int airBottleTypeId) {
		airBottleTypeService.editSpecial(airBottleType, airBottleTypeId);
		return "ok";
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody String del(HttpServletRequest request, Map<String, Object> map, String ids) {
		String idString[] = ids.split(",");
		airBottleTypeService.delByIds(idString);
		return "ok";
	}

	@RequestMapping(value = "getPageList", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getPageList(HttpServletRequest request, Map<String, Object> map, int page,
			int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long total = airBottleTypeService.getTotalNum();
		List<AirBottleType> list = airBottleTypeService.getPageList(page, rows);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}

	@RequestMapping(value = "getAllList", method = RequestMethod.GET)
	public @ResponseBody List<AirBottleType> getAllList(HttpServletRequest request, Map<String, Object> map) {
		List<AirBottleType> list = airBottleTypeService.getAllList();
		return list;
	}

	@RequestMapping(value = "getAllListForSpecialPrice", method = RequestMethod.GET)
	public @ResponseBody List<AirBottleType> getAllListForSpecialPrice(HttpServletRequest request,
			Map<String, Object> map, int secondCategoryId) {

		List<AirBottleType> airBottleTypes = airBottleTypeService.getAllListForSpecialPrice(secondCategoryId);

		List<StoreCustomAirBottlePriceInfo> storeCustomAirBottlePriceInfos = storeCustomAirBottlePriceInfoService
				.getByListSecondCategoryId(secondCategoryId);

		if (storeCustomAirBottlePriceInfos.size() == 0) {
			return airBottleTypes;
		}

		Map<Integer, StoreCustomAirBottlePriceInfo> storeCustomAirBottlePriceInfoMap = new HashMap<Integer, StoreCustomAirBottlePriceInfo>();

		for (StoreCustomAirBottlePriceInfo storeCustomAirBottlePriceInfo : storeCustomAirBottlePriceInfos) {
			storeCustomAirBottlePriceInfoMap.put(storeCustomAirBottlePriceInfo.getAirBottleType().getId(),
					storeCustomAirBottlePriceInfo);
		}

		for (AirBottleType airBottleType : airBottleTypes) {

			StoreCustomAirBottlePriceInfo storeCustomAirBottlePriceInfo = storeCustomAirBottlePriceInfoMap
					.get(airBottleType.getId());

			if (storeCustomAirBottlePriceInfo == null) {
				continue;
			}

			if (storeCustomAirBottlePriceInfo.getCustom_price() != null) {
				airBottleType.setPrice(storeCustomAirBottlePriceInfo.getCustom_price());
			}

			if (storeCustomAirBottlePriceInfo.getCustom_delivery_fee() != null) {
				airBottleType.setDelivery_fee(storeCustomAirBottlePriceInfo.getCustom_delivery_fee());
			}

		}

		return airBottleTypes;
	}

	@RequestMapping(value = "getNewAllList", method = RequestMethod.GET)
	public @ResponseBody BaseDto<List<AirBottleType>> getNewAllList(HttpServletRequest request,
			Map<String, Object> map) {
		List<AirBottleType> list = airBottleTypeService.getAllList();
		return BaseDto.getSuccessResult(list);
	}
}