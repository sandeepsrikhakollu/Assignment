package com.amazon.qa.pages;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.amazon.qa.base.TestBase;

public class Search extends TestBase
{

	@FindBy(xpath="//select[@id='searchDropdownBox']")
	public WebElement selectDropDown;
	
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	public WebElement searchBox;
	
	@FindBy(xpath="//div[@class='autocomplete-results-container']/div")
	public List<WebElement> searchAutoSuuggestions;
	
	@FindBy(xpath="//span[@data-component-type='s-search-results']/div[2]/div")
	public List<WebElement> searchResults;
	
	@FindBy(xpath="//*[@aria-labelledby='p_n_feature_seven_browse-bin-title']//*[contains(text(), 'Analogue')]/parent::a/div")
	public List<WebElement> selectDisplayType;
	
	@FindBy(xpath="//*[@aria-labelledby='p_n_material_browse-title']//*[contains(text(), 'Leather')]/parent::a/div")
	public WebElement selectBrandMaterial;
	
	@FindBy(xpath="//*[@id='brandsRefinements']/ul/li/span")
	public List<WebElement> getAvailableBrands;
	
	@FindBy(xpath="//*[@id='brandsRefinements']/ul/li/span/a/div")
	public List<WebElement> selectBrand;
	
	@FindBy(xpath="//*[@id='brandsRefinements']//*[contains(text(), 'See more')]")
	public WebElement getSeeMoreOption;
	
	
	@FindBy(xpath="//*[@id='brandsRefinements']//*[contains(text(), 'See Less')]")
	public WebElement getSeeLessOption;
	
	@FindBy(xpath="//*[@id='brandsRefinements']//*[contains(text(), 'See')]")
	public WebElement getSeeMoreOrLessOption;
	
	@FindBy(xpath="//*[@id='brandsRefinements']//*[contains(text(), 'Titan')]/parent::a/div")
	public WebElement selectTitanBrand;
	
	
	@FindBy(xpath="//span[contains(text(), '25% Off or more')]")
	public WebElement offer;
	
	@FindBy(xpath="//*[@class='sg-col-inner']/parent::div[starts-with(@data-asin,'B')]")
	public List<WebElement> getSearchResults;
	
	@FindBy(xpath = "//*[@class='sg-col-inner']/parent::div[starts-with(@data-asin,'B')]//*[contains(@class, 'a-size-mini a-spacing-none')]")
	public List<WebElement> getProductName;
	
	@FindBy(xpath = "//*[@class='sg-col-inner']/parent::div[starts-with(@data-asin,'B')]//*[contains(@class, 'a-price a-text-price')]")
	public List<WebElement> getActualPrice;
	
	@FindBy(xpath = "//*[@class='sg-col-inner']/parent::div[starts-with(@data-asin,'B')]//*[(@class='a-price')]")
	public List<WebElement> getOfferPrice;
	
	@FindBy(xpath="//*[@class='sg-col-inner']/parent::div[starts-with(@data-asin,'B')]//*[@class='a-size-base-plus a-color-base']")
	public List<WebElement> getProductTitleFromSearchResults;
	
	@FindBy(xpath="//*[@class='a-row a-size-base a-color-secondary s-align-children-center']")
	public List<WebElement> getDeliveryDetials;
	
	
	@FindBy(xpath="//*[contains(text(), 'RESULTS')]")
	public WebElement getResultText;
	
	@FindBy(xpath = "(//*[@data-component-type='s-result-info-bar']//*[contains(@class, 'a-spacing-top-small')]/span)[1]")
	public WebElement getTotalResultsCount;
	
	
	public Search() throws IOException 
	{
		PageFactory.initElements(driver, this);
	}
	
	public void enterSearchCriteria(String name) throws InterruptedException
	{
		searchBox.sendKeys(""+name+"");
		Thread.sleep(2000);
		String result;
		for(int i=0; i<searchAutoSuuggestions.size(); i++)
		{
			result = searchAutoSuuggestions.get(i).getText();
			
			if(result.equalsIgnoreCase(name))
			{
				searchAutoSuuggestions.get(i).click();
			}
		}		
	}
	
}
