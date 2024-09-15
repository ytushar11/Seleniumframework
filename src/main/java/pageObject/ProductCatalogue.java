package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> list = driver.findElements(By.cssSelector(".card-body"));

	@FindBy(css = ".card-body")
	List<WebElement> Lproduct;
	
	By productList = By.cssSelector(".card-body");
	By addcart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	By spinner=By.cssSelector(".ng-animating");
	
	public List<WebElement> getMeTheList() {
		WaitForElementToAppear(productList);
		return Lproduct;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getMeTheList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		getProductByName(productName).findElement(addcart).click();
		WaitForElementToAppear(toastMessage);
		WaitForElementToDisappear(spinner);
	
		
		
		
	}

}
