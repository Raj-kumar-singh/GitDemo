package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {

		ProductCatalogue productCatalogue = landingPage.LoginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	} 

	// To verify that ZARA COAT 3 is displaying in order page
	
	  @Test(dependsOnMethods = { "submitOrder" }) 
	  public void OrderHistoryTest() {
	  
	  ProductCatalogue productCatalogue = landingPage.LoginApplication("frameworkdesign@gmail.com", "Test@12345");
	  OrderPage ordersPage = productCatalogue.goToOdersPage();
	  Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	  
	  }
	  
	  
	  
	  @DataProvider
	  public Object[][] getData() throws IOException {
		  
//		  HashMap<String, String> map = new HashMap<String, String>();
//		  map.put("email", "frameworkdesign@gmail.com");
//		  map.put("password", "Test@12345");
//		  map.put("product", "ZARA COAT 3");
//		  
//		  HashMap<String, String> map1 = new HashMap<String, String>();
//		  map1.put("email", "frameworkdesign1@gmail.com");
//		  map1.put("password", "Test@12345");
//		  map1.put("product", "ADIDAS ORIGINAL");
		  
		  List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		  return new Object[][] {{data.get(0)}, {data.get(1)}};
	  }
	  
//	  @DataProvider
//	  public Object[][] getData(){
//		  
//		  return new Object[][] {{"frameworkdesign@gmail.com", "Test@12345", "ZARA COAT 3"}, {"frameworkdesign1@gmail.com", "Test@12345", "ADIDAS ORIGINAL"}};
//	  }
	 

}
