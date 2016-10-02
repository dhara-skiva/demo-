package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {
	
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	
	public DataReader(String filename) throws Exception{
		
		File newfile = new File(filename);
		FileInputStream fis = new FileInputStream(newfile);
		
		workbook = new XSSFWorkbook(fis);
		
	}

	public void getSheet(String sheetname){
		sheet = workbook.getSheet(sheetname);
	}
	
	public int getRows(String sheetname){
		int rows =  sheet.getLastRowNum();
		return rows +1;
	}
	
	public int getRows(int sheetIndex){
		int rows =  sheet.getLastRowNum();
		return rows +1;
	}
	
	public String getCellData(int sheetIndex, int rownum, int colsnum){
		
		//System.out.println("Row :"+rownum+ "Col :"+colsnum);
		sheet = workbook.getSheetAt(sheetIndex);
		
		Cell cell = sheet.getRow(rownum).getCell(colsnum);
		String value = "";
		
		switch(cell.getCellType()){
		
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
	
		case Cell.CELL_TYPE_BOOLEAN:
			boolean boolval = cell.getBooleanCellValue(); 
			value = Boolean.toString(boolval);
			break;
		
		case Cell.CELL_TYPE_NUMERIC:
			double dval = cell.getNumericCellValue();
			value = Double.toString(dval);
			break;
		
		case Cell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
			
		default:
			Date dateval = cell.getDateCellValue();
		   // value = DateFormatConverter.
		}
		
		return value;
	}
	
}
