package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//cucumber-> TestNG, Junit
@CucumberOptions(features="src/test/java/cucumber",glue="rahushettyacademy.stepDefinitions", monochrome=true,tags="Regression",plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
	
}
