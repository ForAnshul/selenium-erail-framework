package tests;

import org.testng.annotations.Test;
import com.rapifuzz.pages.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class FullScriptTest {
	private static final Logger logger = Logger.getLogger(LandingPage.class.getName());
	WebDriver driver = null;
	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		logger.info("Launch Finished");
	}    
	@DataProvider(name = "data")
	public Object[][] dp() {
		
		String filePath = "LoginData.xlsx";
        String sheetName = "Sheet1";

        Object[][] excelData = ExcelData.getExcelData(filePath, sheetName);
        return excelData;
		
		//Existing code
		//TODO: Do to time constraint, providing data here only. But this can be improvised by reading data from excel also.
		/*
		 * return new Object[][] { new Object[] { " " + "", " " }, new Object[] { "Abn"
		 * + "", "passabn" }, new Object[] { "Admin" + "", "admin123" }, };
		 */
}

	@Test(dataProvider = "data", dataProviderClass = FullScriptTest.class)
	public void mainTest(String user, String password) {
		//Existing code
		System.out.println(user + " " + password);
		FullScript fs = new FullScript();
		boolean result = fs.main(user, password, driver);
		Assert.assertTrue(result);
	}
	
	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	} 
}






