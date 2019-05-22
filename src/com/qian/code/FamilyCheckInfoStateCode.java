package com.qian.code;

/**
 * @author Lu Kongwen
 * @version Create time：2016-12-14 下午3:11:25
 * @Description
 */
public class FamilyCheckInfoStateCode {

	public static final int T0_BE_ACCETED = 100; // 待受理
	
	public static final int HAS_CHECK = 101; // 已检查

	public static final int HAS_CHECK_HAS_PROPLEM = 200; // 已检查有问题

	public static final int HAS_CHECK_NO_PROPLEM = 300; // 已检查没问题

	public static final int REJECT_CHECK = 400; // 拒绝检查

	public static final int HAS_CANCEL = 500; // 已撤销

}
