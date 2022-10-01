package com.amazon.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.amazon.qa.util.TestUtil;

import freemarker.template.SimpleDate;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static Logger log;
	
	public TestBase() throws IOException
	{
		prop = new Properties();
		try {
			 FileInputStream fip = new FileInputStream("C:\\Users\\Dell\\eclipse-workspace\\Amazon\\src\\main\\java"
					+ "\\com\\amazon\\qa\\config\\config.properties");
			prop.load(fip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void initilization()
	{
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//ystem.setProperty("webdriver.chrome.driver", "F:\\\\Softwares\\\\ChromeDriverforselenium\\\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		log = Logger.getLogger(TestBase.class);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.Page_Load_Timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.Implicitly_Wait,TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		driver.get(prop.getProperty("url"));
	}
	
	
	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir")+"\\Screenshots\\"+testCaseName +"_" + date +".png";
		FileUtils.copyFile(src, new File(destinationFilePath));
		return destinationFilePath;
	}
}
