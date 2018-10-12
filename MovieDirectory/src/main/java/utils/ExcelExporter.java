package utils;

import domain.Movie;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelExporter {

    private static Workbook workbook = new XSSFWorkbook();
    private static Sheet sheet;
    private static List<Movie> movieList = new ArrayList<Movie>();
    private static final String[] columns = {"Tittle", "Director", "Production Date", "Actors", "Genre"};


    public ExcelExporter(List<Movie> movies) throws IOException {
        sheet = workbook.createSheet("Movies");
        ExcelExporter.prepareStyleExcelFile();
        movieList = movies;
        ExcelExporter.saveDataIntoExcel();
        ExcelExporter.writeOutputToExcelFile();
        System.out.println("The Excel file was created- successful " + "\n name: movies.xlsx");
    }

    private static void createRowAndColumns(CellStyle cellStyle) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(cellStyle);
        }
    }

    private static void prepareStyleExcelFile() {
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        ExcelExporter.createRowAndColumns(headerCellStyle);
    }

    private static void saveDataIntoExcel() {
        int rowNum = 1;

        for (Movie mv : movieList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(mv.getTitle());
            row.createCell(1).setCellValue(mv.getDirector());
            row.createCell(2).setCellValue(mv.getProductionDate());
            row.createCell(3).setCellValue(mv.getActors().toString());
            row.createCell(4).setCellValue(mv.getGenre());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private static void writeOutputToExcelFile() throws IOException {
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("movies.xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }
}


