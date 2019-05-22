package com.qian.code;

/**
 * @author Lu Kongwen
 * @version Create time：2015-12-21 下午2:59:51
 * @Description 仓库订单状态编码
 */
public class WareHouseOrderInfoStateCode {

	public static final int WAITING_FOR_CHECK = 100; // 待审核

	public static final int PASS_CHECK = 101; // 审核通过

	public static final int NO_PASS_CHECK = 200; // 审核不通过
	
	public static final int ALREADY_CONFIRM = 300; // 订单已确认完成
}
