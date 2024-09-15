package automationProject.SeleniumTestNgFramework;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import automationProject.TestComponent.BaseTest;
import pageObject.CartPage;
import pageObject.ProductCatalogue;

public class ErrorValidation extends BaseTest {

	// TODO Auto-generated method stub
	String productName = "ZARA COAT 3";
	String location = "india";

	@Test
	public void testofApplication() throws IOException {

		page.loginToApplication("anshikaaa@gmail.com", "Iamking@000");

		Assert.assertEquals("Incorrect email or password.", page.getErrorMessage());

	}

	@Test
	public void ErrorValidationofaddtoCart() {
		ProductCatalogue cataloguepage = page.loginToApplication("anshika@gmail.com", "Iamking@000");
		 cataloguepage.addProductToCart(productName);
		 CartPage cartpage =cataloguepage.getToCartPage();
		boolean product=cartpage.VerifyProductDetail("ZARA COAT 33");
		Assert.assertEquals("ZARA COAT 33", product);
	}
}
