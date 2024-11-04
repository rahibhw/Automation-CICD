package StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.sdet.myProject.TestComponents.BaseTest;
import org.sdet.myProject.pageObject.CartPage;
import org.sdet.myProject.pageObject.CheckoutPage;
import org.sdet.myProject.pageObject.ConfirmationPage;
import org.sdet.myProject.pageObject.LandingPage;
import org.sdet.myProject.pageObject.ProductCatalogue;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplimentation extends BaseTest {
	public LandingPage landingPage;
	ProductCatalogue productCatalogue;
	ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		
		landingPage =launchApplication();
		}
	@Given("^Logged in with my username (.+) and password (.+)$")
	public void Logged_in_with_my_username_and_password(String username, String password) {
		productCatalogue =landingPage.appLogin(username, password);
		}
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) 
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		
	}

	@When("^Checkout (.+) and submit the order$")
	public void  checkout_and_submit_the_order(String productName)
	{
		CartPage cartpage=productCatalogue.goToCartPage();
		boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartpage.goToCheckout();
		checkoutPage.selectCountry("India");
	    confirmationPage =checkoutPage.submitOrder();
	}
	
	@Then("Confirmation message (.+) is displayed on confirmation page")
	public void Confirmation_message_is_displayed_on_confirmation_page(String comfirmMessage)
	{
		String confirmText = confirmationPage.getConfirmationText();
		Assert.assertTrue(confirmText.equalsIgnoreCase(comfirmMessage));
	}
	@Then("{string} message is displayed")
	public void message_is_displayed(String string)
	{
		Assert.assertEquals(string, landingPage.getErrorMessage());
	}
	
	
	
	
	
	
	

}
