package com.rapifuzz.utility;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelSheet {

	public List<String> readExcel(String fileName) throws Exception {
		List<String> expectedStations = new ArrayList<>();
		FileInputStream fis = new FileInputStream(fileName);
		@SuppressWarnings("resource")
		Workbook workbook1 = new XSSFWorkbook(fis);
		Sheet sheet1 = workbook1.getSheetAt(0);
		Iterator<Row> rows = sheet1.iterator();
		while (rows.hasNext()) {
			Row r = rows.next();
			String value = r.getCell(0).getStringCellValue();
			// System.out.println(value);
			expectedStations.add(value.trim());
		}
		return expectedStations;
	}

}
