package org.sdet.myProject.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sdet.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	
	public CartPage (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userName = driver.findElement(By.id("userEmail"));
	//page factory method
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	//By checkoutButton = By.cssSelector(".totalRow button");
	
	public Boolean verifyProductDisplay(String productName)
	{
		   
		   Boolean match =cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		   return match;
	}
	public CheckoutPage goToCheckout()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
		waitForWebElementToClickable(checkoutEle);
		checkoutEle.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	


}
