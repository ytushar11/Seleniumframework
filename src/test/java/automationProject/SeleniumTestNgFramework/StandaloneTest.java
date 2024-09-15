package automationProject.SeleniumTestNgFramework;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automationProject.TestComponent.BaseTest;
import pageObject.CartPage;
import pageObject.CheckoutPage;
import pageObject.ConfirmationPage;
import pageObject.OrderPage;
import pageObject.ProductCatalogue;

public class StandaloneTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getdata")
	public void testofApplication(HashMap<String,String> input) throws IOException {

		String location = "india";
		ProductCatalogue cataloguepage = page.loginToApplication(input.get("email"), input.get("password"));

//		List<WebElement>list=cataloguepage.getMeTheList();
		cataloguepage.addProductToCart(input.get("product"));
		CartPage cartpage = cataloguepage.getToCartPage();
		cartpage.VerifyProductDetail(input.get("product"));
		cartpage.getToCartPage();
		CheckoutPage checkoutpage = cartpage.checkout();
		checkoutpage.SelectDropdown(location);
		ConfirmationPage orderconfrimation = checkoutpage.submit();

		String confText = orderconfrimation.getTextOfOrderpage();
		System.out.println(confText);

		Assert.assertTrue(confText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "testofApplication" })
	public void orderHistory() {

		ProductCatalogue cataloguepage = page.loginToApplication("anshika@gmail.com", "Iamking@000");
		OrderPage orderpage = cataloguepage.getToOrdertPage();
		Assert.assertTrue(orderpage.VerifyOrderDetail(productName));
	}

	@DataProvider
	public Object[][] getdata() throws IOException {
		
	
		List<HashMap<String,String>> data =getJSONData("C://Users//Tushar Yadav//eclipse-workspace//SeleniumTestNgFramework//src//test//java//automationProject//UtilityforJson//dataResource.json");
		return new Object[][] { {data.get(0)},{data.get(1) } };
	}
}
//HashMap<String, String>map= new HashMap<>();
//map.put("email", "anshika@gmail.com");
//map.put("password", "Iamking@000");
//map.put("product", "ZARA COAT 3");
//
//HashMap<String, String>map1= new HashMap<>();
//map1.put("email", "shetty@gmail.com");
//map1.put("password", "Iamking@000");
//map1.put("product", "ADIDAS ORIGINAL");
