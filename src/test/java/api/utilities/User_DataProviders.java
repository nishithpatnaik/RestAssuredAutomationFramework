package api.utilities;

import org.testng.annotations.DataProvider;

/*
 * Here in this Data Provider,we are using String[][] 2D array. So whenever you have Integer/Double values, you need to convert them to from String to Integer/Double in your Test
 * 
 * */

public class User_DataProviders {
	
	
	@DataProvider(name = "UsersDetails_data")
	public Object[][] getUsersDetailsData()
	{
		String Excel_UserData = System.getProperty("user.dir") + "//TestData//User_TestData.xlsx";
		int rowcount = ReadExcelFile.getRowCount(Excel_UserData, "Sheet1");
		int colcount = ReadExcelFile.getColCount(Excel_UserData, "Sheet1");
		
		//Setup a 2D Array
		Object userData[][]  = new Object[rowcount-1][colcount];
		
		//Set values in the 2D array by traversing every row and column
		for(int row=1;row<rowcount;row++) // Start from Row 1 instead of 0 so that you skip the header row
		{
			for(int col=0;col<colcount;col++)
			{
				userData[row-1][col] = ReadExcelFile.getCellValue(Excel_UserData, "Sheet1", row, col); // Fetch each cell value and store in the userData object
			}
		}
		
		return userData;
		
		
	}
	
	@DataProvider(name = "UserNames_Data")
	public Object[] getUseNameData()
	{
		String Excel_UserData = System.getProperty("user.dir") + "//TestData//User_TestData.xlsx";
		int rowcount = ReadExcelFile.getRowCount(Excel_UserData, "Sheet1");
		//int colcount = ReadExcelFile.getColCount(Excel_UserData, "Sheet1");
		
		//Setup a 2D Array
		Object userNameData[]  = new Object[rowcount-1];
		
		//Set values in the 2D array by traversing every row and column
		for(int row=1;row<rowcount;row++) // Start from Row 1 instead of 0 so that you skip the header row
		{
			userNameData[row-1] = ReadExcelFile.getCellValue(Excel_UserData, "Sheet1", row,1); // Fetch each cell value and store in the userData object
		}
		
		return userNameData;
	}

}
