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

import com.qian.code.DeliveryTypeCode;
import com.qian.code.OrderInfoStateCode;
import com.qian.entity.AirBottleType;
import com.qian.entity.User;
import com.qian.service.AirBottleTypeService;
import com.qian.service.OrderService;
import com.qian.service.UserService;
import com.qian.util.ExportHeadsUtils;
import com.qian.util.ExportUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.DeliveryManDeliveryFeeInfoVo;
import com.qian.vo.DeliveryManDeliveryFeeReportInfoSummary;
import com.qian.vo.ExportOrderInfoReportHeads;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-18 上午10:32:37
 * @Description
 */
@Controller
@RequestMapping("/deliveryManDeliveryFeeReport")
public class DeliveryManDeliveryFeeReportController {

	@Autowired
	private AirBottleTypeService airBottleTypeService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	private static final String DELIVERY_MAN_DELIVERY_FEE_REPORT_LIST = "deliveryManDeliveryFeeReport/delivery_man_delivery_fee_report_list";

	@RequiresUser
	@RequestMapping(value = "deliveryManDeliveryFeeReportList", method = RequestMethod.GET)
	public String deliveryManDeliveryFeeReportList(HttpServletRequest request, Map<String, Object> map) {

		return DELIVERY_MAN_DELIVERY_FEE_REPORT_LIST;
	}

	@RequestMapping(value = "getDeliveryManDeliveryFeeReportInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getDeliveryManDeliveryFeeReportInfo(HttpServletRequest request, Map<String, Object> map) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<DeliveryManDeliveryFeeReportInfoSummary> deliveryManDeliveryFeeReportInfoSummaryList = getDeliveryManDeliveryFeeReportInfoSummaryList(request, map);

		result.put("rows", deliveryManDeliveryFeeReportInfoSummaryList);

		double delivery_fee = 0;
		double floor_subsidies_money = 0;
		int order_num = 0;
		int order_bottle_num = 0;

		DeliveryManDeliveryFeeReportInfoSummary deliveryManDeliveryFeeReportInfoSummaryTotal = new DeliveryManDeliveryFeeReportInfoSummary();

		for (DeliveryManDeliveryFeeReportInfoSummary deliveryManDeliveryFeeReportInfoSummary : deliveryManDeliveryFeeReportInfoSummaryList) {

			if (!deliveryManDeliveryFeeReportInfoSummary.getAir_bottle_specifications().equals("总计")) {
				continue;
			}

			delivery_fee += deliveryManDeliveryFeeReportInfoSummary.getDelivery_fee();
			floor_subsidies_money += deliveryManDeliveryFeeReportInfoSummary.getFloor_subsidies_money();
			order_num += deliveryManDeliveryFeeReportInfoSummary.getOrder_num();
			order_bottle_num += deliveryManDeliveryFeeReportInfoSummary.getOrder_bottle_num();
		}

		deliveryManDeliveryFeeReportInfoSummaryTotal.setDelivery_fee(delivery_fee);
		deliveryManDeliveryFeeReportInfoSummaryTotal.setFloor_subsidies_money(floor_subsidies_money);
		deliveryManDeliveryFeeReportInfoSummaryTotal.setOrder_num(order_num);
		deliveryManDeliveryFeeReportInfoSummaryTotal.setOrder_bottle_num(order_bottle_num);

		result.put("footer", deliveryManDeliveryFeeReportInfoSummaryTotal);

		return result;

	}

