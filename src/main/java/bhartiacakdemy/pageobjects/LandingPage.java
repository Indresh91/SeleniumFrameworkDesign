package bhartiacakdemy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bhartiacademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver; 
	public LandingPage(WebDriver driver)    // Constructor 
	{
		super(driver); 
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userName =  driver.findElement(By.id("userEmail")); 

	@FindBy(id = "userEmail")
	WebElement userName; 
	
	@FindBy (id ="userEmail")
	WebElement usernme; 
	
	@FindBy(id = "userPassword")
	WebElement passwordEle; 
	
	@FindBy(id = "login")
	WebElement submit; 
	@FindBy (css = "[class*='flyInOut']")
	WebElement errorMessage; 
	
	public ProductCatalogue loginApplication(String email, String password) 
	{
		
		userName.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue pc = new ProductCatalogue(driver); 
		return pc; 
	}
	public void goTo() 
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() 
	{
		waitForvisibilityOfElement(errorMessage); 
		return errorMessage.getText();
	}
}
