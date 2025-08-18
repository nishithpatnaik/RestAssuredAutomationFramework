package api.utilities;

import org.testng.annotations.DataProvider;
/*
 * Here in this Data Provider, I am using Object[][] 2D array which can hold any type of test data i.e. String, Integer, Double etc.
 * 
 * Note: When you are reading from excel, Apache POI reads all Integer values as Double. So whereever you using this Data Provider, recevice those values as Double instead of Integer and then Type Cast (Ex: Id.value()
 * 
 * Here first we are fetching all the data from an excel and storing in fulldata[][]
 * We assume the first row is header and skip it.
 * For the remaining rows, we traverse and add to the storeData[][] object and return it
 * 
 * 
 * */
public class Store_DataProvider {

    @DataProvider(name = "StoreDetailsData")
    public Object[][] getStoreDetails() {

        // Path to the Excel file
        String excelPath = System.getProperty("user.dir") + "//TestData//Store_TestData.xlsx";

        // Load all data from the sheet (including header)
        Object[][] fullData = ReadExcelFile.getAllData(excelPath, "Sheet1");

        // Check if data is null or has only header row
        if (fullData == null || fullData.length <= 1) {
            return new Object[0][0]; // return empty data
        }

        // Get number of data rows (excluding header) and columns
        int rowCount = fullData.length - 1;
        int colCount = fullData[0].length;

        // Create a new array to store data excluding the header row
        Object[][] storeData = new Object[rowCount][colCount];

        // Copy data from fullData (excluding the header)
        for (int row = 1; row < fullData.length; row++) {
            for (int col = 0; col < colCount; col++) {
                storeData[row - 1][col] = fullData[row][col];
            }
        }

        return storeData;
    }
    
    //Returning only a single columns data
    
    @DataProvider(name = "StoreIds")
    public Object[][] getStoreIds() {
        String Excel_StoreData = System.getProperty("user.dir") + "//TestData//Store_TestData.xlsx";
        int rowcount = ReadExcelFile.getRowCount(Excel_StoreData, "Sheet1");

        Object[][] ids = new Object[rowcount - 1][1];

        for (int row = 1; row < rowcount; row++) {
            Object cellValue = ReadExcelFile.getCellValue(Excel_StoreData, "Sheet1", row, 0);  // Column 0 = id
            ids[row - 1][0] = cellValue;
        }

        return ids;
    }

}
