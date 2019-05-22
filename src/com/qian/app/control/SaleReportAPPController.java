package com.qian.app.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.app.entity.AppSaleReportInfoSummary;
import com.qian.code.OrderInfoStateCode;
import com.qian.entity.AirBottleType;
import com.qian.entity.SecondCategory;
import com.qian.service.AirBottleTypeService;
import com.qian.service.OrderService;
import com.qian.service.SecondCategoryService;
import com.qian.util.StringUtils;
import com.qian.util.TimeUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.Field;
import com.qian.vo.SaleReportInfoSummary;
import com.qian.vo.SaleReportInfoVo;

/**
 * @author Lu Kongwen
 * @version Create time：2018-1-9 下午3:50:36
 * @Description
 */
@Controller
@RequestMapping("/app/saleReport")
public class SaleReportAPPController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private SecondCategoryService secondCategoryService;

	@Autowired
	private AirBottleTypeService airBottleTypeService;
	
	@RequestMapping(value = "getSaleInfo", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<List<AppSaleReportInfoSummary>> getSaleDetailReportInfo(HttpServletRequest request, Map<String, Object> map,String startTime,String endTime,String storeCode) {

		request.setAttribute("begin_report_time", startTime);
		request.setAttribute("end_report_time", endTime);
		request.setAttribute("storeId", storeCode);
		
		List<SaleReportInfoSummary> saleReportInfoSummaryList = getSaleReportInfoDetailSummaryList(request, map);

		List<AppSaleReportInfoSummary> appSaleReportInfoSummaryList = new ArrayList<AppSaleReportInfoSummary>();
		
		for(SaleReportInfoSummary saleReportInfoSummary:saleReportInfoSummaryList){
			
			AppSaleReportInfoSummary appSaleReportInfoSummary = new AppSaleReportInfoSummary(saleReportInfoSummary);
			appSaleReportInfoSummaryList.add(appSaleReportInfoSummary);
		}
		
		return BaseDto.getSuccessResult(appSaleReportInfoSummaryList);
	}
	
	private List<SaleReportInfoSummary> getSaleReportInfoDetailSummaryList(HttpServletRequest request, Map<String, Object> map) {

		List<String> orderInfoSearchConditionList = new ArrayList<String>();
		Field orderInfoSearchConditionFiled = new Field();

		// 已完成的订单
		orderInfoSearchConditionList.add("state.id = ?");
		orderInfoSearchConditionFiled.addInt(OrderInfoStateCode.ALREADY_FINISH);

		String begin_report_time = (String) request.getAttribute("begin_report_time");
		if (StringUtils.nonEmpty(begin_report_time)) {
			orderInfoSearchConditionList.add("report_time >= ?");
			orderInfoSearchConditionFiled.addDate(begin_report_time);
		}

		String end_report_time = (String) request.getAttribute("end_report_time");
		if (StringUtils.nonEmpty(end_report_time)) {
			orderInfoSearchConditionList.add("report_time < ?");
			orderInfoSearchConditionFiled.addDate(TimeUtils.getNextDayString(end_report_time));
		}
		
		String storeId = (String) request.getAttribute("storeId");
		if (StringUtils.nonEmpty(storeId)) {
			orderInfoSearchConditionList.add("secondCategory.id = ?");
			orderInfoSearchConditionFiled.addInt(Integer.valueOf(storeId));
		}
		
		ToolsBarUtils.getOrderInfoSearchCondition(request, orderInfoSearchConditionList, orderInfoSearchConditionFiled);

		List<SaleReportInfoVo> saleDetailReportInfoVoList = orderService.getAllOrderInfo(orderInfoSearchConditionList, orderInfoSearchConditionFiled);

		List<SaleReportInfoSummary> saleReportInfoSummaryList = new ArrayList<SaleReportInfoSummary>();

		Map<String, SaleReportInfoSummary> saleReportInfoSummaryMap = new TreeMap<String, SaleReportInfoSummary>();

		Set<String> OrderTimeSet = new TreeSet<String>();

		StringBuffer keySb = new StringBuffer();

		for (SaleReportInfoVo saleReportInfoVo : saleDetailReportInfoVoList) {

			keySb.setLength(0);
			keySb.append(saleReportInfoVo.getSecondCategoryId());
			keySb.append("_");
			keySb.append(saleReportInfoVo.getAirBottleTypeId());

			String OrderTimeTemp = TimeUtils.getyyyyMMddStringByDate(saleReportInfoVo.getOrder_time());
			keySb.append("_");
			keySb.append(OrderTimeTemp);

			OrderTimeSet.add(OrderTimeTemp);

			String key = keySb.toString();

			SaleReportInfoSummary saleReportInfoSummary = saleReportInfoSummaryMap.get(key);

			if (saleReportInfoSummary == null) {

				saleReportInfoSummary = new SaleReportInfoSummary();
				saleReportInfoSummaryMap.put(key, saleReportInfoSummary);
			}

			saleReportInfoSummary.setOrderNumber(saleReportInfoSummary.getOrderNumber() + 1);
			saleReportInfoSummary.setOrderBottleNumber(saleReportInfoSummary.getOrderBottleNumber() + saleReportInfoVo.getOrderNumber());
			saleReportInfoSummary.setTotal_amount(saleReportInfoSummary.getTotal_amount() + saleReportInfoVo.getTotal_amount());

		}

		List<SecondCategory> secondCategorys = secondCategoryService.getAllList();

		List<AirBottleType> airBottleTypes = airBottleTypeService.getAllList();

		for (SecondCategory secondCategory : secondCategorys) {
			for (AirBottleType airBottleType : airBottleTypes) {
				for (String orderTime : OrderTimeSet) {
					keySb.setLength(0);
					keySb.append(secondCategory.getId());
					keySb.append("_");
					keySb.append(airBottleType.getId());
					keySb.append("_");
					keySb.append(orderTime);

					String key = keySb.toString();

					SaleReportInfoSummary saleReportInfoSummary = saleReportInfoSummaryMap.get(key);

					if (saleReportInfoSummary != null) {

						saleReportInfoSummary.setOrder_date(orderTime);

						saleReportInfoSummary.setSecond_category_id(secondCategory.getId());
						saleReportInfoSummary.setSecond_category_name(secondCategory.getSecond_category_name());
						saleReportInfoSummary.setX_coordinate(secondCategory.getX_coordinate());
						saleReportInfoSummary.setY_coordinate(secondCategory.getY_coordinate());

						saleReportInfoSummary.setAir_bottle_specifications(airBottleType.getAir_bottle_specifications());

						saleReportInfoSummaryList.add(saleReportInfoSummary);
					}
				}
			}
		}

		return saleReportInfoSummaryList;
	}
}
