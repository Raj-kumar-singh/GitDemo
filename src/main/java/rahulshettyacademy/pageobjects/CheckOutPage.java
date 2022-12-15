package rahulshettyacademy.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "//*[@class='ta-item list-group-item ng-star-inserted'][2]")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement submit;
	
	By results = By.xpath("//*[@class='ta-item list-group-item ng-star-inserted']");

	public void selectCountry(String countryName) {

		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		
		//submit.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit);
		return new ConfirmationPage(driver);
	}

}
