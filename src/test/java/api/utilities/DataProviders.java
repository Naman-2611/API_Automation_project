package api.utilities;

import org.testng.annotations.DataProvider;



public class DataProviders {
		
	@DataProvider(name="Data")
	public String[][] getAllData(){
		ExcelData data = new ExcelData("D:\\Workspace\\PetStoreAutomation\\TestData\\userdata.xlsx");
		
		int rows = data.getrowCount("sheet1");
		int columns = data.getColumnCount("sheet1", 1);
		
		String[][] apidata = new String[rows][columns];
		
		for(int i = 1;i<=rows;i++) {
			for(int j =0;j<columns;j++) {
				apidata[i - 1][j] = data.getCellData("Sheet1", i, j);
			}
		}
		
		return apidata;
	}
	
	@DataProvider(name="UserName")
	public String[] getUserNames(){
		ExcelData data = new ExcelData("D:\\Workspace\\PetStoreAutomation\\TestData\\userdata.xlsx");
		
		int rows = data.getrowCount("sheet1");
		
		String[] apidata = new String[rows];
		
		for(int i = 1;i<=rows;i++) {
			
				apidata[i - 1] = data.getCellData("Sheet1", i, 1);
			
		}
		
		return apidata;
	}
}
