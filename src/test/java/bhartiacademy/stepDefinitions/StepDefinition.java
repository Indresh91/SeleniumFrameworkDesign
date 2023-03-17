package bhartiacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import bhartiacademy.TestComponents.BaseTest;
import bhartiacakdemy.pageobjects.CartPage;
import bhartiacakdemy.pageobjects.CheckoutPage;
import bhartiacakdemy.pageobjects.LandingPage;
import bhartiacakdemy.pageobjects.OrderConfirmationPage;
import bhartiacakdemy.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue pc; 
	public OrderConfirmationPage orderCp; 
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException 
	{
		landingPage = launchApplication();  
	}
	
	@Given("^Logged in with the user name (.+) and passowrd (.+)$")   // (.+) is a regular expression and to define it  starts with ^ and ends with $
	public void logged_in_username_password(String username, String password) 
	{
		pc = lp.loginApplication(username,password);
	}
	@When("^I add (.+) to the cart$")
	public void add_to_cart(String requireProduct) throws InterruptedException 
	{
		List<WebElement> productList = pc.getProductList();
		//pc.getProductByName(requireProduct);
		pc.addProductToCart(requireProduct);
	}
		//And checkout <productName> and submit the order
		@And("^checkout (.+) and submit the order$")
		public void submit_order(String requiredProduct) 
		{
			CartPage cp = pc.goToCartPage();			
			Boolean match = cp.verifyProductDisplay(requiredProduct); 
			Assert.assertTrue(match);
			CheckoutPage chkPage = cp.goTocheckout();
			chkPage.selectCountry("ind","India");
		     orderCp = chkPage.placeOrder(); 
		}
		
		//Then "THANKYOU FOR THE ORDER." message is displayed on the screen
	@Then("{string} message is displayed on the screen")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmMationText = orderCp.verifyOrderConfirmation(); 
		Assert.assertTrue(confirmMationText.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_displayed(String string) 
	{
		Assert.assertEquals(string, lp.getErrorMessage());
		driver.close();
	}
	}

