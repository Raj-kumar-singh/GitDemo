package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//*[@class='totalRow']/button")
	WebElement checkoutele;
	
	public Boolean verifyProductDisplay(String productName) {
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckout() {
		checkoutele.click();
		return new CheckOutPage(driver);
	}

}
