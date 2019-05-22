package com.qian.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author Lu Kongwen
 * @version Create time：2017-12-15 下午4:12:39
 * @Description
 */
public class ExcelReader {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private static NumberFormat numberFormat = NumberFormat.getInstance();
	static{
		numberFormat.setGroupingUsed(false);
	}

	public static List<Map<String, String>> readWorkBook(InputStream inp) {

		// 所有的单元格
		List<Map<String, String>> allExcel_tmp = new ArrayList<Map<String, String>>();

		try {
			// poi读取excel
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);
			// 是否是第一行
			Boolean is_first = true;
			// 每个单元格的key
			List<String> cellKeys = new ArrayList<String>();
			int cnt = 0;
			// 利用foreach循环 遍历sheet中的所有行
			for (Row row : sheet) {
				// 一行单元格组成的map
				Map<String, String> excelMap = new HashMap<String, String>();
				// 遍历row中的所有方格
				cnt = 0;
				for (int i = 0; i < row.getLastCellNum(); i++) {
					Cell cell = row.getCell(i);
					// 根据编码转换
					String context = getCellValue(cell);
					if (is_first) {
						cellKeys.add(context);
					} else {
						excelMap.put(cellKeys.get(cnt), context);
					}
					cnt++;
				}
				if (!excelMap.isEmpty()) {
					allExcel_tmp.add(excelMap);
				}

				is_first = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭输入流
				inp.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return allExcel_tmp;
	}

	public static String getCellValue(Cell cell) {

		if (cell == null) {
			return null;
		}

		String value = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC: // 数字
			// 如果为时间格式的内容
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				value = dateFormat.format(cell.getDateCellValue());
				break;
			} else {
				value = numberFormat.format(cell.getNumericCellValue());
			}
			break;
		case HSSFCell.CELL_TYPE_STRING: // 字符串
			value = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
			value = cell.getBooleanCellValue() + "";
			break;
		case HSSFCell.CELL_TYPE_FORMULA: // 公式
			value = cell.getCellFormula() + "";
			break;
		case HSSFCell.CELL_TYPE_BLANK: // 空值
			value = "";
			break;
		case HSSFCell.CELL_TYPE_ERROR: // 故障
			value = "非法字符";
			break;
		default:
			value = "未知类型";
			break;
		}
		return value;
	}
}
