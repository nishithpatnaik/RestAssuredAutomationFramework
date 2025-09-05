package api.utilities;

import org.testng.annotations.DataProvider;

public class Pet_DataProvider {
	
	 @DataProvider(name = "PetDetailsData")
	    
	 public Object[][] getPetDetails() {

	        // Path to the Excel file
	        String excelPath = System.getProperty("user.dir") + "//TestData//Pet_TestData.xlsx";

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
	        Object[][] petData = new Object[rowCount][colCount];

	        // Copy data from fullData (excluding the header)
	        for (int row = 1; row < fullData.length; row++) {
	            for (int col = 0; col < colCount; col++) {
	            	petData[row - 1][col] = fullData[row][col];
	            }
	        }

	        return petData;
	    }
	 
}
