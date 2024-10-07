package com.rapifuzz.utility;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

public class WriteExcelSheet {
	
	private static final String fileName = "Actual_Stations.xlsx";

	public static boolean writeExcel(List<WebElement> elements) {
		boolean result = true;
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Actual_Stations");
		
		List<String> actualStations = new ArrayList<>();
		// Iterate through the list and write to Excel
		for (int i = 0; i < elements.size(); i++) {
			WebElement element = elements.get(i);
			String text = element.getText();

			// Create a new row in Excel and write the text in the first column
			Row row = sheet.createRow(i);
			Cell cell = row.createCell(0);
			cell.setCellValue(text);
			actualStations.add(text.trim());
			// System.out.println(text);
		}
		try {
		// Write the data to an Excel file
		FileOutputStream fileOut = new FileOutputStream(fileName);
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
		} catch(Exception e) {
			result = false;
		}
		return result;
	}

}
