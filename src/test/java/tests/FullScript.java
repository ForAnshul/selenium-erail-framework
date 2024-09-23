package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FullScript {

	public boolean main(String sheetUsername, String sheetPwd, WebDriver driver) {
		boolean result = false;
		// Use WebDriverWait to wait until the username field is visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

		// Locate the username and password fields
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		// Enter credentials
		username.sendKeys(sheetUsername);
		password.sendKeys(sheetPwd);

		// Locate the login button and click it
		WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
		loginButton.click();

		// Optional: Validate login by checking if the Dashboard page loads
		try {
			// Wait for the dashboard to load
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']"))); // Basic wait for simplicity, ideally use WebDriverWait
			List<WebElement> dashboardList = driver.findElements(By.xpath("//h6[text()='Dashboard']"));

			// Verify the dashboard page
			if (dashboardList.size() > 0) {
				System.out.println("Login successful. Dashboard is visible.");
				result = true;
			} else {
				System.out.println("Login failed. Dashboard is not visible.");
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
