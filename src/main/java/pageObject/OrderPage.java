package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;
//	driver.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderHistory;
	
	By checkoutbutton= By.cssSelector(".totalRow button");
	
	
	 public boolean VerifyOrderDetail(String productname) {
		 
		 boolean match=orderHistory.stream().anyMatch(cartP->cartP.getText().equalsIgnoreCase(productname)); 
		 return match;
		 
	 }
	 
}
