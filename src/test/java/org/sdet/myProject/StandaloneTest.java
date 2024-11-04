package org.sdet.myProject;

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
import org.sdet.myProject.pageObject.LandingPage;
import org.testng.Assert;

public class StandaloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";
     	WebDriver driver = new ChromeDriver();	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		LandingPage lp= new LandingPage(driver);
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("rahi@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Mom@1966");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		// finding shopping elements
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod=products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName))
		.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//ng-animating is class name for loading content 
	    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	    
	    driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		// item added to cart - find all 
	   List<WebElement> itemsInCart= driver.findElements(By.cssSelector(".cartSection h3"));
	   Boolean match =itemsInCart.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
	   Assert.assertTrue(match);
	   
	   //now checkout if boolean step is true passed
	   JavascriptExecutor js = (JavascriptExecutor)driver;
	   js.executeScript("window.scrollBy(0,1000)");
	   wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
	   driver.findElement(By.cssSelector(".totalRow button")).click();
	   // payment page filling card details and entering country and then grabbing order confirmation page
	   driver.findElement(By.cssSelector("div[class='field small'] input")).sendKeys("876");
	   //driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("ind");
	   Actions a = new Actions(driver);
	   //a.moveToElement(By.cssSelector("[placeholder='Select Country']")).click().sendKeys("ind").KeyDown(Keys.ARROW_DOWN).build().perform();
	   //driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
	   a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	   driver.findElement(By.xpath("(//button[contains (@class, 'ta-item')])[2]")).click();
	   js.executeScript("window.scrollBy(0,400)");
	   wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
	   driver.findElement(By.cssSelector(".action__submit")).click();
	   
	   String confirmtext=driver.findElement(By.cssSelector(".hero-primary")).getText();
	   Assert.assertTrue(confirmtext.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	   
		
	}

}

