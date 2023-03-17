package bhartiacademy;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bhartiacademy.TestComponents.BaseTest;
import bhartiacakdemy.pageobjects.CartPage;
import bhartiacakdemy.pageobjects.CheckoutPage;
import bhartiacakdemy.pageobjects.LandingPage;
import bhartiacakdemy.pageobjects.OrderConfirmationPage;
import bhartiacakdemy.pageobjects.OrderPage;
import bhartiacakdemy.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAlonePageFactoryTest extends BaseTest {
	String requiredProduct = "ZARA COAT 3";
	@Test(dataProvider= "getData", groups= {"purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String countryInput = "ind";
		String countryName = "India"; 
		//LandingPage lp = launchApplication();  -- For this we added @BeforeMethod in base class and removed this step from the main test
		ProductCatalogue pc = lp.loginApplication(input.get("email"), input.get("password"));
		//ProductCatalogue pc = new ProductCatalogue(driver);
		List<WebElement> productList = pc.getProductList();
		pc.getProductByName(input.get("requiredProduct"));
		pc.addProductToCart(input.get("requiredProduct"));

		// prod.findElement(By.xpath("//div[@class='card-body']/button[2]")).click();
		// prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// // CSS --> #id

		// wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		CartPage cp = pc.goToCartPage();
		
		//CartPage cp = new CartPage(driver); 
		
		Boolean match = cp.verifyProductDisplay(requiredProduct); 
		
		//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		//Boolean match = cartProducts.stream()
				//.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(requiredProduct));
		Assert.assertTrue(match);
		//driver.findElement(By.cssSelector(".totalRow button")).click();
		CheckoutPage chkPage = cp.goTocheckout();
		chkPage.selectCountry(countryInput,countryName);
		OrderConfirmationPage orderCp = chkPage.placeOrder(); 
		
		
		//orderCp.verifyOrderConfirmation();
		
		/*
		 * driver.findElement(By.xpath("//div[@class='form-group']/input")).click();
		 * driver.findElement(By.xpath("//div[@class='form-group']/input")).sendKeys(
		 * "ind"); List<WebElement> countries = driver .findElements(By.
		 * xpath("//section[@class='ta-results list-group ng-star-inserted']/button"));
		 * for (WebElement country : countries) { if
		 * (country.getText().equalsIgnoreCase("India")) { country.click(); break; } }
		 * driver.findElement(By.
		 * xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		 */
		
		/*
		 * wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(
		 * ".hero-primary")))); String confirmMessage =
		 * driver.findElement(By.cssSelector(".hero-primary")).getText();
		 */
		String confirmMationText = orderCp.verifyOrderConfirmation(); 
		Assert.assertTrue(confirmMationText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() 
	{
		//"ZARA COAT 3" is present
		ProductCatalogue pc = lp.loginApplication("iks@gmail.com", "Capital@10");
		OrderPage op = pc.goToOrdersPage();
		Assert.assertTrue(op.verifyOrderDisplay(requiredProduct)); 
	}
	
	public String getScreenshot(String testCaseName) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE); 
		File file = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png"; 
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException 
	{
		/*
		 * HashMap<String,String> map = new HashMap<String,String>(); map.put("email",
		 * "iks@gmail.com"); map.put("password", "Capital@10");
		 * map.put("requiredProduct", "ZARA COAT 3");
		 * 
		 * HashMap<String,String> map1 = new HashMap<String,String>(); map.put("email",
		 * "shetty@gmail.com"); map.put("password", "Iamking@000");
		 * map.put("requiredProduct", "ADIDAS ORIGINAL");
		 */
		 
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\bhartiacademy\\data\\PurchaseOrder.json"); 
		 return new Object[][] {{data.get(0)},{data.get(1)}}; 
	}
	

}
