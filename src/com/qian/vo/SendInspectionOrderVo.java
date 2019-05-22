package com.qian.vo;

import java.util.ArrayList;
import java.util.List;

import com.qian.entity.InspectionOrderInfo;
import com.qian.entity.InspectionOrderItemInfo;
import com.qian.util.InspectionUtils;
import com.qian.util.JSONUtils;

/**
 * @author Lu Kongwen
 * @version Create time：2018-5-24 下午5:36:58
 * @Description
 */
public class SendInspectionOrderVo {

	private String key;

	private String companyCode;

	private Integer count;

	private String remark;

	private String checkOrderInfosStr;

	private String checkNumber;

	public SendInspectionOrderVo(InspectionOrderInfo inspectionOrderInfo, List<InspectionOrderItemInfo> inspectionOrderItemInfos) {

		this.key = InspectionUtils.KEY;
		this.companyCode = InspectionUtils.COMPANY_CODE;
		this.count = inspectionOrderItemInfos.size();
		this.checkNumber = inspectionOrderInfo.getInspectionOrderNumber();

		List<SendInspectionOrderDetailsVo> checkOrderInfos = new ArrayList<SendInspectionOrderDetailsVo>();
		for (InspectionOrderItemInfo inspectionOrderItemInfo : inspectionOrderItemInfos) {
			SendInspectionOrderDetailsVo sendInspectionOrderDetailsVo = new SendInspectionOrderDetailsVo(inspectionOrderItemInfo);
			checkOrderInfos.add(sendInspectionOrderDetailsVo);
		}
		this.checkOrderInfosStr = JSONUtils.fromArrayObject(checkOrderInfos);

	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCheckOrderInfosStr() {
		return checkOrderInfosStr;
	}

	public void setCheckOrderInfosStr(String checkOrderInfosStr) {
		this.checkOrderInfosStr = checkOrderInfosStr;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

}