	@RequestMapping(value = "exportDeliveryManDeliveryFeeReportInfo", method = RequestMethod.POST)
	public void exportDeliveryManDeliveryFeeReportInfo(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {

		List<ExportOrderInfoReportHeads> heads = ExportHeadsUtils.getExportOrderInfoReportHeads(request);

		List<DeliveryManDeliveryFeeReportInfoSummary> deliveryManDeliveryFeeReportInfoSummaryList = getDeliveryManDeliveryFeeReportInfoSummaryList(request, map);

		ExportUtils
				.exportExcel(response, heads, deliveryManDeliveryFeeReportInfoSummaryList, "送气工送工费汇总报表.xls", "template-DeliveryManDeliveryFeeReportInfo.xls");

	}

	private List<DeliveryManDeliveryFeeReportInfoSummary> getDeliveryManDeliveryFeeReportInfoSummaryList(HttpServletRequest request, Map<String, Object> map) {

		List<String> orderInfoSearchConditionList = new ArrayList<String>();
		Field orderInfoSearchConditionFiled = new Field();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		if (user.getSecondCategory() != null) {

			orderInfoSearchConditionList.add("secondCategory.id = ?");
			orderInfoSearchConditionFiled.addInt(user.getSecondCategory().getId()); // 取对应门店

		}

		// 已派送的订单
		orderInfoSearchConditionList.add("state.id = ?");
		orderInfoSearchConditionFiled.addInt(OrderInfoStateCode.ALREADY_FINISH);

		// 配送方式
		orderInfoSearchConditionList.add("deliveryType.id = ?");
		orderInfoSearchConditionFiled.addInt(DeliveryTypeCode.ORDER_DELIVERY);

		ToolsBarUtils.getOrderInfoSearchCondition(request, orderInfoSearchConditionList, orderInfoSearchConditionFiled);

		List<DeliveryManDeliveryFeeInfoVo> deliveryManDeliveryFeeInfoVoList = orderService.getAllDeliveryManDeliveryFeeInfo(orderInfoSearchConditionList,
				orderInfoSearchConditionFiled);

		List<DeliveryManDeliveryFeeReportInfoSummary> deliveryManDeliveryFeeReportInfoSummaryList = new ArrayList<DeliveryManDeliveryFeeReportInfoSummary>();

		Map<String, DeliveryManDeliveryFeeReportInfoSummary> deliveryManDeliveryFeeReportInfoSummaryMap = new TreeMap<String, DeliveryManDeliveryFeeReportInfoSummary>();

		Set<Integer> DeliveryManIdSet = new HashSet<Integer>();

		Integer deliveryManIdTemp = 0;

		StringBuffer keySb = new StringBuffer();

		for (DeliveryManDeliveryFeeInfoVo deliveryManDeliveryFeeInfoVo : deliveryManDeliveryFeeInfoVoList) {

			deliveryManIdTemp = deliveryManDeliveryFeeInfoVo.getDeliveryManId() == null ? 0 : deliveryManDeliveryFeeInfoVo.getDeliveryManId();

			keySb.setLength(0);
			keySb.append(deliveryManIdTemp);
			keySb.append("_");
			keySb.append(deliveryManDeliveryFeeInfoVo.getAirBottleTypeId());

			DeliveryManIdSet.add(deliveryManIdTemp);

			String key = keySb.toString();

			DeliveryManDeliveryFeeReportInfoSummary deliveryManDeliveryFeeReportInfoSummary = deliveryManDeliveryFeeReportInfoSummaryMap.get(key);

			if (deliveryManDeliveryFeeReportInfoSummary == null) {

				deliveryManDeliveryFeeReportInfoSummary = new DeliveryManDeliveryFeeReportInfoSummary();
				deliveryManDeliveryFeeReportInfoSummaryMap.put(key, deliveryManDeliveryFeeReportInfoSummary);
			}

			deliveryManDeliveryFeeReportInfoSummary.setDelivery_fee(deliveryManDeliveryFeeReportInfoSummary.getDelivery_fee()
					+ deliveryManDeliveryFeeInfoVo.getDelivery_fee());
			deliveryManDeliveryFeeReportInfoSummary.setFloor_subsidies_money(deliveryManDeliveryFeeReportInfoSummary.getFloor_subsidies_money()
					+ deliveryManDeliveryFeeInfoVo.getFloor_subsidies_money());
			deliveryManDeliveryFeeReportInfoSummary.setOrder_num(deliveryManDeliveryFeeReportInfoSummary.getOrder_num() + 1);
			deliveryManDeliveryFeeReportInfoSummary.setOrder_bottle_num(deliveryManDeliveryFeeReportInfoSummary.getOrder_bottle_num()
					+ deliveryManDeliveryFeeInfoVo.getOrder_number());
		}

		List<AirBottleType> airBottleTypes = airBottleTypeService.getAllList();

		double deliveryFeeForSecondCategory = 0;
		double floorSubsidiesMoneyForSecondCategory = 0;
		int orderNumForSecondCategory = 0;
		int orderBottleNumForSecondCategory = 0;
		boolean flag = false;

		User deliveryMan = null;

		for (Integer deliveryManId : DeliveryManIdSet) {

			if (0 == deliveryManId) {
				deliveryMan = new User();
				deliveryMan.setFull_name("");
			} else {
				deliveryMan = userService.findById(deliveryManId);
			}

			DeliveryManDeliveryFeeReportInfoSummary deliveryManDeliveryFeeReportInfoSummaryForSecondCategory = new DeliveryManDeliveryFeeReportInfoSummary();

			deliveryFeeForSecondCategory = 0;
			floorSubsidiesMoneyForSecondCategory = 0;
			orderNumForSecondCategory = 0;
			orderBottleNumForSecondCategory = 0;
			flag = false;

			for (AirBottleType airBottleType : airBottleTypes) {

				keySb.setLength(0);
				keySb.append(deliveryManId);
				keySb.append("_");
				keySb.append(airBottleType.getId());

				String key = keySb.toString();

				DeliveryManDeliveryFeeReportInfoSummary deliveryManDeliveryFeeReportInfoSummary = deliveryManDeliveryFeeReportInfoSummaryMap.get(key);

				if (deliveryManDeliveryFeeReportInfoSummary != null) {

					deliveryManDeliveryFeeReportInfoSummary.setAir_bottle_specifications(airBottleType.getAir_bottle_specifications());

					deliveryManDeliveryFeeReportInfoSummary.setDelivery_man_full_name(deliveryMan.getFull_name());

					deliveryManDeliveryFeeReportInfoSummaryList.add(deliveryManDeliveryFeeReportInfoSummary);

					flag = true;

					deliveryFeeForSecondCategory += deliveryManDeliveryFeeReportInfoSummary.getDelivery_fee();
					floorSubsidiesMoneyForSecondCategory += deliveryManDeliveryFeeReportInfoSummary.getFloor_subsidies_money();
					orderNumForSecondCategory += deliveryManDeliveryFeeReportInfoSummary.getOrder_num();
					orderBottleNumForSecondCategory += deliveryManDeliveryFeeReportInfoSummary.getOrder_bottle_num();
				}
			}

			if (flag) {
				deliveryManDeliveryFeeReportInfoSummaryForSecondCategory.setDelivery_man_full_name(deliveryMan.getFull_name());
				deliveryManDeliveryFeeReportInfoSummaryForSecondCategory.setAir_bottle_specifications("总计");
				deliveryManDeliveryFeeReportInfoSummaryForSecondCategory.setOrder_num(orderNumForSecondCategory);
				deliveryManDeliveryFeeReportInfoSummaryForSecondCategory.setOrder_bottle_num(orderBottleNumForSecondCategory);
				deliveryManDeliveryFeeReportInfoSummaryForSecondCategory.setDelivery_fee(deliveryFeeForSecondCategory);
				deliveryManDeliveryFeeReportInfoSummaryForSecondCategory.setFloor_subsidies_money(floorSubsidiesMoneyForSecondCategory);

				deliveryManDeliveryFeeReportInfoSummaryList.add(deliveryManDeliveryFeeReportInfoSummaryForSecondCategory);
			}

		}

		return deliveryManDeliveryFeeReportInfoSummaryList;
	}
}
