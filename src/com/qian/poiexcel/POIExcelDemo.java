package com.qian.poiexcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.processor.RowProcessor;
import net.sf.jxls.transformer.Configuration;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;

public class POIExcelDemo {
    public static class VM {
        private int id;
        private String name;
        private String scale;
        private double price;
        private Date created;

        public VM() {
            super();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getScale() {
            return scale;
        }

        public void setScale(String scale) {
            this.scale = scale;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Date getCreated() {
            return created;
        }

        public void setCreated(Date created) {
            this.created = created;
        }
    }

    public static class ItemCount {
        private String name;
        private List<Integer> counts;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public List<Integer> getCounts() {
            return counts;
        }
        public void setCounts(List<Integer> counts) {
            this.counts = counts;
        }
        
    }

    public static void jxlsCpxDemo() throws ParsePropertyException, InvalidFormatException, IOException {
        XLSTransformer transformer = new XLSTransformer();
        InputStream is = new FileInputStream("E:\\tmp\\template-matrix.xlsx");
        List<ItemCount> itemCounts = new ArrayList<ItemCount>();
        List<String> dates = new ArrayList<String>();
        int maxDates = 10;
        for (int i = 0; i < maxDates ; ++i) {
            dates.add("2013-08-11");
        }
        List<String> dates2 = new ArrayList<String>();
        for (int i = 0; i < maxDates ; ++i) {
            dates2.add("2013-08-13");
        }
        for (int i = 0; i < 17; ++i) {
            ItemCount ic = new ItemCount();
            List<Integer> counts = new ArrayList<Integer>();
            for (int j = 0; j < maxDates; ++j) {
                counts.add(j);
            }
//            ic.setCounts(counts);
            ic.setName("item" + i);
            itemCounts.add(ic);
        }
        List<ItemCount> itemCounts2 = new ArrayList<ItemCount>();
        for (int i = 0; i < 82; ++i) {
            ItemCount ic = new ItemCount();
            List<Integer> counts = new ArrayList<Integer>();
            for (int j = 0; j < maxDates; ++j) {
                counts.add(j);
            }
//            ic.setCounts(counts);
            ic.setName("item" + i);
            itemCounts2.add(ic);
        }
        List<List<String>> datesList = new ArrayList<List<String>>();
        datesList.add(dates);
        datesList.add(dates2);
        Map<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
        map.put("dates", datesList);
        List<List<ItemCount>> itemCountsList = new ArrayList<List<ItemCount>>();
        itemCountsList.add(itemCounts);
        itemCountsList.add(itemCounts2);
        List<String> listSheetNames = new ArrayList<String>();
        listSheetNames.add("1");
        listSheetNames.add("2");
        Workbook workbook = transformer.transformMultipleSheetsList(is, itemCountsList, listSheetNames, "items",
                null, 0);
        workbook.write(new FileOutputStream("E:\\tmp\\simple-multi-cpx-sheet.xlsx"));
    }
    
    public static void jxlsMultiSheetsDemo() throws ParsePropertyException, InvalidFormatException,
            FileNotFoundException, IOException {
        XLSTransformer transformer = new XLSTransformer();
        InputStream is = new FileInputStream("E:\\tmp\\template-simple.xlsx");
        List<VM> vms = new ArrayList<VM>();
        for (int i = 0; i < 30; ++i) {
            VM vma = new VM();
            vma.setName("我的CENTOS");
            vma.setPrice(103);
            vma.setScale("2CPU, 2G MEM, 2T DISK");
            vma.setCreated(new Date());
            vms.add(vma);
        }
        List<VM> vms2 = new ArrayList<VM>();
        for (int i = 0; i < 50; ++i) {
            VM vma = new VM();
            vma.setName("我的CENTOS");
            vma.setPrice(103);
            vma.setScale("2CPU, 2G MEM, 2T DISK");
            vma.setCreated(new Date());
            vms2.add(vma);
        }
        List<List<VM>> objects = new ArrayList<List<VM>>();
        objects.add(vms);
        objects.add(vms2);

        List<String> listSheetNames = new ArrayList<String>();
        listSheetNames.add("1");
        listSheetNames.add("2");

        Workbook workbook = transformer.transformMultipleSheetsList(is, objects, listSheetNames, "vms",
                null, 0);
        workbook.write(new FileOutputStream("E:\\tmp\\simple-multi-sheet.xlsx"));
    }

    public static void jxlsDemo() throws ParsePropertyException, InvalidFormatException, IOException {
        XLSTransformer transformer = new XLSTransformer();
        String srcFilePath = "E:\\tmp\\template-simple.xlsx";
        Map<String, Object> beanParams = new HashMap<String, Object>();
        List<VM> vms = new ArrayList<VM>();
        VM vm = new VM();
        vm.setName("我的CENTOS");
        vm.setPrice(103);
        vm.setScale("2CPU, 2G MEM, 2T DISK");
        vm.setCreated(new Date());
        vms.add(vm);
        VM vm2 = new VM();
        vm2.setName("my-ubuntu");
        vm2.setPrice(200);
        vm2.setScale("1CPU, 3G MEM, 1T DISK");
        vm2.setCreated(new Date());
        vms.add(vm2);
        beanParams.put("vms", vms);

        // for (int i = 0; i < 65536; ++i) {
        // VM vma = new VM();
        // vma.setName("我的CENTOS");
        // vma.setPrice(103);
        // vma.setScale("2CPU, 2G MEM, 2T DISK");
        // vma.setCreated(new Date());
        // vms.add(vma);
        // }
        String destFilePath = "E:\\tmp\\simple.xlsx";
//        transformer.registerRowProcessor(new RowProcessor() {
//
//            public void processRow(net.sf.jxls.transformer.Row row, Map namedCells) {
//                // TODO Auto-generated method stub
//                row.getPoiRow().getRowNum();
//                row.getSheet().getWorkbook().addSheet(sheet);
//                row.getSheet().getWorkbook().
//            }
//        });
        transformer.transformXLS(srcFilePath, beanParams, destFilePath);
    }

    public static void main(String[] args) throws Exception {
//        jxlsDemo();
//        jxlsMultiSheetsDemo();
        // poiExample();
//        jxlsCpxDemo();
//        testExcelUtils();
        testExcelUtilsCpx();
    }

    public static void testExcelUtils() throws Exception {
        List<VM> vms = new ArrayList<VM>();
        for (int i = 0; i < 21; ++i) {
            VM vma = new VM();
            vma.setId(i);
            vma.setName("我的CENTOS" + i);
            vma.setPrice(103);
            vma.setScale("2CPU, 2G MEM, 2T DISK");
            vma.setCreated(new Date());
            vms.add(vma);
        }
        ExcelUtils.generateExcelByTemplate("E:\\tmp\\ex-sample.xlsx", "E:\\tmp\\template-simple.xlsx", vms, "vms", 10);
    }

    public static void testExcelUtilsCpx() throws Exception {
        List<String> dates = new ArrayList<String>();
        int maxDates = 8;
        for (int i = 0; i < maxDates ; ++i) {
            dates.add("2013-08-11");
        }
        List<ItemCount> itemCounts = new ArrayList<ItemCount>();
        for (int i = 0; i < 82; ++i) {
            ItemCount ic = new ItemCount();
            List<Integer> counts = new ArrayList<Integer>();
            for (int j = 0; j < maxDates; ++j) {
                counts.add(j);
            }
            ic.setCounts(counts);
            ic.setName("item" + i);
            itemCounts.add(ic);
        }
        ExcelUtils.generateExcelByTemplate("E:\\tmp\\ex-cpx.xlsx", 
                "E:\\tmp\\template-matrix-bak.xlsx", dates, "dates", itemCounts, "vms", 12);
    }

    public void poiExample() throws FileNotFoundException, IOException {
        Workbook wb = new HSSFWorkbook();
        CreationHelper creationHelper = wb.getCreationHelper();
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        String safeName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]"); // returns
                                                                                   // " O'Brien's sales   "
        Sheet sheet = wb.createSheet(safeName);
        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow((short) 0);
        // Create a cell and put a value in it.
        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        // Or do it on one line.
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(creationHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);

        cell.setCellValue(new Date());

        // we style the second cell as a date (and time). It is important to
        // create a new cell style from the workbook otherwise you can end up
        // modifying the built in style and effecting not only this cell but
        // other cells.
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        cell = row.createCell(1);
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);

        // you can also set date as java.util.Calendar
        cell = row.createCell(2);
        cell.setCellValue(Calendar.getInstance());
        cell.setCellStyle(cellStyle);

        wb.write(fileOut);
        fileOut.close();
    }
}
