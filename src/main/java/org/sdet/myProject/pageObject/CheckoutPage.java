package org.sdet.myProject.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sdet.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	
	public CheckoutPage (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userName = driver.findElement(By.id("userEmail"));
	//page factory method

	
	@FindBy(css="[placeholder='Select Country']")
	WebElement placeholder;
	
	@FindBy(css=".action__submit")
	WebElement submitButton;
	
	@FindBy(xpath="(//button[contains (@class, 'ta-item')])[2]")
	WebElement dropdownEle;
	
	//By result = By.cssSelector(".ta-results");
	//By button = By.cssSelector(".action__submit");
	
	

	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		  a.sendKeys(placeholder, countryName).build().perform();
		  waitForWebElementToClickable(dropdownEle);
           dropdownEle.click();
		   
	}
	public ConfirmationPage submitOrder()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,300)");
		waitForWebElementToClickable(submitButton);
		submitButton.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}


}
