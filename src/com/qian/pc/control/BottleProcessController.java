package com.qian.pc.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.AirBottleTrackingRecord;
import com.qian.entity.MultipleSendRecord;
import com.qian.service.AirBottleTrackingRecordService;
import com.qian.util.ExportHeadsUtils;
import com.qian.util.ExportUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.AirBottleTrackingRecordReportVo;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.ExportAirBottleTrackingRecordReportHeads;
import com.qian.vo.Field;

@Controller
@RequestMapping("/bottleProcess")
public class BottleProcessController {

	private static final String BOTTLE_PROCESS_SEARCH_LIST = "bottleProcess/bottleProcessSearchList";

	private static final String AIR_BOTTLE_BACK_LONG_TIME_DETAIL_REPORT_LIST = "bottleProcess/air_bottle_back_long_time_detail_report_list";

	@Autowired
	private AirBottleTrackingRecordService airBottleTrackingRecordService;

	@RequiresUser
	@RequestMapping(value = "bottleProcessSearchList", method = RequestMethod.GET)
	public String bottleProcessSearchList(HttpServletRequest request, Map<String, Object> map) {
		return BOTTLE_PROCESS_SEARCH_LIST;
	}

	@RequiresUser
	@RequestMapping(value = "airBottleBackLongTimeDetailReportList", method = RequestMethod.GET)
	public String airBottleBackLongTimeDetailReportList(HttpServletRequest request, Map<String, Object> map) {
		return AIR_BOTTLE_BACK_LONG_TIME_DETAIL_REPORT_LIST;
	}

	@RequestMapping(value = "getBottleProcess", method = RequestMethod.POST)
	public @ResponseBody
	List<AirBottleTrackingRecord> getBottleProcess(HttpServletRequest request, Map<String, Object> map, String bottleCode) {

		List<AirBottleTrackingRecord> result = airBottleTrackingRecordService.getBottleProcessByCode(bottleCode);

		return result;
	}

	@RequestMapping(value = "getAirBottleBackLongTimeDetailReportInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getAirBottleBackLongTimeDetailReportInfo(HttpServletRequest request, Map<String, Object> map, int page, int rows) {

		Map<String, Object> result = new HashMap<String, Object>();

		Long total = getAirBottleTrackingRecordReportVoTotalNum(request, map);

		List<AirBottleTrackingRecordReportVo> airBottleTrackingRecordReportVoList = getAirBottleTrackingRecordReportVoList(request, map, page, rows);

		result.put("total", total);
		result.put("rows", airBottleTrackingRecordReportVoList);

		return result;
	}

	@RequestMapping(value = "exportAirBottleBackLongTimeDetailReportInfo", method = RequestMethod.POST)
	public void exportAirBottleBackLongTimeDetailReportInfo(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {

		List<ExportAirBottleTrackingRecordReportHeads> heads = ExportHeadsUtils.getExportAirBottleTrackingRecordReportHeads(request);

		List<AirBottleTrackingRecordReportVo> airBottleTrackingRecordReportVoList = getAirBottleTrackingRecordReportVoList(request, map, 1, 10000000);

		ExportUtils.exportExcel(response, heads, airBottleTrackingRecordReportVoList, "回瓶周期异常明细统计.xlsx", "template-AirBottleBackLongTimeDetailReportInfo.xlsx");

	}

	private List<AirBottleTrackingRecordReportVo> getAirBottleTrackingRecordReportVoList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {

		List<String> airBottleTrackingRecordSearchConditionList = new ArrayList<String>();
		Field airBottleTrackingRecordSearchConditionFiled = new Field();

		airBottleTrackingRecordSearchConditionList.add("is_final = ?");
		airBottleTrackingRecordSearchConditionFiled.addInt(1);

		ToolsBarUtils.getAirBottleFinalTrackingRecordSearchCondition(request, airBottleTrackingRecordSearchConditionList,
				airBottleTrackingRecordSearchConditionFiled);

		List<AirBottleTrackingRecordReportVo> airBottleTrackingRecordReportVoList = airBottleTrackingRecordService.getAllAirBottleFinalTrackingRecord(
				airBottleTrackingRecordSearchConditionList, airBottleTrackingRecordSearchConditionFiled, page, rows);

		return airBottleTrackingRecordReportVoList;

	}

	private Long getAirBottleTrackingRecordReportVoTotalNum(HttpServletRequest request, Map<String, Object> map) {

		List<String> airBottleTrackingRecordSearchConditionList = new ArrayList<String>();
		Field airBottleTrackingRecordSearchConditionFiled = new Field();

		airBottleTrackingRecordSearchConditionList.add("is_final = ?");
		airBottleTrackingRecordSearchConditionFiled.addInt(1);
		
		ToolsBarUtils.getAirBottleFinalTrackingRecordSearchCondition(request, airBottleTrackingRecordSearchConditionList,
				airBottleTrackingRecordSearchConditionFiled);

		return airBottleTrackingRecordService.getAirBottleFinalTrackingRecordTotalNum(airBottleTrackingRecordSearchConditionList,
				airBottleTrackingRecordSearchConditionFiled);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "delMultipleSendRecord", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<List<MultipleSendRecord>> delMultipleSendRecord(HttpServletRequest request, Map<String, Object> map) {

		CommonCode commonCode = airBottleTrackingRecordService.delMultipleSendRecord();

		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}

		return BaseDto.getSuccessResult(null);
	}

	@RequestMapping(value = "getMultipleSendNum", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Integer> getMultipleSendNum(HttpServletRequest request, Map<String, Object> map) {

		Integer count = airBottleTrackingRecordService.getMultipleSendNum();

		return BaseDto.getSuccessResult(count);
	}

}
