package com.qian.pc.control;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qian.entity.AirBottleImportInfo;
import com.qian.entity.User;
import com.qian.mobile.entity.MobileAirBottleSumbitEntity;
import com.qian.service.AirBottleImportInfoService;
import com.qian.util.ExcelReader;
import com.qian.util.ExportHeadsUtils;
import com.qian.util.ExportUtils;
import com.qian.util.ToolsBarUtils;
import com.qian.util.UploadUtils;
import com.qian.vo.BaseDto;
import com.qian.vo.CommonCode;
import com.qian.vo.ExportReportHeads;
import com.qian.vo.Field;

/**
 * @author Lu Kongwen
 * @version Create time：2017-12-15 下午4:50:46
 * @Description
 */
@Controller
@RequestMapping("/airBottleImportInfo")
public class AirBottleImportInfoController {

	private static final String LIST = "airBottleInfoImport/list";

	@Autowired
	private AirBottleImportInfoService airBottleImportInfoService;

	@RequiresUser
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Map<String, Object> map) {
		return LIST;
	}

	@RequestMapping(value = "getPageList")
	public @ResponseBody
	Map<String, Object> getPageList(HttpServletRequest request, Map<String, Object> map, int page, int rows) {

		Map<String, Object> result = new HashMap<String, Object>();
		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		ToolsBarUtils.getAirBottleImportInfoSearchCondition(request, l, filed);

		Long total = airBottleImportInfoService.getTotalNum(l, filed);
		List<AirBottleImportInfo> list = airBottleImportInfoService.getPageList(l, filed, page, rows);

		result.put("total", total);
		result.put("rows", list);

		return result;
	}

	@RequestMapping(value = "upLoadExcel", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BaseDto<Object> upLoadExcel(HttpServletRequest request, Map<String, Object> map, MobileAirBottleSumbitEntity entity) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		// 上传图片
		InputStream inputStream = UploadUtils.uploadFile(request);
		List<Map<String, String>> excvelMaps = ExcelReader.readWorkBook(inputStream);

		CommonCode commonCode = airBottleImportInfoService.upLoadExcel(excvelMaps, user);

		return BaseDto.getResult(commonCode, null);
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> del(HttpServletRequest request, Map<String, Object> map, String ids) {

		CommonCode commonCode = airBottleImportInfoService.delByIds(ids);

		return BaseDto.getResult(commonCode, null);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> add(HttpServletRequest request, Map<String, Object> map, AirBottleImportInfo airBottleImportInfo) {

		CommonCode commonCode = airBottleImportInfoService.add(airBottleImportInfo);

		return BaseDto.getResult(commonCode, null);
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> edit(HttpServletRequest request, Map<String, Object> map, AirBottleImportInfo airBottleImportInfo, int entityId) {

		CommonCode commonCode = airBottleImportInfoService.edit(airBottleImportInfo, entityId);

		return BaseDto.getResult(commonCode, null);
	}

	@RequestMapping(value = "transform", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> transform(HttpServletRequest request, Map<String, Object> map) {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		ToolsBarUtils.getAirBottleImportInfoSearchCondition(request, l, filed);

		CommonCode commonCode = airBottleImportInfoService.transform(l, filed);

		return BaseDto.getResult(commonCode, null);
	}

	@RequestMapping(value = "batchDel", method = RequestMethod.POST)
	public @ResponseBody
	BaseDto<Object> batchDel(HttpServletRequest request, Map<String, Object> map) {

		List<String> l = new ArrayList<String>();
		Field filed = new Field();

		ToolsBarUtils.getAirBottleImportInfoSearchCondition(request, l, filed);

		CommonCode commonCode = airBottleImportInfoService.batchDel(l, filed);

		return BaseDto.getResult(commonCode, null);
	}

	@RequestMapping(value = "downloadTemplate")
	public @ResponseBody
	void downloadTemplate(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {

		List<ExportReportHeads> heads = ExportHeadsUtils.getExportReportHeads(request);

		List<String> result = new ArrayList<String>();

		ExportUtils.exportExcel(response, heads, result, "气瓶导入模板.xls", "template-AirBottleImportInfoTemplate.xls");

	}
}
