package com.qian.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.qian.entity.User;
import com.qian.vo.ExportAirBottleTrackingRecordReportHeads;
import com.qian.vo.ExportFillCheckRecordInfoReportHeads;
import com.qian.vo.ExportICCardRecordReportHeads;
import com.qian.vo.ExportOrderDetailInfoHeads;
import com.qian.vo.ExportOrderInfoReportHeads;
import com.qian.vo.ExportReportHeads;
import com.qian.vo.ExportReturnBottleInfoReportHeads;
import com.qian.vo.FamilyCheckInfoReportHeads;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-11 下午4:33:11
 * @Description
 */
public class ExportHeadsUtils {

	public static List<ExportOrderInfoReportHeads> getExportOrderInfoReportHeads(HttpServletRequest request) {

		List<ExportOrderInfoReportHeads> heads = new ArrayList<ExportOrderInfoReportHeads>();

		ExportOrderInfoReportHeads exportOrderInfoReportHeads = new ExportOrderInfoReportHeads();

		String begin_time = request.getParameter("begin_report_time");
		String end_time = request.getParameter("end_report_time");

		exportOrderInfoReportHeads.setBegin_time(begin_time);
		exportOrderInfoReportHeads.setEnd_time(end_time);

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		exportOrderInfoReportHeads.setExport_operator(user.getFull_name());

		heads.add(exportOrderInfoReportHeads);

		return heads;
	}

	public static List<ExportAirBottleTrackingRecordReportHeads> getExportAirBottleTrackingRecordReportHeads(
			HttpServletRequest request) {

		List<ExportAirBottleTrackingRecordReportHeads> heads = new ArrayList<ExportAirBottleTrackingRecordReportHeads>();

		ExportAirBottleTrackingRecordReportHeads exportAirBottleTrackingRecordReportHeads = new ExportAirBottleTrackingRecordReportHeads();

		String begin_time = request.getParameter("begin_time");

		String end_time = request.getParameter("end_time");

		String no_back_time = request.getParameter("no_back_time");

		exportAirBottleTrackingRecordReportHeads.setBegin_time(begin_time);
		exportAirBottleTrackingRecordReportHeads.setEnd_time(end_time);
		exportAirBottleTrackingRecordReportHeads.setNo_back_time(no_back_time);

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		exportAirBottleTrackingRecordReportHeads.setExport_operator(user.getFull_name());

		heads.add(exportAirBottleTrackingRecordReportHeads);

		return heads;
	}

	public static List<ExportICCardRecordReportHeads> getExportICCardRecordReportHeads(HttpServletRequest request) {

		List<ExportICCardRecordReportHeads> heads = new ArrayList<ExportICCardRecordReportHeads>();

		ExportICCardRecordReportHeads exportICCardRecordReportHeads = new ExportICCardRecordReportHeads();

		String create_time_begin_time = request.getParameter("create_time_begin_time");
		String create_time_end_time = request.getParameter("create_time_end_time");

		exportICCardRecordReportHeads.setCreate_time_begin_time(create_time_begin_time);
		exportICCardRecordReportHeads.setCreate_time_end_time(create_time_end_time);

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		exportICCardRecordReportHeads.setExport_operator(user.getFull_name());

		heads.add(exportICCardRecordReportHeads);

		return heads;
	}

	public static List<ExportReturnBottleInfoReportHeads> getExportReturnBottleInfoReportHeads(
			HttpServletRequest request) {

		List<ExportReturnBottleInfoReportHeads> heads = new ArrayList<ExportReturnBottleInfoReportHeads>();

		ExportReturnBottleInfoReportHeads exportReturnBottleInfoReportHeads = new ExportReturnBottleInfoReportHeads();

		String begin_time = request.getParameter("begin_time");
		String end_time = request.getParameter("end_time");

		exportReturnBottleInfoReportHeads.setBegin_time(begin_time);
		exportReturnBottleInfoReportHeads.setEnd_time(end_time);

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		exportReturnBottleInfoReportHeads.setExport_operator(user.getFull_name());

		heads.add(exportReturnBottleInfoReportHeads);

		return heads;
	}

