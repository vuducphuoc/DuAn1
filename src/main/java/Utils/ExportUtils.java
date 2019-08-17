package Utils;

import Entity.NhanVien;
import Frame.Login.LoginFrame;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportUtils {

    public static void exportExcel(File file, String title, String[] columns, List<Object[]> rows) throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet();

        writeHeader(sheet, title, columns);

        writeContent(sheet, rows);

        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();
    }

    // <editor-fold defaultstate="collapsed" desc="WRITE HEADER ">
    public static void writeHeader(SXSSFSheet sheet, String title, String[] columns) throws FileNotFoundException, IOException {
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columns.length-1));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, columns.length-1));

        sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, columns.length-1));
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, columns.length-1));


        SXSSFRow row;
        SXSSFCell cell;

        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellStyle(createCellStyle1(sheet));
        cell.setCellValue("HỆ THỐNG QUẢN LÝ TÀI SẢN");

        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellStyle(createCellStyle1(sheet));
        cell.setCellValue("NHÓM APT");

        row = sheet.createRow(5);
        cell = row.createCell(0);
        cell.setCellStyle(createCellStyle1(sheet));
        cell.setCellValue(title);

        row = sheet.createRow(9);

        for (int i=0; i< columns.length;i++ ) {
            cell = row.createCell(i);
            cell.setCellStyle(createCellStyle2(sheet));
            cell.setCellValue(columns[i]);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="WRITE CONTENT ">
    private static void writeContent(SXSSFSheet sheet, List<Object[]> listThongKe) {
        int indexRow = 10;

        SXSSFRow row;
        SXSSFCell cell;

        for (int i = 0; i < listThongKe.size(); i++) {
            row = sheet.createRow(indexRow);
            cell = row.createCell(0, CellType.NUMERIC);

            for (int j = 0; j <= listThongKe.get(i).length; j++) {
                if (j == 0) {
                    cell = row.createCell(j);
                    cell.setCellStyle(createCellStyle3(sheet));
                    cell.setCellValue(indexRow - 9);
                } else {
                    cell = row.createCell(j);
                    cell.setCellStyle(createCellStyle3(sheet));
                    cell.setCellValue(listThongKe.get(i)[j-1].toString());
                    if (i != 0) {
                        if (listThongKe.get(i)[j-1].toString().length() > listThongKe.get(i-1)[j-1].toString().length()) {
                            int width = (int) ((listThongKe.get(i)[j-1].toString().length() * 0.44 + 2.24) * 768);
                            sheet.setColumnWidth(j, width);
                        }
                    }
                }
            }
            indexRow++;
        }

        sheet.addMergedRegion(new CellRangeAddress(indexRow + 1, indexRow + 1, 5, 7));
        row = sheet.createRow(indexRow + 1);
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("NGƯỜI THỐNG KÊ");
        cell.setCellStyle(createCellStyle1(sheet));

        sheet.addMergedRegion(new CellRangeAddress(indexRow + 7, indexRow + 7, 5, 7));
        row = sheet.createRow(indexRow + 7);
        cell = row.createCell(5, CellType.STRING);
        NhanVien nhanVien = SingletonDaoUtil.getNhanVienDaoImpl().findById(LoginFrame.accountLogin.getNhanVien());
        cell.setCellValue(nhanVien.getTenNhanVien());
        cell.setCellStyle(createCellStyle1(sheet));
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="AUTO RESIZE ">
    private static void autosizeColumn(SXSSFSheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CREATE CELLSTYLE1 ">
    private static CellStyle createCellStyle1(SXSSFSheet sheet) {
        org.apache.poi.ss.usermodel.Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 13); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CREATE CELLSTYLE2 ">
    private static CellStyle createCellStyle2(SXSSFSheet sheet) {
        org.apache.poi.ss.usermodel.Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 11); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CREATE CELLSTYLE3 ">
    private static CellStyle createCellStyle3(SXSSFSheet sheet) {
        org.apache.poi.ss.usermodel.Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 11); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }
    // </editor-fold>

}
