package com.qian.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.qian.poiexcel.ExcelUtils;

/**
 * @author Lu Kongwen
 * @version Create time：2016-10-9 下午4:57:34
 * @Description
 */
public class ExportUtils {

	public static final int maxRowPerSheet = 100000;
	
	public static void exportExcel(HttpServletResponse response, List<?> datas, String destFileName, String templateFileName) {
		exportExcel(response, null, datas, destFileName, templateFileName);
	}

	public static void exportExcel(HttpServletResponse response, List<?> heads, List<?> datas, String destFileName, String templateFileName) {

		try {
			destFileName = new String(destFileName.getBytes(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		response.setHeader("Content-Disposition", "attachment;filename=" + destFileName);
		response.setContentType("application/vnd.ms-excel");

		InputStream in = null;
		OutputStream out = null;

		try {
			in = new BufferedInputStream(new FileInputStream("D:\\jxls-template\\new-energymanage\\" + templateFileName));
			out = response.getOutputStream();

			if (heads == null) {
				ExcelUtils.generateExcelByTemplate(out, in, datas, "vms", maxRowPerSheet);
			} else {
				ExcelUtils.generateExcelByTemplate(out, in, heads, "hds", datas, "vms", maxRowPerSheet);
			}

			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
