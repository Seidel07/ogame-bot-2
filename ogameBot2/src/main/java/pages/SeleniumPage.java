package pages;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import parameters.Parameters;

public class SeleniumPage {
	
	private static final Logger LOGGER = Logger.getLogger(SeleniumPage.class.getName());
	
	public SeleniumPage() {
		driver = this.openDriver();
		PageFactory.initElements(SeleniumPage.driver, this);
	}
	
	protected static WebDriver driver;
	Parameters parameters = new Parameters();
	
	public boolean webElementIsDisplayedInPage(By by) {
		try {
			driver.findElement(by).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean webElementIsDisplayedInElement(WebElement webElement, By by) {
		try {
			webElement.findElement(by).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void waitUntilElementExistsInPage(By by, long timeOutInMillis) {
		Long startTime = Calendar.getInstance().getTimeInMillis();
		while(!this.webElementIsDisplayedInPage(by)) {
			if (Calendar.getInstance().getTimeInMillis() - startTime > timeOutInMillis) {
				LOGGER.info("The element was not found. Make a timeout exception asshole... Logged");
				break;
			}
		}
	}
	
	public void waitUntilElementExistsInElement(WebElement webElement, By by, long timeOutInMillis) {
		Long startTime = Calendar.getInstance().getTimeInMillis();
		while(!this.webElementIsDisplayedInElement(webElement, by)) {
			if (Calendar.getInstance().getTimeInMillis() - startTime > timeOutInMillis) {
				LOGGER.info("The element was not found. Make a timeout exception asshole... Logged");
				break;
			}
		}
	}
	
	public void getBaseUrl() {
		driver.get(parameters.getBaseUrl());
	}
	
	private WebDriver openDriver() {
		if (driver == null) {
			return new ChromeDriver();
		} else {
			return driver;
		}
	}
	
	public void closeDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	public static void addScreenshot() {
		String path;
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		path = "./target/screenshots/" + "ogame.png";
		try {
			FileUtils.copyFile(scrFile, new File(path));
			FileUtils.copyFile(scrFile, new File("output/" + File.separator + "OGAME" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnButton(WebElement button) {
		boolean flag = false;
		while (!flag) {
			try {
				flag = true;
				button.click();
			} catch (Exception e) {
				flag = false;
			}
		}
	}

	
}
