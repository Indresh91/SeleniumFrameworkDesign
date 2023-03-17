package bhartiacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import bhartiacakdemy.pageobjects.CartPage;
import bhartiacakdemy.pageobjects.OrderPage;

public class AbstractComponents {

	WebDriver driver; 
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
    @FindBy (xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader; 
	
	@FindBy (xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader; 

	public void waitForElelmentToAppear(By findBy) {
	 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
     wait.until(ExpectedConditions.visibilityOfElementLocated(findBy)); 
	}	
	
	public CartPage goToCartPage() 
	{
	 cartHeader.click();	
	 CartPage cp = new CartPage(driver); 
	 return cp; 
	}
	
	public void waitForElementToDisappear(WebElement ele) 
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf((ele)));
		
	}
	
	public void waitForvisibilityOfElement(WebElement element) 
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf((element)));
	}
	
	public OrderPage goToOrdersPage() 
	{
		orderHeader.click();
		OrderPage op = new OrderPage(driver); 
		return op;  
	}
	
}
