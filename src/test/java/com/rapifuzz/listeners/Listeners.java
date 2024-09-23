package com.rapifuzz.listeners;

import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



public class Listeners implements ITestListener {
	
	private static final Logger logger = Logger.getLogger(Listeners.class.getName());
	
	ExtentTest test;
	ExtentReports extent = com.rapifuzz.utility.BrowserUtils.getExtentReports();
	
	public void onTestStart(ITestResult result) {
		logger.info("onTestStart called of listener started");
		test = extent.createTest(result.getMethod().getMethodName());
		logger.info("onTestStart called of listener finished");
	}
	
	public void onTestSuccess(ITestResult result) {
		test.pass(result.getThrowable());
		test.log(Status.PASS, "Test Passed");
	  }
	
	public void onTestFailure(ITestResult result) {
		logger.info("onTestFailure called of listener started");
		test.fail(result.getThrowable());
	  }


	public void onFinish(ITestContext context) {
	    extent.flush();
	  }


}
