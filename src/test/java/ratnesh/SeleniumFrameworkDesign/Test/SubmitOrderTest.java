package ratnesh.SeleniumFrameworkDesign.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import ratnesh.SeleniumFrameworkDesign.TestComponent.BaseTest;
import ratnesh.SeleniumFrameworkDesign.pageObject.CartPage;
import ratnesh.SeleniumFrameworkDesign.pageObject.CheckoutPage;
import ratnesh.SeleniumFrameworkDesign.pageObject.ConfirmationPage;
import ratnesh.SeleniumFrameworkDesign.pageObject.LoginPage;
import ratnesh.SeleniumFrameworkDesign.pageObject.OrderPage;
import ratnesh.SeleniumFrameworkDesign.pageObject.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";
	
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
//		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));	
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationpage = checkoutPage.submitOrder();	
		String confirmmsg = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	
	public void OrderHistoryTest()
	{
		//ZARA COAT 3"
		ProductCatalogue productCatalogue = loginPage.loginApplication("ratan.glbian@gmail.com", "Ratan@123");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	@DataProvider
	
	public Object[][] getData() throws IOException
	{ 
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\ratnesh\\SeleniumFrameworkDesign\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
}


//HashMap<String, String> map = new HashMap<String, String>();
//map.put("email", "ratan.glbian@gmail.com");
//map.put("password", "Ratan@123");
//map.put("product", "ZARA COAT 3");
//
//HashMap<String, String> map1 = new HashMap<String, String>();
//map1.put("email", "ratan.glbian@gmail.com");
//map1.put("password", "Ratan@123");
//map1.put("product", "ADIDAS ORIGINAL");
