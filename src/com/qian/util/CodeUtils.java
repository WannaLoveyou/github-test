package com.qian.util;

import java.text.DecimalFormat;
import java.util.Random;

import com.qian.entity.ClientInfo;
import com.qian.entity.ComplaintInfo;
import com.qian.entity.FamilyCheckInfo;
import com.qian.entity.OrderInfo;
import com.qian.entity.RepairInfo;
import com.qian.entity.VisitInfo;

/**
 * @author Lu Kongwen
 * @version Create time：2016-1-20 下午3:10:27
 * @Description
 */
public class CodeUtils {

	public static String ORDER_STR_FORMAT = "000000";

	// 订单编号
	public static String setOrderCode(OrderInfo orderInfo) {

		int randomNum = new Random().nextInt(10000) + 1;

		DecimalFormat df = new DecimalFormat(ORDER_STR_FORMAT);

		StringBuffer code = new StringBuffer(String.valueOf(orderInfo.getId()));

		code.append(df.format(randomNum));

		return code.toString();
	}

	// 维修受理编号
	public static String setRepairCode(RepairInfo repairInfo) {

		String code = String.valueOf(repairInfo.getId());

		return code;
	}

	// 投诉受理编号
	public static String setComplaintCode(ComplaintInfo complaintInfo) {

		String code = String.valueOf(complaintInfo.getId());

		return code;
	}
	
	// 访问编号
	public static String setVisitCode(VisitInfo visitInfo) {
		
		String code = String.valueOf(visitInfo.getId());
		
		return code;
	}

	// 入户检查编号
	public static String setFamilyCheckCode(FamilyCheckInfo familyCheckInfo) {

		String code = String.valueOf(familyCheckInfo.getId());

		return code;
	}

	public static String setClientCode(ClientInfo clientInfo) {

		String code = String.valueOf(clientInfo.getId());

		return code;
	}

}
