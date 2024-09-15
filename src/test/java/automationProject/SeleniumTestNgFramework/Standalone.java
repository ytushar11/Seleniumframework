package automationProject.SeleniumTestNgFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.LandingPage;

public class Standalone {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		String productName="ZARA COAT 3";
		
		
		driver.manage().window().maximize();
		LandingPage page= new LandingPage(driver);
		page.goTopage();
		page.loginToApplication("anshika@gmail.com", "Iamking@000");
		
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".card-body")));
		List<WebElement> list = driver.findElements(By.cssSelector(".card-body"));
		WebElement prod = list.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		
		 prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		 driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match=cartProducts.stream().anyMatch(cartP->cartP.getText().equalsIgnoreCase(productName));
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("india");
		 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-item.list-group-item.ng-star-inserted")));
		
	List<WebElement>dropdown=driver.findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));
	
	for(WebElement elements:dropdown) {
		String name = elements.getText();
		if(name.equalsIgnoreCase("india")) {
			elements.click();
		}
		
	}
	driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".hero-primary")));
	String result =driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(result.equalsIgnoreCase("Thankyou for the order"));
	//dropdown.stream().filter(option->option.getText().equalsIgnoreCase("india")).;

	}

}
