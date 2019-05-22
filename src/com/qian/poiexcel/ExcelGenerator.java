package com.qian.poiexcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;

public class ExcelGenerator {
    public void generate(String filePath, String templateFilePath, List<Object> objects) throws IOException, InvalidFormatException {
        InputStream is = new FileInputStream(templateFilePath);
        
        Workbook wb = WorkbookFactory.create(is);
        Sheet sheet = wb.getSheetAt(0);
        Template tpl = new Template();

        Row row0 = sheet.getRow(0);
        for (Cell cell : row0) {
            tpl.addHeaders(cell.getStringCellValue());
        }
        Row row1 = sheet.getRow(0);
        for (Cell cell : row1) {
            tpl.addColumns(cell.getStringCellValue());
        }
        OutputStream os = new FileOutputStream(filePath);
        Workbook wwb = new HSSFWorkbook();
        Sheet wsheet = wwb.createSheet();
        // headers
        createHaders(tpl, wsheet);
        // rows
        List<String> columns = tpl.getColumns();
        for (int i = 0; i < objects.size(); ++i) {
            Row row = wsheet.createRow(0);
            for (String column : columns) {
                if (column.startsWith("{") && column.endsWith("}")) {
                    String name = column.replace("{", "").replace("}", "");
                }
            }
        }
    }

    private void createHaders(Template tpl, Sheet wsheet) {
        Row row = wsheet.createRow(0);
        List<String> headers = tpl.getHeaders();
        for (int i = 0; i < headers.size(); ++i) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headers.get(i));
        }
    }
}
