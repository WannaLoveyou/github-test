package com.qian.pc.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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

import com.qian.code.OrderInfoStateCode;
import com.qian.entity.AirBottleType;
import com.qian.entity.PartType;
import com.qian.entity.SecondCategory;
import com.qian.entity.User;
import com.qian.service.AirBottleTypeService;
import com.qian.service.PartFeeInfoService;
import com.qian.service.PartTypeService;
import com.qian.service.SecondCategoryService;
import com.qian.util.ExportHeadsUtils;
import com.qian.util.ExportUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.ExportOrderInfoReportHeads;
import com.qian.vo.Field;
import com.qian.vo.PartSaleReportInfoSummary;
import com.qian.vo.PartSaleReportInfoVo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-11-21 下午2:34:24
 * @Description
 */
@Controller
@RequestMapping("/partSaleReport")
public class PartSaleReportController {

	@Autowired
	private PartFeeInfoService partFeeInfoService;

	@Autowired
	private SecondCategoryService secondCategoryService;

	@Autowired
	private AirBottleTypeService airBottleTypeService;

	@Autowired
	private PartTypeService partTypeService;

	private static final String STORE_PART_SALE_REPORT_LIST = "partSaleReport/store_part_sale_report_list";

	@RequiresUser
	@RequestMapping(value = "storePartSaleReportList", method = RequestMethod.GET)
	public String storePartSaleReportList(HttpServletRequest request, Map<String, Object> map) {

		return STORE_PART_SALE_REPORT_LIST;
	}

	@RequestMapping(value = "getPartSaleReportInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getPartSaleReportInfo(HttpServletRequest request, Map<String, Object> map) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<PartSaleReportInfoSummary> partSaleReportInfoSummaryList = getPartSaleReportInfoSummaryList(request, map);

		result.put("rows", partSaleReportInfoSummaryList);

		int number = 0;
		int total_amount = 0;

		for (PartSaleReportInfoSummary partSaleReportInfoSummary : partSaleReportInfoSummaryList) {
			number += partSaleReportInfoSummary.getNumber();
			total_amount += partSaleReportInfoSummary.getTotal_amount();
		}

		PartSaleReportInfoSummary partSaleReportInfoSummaryTotal = new PartSaleReportInfoSummary();
		partSaleReportInfoSummaryTotal.setNumber(number);
		partSaleReportInfoSummaryTotal.setTotal_amount(total_amount);

		result.put("footer", partSaleReportInfoSummaryTotal);

		return result;

	}
	
	@RequestMapping(value = "exportPartSaleReportInfo", method = RequestMethod.POST)
	public void exportPartSaleReportInfo(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {

		List<ExportOrderInfoReportHeads> heads = ExportHeadsUtils.getExportOrderInfoReportHeads(request);

		List<PartSaleReportInfoSummary> partSaleReportInfoSummaryList = getPartSaleReportInfoSummaryList(request, map);

		ExportUtils.exportExcel(response, heads, partSaleReportInfoSummaryList, "零件销售统计报表.xls", "template-PartSaleReportInfo.xls");

	}

	private List<PartSaleReportInfoSummary> getPartSaleReportInfoSummaryList(HttpServletRequest request, Map<String, Object> map) {

		List<String> orderInfoSearchConditionList = new ArrayList<String>();
		Field orderInfoSearchConditionFiled = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		if (user.getSecondCategory() != null) {

			orderInfoSearchConditionList.add("orderInfo.secondCategory.id = ?");
			orderInfoSearchConditionFiled.addInt(user.getSecondCategory().getId()); // 取对应门店

		}

		// 已派送的订单
		orderInfoSearchConditionList.add("orderInfo.state.id = ?");
		orderInfoSearchConditionFiled.addInt(OrderInfoStateCode.ALREADY_FINISH);

		ToolsBarUtils.getPartFeeInfoSearchCondition(request, orderInfoSearchConditionList, orderInfoSearchConditionFiled);

		List<PartSaleReportInfoVo> partSaleReportInfoVoList = partFeeInfoService.getAllOrderInfo(orderInfoSearchConditionList, orderInfoSearchConditionFiled);

		List<PartSaleReportInfoSummary> partSaleReportInfoSummaryList = new ArrayList<PartSaleReportInfoSummary>();

		Map<String, PartSaleReportInfoSummary> partSaleReportInfoSummaryMap = new TreeMap<String, PartSaleReportInfoSummary>();

		Set<Double> priceSet = new HashSet<Double>();

		StringBuffer keySb = new StringBuffer();

		for (PartSaleReportInfoVo partSaleReportInfoVo : partSaleReportInfoVoList) {

			keySb.setLength(0);
			keySb.append(partSaleReportInfoVo.getSecondCategoryId());
			keySb.append("_");
			keySb.append(partSaleReportInfoVo.getAirBottleTypeId());
			keySb.append("_");
			keySb.append(partSaleReportInfoVo.getPartTypeId());
			keySb.append("_");
			keySb.append(partSaleReportInfoVo.getPrice());

			priceSet.add(partSaleReportInfoVo.getPrice());

			String key = keySb.toString();

			PartSaleReportInfoSummary partSaleReportInfoSummary = partSaleReportInfoSummaryMap.get(key);

			if (partSaleReportInfoSummary == null) {

				partSaleReportInfoSummary = new PartSaleReportInfoSummary();
				partSaleReportInfoSummary.setPrice(partSaleReportInfoVo.getPrice());
				partSaleReportInfoSummaryMap.put(key, partSaleReportInfoSummary);
			}

			partSaleReportInfoSummary.setNumber(partSaleReportInfoSummary.getNumber() + partSaleReportInfoVo.getNumber());
			partSaleReportInfoSummary.setTotal_amount(partSaleReportInfoSummary.getTotal_amount() + partSaleReportInfoVo.getTotal_amount());
		}

		List<SecondCategory> secondCategorys = secondCategoryService.getAllList();

		List<AirBottleType> airBottleTypes = airBottleTypeService.getAllList();

		List<PartType> partTypes = partTypeService.getAllList();

		for (Double price : priceSet) {
			for (SecondCategory secondCategory : secondCategorys) {
				for (AirBottleType airBottleType : airBottleTypes) {
					for (PartType partType : partTypes) {

						keySb.setLength(0);
						keySb.append(secondCategory.getId());
						keySb.append("_");
						keySb.append(airBottleType.getId());
						keySb.append("_");
						keySb.append(partType.getId());
						keySb.append("_");
						keySb.append(price);

						String key = keySb.toString();

						PartSaleReportInfoSummary partSaleReportInfoSummary = partSaleReportInfoSummaryMap.get(key);

						if (partSaleReportInfoSummary != null) {

							partSaleReportInfoSummary.setSecond_category_name(secondCategory.getSecond_category_name());

							partSaleReportInfoSummary.setAir_bottle_specifications(airBottleType.getAir_bottle_specifications());

							partSaleReportInfoSummary.setPart_type_name(partType.getPart_type_name());

							partSaleReportInfoSummaryList.add(partSaleReportInfoSummary);
						}
					}
				}
			}
		}

		return partSaleReportInfoSummaryList;
	}
}
