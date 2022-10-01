package com.amazon.qa.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.Search;
import com.amazon.qa.util.TestUtil;

public class SearchTest extends TestBase
{
	public Search s;
	public TestUtil testUtil;
	SoftAssert softAssert = new SoftAssert();
	
	public SearchTest() throws IOException 
	{
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException, IOException
	{
		initilization();
		s = new Search();
		testUtil = new TestUtil();
	}
	
	@Test
	public void enterSearchCriteriaTest() throws InterruptedException
	{
		String productName;
		String actualPrice;
		String offerPrice;
		String brand;
		String deliveryDetails;
		
		s.enterSearchCriteria("Wrist Watches");
		testUtil.scrollToElement(s.selectDisplayType.get(0));
		s.selectDisplayType.get(0).click();
		testUtil.explicitWait(s.selectBrandMaterial, 20);
		testUtil.scrollToElement(s.selectBrandMaterial);
		s.selectBrandMaterial.click();
		s.selectTitanBrand.click();
		testUtil.explicitWait(s.offer, 10);
		s.offer.click();
		Thread.sleep(3000);

		
		//Verify results are displaying (or) not
		
		softAssert.assertTrue(s.getResultText.isDisplayed(), "Results are not available");
		String resultsCount = s.getTotalResultsCount.getText().split("of")[1].split("results")[0].trim();
		int totalSerachResults = Integer.parseInt(resultsCount);
		softAssert.assertTrue(totalSerachResults > 0, "No results availble for the search criteria.");
		
		
		int count = s.getSearchResults.size();
		log.info("Total count of results in 1st page is: " + count);
		softAssert.assertEquals(count, 48, "First page is showing less number of results");
		System.out.println(s.searchBox.getText());
		softAssert.assertTrue(s.searchBox.getAttribute("value").equalsIgnoreCase("wrist watches"), "Searching with wrong text");
		for(int i = 0; i < count; i++)
		{
			testUtil.scrollToElement(s.getProductName.get(i));
			productName = s.getProductName.get(i).getText();
			actualPrice = s.getActualPrice.get(i).getText().split("₹")[1];
			offerPrice = s.getOfferPrice.get(i).getText().split("₹")[1];
			brand = s.getProductTitleFromSearchResults.get(i).getText();
			deliveryDetails = s.getDeliveryDetials.get(i).getText();
			if (i == 4 || i == 9 || i == 14)
			{
				//System.out.println("Product Name is: " + productName + ". Actual price of the product is: " + actualPrice + ". Offer price is: " + offerPrice);
				log.info("Product Name is: " + productName + ". Actual price of the product is: " + actualPrice + ". Offer price is: " + offerPrice);
				softAssert.assertTrue(productName.contains("Analog"), "Product name not conatins Analog for " + productName);
				softAssert.assertTrue(productName.contains("Watch"), "Product name not conatins Watch for " + productName);
				softAssert.assertTrue(brand.equalsIgnoreCase("Titan"), "Fetched differnt Brand product and the name of the Feched brand is: " + brand);
				softAssert.assertTrue(deliveryDetails.contains("FREE Delivery by Amazon"), productName +" is not elligible for free delivery by Amazon");
			}
		}
		
		//Scrolling to top of the page
		testUtil.scrollToElement(s.selectTitanBrand);
		
		softAssert.assertTrue(s.selectTitanBrand.findElement(By.xpath("//label/input")).isSelected(), "Ttan Brand option is not selected but we have provided value as Titan Brand to select from the list");
		softAssert.assertTrue(s.selectDisplayType.get(0).findElement(By.xpath("//label/input")).isSelected(), "Analog display option is not selected but we have provided value as Analog display to select from the list");
		softAssert.assertTrue(s.selectBrandMaterial.findElement(By.xpath("//label/input")).isSelected(), "Leather Material option is not selected but we have provided value as Leather Material to select from the list");
		softAssert.assertTrue(s.offer.getAttribute("class").contains("a-text-bold"), "25% off or more option is not selected from the list but we have provided value as 25% off or more to select the from the available options");
		softAssert.assertAll();
		
	}
	
	
	@AfterTest 
	public void tearDown() { 
		driver.quit();
	}
}
