package testwithselinuim;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExcelFile {

	HSSFWorkbook workbook;
	HSSFSheet sheet;
	
	public ExcelFile(String filePath) {
		try {
			File excelFile = new File(filePath);
			FileInputStream stream = new FileInputStream(excelFile);
			workbook = new HSSFWorkbook(stream);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public String getData(int sheetNumber, int row, int column) {
		sheet = workbook.getSheetAt(sheetNumber);
		String data = sheet.getRow(row).getCell(column).getStringCellValue();
		return data;
	}
	
	/*
	 * public int getRowCount(int sheetIndex) { int row =
	 * workbook.getSheetAt(sheetIndex).getLastRowNum(); row++; return row; }
	 */
}
