package com.qian.code;

/**
 * @author Lu Kongwen
 * @version Create time：2017-11-27 下午7:06:48
 * @Description
 */
public class StoreMoneyChangeRecordTypeCode {

	public static final int BACK_SAVE = 1; // 银行存入

	public static final int SYSTEM_BORROW = 2; // 向系统借款
	
	public static final int SYSTEM_REPAY = 3; // 向系统还款
	
	public static final int REFUND = 4; //退款申請
	
	public static final int WAREHOUST_ORDER_ADD_PAY = 5; //仓库订单扣款
	
	public static final int WAREHOUST_ORDER_EDIT_PAY = 6; //仓库订单更改差额调整
	
	public static final int SYSTEM_MANUAL_ADD = 7; // 后台手动增加余额
	
	public static final int SYSTEM_MANUAL_REMOVE = 8; // 后台手动扣除余额
	
	public static final int WECHAT_PAY = 9;// 微信支付订单下单
	
	public static final int WECHAT_REFUND = 10;//微信支付订单撤单


}
