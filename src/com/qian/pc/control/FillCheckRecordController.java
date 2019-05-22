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

import com.qian.service.AirBottleTrackingRecordService;
import com.qian.service.FillCheckRecordService;
import com.qian.util.ExportHeadsUtils;
import com.qian.util.ExportUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.vo.ExportFillCheckRecordInfoReportHeads;
import com.qian.vo.Field;
import com.qian.vo.FillCheckRecordVo;

/**
 * @author Lu Kongwen
 * @version Create time：2018-4-16 上午11:31:46
 * @Description
 */
@Controller
@RequestMapping("/fillCheckRecord")
public class FillCheckRecordController {

	private static final String LIST = "fillCheckRecord/list";

	@Autowired
	private AirBottleTrackingRecordService airBottleTrackingRecordService;

	@Autowired
	private FillCheckRecordService fillCheckRecordService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {
		return LIST;
	}

	@RequestMapping(value = "getFillCheckRecordInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getFillCheckRecordInfo(HttpServletRequest request, Map<String, Object> map, int page, int rows, int state) {

		return getFillCheckRecordList(request, map, page, rows, state);

	}

	private Map<String, Object> getFillCheckRecordList(HttpServletRequest request, Map<String, Object> map, int page, int rows, int state) {

	
		if(state == 0){
			return getFillCheckNormalRecordList(request, map, page, rows);
		}

		if(state ==1){
			return getFillCheckExceptionRecordList(request, map, page, rows);
		}

		return null;
	}

	private Map<String, Object> getFillCheckNormalRecordList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<String> NormalL = new ArrayList<String>();
		Field NormalField = new Field();

		ToolsBarUtils.getAirBottleTrackingRecordSearchCondition(request, NormalL, NormalField);

		List<Map<String, Object>> fillCheckNormalRecordVoList = airBottleTrackingRecordService.getFillCheckRecordMapList(NormalL, NormalField, page, rows);
		Long totalNormal = airBottleTrackingRecordService.getFillCheckRecordMapTotalNum(NormalL, NormalField);

		result.put("rows", fillCheckNormalRecordVoList);
		result.put("total", totalNormal);
		
		return result;
	}
	
	
	private Map<String, Object> getFillCheckExceptionRecordList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {

		Map<String, Object> result = new HashMap<String, Object>();
		List<String> ExceptionL = new ArrayList<String>();
		Field ExceptionField = new Field();

		ToolsBarUtils.getFillCheckRecordSearchCondition(request, ExceptionL, ExceptionField);

		List<Map<String, Object>> fillCheckExceptionRecordVoList = fillCheckRecordService.getFillCheckRecordMapList(ExceptionL, ExceptionField, page, rows);
		Long totalException = fillCheckRecordService.getFillCheckRecordMapTotalNum(ExceptionL, ExceptionField);

		result.put("rows", fillCheckExceptionRecordVoList);
		result.put("total", totalException);
		
		return result;
	}
	

	@RequestMapping(value = "exportFillCheckRecordInfo", method = RequestMethod.POST)
	public void exportFillCheckRecordInfo(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,Integer state) throws Exception {

		List<ExportFillCheckRecordInfoReportHeads> heads = ExportHeadsUtils.getExportFillCheckRecordInfoReportHeads(request);


		@SuppressWarnings("unchecked")
		List<Map<String, Object>> result = (List<Map<String, Object>>) getFillCheckRecordList(request, map, 1, 5000, state).get("rows");
		
		ExportUtils.exportExcel(response, heads, result, "充装前、后及充装记录表.xls", "template-FillCheckRecordInfo.xls");

	}

	private Map<String, Object> getFillCheckRecordVoList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<String> l = new ArrayList<String>();
		Field field = new Field();

		ToolsBarUtils.getAirBottleTrackingRecordSearchCondition(request, l, field);

		List<FillCheckRecordVo> fillCheckRecordVoList = airBottleTrackingRecordService.getFillCheckRecordVoList(l, field, page, rows);
		Long total = airBottleTrackingRecordService.getFillCheckRecordVoTotalNum(l, field);

		result.put("rows", fillCheckRecordVoList);
		result.put("total", total);

		return result;
	}

	@RequestMapping(value = "getFillCheckRecordHeader", method = RequestMethod.POST)
	public @ResponseBody
	List<List<Map<String, Object>>> getFillCheckRecordHeader(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map)
			throws Exception {
		return fillCheckRecordService.getFillCheckRecordHeader();
	}
}
