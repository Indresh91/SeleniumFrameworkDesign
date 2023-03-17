package bhartiacademy;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import bhartiacakdemy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String requiredProduct = "ZARA COAT 3"; 
     WebDriverManager.chromedriver().setup();
     WebDriver driver= new ChromeDriver(); 
     LandingPage lp = new LandingPage(driver); 
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
     driver.get("https://rahulshettyacademy.com/client");
     driver.manage().window().maximize(); 
     driver.findElement(By.id("userEmail")).sendKeys("iks@gmail.com"); 
     driver.findElement(By.id("userPassword")).sendKeys("Capital@10"); 
     driver.findElement(By.xpath("//input[@class='btn btn-block login-btn']")).click(); 
     WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); 
      List<WebElement> productList =  driver.findElements(By.cssSelector(".mb-3")); 
      
      
      WebElement prod = productList.stream().filter(product->
      product.findElement(By.cssSelector("b")).getText().equals(requiredProduct)).findFirst().orElse(null);

      prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
     // prod.findElement(By.xpath("//div[@class='card-body']/button[2]")).click();   
     // prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); 
      
       
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));   // CSS --> #id
    
      wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); 
     driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
    List<WebElement> cartProducts =  driver.findElements(By.cssSelector(".cartSection h3"));
    Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(requiredProduct));
    Assert.assertTrue(match); 
    driver.findElement(By.cssSelector(".totalRow button")).click(); 
    driver.findElement(By.xpath("//div[@class='form-group']/input")).click(); 
    driver.findElement(By.xpath("//div[@class='form-group']/input")).sendKeys("ind"); 
    List<WebElement> countries = driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button")); 
    for (WebElement country : countries) 
    {
    	if (country.getText().equalsIgnoreCase("India")) 
    	{
    		country.click(); 
    		break; 
    	}
    }
    driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click(); 
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".hero-primary")))); 
    String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER.")); 
    driver.close(); 
     
	}

}
