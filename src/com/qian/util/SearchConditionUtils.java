package com.qian.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qian.code.AirBottleInventoryStateCode;
import com.qian.code.OrderInfoStateCode;
import com.qian.entity.User;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2016-7-18 上午11:31:34
 * @Description
 */
public class SearchConditionUtils {

	public static void getMyNoConfirmedReceiveAirBottlesByDeliveryMan(List<String> l, Field filed, HttpServletRequest request, User user) {

		l.add("delivery_man.id = ?");
		filed.addInt(user.getId());

		l.add("airBottleInventoryState.id = ?");
		filed.addInt(AirBottleInventoryStateCode.NOT_CONFIRMED_RECEIVE);

		ToolsBarUtils.getDeliveryManAirBottleInfoSearchCondition(request, l, filed);

	}

	public static void getMyNoConfirmedSendAirBottlesByDeliveryMan(List<String> l, Field filed, HttpServletRequest request, User user) {

		l.add("delivery_man.id = ?");
		filed.addInt(user.getId());

		l.add("airBottleInventoryState.id = ?");
		filed.addInt(AirBottleInventoryStateCode.NOT_CONFIRMED_SEND);

		ToolsBarUtils.getDeliveryManAirBottleInfoSearchCondition(request, l, filed);
	}

	public static void getMyDeliveryReceiptList(List<String> l, Field filed, User user) {

		l.add("state.id = ?");
		filed.addInt(OrderInfoStateCode.ALREADY_DELIVERY_BUT_NO_BACK); // 已派送未回单

		l.add("delivery_man.id = ?");
		filed.addInt(user.getId());
	}
	
	public static void getMyNoDeliveryListByDeliveryMan(List<String> l, Field filed, User user) {
		
		l.add("state.id = ?");
		filed.addInt(OrderInfoStateCode.ALREADY_DISPATCHING_BUT_NO_DELIVERY); // 已派工未派送

		l.add("delivery_man.id = ?");
		filed.addInt(user.getId());
	}
}
