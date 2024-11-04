package org.sdet.myProject;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sdet.myProject.TestComponents.BaseTest;
import org.sdet.myProject.TestComponents.Retry;
import org.sdet.myProject.pageObject.CartPage;
import org.sdet.myProject.pageObject.CheckoutPage;
import org.sdet.myProject.pageObject.ConfirmationPage;
import org.sdet.myProject.pageObject.LandingPage;
import org.sdet.myProject.pageObject.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTest extends BaseTest {

       @Test(groups= {"Error Handling"},retryAnalyzer=Retry.class) // for Flaky test to re-run 
       public void loginErrorValidation() throws IOException
       {

		String productName = "ZARA COAT 3";
		String countryName = " India";
        landingPage.appLogin("rahi@gmail.com", "Mom1966");
       Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
       }
       
       @Test
       public void productErrorValidation() throws IOException
       {

		String productName = "ZARA COAT 3";
		String countryName = " India";
        ProductCatalogue productCatalogue =landingPage.appLogin("rahi1@gmail.com", "Mom1966@");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartpage=productCatalogue.goToCartPage();
		boolean match = cartpage.verifyProductDisplay("Zara COAT 33");
		Assert.assertFalse(match);
		
       }
	}


