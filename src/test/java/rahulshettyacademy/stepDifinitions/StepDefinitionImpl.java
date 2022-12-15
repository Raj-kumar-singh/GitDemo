package rahulshettyacademy.stepDifinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.LoginApplication(username, password);		
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName)
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);

		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
		confirmationPage = checkOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmationPage")
	public void message_is_displayed_on_confirmation(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}
}