	public static List<ExportOrderDetailInfoHeads> getExportOrderDetailInfoHeads(HttpServletRequest request) {

		List<ExportOrderDetailInfoHeads> heads = new ArrayList<ExportOrderDetailInfoHeads>();

		ExportOrderDetailInfoHeads exportOrderDetailInfoHeads = new ExportOrderDetailInfoHeads();

		String begin_time = request.getParameter("begin_time");
		String end_time = request.getParameter("end_time");

		exportOrderDetailInfoHeads.setBegin_time(begin_time);
		exportOrderDetailInfoHeads.setEnd_time(end_time);

		String begin_appointment_time = request.getParameter("begin_appointment_time");
		String end_appointment_time = request.getParameter("end_appointment_time");

		exportOrderDetailInfoHeads.setBegin_appointment_time(begin_appointment_time);
		exportOrderDetailInfoHeads.setEnd_appointment_time(end_appointment_time);

		String price = request.getParameter("price");
		exportOrderDetailInfoHeads.setPrice(price);

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		exportOrderDetailInfoHeads.setExport_operator(user.getFull_name());

		heads.add(exportOrderDetailInfoHeads);

		return heads;
	}

	public static List<ExportFillCheckRecordInfoReportHeads> getExportFillCheckRecordInfoReportHeads(
			HttpServletRequest request) {

		List<ExportFillCheckRecordInfoReportHeads> heads = new ArrayList<ExportFillCheckRecordInfoReportHeads>();

		ExportFillCheckRecordInfoReportHeads exportFillCheckRecordInfoReportHeads = new ExportFillCheckRecordInfoReportHeads();

		String fill_date = request.getParameter("fill_date");
		exportFillCheckRecordInfoReportHeads.setFill_date(fill_date);

		String fill_operator = request.getParameter("fill_operator");
		exportFillCheckRecordInfoReportHeads.setFill_operator(fill_operator);

		String air_temperature = request.getParameter("air_temperature");
		exportFillCheckRecordInfoReportHeads.setAir_temperature(air_temperature);

		heads.add(exportFillCheckRecordInfoReportHeads);

		return heads;
	}

	public static List<FamilyCheckInfoReportHeads> getExportFamilyCheckInfoReportHeads(HttpServletRequest request) {

		List<FamilyCheckInfoReportHeads> heads = new ArrayList<FamilyCheckInfoReportHeads>();

		FamilyCheckInfoReportHeads familyCheckInfoReportHeads = new FamilyCheckInfoReportHeads();

		String begin_time = request.getParameter("family_check_time_begin_time");
		String end_time = request.getParameter("family_check_time_end_time");

		familyCheckInfoReportHeads.setBegin_time(begin_time);
		familyCheckInfoReportHeads.setEnd_time(end_time);

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		familyCheckInfoReportHeads.setExport_operator(user.getFull_name());

		heads.add(familyCheckInfoReportHeads);

		return heads;
	}

	public static List<ExportReportHeads> getExportReportHeads(HttpServletRequest request) {
		
		List<ExportReportHeads> heads = new ArrayList<ExportReportHeads>();

		ExportReportHeads exportReportHeads = new ExportReportHeads();

		String begin_time = request.getParameter("begin_report_time");
		if(StringUtils.nonEmpty(begin_time)){
			exportReportHeads.setBegin_time(begin_time);
		}
		
		String end_time = request.getParameter("end_report_time");
		if(StringUtils.nonEmpty(end_time)){
			exportReportHeads.setEnd_time(end_time);
		}
		
		String create_time_begin_time = request.getParameter("create_time_begin_time");
		if(StringUtils.nonEmpty(create_time_begin_time)){
			exportReportHeads.setBegin_time(create_time_begin_time);
		}
		
		String create_time_end_time = request.getParameter("create_time_end_time");
		if(StringUtils.nonEmpty(create_time_end_time)){
			exportReportHeads.setEnd_time(create_time_end_time);
		}

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		exportReportHeads.setExport_operator(user.getFull_name());

		heads.add(exportReportHeads);

		return heads;
	}
}
