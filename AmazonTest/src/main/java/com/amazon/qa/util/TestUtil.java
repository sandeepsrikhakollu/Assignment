package com.amazon.qa.util;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.qa.base.TestBase;

public class TestUtil extends TestBase
{
	
	public TestUtil() throws IOException {
		super();
	}
	public static long Page_Load_Timeout = 30;
	public static long Implicitly_Wait = 20;
	
	public void switchToFrame()
	{
		driver.switchTo().frame(1);
	}
	
	public void scrollToElement(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoViewIfNeeded()", element);
	}
	
	public void scrollUp()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, -1000)", "");
	}
	
	public void scrollDown()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, 1000)", "");
	}

	
	
	public void explicitWait(WebElement element, int seconds)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
	}
	
	public void isFound()
	{
		
	}
	

}
