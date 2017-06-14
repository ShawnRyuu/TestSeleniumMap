package com.baidumap;
import java.util.concurrent.TimeUnit;

//import org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
//import org.testng.Assert;
import org.testng.annotations.Test;

public class MapTest01 {
	private WebDriver driver;
  
    @FindBy(css = "#div#u1 a:nth-of-type(3)")
    private WebElement mapLink;

    @Test
    public void verifySearchButton() {
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.get("http://www.baidu.com/");
	  //String search_text = "Google Search";
	  //WebElement search_button = driver.findElement(By.name("btnK"));
	  
        // Please refer to the WebElement above using @FindBy.
        // WebElement mapLink = driver.findElement(By.cssSelector("div#u1 a:nth-of-type(3)"));
        mapLink.click();
	  
	  WebElement routeLink = driver.findElement(By.cssSelector("div.searchbox-content-button.right-button.route-button.loading-button")); 
	  routeLink.click();
	  
	  WebElement changeCity = driver.findElement(By.cssSelector("a.ui3-city-change-inner.ui3-control-shadow"));
	  changeCity.click();
	  
	  WebElement selectCity = driver.findElement(By.cssSelector("div#selCityHotCityId a:nth-of-type(2)")); 
	  selectCity.click();
	  
        String startFrom = "天安门";
        String destinationTo = "国贸";
	  
	  WebElement startInput = driver.findElement(By.cssSelector("input.route-start-input.input-iploc"));
	  startInput.clear(); 
      startInput.sendKeys(startFrom); 

      
      WebElement goalInput = driver.findElement(By.cssSelector("input.route-end-input"));
      goalInput.clear(); 
      goalInput.sendKeys(destinationTo); 
      
      WebElement walkTo = driver.findElement(By.cssSelector("div.tab-item.bike-tab")); 
      walkTo.click();
      
      WebElement searchRoute = driver.findElement(By.cssSelector("button#search-button")); 
      searchRoute.click();
      
      WebElement chooseRoute = driver.findElement(By.cssSelector("div.selInfoWndBtn")); 
      chooseRoute.click();
      
      
      
	  
	  }
  @BeforeClass
  public void beforeClass() {
	 // driver = new FirefoxDriver();
	  System.setProperty("webdriver.gecko.driver", "C:\\gecko\\geckodriver.exe");
	  driver =new FirefoxDriver();
  }

  @AfterClass
  public void afterClass() {
	 // driver.quit();
  }

}
