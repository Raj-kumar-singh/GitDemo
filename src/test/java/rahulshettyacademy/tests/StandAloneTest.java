package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// Global wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();

		// navigate to site
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage lP = new LandingPage(driver);

		// enter userName, passWord and click on login
		driver.findElement(By.id("userEmail")).sendKeys("frameworkdesign@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@12345");
		driver.findElement(By.id("login")).click();

		// collect all the product
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		//Java Streams - select the desired product and click on the 'add to cart' button
		WebElement desiredProduct = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		desiredProduct.findElement(By.cssSelector(".btn.w-10.rounded")).click();
		
		//wait until toast message is displayed and loading animation is gone 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//click on the cart button
		driver.findElement(By.cssSelector("[routerlink*='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
		driver.findElement(By.xpath("//*[@class='totalRow']/button")).click();
		
		Actions a = new Actions(driver);
		
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='ta-item list-group-item ng-star-inserted']")));
		
		driver.findElement(By.xpath("//*[@class='ta-item list-group-item ng-star-inserted'][2]")).click();
		
		Thread.sleep(3000);
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		

	}

}
