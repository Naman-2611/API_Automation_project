/**
 * 
 */
package api.utilities;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 */
public class ExcelData {

	public String path;
	public  FileInputStream fi;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public  int rowcount;
	public  int cellcount;
	public  XSSFRow row;
	public XSSFCell cell;
	public DataFormatter formatter;
	public String data;
	
	
	public ExcelData(String path){
		this.path = path;
	}

	public int getrowCount(String sheetname) {
		try {
			fi = new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetname);
			rowcount = sheet.getLastRowNum();
			workbook.close();
			fi.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowcount;
	}

	public int getColumnCount(String sheetname , int rownum) {
		try {
			fi = new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetname);
			row = sheet.getRow(rownum);
			cellcount = row.getLastCellNum();
			workbook.close();
			fi.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cellcount;
		
	}

	public String getCellData(String sheetname , int rownum , int colnum) {
		
		try {
			fi = new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetname);
			row = sheet.getRow(rownum);
			cell = row.getCell(colnum);
			
			formatter = new DataFormatter();
			data = formatter.formatCellValue(cell);
			workbook.close();
			fi.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return data;
	}

}
