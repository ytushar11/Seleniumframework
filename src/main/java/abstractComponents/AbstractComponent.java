package abstractComponents;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.CartPage;
import pageObject.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	 driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement OrderHeader;
	
	By cartButton=By.id("toast-container");
	By toastMessage=By.cssSelector("#toast-container");
	
	public void WaitForElementToAppear(By FindBy) {
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(FindBy));
	}
	
	public void WaitForElementToDisappear(By Findby) {
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(Findby));
	}
	
	public void WaitWebElementToAppear(WebElement FindBy) {
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		 wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	public CartPage getToCartPage() {
		WaitForElementToDisappear(cartButton);
		cartHeader.click();
		CartPage cartpage= new CartPage(driver);
		return cartpage;
	}
	public OrderPage getToOrdertPage() {
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		OrderHeader.click();
		OrderPage orderpage= new OrderPage(driver);
		return orderpage;
		
	}

}
