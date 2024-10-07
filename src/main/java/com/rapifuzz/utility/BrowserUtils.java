package com.rapifuzz.utility;

import java.time.Duration;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BrowserUtils {
private static final Logger logger = Logger.getLogger(BrowserUtils.class.getName());
	
	

	public static void waitForElement(WebDriver driver, By findBy) {
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		  wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		}
	
	
	public static ExtentReports getExtentReports() {
		
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		logger.info(" path == " +  path);
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Report");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Anshul");
		return extent;
		
	}

}
