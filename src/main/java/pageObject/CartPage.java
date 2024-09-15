package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	public CartPage(WebDriver driver) {
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
	By checkoutbutton= By.cssSelector(".totalRow button");
	
	
	 public boolean VerifyProductDetail(String productname) {
		 boolean match=productTitles.stream().anyMatch(cartP->cartP.getText().equalsIgnoreCase(productname)); 
		 return match;
		 
	 }
	 public CheckoutPage checkout() {
		 WaitForElementToAppear(checkoutbutton);
		 checkout.click();
		 
		 CheckoutPage checkoutpage = new  CheckoutPage(driver);
		 return checkoutpage;
	 }
}
