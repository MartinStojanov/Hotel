package com.example.demo.model;

import java.io.IOException;
import java.util.List;

import com.example.demo.service.GuestService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import static org.apache.poi.ss.util.CellUtil.createCell;

public class GuestExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Guests> guestsList;



    public GuestExcelExporter(List<Guests> guestsList) {
        this.guestsList = guestsList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Guest Name", style);
        createCell(row, 1, "Guest Surname", style);
        createCell(row, 2, "Guest EMBG", style);
        createCell(row, 3, "Guest E-mail", style);
        createCell(row, 4, "Guest isPaid", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Guests guests : guestsList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, guests.getName(), style);
            createCell(row, columnCount++, guests.getSurname(), style);
            createCell(row, columnCount++, guests.getEMBG(), style);
            createCell(row, columnCount++, guests.getEmail(), style);
            createCell(row, columnCount++, guests.isPaid(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }

}
