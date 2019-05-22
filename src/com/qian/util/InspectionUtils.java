package com.qian.util;

import java.util.ResourceBundle;


/**
 * @author Lu Kongwen
 * @version Create time：2018-5-24 下午3:57:18
 * @Description
 */
public class InspectionUtils {

	static ResourceBundle resource = ResourceBundle.getBundle("properties/inspection");

	public final static String BASE_URL =  resource.getString("inspection.url");

	public final static String KEY = PathUtils.getProperty("inspection.key");
	
	public final static String COMPANY_CODE = PathUtils.getProperty("clouddata.FILLINGCODE");
	
	//发送订单
	public final static String SEND_ORDER = BASE_URL + "/checkOrderInvoke/sendCheckOrder";
	
	//查询订单状态
	public final static String QUERY_ORDER_INFO = BASE_URL + "/checkOrderInvoke/getCheckOrderByNum";
	
	//查询订单明细状态
	public final static String QUERY_ORDER_DETAILS_INFO = BASE_URL + "/checkOrderInvoke/getDetailsByGasNum";

	//查询订单明细检验状态
	public final static String QUERY_ORDER_DETAILS_INSPECTION_INFO = BASE_URL + "/checkOrderInvoke/getResultByGasNum";

	//查询检测项目和检测结果
	public final static String QUERY_ITEM_AND_RESULT = BASE_URL + "/checkOrderInvoke/getCheckItemAndResult";

	//确认检测单
	public final static String CONFIRM_ORDER = BASE_URL + "/checkOrderInvoke/confirmCheckOrder";

}
