package com.baidumap;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MapTest01 {
	private WebDriver driver;

	private String getUrl = "http://www.baidu.com/";

	private String startFrom = "天安门";

	private String destinationTo = "国贸";

	private String filePath = "c:\\com.baidumap\\";

	@FindBy(css = "a[name='tj_trmap']")
	private WebElement mapLink;

	@FindBy(css = "div.searchbox-content-button.right-button.route-button.loading-button")
	private WebElement routeLink;

	@FindBy(css = "a.ui3-city-change-inner.ui3-control-shadow")
	private WebElement changeCity;

	@FindBy(css = "div#selCityHotCityId a:nth-of-type(2)")
	private WebElement selectCity;

	//@FindBy(css = "span:contains(text(),'^北京市$')")
	private WebElement checkselectCity;

	@FindBy(css = "input.route-start-input")
	private WebElement startInput;

	@FindBy(css = "input.route-end-input")
	private WebElement goalInput;

	@FindBy(css = "div.tab-item.bike-tab")
	private WebElement walkTo;

	@FindBy(css = "button#search-button")
	private WebElement searchRoute;

	@FindBy(css = "div.selInfoWndBtn")
	private WebElement chooseRoute;

	@FindBy(css = "path")
	private WebElement mapMarker;

	@Test
	public void verifySearchButton() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(getUrl);

		PageFactory.initElements(driver, this);

		mapLink("01mapLink.png");

		routeLink("02routeLink.png");

		changeCity("03changeCity.png");

		selectCity("04selectCity.png");

		startInput(startFrom);

		goalInput(destinationTo);

		walkTo("05walkTo.png");

		searchRoute("06searchRoute.png");

		chooseRoute("07chooseRoute.png");
	}

	@BeforeClass
	public void beforeClass() {
		// driver = new FirefoxDriver();
		System.setProperty("webdriver.gecko.driver", "C:\\gecko\\geckodriver.exe");
		driver = new FirefoxDriver();
	}

	public void mapLink(String fn) {
		mapLink.click();
		simpleWait(routeLink);
		takesScreenShot(filePath + fn);
	}

	public void routeLink(String fn) {
		routeLink.click();
		simpleWait(changeCity);
		takesScreenShot(filePath + fn);
	}

	public void changeCity(String fn) {
		changeCity.click();
		simpleWait(selectCity);
		takesScreenShot(filePath + fn);
	}

	public void selectCity(String fn) {
		selectCity.click();
		checkselectCity = driver.findElement(By.xpath("//span[contains(text(),'北京市')]"));
		simpleWait(checkselectCity);
		takesScreenShot(filePath + fn);
	}

	public void startInput(String from) {
		startInput.clear();
		startInput.sendKeys(from);
	}

	public void goalInput(String to) {
		goalInput.clear();
		goalInput.sendKeys(to);
	}

	public void walkTo(String fn) {
		walkTo.click();
		takesScreenShot(filePath + fn);
	}

	public void searchRoute(String fn) {
		searchRoute.click();
		simpleWait(chooseRoute);
		takesScreenShot(filePath + fn);
	}

	public void chooseRoute(String fn) {
		chooseRoute.click();
		simpleWait(mapMarker);
		takesScreenShot(filePath + fn);
	}

	@AfterClass
	public void afterClass() {
		driver.close();
		driver.quit();
	}

	public void takesScreenShot(String fn) {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(fn));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void simpleWait(WebElement locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(locator));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
