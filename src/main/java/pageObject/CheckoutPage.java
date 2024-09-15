package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("india");
	@FindBy(css="[placeholder='Select Country']")
	WebElement dropdown;
//	List<WebElement>dropdown=driver.findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));
	@FindBy(css=".ta-item.list-group-item.ng-star-inserted")
	List<WebElement> dropdownList;
	
//	driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	WebElement placeorder;
	
	By itemList=By.cssSelector(".ta-item.list-group-item.ng-star-inserted");
	public void SelectDropdown(String name) {
		dropdown.sendKeys(name);
		WaitForElementToAppear(itemList);
		for(WebElement elements:dropdownList) {
			String nameLocation = elements.getText();
			if(nameLocation.equalsIgnoreCase(name)) {
				elements.click();
			}
	
	}

}
	public ConfirmationPage submit() {
		placeorder.click();
	   return new ConfirmationPage(driver);
	}
}
