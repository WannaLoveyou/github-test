package com.qian.vo;


/**
 * @author Lu Kongwen
 * @version Create time：2015-12-25 上午10:16:27
 * @Description
 */
public enum CommonCode {

	OK(200, "操作成功"),

	NOT_EXIST_ACCOUNT(301, "账号不存在"),

	NOT_CORRECT_PASSWORD(302, "账号密码不正确"),
	
	NOT_STORE_MAN(303,"非门店操作人员"),
	
	NOT_HAS_DEVICE_CODE(304,"设备码为空"),
	
	HAS_ALREAY_LOGIN(305,"账号在其他设备登录"),

	STORE_MAN_CAN_NOT_USE(306,"门店人员不能操作此功能"),
	
	NOT_WAREHOUSE_MAN(307,"非仓库操作人员"),
	
	DATA_IS_NULL(309,"数据为空"),

	NOT_EXIST_AIR_BOTTLE(401, "非法气瓶编码"),

	ALREAY_EXIST_AIR_BOTTLE(402, "气瓶编码已存在"),
	
	NOT_FIND_AIR_BOTTLE(403, "找不到对应钢瓶信息"),
	
	AIR_BOTTLE_HAS_WAITING_FOR_CHECK(404,"该气瓶在待审核状态"),
	
	AIR_BOTTLE_HAS_OVERDUE(405,"该气瓶已过期"),
	
	ALREAY_EXIST_AIR_BOTTLE_SEAL_CODE(406, "气瓶钢印码已存在"),
	
	AIR_BOTTLE_HAS_INSPECTION(407,"该气瓶已在待检列表"),

	AIR_BOTTLE_HAS_NOT_SEND_INSPECTION(411, "该气瓶已送检出库"),
	
	INCOMPLETE_INFORMATION(501, "信息不完整,请重新操作"),

	NOT_INVENTORY_STATUS(601, "该瓶不在库存状态"),

	NOT_ENOUGH_INVENTORY(602, "库存不足"),

	NOT_IN_WAREHOUSE(603, "该瓶不在仓库库存"),
	
	/**没有找到仓库*/
	WAREHOUSE_NULL(604, "没有找到仓库"),

	ORDER_NOT_COMPLETED(701, "订单未完成"),

	ORDER_CAN_NOT_CANCEL(702, "订单不能取消"),

	ORDER_CAN_NOT_FINISH(703, "订单不能完成"),

	ORDER_BOTTLE_TYPE_NOT_MATCH(704, "气瓶类型不匹配"),

	ORDER_BOTTLE_HAS_FINISH(705, "该瓶的订单已经完成不能退回"),
	
	ORDER_NOT_EXIST(706,"订单不存在"),
	
	ORDER_RETURN_MONEY_FAIL(707,"订单退款失败"),
	
	ORDER_HAS_EMPTY_BOTTLE_NOT_FINISH(708, "该订单已回瓶不能退回"),
	
	ORDER_TIME_NOT_CONFORM(709, "预约时间不能小于受理时间"),
	
	ORDER_NOT_DISPATCH(710, "订单不能派工(该订单为微信支付且未付款的订单)"),
	
	ORDER_CAN_HAS_SAME_HEAVY_EMPTY_BOTTLE(711, "订单的重瓶与空瓶不能重复"),
	
	ORDER_HAS_DISPATCH(712, "订单已派工，请刷新"),
	
	ORDER_IS_NOT_PARTS_ORDER(713, "订单非纯零件订单"),
	
	ORDER_HAS_FINISH(714, "订单已完成"),
	
	ORDER_HAS_INVOICE(715, "订单已开票"),
	
	ORDER_OUT_OF_BOTTLE_NUM(716, "订单超出气瓶数量"),
	
	ORDER_AND_CLIENT_NOT_MATCH(717, "订单与瓶不匹配"),
	
	ORDER_CAN_NOT_PRINT(718, "订单不能打印"),
	
	ORDER_CAN_OVER_LIMITS(719, "订单打印超出次数限制"),
	
	ORDERCAN_NOT_PRINT_BY_WECHAT_NOT_PAY(720, "订单为微信未付订单不能打印"),

	ORDER_HAS_NOT_CANCEL_FOR_FIVE_MIN(721, "微信支付订单5分钟内不能撤销"),
	
	ORDER_CAN_NOT_CANCEL_BY_WECHAT_HAS_PAY(722, "不能撤销微信已付订单"),
	
	POSITION_INFO_EMPTY(723, "没有此订单的钢瓶轨迹"),
	
	ORDER_NO_DISPATCHING_CAN_NOT_PRINT(724, "订单未派工不能打印"),
	
	USER_NOT_MATCH(801, "用户信息不匹配"),

	USER_NOT_EXIST(802, "用户不存在"),

	USER_HAS_NOT_CARD_CODE(803, "用户没有办理IC卡"),

	USER_QRCODE_CARD_CODE_OVERDUE(804, "用户二维码已过期"),

	USER_HAS_NOT_BOTTLE(805, "用户没有该气瓶"),
	
	USER_HAS_NOT_EDIT_CLIENT_PERMISSION(806,"没有修改客户信息的权限"),
	
	USER_HAS_EDIT_CARD_CODE(807,"该客户只允许修改卡号"),

	CARD_INFO_EMPTY(901,"用户卡没有信息"),
	
	CARD_MONEY_NOT_ENOUGH(902,"卡余额不足"),
	
	CARD_INFO_HAS_NOT_REAY(903,"用户卡非本门店信息"),
	
	CARD_INFO_HAS_EXIST(904,"该卡已绑定客户"),
	
	CAN_NOT_FIND_ANONYMOUS_CLIENT(905,"找不到匿名客户信息"),
	
	WAREHOUSE_ORDER_NOT_EXIST(1001,"仓库订单不能打印"),
	
	WAREHOUSE_BUSINESS_ORDER_NOT_DISPATCH(1002,"该订单不能派工"),
	
	WAREHOUSE_ORDER_CAN_NOT_CHECK(1003,"该订单不在待审核状态"),

	CLIENT_AIR_BOTTLE_TYPE_FEE_HAS_EXIST(1101,"费用信息已存在"),
	
	THE_RECORD_HAS_EXIST(1201,"该记录已存在"),
	
	THE_RECORD_CAN_NOT_HANDLE(1301,"该记录无法处理"),
	
	STORE_HAS_NOT_ENOUGH_MONEY(1401,"门店余额不足"),
	
	STORE_REFUND_HAS_CHECK(1501,"该退款记录已审核通过不能操作"),
	
	BANK_SYSTEM_ERROR(1601,"银行系统故障，请联系管理员"),
	
	SESSION_IS_NULL(1701,"session为空，请重新登录"),
	OPPENID_IS_NULL(1702,"oppenid为空，请重新登录"),

	HAS_NOT_MATCH_ACTIVE_RULE(9000,"不符合活动规则"),
	
	NEED_CONFIRM(9996,"请先确认"),
	
	NEED_REFRESH(9997,"请先刷新"),
	
	THE_SAME_OPERATION(9998,"重复操作"),
	
	FUNCTION_NOT_OPEN(9999,"功能尚未开启"),
	
	SYSTEM_EXCEPTION(10000, "操作不合法");

	private int code;
	private String msg;

	private CommonCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static String getMsg(int code) {
		for (CommonCode mc : CommonCode.values()) {
			if (mc.getCode() == code) {
				return mc.getMsg();
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
