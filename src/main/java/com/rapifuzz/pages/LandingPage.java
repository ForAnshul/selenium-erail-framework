package com.rapifuzz.pages;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rapifuzz.constants.Constant;


public class LandingPage {


	private static final Logger logger = Logger.getLogger(LandingPage.class.getName());
	    
	    WebDriver driver;

	    // Constructor
	    public LandingPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    //Page Factory-WebElements
	    @FindBy(id = "txtStationFrom")
	    WebElement searchTextbox;
	    
	    @FindBy(xpath = "//div[@class='autocomplete']/div/div[1]")
	    List<WebElement> elements;
	    
	  
	    @FindBy(xpath = "//input[@title='Select Departure date for availability']")
	    WebElement fromDateElement;
	    
	    @FindBy(id = "buttonFromTo")
	    WebElement getTrains;
	    
	    
	    //Actions
		public boolean searchStation() {
			boolean result = false;
			searchTextbox.clear();
			searchTextbox.sendKeys(Constant.DEL_STATION_CODE);
			if (elements.size() > 3) {
				logger.info("4th Station is " + elements.get(3).getText());
				result = true;
			}
			return result;
		}
		
		public boolean compareExcel(List<String> actualStations, List<String> expectedStations) {
			boolean result = true;
			for (String expectedStation : expectedStations) {
				logger.info(expectedStation);
				if (actualStations.contains(expectedStation)) {
					logger.info(expectedStation + " is available.");
				} else {
					logger.info(expectedStation + " is not available.");
					result = false;
				}
			}
			return result;
		}
		
		public boolean calendarSelect() throws Exception {
			boolean result = true;
			// Get the current date
	        LocalDate currentDate = LocalDate.now();

	        // Add 30 days to the current date
	        LocalDate newDate = currentDate.plusDays(30);

	        // Define the desired date format
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DD_MMM_YYYY__E_FORMAT);

	        // Format the new date
	        String formattedDate = newDate.format(formatter);

	        logger.info("formattedDate = " +  formattedDate);
	        long toEpochMilli = newDate.atStartOfDay(ZoneId.systemDefault()) // Start of the day at the system's time zone
	                .toInstant()
	                .toEpochMilli();
	        logger.info(""+toEpochMilli);
	        fromDateElement.click();
	        try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	            result = false;
	        }
	        // Create an instance of JavascriptExecutor
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        String javaScriptFunc = "DoDateSelect(" + toEpochMilli + ");";
	        // Call a JavaScript function (example)
	        js.executeScript(javaScriptFunc);
	        //Thread.sleep(1000);
	       // getTrains.click();
			return result;
			
			
		}
		
		public List<WebElement> getAllStations() {
			return elements;
		}

	
		
		
}
