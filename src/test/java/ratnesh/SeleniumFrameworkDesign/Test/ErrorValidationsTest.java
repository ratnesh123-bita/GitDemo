package ratnesh.SeleniumFrameworkDesign.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import ratnesh.SeleniumFrameworkDesign.TestComponent.BaseTest;
import ratnesh.SeleniumFrameworkDesign.pageObject.CartPage;
import ratnesh.SeleniumFrameworkDesign.pageObject.CheckoutPage;
import ratnesh.SeleniumFrameworkDesign.pageObject.ConfirmationPage;
import ratnesh.SeleniumFrameworkDesign.pageObject.LoginPage;
import ratnesh.SeleniumFrameworkDesign.pageObject.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	
	
	@Test
	public void submitOrder() throws IOException {
		
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = loginPage.loginApplication("ratan.glbian@gmail.com1", "Ratan@123");	
	}
	@Test
	public void productErrorValidation() throws IOException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = loginPage.loginApplication("ratan.glbian@gmail.com", "Ratan@123");	
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
