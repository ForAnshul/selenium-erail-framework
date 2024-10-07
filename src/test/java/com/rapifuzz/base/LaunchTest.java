package com.rapifuzz.base;

import java.time.Duration;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchTest {

	private static final Logger logger = Logger.getLogger(LaunchTest.class.getName());

    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        // Initialize the WebDriver based on the browser parameter
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://erail.in");
        logger.info("setUp execution finished.");
    }

	
	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
}
