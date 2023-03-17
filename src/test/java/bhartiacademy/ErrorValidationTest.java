package bhartiacademy;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import bhartiacademy.TestComponents.BaseTest;
import bhartiacademy.TestComponents.Retry;
import bhartiacakdemy.pageobjects.CartPage;
import bhartiacakdemy.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

	@Test (groups = {"ErrorHandling"},retryAnalyzer= Retry.class) 
	public void LoginErrorValidation() {
		
		
		lp.loginApplication("ik@gmail.com", "Cap10");
		Assert.assertEquals("Incorrect email  password.", lp.getErrorMessage());
	}

	@Test
	public void ProductErrorValidation() throws InterruptedException {
		String requiredProduct = "ZARA COAT 3";
		ProductCatalogue pc = lp.loginApplication("rahulshetty@gmail.com", "Capital@10");

		List<WebElement> productList = pc.getProductList();
		pc.getProductByName(requiredProduct);
		pc.addProductToCart(requiredProduct);
		CartPage cp = pc.goToCartPage();
		Boolean match = cp.verifyProductDisplay("ZARA jhj");
		Assert.assertFalse(match);
	}
}
