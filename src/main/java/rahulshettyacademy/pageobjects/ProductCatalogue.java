package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		
		
		//initialization code
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".btn.w-10.rounded");
	By toastMessage = By.id("toast-container");
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement desiredProduct = getProductList().stream()
		.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
		.orElse(null);
		return desiredProduct;
	}
	
	public void addProductToCart(String productName) {
		
		WebElement desiredProduct = getProductByName(productName);
		desiredProduct.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}

}
