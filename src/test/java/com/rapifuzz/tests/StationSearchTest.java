package com.rapifuzz.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rapifuzz.base.LaunchTest;
import com.rapifuzz.constants.Constant;
import com.rapifuzz.pages.LandingPage;
import com.rapifuzz.utility.ReadExcelSheet;
import com.rapifuzz.utility.WriteExcelSheet;

public class StationSearchTest extends LaunchTest {
	
	private String expectedXls = "Expected_Stations.xlsx";
	private String actualXls = "Actual_Stations.xlsx";

	@Test(priority = 1)
	public void testSearchStation() {
		LandingPage landingPage = new LandingPage(driver);
		// Perform search action
		boolean result = landingPage.searchStation();
		Assert.assertTrue(result);

	}
	
	@Test(priority = 2)
	public void testwriteExcel() {
		LandingPage landingPage = new LandingPage(driver);
		List<WebElement> stationList = landingPage.getAllStations();
		boolean result = WriteExcelSheet.writeExcel(stationList);
		Assert.assertTrue(result);

	}

	@Test(priority = 3)
	public void testCompareExcel() throws Exception {
		LandingPage landingPage = new LandingPage(driver);
		ReadExcelSheet readExcelSheet = new ReadExcelSheet();
		// Perform search action
		
		List<String> expectedStations = readExcelSheet.readExcel(expectedXls);
		List<String> actualStations = readExcelSheet.readExcel(actualXls);
		boolean result = landingPage.compareExcel(actualStations, expectedStations);
		Assert.assertTrue(result);
	}

	@Test(priority = 4)
	public void testCalendarSelect() throws Exception {

		LandingPage landingPage = new LandingPage(driver); // Perform search action
		boolean result = landingPage.calendarSelect();
		Assert.assertTrue(result);
	}

}
