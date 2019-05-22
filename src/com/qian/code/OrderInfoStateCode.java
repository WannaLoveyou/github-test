package com.qian.code;

/**
 * @author Lu Kongwen
 * @version Create time：2015-12-21 下午3:45:38
 * @Description
 */
public class OrderInfoStateCode {

	public static final int ALREADY_ORDER_BUT_NO_DISPATCHING = 1; // 下单未派工

	public static final int ALREADY_DISPATCHING_BUT_NO_DELIVERY = 2; // 派工未派送

	public static final int ALREADY_DELIVERY_BUT_NO_BACK = 3; // 已派送未回单

	public static final int ALREADY_FINISH = 10; // 历史订单

	public static final int ALREADY_CANCEL = 100; // 已撤销

	public static final int HAS_CANCEL = -1;// 已撤销 微信订单显示
}
