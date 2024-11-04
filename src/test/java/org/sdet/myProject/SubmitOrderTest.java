package org.sdet.myProject;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sdet.myProject.TestComponents.BaseTest;
import org.sdet.myProject.pageObject.CartPage;
import org.sdet.myProject.pageObject.CheckoutPage;
import org.sdet.myProject.pageObject.ConfirmationPage;
import org.sdet.myProject.pageObject.LandingPage;
import org.sdet.myProject.pageObject.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {

       @Test(dataProvider="getData", groups={"purchase"})
       public void submitOrder(HashMap<String, String> input) throws IOException
       {
		String countryName = " India";
        ProductCatalogue productCatalogue =landingPage.appLogin(input.get("email"),input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartpage=productCatalogue.goToCartPage();
		boolean match = cartpage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartpage.goToCheckout();
		checkoutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage =checkoutPage.submitOrder();
		String confirmText = confirmationPage.getConfirmationText();
		Assert.assertTrue(confirmText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
       }
 
       @DataProvider
       public Object[][] getData() throws IOException
       {

    	   List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\org\\sdet\\myProject\\Data\\purchase.json");
    	   
    	  return new Object [][]  {{data.get(0)}, {data.get(1)}};
       }
	}



//HashMap<String,String> map = new HashMap<String,String>();
//map.put("email", "rahi@gmail.com");
//map.put("password", "Mom@1966");
//map.put("product", "ZARA COAT 3");

//HashMap<String,String> map1 = new HashMap<String,String>();
//map1.put("email", "rahi1@gmail.com");
//map1.put("password", "Mom1966@");
//map1.put("product", "ADIDAS ORIGINAL");
