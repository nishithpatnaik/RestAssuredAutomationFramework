package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;

public class ReadExcelFile {

    /**
     * Gets the value of a specific cell using a 2D Object array to avoid direct parsing.
     * @param fileName Path to the Excel file
     * @param sheetName Name of the sheet
     * @param rowNo Row number (0-based)
     * @param cellNo Cell number (0-based)
     * @return Cell value as Object (already parsed by getAllData)
     */
    public static Object getCellValue(String fileName, String sheetName, int rowNo, int cellNo) {
        Object[][] data = getAllData(fileName, sheetName);
        if (data == null || rowNo >= data.length || cellNo >= data[0].length) {
            return null;
        }
        return data[rowNo][cellNo];
    }

    /**
     * Gets the total number of rows in a sheet (including header)
     */
    public static int getRowCount(String fileName, String sheetName) {
        try (FileInputStream inputStream = new FileInputStream(fileName);
             XSSFWorkbook workBook = new XSSFWorkbook(inputStream)) {

            XSSFSheet excelSheet = workBook.getSheet(sheetName);
            if (excelSheet == null) {
                return 0;
            }

            return excelSheet.getLastRowNum() == 0 && excelSheet.getRow(0) == null
                   ? 0
                   : excelSheet.getLastRowNum() + 1;

        } catch (IOException e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Gets the total number of columns in the first row of a sheet
     */
    public static int getColCount(String fileName, String sheetName) {
        try (FileInputStream inputStream = new FileInputStream(fileName);
             XSSFWorkbook workBook = new XSSFWorkbook(inputStream)) {

            XSSFSheet excelSheet = workBook.getSheet(sheetName);
            if (excelSheet == null) {
                return 0;
            }

            XSSFRow firstRow = excelSheet.getRow(0);
            if (firstRow == null) {
                return 0;
            }

            return firstRow.getLastCellNum(); // already returns column count

        } catch (IOException e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Gets all data from a sheet as a 2D Object array
     */
    public static Object[][] getAllData(String fileName, String sheetName) {
        try (FileInputStream inputStream = new FileInputStream(fileName);
             XSSFWorkbook workBook = new XSSFWorkbook(inputStream)) {

            XSSFSheet excelSheet = workBook.getSheet(sheetName);
            if (excelSheet == null) {
                return null;
            }

            int rowCount = excelSheet.getLastRowNum() + 1;
            if (rowCount == 0) {
                return new Object[0][0];
            }

            XSSFRow headerRow = excelSheet.getRow(0);
            if (headerRow == null) {
                return new Object[rowCount][0];
            }

            int colCount = headerRow.getLastCellNum();
            Object[][] data = new Object[rowCount][colCount];

            for (int i = 0; i < rowCount; i++) {
                XSSFRow row = excelSheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    if (row != null) {
                        XSSFCell cell = row.getCell(j);
                        data[i][j] = getCellValueAsObject(cell);
                    } else {
                        data[i][j] = null;
                    }
                }
            }

            return data;

        } catch (IOException e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Checks if a sheet exists in the workbook
     */
    public static boolean sheetExists(String fileName, String sheetName) {
        try (FileInputStream inputStream = new FileInputStream(fileName);
             XSSFWorkbook workBook = new XSSFWorkbook(inputStream)) {

            return workBook.getSheet(sheetName) != null;

        } catch (IOException e) {
            System.err.println("Error checking sheet existence: " + e.getMessage());
            return false;
        }
    }

    /**
     * Helper method to get cell value as Object with correct type
     */
    private static Object getCellValueAsObject(XSSFCell cell) {
        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }

            case BOOLEAN:
                return cell.getBooleanCellValue();

            case FORMULA:
                try {
                    switch (cell.getCachedFormulaResultType()) {
                        case STRING:
                            return cell.getStringCellValue().trim();
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                return cell.getDateCellValue();
                            } else {
                                return cell.getNumericCellValue();
                            }
                        case BOOLEAN:
                            return cell.getBooleanCellValue();
                        default:
                            return null;
                    }
                } catch (Exception e) {
                    return "=" + cell.getCellFormula();
                }

            case BLANK:
            case _NONE:
            default:
                return null;
        }
    }
}
